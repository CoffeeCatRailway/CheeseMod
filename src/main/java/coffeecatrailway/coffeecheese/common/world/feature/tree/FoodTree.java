package coffeecatrailway.coffeecheese.common.world.feature.tree;

import coffeecatrailway.coffeecheese.common.world.feature.FoodTreeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author CoffeeCatRailway - Androsa https://github.com/Andromander
 * Created: 5/03/2020
 */
public abstract class FoodTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean b) {
        return null;
    }

    public abstract ConfiguredFeature<FoodTreeFeatureConfig, ?> createTreeFeature(Random rand);

    @Override
    public boolean place(IWorld world, ChunkGenerator<?> generator, BlockPos pos, BlockState state, Random rand) {
        ConfiguredFeature<FoodTreeFeatureConfig, ?> feature = this.createTreeFeature(rand);
        if (feature == null)
            return false;
        else {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
            feature.config.forcePlacement();
            if (feature.place(world, generator, rand, pos))
                return true;
            else {
                world.setBlockState(pos, state, 4);
                return false;
            }
        }
    }
}
