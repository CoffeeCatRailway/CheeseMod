package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.ModItemGroups;
import coffeecatrailway.coffeecheese.common.block.*;
import coffeecatrailway.coffeecheese.common.item.FoodBlockItem;
import coffeecatrailway.coffeecheese.common.world.feature.tree.CheeseTree;
import coffeecatrailway.coffeecheese.common.world.feature.tree.HamTree;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
@SuppressWarnings("unused")
public class ModBlocks {

    /// Ores ///
    private static NonNullUnaryOperator<Block.Properties> ORE_PROPS = prop -> prop.sound(SoundType.STONE).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE);

    public static final RegistryEntry<Block> CHEESE_METAL_ORE = REGISTRATE.object("cheese_metal_ore").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> CHEESE_METAL_ORE_NETHER = REGISTRATE.object("cheese_metal_ore_nether").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> CHEESE_METAL_ORE_END = REGISTRATE.object("cheese_metal_ore_end").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<Block> GRILLED_CHEESE_METAL_ORE = REGISTRATE.object("grilled_cheese_metal_ore").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> GRILLED_CHEESE_METAL_ORE_NETHER = REGISTRATE.object("grilled_cheese_metal_ore_nether").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> GRILLED_CHEESE_METAL_ORE_END = REGISTRATE.object("grilled_cheese_metal_ore_end").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<Block> HAM_RAW_METAL_ORE = REGISTRATE.object("ham_raw_metal_ore").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> HAM_RAW_METAL_ORE_NETHER = REGISTRATE.object("ham_raw_metal_ore_nether").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> HAM_RAW_METAL_ORE_END = REGISTRATE.object("ham_raw_metal_ore_end").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<Block> HAM_COOKED_METAL_ORE = REGISTRATE.object("ham_cooked_metal_ore").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> HAM_COOKED_METAL_ORE_NETHER = REGISTRATE.object("ham_cooked_metal_ore_nether").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> HAM_COOKED_METAL_ORE_END = REGISTRATE.object("ham_cooked_metal_ore_end").block(Block::new).properties(ORE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Food Metal ///
    private static NonNullUnaryOperator<Block.Properties> METAL_PROPS = prop -> prop.sound(SoundType.METAL).hardnessAndResistance(5.0f, 6.0f).harvestTool(ToolType.PICKAXE);

    public static final RegistryEntry<Block> CHEESE_METAL_BLOCK = REGISTRATE.object("cheese_metal_block").block(Block::new).properties(METAL_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> GRILLED_CHEESE_METAL_BLOCK = REGISTRATE.object("grilled_cheese_metal_block").block(Block::new).properties(METAL_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> HAM_RAW_METAL_BLOCK = REGISTRATE.object("ham_raw_metal_block").block(Block::new).properties(METAL_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> HAM_COOKED_METAL_BLOCK = REGISTRATE.object("ham_cooked_metal_block").block(Block::new).properties(METAL_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Nature ///
    private static NonNullUnaryOperator<Block.Properties> GRASS_PROPS = prop -> prop.sound(SoundType.PLANT).doesNotBlockMovement().zeroHardnessAndResistance().notSolid();

    public static final RegistryEntry<DoubleFoodPlantBlock> TALL_CHEESE_GRASS = REGISTRATE.object("tall_cheese_grass").block(DoubleFoodPlantBlock::new)
            .initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor()).properties(GRASS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<DoubleFoodPlantBlock> TALL_GRILLED_CHEESE_GRASS = REGISTRATE.object("tall_grilled_cheese_grass").block(DoubleFoodPlantBlock::new)
            .initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor()).properties(GRASS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<DoubleFoodPlantBlock> TALL_HAM_RAW_GRASS = REGISTRATE.object("tall_ham_raw_grass").block(DoubleFoodPlantBlock::new)
            .initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor()).properties(GRASS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<DoubleFoodPlantBlock> TALL_HAM_COOKED_GRASS = REGISTRATE.object("tall_ham_cooked_grass").block(DoubleFoodPlantBlock::new)
            .initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor()).properties(GRASS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<TallFoodGrassBlock> CHEESE_GRASS = REGISTRATE.object("cheese_grass").block(prop -> new TallFoodGrassBlock(prop, ModBlocks.TALL_CHEESE_GRASS))
            .initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor()).properties(GRASS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<TallFoodGrassBlock> GRILLED_CHEESE_GRASS = REGISTRATE.object("grilled_cheese_grass").block(prop -> new TallFoodGrassBlock(prop, ModBlocks.TALL_GRILLED_CHEESE_GRASS))
            .initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor()).properties(GRASS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<TallFoodGrassBlock> HAM_RAW_GRASS = REGISTRATE.object("ham_raw_grass").block(prop -> new TallFoodGrassBlock(prop, ModBlocks.TALL_HAM_RAW_GRASS))
            .initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor()).properties(GRASS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<TallFoodGrassBlock> HAM_COOKED_GRASS = REGISTRATE.object("ham_cooked_grass").block(prop -> new TallFoodGrassBlock(prop, ModBlocks.TALL_HAM_COOKED_GRASS))
            .initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor()).properties(GRASS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    private static NonNullUnaryOperator<Block.Properties> GRASS_BLOCK_PROPS = prop -> prop.sound(SoundType.PLANT).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT);

    public static final RegistryEntry<FoodGrassBlock> CHEESE_GRASS_BLOCK = REGISTRATE.object("cheese_grass_block")
            .block(prop -> new FoodGrassBlock(prop, ModBlocks.CHEESE_GRASS))
            .initialProperties(Material.ORGANIC, Material.ORGANIC.getColor()).properties(GRASS_BLOCK_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodGrassBlock> GRILLED_CHEESE_GRASS_BLOCK = REGISTRATE.object("grilled_cheese_grass_block")
            .block(prop -> new FoodGrassBlock(prop, ModBlocks.GRILLED_CHEESE_GRASS))
            .initialProperties(Material.ORGANIC, Material.ORGANIC.getColor()).properties(GRASS_BLOCK_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodGrassBlock> HAM_RAW_GRASS_BLOCK = REGISTRATE.object("ham_raw_grass_block")
            .block(prop -> new FoodGrassBlock(prop, ModBlocks.HAM_RAW_GRASS))
            .initialProperties(Material.ORGANIC, Material.ORGANIC.getColor()).properties(GRASS_BLOCK_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodGrassBlock> HAM_COOKED_GRASS_BLOCK = REGISTRATE.object("ham_cooked_grass_block")
            .block(prop -> new FoodGrassBlock(prop, ModBlocks.HAM_COOKED_GRASS))
            .initialProperties(Material.ORGANIC, Material.ORGANIC.getColor()).properties(GRASS_BLOCK_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    private static NonNullUnaryOperator<Block.Properties> SAPLING_PROPS = prop -> prop.doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT).notSolid();

    public static final RegistryEntry<FoodSaplingBlock> CHEESE_SAPLING = REGISTRATE.object("cheese_sapling").block(prop -> new FoodSaplingBlock(new CheeseTree(false), prop))
            .initialProperties(Material.PLANTS, Material.PLANTS.getColor()).properties(SAPLING_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodSaplingBlock> GRILLED_CHEESE_SAPLING = REGISTRATE.object("grilled_cheese_sapling").block(prop -> new FoodSaplingBlock(new CheeseTree(true), prop))
            .initialProperties(Material.PLANTS, Material.PLANTS.getColor()).properties(SAPLING_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodSaplingBlock> HAM_RAW_SAPLING = REGISTRATE.object("ham_raw_sapling").block(prop -> new FoodSaplingBlock(new HamTree(false), prop))
            .initialProperties(Material.PLANTS, Material.PLANTS.getColor()).properties(SAPLING_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodSaplingBlock> HAM_COOKED_SAPLING = REGISTRATE.object("ham_cooked_sapling").block(prop -> new FoodSaplingBlock(new HamTree(true), prop))
            .initialProperties(Material.PLANTS, Material.PLANTS.getColor()).properties(SAPLING_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    private static NonNullUnaryOperator<Block.Properties> PLANKS_PROPS = prop -> prop.hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD);

    public static final RegistryEntry<Block> CHEESE_PLANKS = REGISTRATE.object("cheese_planks").block(Block::new).initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(PLANKS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> GRILLED_CHEESE_PLANKS = REGISTRATE.object("grilled_cheese_planks").block(Block::new).initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(PLANKS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> HAM_RAW_PLANKS = REGISTRATE.object("ham_raw_planks").block(Block::new).initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(PLANKS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<Block> HAM_COOKED_PLANKS = REGISTRATE.object("ham_cooked_planks").block(Block::new).initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(PLANKS_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    private static NonNullUnaryOperator<Block.Properties> LEAVES_PROPS = prop -> prop.hardnessAndResistance(0.2f).tickRandomly().sound(SoundType.PLANT).notSolid();

    public static final RegistryEntry<LeavesBlock> CHEESE_LEAVES = REGISTRATE.object("cheese_leaves").block(LeavesBlock::new)
            .initialProperties(Material.LEAVES, Material.LEAVES.getColor()).properties(LEAVES_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LeavesBlock> GRILLED_CHEESE_LEAVES = REGISTRATE.object("grilled_cheese_leaves").block(LeavesBlock::new)
            .initialProperties(Material.LEAVES, Material.LEAVES.getColor()).properties(LEAVES_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LeavesBlock> HAM_RAW_LEAVES = REGISTRATE.object("ham_raw_leaves").block(LeavesBlock::new)
            .initialProperties(Material.LEAVES, Material.LEAVES.getColor()).properties(LEAVES_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LeavesBlock> HAM_COOKED_LEAVES = REGISTRATE.object("ham_cooked_leaves").block(LeavesBlock::new)
            .initialProperties(Material.LEAVES, Material.LEAVES.getColor()).properties(LEAVES_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    private static NonNullUnaryOperator<Block.Properties> LOG_PROPS = prop -> prop.hardnessAndResistance(2.0f).sound(SoundType.WOOD);

    public static final RegistryEntry<LogBlock> CHEESE_LOG = REGISTRATE.object("cheese_log").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> GRILLED_CHEESE_LOG = REGISTRATE.object("grilled_cheese_log").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> HAM_RAW_LOG = REGISTRATE.object("ham_raw_log").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> HAM_COOKED_LOG = REGISTRATE.object("ham_cooked_log").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<LogBlock> STRPPED_CHEESE_LOG = REGISTRATE.object("stripped_cheese_log").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> STRPPED_GRILLED_CHEESE_LOG = REGISTRATE.object("stripped_grilled_cheese_log").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> STRPPED_HAM_RAW_LOG = REGISTRATE.object("stripped_ham_raw_log").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> STRPPED_HAM_COOKED_LOG = REGISTRATE.object("stripped_ham_cooked_log").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<LogBlock> CHEESE_WOOD = REGISTRATE.object("cheese_wood").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> GRILLED_CHEESE_WOOD = REGISTRATE.object("grilled_cheese_wood").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> HAM_RAW_WOOD = REGISTRATE.object("ham_raw_wood").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> HAM_COOKED_WOOD = REGISTRATE.object("ham_cooked_wood").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<LogBlock> STRPPED_CHEESE_WOOD = REGISTRATE.object("stripped_cheese_wood").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> STRPPED_GRILLED_CHEESE_WOOD = REGISTRATE.object("stripped_grilled_cheese_wood").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> STRPPED_HAM_RAW_WOOD = REGISTRATE.object("stripped_ham_raw_wood").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<LogBlock> STRPPED_HAM_COOKED_WOOD = REGISTRATE.object("stripped_ham_cooked_wood").block(prop -> new LogBlock(MaterialColor.WOOD, prop))
            .initialProperties(Material.WOOD, MaterialColor.OBSIDIAN).properties(LOG_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Food Block ///
    public static final RegistryEntry<FoodBlock> CHEESE_BLOCK = REGISTRATE.object("cheese_block").block(prop -> new FoodBlock(true))
            .item((foodBlock, prop) -> new FoodBlockItem(foodBlock, ModFoods.CHEESE_SLICE, false)).properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodBlock> GRILLED_CHEESE_BLOCK = REGISTRATE.object("grilled_cheese_block").block(prop -> new FoodBlock(true))
            .item((foodBlock, prop) -> new FoodBlockItem(foodBlock, ModFoods.CHEESE_SLICE, true)).properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<FoodBlock> HAM_RAW_BLOCK = REGISTRATE.object("ham_raw_block").block(prop -> new FoodBlock(true))
            .item((foodBlock, prop) -> new FoodBlockItem(foodBlock, ModFoods.HAM, false)).properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodBlock> HAM_COOKED_BLOCK = REGISTRATE.object("ham_cooked_block").block(prop -> new FoodBlock(true))
            .item((foodBlock, prop) -> new FoodBlockItem(foodBlock, ModFoods.HAM_COOKED, false)).properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<FoodBlock> BACON_RAW_BLOCK = REGISTRATE.object("bacon_raw_block").block(prop -> new FoodBlock(false))
            .item((foodBlock, prop) -> new FoodBlockItem(foodBlock, ModFoods.BACON, false)).properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodBlock> BACON_COOKED_BLOCK = REGISTRATE.object("bacon_cooked_block").block(prop -> new FoodBlock(false))
            .item((foodBlock, prop) -> new FoodBlockItem(foodBlock, ModFoods.BACON_COOKED, false)).properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Stairs ///
    public static final RegistryEntry<ModStairsBlock> CHEESE_STAIRS = REGISTRATE.object("cheese_stairs")
            .block(prop -> new ModStairsBlock(() -> ModBlocks.CHEESE_BLOCK.get().getDefaultState(), prop, true)).initialProperties(ModBlocks.CHEESE_BLOCK)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModStairsBlock> GRILLED_CHEESE_STAIRS = REGISTRATE.object("grilled_cheese_stairs")
            .block(prop -> new ModStairsBlock(() -> ModBlocks.GRILLED_CHEESE_BLOCK.get().getDefaultState(), prop, true)).initialProperties(ModBlocks.GRILLED_CHEESE_BLOCK)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModStairsBlock> HAM_RAW_STAIRS = REGISTRATE.object("ham_raw_stairs")
            .block(prop -> new ModStairsBlock(() -> ModBlocks.HAM_RAW_BLOCK.get().getDefaultState(), prop, true)).initialProperties(ModBlocks.HAM_RAW_BLOCK)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModStairsBlock> HAM_COOKED_STAIRS = REGISTRATE.object("ham_cooked_stairs")
            .block(prop -> new ModStairsBlock(() -> ModBlocks.HAM_COOKED_BLOCK.get().getDefaultState(), prop, true)).initialProperties(ModBlocks.HAM_COOKED_BLOCK)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<ModStairsBlock> CHEESE_WOOD_STAIRS = REGISTRATE.object("cheese_wood_stairs")
            .block(prop -> new ModStairsBlock(() -> ModBlocks.CHEESE_PLANKS.get().getDefaultState(), prop, false)).initialProperties(ModBlocks.CHEESE_PLANKS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModStairsBlock> GRILLED_CHEESE_WOOD_STAIRS = REGISTRATE.object("grilled_cheese_wood_stairs")
            .block(prop -> new ModStairsBlock(() -> ModBlocks.GRILLED_CHEESE_PLANKS.get().getDefaultState(), prop, false)).initialProperties(ModBlocks.GRILLED_CHEESE_PLANKS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModStairsBlock> HAM_RAW_WOOD_STAIRS = REGISTRATE.object("ham_raw_wood_stairs")
            .block(prop -> new ModStairsBlock(() -> ModBlocks.HAM_RAW_PLANKS.get().getDefaultState(), prop, false)).initialProperties(ModBlocks.HAM_RAW_PLANKS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModStairsBlock> HAM_COOKED_WOOD_STAIRS = REGISTRATE.object("ham_cooked_wood_stairs")
            .block(prop -> new ModStairsBlock(() -> ModBlocks.HAM_COOKED_PLANKS.get().getDefaultState(), prop, false)).initialProperties(ModBlocks.HAM_COOKED_PLANKS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Slabs ///
    public static final RegistryEntry<ModSlabBlock> CHEESE_SLAB = REGISTRATE.object("cheese_slab")
            .block(prop -> new ModSlabBlock(prop, true)).initialProperties(ModBlocks.CHEESE_BLOCK)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModSlabBlock> GRILLED_CHEESE_SLAB = REGISTRATE.object("grilled_cheese_slab")
            .block(prop -> new ModSlabBlock(prop, true)).initialProperties(ModBlocks.GRILLED_CHEESE_BLOCK)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModSlabBlock> HAM_RAW_SLAB = REGISTRATE.object("ham_raw_slab")
            .block(prop -> new ModSlabBlock(prop, true)).initialProperties(ModBlocks.HAM_RAW_BLOCK)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModSlabBlock> HAM_COOKED_SLAB = REGISTRATE.object("ham_cooked_slab")
            .block(prop -> new ModSlabBlock(prop, true)).initialProperties(ModBlocks.HAM_COOKED_BLOCK)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<ModSlabBlock> CHEESE_WOOD_SLAB = REGISTRATE.object("cheese_wood_slab")
            .block(prop -> new ModSlabBlock(prop, false)).initialProperties(ModBlocks.CHEESE_PLANKS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModSlabBlock> GRILLED_CHEESE_WOOD_SLAB = REGISTRATE.object("grilled_cheese_wood_slab")
            .block(prop -> new ModSlabBlock(prop, false)).initialProperties(ModBlocks.GRILLED_CHEESE_PLANKS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModSlabBlock> HAM_RAW_WOOD_SLAB = REGISTRATE.object("ham_raw_wood_slab")
            .block(prop -> new ModSlabBlock(prop, false)).initialProperties(ModBlocks.HAM_RAW_PLANKS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<ModSlabBlock> HAM_COOKED_WOOD_SLAB = REGISTRATE.object("ham_cooked_wood_slab")
            .block(prop -> new ModSlabBlock(prop, false)).initialProperties(ModBlocks.HAM_COOKED_PLANKS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Fences ///
    private static NonNullUnaryOperator<Block.Properties> FENCE_PROPS = prop -> prop.hardnessAndResistance(2.0F, 3.0F).notSolid();

    public static final RegistryEntry<FenceBlock> CHEESE_FENCE = REGISTRATE.object("cheese_fence").block(FenceBlock::new)
            .initialProperties(ModBlocks.CHEESE_BLOCK).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FenceGateBlock> CHEESE_FENCE_GATE = REGISTRATE.object("cheese_fence_gate").block(FenceGateBlock::new)
            .initialProperties(ModBlocks.CHEESE_BLOCK).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<FenceBlock> GRILLED_CHEESE_FENCE = REGISTRATE.object("grilled_cheese_fence").block(FenceBlock::new)
            .initialProperties(ModBlocks.GRILLED_CHEESE_BLOCK).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FenceGateBlock> GRILLED_CHEESE_FENCE_GATE = REGISTRATE.object("grilled_cheese_fence_gate").block(FenceGateBlock::new)
            .initialProperties(ModBlocks.GRILLED_CHEESE_BLOCK).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<FenceBlock> HAM_RAW_FENCE = REGISTRATE.object("ham_raw_fence").block(FenceBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_BLOCK).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FenceGateBlock> HAM_RAW_FENCE_GATE = REGISTRATE.object("ham_raw_fence_gate").block(FenceGateBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_BLOCK).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<FenceBlock> HAM_COOKED_FENCE = REGISTRATE.object("ham_cooked_fence").block(FenceBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_BLOCK).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FenceGateBlock> HAM_COOKED_FENCE_GATE = REGISTRATE.object("ham_cooked_fence_gate").block(FenceGateBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_BLOCK).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<FenceBlock> CHEESE_WOOD_FENCE = REGISTRATE.object("cheese_wood_fence").block(FenceBlock::new)
            .initialProperties(ModBlocks.CHEESE_PLANKS).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FenceGateBlock> CHEESE_WOOD_FENCE_GATE = REGISTRATE.object("cheese_wood_fence_gate").block(FenceGateBlock::new)
            .initialProperties(ModBlocks.CHEESE_PLANKS).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<FenceBlock> GRILLED_CHEESE_WOOD_FENCE = REGISTRATE.object("grilled_cheese_wood_fence").block(FenceBlock::new)
            .initialProperties(ModBlocks.GRILLED_CHEESE_PLANKS).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FenceGateBlock> GRILLED_CHEESE_WOOD_FENCE_GATE = REGISTRATE.object("grilled_cheese_wood_fence_gate").block(FenceGateBlock::new)
            .initialProperties(ModBlocks.GRILLED_CHEESE_PLANKS).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<FenceBlock> HAM_RAW_WOOD_FENCE = REGISTRATE.object("ham_raw_wood_fence").block(FenceBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_PLANKS).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FenceGateBlock> HAM_RAW_WOOD_FENCE_GATE = REGISTRATE.object("ham_raw_wood_fence_gate").block(FenceGateBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_PLANKS).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<FenceBlock> HAM_COOKED_WOOD_FENCE = REGISTRATE.object("ham_cooked_wood_fence").block(FenceBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_PLANKS).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FenceGateBlock> HAM_COOKED_WOOD_FENCE_GATE = REGISTRATE.object("ham_cooked_wood_fence_gate").block(FenceGateBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_PLANKS).properties(FENCE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Doors ///
    private static NonNullUnaryOperator<Block.Properties> DOOR_PROPS = prop -> prop.hardnessAndResistance(3.0F).notSolid();

    public static final RegistryEntry<DoorBlock> CHEESE_DOOR = REGISTRATE.object("cheese_door").block(DoorBlock::new)
            .initialProperties(ModBlocks.CHEESE_BLOCK).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<DoorBlock> GRILLED_CHEESE_DOOR = REGISTRATE.object("grilled_cheese_door").block(DoorBlock::new)
            .initialProperties(ModBlocks.GRILLED_CHEESE_BLOCK).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<DoorBlock> HAM_RAW_DOOR = REGISTRATE.object("ham_raw_door").block(DoorBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_BLOCK).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<DoorBlock> HAM_COOKED_DOOR = REGISTRATE.object("ham_cooked_door").block(DoorBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_BLOCK).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<DoorBlock> CHEESE_WOOD_DOOR = REGISTRATE.object("cheese_wood_door").block(DoorBlock::new)
            .initialProperties(ModBlocks.CHEESE_PLANKS).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<DoorBlock> GRILLED_CHEESE_WOOD_DOOR = REGISTRATE.object("grilled_cheese_wood_door").block(DoorBlock::new)
            .initialProperties(ModBlocks.GRILLED_CHEESE_PLANKS).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<DoorBlock> HAM_RAW_WOOD_DOOR = REGISTRATE.object("ham_raw_wood_door").block(DoorBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_PLANKS).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<DoorBlock> HAM_COOKED_WOOD_DOOR = REGISTRATE.object("ham_cooked_wood_door").block(DoorBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_PLANKS).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Trap Doors ///
    public static final RegistryEntry<TrapDoorBlock> CHEESE_TRAP_DOOR = REGISTRATE.object("cheese_trap_door").block(TrapDoorBlock::new)
            .initialProperties(ModBlocks.CHEESE_BLOCK).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<TrapDoorBlock> GRILLED_CHEESE_TRAP_DOOR = REGISTRATE.object("grilled_cheese_trap_door").block(TrapDoorBlock::new)
            .initialProperties(ModBlocks.GRILLED_CHEESE_BLOCK).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<TrapDoorBlock> HAM_RAW_TRAP_DOOR = REGISTRATE.object("ham_raw_trap_door").block(TrapDoorBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_BLOCK).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<TrapDoorBlock> HAM_COOKED_TRAP_DOOR = REGISTRATE.object("ham_cooked_trap_door").block(TrapDoorBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_BLOCK).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<TrapDoorBlock> CHEESE_WOOD_TRAP_DOOR = REGISTRATE.object("cheese_wood_trap_door").block(TrapDoorBlock::new)
            .initialProperties(ModBlocks.CHEESE_PLANKS).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<TrapDoorBlock> GRILLED_CHEESE_WOOD_TRAP_DOOR = REGISTRATE.object("grilled_cheese_wood_trap_door").block(TrapDoorBlock::new)
            .initialProperties(ModBlocks.GRILLED_CHEESE_PLANKS).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<TrapDoorBlock> HAM_RAW_WOOD_TRAP_DOOR = REGISTRATE.object("ham_raw_wood_trap_door").block(TrapDoorBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_PLANKS).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<TrapDoorBlock> HAM_COOKED_WOOD_TRAP_DOOR = REGISTRATE.object("ham_cooked_wood_trap_door").block(TrapDoorBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_PLANKS).properties(DOOR_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Pressure Plate ///
    private static NonNullUnaryOperator<Block.Properties> PLATE_BUTTON_PROPS = prop -> prop.doesNotBlockMovement().hardnessAndResistance(0.5F).notSolid();

    public static final RegistryEntry<PressurePlateBlock> CHEESE_PRESSURE_PLATE = REGISTRATE.object("cheese_pressure_plate")
            .block(prop -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, prop)).initialProperties(ModBlocks.CHEESE_BLOCK).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<PressurePlateBlock> GRILLED_CHEESE_PRESSURE_PLATE = REGISTRATE.object("grilled_cheese_pressure_plate")
            .block(prop -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, prop)).initialProperties(ModBlocks.GRILLED_CHEESE_BLOCK).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<HamPressurePlateBlock> HAM_RAW_PRESSURE_PLATE = REGISTRATE.object("ham_raw_pressure_plate").block(HamPressurePlateBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_BLOCK).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<HamPressurePlateBlock> HAM_COOKED_PRESSURE_PLATE = REGISTRATE.object("ham_cooked_pressure_plate").block(HamPressurePlateBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_BLOCK).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<PressurePlateBlock> CHEESE_WOOD_PRESSURE_PLATE = REGISTRATE.object("cheese_wood_pressure_plate")
            .block(prop -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, prop)).initialProperties(ModBlocks.CHEESE_PLANKS).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<PressurePlateBlock> GRILLED_CHEESE_WOOD_PRESSURE_PLATE = REGISTRATE.object("grilled_cheese_wood_pressure_plate")
            .block(prop -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, prop)).initialProperties(ModBlocks.GRILLED_CHEESE_PLANKS).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<PressurePlateBlock> HAM_RAW_WOOD_PRESSURE_PLATE = REGISTRATE.object("ham_raw_wood_pressure_plate")
            .block(prop -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, prop)).initialProperties(ModBlocks.HAM_RAW_PLANKS).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<PressurePlateBlock> HAM_COOKED_WOOD_PRESSURE_PLATE = REGISTRATE.object("ham_cooked_wood_pressure_plate")
            .block(prop -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, prop)).initialProperties(ModBlocks.HAM_COOKED_PLANKS).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Buttons ///
    public static final RegistryEntry<WoodButtonBlock> CHEESE_BUTTON = REGISTRATE.object("cheese_button").block(WoodButtonBlock::new)
            .initialProperties(ModBlocks.CHEESE_BLOCK).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<WoodButtonBlock> GRILLED_CHEESE_BUTTON = REGISTRATE.object("grilled_cheese_button").block(WoodButtonBlock::new)
            .initialProperties(ModBlocks.GRILLED_CHEESE_BLOCK).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<WoodButtonBlock> HAM_RAW_BUTTON = REGISTRATE.object("ham_raw_button").block(WoodButtonBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_BLOCK).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<WoodButtonBlock> HAM_COOKED_BUTTON = REGISTRATE.object("ham_cooked_button").block(WoodButtonBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_BLOCK).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<WoodButtonBlock> CHEESE_WOOD_BUTTON = REGISTRATE.object("cheese_wood_button").block(WoodButtonBlock::new)
            .initialProperties(ModBlocks.CHEESE_PLANKS).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<WoodButtonBlock> GRILLED_CHEESE_WOOD_BUTTON = REGISTRATE.object("grilled_cheese_wood_button").block(WoodButtonBlock::new)
            .initialProperties(ModBlocks.GRILLED_CHEESE_PLANKS).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<WoodButtonBlock> HAM_RAW_WOOD_BUTTON = REGISTRATE.object("ham_raw_wood_button").block(WoodButtonBlock::new)
            .initialProperties(ModBlocks.HAM_RAW_PLANKS).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<WoodButtonBlock> HAM_COOKED_WOOD_BUTTON = REGISTRATE.object("ham_cooked_wood_button").block(WoodButtonBlock::new)
            .initialProperties(ModBlocks.HAM_COOKED_PLANKS).properties(PLATE_BUTTON_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Drawers ///
    private static NonNullUnaryOperator<Block.Properties> DRAWER_PROPS = prop -> prop.hardnessAndResistance(2.5f).sound(SoundType.WOOD).notSolid();

    public static final RegistryEntry<FoodDrawBlock> CHEESE_DRAW = REGISTRATE.object("cheese_draw").block(prop -> new FoodDrawBlock(prop, () -> ModStats.INTERACT_WITH_CHEESE_DRAW))
            .initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(DRAWER_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodDrawBlock> GRILLED_CHEESE_DRAW = REGISTRATE.object("grilled_cheese_draw").block(prop -> new FoodDrawBlock(prop, () -> ModStats.INTERACT_WITH_CHEESE_DRAW))
            .initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(DRAWER_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodDrawBlock> HAM_RAW_DRAW = REGISTRATE.object("ham_raw_draw").block(prop -> new FoodDrawBlock(prop, () -> ModStats.INTERACT_WITH_CHEESE_DRAW))
            .initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(DRAWER_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<FoodDrawBlock> HAM_COOKED_DRAW = REGISTRATE.object("ham_cooked_draw").block(prop -> new FoodDrawBlock(prop, () -> ModStats.INTERACT_WITH_CHEESE_DRAW))
            .initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(DRAWER_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    /// Other ///
    public static final RegistryEntry<GrillBlock> GRILL = REGISTRATE.object("grill").block(GrillBlock::new)
            .initialProperties(Material.IRON, Material.IRON.getColor()).properties(prop -> prop.hardnessAndResistance(3.5f).lightValue(13).notSolid())
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<MelterBlock> MELTER = REGISTRATE.object("melter").block(MelterBlock::new)
            .initialProperties(Material.IRON, Material.IRON.getColor()).properties(prop -> prop.hardnessAndResistance(3.5f).lightValue(13).notSolid())
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();
    public static final RegistryEntry<PizzaOvenBlock> PIZZA_OVEN = REGISTRATE.object("pizza_oven").block(PizzaOvenBlock::new)
            .initialProperties(Material.ROCK, MaterialColor.RED).properties(prop -> prop.hardnessAndResistance(2.0f, 6.0f).notSolid())
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_ALL)).build().register();

    public static final RegistryEntry<PineappleBlock> PINEAPPLE = REGISTRATE.block("pineapple_plant", PineappleBlock::new)
            .initialProperties(Material.PLANTS, Material.PLANTS.getColor()).properties(prop -> prop.doesNotBlockMovement().tickRandomly().sound(SoundType.CROP).notSolid()).register();

    private static NonNullUnaryOperator<Block.Properties> CAKE_PROPS = prop -> prop.hardnessAndResistance(0.5f).sound(SoundType.CLOTH).notSolid();

    public static final RegistryEntry<CakeBlock> CHEESE_CAKE = REGISTRATE.object("cheese_cake").block(CakeBlock::new)
            .initialProperties(Material.CAKE, Material.CAKE.getColor()).properties(CAKE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_FOODS)).build().register();
    public static final RegistryEntry<CakeBlock> GRILLED_CHEESE_CAKE = REGISTRATE.object("grilled_cheese_cake").block(CakeBlock::new)
            .initialProperties(Material.CAKE, Material.CAKE.getColor()).properties(CAKE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_FOODS)).build().register();
    public static final RegistryEntry<CakeBlock> HAM_RAW_CAKE = REGISTRATE.object("ham_raw_cake").block(CakeBlock::new)
            .initialProperties(Material.CAKE, Material.CAKE.getColor()).properties(CAKE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_FOODS)).build().register();
    public static final RegistryEntry<CakeBlock> HAM_COOKED_CAKE = REGISTRATE.object("ham_cooked_cake").block(CakeBlock::new)
            .initialProperties(Material.CAKE, Material.CAKE.getColor()).properties(CAKE_PROPS)
            .item().properties(prop -> prop.group(ModItemGroups.GROUP_FOODS)).build().register();

    public static final RegistryEntry<FoodWorldPortalBlock> FOOD_PORTAL = REGISTRATE.block("food_portal", FoodWorldPortalBlock::new)
            .initialProperties(Material.PORTAL, Material.PORTAL.getColor())
            .properties(prop -> prop.doesNotBlockMovement().harvestTool(ToolType.PICKAXE).hardnessAndResistance(-1.0F, 3000000.0F).lightValue(10).sound(SoundType.GLASS).notSolid())
            .register();

    public static void load() {
    }
}
