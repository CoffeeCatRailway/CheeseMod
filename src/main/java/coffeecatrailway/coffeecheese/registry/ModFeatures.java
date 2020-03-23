package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.world.feature.BaconBallFeature;
import coffeecatrailway.coffeecheese.common.world.feature.FoodTreeFeatureConfig;
import coffeecatrailway.coffeecheese.common.world.feature.tree.CheeseTreeFeature;
import coffeecatrailway.coffeecheese.common.world.feature.tree.HamTreeFeature;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
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
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, CheeseMod.MOD_ID);

    /// Trees ///
    public static final RegistryObject<Feature<FoodTreeFeatureConfig>> CHEESE_TREE = FEATURES.register("cheese_tree", () ->
            new CheeseTreeFeature(false, FoodTreeFeatureConfig::deserializeCheese));
    public static final FoodTreeFeatureConfig CHEESE_TREE_CONFIG = (new FoodTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.CHEESE_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.CHEESE_LEAVES.get().getDefaultState()))
            .setSapling(ModBlocks.CHEESE_SAPLING.get())).build();

    public static final RegistryObject<Feature<FoodTreeFeatureConfig>> GRILLED_CHEESE_TREE = FEATURES.register("grilled_cheese_tree", () ->
            new CheeseTreeFeature(true, FoodTreeFeatureConfig::deserializeGrilledCheese));
    public static final FoodTreeFeatureConfig GRILLED_CHEESE_TREE_CONFIG = (new FoodTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.GRILLED_CHEESE_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.GRILLED_CHEESE_LEAVES.get().getDefaultState()))
            .setSapling(ModBlocks.GRILLED_CHEESE_SAPLING.get())).build();

    public static final RegistryObject<Feature<FoodTreeFeatureConfig>> HAM_RAW_TREE = FEATURES.register("ham_raw_tree", () ->
            new HamTreeFeature(false, FoodTreeFeatureConfig::deserializeHamRaw));
    public static final FoodTreeFeatureConfig HAM_RAW_TREE_CONFIG = (new FoodTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.HAM_RAW_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.HAM_RAW_LEAVES.get().getDefaultState()))
            .setSapling(ModBlocks.HAM_RAW_SAPLING.get())).build();

    public static final RegistryObject<Feature<FoodTreeFeatureConfig>> HAM_COOKED_TREE = FEATURES.register("ham_cooked_tree", () ->
            new HamTreeFeature(true, FoodTreeFeatureConfig::deserializeHamCooked));
    public static final FoodTreeFeatureConfig HAM_COOKED_TREE_CONFIG = (new FoodTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.HAM_COOKED_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.HAM_COOKED_LEAVES.get().getDefaultState()))
            .setSapling(ModBlocks.HAM_COOKED_SAPLING.get())).build();

    /// Other ///
    public static final RegistryObject<BaconBallFeature> BACON_RAW_BALL = FEATURES.register("bacon_raw_ball", () -> new BaconBallFeature(false, NoFeatureConfig::deserialize));
    public static final RegistryObject<BaconBallFeature> BACON_COOKED_BALL = FEATURES.register("bacon_cooked_ball", () -> new BaconBallFeature(true, NoFeatureConfig::deserialize));

    private static final BlockState CHEESE_GRASS = ModBlocks.CHEESE_GRASS.get().getDefaultState();
    private static final BlockState TALL_CHEESE_GRASS = ModBlocks.TALL_CHEESE_GRASS.get().getDefaultState();
    private static final BlockState GRILLED_CHEESE_GRASS = ModBlocks.GRILLED_CHEESE_GRASS.get().getDefaultState();
    private static final BlockState TALL_GRILLED_CHEESE_GRASS = ModBlocks.TALL_GRILLED_CHEESE_GRASS.get().getDefaultState();
    private static final BlockState HAM_RAW_GRASS = ModBlocks.HAM_RAW_GRASS.get().getDefaultState();
    private static final BlockState TALL_HAM_RAW_GRASS = ModBlocks.TALL_HAM_RAW_GRASS.get().getDefaultState();
    private static final BlockState HAM_COOKED_GRASS = ModBlocks.HAM_COOKED_GRASS.get().getDefaultState();
    private static final BlockState TALL_HAM_COOKED_GRASS = ModBlocks.TALL_HAM_COOKED_GRASS.get().getDefaultState();

    public static final BlockClusterFeatureConfig CHEESE_GRASS_CONFIG = new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().func_227407_a_(CHEESE_GRASS, 3).func_227407_a_(GRILLED_CHEESE_GRASS, 1), new SimpleBlockPlacer()).tries(32).build();
    public static final BlockClusterFeatureConfig GRILLED_CHEESE_GRASS_CONFIG = new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().func_227407_a_(GRILLED_CHEESE_GRASS, 3).func_227407_a_(CHEESE_GRASS, 1), new SimpleBlockPlacer()).tries(32).build();
    public static final BlockClusterFeatureConfig HAM_RAW_GRASS_CONFIG = new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().func_227407_a_(HAM_RAW_GRASS, 3).func_227407_a_(HAM_COOKED_GRASS, 1), new SimpleBlockPlacer()).tries(32).build();
    public static final BlockClusterFeatureConfig HAM_COOKED_GRASS_CONFIG = new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().func_227407_a_(HAM_COOKED_GRASS, 3).func_227407_a_(HAM_RAW_GRASS, 1), new SimpleBlockPlacer()).tries(32).build();

    public static final BlockClusterFeatureConfig TALL_CHEESE_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_CHEESE_GRASS), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig TALL_GRILLED_CHEESE_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_GRILLED_CHEESE_GRASS), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig TALL_HAM_RAW_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_HAM_RAW_GRASS), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig TALL_HAM_COOKED_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TALL_HAM_COOKED_GRASS), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();

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
