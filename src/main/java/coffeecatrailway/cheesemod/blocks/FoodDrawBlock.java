package coffeecatrailway.cheesemod.blocks;

import coffeecatrailway.cheesemod.tileentities.FoodDrawTileEntity;
import com.google.common.collect.Lists;
import coffeecatrailway.cheesemod.util.VoxelShapeHelper;
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
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 2/08/2019
 */
public class FoodDrawBlock extends ContainerBlock implements IWaterLoggable {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape[] SHAPE_DETAILS = new VoxelShape[]{
            Block.makeCuboidShape(1.0D, 4.0D, 4.0D, 2.0D, 12.0D, 12.0D),
            Block.makeCuboidShape(15.0D, 4.0D, 4.0D, 14.0D, 12.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 15.0D, 12.0D, 12.0D, 14.0D),

            Block.makeCuboidShape(4.0D, 2.0D, 1.0D, 12.0D, 7.0D, 2.0D),
            Block.makeCuboidShape(5.0D, 3.0D, 0.0D, 11.0D, 6.0D, 1.0D),

            Block.makeCuboidShape(4.0D, 9.0D, 1.0D, 12.0D, 13.0D, 2.0D),
            Block.makeCuboidShape(5.0D, 10.0D, 0.0D, 11.0D, 12.0D, 1.0D)
    };
    private static final VoxelShape SHAPE_BASE = VoxelShapeHelper.combineAll(Lists.newArrayList(
            Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 1.0D, 15.0D),
            Block.makeCuboidShape(2.0D, 1.0D, 2.0D, 14.0D, 14.0D, 14.0D),

            Block.makeCuboidShape(1.0D, 1.0D, 1.0D, 3.0D, 14.0D, 3.0D),
            Block.makeCuboidShape(1.0D, 1.0D, 13.0D, 3.0D, 14.0D, 15.0D),
            Block.makeCuboidShape(13.0D, 1.0D, 13.0D, 15.0D, 14.0D, 15.0D),
            Block.makeCuboidShape(13.0D, 1.0D, 1.0D, 15.0D, 14.0D, 3.0D)));

    public FoodDrawBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        VoxelShape detailsRot = SHAPE_DETAILS[0];
        for (VoxelShape shape : SHAPE_DETAILS) {
            int index;
            switch (state.get(FACING)) {
                default:
                case NORTH:
                    index = 3;
                    break;
                case EAST:
                    index = 0;
                    break;
                case SOUTH:
                    index = 1;
                    break;
                case WEST:
                    index = 2;
                    break;
            }
            detailsRot = VoxelShapeHelper.combineAll(Lists.newArrayList(detailsRot, VoxelShapeHelper.getRotatedShapes(shape)[index]));
        }
        return VoxelShapes.or(SHAPE_BASE, detailsRot);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
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
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof FoodDrawTileEntity)
                ((FoodDrawTileEntity) tile).setCustomName(stack.getDisplayName());
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
        else {
            INamedContainerProvider provider = this.getContainer(state, world, pos);
            if (provider != null)
                player.openContainer(provider);
            return true;
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new FoodDrawTileEntity();
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
}
