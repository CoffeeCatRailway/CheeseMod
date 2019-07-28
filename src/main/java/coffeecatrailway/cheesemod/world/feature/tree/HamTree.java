package coffeecatrailway.cheesemod.world.feature.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 27/07/2019
 */
public class HamTree extends Tree {

    private boolean cooked;

    public HamTree(boolean cooked) {
        this.cooked = cooked;
    }

    @Nullable
    @Override
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
        return new HamTreeFeature(cooked, NoFeatureConfig::deserialize, true);
    }
}
