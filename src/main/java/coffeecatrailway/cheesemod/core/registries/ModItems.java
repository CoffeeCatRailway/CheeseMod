package coffeecatrailway.cheesemod.core.registries;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.core.special.ModArmorMaterial;
import coffeecatrailway.cheesemod.core.special.ModFoods;
import coffeecatrailway.cheesemod.core.special.ModItemTier;
import coffeecatrailway.cheesemod.items.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
public class ModItems {

    /// Ingots ///
    public static Item CHEESE_METAL_INGOT;
    public static Item GRILLED_CHEESE_METAL_INGOT;
    public static Item HAM_RAW_METAL_INGOT;
    public static Item HAM_COOKED_METAL_INGOT;

    /// Tool ///
    public static Item CHEESE_CUTTER;
    public static Item GRINDING_STONES;

    public static Item MILK_CURDLER;
    public static Item ROLLING_PIN;

    /// Tool - Knife ///
    public static Item KNIFE;
    public static Item CHEESE_KNIFE;
    public static Item GRILLED_CHEESE_KNIFE;
    public static Item HAM_RAW_KNIFE;
    public static Item HAM_COOKED_KNIFE;

    /// Tool - Cheese Metal ///
    public static Item CHEESE_METAL_PICKAXE;
    public static Item CHEESE_METAL_AXE;
    public static Item CHEESE_METAL_HOE;
    public static Item CHEESE_METAL_SHOVEL;
    public static Item CHEESE_METAL_SWORD;

    /// Tool - Grilled Cheese Metal ///
    public static Item GRILLED_CHEESE_METAL_PICKAXE;
    public static Item GRILLED_CHEESE_METAL_AXE;
    public static Item GRILLED_CHEESE_METAL_HOE;
    public static Item GRILLED_CHEESE_METAL_SHOVEL;
    public static Item GRILLED_CHEESE_METAL_SWORD;

    /// Tool - Ham Raw Metal ///
    public static Item HAM_RAW_METAL_PICKAXE;
    public static Item HAM_RAW_METAL_AXE;
    public static Item HAM_RAW_METAL_HOE;
    public static Item HAM_RAW_METAL_SHOVEL;
    public static Item HAM_RAW_METAL_SWORD;

    /// Tool - Ham Cooked Metal ///
    public static Item HAM_COOKED_METAL_PICKAXE;
    public static Item HAM_COOKED_METAL_AXE;
    public static Item HAM_COOKED_METAL_HOE;
    public static Item HAM_COOKED_METAL_SHOVEL;
    public static Item HAM_COOKED_METAL_SWORD;

    /// Armor - Cheese Metal ///
    public static Item CHEESE_METAL_HELMET;
    public static Item CHEESE_METAL_CHESTPLATE;
    public static Item CHEESE_METAL_LEGGINGS;
    public static Item CHEESE_METAL_BOOTS;

    /// Armor - Grilled Cheese Metal ///
    public static Item GRILLED_CHEESE_METAL_HELMET;
    public static Item GRILLED_CHEESE_METAL_CHESTPLATE;
    public static Item GRILLED_CHEESE_METAL_LEGGINGS;
    public static Item GRILLED_CHEESE_METAL_BOOTS;

    /// Armor - Ham Raw Metal ///
    public static Item HAM_RAW_METAL_HELMET;
    public static Item HAM_RAW_METAL_CHESTPLATE;
    public static Item HAM_RAW_METAL_LEGGINGS;
    public static Item HAM_RAW_METAL_BOOTS;

    /// Armor - Ham Cooked Metal ///
    public static Item HAM_COOKED_METAL_HELMET;
    public static Item HAM_COOKED_METAL_CHESTPLATE;
    public static Item HAM_COOKED_METAL_LEGGINGS;
    public static Item HAM_COOKED_METAL_BOOTS;

    /// Armor - Food Suit ///
    public static Item CHEESE_SUIT_HAT;
    public static Item CHEESE_SUIT_CHESTPLATE;

    public static Item HAM_SUIT_HAT;
    public static Item HAM_SUIT_CHESTPLATE;
    public static Item HAM_SUIT_BOOTS;

    /// Food ///
    public static Item BLOCK_O_CHEESE;
    public static Item CHEESE_SLICE;

    public static Item SALT;
    public static Item FLOUR;

    public static Item BACON_RAW;
    public static Item BACON_COOKED;

    public static Item BREAD_SLICE;

    public static Item DOUGH;

    /// Food - Egg ///
    public static Item EGG_CRACKED;
    public static Item EGG_COOKED;
    public static Item EGG_GREEN;

    /// Food - Ham ///
    public static Item HAM_RAW;
    public static Item HAM_COOKED;
    public static Item HAM_GREEN;

    /// Food - Toast ///
    public static Item TOAST;
    public static Item TOAST_FRENCH;
    public static Item TOAST_BACON;

    /// Food - Cracker ///
    public static Item CRACKER;
    public static Item CRACKER_CHEESE;
    public static Item CRACKER_CHEESE_GRILLED;
    public static Item CRACKER_HAM;
    public static Item CRACKER_HAM_GRILLED;
    public static Item CRACKER_CHEESE_HAM;
    public static Item CRACKER_CHEESE_HAM_GRILLED;

    /// Food - Toastie ///
    public static Item TOASTIE_CHEESE;
    public static Item TOASTIE_CHEESE_GRILLED;
    public static Item TOASTIE_CHEESE_HAM;
    public static Item TOASTIE_CHEESE_HAM_GRILLED;
    public static Item TOASTIE_CHEESE_HAM_COOKED;
    public static Item TOASTIE_CHEESE_HAM_COOKED_GRILLED;

    /// Food - Pineapple ///
    public static Item PINEAPPLE_PLANT;
    public static Item PINEAPPLE;
    public static Item PINEAPPLE_RING;
    public static Item PINEAPPLE_BIT;

    /// Food - Pizza ///
    public static Item PIZZA;
    public static Item PIZZA_CHEESE;
    public static Item PIZZA_CHEESE_COOKED;
    public static Item PIZZA_CHEESE_HAM;
    public static Item PIZZA_CHEESE_HAM_COOKED;
    public static Item PIZZA_CHEESE_HAM_PINEAPPLE;
    public static Item PIZZA_CHEESE_HAM_PINEAPPLE_COOKED;

    /// Other ///
    public static Item GRILLING_OIL;
    public static Item SMELTING_OIL;

    public static Item CHEESE_BIT;
    public static Item HAM_BIT;

    public static Item WOODEN_GEAR;

    static final Map<String, BlockItem> BLOCKS_TO_REGISTER = new LinkedHashMap<>();

    public static void registerAll(RegistryEvent.Register<Item> event) {
        if (!event.getName().equals(ForgeRegistries.ITEMS.getRegistryName())) return;
        BLOCKS_TO_REGISTER.forEach(ModItems::register);

        /// Ingots ///
        CHEESE_METAL_INGOT = register("cheese_metal_ingot", new Item(new Item.Properties().group(CheeseMod.GROUP_ALL)));
        GRILLED_CHEESE_METAL_INGOT = register("grilled_cheese_metal_ingot", new Item(new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_RAW_METAL_INGOT = register("ham_raw_metal_ingot", new Item(new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_COOKED_METAL_INGOT = register("ham_cooked_metal_ingot", new Item(new Item.Properties().group(CheeseMod.GROUP_ALL)));

        /// Tool ///
        CHEESE_CUTTER = register("cheese_cutter", new ModToolItem(1.5f, 2.5f, ItemTier.WOOD, new Item.Properties().group(CheeseMod.GROUP_ALL).maxStackSize(1)));
        GRINDING_STONES = register("grinding_stones", new ModToolItem(2.0f, 2.7f, ItemTier.STONE, new Item.Properties().group(CheeseMod.GROUP_ALL).maxStackSize(1)));

        MILK_CURDLER = register("milk_curdler", new ModToolItem(1.5f, 2.5f, ItemTier.WOOD, new Item.Properties().group(CheeseMod.GROUP_ALL).maxStackSize(1)));
        ROLLING_PIN = register("rolling_pin", new ModToolItem(1.5f, 2.5f, ItemTier.WOOD, new Item.Properties().group(CheeseMod.GROUP_ALL).maxStackSize(1)));

        /// Tool - Knife ///
        KNIFE = register("knife", new ModToolItem(3.5F, -2.1F, ItemTier.IRON, new Item.Properties().group(CheeseMod.GROUP_ALL).maxStackSize(1)));
        CHEESE_KNIFE = register("cheese_knife", new ModToolItem(5.0F, -2.7F, ModItemTier.CHEESE_METAL, new Item.Properties().group(CheeseMod.GROUP_ALL).maxStackSize(1)));
        GRILLED_CHEESE_KNIFE = register("grilled_cheese_knife", new ModToolItem(6.0F, -3.5F, ModItemTier.GRILLED_CHEESE_METAL, new Item.Properties().group(CheeseMod.GROUP_ALL).maxStackSize(1)));
        HAM_RAW_KNIFE = register("ham_raw_knife", new ModToolItem(4.7F, -2.9F, ModItemTier.HAM_RAW_METAL, new Item.Properties().group(CheeseMod.GROUP_ALL).maxStackSize(1)));
        HAM_COOKED_KNIFE = register("ham_cooked_knife", new ModToolItem(5.8F, -3.0F, ModItemTier.HAM_COOKED_METAL, new Item.Properties().group(CheeseMod.GROUP_ALL).maxStackSize(1)));

        /// Tool - Cheese Metal ///
        CHEESE_METAL_PICKAXE = register("cheese_metal_pickaxe", new PickaxeItem(ModItemTier.CHEESE_METAL, 1, -2.8f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        CHEESE_METAL_AXE = register("cheese_metal_axe", new AxeItem(ModItemTier.CHEESE_METAL, 6, -3.1f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        CHEESE_METAL_HOE = register("cheese_metal_hoe", new HoeItem(ModItemTier.CHEESE_METAL, -1.0f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        CHEESE_METAL_SHOVEL = register("cheese_metal_shovel", new ShovelItem(ModItemTier.CHEESE_METAL, 1.5f, -3.0f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        CHEESE_METAL_SWORD = register("cheese_metal_sword", new SwordItem(ModItemTier.CHEESE_METAL, 3, -2.4f, new Item.Properties().group(CheeseMod.GROUP_ALL)));

        /// Tool - Grilled Cheese Metal ///
        GRILLED_CHEESE_METAL_PICKAXE = register("grilled_cheese_metal_pickaxe", new PickaxeItem(ModItemTier.GRILLED_CHEESE_METAL, 2, -2.0f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        GRILLED_CHEESE_METAL_AXE = register("grilled_cheese_metal_axe", new AxeItem(ModItemTier.GRILLED_CHEESE_METAL, 7, -2.3f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        GRILLED_CHEESE_METAL_HOE = register("grilled_cheese_metal_hoe", new HoeItem(ModItemTier.GRILLED_CHEESE_METAL, -1.0f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        GRILLED_CHEESE_METAL_SHOVEL = register("grilled_cheese_metal_shovel", new ShovelItem(ModItemTier.GRILLED_CHEESE_METAL, 2.5f, -2.2f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        GRILLED_CHEESE_METAL_SWORD = register("grilled_cheese_metal_sword", new SwordItem(ModItemTier.GRILLED_CHEESE_METAL, 4, -1.6f, new Item.Properties().group(CheeseMod.GROUP_ALL)));

        /// Tool - Ham Raw Metal ///
        HAM_RAW_METAL_PICKAXE = register("ham_raw_metal_pickaxe", new PickaxeItem(ModItemTier.HAM_RAW_METAL, 1, -2.7f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_RAW_METAL_AXE = register("ham_raw_metal_axe", new AxeItem(ModItemTier.HAM_RAW_METAL, 6, -3.0f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_RAW_METAL_HOE = register("ham_raw_metal_hoe", new HoeItem(ModItemTier.HAM_RAW_METAL, -1.0f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_RAW_METAL_SHOVEL = register("ham_raw_metal_shovel", new ShovelItem(ModItemTier.HAM_RAW_METAL, 1.8f, -2.9f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_RAW_METAL_SWORD = register("ham_raw_metal_sword", new SwordItem(ModItemTier.HAM_RAW_METAL, 3, -2.3f, new Item.Properties().group(CheeseMod.GROUP_ALL)));

        /// Tool - Ham Cooked Metal ///
        HAM_COOKED_METAL_PICKAXE = register("ham_cooked_metal_pickaxe", new PickaxeItem(ModItemTier.HAM_COOKED_METAL, 2, -2.6f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_COOKED_METAL_AXE = register("ham_cooked_metal_axe", new AxeItem(ModItemTier.HAM_COOKED_METAL, 7, -2.9f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_COOKED_METAL_HOE = register("ham_cooked_metal_hoe", new HoeItem(ModItemTier.HAM_COOKED_METAL, -1.0f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_COOKED_METAL_SHOVEL = register("ham_cooked_metal_shovel", new ShovelItem(ModItemTier.HAM_COOKED_METAL, 2.9f, -2.8f, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_COOKED_METAL_SWORD = register("ham_cooked_metal_sword", new SwordItem(ModItemTier.HAM_COOKED_METAL, 4, -2.2f, new Item.Properties().group(CheeseMod.GROUP_ALL)));

        /// Armor - Cheese Metal ///
        CHEESE_METAL_HELMET = register("cheese_metal_helmet", new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.HEAD, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        CHEESE_METAL_CHESTPLATE = register("cheese_metal_chestplate", new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.CHEST, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        CHEESE_METAL_LEGGINGS = register("cheese_metal_leggings", new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.LEGS, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        CHEESE_METAL_BOOTS = register("cheese_metal_boots", new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.FEET, new Item.Properties().group(CheeseMod.GROUP_ALL)));

        /// Armor - Grilled Cheese Metal ///
        GRILLED_CHEESE_METAL_HELMET = register("grilled_cheese_metal_helmet", new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.HEAD, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        GRILLED_CHEESE_METAL_CHESTPLATE = register("grilled_cheese_metal_chestplate", new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.CHEST, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        GRILLED_CHEESE_METAL_LEGGINGS = register("grilled_cheese_metal_leggings", new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.LEGS, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        GRILLED_CHEESE_METAL_BOOTS = register("grilled_cheese_metal_boots", new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.FEET, new Item.Properties().group(CheeseMod.GROUP_ALL)));

        /// Armor - Ham Raw Metal ///
        HAM_RAW_METAL_HELMET = register("ham_raw_metal_helmet", new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.HEAD, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_RAW_METAL_CHESTPLATE = register("ham_raw_metal_chestplate", new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.CHEST, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_RAW_METAL_LEGGINGS = register("ham_raw_metal_leggings", new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.LEGS, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_RAW_METAL_BOOTS = register("ham_raw_metal_boots", new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.FEET, new Item.Properties().group(CheeseMod.GROUP_ALL)));

        /// Armor - Ham Cooked Metal ///
        HAM_COOKED_METAL_HELMET = register("ham_cooked_metal_helmet", new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.HEAD, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_COOKED_METAL_CHESTPLATE = register("ham_cooked_metal_chestplate", new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.CHEST, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_COOKED_METAL_LEGGINGS = register("ham_cooked_metal_leggings", new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.LEGS, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_COOKED_METAL_BOOTS = register("ham_cooked_metal_boots", new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.FEET, new Item.Properties().group(CheeseMod.GROUP_ALL)));

        /// Armor - Food Suit ///
        CHEESE_SUIT_HAT = register("cheese_suit_hat", new CheeseSuitArmorItem(ModArmorMaterial.CHEESE_SUIT, EquipmentSlotType.HEAD, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        CHEESE_SUIT_CHESTPLATE = register("cheese_suit_chestplate", new CheeseSuitArmorItem(ModArmorMaterial.CHEESE_SUIT, EquipmentSlotType.CHEST, new Item.Properties().group(CheeseMod.GROUP_ALL)));

        HAM_SUIT_HAT = register("ham_suit_hat", new HamSuitArmorItem(ModArmorMaterial.HAM_SUIT, EquipmentSlotType.HEAD, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_SUIT_CHESTPLATE = register("ham_suit_chestplate", new HamSuitArmorItem(ModArmorMaterial.HAM_SUIT, EquipmentSlotType.CHEST, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_SUIT_BOOTS = register("ham_suit_boots", new HamSuitArmorItem(ModArmorMaterial.HAM_SUIT, EquipmentSlotType.FEET, new Item.Properties().group(CheeseMod.GROUP_ALL)));

        /// Food ///
        BLOCK_O_CHEESE = registerFood("block_o_cheese", ModFoods.BLOCK_O_CHEESE, 16);
        CHEESE_SLICE = registerFood("cheese_slice", ModFoods.CHEESE_SLICE, 32);

        SALT = registerFood("salt", ModFoods.INGREDIENT, 64);
        FLOUR = registerFood("flour", ModFoods.INGREDIENT, 64);

        BACON_RAW = registerFood("bacon_raw", ModFoods.BACON, 32);
        BACON_COOKED = registerFood("bacon_cooked", ModFoods.BACON_COOKED, 32);

        BREAD_SLICE = registerFood("bread_slice", ModFoods.BREAD_SLICE, 64);

        DOUGH = registerFood("dough", ModFoods.DOUGH, 64);

        /// Food - Egg ///
        EGG_CRACKED = registerFood("egg_cracked", ModFoods.EGG, 32);
        EGG_COOKED = registerFood("egg_cooked", ModFoods.EGG_COOKED, 32);
        EGG_GREEN = register("egg_green", new GreenFoodItem(ModFoods.EGG_GREEN, 32));

        /// Food - Ham ///
        HAM_RAW = registerFood("ham_raw", ModFoods.HAM, 32);
        HAM_COOKED = registerFood("ham_cooked", ModFoods.HAM_COOKED, 32);
        HAM_GREEN = register("ham_green", new GreenFoodItem(ModFoods.HAM_GREEN, 32));

        /// Food - Toast ///
        TOAST = registerFood("toast", ModFoods.TOAST, 64);
        TOAST_FRENCH = registerFood("toast_french", ModFoods.TOAST_FRENCH, 64);
        TOAST_BACON = registerFood("toast_bacon", ModFoods.TOAST_BACON, 64);

        /// Food - Cracker ///
        CRACKER = registerFood("cracker", ModFoods.CRACKER, 32);
        CRACKER_CHEESE = registerFood("cracker_cheese", ModFoods.CRACKER_CHEESE, 32);
        CRACKER_CHEESE_GRILLED = registerFood("cracker_cheese_grilled", ModFoods.CRACKER_CHEESE_GRILLED, 32);
        CRACKER_HAM = registerFood("cracker_ham", ModFoods.CRACKER_HAM, 32);
        CRACKER_HAM_GRILLED = registerFood("cracker_ham_grilled", ModFoods.CRACKER_HAM_GRILLED, 32);
        CRACKER_CHEESE_HAM = registerFood("cracker_cheese_ham", ModFoods.CRACKER_CHEESE_HAM, 32);
        CRACKER_CHEESE_HAM_GRILLED = registerFood("cracker_cheese_ham_grilled", ModFoods.CRACKER_CHEESE_HAM_GRILLED, 32);

        /// Food - Toastie ///
        TOASTIE_CHEESE = register("toastie_cheese", new ToastieFoodItem(ModFoods.TOASTIE_CHEESE, 32));
        TOASTIE_CHEESE_GRILLED = register("toastie_cheese_grilled", new ToastieFoodItem(ModFoods.TOASTIE_CHEESE_GRILLED, 32).grilled());
        TOASTIE_CHEESE_HAM = register("toastie_cheese_ham", new ToastieFoodItem(ModFoods.TOASTIE_CHEESE_HAM, 32));
        TOASTIE_CHEESE_HAM_GRILLED = register("toastie_cheese_ham_grilled", new ToastieFoodItem(ModFoods.TOASTIE_CHEESE_HAM_GRILLED, 32).grilled());
        TOASTIE_CHEESE_HAM_COOKED = register("toastie_cheese_ham_cooked", new ToastieFoodItem(ModFoods.TOASTIE_CHEESE_HAM_COOKED, 32));
        TOASTIE_CHEESE_HAM_COOKED_GRILLED = register("toastie_cheese_ham_cooked_grilled", new ToastieFoodItem(ModFoods.TOASTIE_CHEESE_HAM_COOKED_GRILLED, 32).grilled());

        /// Food - Pineapple ///
        PINEAPPLE_PLANT = register("pineapple_plant", new BlockNamedItem(ModBlocks.PINEAPPLE, new Item.Properties().group(CheeseMod.GROUP_ALL)));
        PINEAPPLE = registerFood("pineapple", ModFoods.PINEAPPLE, 32);
        PINEAPPLE_RING = registerFood("pineapple_ring", ModFoods.PINEAPPLE_RING, 32);
        PINEAPPLE_BIT = registerFood("pineapple_bit", ModFoods.PINEAPPLE_BIT, 32);

        /// Food - Pizza ///
        PIZZA = registerFood("pizza", ModFoods.DOUGH, 32);
        PIZZA_CHEESE = registerFood("pizza_cheese", ModFoods.PIZZA_CHEESE, 32);
        PIZZA_CHEESE_COOKED = registerFood("pizza_cheese_cooked", ModFoods.PIZZA_CHEESE_COOKED, 32);
        PIZZA_CHEESE_HAM = registerFood("pizza_cheese_ham", ModFoods.PIZZA_CHEESE_HAM, 32);
        PIZZA_CHEESE_HAM_COOKED = registerFood("pizza_cheese_ham_cooked", ModFoods.PIZZA_CHEESE_HAM_COOKED, 32);
        PIZZA_CHEESE_HAM_PINEAPPLE = registerFood("pizza_cheese_ham_pineapple", ModFoods.PIZZA_CHEESE_HAM_PINEAPPLE, 32);
        PIZZA_CHEESE_HAM_PINEAPPLE_COOKED = registerFood("pizza_cheese_ham_pineapple_cooked", ModFoods.PIZZA_CHEESE_HAM_PINEAPPLE_COOKED, 32);

        /// Other ///
        CHEESE_BIT = register("cheese_bit", new Item(new Item.Properties().group(CheeseMod.GROUP_ALL)));
        HAM_BIT = register("ham_bit", new Item(new Item.Properties().group(CheeseMod.GROUP_ALL)));

        WOODEN_GEAR = register("wooden_gear", new Item(new Item.Properties().group(CheeseMod.GROUP_ALL)));

        CheeseMod.LOGGER.info("Items registered");
    }

    private static Item registerFood(String name, Food food, int stackSize) {
        return register(name, new Item(new Item.Properties().group(CheeseMod.GROUP_FOODS).food(food).maxStackSize(stackSize)));
    }

    private static <I extends Item> I register(String name, I item) {
        item.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
