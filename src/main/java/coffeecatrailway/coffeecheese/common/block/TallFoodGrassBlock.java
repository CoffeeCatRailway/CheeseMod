package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.integration.registrate.CheeseTags;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IForgeShearable;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 6/02/2020
 */
public class TallFoodGrassBlock extends BushBlock implements IGrowable, IForgeShearable {

    private static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    private final Supplier<Supplier<DoubleFoodPlantBlock>> doublePlantBlock;

    public TallFoodGrassBlock(Properties properties, Supplier<Supplier<DoubleFoodPlantBlock>> doublePlantBlock) {
        super(properties);
        this.doublePlantBlock = doublePlantBlock;
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
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        if (this.doublePlantBlock.get().get().getDefaultState().isValidPosition(world, pos) && world.isAirBlock(pos.up()))
            this.doublePlantBlock.get().get().placeAt(world, pos, 2);
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XYZ;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        Block block = world.getBlockState(pos.down()).getBlock();
        return CheeseTags.Blocks.FOOD_GRASS_BLOCKS.contains(block) || super.isValidGround(state, world, pos) || block == Blocks.FARMLAND;
    }
}
