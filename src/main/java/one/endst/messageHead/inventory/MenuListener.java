package one.endst.messageHead.inventory;

import one.endst.messageHead.util.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(org.bukkit.event.inventory.InventoryClickEvent event) {

        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        InventoryHolder holder = inventory.getHolder();

        if (holder instanceof VoiceMenu menu) {
            event.setCancelled(true); // Prevent default behavior

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != org.bukkit.Material.AIR) {

                if (event.getAction().equals(InventoryAction.PICKUP_ALL)) {

                    menu.handleLeftClick(player, event.getSlot());

                }

                if (event.getAction().equals(InventoryAction.PICKUP_HALF)) {

                    menu.handleRightClick(player, event.getSlot());

                }
            }
        }
    }
}
