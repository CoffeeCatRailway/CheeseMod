package coffeecatteam.cheesemod.init;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.objects.items.ItemGrillOil;
import coffeecatteam.cheesemod.objects.items.ItemKnife;
import coffeecatteam.cheesemod.objects.items.ItemTest;
import coffeecatteam.cheesemod.objects.items.base.ItemBase;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseFood;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseSeeds;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseTool;
import coffeecatteam.cheesemod.objects.items.foods.ItemCracker;
import coffeecatteam.cheesemod.objects.items.foods.ItemFoodGreen;
import coffeecatteam.cheesemod.objects.items.foods.ItemToastie;
import coffeecatteam.cheesemod.util.OreDictionaries;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class InitItem {
	
	public static final ToolMaterial OIL_MATERIAL = EnumHelper.addToolMaterial("oil_material", 1, 200, 0, 0.5F, 0);
	public static final ToolMaterial BASIC_MATERIAL = EnumHelper.addToolMaterial("basic_material", 1, 10, 0, 0.5F, 0);

	/*
	 * Other
	 */
	public static final Item GRILLING_OIL = new ItemGrillOil("grilling_oil", OreDictionaries.grillOil, OIL_MATERIAL, false, ItemStack.EMPTY);
	public static final Item SMELTING_OIL = new ItemGrillOil("smelting_oil", OreDictionaries.grillOil, OIL_MATERIAL, false, ItemStack.EMPTY);

	/*
	 *  Ingots
	 */
	public static final Item CHEESE_METAL_INGOT = new ItemBase("cheese_metal_ingot", OreDictionaries.foodIngot);
	public static final Item GRILLED_CHEESE_METAL_INGOT = new ItemBase("grilled_cheese_metal_ingot", OreDictionaries.foodIngot);
	public static final Item HAM_RAW_METAL_INGOT = new ItemBase("ham_raw_metal_ingot", OreDictionaries.foodIngot);
	public static final Item HAM_COOKED_METAL_INGOT = new ItemBase("ham_cooked_metal_ingot", OreDictionaries.foodIngot);
	
	/*
	 *  Tools
	 */
	public static final Item KNIFE = new ItemKnife("knife", "knife", ToolMaterial.IRON, true, new ItemStack(Items.IRON_INGOT, 1, 0));
	public static final Item CHEESE_KNIFE = new ItemKnife("cheese_knife", "knife", InitTool.CHEESE_METAL_TOOL_MATERIAL, true, new ItemStack(CHEESE_METAL_INGOT, 1, 0));
	public static final Item HAM_RAW_KNIFE = new ItemKnife("ham_raw_knife", "knife", InitTool.HAM_RAW_METAL_TOOL_MATERIAL, true, new ItemStack(HAM_RAW_METAL_INGOT, 1, 0));
	public static final Item HAM_COOKED_KNIFE = new ItemKnife("ham_cooked_knife", "knife", InitTool.HAM_COOKED_METAL_TOOL_MATERIAL, true, new ItemStack(HAM_COOKED_METAL_INGOT, 1, 0));

	public static final Item CHEESE_CUTTER = new ItemBaseTool("cheese_cutter", BASIC_MATERIAL, true, new ItemStack(Blocks.STONE, 1, 0));
	public static final Item GRINDING_STONES = new ItemBaseTool("grinding_stones", ToolMaterial.STONE, false, ItemStack.EMPTY);
	
	public static final Item MILK_CURDLER = new ItemBaseTool("milk_curdler", ToolMaterial.WOOD, true, new ItemStack(Blocks.PLANKS, 1, 32767));
	public static final Item ROLLING_PIN = new ItemBaseTool("rolling_pin", ToolMaterial.WOOD, true, new ItemStack(Blocks.PLANKS, 1, 32767));

	/*
	 *  Foods
	 */
	public static final Item BLOCK_O_CHEESE = new ItemBaseFood("block_o_cheese", OreDictionaries.foodCheese, 6, false);
	public static final Item CHEESE_SLICE = new ItemBaseFood("cheese_slice", OreDictionaries.foodCheese, 2, false);
	
	public static final Item SALT = new ItemBaseFood("salt", OreDictionaries.foodIngredient, 1, false);
	public static final Item FLOUR = new ItemBaseFood("flour", OreDictionaries.foodIngredient, 1, false);
	
	public static final Item BACON_RAW = new ItemBaseFood("bacon_raw", OreDictionaries.foodBacon, 2, true);
	public static final Item BACON_COOKED = new ItemBaseFood("bacon_cooked", OreDictionaries.foodBacon, 5, false);

	public static final Item BREAD_SLICE = new ItemBaseFood("bread_slice", OreDictionaries.foodBreadSlice, 2, false);
	
	public static final Item DOUGH = new ItemBaseFood("dough", OreDictionaries.foodIngredient, 3, false);

	public static final Item TOAST_PLAIN = new ItemBaseFood("toast_plain", OreDictionaries.foodToast, 3, false);
    public static final Item TOAST_FRENCH = new ItemBaseFood("toast_french", OreDictionaries.foodToast, 6, false);
    public static final Item TOAST_BACON = new ItemBaseFood("toast_bacon", OreDictionaries.foodToast, 8, false);

    public static final Item EGG_CRACKED = new ItemFoodGreen("egg_cracked", 3, false);
    public static final Item EGG_COOKED = new ItemFoodGreen("egg_cooked", 6, false);
    public static final Item EGG_GREEN = new ItemFoodGreen("egg_green", 3, true);

    public static final Item HAM_RAW = new ItemFoodGreen("ham_raw", 2, false);
    public static final Item HAM_COOKED = new ItemFoodGreen("ham_cooked", 3, false);
    public static final Item HAM_GREEN = new ItemFoodGreen("ham_green", 3, true);

    public static final Item CRACKER_PLAIN = new ItemCracker("plain", 5);
    public static final Item CRACKER_CRAYFISH = new ItemCracker("crayfish", 10);
    public static final Item CRACKER_N_CHEESE = new ItemCracker("cheese", 7);
    public static final Item CRACKER_N_GRILLED_CHEESE = new ItemCracker("grilled_cheese", 10);
    public static final Item CRACKER_CHEESE_N_CRAYFISH = new ItemCracker("cheese_n_crayfish", 12);
    public static final Item CRACKER_GRILLED_CHEESE_N_CRAYFISH = new ItemCracker("grilled_cheese_n_crayfish", 15);

    public static final Item TOASTIE_CHEESE = new ItemToastie("cheese", 8, false);
    public static final Item TOASTIE_GRILLED_CHEESE = new ItemToastie("grilled_cheese", 11, true);
    public static final Item TOASTIE_HAM_RAW_N_CHEESE = new ItemToastie("ham_raw_n_cheese", 9, false);
    public static final Item TOASTIE_GRILLED_HAM_RAW_N_CHEESE = new ItemToastie("grilled_ham_raw_n_cheese", 12, true);
    public static final Item TOASTIE_HAM_COOKED_N_CHEESE = new ItemToastie("ham_cooked_n_cheese", 11, false);
    public static final Item TOASTIE_GRILLED_HAM_COOKED_N_CHEESE = new ItemToastie("grilled_ham_cooked_n_cheese", 14, true);

    public static final Item PIZZA_BLAND = new ItemBaseFood("pizza_bland", OreDictionaries.foodPizza, 5, false);
    public static final Item PIZZA_UNCOOKED_CHEESE = new ItemBaseFood("pizza_uncooked_cheese", OreDictionaries.foodPizza, 7, false);
    public static final Item PIZZA_COOKED_CHEESE = new ItemBaseFood("pizza_cooked_cheese", OreDictionaries.foodPizza, 10, false);
    public static final Item PIZZA_UNCOOKED_HAM_N_CHEESE = new ItemBaseFood("pizza_uncooked_ham_n_cheese", OreDictionaries.foodPizza, 9, false);
    public static final Item PIZZA_COOKED_HAM_N_CHEESE = new ItemBaseFood("pizza_cooked_ham_n_cheese", OreDictionaries.foodPizza, 13, false);
    public static final Item PIZZA_UNCOOKED_HAM_PINEAPPLE_N_CHEESE = new ItemBaseFood("pizza_uncooked_ham_pineapple_n_cheese", OreDictionaries.foodPizza, 12, false);
    public static final Item PIZZA_COOKED_HAM_PINEAPPLE_N_CHEESE = new ItemBaseFood("pizza_cooked_ham_pineapple_n_cheese", OreDictionaries.foodPizza, 15, false);
	
	// Pineapple
	public static final Item PINEAPPLE_PLANT = new ItemBaseSeeds("pineapple_plant", "pineapple", InitBlock.PINEAPPLE, Blocks.FARMLAND);
	public static final Item PINEAPPLE = new ItemBaseFood("pineapple", "pineapple", 12, false);
	public static final Item PINEAPPLE_RING = new ItemBaseFood("pineapple_ring", "foodPineapple", 3, false);
	public static final Item PINEAPPLE_BIT = new ItemBaseFood("pineapple_bit", "foodPineapple", 1, false);
	
	/*
	 * Other
	 */
	public static final Item CHEESE_BIT = new ItemBase("cheese_bit", OreDictionaries.foodBit);
	public static final Item HAM_BIT = new ItemBase("ham_bit", OreDictionaries.foodBit);

	public static final Item WOODEN_GEAR = new ItemBase("wooden_gear", OreDictionaries.gearWood);

	public static final Item TEST_ITEM = new ItemTest("test_item");

	public static void init() {
	    // Oils
	    register(GRILLING_OIL, SMELTING_OIL);

	    // Ingots
        register(CHEESE_METAL_INGOT, GRILLED_CHEESE_METAL_INGOT, HAM_RAW_METAL_INGOT, HAM_COOKED_METAL_INGOT);

        // Tools
        register(KNIFE, CHEESE_KNIFE, HAM_RAW_KNIFE, HAM_COOKED_KNIFE);
        register(CHEESE_CUTTER, GRINDING_STONES, MILK_CURDLER, ROLLING_PIN);

        // Foods
        register(BLOCK_O_CHEESE, CHEESE_SLICE, BACON_RAW, BACON_COOKED);
        register(SALT, FLOUR, BREAD_SLICE, DOUGH);
        register(TOAST_PLAIN, TOAST_FRENCH, TOAST_BACON);
        register(EGG_CRACKED, EGG_COOKED, EGG_GREEN);
        register(HAM_RAW, HAM_COOKED, HAM_GREEN);
        register(CRACKER_PLAIN, CRACKER_CRAYFISH, CRACKER_N_CHEESE, CRACKER_N_GRILLED_CHEESE, CRACKER_CHEESE_N_CRAYFISH, CRACKER_GRILLED_CHEESE_N_CRAYFISH);
        register(TOASTIE_CHEESE, TOASTIE_GRILLED_CHEESE, TOASTIE_HAM_RAW_N_CHEESE, TOASTIE_GRILLED_HAM_RAW_N_CHEESE, TOASTIE_HAM_COOKED_N_CHEESE, TOASTIE_GRILLED_HAM_COOKED_N_CHEESE);
        register(PIZZA_BLAND, PIZZA_UNCOOKED_CHEESE, PIZZA_COOKED_CHEESE, PIZZA_UNCOOKED_HAM_N_CHEESE, PIZZA_COOKED_HAM_N_CHEESE, PIZZA_COOKED_HAM_N_CHEESE, PIZZA_UNCOOKED_HAM_PINEAPPLE_N_CHEESE, PIZZA_COOKED_HAM_PINEAPPLE_N_CHEESE);

        // Pineapple
        register(PINEAPPLE_PLANT, PINEAPPLE, PINEAPPLE_RING, PINEAPPLE_BIT);

        // Other
        register(CHEESE_BIT, HAM_BIT, WOODEN_GEAR);

		if (CheeseMod.DEVELOPER_MODE)
			register(TEST_ITEM);
	}

    private static void register(Item... items) {
        for (Item item : items)
            register(item);
    }

	private static void register(Item item) {
        RegistrationHandler.Items.ITEMS.add(item);
    }
}
