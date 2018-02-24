package coffeecatteam.cheesemod.crafting.foodmakers;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.init.InitItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {
	
	public static void initGrill() {
		// Blocks
		Grilling.addRecipe(new ItemStack(InitItem.GRILLING_OIL, 1, 32767), new ItemStack(InitBlock.FOOD_BLOCK, 1, 0), new ItemStack(InitBlock.FOOD_BLOCK, 1, 1), 0.7F);

		Grilling.addRecipe(new ItemStack(InitItem.GRILLING_OIL, 1, 32767), new ItemStack(InitBlock.CHEESE_STAIRS, 1), new ItemStack(InitBlock.GRILLED_CHEESE_STAIRS), 0.7F);
		Grilling.addRecipe(new ItemStack(InitItem.GRILLING_OIL, 1, 32767), new ItemStack(InitBlock.CHEESE_SLAB, 1), new ItemStack(InitBlock.GRILLED_CHEESE_SLAB), 0.7F);

		// Items
		Grilling.addRecipe(new ItemStack(InitItem.GRILLING_OIL, 1, 32767), new ItemStack(InitItem.CHEESE_TOASTIE, 1), new ItemStack(InitItem.GRILLED_CHEESE_TOASTIE), 0.7F);
		Grilling.addRecipe(new ItemStack(InitItem.GRILLING_OIL, 1, 32767), new ItemStack(InitItem.HAM_RAW_N_CHEESE_TOASTIE, 1), new ItemStack(InitItem.GRILLED_HAM_RAW_N_CHEESE_TOASTIE), 0.7F);
		Grilling.addRecipe(new ItemStack(InitItem.GRILLING_OIL, 1, 32767), new ItemStack(InitItem.HAM_COOKED_N_CHEESE_TOASTIE, 1), new ItemStack(InitItem.GRILLED_HAM_COOKED_N_CHEESE_TOASTIE), 0.7F);
		
		Grilling.addRecipe(new ItemStack(InitItem.GRILLING_OIL, 1, 32767), new ItemStack(InitItem.CHEESE_N_CRACKER, 1), new ItemStack(InitItem.GRILLED_CHEESE_N_CRACKER), 1.0F);
		
		FurnaceRecipes.instance().getSmeltingList().forEach((input, output) -> {
			Grilling.addRecipe(new ItemStack(InitItem.SMELTING_OIL, 1, 32767), input, output, FurnaceRecipes.instance().getSmeltingExperience(input));
		});
	}
	
	public static void initCrackerMaker() {
		if (CheeseMod.DEVELOPER_MODE)
			CrackerMaking.addRecipe(new ItemStack(InitItem.CRACKER, 1), 1, new ItemStack(InitItem.FLOUR), new ItemStack(InitItem.SALT), new ItemStack(Items.SUGAR), new ItemStack(Items.WATER_BUCKET));
	}
}
