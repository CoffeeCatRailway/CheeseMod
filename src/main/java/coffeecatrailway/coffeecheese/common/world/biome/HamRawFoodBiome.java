package coffeecatrailway.coffeecheese.common.world.biome;

import coffeecatrailway.coffeecheese.registry.ModBiomes;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2020
 */
public class HamRawFoodBiome extends BaseFoodBiome {

    public HamRawFoodBiome(Category category) {
        super(ModBiomes.HAM_RAW_WATER_COLOR, ModBiomes.HAM_RAW_WATER_FOG_COLOR, ModBiomes.HAM_RAW_GRASS_COLOR, ModBlocks.HAM_RAW_GRASS_BLOCK.get().getDefaultState(), category);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.HAM_RAW_GRASS.get().getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(3)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DOUBLE_PLANT, new DoublePlantConfig(ModBlocks.TALL_HAM_RAW_GRASS.get().getDefaultState()), Placement.NOISE_HEIGHTMAP_32, new NoiseDependant(-0.8D, 0, 10)));
        if (this.category == Category.FOREST)
            this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(
                            new Feature[]{ModFeatures.HAM_COOKED_TREE.get()}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.2F}, ModFeatures.HAM_RAW_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG),
                    Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(10, 0.1f, 1)));
    }
}
