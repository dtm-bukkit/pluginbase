package pluginbase.config.datasource.yaml;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.SimpleConfigurationNode;
import ninja.leaping.configurate.loader.AbstractConfigurationLoader;
import ninja.leaping.configurate.loader.CommentHandler;
import ninja.leaping.configurate.loader.CommentHandlers;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

class YamlConfigLoader extends AbstractConfigurationLoader<ConfigurationNode> {

    private final ThreadLocal<Yaml> yaml;
    private final boolean commentsEnabled;
    private DumperOptions options;
    private Representer representer;

    public static class Builder extends AbstractConfigurationLoader.Builder<Builder> {

        private final DumperOptions options = new DumperOptions();
        private boolean commentsEnabled = false;

        protected Builder() {
            setIndent(4);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        }

        public Builder setIndent(int indent) {
            options.setIndent(indent);
            return this;
        }

        public Builder setCommentsEnabled(boolean commentsEnabled) {
            this.commentsEnabled = commentsEnabled;
            return this;
        }

        /**
         * Sets the flow style for this configuration
         * Flow: the compact, json-like representation.<br>
         * Example: <code>
         *     {value: [list, of, elements], another: value}
         * </code>
         *
         * Block: expanded, traditional YAML<br>
         * Emample: <code>
         *     value:
         *     - list
         *     - of
         *     - elements
         *     another: value
         * </code>
         *
         * @param style the appropritae flow style to use
         * @return this
         */
        public Builder setFlowStyle(DumperOptions.FlowStyle style) {
            options.setDefaultFlowStyle(style);
            return this;
        }

        @Override
        public YamlConfigLoader build() {
            return new YamlConfigLoader(this, options, commentsEnabled);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private YamlConfigLoader(@NotNull Builder builder, final DumperOptions options, boolean doComments) {
        super(builder, new CommentHandler[] {CommentHandlers.HASH});
        this.commentsEnabled = doComments; // TODO fix broken comment instrumenter D:
        this.options = options;
        representer = new Representer();
        representer.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        this.yaml = new ThreadLocal<Yaml>() {
            @Override
            protected Yaml initialValue() {
                return new Yaml(representer, options);
            }
        };
    }

    @Override
    protected void loadInternal(ConfigurationNode node, BufferedReader reader) throws IOException {
        node.setValue(((Yaml)this.yaml.get()).load(reader));
    }

    @Override
    protected void saveInternal(ConfigurationNode node, Writer writer) throws IOException {
        if (commentsEnabled) {
            Object value = node.getValue();
            if (!(value instanceof Map)) {
                throw new IOException("Data must be in the form of a Map");
            }
            String dump = yaml.get().dump(value);
            YamlFileCommentInstrumenter commentInstrumenter = YamlCommentsMapper.createYamlCommentInstrumenter((Map<?, ?>) value, options.getIndent());
            dump = commentInstrumenter.addCommentsToYamlString(dump);
            writer.write(dump);
        } else {
            yaml.get().dump(node.getValue(), writer);
        }
    }

    @Override
    public ConfigurationNode createEmptyNode(ConfigurationOptions options) {
        return SimpleConfigurationNode.root(options);
    }
}
