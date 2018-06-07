package coffeecatteam.cheesemod.crafting.foodmakers;

import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.init.InitItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class Recipes {
	
	private static final ItemStack GRILLING_OIL = new ItemStack(InitItem.GRILLING_OIL, 1, 32767);
	
	public static void initGrill() {
		// Blocks
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitBlock.FOOD_BLOCK_CHEESE), new ItemStack(InitBlock.FOOD_BLOCK_GRILLED_CHEESE), 0.7F);

		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitBlock.CHEESE_STAIRS, 1), new ItemStack(InitBlock.GRILLED_CHEESE_STAIRS), 0.7F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitBlock.CHEESE_SLAB, 1), new ItemStack(InitBlock.GRILLED_CHEESE_SLAB), 0.7F);

		// Items
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.BACON_RAW, 1), new ItemStack(InitItem.BACON_COOKED), 0.7F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.EGG_CRACKED, 1), new ItemStack(InitItem.EGG_COOKED, 1), 0.7F);
		
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.TOASTIE_CHEESE), new ItemStack(InitItem.TOASTIE_GRILLED_CHEESE), 0.7F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.TOASTIE_HAM_RAW_N_CHEESE), new ItemStack(InitItem.TOASTIE_GRILLED_HAM_RAW_N_CHEESE), 0.7F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.TOASTIE_HAM_COOKED_N_CHEESE), new ItemStack(InitItem.TOASTIE_GRILLED_HAM_COOKED_N_CHEESE), 0.7F);
		
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.CRACKER_N_CHEESE, 1, 2), new ItemStack(InitItem.CRACKER_N_GRILLED_CHEESE, 1, 3), 1.0F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.CRACKER_CHEESE_N_CRAYFISH, 1, 4), new ItemStack(InitItem.CRACKER_GRILLED_CHEESE_N_CRAYFISH, 1, 5), 2.0F);
		
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.PIZZA_UNCOOKED_CHEESE), new ItemStack(InitItem.PIZZA_COOKED_CHEESE), 2.0F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.PIZZA_UNCOOKED_HAM_N_CHEESE), new ItemStack(InitItem.PIZZA_COOKED_HAM_N_CHEESE), 2.5F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.PIZZA_UNCOOKED_HAM_PINEAPPLE_N_CHEESE), new ItemStack(InitItem.PIZZA_COOKED_HAM_PINEAPPLE_N_CHEESE), 2.9F);
		
		FurnaceRecipes.instance().getSmeltingList().forEach((input, output) -> {
			Grilling.addRecipe(new ItemStack(InitItem.SMELTING_OIL, 1, 32767), input, output, FurnaceRecipes.instance().getSmeltingExperience(input));
		});
	}
	
	public static void initCrackerMaker() {
		CrackerMaking.addRecipe(new ItemStack(InitItem.CRACKER_PLAIN, 1), 1, new ItemStack(InitItem.FLOUR), new ItemStack(InitItem.SALT), new ItemStack(Items.SUGAR), new ItemStack(Items.WATER_BUCKET));
		CrackerMaking.addRecipe(new ItemStack(InitItem.CRACKER_CRAYFISH, 1, 1), 1, new ItemStack(InitItem.CRACKER_PLAIN), new ItemStack(InitItem.SALT), new ItemStack(InitItem.SALT), new ItemStack(Items.FISH, 1, 32767));
	}
}
