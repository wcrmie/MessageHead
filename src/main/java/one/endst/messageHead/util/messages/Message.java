package one.endst.messageHead.util.messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {
    public static String create(MessageType type, String message) {
        return (ChatColor.DARK_GRAY + "[" + type.getIconColor() + type.getIcon() + ChatColor.DARK_GRAY + "] " + type.getMsgColor() + message);
    }

    public static void sendAll(String message) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.sendMessage(message);
        }
    }
}
