package coffeecatrailway.coffeecheese.common.world.biome;

import coffeecatrailway.coffeecheese.registry.ModFeatures;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2020
 */
public class GrilledCheeseFoodBiome extends BaseFoodBiome {

    public GrilledCheeseFoodBiome(Builder builder, int grassColor) {
        super(builder, grassColor);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        ModFeatures.addFoodGrass(this, ModFeatures.getGrilledCheeseGrassConfig());
        ModFeatures.addTallFoodGrass(this, ModFeatures.getTallGrilledCheeseGrassConfig());
        if (this.category == Category.FOREST)
            ModFeatures.addFoodTrees(this, ModFeatures.GRILLED_CHEESE_TREE.get().withConfiguration(ModFeatures.getGrilledCheeseTreeConfig()), ModFeatures.CHEESE_TREE.get().withConfiguration(ModFeatures.getCheeseTreeConfig()), 9);
    }
}
