package coffeecatteam.cheesemod.init;

import java.util.HashSet;
import java.util.Set;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.items.ItemTest;
import coffeecatteam.cheesemod.objects.items.base.ItemBase;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseFood;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseTool;
import coffeecatteam.cheesemod.objects.items.foods.ItemCracker;
import coffeecatteam.cheesemod.objects.items.foods.ItemToastie;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class InitItem {
	
	public static final ToolMaterial OIL_MATERIAL = EnumHelper.addToolMaterial("oil_material", 1, 100, 0, 0.5F, 0);
	public static final ToolMaterial BASIC_MATERIAL = EnumHelper.addToolMaterial("basic_material", 1, 10, 0, 0.5F, 0);

	/*
	 * Other
	 */
	public static final Item GRILLING_OIL = new ItemBaseTool("grilling_oil", OIL_MATERIAL);
	public static final Item SMELTING_OIL = new ItemBaseTool("smelting_oil", OIL_MATERIAL);
	
	/*
	 *  Tools
	 */
	public static final Item KNIFE = new ItemBaseTool("knife", ToolMaterial.IRON);
	public static final Item CHEESE_KNIFE = new ItemBaseTool("cheese_knife", InitTool.CHEESE_METAL_TOOL_MATERIAL);
	public static final Item HAM_RAW_KNIFE = new ItemBaseTool("ham_raw_knife", InitTool.HAM_RAW_METAL_TOOL_MATERIAL);
	public static final Item HAM_COOKED_KNIFE = new ItemBaseTool("ham_cooked_knife", InitTool.HAM_COOKED_METAL_TOOL_MATERIAL);

	public static final Item CHEESE_CUTTER = new ItemBaseTool("cheese_cutter", BASIC_MATERIAL);
	public static final Item GRINDING_STONES = new ItemBaseTool("grinding_stones", ToolMaterial.STONE);

	/*
	 *  Ingots
	 */
	public static final Item CHEESE_METAL_INGOT = new ItemBase("cheese_metal_ingot");
	public static final Item GRILLED_CHEESE_METAL_INGOT = new ItemBase("grilled_cheese_metal_ingot");
	public static final Item HAM_RAW_METAL_INGOT = new ItemBase("ham_raw_metal_ingot");
	public static final Item HAM_COOKED_METAL_INGOT = new ItemBase("ham_cooked_metal_ingot");

	/*
	 *  Foods
	 */
	public static final Item BLOCK_O_CHEESE = new ItemBaseFood("block_o_cheese", 6, false);
	public static final Item CHEESE_SLICE = new ItemBaseFood("cheese_slice", 2, false);
	
	public static final Item SALT = new ItemBaseFood("salt", 1, false);
	public static final Item FLOUR = new ItemBaseFood("flour", 1, false);
	
	public static final Item HAM_RAW = new ItemBaseFood("ham_raw", 2, true);
	public static final Item HAM_COOKED = new ItemBaseFood("ham_cooked", 4, false);

	public static final Item TOAST = new ItemBaseFood("toast", 3, false);
	public static final Item TOAST_SLICE = new ItemBaseFood("toast_slice", 2, false);

	// Toasties
	public static final Item CHEESE_TOASTIE = new ItemToastie("cheese_toastie", 8, true, "Grilled anyone..?", false);
	public static final Item GRILLED_CHEESE_TOASTIE = new ItemToastie("grilled_cheese_toastie", 13, true, "Tastes better grilled, right..?", true);
	public static final Item HAM_RAW_N_CHEESE_TOASTIE = new ItemToastie("ham_raw_n_cheese_toastie", 13, false, "So, Do you want it grilled anyone..?", false);
	public static final Item HAM_COOKED_N_CHEESE_TOASTIE = new ItemToastie("ham_cooked_n_cheese_toastie", 15, false, "So, Do you want it grilled anyone..?", false);
	public static final Item GRILLED_HAM_RAW_N_CHEESE_TOASTIE = new ItemToastie("grilled_ham_raw_n_cheese_toastie", 17, false, "So, Does it taste better grilled, right..?", true);
	public static final Item GRILLED_HAM_COOKED_N_CHEESE_TOASTIE = new ItemToastie( "grilled_ham_cooked_n_cheese_toastie", 19, false, "So, Does it taste better grilled, right..?", true);
	
	// Crackers
	public static final Item CRACKER = new ItemCracker("cracker", 5, false);
	public static final Item CHEESE_N_CRACKER = new ItemCracker("cheese_n_cracker", 7, false);
	public static final Item GRILLED_CHEESE_N_CRACKER = new ItemCracker("grilled_cheese_n_cracker", 10, false);
	
	public static final Item CHEESE_BIT = new ItemBase("cheese_bit");
	public static final Item HAM_BIT = new ItemBase("ham_bit");

	// Random
	public static final Item TEST_ITEM = new ItemTest("test_item");

	public static void init() {
		if (CheeseMod.DEVELOPER_MODE)
			register(TEST_ITEM);
	}

	private static void register(Item item) {
		ForgeRegistries.ITEMS.register(item);
		ItemRegistrationHandler.ITEM_LIST.add(item);
	}

	@EventBusSubscriber(modid = Reference.MODID)
	public static class ItemRegistrationHandler {
		public static final Set<Item> ITEM_LIST = new HashSet<>();

		private static final Set<Item> registeredItemList = new HashSet<>();
		private static final Item[] items = {
				// Other
				GRILLING_OIL,
				SMELTING_OIL,
				// Tools
				KNIFE, CHEESE_KNIFE, HAM_RAW_KNIFE, HAM_COOKED_KNIFE,
				CHEESE_CUTTER, GRINDING_STONES,
				// Ingots
				CHEESE_METAL_INGOT, GRILLED_CHEESE_METAL_INGOT, HAM_RAW_METAL_INGOT, HAM_COOKED_METAL_INGOT,
				// Foods
				BLOCK_O_CHEESE, CHEESE_SLICE,
				SALT, FLOUR,
				HAM_RAW, HAM_COOKED,
				TOAST, TOAST_SLICE,
				// Toasties
				CHEESE_TOASTIE, GRILLED_CHEESE_TOASTIE, HAM_RAW_N_CHEESE_TOASTIE, HAM_COOKED_N_CHEESE_TOASTIE, GRILLED_HAM_RAW_N_CHEESE_TOASTIE, GRILLED_HAM_COOKED_N_CHEESE_TOASTIE,
				// Crackers
				CRACKER, CHEESE_N_CRACKER, GRILLED_CHEESE_N_CRACKER,
				CHEESE_BIT, HAM_BIT
		};

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			IForgeRegistry<Item> reg = event.getRegistry();

			for (Item item : items) {
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
