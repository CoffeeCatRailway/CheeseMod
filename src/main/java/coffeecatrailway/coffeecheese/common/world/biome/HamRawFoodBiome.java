package coffeecatrailway.coffeecheese.common.world.biome;

import coffeecatrailway.coffeecheese.registry.ModBiomes;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModFeatures;

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
        ModFeatures.addFoodGrass(this, ModFeatures.getHamRawGrassConfig());
        ModFeatures.addTallFoodGrass(this, ModFeatures.getTallHamRawGrassConfig());
        if (this.category == Category.FOREST)
            ModFeatures.addFoodTrees(this, ModFeatures.HAM_RAW_TREE.get().withConfiguration(ModFeatures.getHamRawTreeConfig()), ModFeatures.HAM_COOKED_TREE.get().withConfiguration(ModFeatures.getHamCookedTreeConfig()), 10);
    }
}
