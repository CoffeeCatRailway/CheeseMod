package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.integration.registrate.CheeseTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

/**
 * @author CoffeeCatRailway
 * Created: 29/07/2020
 */
public class FoodSaplingBlock extends SaplingBlock {

    public FoodSaplingBlock(Tree tree, Properties properties) {
        super(tree, properties);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        Block block = world.getBlockState(pos.down()).getBlock();
        return CheeseTags.Blocks.FOOD_GRASS_BLOCKS.contains(block) || super.isValidGround(state, world, pos) || block == Blocks.FARMLAND;
    }
}
