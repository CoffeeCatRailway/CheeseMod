package coffeecatrailway.cheesemod.common.block;

import coffeecatrailway.cheesemod.core.ModStats;
import coffeecatrailway.cheesemod.common.tileentity.GrillTileEntity;
import coffeecatrailway.cheesemod.common.tileentity.MelterTileEntity;
import coffeecatrailway.cheesemod.util.VoxelShapeHelper;
import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 8/08/2019
 */
public class MelterBlock extends ContainerBlock implements IWaterLoggable {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE_BASE = VoxelShapeHelper.combineAll(Lists.newArrayList(
            /// Stands ///
            Block.makeCuboidShape(1.5D, 0.0D, 1.5D, 2.5D, 3.1D, 2.5D),
            Block.makeCuboidShape(14.5D, 0.0D, 1.5D, 13.5D, 3.1D, 2.5D),
            Block.makeCuboidShape(14.5D, 0.0D, 14.5D, 13.5D, 3.1D, 13.5D),
            Block.makeCuboidShape(1.5D, 0.0D, 14.5D, 2.5D, 3.1D, 13.5D),

            /// Top ///
            Block.makeCuboidShape(1.0D, 3.1D, 1.0D, 15.0D, 4.1D, 15.0D),
            Block.makeCuboidShape(0.0D, 4.1D, 1.0D, 1.0D, 7.1D, 15.0D),
            Block.makeCuboidShape(15.0D, 4.1D, 1.0D, 16.0D, 7.1D, 15.0D),
            Block.makeCuboidShape(1.0D, 4.1D, 0.0D, 15.0D, 7.1D, 1.0D),
            Block.makeCuboidShape(1.0D, 4.1D, 15.0D, 15.0D, 7.1D, 16.0D)
    ));

    private static final VoxelShape SHAPE_LOG = Block.makeCuboidShape(0.5D, 0.0D, 6.0D, 15.5D, 2.0D, 9.0D);
    private static final VoxelShape SHAPE_CONTROLS = Block.makeCuboidShape(16.0D, 4.6D, 6.0D, 17.0D, 6.0D, 10.0D);

    public MelterBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, Boolean.FALSE).with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        List<VoxelShape> shapes = new ArrayList<>();
        shapes.add(SHAPE_BASE);

        Direction defaultDir = Direction.EAST;
        VoxelShape[] log = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(SHAPE_LOG, defaultDir));
        VoxelShape[] controls = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(SHAPE_CONTROLS, defaultDir));

        Direction facing = state.get(FACING);
        shapes.add(log[facing.getHorizontalIndex()]);
        shapes.add(controls[facing.getHorizontalIndex()]);
        return VoxelShapeHelper.combineAll(shapes);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int getLightValue(BlockState state) {
        return state.get(LIT) ? super.getLightValue(state) : 0;
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
        if (state.get(WATERLOGGED))
            world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        return super.updatePostPlacement(state, facing, facingState, world, pos, facingPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
    }

    @Override
    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT, WATERLOGGED);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof GrillTileEntity)
                ((GrillTileEntity) tile).setCustomName(stack.getDisplayName());
        }
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof IInventory) {
                InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
                world.updateComparatorOutputLevel(pos, this);
            }

            super.onReplaced(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (world.isRemote)
            return true;
        else if (player.getHeldItem(hand).getItem() == Items.BUCKET && hand == Hand.MAIN_HAND) {
            if (world.getTileEntity(pos) instanceof MelterTileEntity) {
                MelterTileEntity tile = (MelterTileEntity) world.getTileEntity(pos);
                int fluidAmount = tile.getTank().getFluidAmount();
                if (fluidAmount >= FluidAttributes.BUCKET_VOLUME) {
                    if (!player.abilities.isCreativeMode) {
                        player.inventory.addItemStackToInventory(new ItemStack(tile.getTank().getFluid().getFluid().getFilledBucket()));
                        player.getHeldItem(hand).shrink(1);
                    }

                    tile.getTank().drain(FluidAttributes.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return true;
                }
            }
        } else {
            INamedContainerProvider provider = this.getContainer(state, world, pos);
            if (provider != null) {
                player.openContainer(provider);
                player.addStat(ModStats.INTERACT_WITH_GRILL);
            }
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new MelterTileEntity();
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof IInventory)
            return Container.calcRedstoneFromInventory((IInventory) tile);
        return super.getComparatorInputOverride(state, world, pos);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (state.get(LIT)) {
            double d0 = (double) pos.getX();
            double d1 = (double) pos.getY() + 0.2D;
            double d2 = (double) pos.getZ();
            if (rand.nextDouble() < 0.1D)
                world.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);

            double d3 = rand.nextDouble() * 0.9D + 0.1D;
            double d4 = rand.nextDouble() * 0.9D + 0.1D;
            world.addParticle(ParticleTypes.SMOKE, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
        }
    }
}
