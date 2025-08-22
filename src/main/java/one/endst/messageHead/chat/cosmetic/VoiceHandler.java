package one.endst.messageHead.chat.cosmetic;

import one.endst.messageHead.MessageHead;
import one.endst.messageHead.util.messages.Message;
import one.endst.messageHead.util.messages.MessageType;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class VoiceHandler {

    public static Sound getVoice(Player player) {

        return Voice.values()[player.getPersistentDataContainer().getOrDefault(new NamespacedKey(MessageHead.getPlugin(), "voice"), PersistentDataType.INTEGER, 0)].getSound();

    }

    public static void setVoice(Player player, Voice voice) {

        player.getPersistentDataContainer().set(new NamespacedKey(MessageHead.getPlugin(), "voice"), PersistentDataType.INTEGER, voice.ordinal());
        player.sendMessage(Message.create(MessageType.SUCCESS, "Your voice has been set to " + voice.getName()));

    }

    public static float getPitch(Player player) {

        return player.getPersistentDataContainer().getOrDefault(new NamespacedKey(MessageHead.getPlugin(), "pitch"), PersistentDataType.FLOAT, 1.0F);

    }

    public static void setPitch(Player player, float pitch) {

        if (pitch > 2.0F) {
            pitch = 2.0F;
        }

        player.getPersistentDataContainer().set(new NamespacedKey(MessageHead.getPlugin(), "pitch"), PersistentDataType.FLOAT, pitch);
        player.sendMessage(Message.create(MessageType.SUCCESS, "Your voice pitch has been set to " + pitch));

    }

}
