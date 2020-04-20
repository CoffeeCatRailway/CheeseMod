package coffeecatrailway.coffeecheese.common.world.feature.tree;

import coffeecatrailway.coffeecheese.common.world.feature.FoodTreeFeatureConfig;
import coffeecatrailway.coffeecheese.registry.ModFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

/**
 * @author CoffeeCatRailway - Androsa https://github.com/Andromander
 * Created: 27/07/2019
 */
public class CheeseTree extends FoodTree {

    private boolean grilled;

    public CheeseTree(boolean grilled) {
        this.grilled = grilled;
    }

    @Override
    public ConfiguredFeature<FoodTreeFeatureConfig, ?> createTreeFeature(Random rand) {
        return grilled ? ModFeatures.GRILLED_CHEESE_TREE.get().withConfiguration(ModFeatures.getGrilledCheeseTreeConfig()) : ModFeatures.CHEESE_TREE.get().withConfiguration(ModFeatures.getCheeseTreeConfig());
    }
}
