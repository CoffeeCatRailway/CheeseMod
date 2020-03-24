package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.world.feature.BaconBallFeature;
import coffeecatrailway.coffeecheese.common.world.feature.FoodTreeFeatureConfig;
import coffeecatrailway.coffeecheese.common.world.feature.tree.CheeseTreeFeature;
import coffeecatrailway.coffeecheese.common.world.feature.tree.HamTreeFeature;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.IntArrayNBT;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, CheeseMod.MOD_ID);

    private static final Supplier<BlockState> CHEESE_LOG = () -> ModBlocks.CHEESE_LOG.get().getDefaultState();
    private static final Supplier<BlockState> CHEESE_LEAVES = () -> ModBlocks.CHEESE_LEAVES.get().getDefaultState();
    private static final Supplier<IPlantable> CHEESE_SAPLING = ModBlocks.CHEESE_SAPLING::get;

    private static final Supplier<BlockState> CHEESE_GRASS = () -> ModBlocks.CHEESE_GRASS.get().getDefaultState();
    private static final Supplier<BlockState> TALL_CHEESE_GRASS = () -> ModBlocks.TALL_CHEESE_GRASS.get().getDefaultState();

    private static final Supplier<BlockState> GRILLED_CHEESE_LOG = () -> ModBlocks.GRILLED_CHEESE_LOG.get().getDefaultState();
    private static final Supplier<BlockState> GRILLED_CHEESE_LEAVES = () -> ModBlocks.GRILLED_CHEESE_LEAVES.get().getDefaultState();
    private static final Supplier<IPlantable> GRILLED_CHEESE_SAPLING = ModBlocks.GRILLED_CHEESE_SAPLING::get;

    private static final Supplier<BlockState> GRILLED_CHEESE_GRASS = () -> ModBlocks.GRILLED_CHEESE_GRASS.get().getDefaultState();
    private static final Supplier<BlockState> TALL_GRILLED_CHEESE_GRASS = () -> ModBlocks.TALL_GRILLED_CHEESE_GRASS.get().getDefaultState();

    private static final Supplier<BlockState> HAM_RAW_LOG = () -> ModBlocks.HAM_RAW_LOG.get().getDefaultState();
    private static final Supplier<BlockState> HAM_RAW_LEAVES = () -> ModBlocks.HAM_RAW_LEAVES.get().getDefaultState();
    private static final Supplier<IPlantable> HAM_RAW_SAPLING = ModBlocks.HAM_RAW_SAPLING::get;

    private static final Supplier<BlockState> HAM_RAW_GRASS = () -> ModBlocks.HAM_RAW_GRASS.get().getDefaultState();
    private static final Supplier<BlockState> TALL_HAM_RAW_GRASS = () -> ModBlocks.TALL_HAM_RAW_GRASS.get().getDefaultState();

    private static final Supplier<BlockState> HAM_COOKED_LOG = () -> ModBlocks.HAM_COOKED_LOG.get().getDefaultState();
    private static final Supplier<BlockState> HAM_COOKED_LEAVES = () -> ModBlocks.HAM_COOKED_LEAVES.get().getDefaultState();
    private static final Supplier<IPlantable> HAM_COOKED_SAPLING = ModBlocks.HAM_COOKED_SAPLING::get;
    
    private static final Supplier<BlockState> HAM_COOKED_GRASS = () -> ModBlocks.HAM_COOKED_GRASS.get().getDefaultState();
    private static final Supplier<BlockState> TALL_HAM_COOKED_GRASS = () -> ModBlocks.TALL_HAM_COOKED_GRASS.get().getDefaultState();

    /// Trees ///
    public static final RegistryObject<Feature<FoodTreeFeatureConfig>> CHEESE_TREE = FEATURES.register("cheese_tree", () ->
            new CheeseTreeFeature(false, FoodTreeFeatureConfig::deserializeCheese));
    public static final FoodTreeFeatureConfig CHEESE_TREE_CONFIG = (new FoodTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(CHEESE_LOG.get()), new SimpleBlockStateProvider(CHEESE_LEAVES.get()))
            .setSapling(CHEESE_SAPLING.get())).build();

    public static final RegistryObject<Feature<FoodTreeFeatureConfig>> GRILLED_CHEESE_TREE = FEATURES.register("grilled_cheese_tree", () ->
            new CheeseTreeFeature(true, FoodTreeFeatureConfig::deserializeGrilledCheese));
    public static final FoodTreeFeatureConfig GRILLED_CHEESE_TREE_CONFIG = (new FoodTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(GRILLED_CHEESE_LOG.get()), new SimpleBlockStateProvider(GRILLED_CHEESE_LEAVES.get()))
            .setSapling(GRILLED_CHEESE_SAPLING.get())).build();

    public static final RegistryObject<Feature<FoodTreeFeatureConfig>> HAM_RAW_TREE = FEATURES.register("ham_raw_tree", () ->
            new HamTreeFeature(false, FoodTreeFeatureConfig::deserializeHamRaw));
    public static final FoodTreeFeatureConfig HAM_RAW_TREE_CONFIG = (new FoodTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(HAM_RAW_LOG.get()), new SimpleBlockStateProvider(HAM_RAW_LEAVES.get()))
            .setSapling(HAM_RAW_SAPLING.get())).build();

    public static final RegistryObject<Feature<FoodTreeFeatureConfig>> HAM_COOKED_TREE = FEATURES.register("ham_cooked_tree", () ->
            new HamTreeFeature(true, FoodTreeFeatureConfig::deserializeHamCooked));
    public static final FoodTreeFeatureConfig HAM_COOKED_TREE_CONFIG = (new FoodTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(HAM_COOKED_LOG.get()), new SimpleBlockStateProvider(HAM_COOKED_LEAVES.get()))
            .setSapling(HAM_COOKED_SAPLING.get())).build();

    /// Other ///
    public static final RegistryObject<BaconBallFeature> BACON_RAW_BALL = FEATURES.register("bacon_raw_ball", () -> new BaconBallFeature(false, NoFeatureConfig::deserialize));
    public static final RegistryObject<BaconBallFeature> BACON_COOKED_BALL = FEATURES.register("bacon_cooked_ball", () -> new BaconBallFeature(true, NoFeatureConfig::deserialize));

    public static final BlockClusterFeatureConfig CHEESE_GRASS_CONFIG = new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().func_227407_a_(CHEESE_GRASS.get(), 3).func_227407_a_(GRILLED_CHEESE_GRASS.get(), 1), new SimpleBlockPlacer()).tries(32).build();
    public static final BlockClusterFeatureConfig GRILLED_CHEESE_GRASS_CONFIG = new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().func_227407_a_(GRILLED_CHEESE_GRASS.get(), 3).func_227407_a_(CHEESE_GRASS.get(), 1), new SimpleBlockPlacer()).tries(32).build();
    public static final BlockClusterFeatureConfig HAM_RAW_GRASS_CONFIG = new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().func_227407_a_(HAM_RAW_GRASS.get(), 3).func_227407_a_(HAM_COOKED_GRASS.get(), 1), new SimpleBlockPlacer()).tries(32).build();
    public static final BlockClusterFeatureConfig HAM_COOKED_GRASS_CONFIG = new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().func_227407_a_(HAM_COOKED_GRASS.get(), 3).func_227407_a_(HAM_RAW_GRASS.get(), 1), new SimpleBlockPlacer()).tries(32).build();

    public static final BlockClusterFeatureConfig TALL_CHEESE_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_CHEESE_GRASS.get()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig TALL_GRILLED_CHEESE_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_GRILLED_CHEESE_GRASS.get()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig TALL_HAM_RAW_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_HAM_RAW_GRASS.get()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig TALL_HAM_COOKED_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_HAM_COOKED_GRASS.get()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();

    public static void addFoodGrass(Biome biome, BlockClusterFeatureConfig config) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(25))));
    }

    public static void addTallFoodGrass(Biome biome, BlockClusterFeatureConfig config) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(7))));
    }

    public static void addFoodTrees(Biome biome, ConfiguredFeature<? extends FoodTreeFeatureConfig, ?> mainTree, ConfiguredFeature<? extends FoodTreeFeatureConfig, ?> secondTree) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(secondTree.func_227227_a_(0.1F)), mainTree)).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
    }
}
