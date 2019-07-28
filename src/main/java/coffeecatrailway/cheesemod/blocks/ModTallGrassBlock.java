package coffeecatrailway.cheesemod.blocks;

import coffeecatrailway.cheesemod.core.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * @author CoffeeCatRailway
 * Created: 28/07/2019
 */
public class ModTallGrassBlock extends TallGrassBlock {

    public ModTallGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == ModBlocks.CHEESE_GRASS_BLOCK ||
                block == ModBlocks.GRILLED_CHEESE_GRASS_BLOCK ||
                block == ModBlocks.HAM_RAW_GRASS_BLOCK ||
                block == ModBlocks.HAM_COOKED_GRASS_BLOCK ||
                super.isValidGround(state, worldIn, pos);
    }
}
