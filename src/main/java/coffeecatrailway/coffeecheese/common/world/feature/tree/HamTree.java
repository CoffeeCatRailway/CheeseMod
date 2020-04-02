package coffeecatrailway.coffeecheese.common.world.feature.tree;

import coffeecatrailway.coffeecheese.common.world.feature.FoodTreeFeatureConfig;
import coffeecatrailway.coffeecheese.registry.ModFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 27/07/2019
 */
public class HamTree extends FoodTree {

    private boolean cooked;

    public HamTree(boolean cooked) {
        this.cooked = cooked;
    }

    @Override
    public ConfiguredFeature<FoodTreeFeatureConfig, ?> createTreeFeature(Random rand) {
        return cooked ? ModFeatures.HAM_COOKED_TREE.get().withConfiguration(ModFeatures.getHamCookedTreeConfig()) : ModFeatures.HAM_RAW_TREE.get().withConfiguration(ModFeatures.getHamRawTreeConfig());
    }
}
