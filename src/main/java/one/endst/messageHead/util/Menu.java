package one.endst.messageHead.util;

import io.papermc.paper.datacomponent.DataComponentType;
import net.kyori.adventure.text.Component;
import one.endst.messageHead.chat.cosmetic.Voice;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Menu{

    private static final ItemStack menuItem = new ItemBuilder(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)).setName(" ").build();

    public static int calculateSlotClicked(int slot) {

        if (slot % 9 == 0 || slot % 9 == 8) {
            return -1; // Prevent clicking on the first and last column
        }
        if (slot < 10 || slot > 44) {
            return -1; // Prevent clicking on the first and last row
        }

        return slot - 8 - (slot / 9) * 2;

    }

    public static class MenuBuilder {

        int size;
        String title;
        InventoryHolder holder;

        public MenuBuilder(int size, String title, InventoryHolder holder) {

            this.size = size;
            this.title = title;
            this.holder = holder;

        }

        public Inventory build() {

            Inventory inventory = Bukkit.createInventory(holder, size, Component.text(title));

            for (int i = 0; i < size; i++) {
                if (calculateSlotClicked(i) == -1) {
                    inventory.setItem(i, menuItem);
                } else {
                    if (calculateSlotClicked(i) < Voice.values().length && calculateSlotClicked(i) >= 0) {

                        // Create an item for each voice
                        inventory.setItem(i, new ItemBuilder(Voice.values()[calculateSlotClicked(i)].getItem())
                                .setLore(ChatColor.GRAY + "[LEFT-CLICK] to select.", ChatColor.GRAY + "[RIGHT-CLICK] to preview sound.")
                                .setName(ChatColor.YELLOW + Voice.values()[calculateSlotClicked(i)].getName())
                                .build());

                    }
                }
            }
            return inventory;
        }


    }

    public static class ItemBuilder {

        private final ItemStack item;

        public ItemBuilder(ItemStack item) {
            this.item = item;
        }

        public ItemBuilder setName(String name) {
            if (name != null && !name.isEmpty()) {
                item.editMeta(meta -> meta.displayName(Component.text(name)));
            }
            return this;
        }

        public ItemBuilder setLore(String... lore) {
            if (lore != null && lore.length > 0) {
                item.editMeta(meta -> meta.lore(Arrays
                        .stream(lore)
                        .map(Component::text)
                        .toList()));
            }
            return this;
        }

        public <T> ItemBuilder addComponent(DataComponentType.Valued<T> type, T value) {

            item.setData(type, value);
            return this;

        }

        public ItemBuilder setAmount(int amount) {
            item.setAmount(amount);
            return this;
        }

        public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
            item.addUnsafeEnchantment(enchantment, level);
            return this;
        }

        public ItemStack build() {
            return item;
        }

    }
}
