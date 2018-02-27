package coffeecatteam.cheesemod.crafting;

import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.init.InitItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smelting {

	public static void register() {
		// Cheese Ore > Cheese Ingot
		GameRegistry.addSmelting(InitBlock.CHEESE_METAL_ORE, new ItemStack(InitItem.CHEESE_METAL_INGOT, 1), 10);

		// Ham Raw Ore > Ham Raw Ingot
		GameRegistry.addSmelting(InitBlock.HAM_RAW_METAL_ORE, new ItemStack(InitItem.HAM_RAW_METAL_INGOT, 1), 10);

		// Ham Cooked Ore > Ham Cooked Ingot
		GameRegistry.addSmelting(InitBlock.HAM_COOKED_METAL_ORE, new ItemStack(InitItem.HAM_COOKED_METAL_INGOT, 1), 15);

		// Ham Raw > Ham Cooked
		GameRegistry.addSmelting(InitItem.HAM_RAW, new ItemStack(InitItem.HAM_COOKED, 1), 6);

		// Ham Raw Block > Ham Cooked Block
		GameRegistry.addSmelting(new ItemStack(InitBlock.FOOD_BLOCK, 1, 2), new ItemStack(InitBlock.FOOD_BLOCK, 1, 3),
				10);

		// Grilled Cheese Ore > Grilled Cheese Ingot
		GameRegistry.addSmelting(InitBlock.GRILLED_CHEESE_METAL_ORE,
				new ItemStack(InitItem.GRILLED_CHEESE_METAL_INGOT, 1), 15);

		// Bread > Toast
		GameRegistry.addSmelting(Items.BREAD, new ItemStack(InitItem.TOAST, 1), 7);

		// Dough > Bread
		GameRegistry.addSmelting(InitItem.DOUGH, new ItemStack(Items.BREAD, 1), 4);

		// Nether Cheese Ore > Cheese Ingot
		GameRegistry.addSmelting(InitBlock.NETHER_CHEESE_METAL_ORE, new ItemStack(InitItem.CHEESE_METAL_INGOT, 1), 10);

		// Nether Grilled Cheese Ore > Grilled Cheese Ingot
		GameRegistry.addSmelting(InitBlock.NETHER_GRILLED_CHEESE_METAL_ORE,
				new ItemStack(InitItem.GRILLED_CHEESE_METAL_INGOT, 1), 15);

		// Nether Ham Raw Ore > Ham Raw Ingot
		GameRegistry.addSmelting(InitBlock.NETHER_HAM_RAW_METAL_ORE, new ItemStack(InitItem.HAM_RAW_METAL_INGOT, 1),
				10);

		// Nether Ham Cooked Ore > Ham Cooked Ingot
		GameRegistry.addSmelting(InitBlock.NETHER_HAM_COOKED_METAL_ORE,
				new ItemStack(InitItem.HAM_COOKED_METAL_INGOT, 1), 15);

		// End Cheese Ore > Cheese Ingot
		GameRegistry.addSmelting(InitBlock.END_CHEESE_METAL_ORE, new ItemStack(InitItem.CHEESE_METAL_INGOT, 1), 10);

		// End Grilled Cheese Ore > Grilled Cheese Ingot
		GameRegistry.addSmelting(InitBlock.END_GRILLED_CHEESE_METAL_ORE,
				new ItemStack(InitItem.GRILLED_CHEESE_METAL_INGOT, 1), 15);

		// End Ham Raw Ore > Ham Raw Ingot
		GameRegistry.addSmelting(InitBlock.END_HAM_RAW_METAL_ORE, new ItemStack(InitItem.HAM_RAW_METAL_INGOT, 1), 10);

		// End Ham Cooked Ore > Ham Cooked Ingot
		GameRegistry.addSmelting(InitBlock.END_HAM_COOKED_METAL_ORE, new ItemStack(InitItem.HAM_COOKED_METAL_INGOT, 1),
				15);

		// Water Bucket > Salt
		GameRegistry.addSmelting(Items.WATER_BUCKET, new ItemStack(InitItem.SALT, 2), 15);
	}
}
