package coffeecatteam.cheesemod.init;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.objects.blocks.BlockCheeseDraw;
import coffeecatteam.cheesemod.objects.blocks.BlockCrackerMaker;
import coffeecatteam.cheesemod.objects.blocks.BlockGrill;
import coffeecatteam.cheesemod.objects.blocks.BlockHamDraw;
import coffeecatteam.cheesemod.objects.blocks.base.*;
import coffeecatteam.cheesemod.objects.blocks.crops.BlockPineapple;
import coffeecatteam.cheesemod.objects.blocks.base.BlockBaseGrass;
import coffeecatteam.cheesemod.objects.blocks.slab.BlockDoubleSlab;
import coffeecatteam.cheesemod.objects.blocks.slab.BlockHalfSlab;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeCheese;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeGrilledCheese;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeHamCooked;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeHamRaw;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class InitBlock {

	// Ores
	public static final Block CHEESE_METAL_ORE = new BlockBase("cheese_metal_ore", 2.0F, 4.0F, Material.ROCK, false, 1, CheeseMod.CHEESETAB);
	public static final Block NETHER_CHEESE_METAL_ORE = new BlockBase("nether_cheese_metal_ore", 2.0F, 4.0F, Material.ROCK, false, 1, CheeseMod.CHEESETAB);
	public static final Block END_CHEESE_METAL_ORE = new BlockBase("end_cheese_metal_ore", 2.0F, 4.0F, Material.ROCK, false, 1, CheeseMod.CHEESETAB);
	
	public static final Block GRILLED_CHEESE_METAL_ORE = new BlockBase("grilled_cheese_metal_ore", 2.5F, 4.5F, Material.ROCK, false, 1, CheeseMod.CHEESETAB);
	public static final Block NETHER_GRILLED_CHEESE_METAL_ORE = new BlockBase("nether_grilled_cheese_metal_ore", 2.5F, 4.5F, Material.ROCK, false, 1, CheeseMod.CHEESETAB);
	public static final Block END_GRILLED_CHEESE_METAL_ORE = new BlockBase("end_grilled_cheese_metal_ore", 2.5F, 4.5F, Material.ROCK, false, 1, CheeseMod.CHEESETAB);

	public static final Block HAM_RAW_METAL_ORE = new BlockBase("ham_raw_metal_ore", 1.5F, 3.5F, Material.ROCK, false, 2, CheeseMod.CHEESETAB);
	public static final Block NETHER_HAM_RAW_METAL_ORE = new BlockBase("nether_ham_raw_metal_ore", 1.5F, 3.5F, Material.ROCK, false, 2, CheeseMod.CHEESETAB);
	public static final Block END_HAM_RAW_METAL_ORE = new BlockBase("end_ham_raw_metal_ore", 1.5F, 3.5F, Material.ROCK, false, 2, CheeseMod.CHEESETAB);
	
	public static final Block HAM_COOKED_METAL_ORE = new BlockBase("ham_cooked_metal_ore", 2.0F, 4.0F, Material.ROCK, false, 2, CheeseMod.CHEESETAB);
	public static final Block NETHER_HAM_COOKED_METAL_ORE = new BlockBase("nether_ham_cooked_metal_ore", 2.0F, 4.0F, Material.ROCK, false, 2, CheeseMod.CHEESETAB);
	public static final Block END_HAM_COOKED_METAL_ORE = new BlockBase("end_ham_cooked_metal_ore", 2.0F, 4.0F, Material.ROCK, false, 2, CheeseMod.CHEESETAB);

	// Food Metal
	public static final Block CHEESE_METAL_BLOCK = new BlockBase("cheese_metal_block", 2.5F, 4.5F, Material.IRON, false, 2, CheeseMod.CHEESETAB);
	public static final Block GRILLED_CHEESE_METAL_BLOCK = new BlockBase("grilled_cheese_metal_block", 3.0F, 5.0F, Material.IRON, false, 2, CheeseMod.CHEESETAB);

	public static final Block HAM_RAW_METAL_BLOCK = new BlockBase("ham_raw_metal_block", 2.0F, 4.0F, Material.IRON, false, 2, CheeseMod.CHEESETAB);
	public static final Block HAM_COOKED_METAL_BLOCK = new BlockBase("ham_cooked_metal_block", 2.5F, 4.5F, Material.IRON, false, 2, CheeseMod.CHEESETAB);

	// Food Wood/Tree
    public static final Block PLANKS_CHEESE = new BlockBase("planks_cheese", 1.0f, 1.0f, Material.WOOD, true, 1, CheeseMod.CHEESETAB);
    public static final Block PLANKS_GRILLED_CHEESE = new BlockBase("planks_grilled_cheese", 1.0f, 1.0f, Material.WOOD, true, 1, CheeseMod.CHEESETAB);
    public static final Block PLANKS_HAM_RAW = new BlockBase("planks_ham_raw", 1.0f, 1.0f, Material.WOOD, true, 1, CheeseMod.CHEESETAB);
    public static final Block PLANKS_HAM_COOKED = new BlockBase("planks_ham_cooked", 1.0f, 1.0f, Material.WOOD, true, 1, CheeseMod.CHEESETAB);

    public static final Block LEAVES_CHEESE = new BlockBaseLeaves("cheese");
    public static final Block LEAVES_GRILLED_CHEESE = new BlockBaseLeaves("grilled_cheese");
    public static final Block LEAVES_HAM_RAW = new BlockBaseLeaves("ham_raw");
    public static final Block LEAVES_HAM_COOKED = new BlockBaseLeaves("ham_cooked");

    public static final Block LOG_CHEESE = new BlockBaseFacing("log_cheese", 1.0f, 1.0f, 1, false, true);
    public static final Block LOG_GRILLED_CHEESE = new BlockBaseFacing("log_grilled_cheese", 1.0f, 1.0f, 1, false, true);
    public static final Block LOG_HAM_RAW = new BlockBaseFacing("log_ham_raw", 1.0f, 1.0f, 1, false, true);
    public static final Block LOG_HAM_COOKED = new BlockBaseFacing("log_ham_cooked", 1.0f, 1.0f, 1, false, true);

    public static final Block SAPLING_CHEESE = new BlockBaseSapling("sapling_cheese", 0.5f, 0.5f, new WorldGenTreeCheese());
    public static final Block SAPLING_GRILLED_CHEESE = new BlockBaseSapling("sapling_grilled_cheese", 0.5f, 0.5f, new WorldGenTreeGrilledCheese());
    public static final Block SAPLING_HAM_RAW = new BlockBaseSapling("sapling_ham_raw", 0.5f, 0.5f, new WorldGenTreeHamRaw());
    public static final Block SAPLING_HAM_COOKED = new BlockBaseSapling("sapling_ham_cooked", 0.5f, 0.5f, new WorldGenTreeHamCooked());

    // Food Block
    public static final Block FOOD_BLOCK_CHEESE = new BlockBaseFacing("food_block_cheese", 1.0f, 1.0f, 1, true, false);
    public static final Block FOOD_BLOCK_GRILLED_CHEESE = new BlockBaseFacing("food_block_grilled_cheese", 1.0f, 1.0f, 1, true, false);
    public static final Block FOOD_BLOCK_HAM_RAW = new BlockBaseFacing("food_block_ham_raw", 1.0f, 1.0f, 1, true, false);
    public static final Block FOOD_BLOCK_HAM_COOKED = new BlockBaseFacing("food_block_ham_cooked", 1.0f, 1.0f, 1, true, false);

	public static final Block GRASS_CHEESE = new BlockBaseGrass("grass_cheese", 1.0f, 1.0f, 1);
    public static final Block GRASS_HAM = new BlockBaseGrass("grass_ham", 1.0f, 1.0f, 1);
	
	// Food Makers
    public static final BlockGrill GRILL_IDLE = new BlockGrill("grill", 1.0F, 1.5F, Material.IRON, 1, false);
    public static final BlockGrill GRILL_ACTIVE = new BlockGrill("grill", 1.0F, 1.5F, Material.IRON, 1, true);

    public static final BlockCrackerMaker CRACKER_MAKER_IDLE = new BlockCrackerMaker("cracker_maker", 1.0F, 1.5F, Material.IRON, 1, false);
    public static final BlockCrackerMaker CRACKER_MAKER_ACTIVE = new BlockCrackerMaker("cracker_maker", 1.0F, 1.5F, Material.IRON, 1, true);

	// Drawers
	public static final Block CHEESE_DRAW = new BlockCheeseDraw("cheese_draw", 0.5F, 0.8F, Material.WOOD, 1);
	public static final Block HAM_DRAW = new BlockHamDraw("ham_draw", 0.5F, 0.8F, Material.WOOD, 1);

	// Stairs
	public static final Block CHEESE_STAIRS = new BlockBaseStairs("cheese_stairs", FOOD_BLOCK_CHEESE.getDefaultState());
	public static final Block GRILLED_CHEESE_STAIRS = new BlockBaseStairs("grilled_cheese_stairs", FOOD_BLOCK_GRILLED_CHEESE.getDefaultState());
	public static final Block HAM_RAW_STAIRS = new BlockBaseStairs("ham_raw_stairs", FOOD_BLOCK_HAM_RAW.getDefaultState());
	public static final Block HAM_COOKED_STAIRS = new BlockBaseStairs("ham_cooked_stairs", FOOD_BLOCK_HAM_COOKED.getDefaultState());

	// Slabs
	public static final BlockSlab CHEESE_SLAB = new BlockHalfSlab("cheese_slab", 0.5F, 1.0F, Material.SPONGE, 1);
	public static final BlockSlab CHEESE_DOUBLE_SLAB = new BlockDoubleSlab("cheese_double_slab", 0.5F, 1.0F, Material.SPONGE, 1);
	public static final BlockSlab GRILLED_CHEESE_SLAB = new BlockHalfSlab("grilled_cheese_slab", 0.7F, 1.2F, Material.SPONGE, 1);
	public static final BlockSlab GRILLED_CHEESE_DOUBLE_SLAB = new BlockDoubleSlab("grilled_cheese_double_slab", 0.7F, 1.2F, Material.SPONGE, 1);
	public static final BlockSlab HAM_RAW_SLAB = new BlockHalfSlab("ham_raw_slab", 0.3F, 0.5F, Material.SPONGE, 1);
	public static final BlockSlab HAM_RAW_DOUBLE_SLAB = new BlockDoubleSlab("ham_raw_double_slab", 0.3F, 0.5F, Material.SPONGE, 1);
	public static final BlockSlab HAM_COOKED_SLAB = new BlockHalfSlab("ham_cooked_slab", 0.5F, 0.7F, Material.SPONGE, 1);
	public static final BlockSlab HAM_COOKED_DOUBLE_SLAB = new BlockDoubleSlab("ham_cooked_double_slab", 0.5F, 0.7F, Material.SPONGE, 1);
	
	// Other
	public static final Block PINEAPPLE = new BlockPineapple("pineapple_plant");
	public static final Block CHEESE_CAKE = new BlockBaseCake("cake_cheese", 0.5F);

	public static void init() {
        initSpecial();

        register(CHEESE_METAL_ORE, GRILLED_CHEESE_METAL_ORE, HAM_RAW_METAL_ORE, HAM_COOKED_METAL_ORE);
        register(NETHER_CHEESE_METAL_ORE, NETHER_GRILLED_CHEESE_METAL_ORE, NETHER_HAM_RAW_METAL_ORE, NETHER_HAM_COOKED_METAL_ORE);
        register(END_CHEESE_METAL_ORE, END_GRILLED_CHEESE_METAL_ORE, END_HAM_RAW_METAL_ORE, END_HAM_COOKED_METAL_ORE);

        register(CHEESE_METAL_BLOCK, GRILLED_CHEESE_METAL_BLOCK, HAM_RAW_METAL_BLOCK, HAM_COOKED_METAL_BLOCK);

        register(CHEESE_DRAW, HAM_DRAW);
        register(GRILL_IDLE, CRACKER_MAKER_IDLE);
        register(CHEESE_CAKE);

        register(FOOD_BLOCK_CHEESE, FOOD_BLOCK_GRILLED_CHEESE, FOOD_BLOCK_HAM_RAW, FOOD_BLOCK_HAM_COOKED);
        register(CHEESE_STAIRS, GRILLED_CHEESE_STAIRS, HAM_RAW_STAIRS, HAM_COOKED_STAIRS);

        register(PLANKS_CHEESE, PLANKS_GRILLED_CHEESE, PLANKS_HAM_RAW, PLANKS_HAM_COOKED);
        register(LEAVES_CHEESE, LEAVES_GRILLED_CHEESE, LEAVES_HAM_RAW, LEAVES_HAM_COOKED);
        register(LOG_CHEESE, LOG_GRILLED_CHEESE, LOG_HAM_RAW, LOG_HAM_COOKED);
        register(SAPLING_CHEESE, SAPLING_GRILLED_CHEESE, SAPLING_HAM_RAW, SAPLING_HAM_COOKED);

        register(GRASS_CHEESE, GRASS_HAM);
	}

	private static void initSpecial() {
        ForgeRegistries.BLOCKS.register(GRILL_ACTIVE);
        ForgeRegistries.BLOCKS.register(CRACKER_MAKER_ACTIVE);
        ForgeRegistries.BLOCKS.register(PINEAPPLE);

        registerItemBlock(CHEESE_SLAB, new ItemSlab(CHEESE_SLAB, CHEESE_SLAB, CHEESE_DOUBLE_SLAB));
        registerItemBlock(GRILLED_CHEESE_SLAB, new ItemSlab(GRILLED_CHEESE_SLAB, GRILLED_CHEESE_SLAB, GRILLED_CHEESE_DOUBLE_SLAB));
        registerItemBlock(HAM_RAW_SLAB, new ItemSlab(HAM_RAW_SLAB, HAM_RAW_SLAB, HAM_RAW_DOUBLE_SLAB));
        registerItemBlock(HAM_COOKED_SLAB, new ItemSlab(HAM_COOKED_SLAB, HAM_COOKED_SLAB, HAM_COOKED_DOUBLE_SLAB));

        ForgeRegistries.BLOCKS.registerAll(CHEESE_DOUBLE_SLAB, GRILLED_CHEESE_DOUBLE_SLAB, HAM_RAW_DOUBLE_SLAB, HAM_COOKED_DOUBLE_SLAB);
    }

    private static void register(Block... blocks) {
        for (Block block : blocks)
            register(block);
    }

    private static void register(Block block) {
	    RegistrationHandler.Blocks.BLOCKS.add(block);
        ItemBlock itemBlock = (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
        RegistrationHandler.Items.ITEMS.add(itemBlock);
    }

	public static void registerItemBlock(Block block, ItemBlock itemblock) {
		ForgeRegistries.BLOCKS.register(block);
		itemblock.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(itemblock);
		
		if (itemblock instanceof ItemSlab)
			RegistrationHandler.Items.ITEMS.add(itemblock);
	}
}
