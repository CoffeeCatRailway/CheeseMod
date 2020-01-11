package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.ModItemGroups;
import coffeecatrailway.coffeecheese.common.entity.item.BoatEntityCM;
import coffeecatrailway.coffeecheese.common.item.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
@SuppressWarnings("unused")
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, CheeseMod.MOD_ID);
    public static final List<RegistryObject<Item>> FOODS = new ArrayList<RegistryObject<Item>>();

    /// Ingots ///
    public static final RegistryObject<Item> CHEESE_METAL_INGOT = ITEMS.register("cheese_metal_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.GROUP_ALL)));
    public static final RegistryObject<Item> GRILLED_CHEESE_METAL_INGOT = ITEMS.register("grilled_cheese_metal_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.GROUP_ALL)));
    public static final RegistryObject<Item> HAM_RAW_METAL_INGOT = ITEMS.register("ham_raw_metal_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.GROUP_ALL)));
    public static final RegistryObject<Item> HAM_COOKED_METAL_INGOT = ITEMS.register("ham_cooked_metal_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.GROUP_ALL)));

    /// Tool ///
    public static final RegistryObject<Item> CHEESE_CUTTER = ITEMS.register("cheese_cutter", () -> new CraftToolItem(ItemTier.WOOD, 1, 2.5f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS).maxStackSize(1)));
    public static final RegistryObject<Item> GRINDING_STONES = ITEMS.register("grinding_stones", () -> new CraftToolItem(ItemTier.STONE, 2, 2.7f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS).maxStackSize(1)));

    public static final RegistryObject<Item> MILK_CURDLER = ITEMS.register("milk_curdler", () -> new CraftToolItem(ItemTier.WOOD, 2, 2.5f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS).maxStackSize(1)));
    public static final RegistryObject<Item> ROLLING_PIN = ITEMS.register("rolling_pin", () -> new CraftToolItem(ItemTier.WOOD, 1, 2.5f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS).maxStackSize(1)));

    /// Tool - Knife ///
    public static final RegistryObject<Item> KNIFE = ITEMS.register("knife", () -> new CraftToolItem(ItemTier.IRON, 4, -2.1f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS).maxStackSize(1)));
    public static final RegistryObject<Item> CHEESE_KNIFE = ITEMS.register("cheese_knife", () -> new CraftToolItem(ModItemTier.CHEESE_METAL, 5, -2.7f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS).maxStackSize(1)));
    public static final RegistryObject<Item> GRILLED_CHEESE_KNIFE = ITEMS.register("grilled_cheese_knife", () -> new CraftToolItem(ModItemTier.GRILLED_CHEESE_METAL, 6, -3.5f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS).maxStackSize(1)));
    public static final RegistryObject<Item> HAM_RAW_KNIFE = ITEMS.register("ham_raw_knife", () -> new CraftToolItem(ModItemTier.HAM_RAW_METAL, 5, -2.9f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS).maxStackSize(1)));
    public static final RegistryObject<Item> HAM_COOKED_KNIFE = ITEMS.register("ham_cooked_knife", () -> new CraftToolItem(ModItemTier.HAM_COOKED_METAL, 6, -3.0f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS).maxStackSize(1)));

    /// Tool - Cheese Metal ///
    public static final RegistryObject<Item> CHEESE_METAL_PICKAXE = ITEMS.register("cheese_metal_pickaxe", () -> new PickaxeItem(ModItemTier.CHEESE_METAL, 1, -2.8f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> CHEESE_METAL_AXE = ITEMS.register("cheese_metal_axe", () -> new AxeItem(ModItemTier.CHEESE_METAL, 6, -3.1f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> CHEESE_METAL_HOE = ITEMS.register("cheese_metal_hoe", () -> new HoeItem(ModItemTier.CHEESE_METAL, -1.0f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> CHEESE_METAL_SHOVEL = ITEMS.register("cheese_metal_shovel", () -> new ShovelItem(ModItemTier.CHEESE_METAL, 1.5f, -3.0f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> CHEESE_METAL_SWORD = ITEMS.register("cheese_metal_sword", () -> new SwordItem(ModItemTier.CHEESE_METAL, 3, -2.4f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));

    /// Tool - Grilled Cheese Metal ///
    public static final RegistryObject<Item> GRILLED_CHEESE_METAL_PICKAXE = ITEMS.register("grilled_cheese_metal_pickaxe", () -> new PickaxeItem(ModItemTier.GRILLED_CHEESE_METAL, 2, -2.0f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> GRILLED_CHEESE_METAL_AXE = ITEMS.register("grilled_cheese_metal_axe", () -> new AxeItem(ModItemTier.GRILLED_CHEESE_METAL, 7, -2.3f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> GRILLED_CHEESE_METAL_HOE = ITEMS.register("grilled_cheese_metal_hoe", () -> new HoeItem(ModItemTier.GRILLED_CHEESE_METAL, -1.0f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> GRILLED_CHEESE_METAL_SHOVEL = ITEMS.register("grilled_cheese_metal_shovel", () -> new ShovelItem(ModItemTier.GRILLED_CHEESE_METAL, 2.5f, -2.2f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> GRILLED_CHEESE_METAL_SWORD = ITEMS.register("grilled_cheese_metal_sword", () -> new SwordItem(ModItemTier.GRILLED_CHEESE_METAL, 4, -1.6f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));

    /// Tool - Ham Raw Metal ///
    public static final RegistryObject<Item> HAM_RAW_METAL_PICKAXE = ITEMS.register("ham_raw_metal_pickaxe", () -> new PickaxeItem(ModItemTier.HAM_RAW_METAL, 1, -2.7f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_RAW_METAL_AXE = ITEMS.register("ham_raw_metal_axe", () -> new AxeItem(ModItemTier.HAM_RAW_METAL, 6, -3.0f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_RAW_METAL_HOE = ITEMS.register("ham_raw_metal_hoe", () -> new HoeItem(ModItemTier.HAM_RAW_METAL, -1.0f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_RAW_METAL_SHOVEL = ITEMS.register("ham_raw_metal_shovel", () -> new ShovelItem(ModItemTier.HAM_RAW_METAL, 1.8f, -2.9f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_RAW_METAL_SWORD = ITEMS.register("ham_raw_metal_sword", () -> new SwordItem(ModItemTier.HAM_RAW_METAL, 3, -2.3f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));

    /// Tool - Ham Cooked Metal ///
    public static final RegistryObject<Item> HAM_COOKED_METAL_PICKAXE = ITEMS.register("ham_cooked_metal_pickaxe", () -> new PickaxeItem(ModItemTier.HAM_COOKED_METAL, 2, -2.6f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_COOKED_METAL_AXE = ITEMS.register("ham_cooked_metal_axe", () -> new AxeItem(ModItemTier.HAM_COOKED_METAL, 7, -2.9f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_COOKED_METAL_HOE = ITEMS.register("ham_cooked_metal_hoe", () -> new HoeItem(ModItemTier.HAM_COOKED_METAL, -1.0f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_COOKED_METAL_SHOVEL = ITEMS.register("ham_cooked_metal_shovel", () -> new ShovelItem(ModItemTier.HAM_COOKED_METAL, 2.9f, -2.8f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_COOKED_METAL_SWORD = ITEMS.register("ham_cooked_metal_sword", () -> new SwordItem(ModItemTier.HAM_COOKED_METAL, 4, -2.2f, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));

    /// Armor - Cheese Metal ///
    public static final RegistryObject<Item> CHEESE_METAL_HELMET = ITEMS.register("cheese_metal_helmet", () -> new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> CHEESE_METAL_CHESTPLATE = ITEMS.register("cheese_metal_chestplate", () -> new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> CHEESE_METAL_LEGGINGS = ITEMS.register("cheese_metal_leggings", () -> new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> CHEESE_METAL_BOOTS = ITEMS.register("cheese_metal_boots", () -> new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));

    /// Armor - Grilled Cheese Metal ///
    public static final RegistryObject<Item> GRILLED_CHEESE_METAL_HELMET = ITEMS.register("grilled_cheese_metal_helmet", () -> new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> GRILLED_CHEESE_METAL_CHESTPLATE = ITEMS.register("grilled_cheese_metal_chestplate", () -> new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> GRILLED_CHEESE_METAL_LEGGINGS = ITEMS.register("grilled_cheese_metal_leggings", () -> new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> GRILLED_CHEESE_METAL_BOOTS = ITEMS.register("grilled_cheese_metal_boots", () -> new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));

    /// Armor - Ham Raw Metal ///
    public static final RegistryObject<Item> HAM_RAW_METAL_HELMET = ITEMS.register("ham_raw_metal_helmet", () -> new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_RAW_METAL_CHESTPLATE = ITEMS.register("ham_raw_metal_chestplate", () -> new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_RAW_METAL_LEGGINGS = ITEMS.register("ham_raw_metal_leggings", () -> new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_RAW_METAL_BOOTS = ITEMS.register("ham_raw_metal_boots", () -> new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));

    /// Armor - Ham Cooked Metal ///
    public static final RegistryObject<Item> HAM_COOKED_METAL_HELMET = ITEMS.register("ham_cooked_metal_helmet", () -> new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_COOKED_METAL_CHESTPLATE = ITEMS.register("ham_cooked_metal_chestplate", () -> new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_COOKED_METAL_LEGGINGS = ITEMS.register("ham_cooked_metal_leggings", () -> new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_COOKED_METAL_BOOTS = ITEMS.register("ham_cooked_metal_boots", () -> new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));

    /// Armor - Food Suit ///
    public static final RegistryObject<Item> CHEESE_SUIT_HAT = ITEMS.register("cheese_suit_hat", () -> new CheeseSuitArmorItem(ModArmorMaterial.CHEESE_SUIT, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> CHEESE_SUIT_CHESTPLATE = ITEMS.register("cheese_suit_chestplate", () -> new CheeseSuitArmorItem(ModArmorMaterial.CHEESE_SUIT, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));

    public static final RegistryObject<Item> HAM_SUIT_HAT = ITEMS.register("ham_suit_hat", () -> new HamSuitArmorItem(ModArmorMaterial.HAM_SUIT, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_SUIT_CHESTPLATE = ITEMS.register("ham_suit_chestplate", () -> new HamSuitArmorItem(ModArmorMaterial.HAM_SUIT, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));
    public static final RegistryObject<Item> HAM_SUIT_BOOTS = ITEMS.register("ham_suit_boots", () -> new HamSuitArmorItem(ModArmorMaterial.HAM_SUIT, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)));

    /// Food ///
    public static final RegistryObject<Item> BLOCK_O_CHEESE = registerItemFood("block_o_cheese", ModFoods.BLOCK_O_CHEESE, 16);
    public static final RegistryObject<Item> CHEESE_BALL = ITEMS.register("cheese_ball", () -> new CheeseBallItem(new Item.Properties().food(ModFoods.CHEESE_BALL).maxStackSize(16).group(ModItemGroups.GROUP_FOODS)));

    public static final RegistryObject<Item> CHEESE_SLICE = registerItemFood("cheese_slice", ModFoods.CHEESE_SLICE, 32);

    public static final RegistryObject<Item> SALT = registerItemFood("salt", ModFoods.INGREDIENT, 64);
    public static final RegistryObject<Item> FLOUR = registerItemFood("flour", ModFoods.INGREDIENT, 64);

    public static final RegistryObject<Item> BACON_RAW = registerItemFood("bacon_raw", ModFoods.BACON, 32);
    public static final RegistryObject<Item> BACON_COOKED = registerItemFood("bacon_cooked", ModFoods.BACON_COOKED, 32);

    public static final RegistryObject<Item> BREAD_SLICE = registerItemFood("bread_slice", ModFoods.BREAD_SLICE, 64);

    public static final RegistryObject<Item> DOUGH = registerItemFood("dough", ModFoods.DOUGH, 64);

    /// Food - Egg ///
    public static final RegistryObject<Item> EGG_CRACKED = registerItemFood("egg_cracked", ModFoods.EGG, 32);
    public static final RegistryObject<Item> EGG_COOKED = registerItemFood("egg_cooked", ModFoods.EGG_COOKED, 32);
    public static final RegistryObject<Item> EGG_GREEN = ITEMS.register("egg_green", () -> new GreenFoodItem(ModFoods.EGG_GREEN, 32));

    /// Food - Ham ///
    public static final RegistryObject<Item> HAM_RAW = registerItemFood("ham_raw", ModFoods.HAM, 32);
    public static final RegistryObject<Item> HAM_COOKED = registerItemFood("ham_cooked", ModFoods.HAM_COOKED, 32);
    public static final RegistryObject<Item> HAM_GREEN = ITEMS.register("ham_green", () -> new GreenFoodItem(ModFoods.HAM_GREEN, 32));

    /// Food - Toast ///
    public static final RegistryObject<Item> TOAST = registerItemFood("toast", ModFoods.TOAST, 64);
    public static final RegistryObject<Item> TOAST_FRENCH = registerItemFood("toast_french", ModFoods.TOAST_FRENCH, 64);
    public static final RegistryObject<Item> TOAST_BACON = registerItemFood("toast_bacon", ModFoods.TOAST_BACON, 64);

    /// Food - Cracker ///
    public static final RegistryObject<Item> CRACKER = registerItemFood("cracker", ModFoods.CRACKER, 32);
    public static final RegistryObject<Item> CRACKER_CHEESE = registerItemFood("cracker_cheese", ModFoods.CRACKER_CHEESE, 32);
    public static final RegistryObject<Item> CRACKER_CHEESE_GRILLED = registerItemFood("cracker_cheese_grilled", ModFoods.CRACKER_CHEESE_GRILLED, 32);
    public static final RegistryObject<Item> CRACKER_HAM = registerItemFood("cracker_ham", ModFoods.CRACKER_HAM, 32);
    public static final RegistryObject<Item> CRACKER_HAM_GRILLED = registerItemFood("cracker_ham_grilled", ModFoods.CRACKER_HAM_GRILLED, 32);
    public static final RegistryObject<Item> CRACKER_CHEESE_HAM = registerItemFood("cracker_cheese_ham", ModFoods.CRACKER_CHEESE_HAM, 32);
    public static final RegistryObject<Item> CRACKER_CHEESE_HAM_GRILLED = registerItemFood("cracker_cheese_ham_grilled", ModFoods.CRACKER_CHEESE_HAM_GRILLED, 32);

    /// Food - Toastie ///
    public static final RegistryObject<Item> TOASTIE_CHEESE = ITEMS.register("toastie_cheese", () -> new ToastieFoodItem(ModFoods.TOASTIE_CHEESE, 32));
    public static final RegistryObject<Item> TOASTIE_CHEESE_GRILLED = ITEMS.register("toastie_cheese_grilled", () -> new ToastieFoodItem(ModFoods.TOASTIE_CHEESE_GRILLED, 32).grilled());
    public static final RegistryObject<Item> TOASTIE_CHEESE_HAM = ITEMS.register("toastie_cheese_ham", () -> new ToastieFoodItem(ModFoods.TOASTIE_CHEESE_HAM, 32));
    public static final RegistryObject<Item> TOASTIE_CHEESE_HAM_GRILLED = ITEMS.register("toastie_cheese_ham_grilled", () -> new ToastieFoodItem(ModFoods.TOASTIE_CHEESE_HAM_GRILLED, 32).grilled());
    public static final RegistryObject<Item> TOASTIE_CHEESE_HAM_COOKED = ITEMS.register("toastie_cheese_ham_cooked", () -> new ToastieFoodItem(ModFoods.TOASTIE_CHEESE_HAM_COOKED, 32));
    public static final RegistryObject<Item> TOASTIE_CHEESE_HAM_COOKED_GRILLED = ITEMS.register("toastie_cheese_ham_cooked_grilled", () -> new ToastieFoodItem(ModFoods.TOASTIE_CHEESE_HAM_COOKED_GRILLED, 32).grilled());

    /// Food - Pineapple ///
    public static final RegistryObject<Item> PINEAPPLE_PLANT = ITEMS.register("pineapple_plant", () -> new BlockNamedItem(ModBlocks.PINEAPPLE.get(), new Item.Properties().food(ModFoods.PINEAPPLE_PLANT).group(ModItemGroups.GROUP_FOODS)));
    public static final RegistryObject<Item> PINEAPPLE = registerItemFood("pineapple", ModFoods.PINEAPPLE, 32);
    public static final RegistryObject<Item> PINEAPPLE_RING = registerItemFood("pineapple_ring", ModFoods.PINEAPPLE_RING, 32);
    public static final RegistryObject<Item> PINEAPPLE_BIT = registerItemFood("pineapple_bit", ModFoods.PINEAPPLE_BIT, 32);

    /// Food - Pizza ///
    public static final RegistryObject<Item> PIZZA = registerItemFood("pizza", ModFoods.DOUGH, 32);
    public static final RegistryObject<Item> PIZZA_CHEESE = registerItemFood("pizza_cheese", ModFoods.PIZZA_CHEESE, 32);
    public static final RegistryObject<Item> PIZZA_CHEESE_COOKED = registerItemFood("pizza_cheese_cooked", ModFoods.PIZZA_CHEESE_COOKED, 32);
    public static final RegistryObject<Item> PIZZA_CHEESE_HAM = registerItemFood("pizza_cheese_ham", ModFoods.PIZZA_CHEESE_HAM, 32);
    public static final RegistryObject<Item> PIZZA_CHEESE_HAM_COOKED = registerItemFood("pizza_cheese_ham_cooked", ModFoods.PIZZA_CHEESE_HAM_COOKED, 32);
    public static final RegistryObject<Item> PIZZA_CHEESE_HAM_PINEAPPLE = registerItemFood("pizza_cheese_ham_pineapple", ModFoods.PIZZA_CHEESE_HAM_PINEAPPLE, 32);
    public static final RegistryObject<Item> PIZZA_CHEESE_HAM_PINEAPPLE_COOKED = registerItemFood("pizza_cheese_ham_pineapple_cooked", ModFoods.PIZZA_CHEESE_HAM_PINEAPPLE_COOKED, 32);

    /// Spawn Eggs ///
    public static final RegistryObject<Item> CHEESE_FOODIE_SPAWN_EGG = ITEMS.register("cheese_foodie_spawn_egg", () -> new MeSpawnEggItem(ModEntities.CHEESE_FOODIE.get(), 11445876, 9404726));
    public static final RegistryObject<Item> GRILLED_CHEESE_FOODIE_SPAWN_EGG = ITEMS.register("grilled_cheese_foodie_spawn_egg", () -> new MeSpawnEggItem(ModEntities.GRILLED_CHEESE_FOODIE.get(), 8484425, 8877339));
    public static final RegistryObject<Item> HAM_RAW_FOODIE_SPAWN_EGG = ITEMS.register("ham_raw_foodie_spawn_egg", () -> new MeSpawnEggItem(ModEntities.HAM_RAW_FOODIE.get(), 12152929, 13920850));
    public static final RegistryObject<Item> HAM_COOKED_FOODIE_SPAWN_EGG = ITEMS.register("ham_cooked_foodie_spawn_egg", () -> new MeSpawnEggItem(ModEntities.HAM_COOKED_FOODIE.get(), 13218964, 16770746));

    /// Boats ///
    public static final RegistryObject<Item> BOAT_CHEESE = ITEMS.register("cheese_boat", () -> new BoatItemCM(BoatEntityCM.Type.CHEESE, new Item.Properties().maxStackSize(1).group(ModItemGroups.GROUP_ALL)));
    public static final RegistryObject<Item> BOAT_GRILLED_CHEESE = ITEMS.register("grilled_cheese_boat", () -> new BoatItemCM(BoatEntityCM.Type.GRILLED_CHEESE, new Item.Properties().maxStackSize(1).group(ModItemGroups.GROUP_ALL)));
    public static final RegistryObject<Item> BOAT_HAM_RAW = ITEMS.register("ham_raw_boat", () -> new BoatItemCM(BoatEntityCM.Type.HAM_RAW, new Item.Properties().maxStackSize(1).group(ModItemGroups.GROUP_ALL)));
    public static final RegistryObject<Item> BOAT_HAM_COOKED = ITEMS.register("ham_cooked_boat", () -> new BoatItemCM(BoatEntityCM.Type.HAM_COOKED, new Item.Properties().maxStackSize(1).group(ModItemGroups.GROUP_ALL)));

    /// Other ///
    public static final RegistryObject<Item> CHEESE_BIT = ITEMS.register("cheese_bit", () -> new Item(new Item.Properties().group(ModItemGroups.GROUP_ALL)));
    public static final RegistryObject<Item> HAM_BIT = ITEMS.register("ham_bit", () -> new Item(new Item.Properties().group(ModItemGroups.GROUP_ALL)));

    public static final RegistryObject<Item> WOODEN_GEAR = ITEMS.register("wooden_gear", () -> new Item(new Item.Properties().group(ModItemGroups.GROUP_ALL)));

    public static final RegistryObject<Item> OIL_CATCHER = ITEMS.register("oil_catcher", () -> new Item(new Item.Properties().group(ModItemGroups.GROUP_ARMOR_TOOLS)) {
        @Override
        public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
            super.addInformation(stack, worldIn, tooltip, flagIn);
            tooltip.add(new StringTextComponent(I18n.format("item." + CheeseMod.MOD_ID + ".oil_catcher.info")));
        }
    });

    private static RegistryObject<Item> registerItemFood(String name, Food food, int stackSize) {
        RegistryObject<Item> reg = ITEMS.register(name, () -> new Item(new Item.Properties().food(food).maxStackSize(stackSize).group(ModItemGroups.GROUP_FOODS)));
        FOODS.add(reg);
        return reg;
    }
}
