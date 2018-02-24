package coffeecatteam.cheesemod.crafting;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.init.InitItem;
import net.minecraft.item.ItemStack;

public class RecipeGroups {

	// Knifes
	public static ItemStack[] knifes = { new ItemStack(InitItem.KNIFE, 1, 32767),
			new ItemStack(InitItem.CHEESE_KNIFE, 1, 32767), new ItemStack(InitItem.HAM_RAW_KNIFE, 1, 32767),
			new ItemStack(InitItem.HAM_COOKED_KNIFE, 1, 32767) };

	// Resource Locations\Groups
	/*
	 * Tools
	 */
	public static String cheeseTools = Reference.MODID + ":cheese_tool";
	public static String grilledCheeseTools = Reference.MODID + ":grilled_cheese_tool";
	public static String hamRawTools = Reference.MODID + ":ham_raw_tool";
	public static String hamCookedTools = Reference.MODID + ":ham_cooked_tool";

	/*
	 * Blocks
	 */
	public static String cheeseBlocks = Reference.MODID + ":cheese_blocks";
	public static String grilledCheeseBlocks = Reference.MODID + ":grilled_cheese_blocks";
	public static String hamRawBlocks = Reference.MODID + ":ham_raw_blocks";
	public static String hamCookedBlocks = Reference.MODID + ":ham_cooked_blocks";
	public static String foodDraws = Reference.MODID + ":food_draws";

	/*
	 * Items
	 */
	public static String cheeseItems = Reference.MODID + ":cheese_items";
	public static String grilledCheeseItems = Reference.MODID + ":grilled_cheese_items";
	public static String hamRawItems = Reference.MODID + ":ham_raw_items";
	public static String hamCookedItems = Reference.MODID + ":ham_cooked_items";
}
