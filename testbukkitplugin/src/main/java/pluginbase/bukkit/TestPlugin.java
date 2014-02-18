package pluginbase.bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;
import pluginbase.jdbc.JdbcAgent;
import pluginbase.jdbc.SpringDatabaseSettings;
import pluginbase.jdbc.SpringJdbcAgent;

import java.util.concurrent.Callable;

public class TestPlugin extends JavaPlugin {

    JdbcAgent jdbcAgent;

    BukkitPluginAgent pluginAgent = BukkitPluginAgent.getPluginAgent(this, "pb");

    public void onLoad() {
        pluginAgent.setJdbcAgentCallable(new Callable<JdbcAgent>() {
            @Override
            public JdbcAgent call() throws Exception {
                return jdbcAgent;
            }
        });
        pluginAgent.loadPluginBase();
    }

    @Override
    public void onEnable() {
        pluginAgent.enablePluginBase();
        //getCommandHandler().registerCommand(new MockQueuedCommand(this));
        try {
            jdbcAgent = SpringJdbcAgent.createAgent(pluginAgent.loadDatabaseSettings(new SpringDatabaseSettings()), getDataFolder(), getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onDisable() {
        pluginAgent.disablePluginBase();
    }

    @Nullable
    public JdbcAgent getJdbcAgent() {
        return pluginAgent.getPluginBase().getJdbcAgent();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return pluginAgent.callCommand(sender, command, label, args);
    }
}
