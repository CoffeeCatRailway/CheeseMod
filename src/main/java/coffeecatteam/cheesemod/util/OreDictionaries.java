package coffeecatteam.cheesemod.util;

import coffeecatteam.cheesemod.init.InitItem;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.init.InitBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
	
	/*
	 * Items
	 */
	public static final String grillOil = "grillOil";
	public static final String foodIngot = "foodMetalIngot";

	public static final String foodIngredient = "foodIngredient";
	
	public static final String foodCheese = "foodCheese";
	public static final String foodHam = "foodHam";
	public static final String foodToast = "foodToast";
	
	public static final String foodPizza = "foodPizza";
	public static final String foodToastie = "foodToastie";
	public static final String foodCracker = "foodCracker";

	public static final String foodBit = "foodBit";
	public static final String gearWood = "gearWood";

	public static void init() {
		/*
		 * Blocks
		 */
		registerOres(blockFoodMetalOre, InitBlock.CHEESE_METAL_ORE, InitBlock.NETHER_CHEESE_METAL_ORE, InitBlock.END_CHEESE_METAL_ORE);
		registerOres(blockFoodMetalOre, InitBlock.GRILLED_CHEESE_METAL_ORE, InitBlock.NETHER_GRILLED_CHEESE_METAL_ORE, InitBlock.END_GRILLED_CHEESE_METAL_ORE);
		registerOres(blockFoodMetalOre, InitBlock.HAM_RAW_METAL_ORE, InitBlock.NETHER_HAM_RAW_METAL_ORE, InitBlock.END_HAM_RAW_METAL_ORE);
		registerOres(blockFoodMetalOre, InitBlock.HAM_COOKED_METAL_ORE, InitBlock.NETHER_HAM_COOKED_METAL_ORE, InitBlock.END_HAM_COOKED_METAL_ORE);
		
		registerOres(blockFoodMetal, InitBlock.CHEESE_METAL_BLOCK, InitBlock.GRILLED_CHEESE_METAL_BLOCK);
		registerOres(blockFoodMetal, InitBlock.HAM_RAW_METAL_BLOCK, InitBlock.HAM_COOKED_METAL_BLOCK);
		
		// Food Blocks
		for (int i = 0; i < EnumHandler.EnumWoodType.values().length; i++) {
			OreDictionary.registerOre(blockFood, new ItemStack(InitBlock.FOOD_BLOCK, 1, i));
		}
		registerOres(blockFoodStairs, InitBlock.CHEESE_STAIRS, InitBlock.GRILLED_CHEESE_STAIRS, InitBlock.HAM_RAW_STAIRS, InitBlock.HAM_COOKED_STAIRS);
		registerOres(blockFoodSlab, InitBlock.CHEESE_SLAB, InitBlock.GRILLED_CHEESE_SLAB, InitBlock.HAM_RAW_SLAB, InitBlock.HAM_COOKED_SLAB);

		registerOres(blockFoodMaker, InitBlock.GRILL_IDLE, InitBlock.CRACKER_MAKER_IDLE);
		registerOres(blockFoodDraw, InitBlock.CHEESE_DRAW, InitBlock.HAM_DRAW);
		
		// Trees
		for (int i = 0; i < EnumHandler.EnumWoodType.values().length; i++) {
			OreDictionary.registerOre("plankWood", new ItemStack(InitBlock.CHEESE_PLANKS, 1, i));
			OreDictionary.registerOre("treeWood", new ItemStack(InitBlock.CHEESE_LOG, 1, i));
			OreDictionary.registerOre("treeLeaves", new ItemStack(InitBlock.CHEESE_LOG, 1, i));
			OreDictionary.registerOre("treeSapling", new ItemStack(InitBlock.CHEESE_SAPLING, 1, i));
		}
	}
	
	private static void registerOres(String key, Object... items) {
        for (final Object item : items) {
            if (item instanceof Block) {
                OreDictionary.registerOre(key, (Block) item);
            } else if (item instanceof Item) {
                OreDictionary.registerOre(key, (Item) item);
            }

        }
    }
}
