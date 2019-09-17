package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.ModItemGroups;
import coffeecatrailway.cheesemod.common.block.*;
import coffeecatrailway.cheesemod.common.item.FoodBlockItem;
import coffeecatrailway.cheesemod.common.world.feature.tree.CheeseTree;
import coffeecatrailway.cheesemod.common.world.feature.tree.HamTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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

    /// Fences ///
    public static Block CHEESE_FENCE;
    public static Block CHEESE_FENCE_GATE;

    public static Block GRILLED_CHEESE_FENCE;
    public static Block GRILLED_CHEESE_FENCE_GATE;

    public static Block HAM_RAW_FENCE;
    public static Block HAM_RAW_FENCE_GATE;

    public static Block HAM_COOKED_FENCE;
    public static Block HAM_COOKED_FENCE_GATE;

    public static Block CHEESE_WOOD_FENCE;
    public static Block CHEESE_WOOD_FENCE_GATE;

    public static Block GRILLED_CHEESE_WOOD_FENCE;
    public static Block GRILLED_CHEESE_WOOD_FENCE_GATE;

    public static Block HAM_RAW_WOOD_FENCE;
    public static Block HAM_RAW_WOOD_FENCE_GATE;

    public static Block HAM_COOKED_WOOD_FENCE;
    public static Block HAM_COOKED_WOOD_FENCE_GATE;

    /// Pressure Plates ///
    public static Block CHEESE_PRESSURE_PLATE;
    public static Block GRILLED_CHEESE_PRESSURE_PLATE;
    public static Block HAM_RAW_PRESSURE_PLATE;
    public static Block HAM_COOKED_PRESSURE_PLATE;

    public static Block CHEESE_WOOD_PRESSURE_PLATE;
    public static Block GRILLED_CHEESE_WOOD_PRESSURE_PLATE;
    public static Block HAM_RAW_WOOD_PRESSURE_PLATE;
    public static Block HAM_COOKED_WOOD_PRESSURE_PLATE;

    /// Drawers ///
    public static Block CHEESE_DRAW;
    public static Block GRILLED_CHEESE_DRAW;
    public static Block HAM_RAW_DRAW;
    public static Block HAM_COOKED_DRAW;

    /// Fluids ///
    public static Block OIL;
    public static Block MELTED_CHEESE;

    /// Other ///
    public static Block GRILL;
    public static Block MELTER;

    public static Block PINEAPPLE;

    public static Block CHEESE_CAKE;
    public static Block GRILLED_CHEESE_CAKE;
    public static Block HAM_RAW_CAKE;
    public static Block HAM_COOKED_CAKE;

    public static void registerAll(RegistryEvent.Register<Block> event) {
        if (!event.getName().equals(ForgeRegistries.BLOCKS.getRegistryName())) return;

        /// Ores ///
        CHEESE_METAL_ORE = register("cheese_metal_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
        CHEESE_METAL_ORE_NETHER = register("cheese_metal_ore_nether", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
        CHEESE_METAL_ORE_END = register("cheese_metal_ore_end", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));

        GRILLED_CHEESE_METAL_ORE = register("grilled_cheese_metal_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
        GRILLED_CHEESE_METAL_ORE_NETHER = register("grilled_cheese_metal_ore_nether", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
        GRILLED_CHEESE_METAL_ORE_END = register("grilled_cheese_metal_ore_end", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));

        HAM_RAW_METAL_ORE = register("ham_raw_metal_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
        HAM_RAW_METAL_ORE_NETHER = register("ham_raw_metal_ore_nether", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
        HAM_RAW_METAL_ORE_END = register("ham_raw_metal_ore_end", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));

        HAM_COOKED_METAL_ORE = register("ham_cooked_metal_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
        HAM_COOKED_METAL_ORE_NETHER = register("ham_cooked_metal_ore_nether", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
        HAM_COOKED_METAL_ORE_END = register("ham_cooked_metal_ore_end", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));

        /// Food Metal ///
        CHEESE_METAL_BLOCK = register("cheese_metal_block", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL)));
        GRILLED_CHEESE_METAL_BLOCK = register("grilled_cheese_metal_block", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL)));
        HAM_RAW_METAL_BLOCK = register("ham_raw_metal_block", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL)));
        HAM_COOKED_METAL_BLOCK = register("ham_cooked_metal_block", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL)));

        /// Food Wood/Tree ///
        CHEESE_PLANKS = register("cheese_planks", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD)));
        GRILLED_CHEESE_PLANKS = register("grilled_cheese_planks", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD)));
        HAM_RAW_PLANKS = register("ham_raw_planks", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD)));
        HAM_COOKED_PLANKS = register("ham_cooked_planks", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD)));

        CHEESE_LEAVES = register("cheese_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f).tickRandomly().sound(SoundType.PLANT)));
        GRILLED_CHEESE_LEAVES = register("grilled_cheese_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f).tickRandomly().sound(SoundType.PLANT)));
        HAM_RAW_LEAVES = register("ham_raw_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f).tickRandomly().sound(SoundType.PLANT)));
        HAM_COOKED_LEAVES = register("ham_cooked_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f).tickRandomly().sound(SoundType.PLANT)));

        CHEESE_LOG = register("cheese_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        GRILLED_CHEESE_LOG = register("grilled_cheese_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        HAM_RAW_LOG = register("ham_raw_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        HAM_COOKED_LOG = register("ham_cooked_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));

        STRPPED_CHEESE_LOG = register("stripped_cheese_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        STRPPED_GRILLED_CHEESE_LOG = register("stripped_grilled_cheese_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        STRPPED_HAM_RAW_LOG = register("stripped_ham_raw_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        STRPPED_HAM_COOKED_LOG = register("stripped_ham_cooked_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));

        CHEESE_WOOD = register("cheese_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        GRILLED_CHEESE_WOOD = register("grilled_cheese_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        HAM_RAW_WOOD = register("ham_raw_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        HAM_COOKED_WOOD = register("ham_cooked_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));

        STRPPED_CHEESE_WOOD = register("stripped_cheese_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        STRPPED_GRILLED_CHEESE_WOOD = register("stripped_grilled_cheese_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        STRPPED_HAM_RAW_WOOD = register("stripped_ham_raw_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
        STRPPED_HAM_COOKED_WOOD = register("stripped_ham_cooked_wood", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));

        CHEESE_SAPLING = register("cheese_sapling", new SaplingBlock(new CheeseTree(false), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
        GRILLED_CHEESE_SAPLING = register("grilled_cheese_sapling", new SaplingBlock(new CheeseTree(true), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
        HAM_RAW_SAPLING = register("ham_raw_sapling", new SaplingBlock(new HamTree(false), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
        HAM_COOKED_SAPLING = register("ham_cooked_sapling", new SaplingBlock(new HamTree(true), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));

        /// Food Block ///
        CHEESE_BLOCK = new FoodBlock();
        register("cheese_block", CHEESE_BLOCK, new FoodBlockItem(CHEESE_BLOCK, ModFoods.CHEESE_SLICE, false));

        GRILLED_CHEESE_BLOCK = new FoodBlock();
        register("grilled_cheese_block", GRILLED_CHEESE_BLOCK, new FoodBlockItem(GRILLED_CHEESE_BLOCK, ModFoods.CHEESE_SLICE, true));

        HAM_RAW_BLOCK = new FoodBlock();
        register("ham_raw_block", HAM_RAW_BLOCK, new FoodBlockItem(HAM_RAW_BLOCK, ModFoods.HAM_COOKED, false));

        HAM_COOKED_BLOCK = new FoodBlock();
        register("ham_cooked_block", HAM_COOKED_BLOCK, new FoodBlockItem(HAM_COOKED_BLOCK, ModFoods.HAM_COOKED, true));

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

        /// Fences ///
        CHEESE_FENCE = register("cheese_fence", new FenceBlock(Block.Properties.from(CHEESE_BLOCK).hardnessAndResistance(2.0F, 3.0F)));
        CHEESE_FENCE_GATE = register("cheese_fence_gate", new FenceGateBlock(Block.Properties.from(CHEESE_BLOCK).hardnessAndResistance(2.0F, 3.0F)));

        GRILLED_CHEESE_FENCE = register("grilled_cheese_fence", new FenceBlock(Block.Properties.from(GRILLED_CHEESE_BLOCK).hardnessAndResistance(2.0F, 3.0F)));
        GRILLED_CHEESE_FENCE_GATE = register("grilled_cheese_fence_gate", new FenceGateBlock(Block.Properties.from(GRILLED_CHEESE_BLOCK).hardnessAndResistance(2.0F, 3.0F)));

        HAM_RAW_FENCE = register("ham_raw_fence", new FenceBlock(Block.Properties.from(HAM_RAW_BLOCK).hardnessAndResistance(2.0F, 3.0F)));
        HAM_RAW_FENCE_GATE = register("ham_raw_fence_gate", new FenceGateBlock(Block.Properties.from(HAM_RAW_BLOCK).hardnessAndResistance(2.0F, 3.0F)));

        HAM_COOKED_FENCE = register("ham_cooked_fence", new FenceBlock(Block.Properties.from(HAM_COOKED_BLOCK).hardnessAndResistance(2.0F, 3.0F)));
        HAM_COOKED_FENCE_GATE = register("ham_cooked_fence_gate", new FenceGateBlock(Block.Properties.from(HAM_COOKED_BLOCK).hardnessAndResistance(2.0F, 3.0F)));

        CHEESE_WOOD_FENCE = register("cheese_wood_fence", new FenceBlock(Block.Properties.from(CHEESE_PLANKS).hardnessAndResistance(2.0F, 3.0F)));
        CHEESE_WOOD_FENCE_GATE = register("cheese_wood_fence_gate", new FenceGateBlock(Block.Properties.from(CHEESE_PLANKS).hardnessAndResistance(2.0F, 3.0F)));

        GRILLED_CHEESE_WOOD_FENCE = register("grilled_cheese_wood_fence", new FenceBlock(Block.Properties.from(GRILLED_CHEESE_PLANKS).hardnessAndResistance(2.0F, 3.0F)));
        GRILLED_CHEESE_WOOD_FENCE_GATE = register("grilled_cheese_wood_fence_gate", new FenceGateBlock(Block.Properties.from(GRILLED_CHEESE_PLANKS).hardnessAndResistance(2.0F, 3.0F)));

        HAM_RAW_WOOD_FENCE = register("ham_raw_wood_fence", new FenceBlock(Block.Properties.from(HAM_RAW_PLANKS).hardnessAndResistance(2.0F, 3.0F)));
        HAM_RAW_WOOD_FENCE_GATE = register("ham_raw_wood_fence_gate", new FenceGateBlock(Block.Properties.from(HAM_RAW_PLANKS).hardnessAndResistance(2.0F, 3.0F)));

        HAM_COOKED_WOOD_FENCE = register("ham_cooked_wood_fence", new FenceBlock(Block.Properties.from(HAM_COOKED_PLANKS).hardnessAndResistance(2.0F, 3.0F)));
        HAM_COOKED_WOOD_FENCE_GATE = register("ham_cooked_wood_fence_gate", new FenceGateBlock(Block.Properties.from(HAM_COOKED_PLANKS).hardnessAndResistance(2.0F, 3.0F)));

        /// Pressure Plate ///
        CHEESE_PRESSURE_PLATE = register("cheese_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(CHEESE_BLOCK).doesNotBlockMovement().hardnessAndResistance(0.5F)));
        GRILLED_CHEESE_PRESSURE_PLATE = register("grilled_cheese_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(GRILLED_CHEESE_BLOCK).doesNotBlockMovement().hardnessAndResistance(0.5F)));
        HAM_RAW_PRESSURE_PLATE = register("ham_raw_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HAM_RAW_BLOCK).doesNotBlockMovement().hardnessAndResistance(0.5F)));
        HAM_COOKED_PRESSURE_PLATE = register("ham_cooked_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HAM_COOKED_BLOCK).doesNotBlockMovement().hardnessAndResistance(0.5F)));

        CHEESE_WOOD_PRESSURE_PLATE = register("cheese_wood_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(CHEESE_PLANKS).doesNotBlockMovement().hardnessAndResistance(0.5F)));
        GRILLED_CHEESE_WOOD_PRESSURE_PLATE = register("grilled_cheese_wood_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(GRILLED_CHEESE_PLANKS).doesNotBlockMovement().hardnessAndResistance(0.5F)));
        HAM_RAW_WOOD_PRESSURE_PLATE = register("ham_raw_wood_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HAM_RAW_PLANKS).doesNotBlockMovement().hardnessAndResistance(0.5F)));
        HAM_COOKED_WOOD_PRESSURE_PLATE = register("ham_cooked_wood_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HAM_COOKED_PLANKS).doesNotBlockMovement().hardnessAndResistance(0.5F)));

        /// Drawers ///
        CHEESE_DRAW = register("cheese_draw", new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD), () -> ModStats.INTERACT_WITH_CHEESE_DRAW));
        GRILLED_CHEESE_DRAW = register("grilled_cheese_draw", new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD), () -> ModStats.INTERACT_WITH_CHEESE_DRAW));
        HAM_RAW_DRAW = register("ham_raw_draw", new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD), () -> ModStats.INTERACT_WITH_HAM_DRAW));
        HAM_COOKED_DRAW = register("ham_cooked_draw", new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD), () -> ModStats.INTERACT_WITH_HAM_DRAW));

        /// Fluids ///
        OIL = register("oil_block", new FlowingFluidBlock(ModFluids.OIL_SOURCE, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0f).noDrops()), (ItemGroup) null);
        MELTED_CHEESE = register("melted_cheese", new FlowingFluidBlock(ModFluids.MELTED_CHEESE_SOURCE, Block.Properties.create(Material.LAVA).doesNotBlockMovement().hardnessAndResistance(100.0f).noDrops()), (ItemGroup) null);

        /// Other ///
        GRILL = register("grill", new GrillBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5f).lightValue(13)));
        MELTER = register("melter", new MelterBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5f).lightValue(13)));

        PINEAPPLE = register("pineapple_plant", new PineappleBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP)), (BlockItem) null);

        CHEESE_CAKE = register("cheese_cake", new CakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5f).sound(SoundType.CLOTH)), ModItemGroups.GROUP_FOODS);
        GRILLED_CHEESE_CAKE = register("grilled_cheese_cake", new CakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5f).sound(SoundType.CLOTH)), ModItemGroups.GROUP_FOODS);
        HAM_RAW_CAKE = register("ham_raw_cake", new CakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5f).sound(SoundType.CLOTH)), ModItemGroups.GROUP_FOODS);
        HAM_COOKED_CAKE = register("ham_cooked_cake", new CakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5f).sound(SoundType.CLOTH)), ModItemGroups.GROUP_FOODS);

        CheeseMod.LOGGER.info("Blocks registered");
    }

    public static <B extends Block> B register(String name, B block) {
        return register(name, block, ModItemGroups.GROUP_ALL);
    }

    public static <B extends Block> B register(String name, B block, ItemGroup group) {
        BlockItem item = new BlockItem(block, new Item.Properties().group(group));
        return register(name, block, item);
    }

    public static <B extends Block> B register(String name, B block, @Nullable BlockItem item) {
        block.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.BLOCKS.register(block);
        if (item != null) {
            ModItems.BLOCKS_TO_REGISTER.put(name, item);
            if (item.getGroup() == ModItemGroups.GROUP_FOODS) ModItems.FOODS.add(item);
        }
        return block;
    }
}
