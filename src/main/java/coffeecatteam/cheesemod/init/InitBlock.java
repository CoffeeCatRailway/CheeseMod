package coffeecatteam.cheesemod.init;

import java.util.HashSet;
import java.util.Set;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.init.InitItem.ItemRegistrationHandler;
import coffeecatteam.cheesemod.objects.blocks.BlockCheeseDraw;
import coffeecatteam.cheesemod.objects.blocks.BlockCrackerMaker;
import coffeecatteam.cheesemod.objects.blocks.BlockDoubleSlab;
import coffeecatteam.cheesemod.objects.blocks.BlockGrill;
import coffeecatteam.cheesemod.objects.blocks.BlockHalfSlab;
import coffeecatteam.cheesemod.objects.blocks.BlockHamDraw;
import coffeecatteam.cheesemod.objects.blocks.base.BlockBase;
import coffeecatteam.cheesemod.objects.blocks.base.BlockBaseStairs;
import coffeecatteam.cheesemod.objects.blocks.food.BlockFood;
import coffeecatteam.cheesemod.objects.blocks.food.BlockFoodGround;
import coffeecatteam.cheesemod.objects.blocks.item.ItemBlockVariants;
import coffeecatteam.cheesemod.objects.blocks.tree.BlockCheeseLeaves;
import coffeecatteam.cheesemod.objects.blocks.tree.BlockCheeseLog;
import coffeecatteam.cheesemod.objects.blocks.tree.BlockCheesePlanks;
import coffeecatteam.cheesemod.objects.blocks.tree.BlockCheeseSapling;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class InitBlock {

	// Ores
	public static final Block CHEESE_METAL_ORE = new BlockBase("cheese_metal_ore", 2.0F, 4.0F, Material.ROCK, "pickaxe", 1, CheeseMod.CHEESETAB);
	public static final Block NETHER_CHEESE_METAL_ORE = new BlockBase("nether_cheese_metal_ore", 2.0F, 4.0F, Material.ROCK, "pickaxe", 1, CheeseMod.CHEESETAB);
	public static final Block END_CHEESE_METAL_ORE = new BlockBase("end_cheese_metal_ore", 2.0F, 4.0F, Material.ROCK, "pickaxe", 1, CheeseMod.CHEESETAB);
	
	public static final Block GRILLED_CHEESE_METAL_ORE = new BlockBase("grilled_cheese_metal_ore", 2.5F, 4.5F, Material.ROCK, "pickaxe", 1, CheeseMod.CHEESETAB);
	public static final Block NETHER_GRILLED_CHEESE_METAL_ORE = new BlockBase("nether_grilled_cheese_metal_ore", 2.5F, 4.5F, Material.ROCK, "pickaxe", 1, CheeseMod.CHEESETAB);
	public static final Block END_GRILLED_CHEESE_METAL_ORE = new BlockBase("end_grilled_cheese_metal_ore", 2.5F, 4.5F, Material.ROCK, "pickaxe", 1, CheeseMod.CHEESETAB);

	public static final Block HAM_RAW_METAL_ORE = new BlockBase("ham_raw_metal_ore", 1.5F, 3.5F, Material.ROCK, "pickaxe", 2, CheeseMod.CHEESETAB);
	public static final Block NETHER_HAM_RAW_METAL_ORE = new BlockBase("nether_ham_raw_metal_ore", 1.5F, 3.5F, Material.ROCK, "pickaxe", 2, CheeseMod.CHEESETAB);
	public static final Block END_HAM_RAW_METAL_ORE = new BlockBase("end_ham_raw_metal_ore", 1.5F, 3.5F, Material.ROCK, "pickaxe", 2, CheeseMod.CHEESETAB);
	
	public static final Block HAM_COOKED_METAL_ORE = new BlockBase("ham_cooked_metal_ore", 2.0F, 4.0F, Material.ROCK, "pickaxe", 2, CheeseMod.CHEESETAB);
	public static final Block NETHER_HAM_COOKED_METAL_ORE = new BlockBase("nether_ham_cooked_metal_ore", 2.0F, 4.0F, Material.ROCK, "pickaxe", 2, CheeseMod.CHEESETAB);
	public static final Block END_HAM_COOKED_METAL_ORE = new BlockBase("end_ham_cooked_metal_ore", 2.0F, 4.0F, Material.ROCK, "pickaxe", 2, CheeseMod.CHEESETAB);

	// Food Metal Blocks
	public static final Block CHEESE_METAL_BLOCK = new BlockBase("cheese_metal_block", 2.5F, 4.5F, Material.IRON, "pickaxe", 2, CheeseMod.CHEESETAB);
	public static final Block GRILLED_CHEESE_METAL_BLOCK = new BlockBase("grilled_cheese_metal_block", 3.0F, 5.0F, Material.IRON, "pickaxe", 2, CheeseMod.CHEESETAB);

	public static final Block HAM_RAW_METAL_BLOCK = new BlockBase("ham_raw_metal_block", 2.0F, 4.0F, Material.IRON, "pickaxe", 2, CheeseMod.CHEESETAB);
	public static final Block HAM_COOKED_METAL_BLOCK = new BlockBase("ham_cooked_metal_block", 2.5F, 4.5F, Material.IRON, "pickaxe", 2, CheeseMod.CHEESETAB);

	// Blocks With Variants
	public static final Block CHEESE_PLANKS = new BlockCheesePlanks("cheese_planks", 1.0f, 1.0f, 1);
	public static final Block CHEESE_LEAVES = new BlockCheeseLeaves("cheese_leaves", 0.5f, 0.5f);
	public static final Block CHEESE_LOG = new BlockCheeseLog("cheese_log", 1.0f, 1.0f, 1);
	public static final Block CHEESE_SAPLING = new BlockCheeseSapling("cheese_sapling", 0.5f, 0.5f);

	public static final Block FOOD_BLOCK = new BlockFood("food_block", 1.0f, 1.0f, 1);
	public static final Block FOOD_GROUND_BLOCK = new BlockFoodGround("food_ground_block", 1.0f, 1.0f, 1);
	
	// Food Makers
    public static final BlockGrill GRILL_IDLE = new BlockGrill("grill", 1.0F, 1.5F, Material.IRON, 1, false);
    public static final BlockGrill GRILL_ACTIVE = new BlockGrill("grill", 1.0F, 1.5F, Material.IRON, 1, true);

    public static final BlockCrackerMaker CRACKER_MAKER_IDLE = new BlockCrackerMaker("cracker_maker", 1.0F, 1.5F, Material.IRON, 1, false);
    public static final BlockCrackerMaker CRACKER_MAKER_ACTIVE = new BlockCrackerMaker("cracker_maker", 1.0F, 1.5F, Material.IRON, 1, true);

	// Drawers
	public static final Block CHEESE_DRAW = new BlockCheeseDraw("cheese_draw", 0.5F, 0.8F, Material.WOOD, 1);
	public static final Block HAM_DRAW = new BlockHamDraw("ham_draw", 0.5F, 0.8F, Material.WOOD, 1);

	// Stairs
	public static final Block CHEESE_STAIRS = new BlockBaseStairs("cheese_stairs", FOOD_BLOCK.getDefaultState().withProperty(BlockFood.VARIANT, EnumHandler.EnumWoodType.CHEESE));
	public static final Block GRILLED_CHEESE_STAIRS = new BlockBaseStairs("grilled_cheese_stairs", FOOD_BLOCK.getDefaultState().withProperty(BlockFood.VARIANT, EnumHandler.EnumWoodType.GRILLED_CHEESE));
	public static final Block HAM_RAW_STAIRS = new BlockBaseStairs("ham_raw_stairs", FOOD_BLOCK.getDefaultState().withProperty(BlockFood.VARIANT, EnumHandler.EnumWoodType.HAM_RAW));
	public static final Block HAM_COOKED_STAIRS = new BlockBaseStairs("ham_cooked_stairs", FOOD_BLOCK.getDefaultState().withProperty(BlockFood.VARIANT, EnumHandler.EnumWoodType.HAM_COOKED));

	// Slabs
	public static final BlockSlab CHEESE_SLAB = new BlockHalfSlab("cheese_slab", 0.5F, 1.0F, Material.SPONGE, 1);
	public static final BlockSlab CHEESE_DOUBLE_SLAB = new BlockDoubleSlab("cheese_double_slab", 0.5F, 1.0F, Material.SPONGE, 1);
	public static final BlockSlab GRILLED_CHEESE_SLAB = new BlockHalfSlab("grilled_cheese_slab", 0.7F, 1.2F, Material.SPONGE, 1);
	public static final BlockSlab GRILLED_CHEESE_DOUBLE_SLAB = new BlockDoubleSlab("grilled_cheese_double_slab", 0.7F, 1.2F, Material.SPONGE, 1);
	public static final BlockSlab HAM_RAW_SLAB = new BlockHalfSlab("ham_raw_slab", 0.3F, 0.5F, Material.SPONGE, 1);
	public static final BlockSlab HAM_RAW_DOUBLE_SLAB = new BlockDoubleSlab("ham_raw_double_slab", 0.3F, 0.5F, Material.SPONGE, 1);
	public static final BlockSlab HAM_COOKED_SLAB = new BlockHalfSlab("ham_cooked_slab", 0.5F, 0.7F, Material.SPONGE, 1);
	public static final BlockSlab HAM_COOKED_DOUBLE_SLAB = new BlockDoubleSlab("ham_cooked_double_slab", 0.5F, 0.7F, Material.SPONGE, 1);

	public static void init() {
        ForgeRegistries.BLOCKS.register(GRILL_ACTIVE);
        ForgeRegistries.BLOCKS.register(CRACKER_MAKER_ACTIVE);
        
		// Register blocks with variants
		register(CHEESE_PLANKS, new ItemBlockVariants(CHEESE_PLANKS));
		register(CHEESE_LOG, new ItemBlockVariants(CHEESE_LOG));
		register(CHEESE_LEAVES, new ItemBlockVariants(CHEESE_LEAVES));
		register(CHEESE_SAPLING, new ItemBlockVariants(CHEESE_SAPLING));

		register(FOOD_BLOCK, new ItemBlockVariants(FOOD_BLOCK));
		register(FOOD_GROUND_BLOCK, new ItemBlockVariants(FOOD_GROUND_BLOCK));

		// Register block slabs
		register(CHEESE_SLAB, new ItemSlab(CHEESE_SLAB, CHEESE_SLAB, CHEESE_DOUBLE_SLAB));
		register(GRILLED_CHEESE_SLAB,
				new ItemSlab(GRILLED_CHEESE_SLAB, GRILLED_CHEESE_SLAB, GRILLED_CHEESE_DOUBLE_SLAB));
		register(HAM_RAW_SLAB, new ItemSlab(HAM_RAW_SLAB, HAM_RAW_SLAB, HAM_RAW_DOUBLE_SLAB));
		register(HAM_COOKED_SLAB, new ItemSlab(HAM_COOKED_SLAB, HAM_COOKED_SLAB, HAM_COOKED_DOUBLE_SLAB));

		ForgeRegistries.BLOCKS.registerAll(CHEESE_DOUBLE_SLAB, GRILLED_CHEESE_DOUBLE_SLAB, HAM_RAW_DOUBLE_SLAB,
				HAM_COOKED_DOUBLE_SLAB);
	}

	// Register Variants
	public static void register(Block block, ItemBlock itemblock) {
		ForgeRegistries.BLOCKS.register(block);
		itemblock.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(itemblock);
		
		if (itemblock instanceof ItemSlab)
			ItemRegistrationHandler.ITEM_LIST.add(itemblock);
	}

	@EventBusSubscriber(modid = Reference.MODID)
	public static class BlockRegistrationHandler {
		public static final Set<Block> BLOCK_LIST = new HashSet<>();
		public static final Set<Item> ITEM_LIST = new HashSet<>();

		private static final Set<Block> registeredBlockList = new HashSet<>();
		private static final Block[] blocks = { CHEESE_METAL_ORE, GRILLED_CHEESE_METAL_ORE, CHEESE_METAL_BLOCK,
				GRILLED_CHEESE_METAL_BLOCK, CHEESE_DRAW, HAM_DRAW, CHEESE_STAIRS,
				GRILLED_CHEESE_STAIRS, HAM_RAW_METAL_ORE, HAM_COOKED_METAL_ORE, HAM_RAW_METAL_BLOCK,
				HAM_COOKED_METAL_BLOCK, HAM_RAW_STAIRS, HAM_COOKED_STAIRS, NETHER_CHEESE_METAL_ORE,
				NETHER_GRILLED_CHEESE_METAL_ORE, NETHER_HAM_RAW_METAL_ORE, NETHER_HAM_COOKED_METAL_ORE,
				END_CHEESE_METAL_ORE, END_GRILLED_CHEESE_METAL_ORE, END_HAM_RAW_METAL_ORE, END_HAM_COOKED_METAL_ORE, GRILL_IDLE, CRACKER_MAKER_IDLE };

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> reg = event.getRegistry();

			for (final Block block : blocks) {
				reg.register(block);
				BLOCK_LIST.add(block);
			}
		}

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> reg = event.getRegistry();

			for (final Block item : blocks) {
				ItemBlock ib = (ItemBlock) new ItemBlock(item).setRegistryName(item.getRegistryName());
				reg.register(ib);
				ITEM_LIST.add(ib);
			}
		}

		@SubscribeEvent
		public static void registerModels(final ModelRegistryEvent event) {

			for (Block block : BLOCK_LIST)
				if (!registeredBlockList.contains(block))
					registerBlockModel(block);

			for (int i = 0; i < EnumHandler.EnumWoodType.values().length; i++) {
				registerBlockModelVariants(CHEESE_PLANKS, i,
						"planks_" + EnumHandler.EnumWoodType.values()[i].getName());
				registerBlockModelVariants(CHEESE_LOG, i, "log_" + EnumHandler.EnumWoodType.values()[i].getName());
				registerBlockModelVariants(CHEESE_LEAVES, i,
						"leaves_" + EnumHandler.EnumWoodType.values()[i].getName());
				registerBlockModelVariants(CHEESE_SAPLING, i,
						"sapling_" + EnumHandler.EnumWoodType.values()[i].getName());

				registerBlockModelVariants(FOOD_BLOCK, i,
						"food_block_" + EnumHandler.EnumWoodType.values()[i].getName());
			}
			
			for (int i = 0; i < EnumHandler.EnumGroundType.values().length; i++) {
				registerBlockModelVariants(FOOD_GROUND_BLOCK, i,
						"food_ground_block_" + EnumHandler.EnumGroundType.values()[i].getName());
			}
		}

		private static void registerBlockModel(final Block block) {
			final String registryName = block.getRegistryName().toString();
			final ModelResourceLocation location = new ModelResourceLocation(registryName, "inventory");
			registerBlockModel(block, location);
		}

		private static void registerBlockModel(final Block block, final ModelResourceLocation modelResourceLocation) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, modelResourceLocation);
			registeredBlockList.add(block);
		}

		public static void registerBlockModelVariants(Block block, int meta, String filename) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
					new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), "inventory"));
		}
	}
}
