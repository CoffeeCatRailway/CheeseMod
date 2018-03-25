package coffeecatteam.cheesemod.init;

import java.util.HashSet;
import java.util.Set;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.items.ItemGrillOil;
import coffeecatteam.cheesemod.objects.items.ItemKnife;
import coffeecatteam.cheesemod.objects.items.ItemTest;
import coffeecatteam.cheesemod.objects.items.base.ItemBase;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseFood;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseSeeds;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseTool;
import coffeecatteam.cheesemod.objects.items.foods.ItemCracker;
import coffeecatteam.cheesemod.objects.items.foods.ItemPizza;
import coffeecatteam.cheesemod.objects.items.foods.ItemToast;
import coffeecatteam.cheesemod.objects.items.foods.ItemToastie;
import coffeecatteam.cheesemod.util.OreDictionaries;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.util.handlers.EnumHandler.EnumToastType;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

public class InitItem {
	
	public static final ToolMaterial OIL_MATERIAL = EnumHelper.addToolMaterial("oil_material", 1, 200, 0, 0.5F, 0);
	public static final ToolMaterial BASIC_MATERIAL = EnumHelper.addToolMaterial("basic_material", 1, 10, 0, 0.5F, 0);

	/*
	 * Other
	 */
	public static final Item GRILLING_OIL = new ItemGrillOil("grilling_oil", OreDictionaries.grillOil, OIL_MATERIAL, false, ItemStack.EMPTY);
	public static final Item SMELTING_OIL = new ItemGrillOil("smelting_oil", OreDictionaries.grillOil, OIL_MATERIAL, false, ItemStack.EMPTY);

	/*
	 *  Ingots
	 */
	public static final Item CHEESE_METAL_INGOT = new ItemBase("cheese_metal_ingot", OreDictionaries.foodIngot);
	public static final Item GRILLED_CHEESE_METAL_INGOT = new ItemBase("grilled_cheese_metal_ingot", OreDictionaries.foodIngot);
	public static final Item HAM_RAW_METAL_INGOT = new ItemBase("ham_raw_metal_ingot", OreDictionaries.foodIngot);
	public static final Item HAM_COOKED_METAL_INGOT = new ItemBase("ham_cooked_metal_ingot", OreDictionaries.foodIngot);
	
	/*
	 *  Tools
	 */
	public static final Item KNIFE = new ItemKnife("knife", "knife", ToolMaterial.IRON, true, new ItemStack(Items.IRON_INGOT, 1, 0));
	public static final Item CHEESE_KNIFE = new ItemKnife("cheese_knife", "knife", InitTool.CHEESE_METAL_TOOL_MATERIAL, true, new ItemStack(CHEESE_METAL_INGOT, 1, 0));
	public static final Item HAM_RAW_KNIFE = new ItemKnife("ham_raw_knife", "knife", InitTool.HAM_RAW_METAL_TOOL_MATERIAL, true, new ItemStack(HAM_RAW_METAL_INGOT, 1, 0));
	public static final Item HAM_COOKED_KNIFE = new ItemKnife("ham_cooked_knife", "knife", InitTool.HAM_COOKED_METAL_TOOL_MATERIAL, true, new ItemStack(HAM_COOKED_METAL_INGOT, 1, 0));

	public static final Item CHEESE_CUTTER = new ItemBaseTool("cheese_cutter", BASIC_MATERIAL, true, new ItemStack(Blocks.STONE, 1, 0));
	public static final Item GRINDING_STONES = new ItemBaseTool("grinding_stones", ToolMaterial.STONE, false, ItemStack.EMPTY);
	
	public static final Item MILK_CURDLER = new ItemBaseTool("milk_curdler", ToolMaterial.WOOD, true, new ItemStack(Blocks.PLANKS, 1, 0));
	public static final Item ROLLING_PIN = new ItemBaseTool("rolling_pin", ToolMaterial.WOOD, true, new ItemStack(Blocks.PLANKS, 1, 0));

	/*
	 *  Foods
	 */
	public static final Item BLOCK_O_CHEESE = new ItemBaseFood("block_o_cheese", OreDictionaries.foodCheese, 6, false);
	public static final Item CHEESE_SLICE = new ItemBaseFood("cheese_slice", OreDictionaries.foodCheese, 2, false);
	
	public static final Item SALT = new ItemBaseFood("salt", OreDictionaries.foodIngredient, 1, false);
	public static final Item FLOUR = new ItemBaseFood("flour", OreDictionaries.foodIngredient, 1, false);
	
	public static final Item HAM_RAW = new ItemBaseFood("ham_raw", OreDictionaries.foodHam, 2, true);
	public static final Item HAM_COOKED = new ItemBaseFood("ham_cooked", OreDictionaries.foodHam, 3, false);
	
	public static final Item BACON_RAW = new ItemBaseFood("bacon_raw", OreDictionaries.foodBacon, 2, true);
	public static final Item BACON_COOKED = new ItemBaseFood("bacon_cooked", OreDictionaries.foodBacon, 5, false);

	public static final Item TOAST = new ItemToast("toast", OreDictionaries.foodToast);
	public static final Item BREAD_SLICE = new ItemBaseFood("bread_slice", OreDictionaries.foodBreadSlice, 2, false);
	
	public static final Item DOUGH = new ItemBaseFood("dough", OreDictionaries.foodIngredient, 3, false);
	
	// Pizza
	public static final Item PIZZA = new ItemPizza("pizza", OreDictionaries.foodPizza);

	// Toastie
	public static final Item TOASTIE = new ItemToastie("toastie", OreDictionaries.foodToastie);
	
	// Cracker
	public static final Item CRACKER = new ItemCracker("cracker", OreDictionaries.foodCracker);
	
	// Pineapple
	public static final Item PINEAPPLE_PLANT = new ItemBaseSeeds("pineapple_plant", "pineapple", InitBlock.PINEAPPLE, Blocks.FARMLAND);
	public static final Item PINEAPPLE = new ItemBaseFood("pineapple", "pineapple", 12, false);
	public static final Item PINEAPPLE_RING = new ItemBaseFood("pineapple_ring", "foodPineapple", 3, false);
	public static final Item PINEAPPLE_BIT = new ItemBaseFood("pineapple_bit", "foodPineapple", 1, false);
	
	/*
	 * Other
	 */
	public static final Item CHEESE_BIT = new ItemBase("cheese_bit", OreDictionaries.foodBit);
	public static final Item HAM_BIT = new ItemBase("ham_bit", OreDictionaries.foodBit);

	public static final Item WOODEN_GEAR = new ItemBase("wooden_gear", OreDictionaries.gearWood);

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
				// Ingots
				CHEESE_METAL_INGOT, GRILLED_CHEESE_METAL_INGOT, HAM_RAW_METAL_INGOT, HAM_COOKED_METAL_INGOT,
				// Tools
				KNIFE, CHEESE_KNIFE, HAM_RAW_KNIFE, HAM_COOKED_KNIFE,
				CHEESE_CUTTER, GRINDING_STONES,
				MILK_CURDLER, ROLLING_PIN,
				// Foods
				BLOCK_O_CHEESE, CHEESE_SLICE,
				SALT, FLOUR,
				HAM_RAW, HAM_COOKED,
				BACON_RAW, BACON_COOKED,
				TOAST, BREAD_SLICE,
				DOUGH,
				// Pizza
				PIZZA,
				// Toastie
				TOASTIE,
				// Cracker
				CRACKER,
				// Pineapple
				PINEAPPLE_PLANT, PINEAPPLE, PINEAPPLE_RING, PINEAPPLE_BIT,
				// Other
				CHEESE_BIT, HAM_BIT,
				WOODEN_GEAR
		};

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			IForgeRegistry<Item> reg = event.getRegistry();

			for (Item item : items) {
				reg.register(item);
				ITEM_LIST.add(item);
				if (item instanceof IOreDict) {
					if (!item.getHasSubtypes()) {
						String oreDict = ((IOreDict) item).registerOre();
						OreDictionary.registerOre(oreDict, item);
						CheeseMod.logger.info("Item [" + item.getUnlocalizedName() + "] registered to ore dictionary name [" + oreDict + "]!");
					}
				}
			}
		}

		@SubscribeEvent
		public static void registerModels(final ModelRegistryEvent event) {
			for (Item item : ITEM_LIST)
				if (!item.getHasSubtypes() || item instanceof ItemSlab)
					if (!registeredItemList.contains(item))
						registerItemModel(item);
			
			/*
			 * Register toast item models
			 */
			for (int i = 0; i < EnumHandler.EnumToastType.values().length; i++) {
				registerItemModelVariants(TOAST, i, "toast_" + EnumHandler.EnumToastType.values()[i].getName());
				
				ItemStack stack = new ItemStack(TOAST, 1, i);
				String oreDict = ((IOreDict) stack.getItem()).registerOre();
				OreDictionary.registerOre(oreDict, stack);
				String name = stack.getItem().getUnlocalizedName() + "." + EnumToastType.byMetaData(i).getUnlocalizedName();
				CheeseMod.logger.info("Item [" + name + "] registered to ore dictionary name [" + oreDict + "]!");
			}
			
			/*
			 * Register cracker item models
			 */
			for (int i = 0; i < EnumHandler.EnumCrackerType.values().length; i++) {
				registerItemModelVariants(CRACKER, i, "cracker_" + EnumHandler.EnumCrackerType.values()[i].getName());
				
				ItemStack stack = new ItemStack(CRACKER, 1, i);
				String oreDict = ((IOreDict) stack.getItem()).registerOre();
				OreDictionary.registerOre(oreDict, stack);
				String name = stack.getItem().getUnlocalizedName() + "." + EnumHandler.EnumCrackerType.byMetaData(i).getUnlocalizedName();
				CheeseMod.logger.info("Item [" + name + "] registered to ore dictionary name [" + oreDict + "]!");
			}
			
			/*
			 * Register toastie item models
			 */
			for (int i = 0; i < EnumHandler.EnumToastieType.values().length; i++) {
				registerItemModelVariants(TOASTIE, i, "toastie_" + EnumHandler.EnumToastieType.values()[i].getName());
				
				ItemStack stack = new ItemStack(TOASTIE, 1, i);
				String oreDict = ((IOreDict) stack.getItem()).registerOre();
				OreDictionary.registerOre(oreDict, stack);
				String name = stack.getItem().getUnlocalizedName() + "." + EnumHandler.EnumToastieType.byMetaData(i).getUnlocalizedName();
				CheeseMod.logger.info("Item [" + name + "] registered to ore dictionary name [" + oreDict + "]!");
			}
			
			/*
			 * Register pizza item models
			 */
			for (int i = 0; i < EnumHandler.EnumPizzaType.values().length; i++) {
				registerItemModelVariants(PIZZA, i, "pizza_" + EnumHandler.EnumPizzaType.values()[i].getName());
				
				ItemStack stack = new ItemStack(PIZZA, 1, i);
				String oreDict = ((IOreDict) stack.getItem()).registerOre();
				OreDictionary.registerOre(oreDict, stack);
				String name = stack.getItem().getUnlocalizedName() + "." + EnumHandler.EnumPizzaType.byMetaData(i).getUnlocalizedName();
				CheeseMod.logger.info("Item [" + name + "] registered to ore dictionary name [" + oreDict + "]!");
			}
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

		private static void registerItemModelVariants(Item item, int meta, String filename) {
			ModelLoader.setCustomModelResourceLocation(item, meta,
					new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), "inventory"));
		}
	}
}
