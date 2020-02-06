package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.registry.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 6/02/2020
 */
public class TallFoodGrassBlock extends BushBlock implements IGrowable, net.minecraftforge.common.IShearable {

    private static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public TallFoodGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, BlockState state) {
    }

    @Override
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XYZ;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == ModBlocks.CHEESE_GRASS_BLOCK.get() ||
                block == ModBlocks.GRILLED_CHEESE_GRASS_BLOCK.get() ||
                block == ModBlocks.HAM_RAW_GRASS_BLOCK.get() ||
                block == ModBlocks.HAM_COOKED_GRASS_BLOCK.get() ||
                block == Blocks.DIRT;
    }
}
