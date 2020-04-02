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
        ModFeatures.addFoodGrass(this, ModFeatures.getCheeseGrassConfig());
        ModFeatures.addTallFoodGrass(this, ModFeatures.getTallCheeseGrassConfig());
        if (this.category == Category.FOREST)
            ModFeatures.addFoodTrees(this, ModFeatures.CHEESE_TREE.get().withConfiguration(ModFeatures.getCheeseTreeConfig()), ModFeatures.GRILLED_CHEESE_TREE.get().withConfiguration(ModFeatures.getGrilledCheeseTreeConfig()), 10);
    }
}
