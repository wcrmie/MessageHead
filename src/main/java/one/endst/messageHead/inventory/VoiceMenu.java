package one.endst.messageHead.inventory;

import one.endst.messageHead.chat.cosmetic.Voice;
import one.endst.messageHead.chat.cosmetic.VoiceHandler;
import one.endst.messageHead.util.Menu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import static one.endst.messageHead.util.Menu.calculateSlotClicked;

public class VoiceMenu implements InventoryHolder {

    private final Inventory inventory;
    private int page;

    public VoiceMenu(int page) {
        this.inventory = new Menu.MenuBuilder(54, "Voice Menu", this).build();
        this.page = page;
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public void handleLeftClick(Player player, int slot) {
        if (slot < 0 || slot >= inventory.getSize()) return; // Check if the slot is valid

        int clickedSlot = calculateSlotClicked(slot);
        if (clickedSlot == -1) return; // Ignore clicks on the first and last column

        // Handle the click event here
        if (Voice.values()[clickedSlot] != null) {

            player.playSound(player, Voice.values()[clickedSlot].getSound(), 1, VoiceHandler.getPitch(player));
            VoiceHandler.setVoice(player, Voice.values()[clickedSlot]);

        }
    }

    public void handleRightClick(Player player, int slot) {
        if (slot < 0 || slot >= inventory.getSize()) return; // Check if the slot is valid

        int clickedSlot = calculateSlotClicked(slot);
        if (clickedSlot == -1) return; // Ignore clicks on the first and last column

        // Handle the right-click event here
        if (Voice.values()[clickedSlot] != null) {

            player.playSound(player, Voice.values()[clickedSlot].getSound(), 1, VoiceHandler.getPitch(player));

        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
