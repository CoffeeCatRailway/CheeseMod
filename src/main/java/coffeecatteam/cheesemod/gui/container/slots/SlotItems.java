
package coffeecatteam.cheesemod.gui.container.slots;

import coffeecatteam.cheesemod.init.InitArmor;
import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.init.InitItem;
import coffeecatteam.cheesemod.init.InitTool;
import coffeecatteam.cheesemod.objects.blocks.tree.BlockCheesePlanks;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class SlotItems {

	public static Item[] cheeseItems = {
			InitItem.BLOCK_O_CHEESE, // Items
			InitItem.CHEESE_SLICE,
			InitItem.CHEESE_TOASTIE,
			InitItem.CHEESE_KNIFE,
			InitItem.CHEESE_METAL_INGOT,
			
			InitItem.GRILLED_CHEESE_TOASTIE, // Grilled Items
			InitItem.GRILLED_CHEESE_METAL_INGOT,

			Item.getItemFromBlock(InitBlock.CHEESE_METAL_ORE), // Blocks
			Item.getItemFromBlock(InitBlock.CHEESE_METAL_BLOCK),
			Item.getItemFromBlock(InitBlock.FOOD_BLOCK.getDefaultState().withProperty(BlockCheesePlanks.VARIANT, EnumHandler.EnumWoodType.CHEESE).getBlock()),
			Item.getItemFromBlock(InitBlock.CHEESE_SLAB),
			Item.getItemFromBlock(InitBlock.CHEESE_STAIRS),
			
			Item.getItemFromBlock(InitBlock.GRILLED_CHEESE_METAL_ORE), // Grilled Blocks
			Item.getItemFromBlock(InitBlock.GRILLED_CHEESE_METAL_BLOCK),
			Item.getItemFromBlock(InitBlock.FOOD_BLOCK.getDefaultState().withProperty(BlockCheesePlanks.VARIANT, EnumHandler.EnumWoodType.GRILLED_CHEESE).getBlock()),
			Item.getItemFromBlock(InitBlock.GRILLED_CHEESE_SLAB),
			Item.getItemFromBlock(InitBlock.GRILLED_CHEESE_STAIRS),

			InitArmor.CHEESE_HELMET, // Armor
			InitArmor.CHEESE_CHESTPLATE,
			InitArmor.CHEESE_LEGGINGS,
			InitArmor.CHEESE_BOOTS,
			
			InitArmor.GRILLED_CHEESE_HELMET, // Grilled Armor
			InitArmor.GRILLED_CHEESE_CHESTPLATE,
			InitArmor.GRILLED_CHEESE_LEGGINGS,
			InitArmor.GRILLED_CHEESE_BOOTS,

			InitTool.CHEESE_METAL_PICKAXE, // Tools
			InitTool.CHEESE_METAL_AXE,
			InitTool.CHEESE_METAL_HOE,
			InitTool.CHEESE_METAL_SHOVEL,
			InitTool.CHEESE_METAL_SWORD,
			InitTool.CHEESE_METAL_MULTITOOL,
			
			InitTool.GRILLED_CHEESE_METAL_PICKAXE, // Grilled Tools
			InitTool.GRILLED_CHEESE_METAL_AXE,
			InitTool.GRILLED_CHEESE_METAL_HOE,
			InitTool.GRILLED_CHEESE_METAL_SHOVEL,
			InitTool.GRILLED_CHEESE_METAL_SWORD,
			InitTool.GRILLED_CHEESE_METAL_MULTITOOL
	};

	public static Item[] hamItems = {
			Items.PORKCHOP, // Items
			InitItem.HAM_RAW,
			InitItem.HAM_RAW_N_CHEESE_TOASTIE,
			InitItem.HAM_RAW_KNIFE,
			InitItem.HAM_RAW_METAL_INGOT,
			
			Items.COOKED_PORKCHOP, // Cooked Items
			InitItem.HAM_COOKED,
			InitItem.HAM_COOKED_N_CHEESE_TOASTIE,
			InitItem.HAM_COOKED_KNIFE,
			InitItem.HAM_COOKED_METAL_INGOT,

			Item.getItemFromBlock(InitBlock.HAM_RAW_METAL_ORE), // Blocks
			Item.getItemFromBlock(InitBlock.HAM_RAW_METAL_BLOCK),
			Item.getItemFromBlock(InitBlock.FOOD_BLOCK.getDefaultState().withProperty(BlockCheesePlanks.VARIANT, EnumHandler.EnumWoodType.HAM_RAW).getBlock()),
			Item.getItemFromBlock(InitBlock.HAM_RAW_SLAB),
			Item.getItemFromBlock(InitBlock.HAM_RAW_STAIRS),
			
			Item.getItemFromBlock(InitBlock.HAM_COOKED_METAL_ORE), // Cooked Blocks
			Item.getItemFromBlock(InitBlock.HAM_COOKED_METAL_BLOCK),
			Item.getItemFromBlock(InitBlock.FOOD_BLOCK.getDefaultState().withProperty(BlockCheesePlanks.VARIANT, EnumHandler.EnumWoodType.HAM_COOKED).getBlock()),
			Item.getItemFromBlock(InitBlock.HAM_COOKED_SLAB),
			Item.getItemFromBlock(InitBlock.HAM_COOKED_STAIRS),

			InitArmor.HAM_RAW_HELMET, // Armor
			InitArmor.HAM_RAW_CHESTPLATE,
			InitArmor.HAM_RAW_LEGGINGS,
			InitArmor.HAM_RAW_BOOTS,
			
			InitArmor.HAM_COOKED_HELMET, // Cooked Armor
			InitArmor.HAM_COOKED_CHESTPLATE,
			InitArmor.HAM_COOKED_LEGGINGS,
			InitArmor.HAM_COOKED_BOOTS,

			InitTool.HAM_RAW_METAL_PICKAXE, // Tools
			InitTool.HAM_RAW_METAL_AXE,
			InitTool.HAM_RAW_METAL_HOE,
			InitTool.HAM_RAW_METAL_SHOVEL,
			InitTool.HAM_RAW_METAL_SWORD,
			InitTool.HAM_RAW_METAL_MULTITOOL,
			
			InitTool.HAM_COOKED_METAL_PICKAXE, // Cooked Tools
			InitTool.HAM_COOKED_METAL_AXE,
			InitTool.HAM_COOKED_METAL_HOE,
			InitTool.HAM_COOKED_METAL_SHOVEL,
			InitTool.HAM_COOKED_METAL_SWORD,
			InitTool.HAM_COOKED_METAL_MULTITOOL
	};
}
