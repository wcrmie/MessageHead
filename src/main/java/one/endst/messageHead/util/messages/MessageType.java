package one.endst.messageHead.util.messages;

import org.bukkit.ChatColor;

public enum MessageType {
    FAIL(ChatColor.RED, "❌", ChatColor.RED),
    ERROR(ChatColor.GOLD, "⚠", ChatColor.YELLOW),
    SUCCESS(ChatColor.GREEN, "✔", ChatColor.GREEN),
    GENERIC(ChatColor.BLUE, "ℹ", ChatColor.WHITE),
    ANNOUNCEMENT(ChatColor.GOLD, "ANNOUNCEMENT", ChatColor.YELLOW);

    final ChatColor iconColor;
    final String icon;
    final ChatColor color;

    MessageType(ChatColor iconColor, String icon, ChatColor msgColor) {
        this.iconColor = iconColor;
        this.icon = icon;
        this.color = msgColor;
    }

    public ChatColor getIconColor() {
        return iconColor;
    }

    public String getIcon() {
        return icon;
    }

    public ChatColor getMsgColor() {
        return color;
    }
}
