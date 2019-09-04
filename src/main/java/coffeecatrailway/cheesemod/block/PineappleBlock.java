package coffeecatrailway.cheesemod.block;

import coffeecatrailway.cheesemod.core.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

/**
 * @author CoffeeCatRailway
 * Created: 23/07/2019
 */
public class PineappleBlock extends CropsBlock {

    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 7.0D, 12.0D),

            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 11.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 11.0D, 12.0D),

            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 13.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 13.0D, 12.0D),

            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D),

            Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D)
    };

    public PineappleBlock(Properties builder) {
        super(builder.zeroHardnessAndResistance());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(this.getAgeProperty())];
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ModItems.PINEAPPLE_PLANT;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader reader, BlockPos pos) {
        return super.isValidGround(state, reader, pos) || state.getBlock() instanceof GrassBlock;
    }
}
