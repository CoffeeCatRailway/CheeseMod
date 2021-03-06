package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.common.world.feature.FoodTreeFeatureConfig;
import coffeecatrailway.coffeecheese.common.world.feature.tree.CheeseTreeFeature;
import coffeecatrailway.coffeecheese.common.world.feature.tree.HamTreeFeature;
import com.google.common.collect.ImmutableList;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class ModFeatures {

    /// Trees ///
    public static final RegistryEntry<CheeseTreeFeature> CHEESE_TREE = REGISTRATE.feature("cheese_tree", () ->
            new CheeseTreeFeature(false, FoodTreeFeatureConfig::deserializeCheese)).register();

    public static final RegistryEntry<CheeseTreeFeature> GRILLED_CHEESE_TREE = REGISTRATE.feature("grilled_cheese_tree", () ->
            new CheeseTreeFeature(true, FoodTreeFeatureConfig::deserializeGrilledCheese)).register();

    public static final RegistryEntry<HamTreeFeature> HAM_RAW_TREE = REGISTRATE.feature("ham_raw_tree", () ->
            new HamTreeFeature(false, FoodTreeFeatureConfig::deserializeHamRaw)).register();

    public static final RegistryEntry<HamTreeFeature> HAM_COOKED_TREE = REGISTRATE.feature("ham_cooked_tree", () ->
            new HamTreeFeature(true, FoodTreeFeatureConfig::deserializeHamCooked)).register();

    public static FoodTreeFeatureConfig getCheeseTreeConfig() {
        return (new FoodTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(ModBlocks.CHEESE_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.CHEESE_LEAVES.get().getDefaultState()))
                .setSapling(ModBlocks.CHEESE_SAPLING.get())).build();
    }

    public static FoodTreeFeatureConfig getGrilledCheeseTreeConfig() {
        return (new FoodTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(ModBlocks.GRILLED_CHEESE_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.GRILLED_CHEESE_LEAVES.get().getDefaultState()))
                .setSapling(ModBlocks.GRILLED_CHEESE_SAPLING.get())).build();
    }

    public static FoodTreeFeatureConfig getHamRawTreeConfig() {
        return (new FoodTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(ModBlocks.HAM_RAW_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.HAM_RAW_LEAVES.get().getDefaultState()))
                .setSapling(ModBlocks.HAM_RAW_SAPLING.get())).build();
    }

    public static FoodTreeFeatureConfig getHamCookedTreeConfig() {
        return (new FoodTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(ModBlocks.HAM_COOKED_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.HAM_COOKED_LEAVES.get().getDefaultState()))
                .setSapling(ModBlocks.HAM_COOKED_SAPLING.get())).build();
    }

    public static BlockClusterFeatureConfig getCheeseGrassConfig() {
        return new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(ModBlocks.CHEESE_GRASS.get().getDefaultState(), 3)
                .addWeightedBlockstate(ModBlocks.GRILLED_CHEESE_GRASS.get().getDefaultState(), 1), new SimpleBlockPlacer()).tries(32).build();
    }

    public static BlockClusterFeatureConfig getGrilledCheeseGrassConfig() {
        return new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(ModBlocks.GRILLED_CHEESE_GRASS.get().getDefaultState(), 3)
                .addWeightedBlockstate(ModBlocks.CHEESE_GRASS.get().getDefaultState(), 1), new SimpleBlockPlacer()).tries(32).build();
    }

    public static BlockClusterFeatureConfig getHamRawGrassConfig() {
        return new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(ModBlocks.HAM_RAW_GRASS.get().getDefaultState(), 3)
                .addWeightedBlockstate(ModBlocks.HAM_COOKED_GRASS.get().getDefaultState(), 1), new SimpleBlockPlacer()).tries(32).build();
    }

    public static BlockClusterFeatureConfig getHamCookedGrassConfig() {
        return new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(ModBlocks.HAM_COOKED_GRASS.get().getDefaultState(), 3)
                .addWeightedBlockstate(ModBlocks.HAM_RAW_GRASS.get().getDefaultState(), 1), new SimpleBlockPlacer()).tries(32).build();
    }

    public static BlockClusterFeatureConfig getTallCheeseGrassConfig() {
        return (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.TALL_CHEESE_GRASS.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    }

    public static BlockClusterFeatureConfig getTallGrilledCheeseGrassConfig() {
        return (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.TALL_GRILLED_CHEESE_GRASS.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    }

    public static BlockClusterFeatureConfig getTallHamRawGrassConfig() {
        return (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.TALL_HAM_RAW_GRASS.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    }

    public static BlockClusterFeatureConfig getTallHamCookedGrassConfig() {
        return (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.TALL_HAM_COOKED_GRASS.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    }

    public static void addFoodGrass(Biome biome, BlockClusterFeatureConfig config) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(25))));
    }

    public static void addTallFoodGrass(Biome biome, BlockClusterFeatureConfig config) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(7))));
    }

    public static void addFoodTrees(Biome biome, ConfiguredFeature<? extends FoodTreeFeatureConfig, ?> mainTree, ConfiguredFeature<? extends FoodTreeFeatureConfig, ?> secondTree, int count) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(secondTree.withChance(0.15F)), mainTree)).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(count, 0.1F, 1))));
    }

    public static void load() {
    }
}
