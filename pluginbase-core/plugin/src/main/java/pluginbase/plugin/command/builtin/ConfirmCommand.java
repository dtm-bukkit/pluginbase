/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package pluginbase.plugin.command.builtin;

import org.jetbrains.annotations.NotNull;
import pluginbase.command.CommandContext;
import pluginbase.command.CommandHandler;
import pluginbase.command.CommandInfo;
import pluginbase.messages.Message;
import pluginbase.messages.Theme;
import pluginbase.minecraft.BasePlayer;
import pluginbase.permission.Perm;
import pluginbase.permission.PermFactory;
import pluginbase.plugin.PluginBase;

/**
 * Confirms queued commands.
 */
@CommandInfo(
        primaryAlias = "confirm",
        desc = "Confirms a previously entered command."
)
public class ConfirmCommand extends BuiltInCommand {

    public final static Message COMMAND_CONFIRM_HELP = Message.createMessage("cmd.confirm.help", Theme.HELP + "Confirms the usage of a previously entered command, if required.");

    private final Perm perm;

    protected ConfirmCommand(@NotNull final PluginBase plugin) {
        super(plugin);
        perm = PermFactory.newPerm(plugin.getPluginClass(), "cmd.confirm").usePluginName().commandPermission()
                .desc("If you have not been prompted to use this, it will not do anything.").build();
    }

    /** {@inheritDoc} */
    @Override
    public Perm getPerm() {
        return perm;
    }

    /** {@inheritDoc} */
    @Override
    public Message getHelp() {
        return COMMAND_CONFIRM_HELP;
    }

    /** {@inheritDoc} */
    @Override
    public boolean runCommand(@NotNull final BasePlayer sender, @NotNull final CommandContext context) {
        if (!getCommandProvider().getCommandHandler().confirmCommand(sender)) {
            getMessager().message(sender, CommandHandler.NO_QUEUED_COMMANDS);
        }
        return true;
    }
}
