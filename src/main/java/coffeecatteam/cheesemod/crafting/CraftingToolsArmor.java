package coffeecatteam.cheesemod.crafting;

import coffeecatteam.cheesemod.init.InitArmor;
import coffeecatteam.cheesemod.init.InitItem;
import coffeecatteam.cheesemod.init.InitTool;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CraftingToolsArmor {

	public static void register() {
		// Cheese Tools & Armor
		registerToolCrafting(InitTool.CHEESE_METAL_PICKAXE, InitTool.CHEESE_METAL_AXE, InitTool.CHEESE_METAL_HOE,
				InitTool.CHEESE_METAL_SHOVEL, InitTool.CHEESE_METAL_SWORD, InitItem.CHEESE_METAL_INGOT, "cheese_metal_tools");
		registerArmorCrafting(InitArmor.CHEESE_HELMET, InitArmor.CHEESE_CHESTPLATE, InitArmor.CHEESE_LEGGINGS,
				InitArmor.CHEESE_BOOTS, InitItem.CHEESE_METAL_INGOT, "cheese_armor");

		registerToolCrafting(InitTool.HAM_RAW_METAL_PICKAXE, InitTool.HAM_RAW_METAL_AXE, InitTool.HAM_RAW_METAL_HOE,
				InitTool.HAM_RAW_METAL_SHOVEL, InitTool.HAM_RAW_METAL_SWORD, InitItem.HAM_RAW_METAL_INGOT,
				"ham_raw_metal_tools");
		registerArmorCrafting(InitArmor.HAM_RAW_HELMET, InitArmor.HAM_RAW_CHESTPLATE, InitArmor.HAM_RAW_LEGGINGS,
				InitArmor.HAM_RAW_BOOTS, InitItem.HAM_RAW_METAL_INGOT, "ham_raw_armor");

		registerToolCrafting(InitTool.HAM_COOKED_METAL_PICKAXE, InitTool.HAM_COOKED_METAL_AXE,
				InitTool.HAM_COOKED_METAL_HOE, InitTool.HAM_COOKED_METAL_SHOVEL, InitTool.HAM_COOKED_METAL_SWORD,
				InitItem.HAM_COOKED_METAL_INGOT, "ham_cooked_metal_tools");
		registerArmorCrafting(InitArmor.HAM_COOKED_HELMET, InitArmor.HAM_COOKED_CHESTPLATE,
				InitArmor.HAM_COOKED_LEGGINGS, InitArmor.HAM_COOKED_BOOTS, InitItem.HAM_COOKED_METAL_INGOT,
				"ham_cooked_armor");

		// Grilled Cheese Tools & Armor
		registerToolCrafting(InitTool.GRILLED_CHEESE_METAL_PICKAXE, InitTool.GRILLED_CHEESE_METAL_AXE,
				InitTool.GRILLED_CHEESE_METAL_HOE, InitTool.GRILLED_CHEESE_METAL_SHOVEL,
				InitTool.GRILLED_CHEESE_METAL_SWORD, InitItem.GRILLED_CHEESE_METAL_INGOT, "grilled_cheese_metal_tools");
		registerArmorCrafting(InitArmor.GRILLED_CHEESE_HELMET, InitArmor.GRILLED_CHEESE_CHESTPLATE,
				InitArmor.GRILLED_CHEESE_LEGGINGS, InitArmor.GRILLED_CHEESE_BOOTS, InitItem.GRILLED_CHEESE_METAL_INGOT,
				"grilled_cheese_armor");
	}

	private static void registerToolCrafting(Item pickaxe, Item axe, Item hoe, Item shovel, Item sword, Item ingot,
			String resourceLocation) {
		for (ItemStack stack : OreDictionary.getOres("stickWood")) { 
			// Pickaxe
			GameRegistry.addShapedRecipe(new ResourceLocation("" + pickaxe.getRegistryName()),
					new ResourceLocation(resourceLocation), new ItemStack(pickaxe), "CCC", " S ", " S ", 'C', ingot, 'S',
					stack);
	
			// Axe
			GameRegistry.addShapedRecipe(new ResourceLocation("" + axe.getRegistryName()),
					new ResourceLocation(resourceLocation), new ItemStack(axe), "CC", "CS", " S", 'C', ingot, 'S',
					stack);
	
			// Hoe
			GameRegistry.addShapedRecipe(new ResourceLocation("" + hoe.getRegistryName()),
					new ResourceLocation(resourceLocation), new ItemStack(hoe), "CC", " S", " S", 'C', ingot, 'S',
					stack);
	
			// Shovel
			GameRegistry.addShapedRecipe(new ResourceLocation("" + shovel.getRegistryName()),
					new ResourceLocation(resourceLocation), new ItemStack(shovel), "C", "S", "S", 'C', ingot, 'S',
					stack);
	
			// Sword
			GameRegistry.addShapedRecipe(new ResourceLocation("" + sword.getRegistryName()),
					new ResourceLocation(resourceLocation), new ItemStack(sword), "C", "C", "S", 'C', ingot, 'S',
					stack);
		}
	}

	private static void registerArmorCrafting(Item helmet, Item chestplate, Item leggings, Item boots, Item ingot,
			String resourceLocation) {
		// Helmet
		GameRegistry.addShapedRecipe(new ResourceLocation("" + helmet.getRegistryName()),
				new ResourceLocation(resourceLocation), new ItemStack(helmet), "CCC", "C C", 'C', ingot);

		// Chestplate
		GameRegistry.addShapedRecipe(new ResourceLocation("" + chestplate.getRegistryName()),
				new ResourceLocation(resourceLocation), new ItemStack(chestplate), "C C", "CCC", "CCC", 'C', ingot);

		// Leggings
		GameRegistry.addShapedRecipe(new ResourceLocation("" + leggings.getRegistryName()),
				new ResourceLocation(resourceLocation), new ItemStack(leggings), "CCC", "C C", "C C", 'C', ingot);

		// Boots
		GameRegistry.addShapedRecipe(new ResourceLocation("" + boots.getRegistryName()),
				new ResourceLocation(resourceLocation), new ItemStack(boots), "C C", "C C", 'C', ingot);
	}
}
