package coffeecatrailway.coffeecheese.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 22/01/2020
 */
public class FoodGrassBlock extends Block {

    public FoodGrassBlock(Properties properties) {
        super(properties);
    }
    
    private static boolean hasLight(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = world.getBlockState(blockpos);
        int i = LightEngine.func_215613_a(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(world, blockpos));
        return i < world.getMaxLightLevel();
    }

    private static boolean canSpread(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return hasLight(state, world, pos) && !world.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!worldIn.isRemote) {
            if (!worldIn.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (!hasLight(state, worldIn, pos)) {
                worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
            } else {
                if (worldIn.getLight(pos.up()) >= 9) {
                    BlockState blockstate = this.getDefaultState();

                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && canSpread(blockstate, worldIn, blockpos)) {
                            worldIn.setBlockState(blockpos, blockstate);
                        }
                    }
                }

            }
        }
    }

    @Override
    public boolean isSolid(BlockState state) {
        return true;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
