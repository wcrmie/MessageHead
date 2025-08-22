package one.endst.messageHead.commands;

import one.endst.messageHead.chat.cosmetic.VoiceHandler;
import one.endst.messageHead.util.messages.Message;
import one.endst.messageHead.util.messages.MessageType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetPitch implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (args.length < 1) {
            sender.sendMessage(Message.create(MessageType.ERROR, "Usage: /setpitch <pitch>"));
            return true;
        }

        if (sender instanceof Player player) {

            try {
                float pitch = Float.parseFloat(args[0]);

                if (pitch < 0 || pitch > 2) {
                    sender.sendMessage(Message.create(MessageType.ERROR, "Pitch must be between 0.0 and 2.0"));
                    return true;
                }

                VoiceHandler.setPitch(player, pitch);
                return true;
            } catch (NumberFormatException e) {
                sender.sendMessage(Message.create(MessageType.ERROR, "Invalid pitch value. Please enter a number between 0.0 and 2.0"));
                return true;
            }
        }

        sender.sendMessage(Message.create(MessageType.ERROR, "This command can only be used by players!"));

        return false;
    }
}
