package one.endst.messageHead.chat;

import one.endst.messageHead.MessageHead;
import one.endst.messageHead.util.ConfigFetcher;
import one.endst.messageHead.util.messages.Message;
import one.endst.messageHead.util.messages.MessageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onChat(PlayerChatEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.length() > MessageHead.getPlugin().getConfigFetcher().getCharLimit()) {

            player.sendMessage(Message.create(MessageType.FAIL, "Your message must be less than " + MessageHead.getPlugin().getConfigFetcher().getCharLimit() + " characters long."));
            event.setCancelled(true);
            return;

        }

        Display.queueMessage(message, player);

    }

    @EventHandler
    public void onQuit(org.bukkit.event.player.PlayerQuitEvent event) {

        Display.purge(event.getPlayer());

    }
}
