package coffeecatteam.cheesemod.init;

import java.util.HashSet;
import java.util.Set;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.tools.MultiTool;
import coffeecatteam.cheesemod.objects.tools.ToolAxe;
import coffeecatteam.cheesemod.objects.tools.ToolHoe;
import coffeecatteam.cheesemod.objects.tools.ToolPickaxe;
import coffeecatteam.cheesemod.objects.tools.ToolShovel;
import coffeecatteam.cheesemod.objects.tools.ToolSword;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class InitTool {
	
	public static final ToolMaterial CHEESE_METAL_TOOL_MATERIAL = EnumHelper.addToolMaterial("cheese_metal", 1, 150, 4.0F, 2.0F, 5);
	public static final ToolMaterial GRILLED_CHEESE_TOOL_MATERIAL = EnumHelper.addToolMaterial("grilled_cheese_metal", 2, 200, 5.2F, 2.5F, 8);

	public static final ToolMaterial HAM_RAW_METAL_TOOL_MATERIAL = EnumHelper.addToolMaterial("ham_raw_metal_metal", 1, 100, 3.5F, 2.3F, 6);
	public static final ToolMaterial HAM_COOKED_METAL_TOOL_MATERIAL = EnumHelper.addToolMaterial(" ham_cooked_metal_metal", 2, 170, 4.0F, 2.6F, 8);

	public static final Item CHEESE_METAL_PICKAXE = new ToolPickaxe("cheese_metal_pickaxe", CHEESE_METAL_TOOL_MATERIAL);
	public static final Item CHEESE_METAL_AXE = new ToolAxe("cheese_metal_axe", CHEESE_METAL_TOOL_MATERIAL);
	public static final Item CHEESE_METAL_HOE = new ToolHoe("cheese_metal_hoe", CHEESE_METAL_TOOL_MATERIAL);
	public static final Item CHEESE_METAL_SHOVEL = new ToolShovel("cheese_metal_shovel", CHEESE_METAL_TOOL_MATERIAL);
	public static final Item CHEESE_METAL_SWORD = new ToolSword("cheese_metal_sword", CHEESE_METAL_TOOL_MATERIAL);
	
	public static final Item GRILLED_CHEESE_METAL_PICKAXE = new ToolPickaxe("grilled_cheese_metal_pickaxe", GRILLED_CHEESE_TOOL_MATERIAL);
	public static final Item GRILLED_CHEESE_METAL_AXE = new ToolAxe("grilled_cheese_metal_axe", GRILLED_CHEESE_TOOL_MATERIAL);
	public static final Item GRILLED_CHEESE_METAL_HOE = new ToolHoe("grilled_cheese_metal_hoe", GRILLED_CHEESE_TOOL_MATERIAL);
	public static final Item GRILLED_CHEESE_METAL_SHOVEL = new ToolShovel("grilled_cheese_metal_shovel", GRILLED_CHEESE_TOOL_MATERIAL);
	public static final Item GRILLED_CHEESE_METAL_SWORD = new ToolSword("grilled_cheese_metal_sword", GRILLED_CHEESE_TOOL_MATERIAL);

	public static final Item HAM_RAW_METAL_PICKAXE = new ToolPickaxe("ham_raw_metal_pickaxe", HAM_RAW_METAL_TOOL_MATERIAL);
	public static final Item HAM_RAW_METAL_AXE = new ToolAxe("ham_raw_metal_axe", HAM_RAW_METAL_TOOL_MATERIAL);
	public static final Item HAM_RAW_METAL_HOE = new ToolHoe("ham_raw_metal_hoe", HAM_RAW_METAL_TOOL_MATERIAL);
	public static final Item HAM_RAW_METAL_SHOVEL = new ToolShovel("ham_raw_metal_shovel", HAM_RAW_METAL_TOOL_MATERIAL);
	public static final Item HAM_RAW_METAL_SWORD = new ToolSword("ham_raw_metal_sword", HAM_RAW_METAL_TOOL_MATERIAL);
	
	public static final Item HAM_COOKED_METAL_PICKAXE = new ToolPickaxe("ham_cooked_metal_pickaxe", HAM_COOKED_METAL_TOOL_MATERIAL);
	public static final Item HAM_COOKED_METAL_AXE = new ToolAxe("ham_cooked_metal_axe", HAM_COOKED_METAL_TOOL_MATERIAL);
	public static final Item HAM_COOKED_METAL_HOE = new ToolHoe("ham_cooked_metal_hoe", HAM_COOKED_METAL_TOOL_MATERIAL);
	public static final Item HAM_COOKED_METAL_SHOVEL = new ToolShovel("ham_cooked_metal_shovel", HAM_COOKED_METAL_TOOL_MATERIAL);
	public static final Item HAM_COOKED_METAL_SWORD = new ToolSword("ham_cooked_metal_sword", HAM_COOKED_METAL_TOOL_MATERIAL);

	public static final Item CHEESE_METAL_MULTITOOL = new MultiTool("cheese_metal_multitool", CHEESE_METAL_TOOL_MATERIAL);
	public static final Item GRILLED_CHEESE_METAL_MULTITOOL = new MultiTool("grilled_cheese_metal_multitool", GRILLED_CHEESE_TOOL_MATERIAL);
	public static final Item HAM_RAW_METAL_MULTITOOL = new MultiTool("ham_raw_metal_multitool", HAM_RAW_METAL_TOOL_MATERIAL);
	public static final Item HAM_COOKED_METAL_MULTITOOL = new MultiTool("ham_cooked_metal_multitool", HAM_COOKED_METAL_TOOL_MATERIAL);

	@EventBusSubscriber(modid = Reference.MODID)
	public static class ToolRegistrationHandler {
		public static final Set<Item> ITEM_LIST = new HashSet<>();
		
		private static final Set<Item> registeredItemList = new HashSet<>();
		private static final Item[] items = { CHEESE_METAL_PICKAXE, CHEESE_METAL_AXE, CHEESE_METAL_HOE, CHEESE_METAL_SHOVEL, CHEESE_METAL_SWORD,
				GRILLED_CHEESE_METAL_PICKAXE, GRILLED_CHEESE_METAL_AXE, GRILLED_CHEESE_METAL_HOE, GRILLED_CHEESE_METAL_SHOVEL,
				GRILLED_CHEESE_METAL_SWORD, HAM_RAW_METAL_PICKAXE, HAM_RAW_METAL_AXE, HAM_RAW_METAL_HOE, HAM_RAW_METAL_SHOVEL, HAM_RAW_METAL_SWORD,
				HAM_COOKED_METAL_PICKAXE, HAM_COOKED_METAL_AXE, HAM_COOKED_METAL_HOE, HAM_COOKED_METAL_SHOVEL, HAM_COOKED_METAL_SWORD,
				CHEESE_METAL_MULTITOOL, GRILLED_CHEESE_METAL_MULTITOOL, HAM_RAW_METAL_MULTITOOL, HAM_COOKED_METAL_MULTITOOL };

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> reg = event.getRegistry();

			for (final Item item : items) {
				reg.register(item);
				ITEM_LIST.add(item);
			}
		}
		
		@SubscribeEvent
		public static void registerModels(final ModelRegistryEvent event) {

			for (Item item : ITEM_LIST)
				if (!registeredItemList.contains(item))
					registerItemModel(item);
		}

		private static void registerItemModel(final Item item) {
			final String registryName = item.getRegistryName().toString();
			final ModelResourceLocation location = new ModelResourceLocation(registryName, "inventory");
			registerItemModel(item, location);
		}

		private static void registerItemModel(final Item item, final ModelResourceLocation modelResourceLocation) {
			ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);
			registeredItemList.add(item);
		}
	}
}
