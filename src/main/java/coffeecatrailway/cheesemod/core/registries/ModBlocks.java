package coffeecatrailway.cheesemod.core.registries;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.blocks.*;
import coffeecatrailway.cheesemod.core.special.ModFoods;
import coffeecatrailway.cheesemod.items.FoodBlockItem;
import coffeecatrailway.cheesemod.world.feature.tree.CheeseTree;
import coffeecatrailway.cheesemod.world.feature.tree.HamTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
public class ModBlocks {

    /// Ores ///
    public static Block CHEESE_METAL_ORE;
    public static Block CHEESE_METAL_ORE_NETHER;
    public static Block CHEESE_METAL_ORE_END;

    public static Block GRILLED_CHEESE_METAL_ORE;
    public static Block GRILLED_CHEESE_METAL_ORE_NETHER;
    public static Block GRILLED_CHEESE_METAL_ORE_END;

    public static Block HAM_RAW_METAL_ORE;
    public static Block HAM_RAW_METAL_ORE_NETHER;
    public static Block HAM_RAW_METAL_ORE_END;

    public static Block HAM_COOKED_METAL_ORE;
    public static Block HAM_COOKED_METAL_ORE_NETHER;
    public static Block HAM_COOKED_METAL_ORE_END;

    /// Food Metal ///
    public static Block CHEESE_METAL_BLOCK;
    public static Block GRILLED_CHEESE_METAL_BLOCK;
    public static Block HAM_RAW_METAL_BLOCK;
    public static Block HAM_COOKED_METAL_BLOCK;

    /// Food Wood/Tree Blocks ///
    public static Block CHEESE_PLANKS;
    public static Block GRILLED_CHEESE_PLANKS;
    public static Block HAM_RAW_PLANKS;
    public static Block HAM_COOKED_PLANKS;

    public static Block CHEESE_LEAVES;
    public static Block GRILLED_CHEESE_LEAVES;
    public static Block HAM_RAW_LEAVES;
    public static Block HAM_COOKED_LEAVES;

    public static Block CHEESE_LOG;
    public static Block GRILLED_CHEESE_LOG;
    public static Block HAM_RAW_LOG;
    public static Block HAM_COOKED_LOG;

    public static Block STRPPED_CHEESE_LOG;
    public static Block STRPPED_GRILLED_CHEESE_LOG;
    public static Block STRPPED_HAM_RAW_LOG;
    public static Block STRPPED_HAM_COOKED_LOG;

    public static Block CHEESE_WOOD;
    public static Block GRILLED_CHEESE_WOOD;
    public static Block HAM_RAW_WOOD;
    public static Block HAM_COOKED_WOOD;

    public static Block STRPPED_CHEESE_WOOD;
    public static Block STRPPED_GRILLED_CHEESE_WOOD;
    public static Block STRPPED_HAM_RAW_WOOD;
    public static Block STRPPED_HAM_COOKED_WOOD;

    public static Block CHEESE_SAPLING;
    public static Block GRILLED_CHEESE_SAPLING;
    public static Block HAM_RAW_SAPLING;
    public static Block HAM_COOKED_SAPLING;

    /// Food Block ///
    public static Block CHEESE_BLOCK;
    public static Block GRILLED_CHEESE_BLOCK;
    public static Block HAM_RAW_BLOCK;
    public static Block HAM_COOKED_BLOCK;

    public static Block CHEESE_GRASS_BLOCK;
    public static Block CHEESE_GRASS;
    public static Block CHEESE_TALL_GRASS;

    public static Block GRILLED_CHEESE_GRASS_BLOCK;
    public static Block GRILLED_CHEESE_GRASS;
    public static Block GRILLED_CHEESE_TALL_GRASS;

    public static Block HAM_RAW_GRASS_BLOCK;
    public static Block HAM_RAW_GRASS;
    public static Block HAM_RAW_TALL_GRASS;

    public static Block HAM_COOKED_GRASS_BLOCK;
    public static Block HAM_COOKED_GRASS;
    public static Block HAM_COOKED_TALL_GRASS;

    /// Stairs ///
    public static Block CHEESE_STAIRS;
    public static Block GRILLED_CHEESE_STAIRS;
    public static Block HAM_RAW_STAIRS;
    public static Block HAM_COOKED_STAIRS;

    public static Block CHEESE_WOOD_STAIRS;
    public static Block GRILLED_CHEESE_WOOD_STAIRS;
    public static Block HAM_RAW_WOOD_STAIRS;
    public static Block HAM_COOKED_WOOD_STAIRS;

    /// Slabs ///
    public static Block CHEESE_SLAB;
    public static Block GRILLED_CHEESE_SLAB;
    public static Block HAM_RAW_SLAB;
    public static Block HAM_COOKED_SLAB;

    public static Block CHEESE_WOOD_SLAB;
    public static Block GRILLED_CHEESE_WOOD_SLAB;
    public static Block HAM_RAW_WOOD_SLAB;
    public static Block HAM_COOKED_WOOD_SLAB;

    /// Drawers ///
    public static Block CHEESE_DRAW;
    public static Block GRILLED_CHEESE_DRAW;
    public static Block HAM_RAW_DRAW;
    public static Block HAM_COOKED_DRAW;

    /// Other ///
    public static Block GRILL;

    public static Block PINEAPPLE;
    public static Block CHEESE_CAKE;

    public static void registerAll(RegistryEvent.Register<Block> event) {
        if (!event.getName().equals(ForgeRegistries.BLOCKS.getRegistryName())) return;

        /// Ores ///
        CHEESE_METAL_ORE = register("cheese_metal_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));
        CHEESE_METAL_ORE_NETHER = register("cheese_metal_ore_nether", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));
        CHEESE_METAL_ORE_END = register("cheese_metal_ore_end", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));

        GRILLED_CHEESE_METAL_ORE = register("grilled_cheese_metal_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));
        GRILLED_CHEESE_METAL_ORE_NETHER = register("grilled_cheese_metal_ore_nether", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));
        GRILLED_CHEESE_METAL_ORE_END = register("grilled_cheese_metal_ore_end", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));

        HAM_RAW_METAL_ORE = register("ham_raw_metal_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));
        HAM_RAW_METAL_ORE_NETHER = register("ham_raw_metal_ore_nether", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));
        HAM_RAW_METAL_ORE_END = register("ham_raw_metal_ore_end", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));

        HAM_COOKED_METAL_ORE = register("ham_cooked_metal_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));
        HAM_COOKED_METAL_ORE_NETHER = register("ham_cooked_metal_ore_nether", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));
        HAM_COOKED_METAL_ORE_END = register("ham_cooked_metal_ore_end", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));

        /// Food Metal ///
        CHEESE_METAL_BLOCK = register("cheese_metal_block", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
        GRILLED_CHEESE_METAL_BLOCK = register("grilled_cheese_metal_block", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
        HAM_RAW_METAL_BLOCK = register("ham_raw_metal_block", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
        HAM_COOKED_METAL_BLOCK = register("ham_cooked_metal_block", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));

        /// Food Wood/Tree ///
        CHEESE_PLANKS = register("cheese_planks", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
        GRILLED_CHEESE_PLANKS = register("grilled_cheese_planks", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
        HAM_RAW_PLANKS = register("ham_raw_planks", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
        HAM_COOKED_PLANKS = register("ham_cooked_planks", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

        CHEESE_LEAVES = register("cheese_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)));
        GRILLED_CHEESE_LEAVES = register("grilled_cheese_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)));
        HAM_RAW_LEAVES = register("ham_raw_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)));
        HAM_COOKED_LEAVES = register("ham_cooked_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)));

        CHEESE_LOG = register("cheese_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        GRILLED_CHEESE_LOG = register("grilled_cheese_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        HAM_RAW_LOG = register("ham_raw_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        HAM_COOKED_LOG = register("ham_cooked_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));

        STRPPED_CHEESE_LOG = register("stripped_cheese_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        STRPPED_GRILLED_CHEESE_LOG = register("stripped_grilled_cheese_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        STRPPED_HAM_RAW_LOG = register("stripped_ham_raw_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        STRPPED_HAM_COOKED_LOG = register("stripped_ham_cooked_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));

        CHEESE_WOOD = register("cheese_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        GRILLED_CHEESE_WOOD = register("grilled_cheese_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        HAM_RAW_WOOD = register("ham_raw_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        HAM_COOKED_WOOD = register("ham_cooked_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));

        STRPPED_CHEESE_WOOD = register("stripped_cheese_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        STRPPED_GRILLED_CHEESE_WOOD = register("stripped_grilled_cheese_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        STRPPED_HAM_RAW_WOOD = register("stripped_ham_raw_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
        STRPPED_HAM_COOKED_WOOD = register("stripped_ham_cooked_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));

        CHEESE_SAPLING = register("cheese_sapling", new ModSaplingBlock(new CheeseTree(false), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT)));
        GRILLED_CHEESE_SAPLING = register("grilled_cheese_sapling", new ModSaplingBlock(new CheeseTree(true), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT)));
        HAM_RAW_SAPLING = register("ham_raw_sapling", new ModSaplingBlock(new HamTree(false), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT)));
        HAM_COOKED_SAPLING = register("ham_cooked_sapling", new ModSaplingBlock(new HamTree(true), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT)));

        /// Food Block ///
        CHEESE_BLOCK = new FoodBlock();
        register("cheese_block", CHEESE_BLOCK, new FoodBlockItem(CHEESE_BLOCK, ModFoods.CHEESE_SLICE, false));

        GRILLED_CHEESE_BLOCK = new FoodBlock();
        register("grilled_cheese_block", GRILLED_CHEESE_BLOCK, new FoodBlockItem(GRILLED_CHEESE_BLOCK, ModFoods.CHEESE_SLICE, true));

        HAM_RAW_BLOCK = new FoodBlock();
        register("ham_raw_block", HAM_RAW_BLOCK, new FoodBlockItem(HAM_RAW_BLOCK, ModFoods.HAM, false));

        HAM_COOKED_BLOCK = new FoodBlock();
        register("ham_cooked_block", HAM_COOKED_BLOCK, new FoodBlockItem(HAM_COOKED_BLOCK, ModFoods.HAM_COOKED, false));

        CHEESE_GRASS_BLOCK = register("cheese_grass_block", new GrassBlock(Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));
        CHEESE_GRASS = register("cheese_grass", new ModTallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));
        CHEESE_TALL_GRASS = register("cheese_tall_grass", new ModDoublePlantBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));

        GRILLED_CHEESE_GRASS_BLOCK = register("grilled_cheese_grass_block", new GrassBlock(Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));
        GRILLED_CHEESE_GRASS = register("grilled_cheese_grass", new ModTallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));
        GRILLED_CHEESE_TALL_GRASS = register("grilled_cheese_tall_grass", new ModDoublePlantBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));

        HAM_RAW_GRASS_BLOCK = register("ham_raw_grass_block", new GrassBlock(Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));
        HAM_RAW_GRASS = register("ham_raw_grass", new ModTallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));
        HAM_RAW_TALL_GRASS = register("ham_raw_tall_grass", new ModDoublePlantBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));

        HAM_COOKED_GRASS_BLOCK = register("ham_cooked_grass_block", new GrassBlock(Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));
        HAM_COOKED_GRASS = register("ham_cooked_grass", new ModTallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));
        HAM_COOKED_TALL_GRASS = register("ham_cooked_tall_grass", new ModDoublePlantBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)));

        /// Stairs ///
        CHEESE_STAIRS = register("cheese_stairs", new ModStairsBlock(CHEESE_BLOCK.getDefaultState(), Block.Properties.from(CHEESE_BLOCK), true));
        GRILLED_CHEESE_STAIRS = register("grilled_cheese_stairs", new ModStairsBlock(GRILLED_CHEESE_BLOCK.getDefaultState(), Block.Properties.from(GRILLED_CHEESE_BLOCK), true));
        HAM_RAW_STAIRS = register("ham_raw_stairs", new ModStairsBlock(HAM_RAW_BLOCK.getDefaultState(), Block.Properties.from(HAM_RAW_BLOCK), true));
        HAM_COOKED_STAIRS = register("ham_cooked_stairs", new ModStairsBlock(HAM_COOKED_BLOCK.getDefaultState(), Block.Properties.from(HAM_COOKED_BLOCK), true));

        CHEESE_WOOD_STAIRS = register("cheese_wood_stairs", new ModStairsBlock(CHEESE_PLANKS.getDefaultState(), Block.Properties.from(CHEESE_PLANKS), false));
        GRILLED_CHEESE_WOOD_STAIRS = register("grilled_cheese_wood_stairs", new ModStairsBlock(GRILLED_CHEESE_PLANKS.getDefaultState(), Block.Properties.from(GRILLED_CHEESE_PLANKS), false));
        HAM_RAW_WOOD_STAIRS = register("ham_raw_wood_stairs", new ModStairsBlock(HAM_RAW_PLANKS.getDefaultState(), Block.Properties.from(HAM_RAW_PLANKS), false));
        HAM_COOKED_WOOD_STAIRS = register("ham_cooked_wood_stairs", new ModStairsBlock(HAM_COOKED_PLANKS.getDefaultState(), Block.Properties.from(HAM_COOKED_PLANKS), false));

        /// Slabs ///
        CHEESE_SLAB = register("cheese_slab", new ModSlabBlock(Block.Properties.from(CHEESE_BLOCK), true));
        GRILLED_CHEESE_SLAB = register("grilled_cheese_slab", new ModSlabBlock(Block.Properties.from(GRILLED_CHEESE_BLOCK), true));
        HAM_RAW_SLAB = register("ham_raw_slab", new ModSlabBlock(Block.Properties.from(HAM_RAW_BLOCK), true));
        HAM_COOKED_SLAB = register("ham_cooked_slab", new ModSlabBlock(Block.Properties.from(HAM_COOKED_BLOCK), true));

        CHEESE_WOOD_SLAB = register("cheese_wood_slab", new ModSlabBlock(Block.Properties.from(CHEESE_PLANKS), false));
        GRILLED_CHEESE_WOOD_SLAB = register("grilled_cheese_wood_slab", new ModSlabBlock(Block.Properties.from(GRILLED_CHEESE_PLANKS), false));
        HAM_RAW_WOOD_SLAB = register("ham_raw_wood_slab", new ModSlabBlock(Block.Properties.from(HAM_RAW_PLANKS), false));
        HAM_COOKED_WOOD_SLAB = register("ham_cooked_wood_slab", new ModSlabBlock(Block.Properties.from(HAM_COOKED_PLANKS), false));

        /// Drawers ///
        CHEESE_DRAW = register("cheese_draw", new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
        GRILLED_CHEESE_DRAW = register("grilled_cheese_draw", new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
        HAM_RAW_DRAW = register("ham_raw_draw", new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
        HAM_COOKED_DRAW = register("ham_cooked_draw", new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));

        /// Other ///
        GRILL = register("grill", new GrillBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).lightValue(13)));

        PINEAPPLE = register("pineapple_plant", new PineappleBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP)), null);
        CHEESE_CAKE = register("cheese_cake", new ModCakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH)));

        CheeseMod.LOGGER.info("Blocks registered");
    }

    private static <B extends Block> B register(String name, B block) {
        BlockItem item = new BlockItem(block, new Item.Properties().group(CheeseMod.GROUP_ALL));
        return register(name, block, item);
    }

    private static <B extends Block> B register(String name, B block, @Nullable BlockItem item) {
        block.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.BLOCKS.register(block);
        if (item != null)
            ModItems.BLOCKS_TO_REGISTER.put(name, item);
        return block;
    }
}
