package coffeecatrailway.coffeecheese.common.world.feature.tree;

import coffeecatrailway.coffeecheese.common.world.feature.FoodTreeFeatureConfig;
import coffeecatrailway.coffeecheese.registry.ModFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import javax.annotation.Nullable;
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
        return grilled ? ModFeatures.GRILLED_CHEESE_TREE.get().withConfiguration(ModFeatures.GRILLED_CHEESE_TREE_CONFIG) : ModFeatures.CHEESE_TREE.get().withConfiguration(ModFeatures.CHEESE_TREE_CONFIG);
    }
}
