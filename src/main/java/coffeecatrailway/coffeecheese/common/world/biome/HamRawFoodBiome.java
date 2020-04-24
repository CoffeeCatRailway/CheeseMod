package coffeecatrailway.coffeecheese.common.world.biome;

import coffeecatrailway.coffeecheese.registry.ModBiomes;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModFeatures;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2020
 */
public class HamRawFoodBiome extends BaseFoodBiome {

    public HamRawFoodBiome(Builder builder, int grassColor) {
        super(builder, grassColor);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        ModFeatures.addFoodGrass(this, ModFeatures.getHamRawGrassConfig());
        ModFeatures.addTallFoodGrass(this, ModFeatures.getTallHamRawGrassConfig());
        if (this.category == Category.FOREST)
            ModFeatures.addFoodTrees(this, ModFeatures.HAM_RAW_TREE.get().withConfiguration(ModFeatures.getHamRawTreeConfig()), ModFeatures.HAM_COOKED_TREE.get().withConfiguration(ModFeatures.getHamCookedTreeConfig()), 10);
    }
}
