package coffeecatteam.cheesemod.util;

import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.init.InitBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaries {

	/*
	 * Blocks
	 */
	public static final String blockFoodMetalOre = "blockFoodMetalOre";
	public static final String blockFoodMetal = "blockFoodMetal";

	public static final String blockFood = "blockFood";
	public static final String blockFoodStairs = "blockFoodStairs";
	public static final String blockFoodSlab = "blockFoodSlab";

	public static final String blockFoodMaker = "blockFoodMaker";
	public static final String blockFoodDraw = "blockFoodDraw";

	public static final String cake = "cake";

	/*
	 * Items
	 */
	public static final String grillOil = "grillOil";
	public static final String foodIngot = "foodMetalIngot";

	public static final String foodIngredient = "foodIngredient";

	public static final String foodCheese = "foodCheese";
	public static final String foodHam = "foodHam";
	public static final String foodBacon = "foodBacon";
	public static final String foodToast = "foodToast";
	public static final String foodBreadSlice = "foodBreadSlice";

	public static final String foodPizza = "foodPizza";
	public static final String foodToastie = "foodToastie";
	public static final String foodCracker = "foodCracker";
	public static final String foodEgg = "foodEgg";

	public static final String foodBit = "foodBit";
	public static final String gearWood = "gearWood";

	public static void init() {
		/*
		 * Blocks
		 */
		registerOre(blockFoodMetalOre, InitBlock.CHEESE_METAL_ORE, InitBlock.NETHER_CHEESE_METAL_ORE,
				InitBlock.END_CHEESE_METAL_ORE);
		registerOre(blockFoodMetalOre, InitBlock.GRILLED_CHEESE_METAL_ORE, InitBlock.NETHER_GRILLED_CHEESE_METAL_ORE,
				InitBlock.END_GRILLED_CHEESE_METAL_ORE);
		registerOre(blockFoodMetalOre, InitBlock.HAM_RAW_METAL_ORE, InitBlock.NETHER_HAM_RAW_METAL_ORE,
				InitBlock.END_HAM_RAW_METAL_ORE);
		registerOre(blockFoodMetalOre, InitBlock.HAM_COOKED_METAL_ORE, InitBlock.NETHER_HAM_COOKED_METAL_ORE,
				InitBlock.END_HAM_COOKED_METAL_ORE);

		registerOre(blockFoodMetal, InitBlock.CHEESE_METAL_BLOCK, InitBlock.GRILLED_CHEESE_METAL_BLOCK);
		registerOre(blockFoodMetal, InitBlock.HAM_RAW_METAL_BLOCK, InitBlock.HAM_COOKED_METAL_BLOCK);

		// Food Blocks
		registerOre(blockFoodStairs, InitBlock.CHEESE_STAIRS, InitBlock.GRILLED_CHEESE_STAIRS,
				InitBlock.HAM_RAW_STAIRS, InitBlock.HAM_COOKED_STAIRS);
		registerOre(blockFoodSlab, InitBlock.CHEESE_SLAB, InitBlock.GRILLED_CHEESE_SLAB, InitBlock.HAM_RAW_SLAB,
				InitBlock.HAM_COOKED_SLAB);

		registerOre(blockFoodMaker, InitBlock.GRILL_IDLE, InitBlock.CRACKER_MAKER_IDLE);
		registerOre(blockFoodDraw, InitBlock.CHEESE_DRAW, InitBlock.HAM_DRAW);

		// Cakes
		registerOre(cake, InitBlock.CHEESE_CAKE);
	}

	public static void registerOre(String key, Object... items) {
		for (final Object item : items) {
			if (item instanceof Block) {
				OreDictionary.registerOre(key, (Block) item);
			} else if (item instanceof Item) {
				OreDictionary.registerOre(key, (Item) item);
			}
		}
	}
}
