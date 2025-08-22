package one.endst.messageHead.commands;

import com.google.common.base.Joiner;
import one.endst.messageHead.chat.cosmetic.Voice;
import one.endst.messageHead.chat.cosmetic.VoiceHandler;
import one.endst.messageHead.util.messages.Message;
import one.endst.messageHead.util.messages.MessageType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetVoice implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player) {

            if (args.length < 1) {
                sender.sendMessage(Message.create(MessageType.ERROR, "Usage: /setvoice <voice>"));
                return true;
            }

            String voice = args[0];

            // Try parsing as integer
            try {

                int voiceIndex = Integer.parseInt(voice);

                if (voiceIndex < 0 || voiceIndex >= Voice.values().length) {
                    sender.sendMessage(Message.create(MessageType.ERROR, "Invalid voice index! Please choose a number between 0 and " + (Voice.values().length - 1)));
                    return true;
                }

                VoiceHandler.setVoice(player, Voice.values()[voiceIndex]);


            } catch (NumberFormatException e) {
                // Maybe they are trying to use a voice name

                String voiceName = Joiner.on(" ").join(args);

                for (Voice v : Voice.values()) {
                    if (v.name().equalsIgnoreCase(voiceName)) {
                        VoiceHandler.setVoice(player, v);
                        return true;
                    }
                }

                player.sendMessage(Message.create(MessageType.ERROR, "Invalid voice! You may either use the voice name or index."));

                return true;
            }

        } else {
            sender.sendMessage(Message.create(MessageType.ERROR, "This command can only be used by players!"));
            return true;
        }

        return false;
    }
}
