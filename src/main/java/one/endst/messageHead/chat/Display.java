package one.endst.messageHead.chat;

import one.endst.messageHead.MessageHead;
import one.endst.messageHead.chat.cosmetic.VoiceHandler;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Queue;

public class Display {

    private static final HashMap<Player, Queue<String>> messageQueue = new HashMap<>();
    private static final HashMap<Player, ArmorStand> messageDisplays = new HashMap<>();
    private static final HashMap<ArmorStand, ArmorStand> newLines = new HashMap<>();

    public static void queueMessage(String message, Player player) {

        Queue<String> queue = messageQueue.get(player);

        if (queue == null) {
            queue = new java.util.LinkedList<>();
        }

        queue.offer(message);
        messageQueue.put(player, queue);

        // If there are no active displays for the player, display the next message
        if (messageDisplays.get(player) == null) {
            displayNextMessage(player);
        }
    }

    public static void displayNextMessage(Player player) {

        Queue<String> queue = messageQueue.get(player);

        if (queue != null && !queue.isEmpty()) {
            String message = queue.poll();
            if (message != null) {
                createMessage(message, player);
            }
        }
    }

    public static void createMessage(String message, Player player) {
        messageDisplays.put(player, createDisplay(player));

        ArmorStand display = messageDisplays.get(player);

        messageLoop(message, player, display);
    }

    public static ArmorStand createDisplay(Player player) {
        ArmorStand display = player.getWorld().spawn(player.getLocation().add(0, 2, 0), ArmorStand.class);
        display.setGravity(false);
        display.setInvisible(true);
        display.setMarker(true);

        return display;
    }

    public static void messageLoop(String message, Player player, ArmorStand display) {

        if (display == null || !player.isOnline()) {
            purge(player);
        }

        World world = player.getWorld();

        new BukkitRunnable() {

            private int chars = 0;
            private int total = 0;
            private String chat = "";

            @Override
            public void run() {

                char currentChar = message.charAt(total);

                Location location = player.getLocation().add(0, 2, 0);
                if (!("" + currentChar).equals(" ")) world.playSound(location, VoiceHandler.getVoice(player), 0.5F, VoiceHandler.getPitch(player));

                // Teleport armor stand to player, if it exists
                if (!messageTeleport(player)) {
                    cancel();
                }

                // When loop is finished
                if (total + 1 >= message.length()) {

                    // Get rid of display
                    messageFade(display, player);
                    cancel();
                }

                // New line
                if (chars >= 32 && ("" + currentChar).equals(" ")){
                    messageNewLine(display, world);
                    chars = 0;
                    chat = "";
                } else if (chars >= 50) {
                    messageNewLine(display, world);
                    chars = 0;
                    chat = "";
                }

                // Add character to chat
                chat += currentChar;
                display.setCustomName(chat);
                display.setCustomNameVisible(true);

                chars++;
                total++;

            }
        }.runTaskTimer(MessageHead.getPlugin(), 0L, 1L);
    }

    public static void messageNewLine(ArmorStand a, World world){
        if (!messageHasLines(a)){
            ArmorStand newLine = world.spawn(a.getLocation(), ArmorStand.class);
            newLine.setVisible(false);
            newLine.setGravity(false);
            newLine.setCustomName(a.getCustomName());
            newLine.setCustomNameVisible(true);
            newLine.setInvulnerable(true);
            newLine.setMarker(true);
            newLines.put(a, newLine);
        } else {
            messageNewLine(newLines.get(a), world);
            newLines.get(a).setCustomName(a.getCustomName());
        }
    }

    public static void messageNewLineTp(ArmorStand a, ArmorStand nl){
        Location loc = a.getLocation().add(0, 0.3, 0);
        nl.teleport(loc);
        if (messageHasLines(nl)) {
            messageNewLineTp(nl, newLines.get(nl));
        }
    }

    public static void lineDel(ArmorStand a){
        ArmorStand nl = newLines.get(a);
        if (messageHasLines(nl)) {
            lineDel(nl);
        }
        if (nl != null) nl.remove();
    }

    public static boolean messageHasLines(ArmorStand a){
        return newLines.get(a) != null;
    }

    public static boolean messageTeleport(Player p) {
        if (!(messageDisplays.get(p) == null)){
            ArmorStand a = messageDisplays.get(p);
            a.teleport(p.getLocation().add(0, 2, 0));
            if (messageHasLines(a)) {
                messageNewLineTp(a, newLines.get(a));
            }
            return true;
        } else return false;
    }

    public static void messageFade(ArmorStand a, Player p){
        BukkitTask balls = new BukkitRunnable(){
            @Override
            public void run(){
                if (!messageTeleport(p)) {
                    cancel();
                }
            }
        }.runTaskTimer(MessageHead.getPlugin(), 1, 1);
        new BukkitRunnable(){
            @Override
            public void run(){
                balls.cancel();
                lineDel(a);
                a.remove();
                messageDisplays.remove(p);
                if (messageQueue.get(p).peek() != null) {
                    displayNextMessage(p);
                } else cancel();
            }
        }.runTaskLater(MessageHead.getPlugin(), 60L);
    }

    public static void purge(Player player) {

        // Get rid of the display and remove it from the map
        ArmorStand display = messageDisplays.remove(player);
        lineDel(display);
        if (display != null) display.remove();

        // Remove the player from the message queue
        messageQueue.remove(player);

    }
}
