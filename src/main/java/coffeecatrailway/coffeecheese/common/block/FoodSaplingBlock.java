package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

/**
 * @author CoffeeCatRailway
 * Created: 8/02/2020
 */
public class FoodSaplingBlock extends SaplingBlock {

    public FoodSaplingBlock(Tree tree, Properties properties) {
        super(tree, properties);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        Block block = world.getBlockState(pos.down()).getBlock();
        return block == ModBlocks.CHEESE_GRASS_BLOCK.get() ||
                block == ModBlocks.GRILLED_CHEESE_GRASS_BLOCK.get() ||
                block == ModBlocks.HAM_RAW_GRASS_BLOCK.get() ||
                block == ModBlocks.HAM_COOKED_GRASS_BLOCK.get() ||
                super.isValidGround(state, world, pos) || block == Blocks.FARMLAND;
    }
}
