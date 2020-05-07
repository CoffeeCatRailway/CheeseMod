package coffeecatrailway.coffeecheese.common.world.feature.tree;

import coffeecatrailway.coffeecheese.common.world.feature.FoodTreeFeatureConfig;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

/**
 * @author CoffeeCatRailway - Androsa https://github.com/Andromander
 * Created: 27/07/2019
 */
public class CheeseTreeFeature extends TreeFeature {

    private final int minTreeHeight;
    private final BlockState trunk;
    private final BlockState leaf;
    private final BlockState food;

    public CheeseTreeFeature(boolean grilled, Function<Dynamic<?>, ? extends FoodTreeFeatureConfig> function) {
        super(function);
        if (grilled) {
            this.minTreeHeight = 6;
            this.trunk = ModBlocks.GRILLED_CHEESE_LOG.get().getDefaultState();
            this.leaf = ModBlocks.GRILLED_CHEESE_LEAVES.get().getDefaultState();
            this.food = ModBlocks.GRILLED_CHEESE_BLOCK.get().getDefaultState();
        } else {
            this.minTreeHeight = 4;
            this.trunk = ModBlocks.CHEESE_LOG.get().getDefaultState();
            this.leaf = ModBlocks.CHEESE_LEAVES.get().getDefaultState();
            this.food = ModBlocks.CHEESE_BLOCK.get().getDefaultState();
        }
    }

    @Override
    protected boolean func_225557_a_(IWorldGenerationReader world, Random rand, BlockPos position, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox boundingBox, FoodTreeFeatureConfig config) {
        int i = this.minTreeHeight + rand.nextInt(3);
        boolean flag = true;
        if (position.getY() >= 1 && position.getY() + i + 1 <= world.getMaxHeight()) {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;
                if (j == position.getY())
                    k = 0;

                if (j >= position.getY() + 1 + i - 2)
                    k = 2;

                BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                        if (j >= 0 && j < world.getMaxHeight()) {
                            if (!isSoil(world, blockpos$mutableblockpos.setPos(l, j, i1)))
                                flag = false;
                        } else
                            flag = false;
                    }
                }
            }

            if (!flag)
                return false;
            else if (isSoil(world, position.down()) && position.getY() < world.getMaxHeight() - i - 1) {
                this.setDirtAt(world, position.down(), position);

                for (int l2 = position.getY() - 3 + i; l2 <= position.getY() + i; ++l2) {
                    int l3 = l2 - (position.getY() + i);
                    int j4 = (int) (1 - l3 / 1.05f);

                    for (int j1 = position.getX() - j4; j1 <= position.getX() + j4; ++j1) {
                        int k1 = j1 - position.getX();

                        for (int l1 = position.getZ() - j4; l1 <= position.getZ() + j4; ++l1) {
                            int i2 = l1 - position.getZ();
                            if (Math.abs(k1) != j4 || Math.abs(i2) != j4 && l3 != 0) {
                                BlockPos blockpos = new BlockPos(j1, l2, l1);
                                if (isAirOrLeaves(world, blockpos) || isSoil(world, blockpos))
                                    this.func_227219_b_(world, rand, blockpos, leavesPos, boundingBox, config);
                            }
                        }
                    }
                }

                for (int i3 = 0; i3 < i; ++i3)
                    if (isAirOrLeaves(world, position.up(i3)) || isSoil(world, position.up(i3)))
                        this.func_227216_a_(world, rand, position.up(i3), logPos, boundingBox, config);

                return true;
            } else
                return false;
        } else
            return false;
    }
}
