package pluginbase.command;

import org.jetbrains.annotations.NotNull;
import pluginbase.messages.BundledMessage;
import pluginbase.messages.PluginBaseException;
import pluginbase.messages.messaging.SendablePluginBaseException;

/**
 * Used when commands throw exceptions.
 */
public class CommandException extends SendablePluginBaseException {

    public CommandException(@NotNull final BundledMessage languageMessage) {
        super(languageMessage);
    }

    public CommandException(@NotNull final BundledMessage languageMessage, @NotNull final Throwable throwable) {
        super(languageMessage, throwable);
    }

    public CommandException(@NotNull final BundledMessage languageMessage, @NotNull final PluginBaseException cause) {
        super(languageMessage, cause);
    }

    public CommandException(@NotNull final PluginBaseException e) {
        super(e);
    }
}
