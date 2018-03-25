package coffeecatteam.cheesemod.crafting.foodmakers;

import java.util.Map.Entry;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.init.InitItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {
	
	private static final ItemStack GRILLING_OIL = new ItemStack(InitItem.GRILLING_OIL, 1, 32767);
	
	public static void initGrill() {
		// Blocks
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitBlock.FOOD_BLOCK, 1, 0), new ItemStack(InitBlock.FOOD_BLOCK, 1, 1), 0.7F);

		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitBlock.CHEESE_STAIRS, 1), new ItemStack(InitBlock.GRILLED_CHEESE_STAIRS), 0.7F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitBlock.CHEESE_SLAB, 1), new ItemStack(InitBlock.GRILLED_CHEESE_SLAB), 0.7F);

		// Items
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.BACON_RAW, 1), new ItemStack(InitItem.BACON_COOKED), 0.7F);
		
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.TOASTIE, 1, 0), new ItemStack(InitItem.TOASTIE, 1, 1), 0.7F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.TOASTIE, 1, 2), new ItemStack(InitItem.TOASTIE, 1, 3), 0.7F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.TOASTIE, 1, 4), new ItemStack(InitItem.TOASTIE, 1, 5), 0.7F);
		
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.CRACKER, 1, 2), new ItemStack(InitItem.CRACKER, 1, 3), 1.0F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.CRACKER, 1, 4), new ItemStack(InitItem.CRACKER, 1, 5), 2.0F);
		
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.PIZZA, 1, 1), new ItemStack(InitItem.PIZZA, 1, 2), 2.0F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.PIZZA, 1, 3), new ItemStack(InitItem.PIZZA, 1, 4), 2.5F);
		Grilling.addRecipe(GRILLING_OIL, new ItemStack(InitItem.PIZZA, 1, 5), new ItemStack(InitItem.PIZZA, 1, 6), 2.9F);
		
		FurnaceRecipes.instance().getSmeltingList().forEach((input, output) -> {
			Grilling.addRecipe(new ItemStack(InitItem.SMELTING_OIL, 1, 32767), input, output, FurnaceRecipes.instance().getSmeltingExperience(input));
		});
	}
	
	public static void initCrackerMaker() {
		CrackerMaking.addRecipe(new ItemStack(InitItem.CRACKER, 1, 0), 1, new ItemStack(InitItem.FLOUR), new ItemStack(InitItem.SALT), new ItemStack(Items.SUGAR), new ItemStack(Items.WATER_BUCKET));
		CrackerMaking.addRecipe(new ItemStack(InitItem.CRACKER, 1, 1), 1, new ItemStack(InitItem.CRACKER), new ItemStack(InitItem.SALT), new ItemStack(InitItem.SALT), new ItemStack(Items.FISH, 1, 32767));
	}
}
