package coffeecatrailway.coffeecheese.common.world.biome;

import coffeecatrailway.coffeecheese.registry.ModBiomes;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModFeatures;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2020
 */
public class CheeseFoodBiome extends BaseFoodBiome {

    public CheeseFoodBiome(Category category) {
        super(ModBiomes.CHEESE_WATER_COLOR, ModBiomes.CHEESE_WATER_FOG_COLOR, ModBiomes.CHEESE_GRASS_COLOR, ModBlocks.CHEESE_GRASS_BLOCK.get().getDefaultState(), category);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        ModFeatures.addFoodGrass(this, ModFeatures.CHEESE_GRASS_CONFIG);
        ModFeatures.addTallFoodGrass(this, ModFeatures.TALL_CHEESE_GRASS_CONFIG);
        if (this.category == Category.FOREST)
            ModFeatures.addFoodTrees(this, ModFeatures.CHEESE_TREE.get().withConfiguration(ModFeatures.CHEESE_TREE_CONFIG), ModFeatures.GRILLED_CHEESE_TREE.get().withConfiguration(ModFeatures.GRILLED_CHEESE_TREE_CONFIG));
    }
}
