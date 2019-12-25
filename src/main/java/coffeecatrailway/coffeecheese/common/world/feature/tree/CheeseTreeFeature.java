package coffeecatrailway.coffeecheese.common.world.feature.tree;

import coffeecatrailway.coffeecheese.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 27/07/2019
 */
public class CheeseTreeFeature extends TreeFeature<NoFeatureConfig> {

    private final int minTreeHeight;
    private final BlockState trunk;
    private final BlockState leaf;

    public CheeseTreeFeature(boolean grilled, Function<Dynamic<?>, ? extends NoFeatureConfig> dynamicFunction, boolean doBlockNofityOnPlace) {
        super(dynamicFunction, doBlockNofityOnPlace);
        if (grilled) {
            this.minTreeHeight = 6;
            this.trunk = ModBlocks.GRILLED_CHEESE_LOG.get().getDefaultState();
            this.leaf = ModBlocks.GRILLED_CHEESE_LEAVES.get().getDefaultState();
        } else {
            this.minTreeHeight = 4;
            this.trunk = ModBlocks.CHEESE_LOG.get().getDefaultState();
            this.leaf = ModBlocks.CHEESE_LEAVES.get().getDefaultState();
        }
    }

    @Override
    protected boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader world, Random rand, BlockPos position, MutableBoundingBox mutableBoundingBox) {
        int i = this.minTreeHeight + rand.nextInt(3);
        boolean flag = true;
        if (position.getY() >= 1 && position.getY() + i + 1 <= world.getMaxHeight()) {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;
                if (j == position.getY()) {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                        if (j >= 0 && j < world.getMaxHeight()) {
                            if (!func_214587_a(world, blockpos$mutableblockpos.setPos(l, j, i1))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else if (isSoil(world, position.down()) && position.getY() < world.getMaxHeight() - i - 1) {
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
                                if (isAirOrLeaves(world, blockpos) || isSoil(world, blockpos)) {
                                    this.setLogState(changedBlocks, world, blockpos, this.leaf, mutableBoundingBox);
                                }
                            }
                        }
                    }
                }

                for (int i3 = 0; i3 < i; ++i3) {
                    if (isAirOrLeaves(world, position.up(i3)) || isSoil(world, position.up(i3))) {
                        this.setLogState(changedBlocks, world, position.up(i3), this.trunk, mutableBoundingBox);
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
