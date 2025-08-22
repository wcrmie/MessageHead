package one.endst.messageHead.chat.cosmetic;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public enum Voice {

    STONE_BUTTON_CLICK_OFF("Stone Button", Sound.BLOCK_STONE_BUTTON_CLICK_OFF, ItemStack.of(Material.STONE_BUTTON)),
    WOODEN_BUTTON_CLICK_OFF("Wooden Button", Sound.BLOCK_WOODEN_BUTTON_CLICK_OFF, ItemStack.of(Material.OAK_BUTTON)),
    DISPENSER_DISPENSE("Dispenser", Sound.BLOCK_DISPENSER_DISPENSE, ItemStack.of(Material.DISPENSER)),
    STONE_BREAK("Stone Break", Sound.BLOCK_STONE_BREAK, ItemStack.of(Material.STONE)),
    BONE_BLOCK_BREAK("Bone Block Break", Sound.BLOCK_BONE_BLOCK_BREAK, ItemStack.of(Material.BONE_BLOCK)),
    ARMOR_STAND_HIT("Armor Stand Hit", Sound.ENTITY_ARMOR_STAND_HIT, ItemStack.of(Material.ARMOR_STAND)),
    WOOL_HIT("Wool Hit", Sound.BLOCK_WOOL_HIT, ItemStack.of(Material.PINK_WOOL)),
    BAMBOO_HIT("Bamboo Hit", Sound.BLOCK_BAMBOO_HIT, ItemStack.of(Material.BAMBOO)),
    AMETHYST_BLOCK_BREAK("Amethyst Block Break", Sound.BLOCK_AMETHYST_BLOCK_BREAK, ItemStack.of(Material.AMETHYST_BLOCK)),
    ENDER_EYE_DEATH("Ender Eye Death", Sound.ENTITY_ENDER_EYE_DEATH, ItemStack.of(Material.ENDER_EYE)),
    PLAYER_BURP("Burp", Sound.ENTITY_PLAYER_BURP, ItemStack.of(Material.COOKED_BEEF)),
    CAT_AMBIENT("Meow", Sound.ENTITY_CAT_AMBIENT, ItemStack.of(Material.CAT_SPAWN_EGG)),
    WOLF_AMBIENT("Bark", Sound.ENTITY_WOLF_AMBIENT, ItemStack.of(Material.WOLF_SPAWN_EGG)),
    SHEEP_DEATH("Sheep Baa", Sound.ENTITY_SHEEP_DEATH, ItemStack.of(Material.SHEEP_SPAWN_EGG)),
    BEE_HURT("Buzz", Sound.ENTITY_BEE_HURT, ItemStack.of(Material.BEEHIVE)),
    ARMADILLO_BRUSH("Armadillo Brush", Sound.ENTITY_ARMADILLO_BRUSH, ItemStack.of(Material.ARMADILLO_SCUTE)),
    CAMEL_DASH("Camel Dash", Sound.ENTITY_CAMEL_DASH, ItemStack.of(Material.CAMEL_SPAWN_EGG)),
    VILLAGER_TRADE("Villager", Sound.ENTITY_VILLAGER_TRADE, ItemStack.of(Material.EMERALD)),
    VILLAGER_NO("Angry Villager", Sound.ENTITY_VILLAGER_NO, ItemStack.of(Material.DIRT)),
    CREEPER_HURT("Creeper", Sound.ENTITY_CREEPER_HURT, ItemStack.of(Material.CREEPER_HEAD)),
    ZOMBIE_HURT("Zombie", Sound.ENTITY_ZOMBIE_HURT, ItemStack.of(Material.ZOMBIE_HEAD)),
    SKELETON_HURT("Skeleton", Sound.ENTITY_SKELETON_HURT, ItemStack.of(Material.SKELETON_SKULL)),
    ENDERMAN_HURT("Enderman", Sound.ENTITY_ENDERMAN_HURT, ItemStack.of(Material.ENDER_PEARL)),
    ENDER_DRAGON_HURT("Ender Dragon", Sound.ENTITY_ENDER_DRAGON_HURT, ItemStack.of(Material.DRAGON_HEAD)),
    WITCH_THROW("Witch Throw", Sound.ENTITY_WITCH_THROW, ItemStack.of(Material.SPLASH_POTION)),
    DECORATED_POT_INSERT("Pot Interact", Sound.BLOCK_DECORATED_POT_INSERT, ItemStack.of(Material.DECORATED_POT)),
    DECORATED_POT_INSERT_FAIL("Pot Interact Fail", Sound.BLOCK_DECORATED_POT_INSERT_FAIL, ItemStack.of(Material.DECORATED_POT));

    final String name;
    final Sound sound;
    @NotNull
    final ItemStack item;

    Voice(String name, Sound sound, @NotNull ItemStack item) {
        this.item = item;
        this.name = name;
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public Sound getSound() {
        return sound;
    }

    @NotNull
    public ItemStack getItem() {
        return item;
    }

}
