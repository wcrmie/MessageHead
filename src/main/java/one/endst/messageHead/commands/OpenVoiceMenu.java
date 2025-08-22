package one.endst.messageHead.commands;

import one.endst.messageHead.inventory.VoiceMenu;
import one.endst.messageHead.util.messages.Message;
import one.endst.messageHead.util.messages.MessageType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenVoiceMenu implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player) {

            new VoiceMenu(1).open(player);
            return true;

        }

        sender.sendMessage(Message.create(MessageType.ERROR, "This command can only be used by players!"));
        return true;
    }
}
