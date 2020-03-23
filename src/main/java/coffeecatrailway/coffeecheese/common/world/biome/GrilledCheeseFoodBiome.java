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
public class GrilledCheeseFoodBiome extends BaseFoodBiome {

    public GrilledCheeseFoodBiome(Category category) {
        super(ModBiomes.GRILLED_CHEESE_WATER_COLOR, ModBiomes.GRILLED_CHEESE_WATER_FOG_COLOR, ModBiomes.GRILLED_CHEESE_GRASS_COLOR, ModBlocks.GRILLED_CHEESE_GRASS_BLOCK.get().getDefaultState(), category);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        ModFeatures.addFoodGrass(this, ModFeatures.GRILLED_CHEESE_GRASS_CONFIG);
        ModFeatures.addTallFoodGrass(this, ModFeatures.TALL_GRILLED_CHEESE_GRASS_CONFIG);
        if (this.category == Category.FOREST)
            ModFeatures.addFoodTrees(this, ModFeatures.GRILLED_CHEESE_TREE.get().withConfiguration(ModFeatures.GRILLED_CHEESE_TREE_CONFIG), ModFeatures.CHEESE_TREE.get().withConfiguration(ModFeatures.CHEESE_TREE_CONFIG));
    }
}
