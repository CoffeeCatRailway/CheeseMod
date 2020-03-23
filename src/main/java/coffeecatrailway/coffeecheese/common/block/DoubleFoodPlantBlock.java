package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

/**
 * @author CoffeeCatRailway
 * Created: 7/02/2020
 */
public class DoubleFoodPlantBlock extends DoublePlantBlock {

    public DoubleFoodPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos.down());
        Block block = blockstate.getBlock();
        return block == ModBlocks.CHEESE_GRASS_BLOCK.get() ||
                block == ModBlocks.GRILLED_CHEESE_GRASS_BLOCK.get() ||
                block == ModBlocks.HAM_RAW_GRASS_BLOCK.get() ||
                block == ModBlocks.HAM_COOKED_GRASS_BLOCK.get() ||
                super.isValidGround(state, world, pos) || block == Blocks.FARMLAND || (block == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER);
    }
}
