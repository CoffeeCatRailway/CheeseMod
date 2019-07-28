package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.blocks.FoodBlock;
import coffeecatrailway.cheesemod.blocks.ModCakeBlock;
import coffeecatrailway.cheesemod.blocks.ModSaplingBlock;
import coffeecatrailway.cheesemod.blocks.PineappleBlock;
import coffeecatrailway.cheesemod.core.special.ModFoods;
import coffeecatrailway.cheesemod.items.FoodBlockItem;
import coffeecatrailway.cheesemod.world.feature.tree.CheeseTree;
import coffeecatrailway.cheesemod.world.feature.tree.HamTree;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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

    public static Block CHEESE_SAPLING;
    public static Block GRILLED_CHEESE_SAPLING;
    public static Block HAM_RAW_SAPLING;
    public static Block HAM_COOKED_SAPLING;

    /// Food Block ///
    public static Block CHEESE_BLOCK;
    public static Block GRILLED_CHEESE_BLOCK;
    public static Block HAM_RAW_BLOCK;
    public static Block HAM_COOKED_BLOCK;

    public static Block CHEESE_GRASS;
    public static Block GRILLED_CHEESE_GRASS;
    public static Block HAM_RAW_GRASS;
    public static Block HAM_COOKED_GRASS;

    /// Food Makers ///
    public static Block GRILL;

    public static Block CRACKER_MAKER;

    /// Drawers ///
    public static Block CHEESE_DRAW;
    public static Block GRILLED_CHEESE_DRAW;
    public static Block HAM_RAW_DRAW;
    public static Block HAM_COOKED_DRAW;

    /// Stairs ///
    public static Block CHEESE_STAIRS;
    public static Block GRILLED_CHEESE_STAIRS;
    public static Block HAM_RAW_STAIRS;
    public static Block HAM_COOKED_STAIRS;

    /// Slabs ///
    public static Block CHEESE_SLAB;
    public static Block GRILLED_CHEESE_SLAB;
    public static Block HAM_RAW_SLAB;
    public static Block HAM_COOKED_SLAB;

    /// Other ///
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

        /// Food Makers ///

        /// Drawers ///

        /// Stairs ///

        /// Slabs ///

        /// Other ///
        PINEAPPLE = register("pineapple_plant", new PineappleBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP)), null);
        CHEESE_CAKE = register("cheese_cake", new ModCakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH)));

        CheeseMod.LOGGER.info("Blocks registered");
    }

    private static <T extends Block> T register(String name, T block) {
        BlockItem item = new BlockItem(block, new Item.Properties().group(CheeseMod.GROUP_ITEMS));
        return register(name, block, item);
    }

    private static <T extends Block> T register(String name, T block, @Nullable BlockItem item) {
        ResourceLocation id = new ResourceLocation(CheeseMod.MOD_ID, name);
        block.setRegistryName(id);
        ForgeRegistries.BLOCKS.register(block);
        if (item != null) {
            ModItems.BLOCKS_TO_REGISTER.put(name, item);
        }
        return block;
    }
}
