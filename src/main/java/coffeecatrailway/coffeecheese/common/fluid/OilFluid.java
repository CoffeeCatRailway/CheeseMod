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
import net.minecraft.particles.ParticleTypes;
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
public abstract class OilFluid extends FlowingFluid {

    @Override
    public Fluid getStillFluid() {
        return ModFluids.OIL_SOURCE;
    }

    @Override
    public Fluid getFlowingFluid() {
        return ModFluids.OIL_FLOWING;
    }

    @Override
    public Item getFilledBucket() {
        return ModItems.OIL_BUCKET;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        return ModBlocks.OIL.getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(CheeseMod.getLocation("block/oil_still"), CheeseMod.getLocation("block/oil_flowing"))
                .viscosity(1500).density(1500).overlay(CheeseMod.getLocation("block/oil_overlay")).build(ModFluids.OIL_SOURCE);
    }

    @Override
    protected boolean canSourcesMultiply() {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(World world, BlockPos pos, IFluidState state, Random random) {
        if (!state.isSource() && !state.get(FALLING)) {
            if (random.nextInt(64) == 0)
                world.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, random.nextFloat() * 0.25f + 0.75f, random.nextFloat() + 0.5f, false);
        } else if (random.nextInt(10) == 0)
            world.addParticle(ParticleTypes.UNDERWATER, (double) ((float) pos.getX() + random.nextFloat()), (double) ((float) pos.getY() + random.nextFloat()), (double) ((float) pos.getZ() + random.nextFloat()), 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected void beforeReplacingBlock(IWorld world, BlockPos pos, BlockState state) {
        TileEntity tileentity = state.getBlock().hasTileEntity() ? world.getTileEntity(pos) : null;
        Block.spawnDrops(state, world.getWorld(), pos, tileentity);
    }

    @Override
    protected int getSlopeFindDistance(IWorldReader world) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(IWorldReader world) {
        return 1;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    protected boolean func_215665_a(IFluidState state, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.isIn(ModFluids.OIL_TAG);
    }

    @Override
    public int getTickRate(IWorldReader reader) {
        return 5;
    }

    @Override
    protected float getExplosionResistance() {
        return 50.0f;
    }

    @Override
    public boolean isEquivalentTo(Fluid fluid) {
        return fluid == getStillFluid() || fluid == getFlowingFluid();
    }

    public static class Flowing extends OilFluid {

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

    public static class Source extends OilFluid {

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
