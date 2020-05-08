package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.ModItemGroups;
import coffeecatrailway.coffeecheese.client.render.item.StackableFoodRenderer;
import coffeecatrailway.coffeecheese.common.entity.item.BoatEntityCM;
import coffeecatrailway.coffeecheese.common.item.*;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

import java.util.ArrayList;
import java.util.List;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
@SuppressWarnings("unused")
public class ModItems {

    public static final List<Item> FOODS = new ArrayList<>();

    /// Ingots ///
    public static final RegistryEntry<Item> CHEESE_METAL_INGOT = REGISTRATE.item("cheese_metal_ingot", Item::new)
            .properties(prop -> prop.food(ModFoods.INGOT)).register();
    public static final RegistryEntry<Item> GRILLED_CHEESE_METAL_INGOT = REGISTRATE.item("grilled_cheese_metal_ingot", Item::new)
            .properties(prop -> prop.food(ModFoods.INGOT)).register();
    public static final RegistryEntry<Item> HAM_RAW_METAL_INGOT = REGISTRATE.item("ham_raw_metal_ingot", Item::new)
            .properties(prop -> prop.food(ModFoods.INGOT)).register();
    public static final RegistryEntry<Item> HAM_COOKED_METAL_INGOT = REGISTRATE.item("ham_cooked_metal_ingot", Item::new)
            .properties(prop -> prop.food(ModFoods.INGOT)).register();

    /// Tool ///
    public static final RegistryEntry<CraftToolItem> CHEESE_CUTTER = REGISTRATE.item("cheese_cutter", prop -> new CraftToolItem(ItemTier.WOOD, 1, 2.5f, prop))
            .properties(prop -> prop.maxStackSize(1)).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<CraftToolItem> GRINDING_STONES = REGISTRATE.item("grinding_stones", prop -> new CraftToolItem(ItemTier.STONE, 2, 2.7f, prop))
            .properties(prop -> prop.maxStackSize(1)).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    public static final RegistryEntry<CraftToolItem> MILK_CURDLER = REGISTRATE.item("milk_curdler", prop -> new CraftToolItem(ItemTier.WOOD, 2, 2.5f, prop))
            .properties(prop -> prop.maxStackSize(1)).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<CraftToolItem> ROLLING_PIN = REGISTRATE.item("rolling_pin", prop -> new CraftToolItem(ItemTier.WOOD, 1, 2.5f, prop))
            .properties(prop -> prop.maxStackSize(1)).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Tool - Knife ///
    public static final RegistryEntry<CraftToolItem> KNIFE = REGISTRATE.item("knife", prop -> new CraftToolItem(ItemTier.IRON, 4, -2.1f, prop))
            .properties(prop -> prop.maxStackSize(1)).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<CraftToolItem> CHEESE_KNIFE = REGISTRATE.item("cheese_knife", prop -> new CraftToolItem(ModItemTier.CHEESE_METAL, 5, -2.7f, prop))
            .properties(prop -> prop.maxStackSize(1)).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<CraftToolItem> GRILLED_CHEESE_KNIFE = REGISTRATE.item("grilled_cheese_knife", prop -> new CraftToolItem(ModItemTier.GRILLED_CHEESE_METAL, 6, -3.5f, prop))
            .properties(prop -> prop.maxStackSize(1)).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<CraftToolItem> HAM_RAW_KNIFE = REGISTRATE.item("ham_raw_knife", prop -> new CraftToolItem(ModItemTier.HAM_RAW_METAL, 5, -2.9f, prop))
            .properties(prop -> prop.maxStackSize(1)).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<CraftToolItem> HAM_COOKED_KNIFE = REGISTRATE.item("ham_cooked_knife", prop -> new CraftToolItem(ModItemTier.HAM_COOKED_METAL, 6, -3.0f, prop))
            .properties(prop -> prop.maxStackSize(1)).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Tool - Cheese Metal ///
    public static final RegistryEntry<PickaxeItem> CHEESE_METAL_PICKAXE = REGISTRATE.item("cheese_metal_pickaxe", prop -> new PickaxeItem(ModItemTier.CHEESE_METAL, 1, -2.8f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<AxeItem> CHEESE_METAL_AXE = REGISTRATE.item("cheese_metal_axe", prop -> new AxeItem(ModItemTier.CHEESE_METAL, 6, -3.1f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<HoeItem> CHEESE_METAL_HOE = REGISTRATE.item("cheese_metal_hoe", prop -> new HoeItem(ModItemTier.CHEESE_METAL, -1.0f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ShovelItem> CHEESE_METAL_SHOVEL = REGISTRATE.item("cheese_metal_shovel", prop -> new ShovelItem(ModItemTier.CHEESE_METAL, 1.5f, -3.0f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<SwordItem> CHEESE_METAL_SWORD = REGISTRATE.item("cheese_metal_sword", prop -> new SwordItem(ModItemTier.CHEESE_METAL, 3, -2.4f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Tool - Grilled Cheese Metal ///
    public static final RegistryEntry<PickaxeItem> GRILLED_CHEESE_METAL_PICKAXE = REGISTRATE.item("grilled_cheese_metal_pickaxe", prop -> new PickaxeItem(ModItemTier.GRILLED_CHEESE_METAL, 2, -2.0f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<AxeItem> GRILLED_CHEESE_METAL_AXE = REGISTRATE.item("grilled_cheese_metal_axe", prop -> new AxeItem(ModItemTier.GRILLED_CHEESE_METAL, 7, -2.3f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<HoeItem> GRILLED_CHEESE_METAL_HOE = REGISTRATE.item("grilled_cheese_metal_hoe", prop -> new HoeItem(ModItemTier.GRILLED_CHEESE_METAL, -1.0f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ShovelItem> GRILLED_CHEESE_METAL_SHOVEL = REGISTRATE.item("grilled_cheese_metal_shovel", prop -> new ShovelItem(ModItemTier.GRILLED_CHEESE_METAL, 2.5f, -2.2f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<SwordItem> GRILLED_CHEESE_METAL_SWORD = REGISTRATE.item("grilled_cheese_metal_sword", prop -> new SwordItem(ModItemTier.GRILLED_CHEESE_METAL, 4, -1.6f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Tool - Ham Raw Metal ///
    public static final RegistryEntry<PickaxeItem> HAM_RAW_METAL_PICKAXE = REGISTRATE.item("ham_raw_metal_pickaxe", prop -> new PickaxeItem(ModItemTier.HAM_RAW_METAL, 1, -2.7f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<AxeItem> HAM_RAW_METAL_AXE = REGISTRATE.item("ham_raw_metal_axe", prop -> new AxeItem(ModItemTier.HAM_RAW_METAL, 6, -3.0f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<HoeItem> HAM_RAW_METAL_HOE = REGISTRATE.item("ham_raw_metal_hoe", prop -> new HoeItem(ModItemTier.HAM_RAW_METAL, -1.0f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ShovelItem> HAM_RAW_METAL_SHOVEL = REGISTRATE.item("ham_raw_metal_shovel", prop -> new ShovelItem(ModItemTier.HAM_RAW_METAL, 1.8f, -2.9f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<SwordItem> HAM_RAW_METAL_SWORD = REGISTRATE.item("ham_raw_metal_sword", prop -> new SwordItem(ModItemTier.HAM_RAW_METAL, 3, -2.3f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Tool - Ham Cooked Metal ///
    public static final RegistryEntry<PickaxeItem> HAM_COOKED_METAL_PICKAXE = REGISTRATE.item("ham_cooked_metal_pickaxe", prop -> new PickaxeItem(ModItemTier.HAM_COOKED_METAL, 2, -2.6f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<AxeItem> HAM_COOKED_METAL_AXE = REGISTRATE.item("ham_cooked_metal_axe", prop -> new AxeItem(ModItemTier.HAM_COOKED_METAL, 7, -2.9f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<HoeItem> HAM_COOKED_METAL_HOE = REGISTRATE.item("ham_cooked_metal_hoe", prop -> new HoeItem(ModItemTier.HAM_COOKED_METAL, -1.0f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ShovelItem> HAM_COOKED_METAL_SHOVEL = REGISTRATE.item("ham_cooked_metal_shovel", prop -> new ShovelItem(ModItemTier.HAM_COOKED_METAL, 2.9f, -2.8f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<SwordItem> HAM_COOKED_METAL_SWORD = REGISTRATE.item("ham_cooked_metal_sword", prop -> new SwordItem(ModItemTier.HAM_COOKED_METAL, 4, -2.2f, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Armor - Cheese Metal ///
    public static final RegistryEntry<ArmorItem> CHEESE_METAL_HELMET = REGISTRATE.item("cheese_metal_helmet", prop -> new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.HEAD, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> CHEESE_METAL_CHESTPLATE = REGISTRATE.item("cheese_metal_chestplate", prop -> new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.CHEST, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> CHEESE_METAL_LEGGINGS = REGISTRATE.item("cheese_metal_leggings", prop -> new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.LEGS, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> CHEESE_METAL_BOOTS = REGISTRATE.item("cheese_metal_boots", prop -> new ArmorItem(ModArmorMaterial.CHEESE, EquipmentSlotType.FEET, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Armor - Grilled Cheese Metal ///
    public static final RegistryEntry<ArmorItem> GRILLED_CHEESE_METAL_HELMET = REGISTRATE.item("grilled_cheese_metal_helmet", prop -> new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.HEAD, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> GRILLED_CHEESE_METAL_CHESTPLATE = REGISTRATE.item("grilled_cheese_metal_chestplate", prop -> new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.CHEST, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> GRILLED_CHEESE_METAL_LEGGINGS = REGISTRATE.item("grilled_cheese_metal_leggings", prop -> new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.LEGS, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> GRILLED_CHEESE_METAL_BOOTS = REGISTRATE.item("grilled_cheese_metal_boots", prop -> new ArmorItem(ModArmorMaterial.GRILLED_CHEESE, EquipmentSlotType.FEET, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Armor - Ham Raw Metal ///
    public static final RegistryEntry<ArmorItem> HAM_RAW_METAL_HELMET = REGISTRATE.item("ham_raw_metal_helmet", prop -> new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.HEAD, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> HAM_RAW_METAL_CHESTPLATE = REGISTRATE.item("ham_raw_metal_chestplate", prop -> new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.CHEST, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> HAM_RAW_METAL_LEGGINGS = REGISTRATE.item("ham_raw_metal_leggings", prop -> new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.LEGS, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> HAM_RAW_METAL_BOOTS = REGISTRATE.item("ham_raw_metal_boots", prop -> new ArmorItem(ModArmorMaterial.HAM_RAW, EquipmentSlotType.FEET, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Armor - Ham Cooked Metal ///
    public static final RegistryEntry<ArmorItem> HAM_COOKED_METAL_HELMET = REGISTRATE.item("ham_cooked_metal_helmet", prop -> new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.HEAD, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> HAM_COOKED_METAL_CHESTPLATE = REGISTRATE.item("ham_cooked_metal_chestplate", prop -> new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.CHEST, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> HAM_COOKED_METAL_LEGGINGS = REGISTRATE.item("ham_cooked_metal_leggings", prop -> new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.LEGS, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<ArmorItem> HAM_COOKED_METAL_BOOTS = REGISTRATE.item("ham_cooked_metal_boots", prop -> new ArmorItem(ModArmorMaterial.HAM_COOKED, EquipmentSlotType.FEET, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Armor - Food Suit ///
    public static final RegistryEntry<CheeseSuitArmorItem> CHEESE_SUIT_HAT = REGISTRATE.item("cheese_suit_hat", prop -> new CheeseSuitArmorItem(ModArmorMaterial.CHEESE_SUIT, EquipmentSlotType.HEAD, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<CheeseSuitArmorItem> CHEESE_SUIT_CHESTPLATE = REGISTRATE.item("cheese_suit_chestplate", prop -> new CheeseSuitArmorItem(ModArmorMaterial.CHEESE_SUIT, EquipmentSlotType.CHEST, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    public static final RegistryEntry<HamSuitArmorItem> HAM_SUIT_HAT = REGISTRATE.item("ham_suit_hat", prop -> new HamSuitArmorItem(ModArmorMaterial.HAM_SUIT, EquipmentSlotType.HEAD, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<HamSuitArmorItem> HAM_SUIT_CHESTPLATE = REGISTRATE.item("ham_suit_chestplate", prop -> new HamSuitArmorItem(ModArmorMaterial.HAM_SUIT, EquipmentSlotType.CHEST, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();
    public static final RegistryEntry<HamSuitArmorItem> HAM_SUIT_BOOTS = REGISTRATE.item("ham_suit_boots", prop -> new HamSuitArmorItem(ModArmorMaterial.HAM_SUIT, EquipmentSlotType.FEET, prop))
            .group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    /// Food ///
    public static final RegistryEntry<Item> BLOCK_O_CHEESE = REGISTRATE.item("block_o_cheese", Item::new)
            .properties(prop -> prop.food(ModFoods.BLOCK_O_CHEESE).maxStackSize(16)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<CheeseBallItem> CHEESE_BALL = REGISTRATE.item("cheese_ball", CheeseBallItem::new)
            .properties(prop -> prop.food(ModFoods.CHEESE_BALL).maxStackSize(16)).group(() -> ModItemGroups.GROUP_FOODS).register();

    public static final RegistryEntry<Item> CHEESE_SLICE = REGISTRATE.item("cheese_slice", Item::new)
            .properties(prop -> prop.food(ModFoods.CHEESE_SLICE).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();

    public static final RegistryEntry<Item> SALT = REGISTRATE.item("salt", Item::new)
            .properties(prop -> prop.food(ModFoods.INGREDIENT)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> FLOUR = REGISTRATE.item("flour", Item::new)
            .properties(prop -> prop.food(ModFoods.INGREDIENT)).group(() -> ModItemGroups.GROUP_FOODS).register();

    public static final RegistryEntry<Item> BACON_RAW = REGISTRATE.item("bacon_raw", Item::new)
            .properties(prop -> prop.food(ModFoods.BACON).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> BACON_COOKED = REGISTRATE.item("bacon_cooked", Item::new)
            .properties(prop -> prop.food(ModFoods.BACON_COOKED).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();

    public static final RegistryEntry<Item> BREAD_SLICE = REGISTRATE.item("bread_slice", Item::new)
            .properties(prop -> prop.food(ModFoods.BREAD_SLICE)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<SandwichItem> SANDWICH = REGISTRATE.item("sandwich", SandwichItem::new)
            .properties(prop -> prop.maxStackSize(4).setISTER(() -> StackableFoodRenderer::new)).group(() -> ModItemGroups.GROUP_FOODS).register();

    public static final RegistryEntry<CrackerItem> CRACKER = REGISTRATE.item("cracker", CrackerItem::new)
            .properties(prop -> prop.maxStackSize(16).setISTER(() -> StackableFoodRenderer::new)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> CRACKER_DUMMY_ITEM = REGISTRATE.item("cracker_dummy_item", Item::new)
            .properties(prop -> prop.food(ModFoods.CRACKER).maxStackSize(1).group(null)).register();
    public static final RegistryEntry<Item> CRACKER_TOASTED_DUMMY_ITEM = REGISTRATE.item("cracker_toasted_dummy_item", Item::new)
            .properties(prop -> prop.food(ModFoods.CRACKER_TOASTED).maxStackSize(1).group(null)).register();

    public static final RegistryEntry<Item> DOUGH = REGISTRATE.item("dough", Item::new)
            .properties(prop -> prop.food(ModFoods.DOUGH)).group(() -> ModItemGroups.GROUP_FOODS).register();

    /// Food - Egg ///
    public static final RegistryEntry<Item> EGG_CRACKED = REGISTRATE.item("egg_cracked", Item::new)
            .properties(prop -> prop.food(ModFoods.EGG).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> EGG_COOKED = REGISTRATE.item("egg_cooked", Item::new)
            .properties(prop -> prop.food(ModFoods.EGG_COOKED).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<GreenFoodItem> EGG_GREEN = REGISTRATE.item("egg_green", prop -> new GreenFoodItem(ModFoods.EGG_GREEN, 32)).register();

    /// Food - Ham ///
    public static final RegistryEntry<Item> HAM_RAW = REGISTRATE.item("ham_raw", Item::new)
            .properties(prop -> prop.food(ModFoods.HAM).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> HAM_COOKED = REGISTRATE.item("ham_cooked", Item::new)
            .properties(prop -> prop.food(ModFoods.HAM_COOKED).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<GreenFoodItem> HAM_GREEN = REGISTRATE.item("ham_green", prop -> new GreenFoodItem(ModFoods.HAM_GREEN, 32)).register();

    /// Food - Toast ///
    public static final RegistryEntry<Item> TOAST = REGISTRATE.item("toast", Item::new)
            .properties(prop -> prop.food(ModFoods.TOAST)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> TOAST_FRENCH = REGISTRATE.item("toast_french", Item::new)
            .properties(prop -> prop.food(ModFoods.TOAST_FRENCH)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> TOAST_BACON = REGISTRATE.item("toast_bacon", Item::new)
            .properties(prop -> prop.food(ModFoods.TOAST_BACON)).group(() -> ModItemGroups.GROUP_FOODS).register();

    /// Food - Pineapple ///
    public static final RegistryEntry<BlockNamedItem> PINEAPPLE_PLANT = REGISTRATE.item("pineapple_plant", prop -> new BlockNamedItem(ModBlocks.PINEAPPLE.get(), prop))
            .properties(prop -> prop.food(ModFoods.PINEAPPLE_PLANT)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> PINEAPPLE = REGISTRATE.item("pineapple", Item::new)
            .properties(prop -> prop.food(ModFoods.PINEAPPLE).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> PINEAPPLE_RING = REGISTRATE.item("pineapple_ring", Item::new)
            .properties(prop -> prop.food(ModFoods.PINEAPPLE_RING).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> PINEAPPLE_BIT = REGISTRATE.item("pineapple_bit", Item::new)
            .properties(prop -> prop.food(ModFoods.PINEAPPLE_BIT).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();

    /// Food - Pizza ///
    public static final RegistryEntry<Item> PIZZA = REGISTRATE.item("pizza", Item::new)
            .properties(prop -> prop.food(ModFoods.DOUGH).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> PIZZA_CHEESE = REGISTRATE.item("pizza_cheese", Item::new)
            .properties(prop -> prop.food(ModFoods.PIZZA_CHEESE).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> PIZZA_CHEESE_COOKED = REGISTRATE.item("pizza_cheese_cooked", Item::new)
            .properties(prop -> prop.food(ModFoods.PIZZA_CHEESE_COOKED).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> PIZZA_CHEESE_HAM = REGISTRATE.item("pizza_cheese_ham", Item::new)
            .properties(prop -> prop.food(ModFoods.PIZZA_CHEESE_HAM).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> PIZZA_CHEESE_HAM_COOKED = REGISTRATE.item("pizza_cheese_ham_cooked", Item::new)
            .properties(prop -> prop.food(ModFoods.PIZZA_CHEESE_HAM_COOKED).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> PIZZA_CHEESE_HAM_PINEAPPLE = REGISTRATE.item("pizza_cheese_ham_pineapple", Item::new)
            .properties(prop -> prop.food(ModFoods.PIZZA_CHEESE_HAM_PINEAPPLE).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();
    public static final RegistryEntry<Item> PIZZA_CHEESE_HAM_PINEAPPLE_COOKED = REGISTRATE.item("pizza_cheese_ham_pineapple_cooked", Item::new)
            .properties(prop -> prop.food(ModFoods.PIZZA_CHEESE_HAM_PINEAPPLE_COOKED).maxStackSize(32)).group(() -> ModItemGroups.GROUP_FOODS).register();

    /// Boats ///
    public static final RegistryEntry<BoatItemCM> BOAT_CHEESE = REGISTRATE.item("cheese_boat", prop -> new BoatItemCM(BoatEntityCM.Type.CHEESE, prop))
            .properties(prop -> prop.maxStackSize(1)).register();
    public static final RegistryEntry<BoatItemCM> BOAT_GRILLED_CHEESE = REGISTRATE.item("grilled_cheese_boat", prop -> new BoatItemCM(BoatEntityCM.Type.GRILLED_CHEESE, prop))
            .properties(prop -> prop.maxStackSize(1)).register();
    public static final RegistryEntry<BoatItemCM> BOAT_HAM_RAW = REGISTRATE.item("ham_raw_boat", prop -> new BoatItemCM(BoatEntityCM.Type.HAM_RAW, prop))
            .properties(prop -> prop.maxStackSize(1)).register();
    public static final RegistryEntry<BoatItemCM> BOAT_HAM_COOKED = REGISTRATE.item("ham_cooked_boat", prop -> new BoatItemCM(BoatEntityCM.Type.HAM_COOKED, prop))
            .properties(prop -> prop.maxStackSize(1)).register();

    /// Other ///
    public static final RegistryEntry<Item> CHEESE_BIT = REGISTRATE.item("cheese_bit", Item::new).register();
    public static final RegistryEntry<Item> HAM_BIT = REGISTRATE.item("ham_bit", Item::new).register();

    public static final RegistryEntry<Item> WOODEN_GEAR = REGISTRATE.item("wooden_gear", Item::new).register();

    public static final RegistryEntry<OilCatcherItem> OIL_CATCHER = REGISTRATE.item("oil_catcher", OilCatcherItem::new).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    public static final RegistryEntry<MagicFoodStickItem> MAGIC_FOOD_STICK = REGISTRATE.item("magic_food_stick", MagicFoodStickItem::new)
            .properties(prop -> prop.rarity(Rarity.RARE).maxStackSize(1).maxDamage(20)).group(() -> ModItemGroups.GROUP_ARMOR_TOOLS).register();

    public static void load() {
    }
}
