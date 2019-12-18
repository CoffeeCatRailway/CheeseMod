package coffeecatrailway.coffeecheese.common.fluid;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.core.ModBlocks;
import coffeecatrailway.coffeecheese.core.ModFluids;
import coffeecatrailway.coffeecheese.core.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 30/08/2019
 */
public abstract class MeltedCheeseFluid extends FlowingFluid {

    @Override
    public Fluid getStillFluid() {
        return ModFluids.MELTED_CHEESE_SOURCE;
    }

    @Override
    public Fluid getFlowingFluid() {
        return ModFluids.MELTED_CHEESE_FLOWING;
    }

    @Override
    public Item getFilledBucket() {
        return ModItems.MELTED_CHEESE_BUCKET;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        return ModBlocks.MELTED_CHEESE.getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(CheeseMod.getLocation("block/melted_cheese_still"), CheeseMod.getLocation("block/melted_cheese_flowing"))
                .viscosity(1500).density(1500).temperature(500).build(ModFluids.MELTED_CHEESE_SOURCE);
    }

    @Override
    protected boolean canSourcesMultiply() {
        return false;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(World world, BlockPos pos, IFluidState state, Random random) {
        BlockPos blockpos = pos.up();
        if (world.getBlockState(blockpos).isAir() && !world.getBlockState(blockpos).isOpaqueCube(world, blockpos)) {
            if (random.nextInt(100) == 0) {
                double d0 = (double) ((float) pos.getX() + random.nextFloat());
                double d1 = (double) (pos.getY() + 1);
                double d2 = (double) ((float) pos.getZ() + random.nextFloat());
                world.playSound(d0, d1, d2, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2f, 0.9F + random.nextFloat() * 0.15f, false);
            }

            if (random.nextInt(200) == 0) {
                world.playSound((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2f, 0.9F + random.nextFloat() * 0.15f, false);
            }
        }
    }

    @Override
    protected void beforeReplacingBlock(IWorld world, BlockPos pos, BlockState state) {
        TileEntity tileentity = state.getBlock().hasTileEntity() ? world.getTileEntity(pos) : null;
        Block.spawnDrops(state, world.getWorld(), pos, tileentity);
    }

    @Override
    protected int getSlopeFindDistance(IWorldReader world) {
        return world.getDimension().doesWaterVaporize() ? 4 : 2;
    }

    @Override
    protected int getLevelDecreasePerBlock(IWorldReader world) {
        return world.getDimension().doesWaterVaporize() ? 1 : 2;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    protected boolean func_215665_a(IFluidState state, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.isIn(ModFluids.MELTED_CHEESE_TAG);
    }

    @Override
    public int getTickRate(IWorldReader reader) {
        return 10;
    }

    @Override
    protected float getExplosionResistance() {
        return 50.0f;
    }

    @Override
    public boolean isEquivalentTo(Fluid fluid) {
        return fluid == getStillFluid() || fluid == getFlowingFluid();
    }

    public static class Flowing extends MeltedCheeseFluid {

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        @Override
        public int getLevel(IFluidState state) {
            return state.get(LEVEL_1_8);
        }

        @Override
        public boolean isSource(IFluidState state) {
            return false;
        }
    }

    public static class Source extends MeltedCheeseFluid {

        @Override
        public int getLevel(IFluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(IFluidState state) {
            return true;
        }
    }
}
