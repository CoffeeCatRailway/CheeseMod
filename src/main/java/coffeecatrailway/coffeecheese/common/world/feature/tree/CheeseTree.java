package coffeecatrailway.coffeecheese.common.world.feature.tree;

import coffeecatrailway.coffeecheese.core.ModFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 27/07/2019
 */
public class CheeseTree extends Tree {

    private boolean grilled;

    public CheeseTree(boolean grilled) {
        this.grilled = grilled;
    }

    @Nullable
    @Override
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
        return grilled ? ModFeatures.GRILLED_CHEESE_TREE : ModFeatures.CHEESE_TREE;
    }
}
