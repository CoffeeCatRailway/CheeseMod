package coffeecatrailway.coffeecheese.common.world.biome;

import coffeecatrailway.coffeecheese.registry.ModBiomes;
import coffeecatrailway.coffeecheese.registry.ModFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2020
 */
public class CheeseFoodBiome extends BaseFoodBiome {

    public CheeseFoodBiome(Category category) {
        super(ModBiomes.CHEESE_WATER_COLOR, ModBiomes.CHEESE_WATER_FOG_COLOR, ModBiomes.CHEESE_GRASS_COLOR, Blocks.GRASS_BLOCK.getDefaultState(), category);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(
                            new Feature[]{ModFeatures.GRILLED_CHEESE_TREE.get()}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.2F}, ModFeatures.CHEESE_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG),
                    Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(10, 0.1f, 1)));
    }
}
