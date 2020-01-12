package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.ModItemGroups;
import coffeecatrailway.coffeecheese.common.block.*;
import coffeecatrailway.coffeecheese.common.item.FoodBlockItem;
import coffeecatrailway.coffeecheese.common.world.feature.tree.CheeseTree;
import coffeecatrailway.coffeecheese.common.world.feature.tree.HamTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
@SuppressWarnings("unused")
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, CheeseMod.MOD_ID);

    /// Ores ///
    public static final RegistryObject<Block> CHEESE_METAL_ORE = registerBlockGeneral("cheese_metal_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> CHEESE_METAL_ORE_NETHER = registerBlockGeneral("cheese_metal_ore_nether", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> CHEESE_METAL_ORE_END = registerBlockGeneral("cheese_metal_ore_end", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> GRILLED_CHEESE_METAL_ORE = registerBlockGeneral("grilled_cheese_metal_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> GRILLED_CHEESE_METAL_ORE_NETHER = registerBlockGeneral("grilled_cheese_metal_ore_nether", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> GRILLED_CHEESE_METAL_ORE_END = registerBlockGeneral("grilled_cheese_metal_ore_end", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> HAM_RAW_METAL_ORE = registerBlockGeneral("ham_raw_metal_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> HAM_RAW_METAL_ORE_NETHER = registerBlockGeneral("ham_raw_metal_ore_nether", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> HAM_RAW_METAL_ORE_END = registerBlockGeneral("ham_raw_metal_ore_end", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> HAM_COOKED_METAL_ORE = registerBlockGeneral("ham_cooked_metal_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> HAM_COOKED_METAL_ORE_NETHER = registerBlockGeneral("ham_cooked_metal_ore_nether", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> HAM_COOKED_METAL_ORE_END = registerBlockGeneral("ham_cooked_metal_ore_end", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));

    /// Food Metal ///
    public static final RegistryObject<Block> CHEESE_METAL_BLOCK = registerBlockGeneral("cheese_metal_block", () -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> GRILLED_CHEESE_METAL_BLOCK = registerBlockGeneral("grilled_cheese_metal_block", () -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> HAM_RAW_METAL_BLOCK = registerBlockGeneral("ham_raw_metal_block", () -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> HAM_COOKED_METAL_BLOCK = registerBlockGeneral("ham_cooked_metal_block", () -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 6.0f).sound(SoundType.METAL)));

    /// Food Wood/Tree ///
    public static final RegistryObject<Block> CHEESE_PLANKS = registerBlockGeneral("cheese_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GRILLED_CHEESE_PLANKS = registerBlockGeneral("grilled_cheese_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> HAM_RAW_PLANKS = registerBlockGeneral("ham_raw_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> HAM_COOKED_PLANKS = registerBlockGeneral("ham_cooked_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CHEESE_LEAVES = registerBlockGeneral("cheese_leaves", () -> new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f).tickRandomly().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> GRILLED_CHEESE_LEAVES = registerBlockGeneral("grilled_cheese_leaves", () -> new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f).tickRandomly().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> HAM_RAW_LEAVES = registerBlockGeneral("ham_raw_leaves", () -> new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f).tickRandomly().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> HAM_COOKED_LEAVES = registerBlockGeneral("ham_cooked_leaves", () -> new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f).tickRandomly().sound(SoundType.PLANT)));

    public static final RegistryObject<Block> CHEESE_LOG = registerBlockGeneral("cheese_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GRILLED_CHEESE_LOG = registerBlockGeneral("grilled_cheese_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> HAM_RAW_LOG = registerBlockGeneral("ham_raw_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> HAM_COOKED_LOG = registerBlockGeneral("ham_cooked_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> STRPPED_CHEESE_LOG = registerBlockGeneral("stripped_cheese_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRPPED_GRILLED_CHEESE_LOG = registerBlockGeneral("stripped_grilled_cheese_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRPPED_HAM_RAW_LOG = registerBlockGeneral("stripped_ham_raw_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRPPED_HAM_COOKED_LOG = registerBlockGeneral("stripped_ham_cooked_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CHEESE_WOOD = registerBlockGeneral("cheese_wood", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GRILLED_CHEESE_WOOD = registerBlockGeneral("grilled_cheese_wood", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> HAM_RAW_WOOD = registerBlockGeneral("ham_raw_wood", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> HAM_COOKED_WOOD = registerBlockGeneral("ham_cooked_wood", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> STRPPED_CHEESE_WOOD = registerBlockGeneral("stripped_cheese_wood", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRPPED_GRILLED_CHEESE_WOOD = registerBlockGeneral("stripped_grilled_cheese_wood", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRPPED_HAM_RAW_WOOD = registerBlockGeneral("stripped_ham_raw_wood", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRPPED_HAM_COOKED_WOOD = registerBlockGeneral("stripped_ham_cooked_wood", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CHEESE_SAPLING = registerBlockGeneral("cheese_sapling", () -> new SaplingBlock(new CheeseTree(false), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> GRILLED_CHEESE_SAPLING = registerBlockGeneral("grilled_cheese_sapling", () -> new SaplingBlock(new CheeseTree(true), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> HAM_RAW_SAPLING = registerBlockGeneral("ham_raw_sapling", () -> new SaplingBlock(new HamTree(false), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> HAM_COOKED_SAPLING = registerBlockGeneral("ham_cooked_sapling", () -> new SaplingBlock(new HamTree(true), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));

    /// Food Block ///
    public static final RegistryObject<Block> CHEESE_BLOCK = BLOCKS.register("cheese_block", () -> new FoodBlock(true));
    public static final RegistryObject<Block> GRILLED_CHEESE_BLOCK = BLOCKS.register("grilled_cheese_block", () -> new FoodBlock(true));

    public static final RegistryObject<Block> HAM_RAW_BLOCK = BLOCKS.register("ham_raw_block", () -> new FoodBlock(true));
    public static final RegistryObject<Block> HAM_COOKED_BLOCK = BLOCKS.register("ham_cooked_block", () -> new FoodBlock(true));

    public static final RegistryObject<Block> BACON_RAW_BLOCK = BLOCKS.register("bacon_raw_block", () -> new FoodBlock(false));
    public static final RegistryObject<Block> BACON_COOKED_BLOCK = BLOCKS.register("bacon_cooked_block", () -> new FoodBlock(false));

    static {
        ModItems.ITEMS.register("cheese_block", () -> new FoodBlockItem(CHEESE_BLOCK.get(), ModFoods.CHEESE_SLICE, false));
        ModItems.ITEMS.register("grilled_cheese_block", () -> new FoodBlockItem(GRILLED_CHEESE_BLOCK.get(), ModFoods.CHEESE_SLICE, false));

        ModItems.ITEMS.register("ham_raw_block", () -> new FoodBlockItem(HAM_RAW_BLOCK.get(), ModFoods.HAM, false));
        ModItems.ITEMS.register("ham_cooked_block", () -> new FoodBlockItem(HAM_COOKED_BLOCK.get(), ModFoods.HAM_COOKED, false));

        ModItems.ITEMS.register("bacon_raw_block", () -> new FoodBlockItem(BACON_RAW_BLOCK.get(), ModFoods.BACON, false));
        ModItems.ITEMS.register("bacon_cooked_block", () -> new FoodBlockItem(BACON_COOKED_BLOCK.get(), ModFoods.BACON_COOKED, false));
    }

    /// Stairs ///
    public static final RegistryObject<Block> CHEESE_STAIRS = registerBlockGeneral("cheese_stairs", () -> new ModStairsBlock(CHEESE_BLOCK.get().getDefaultState(), Block.Properties.from(CHEESE_BLOCK.get()), true));
    public static final RegistryObject<Block> GRILLED_CHEESE_STAIRS = registerBlockGeneral("grilled_cheese_stairs", () -> new ModStairsBlock(GRILLED_CHEESE_BLOCK.get().getDefaultState(), Block.Properties.from(GRILLED_CHEESE_BLOCK.get()), true));
    public static final RegistryObject<Block> HAM_RAW_STAIRS = registerBlockGeneral("ham_raw_stairs", () -> new ModStairsBlock(HAM_RAW_BLOCK.get().getDefaultState(), Block.Properties.from(HAM_RAW_BLOCK.get()), true));
    public static final RegistryObject<Block> HAM_COOKED_STAIRS = registerBlockGeneral("ham_cooked_stairs", () -> new ModStairsBlock(HAM_COOKED_BLOCK.get().getDefaultState(), Block.Properties.from(HAM_COOKED_BLOCK.get()), true));

    public static final RegistryObject<Block> CHEESE_WOOD_STAIRS = registerBlockGeneral("cheese_wood_stairs", () -> new ModStairsBlock(CHEESE_PLANKS.get().getDefaultState(), Block.Properties.from(CHEESE_PLANKS.get()), false));
    public static final RegistryObject<Block> GRILLED_CHEESE_WOOD_STAIRS = registerBlockGeneral("grilled_cheese_wood_stairs", () -> new ModStairsBlock(GRILLED_CHEESE_PLANKS.get().getDefaultState(), Block.Properties.from(GRILLED_CHEESE_PLANKS.get()), false));
    public static final RegistryObject<Block> HAM_RAW_WOOD_STAIRS = registerBlockGeneral("ham_raw_wood_stairs", () -> new ModStairsBlock(HAM_RAW_PLANKS.get().getDefaultState(), Block.Properties.from(HAM_RAW_PLANKS.get()), false));
    public static final RegistryObject<Block> HAM_COOKED_WOOD_STAIRS = registerBlockGeneral("ham_cooked_wood_stairs", () -> new ModStairsBlock(HAM_COOKED_PLANKS.get().getDefaultState(), Block.Properties.from(HAM_COOKED_PLANKS.get()), false));

    /// Slabs ///
    public static final RegistryObject<Block> CHEESE_SLAB = registerBlockGeneral("cheese_slab", () -> new ModSlabBlock(Block.Properties.from(CHEESE_BLOCK.get()), true));
    public static final RegistryObject<Block> GRILLED_CHEESE_SLAB = registerBlockGeneral("grilled_cheese_slab", () -> new ModSlabBlock(Block.Properties.from(GRILLED_CHEESE_BLOCK.get()), true));
    public static final RegistryObject<Block> HAM_RAW_SLAB = registerBlockGeneral("ham_raw_slab", () -> new ModSlabBlock(Block.Properties.from(HAM_RAW_BLOCK.get()), true));
    public static final RegistryObject<Block> HAM_COOKED_SLAB = registerBlockGeneral("ham_cooked_slab", () -> new ModSlabBlock(Block.Properties.from(HAM_COOKED_BLOCK.get()), true));

    public static final RegistryObject<Block> CHEESE_WOOD_SLAB = registerBlockGeneral("cheese_wood_slab", () -> new ModSlabBlock(Block.Properties.from(CHEESE_PLANKS.get()), false));
    public static final RegistryObject<Block> GRILLED_CHEESE_WOOD_SLAB = registerBlockGeneral("grilled_cheese_wood_slab", () -> new ModSlabBlock(Block.Properties.from(GRILLED_CHEESE_PLANKS.get()), false));
    public static final RegistryObject<Block> HAM_RAW_WOOD_SLAB = registerBlockGeneral("ham_raw_wood_slab", () -> new ModSlabBlock(Block.Properties.from(HAM_RAW_PLANKS.get()), false));
    public static final RegistryObject<Block> HAM_COOKED_WOOD_SLAB = registerBlockGeneral("ham_cooked_wood_slab", () -> new ModSlabBlock(Block.Properties.from(HAM_COOKED_PLANKS.get()), false));

    /// Fences ///
    public static final RegistryObject<Block> CHEESE_FENCE = registerBlockGeneral("cheese_fence", () -> new FenceBlock(Block.Properties.from(CHEESE_BLOCK.get()).hardnessAndResistance(2.0F, 3.0F)));
    public static final RegistryObject<Block> CHEESE_FENCE_GATE = registerBlockGeneral("cheese_fence_gate", () -> new FenceGateBlock(Block.Properties.from(CHEESE_BLOCK.get()).hardnessAndResistance(2.0F, 3.0F)));

    public static final RegistryObject<Block> GRILLED_CHEESE_FENCE = registerBlockGeneral("grilled_cheese_fence", () -> new FenceBlock(Block.Properties.from(GRILLED_CHEESE_BLOCK.get()).hardnessAndResistance(2.0F, 3.0F)));
    public static final RegistryObject<Block> GRILLED_CHEESE_FENCE_GATE = registerBlockGeneral("grilled_cheese_fence_gate", () -> new FenceGateBlock(Block.Properties.from(GRILLED_CHEESE_BLOCK.get()).hardnessAndResistance(2.0F, 3.0F)));

    public static final RegistryObject<Block> HAM_RAW_FENCE = registerBlockGeneral("ham_raw_fence", () -> new FenceBlock(Block.Properties.from(HAM_RAW_BLOCK.get()).hardnessAndResistance(2.0F, 3.0F)));
    public static final RegistryObject<Block> HAM_RAW_FENCE_GATE = registerBlockGeneral("ham_raw_fence_gate", () -> new FenceGateBlock(Block.Properties.from(HAM_RAW_BLOCK.get()).hardnessAndResistance(2.0F, 3.0F)));

    public static final RegistryObject<Block> HAM_COOKED_FENCE = registerBlockGeneral("ham_cooked_fence", () -> new FenceBlock(Block.Properties.from(HAM_COOKED_BLOCK.get()).hardnessAndResistance(2.0F, 3.0F)));
    public static final RegistryObject<Block> HAM_COOKED_FENCE_GATE = registerBlockGeneral("ham_cooked_fence_gate", () -> new FenceGateBlock(Block.Properties.from(HAM_COOKED_BLOCK.get()).hardnessAndResistance(2.0F, 3.0F)));

    public static final RegistryObject<Block> CHEESE_WOOD_FENCE = registerBlockGeneral("cheese_wood_fence", () -> new FenceBlock(Block.Properties.from(CHEESE_PLANKS.get()).hardnessAndResistance(2.0F, 3.0F)));
    public static final RegistryObject<Block> CHEESE_WOOD_FENCE_GATE = registerBlockGeneral("cheese_wood_fence_gate", () -> new FenceGateBlock(Block.Properties.from(CHEESE_PLANKS.get()).hardnessAndResistance(2.0F, 3.0F)));

    public static final RegistryObject<Block> GRILLED_CHEESE_WOOD_FENCE = registerBlockGeneral("grilled_cheese_wood_fence", () -> new FenceBlock(Block.Properties.from(GRILLED_CHEESE_PLANKS.get()).hardnessAndResistance(2.0F, 3.0F)));
    public static final RegistryObject<Block> GRILLED_CHEESE_WOOD_FENCE_GATE = registerBlockGeneral("grilled_cheese_wood_fence_gate", () -> new FenceGateBlock(Block.Properties.from(GRILLED_CHEESE_PLANKS.get()).hardnessAndResistance(2.0F, 3.0F)));

    public static final RegistryObject<Block> HAM_RAW_WOOD_FENCE = registerBlockGeneral("ham_raw_wood_fence", () -> new FenceBlock(Block.Properties.from(HAM_RAW_PLANKS.get()).hardnessAndResistance(2.0F, 3.0F)));
    public static final RegistryObject<Block> HAM_RAW_WOOD_FENCE_GATE = registerBlockGeneral("ham_raw_wood_fence_gate", () -> new FenceGateBlock(Block.Properties.from(HAM_RAW_PLANKS.get()).hardnessAndResistance(2.0F, 3.0F)));

    public static final RegistryObject<Block> HAM_COOKED_WOOD_FENCE = registerBlockGeneral("ham_cooked_wood_fence", () -> new FenceBlock(Block.Properties.from(HAM_COOKED_PLANKS.get()).hardnessAndResistance(2.0F, 3.0F)));
    public static final RegistryObject<Block> HAM_COOKED_WOOD_FENCE_GATE = registerBlockGeneral("ham_cooked_wood_fence_gate", () -> new FenceGateBlock(Block.Properties.from(HAM_COOKED_PLANKS.get()).hardnessAndResistance(2.0F, 3.0F)));

    /// Doors ///
    public static final RegistryObject<Block> CHEESE_DOOR = registerBlockGeneral("cheese_door", () -> new DoorBlock(Block.Properties.from(CHEESE_BLOCK.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> GRILLED_CHEESE_DOOR = registerBlockGeneral("grilled_cheese_door", () -> new DoorBlock(Block.Properties.from(GRILLED_CHEESE_BLOCK.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> HAM_RAW_DOOR = registerBlockGeneral("ham_raw_door", () -> new DoorBlock(Block.Properties.from(HAM_RAW_BLOCK.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> HAM_COOKED_DOOR = registerBlockGeneral("ham_cooked_door", () -> new DoorBlock(Block.Properties.from(HAM_COOKED_BLOCK.get()).hardnessAndResistance(3.0F)));

    public static final RegistryObject<Block> CHEESE_WOOD_DOOR = registerBlockGeneral("cheese_wood_door", () -> new DoorBlock(Block.Properties.from(CHEESE_PLANKS.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> GRILLED_CHEESE_WOOD_DOOR = registerBlockGeneral("grilled_cheese_wood_door", () -> new DoorBlock(Block.Properties.from(GRILLED_CHEESE_PLANKS.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> HAM_RAW_WOOD_DOOR = registerBlockGeneral("ham_raw_wood_door", () -> new DoorBlock(Block.Properties.from(HAM_RAW_PLANKS.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> HAM_COOKED_WOOD_DOOR = registerBlockGeneral("ham_cooked_wood_door", () -> new DoorBlock(Block.Properties.from(HAM_COOKED_PLANKS.get()).hardnessAndResistance(3.0F)));

    /// Trap Doors ///
    public static final RegistryObject<Block> CHEESE_TRAP_DOOR = registerBlockGeneral("cheese_trap_door", () -> new TrapDoorBlock(Block.Properties.from(CHEESE_BLOCK.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> GRILLED_CHEESE_TRAP_DOOR = registerBlockGeneral("grilled_cheese_trap_door", () -> new TrapDoorBlock(Block.Properties.from(GRILLED_CHEESE_BLOCK.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> HAM_RAW_TRAP_DOOR = registerBlockGeneral("ham_raw_trap_door", () -> new TrapDoorBlock(Block.Properties.from(HAM_RAW_BLOCK.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> HAM_COOKED_TRAP_DOOR = registerBlockGeneral("ham_cooked_trap_door", () -> new TrapDoorBlock(Block.Properties.from(HAM_COOKED_BLOCK.get()).hardnessAndResistance(3.0F)));

    public static final RegistryObject<Block> CHEESE_WOOD_TRAP_DOOR = registerBlockGeneral("cheese_wood_trap_door", () -> new TrapDoorBlock(Block.Properties.from(CHEESE_PLANKS.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> GRILLED_CHEESE_WOOD_TRAP_DOOR = registerBlockGeneral("grilled_cheese_wood_trap_door", () -> new TrapDoorBlock(Block.Properties.from(GRILLED_CHEESE_PLANKS.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> HAM_RAW_WOOD_TRAP_DOOR = registerBlockGeneral("ham_raw_wood_trap_door", () -> new TrapDoorBlock(Block.Properties.from(HAM_RAW_PLANKS.get()).hardnessAndResistance(3.0F)));
    public static final RegistryObject<Block> HAM_COOKED_WOOD_TRAP_DOOR = registerBlockGeneral("ham_cooked_wood_trap_door", () -> new TrapDoorBlock(Block.Properties.from(HAM_COOKED_PLANKS.get()).hardnessAndResistance(3.0F)));

    /// Pressure Plate ///
    public static final RegistryObject<Block> CHEESE_PRESSURE_PLATE = registerBlockGeneral("cheese_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(CHEESE_BLOCK.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> GRILLED_CHEESE_PRESSURE_PLATE = registerBlockGeneral("grilled_cheese_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(GRILLED_CHEESE_BLOCK.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> HAM_RAW_PRESSURE_PLATE = registerBlockGeneral("ham_raw_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HAM_RAW_BLOCK.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> HAM_COOKED_PRESSURE_PLATE = registerBlockGeneral("ham_cooked_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HAM_COOKED_BLOCK.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));

    public static final RegistryObject<Block> CHEESE_WOOD_PRESSURE_PLATE = registerBlockGeneral("cheese_wood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(CHEESE_PLANKS.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> GRILLED_CHEESE_WOOD_PRESSURE_PLATE = registerBlockGeneral("grilled_cheese_wood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(GRILLED_CHEESE_PLANKS.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> HAM_RAW_WOOD_PRESSURE_PLATE = registerBlockGeneral("ham_raw_wood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HAM_RAW_PLANKS.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> HAM_COOKED_WOOD_PRESSURE_PLATE = registerBlockGeneral("ham_cooked_wood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(HAM_COOKED_PLANKS.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));

    /// Buttons ///
    public static final RegistryObject<Block> CHEESE_BUTTON = registerBlockGeneral("cheese_button", () -> new WoodButtonBlock(Block.Properties.from(CHEESE_BLOCK.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> GRILLED_CHEESE_BUTTON = registerBlockGeneral("grilled_cheese_button", () -> new WoodButtonBlock(Block.Properties.from(GRILLED_CHEESE_BLOCK.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> HAM_RAW_BUTTON = registerBlockGeneral("ham_raw_button", () -> new WoodButtonBlock(Block.Properties.from(HAM_RAW_BLOCK.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> HAM_COOKED_BUTTON = registerBlockGeneral("ham_cooked_button", () -> new WoodButtonBlock(Block.Properties.from(HAM_COOKED_BLOCK.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));

    public static final RegistryObject<Block> CHEESE_WOOD_BUTTON = registerBlockGeneral("cheese_wood_button", () -> new WoodButtonBlock(Block.Properties.from(CHEESE_PLANKS.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> GRILLED_CHEESE_WOOD_BUTTON = registerBlockGeneral("grilled_cheese_wood_button", () -> new WoodButtonBlock(Block.Properties.from(GRILLED_CHEESE_PLANKS.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> HAM_RAW_WOOD_BUTTON = registerBlockGeneral("ham_raw_wood_button", () -> new WoodButtonBlock(Block.Properties.from(HAM_RAW_PLANKS.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> HAM_COOKED_WOOD_BUTTON = registerBlockGeneral("ham_cooked_wood_button", () -> new WoodButtonBlock(Block.Properties.from(HAM_COOKED_PLANKS.get()).doesNotBlockMovement().hardnessAndResistance(0.5F)));

    /// Drawers ///
    public static final RegistryObject<Block> CHEESE_DRAW = registerBlockGeneral("cheese_draw", () -> new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD), () -> ModStats.INTERACT_WITH_CHEESE_DRAW));
    public static final RegistryObject<Block> GRILLED_CHEESE_DRAW = registerBlockGeneral("grilled_cheese_draw", () -> new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD), () -> ModStats.INTERACT_WITH_CHEESE_DRAW));
    public static final RegistryObject<Block> HAM_RAW_DRAW = registerBlockGeneral("ham_raw_draw", () -> new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD), () -> ModStats.INTERACT_WITH_HAM_DRAW));
    public static final RegistryObject<Block> HAM_COOKED_DRAW = registerBlockGeneral("ham_cooked_draw", () -> new FoodDrawBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD), () -> ModStats.INTERACT_WITH_HAM_DRAW));

    /// Other ///
    public static final RegistryObject<Block> GRILL = registerBlockGeneral("grill", () -> new GrillBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5f).lightValue(13)));
    public static final RegistryObject<Block> MELTER = registerBlockGeneral("melter", () -> new MelterBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5f).lightValue(13)));
    public static final RegistryObject<Block> PIZZA_OVEN = registerBlockGeneral("pizza_oven", () -> new PizzaOvenBlock(Block.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(2.0f, 6.0f)));

    public static final RegistryObject<PineappleBlock> PINEAPPLE = BLOCKS.register("pineapple_plant", () -> new PineappleBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP)));

    public static final RegistryObject<Block> CHEESE_CAKE = registerBlockGroup("cheese_cake", () -> new CakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5f).sound(SoundType.CLOTH)), ModItemGroups.GROUP_FOODS);
    public static final RegistryObject<Block> GRILLED_CHEESE_CAKE = registerBlockGroup("grilled_cheese_cake", () -> new CakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5f).sound(SoundType.CLOTH)), ModItemGroups.GROUP_FOODS);
    public static final RegistryObject<Block> HAM_RAW_CAKE = registerBlockGroup("ham_raw_cake", () -> new CakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5f).sound(SoundType.CLOTH)), ModItemGroups.GROUP_FOODS);
    public static final RegistryObject<Block> HAM_COOKED_CAKE = registerBlockGroup("ham_cooked_cake", () -> new CakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5f).sound(SoundType.CLOTH)), ModItemGroups.GROUP_FOODS);

    private static <T extends Block> RegistryObject<T> registerBlockGeneral(String name, Supplier<? extends T> block) {
        return registerBlock(name, block, item -> registerBlockItem(item, ModItemGroups.GROUP_ALL));
    }

    private static <T extends Block> RegistryObject<T> registerBlockGroup(String name, Supplier<? extends T> block, ItemGroup group) {
        return registerBlock(name, block, item -> registerBlockItem(item, group));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, ItemGroup group) {
        return () -> new BlockItem(block.get(), new Item.Properties().group(group));
    }
}
