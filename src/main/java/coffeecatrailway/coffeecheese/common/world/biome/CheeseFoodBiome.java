package coffeecatrailway.coffeecheese.common.world.biome;

import coffeecatrailway.coffeecheese.registry.ModFeatures;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2020
 */
public class CheeseFoodBiome extends BaseFoodBiome {

    public CheeseFoodBiome(Builder builder, int grassColor) {
        super(builder, grassColor);
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
