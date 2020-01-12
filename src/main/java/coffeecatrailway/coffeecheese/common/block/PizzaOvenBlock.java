package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.common.tileentity.PizzaOvenTileEntity;
import coffeecatrailway.coffeecheese.registry.ModStats;
import coffeecatrailway.coffeecheese.util.VoxelShapeHelper;
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

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 10/01/2020
 */
public class PizzaOvenBlock extends ContainerBlock implements IWaterLoggable {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE_STATIONARY = VoxelShapeHelper.combineAll(Lists.newArrayList(
            // legs
            Block.makeCuboidShape(.5d, 0d, .5d, 2.5d, 2d, 2.5d),
            Block.makeCuboidShape(13.5d, 0d, .5d, 15.5d, 2d, 2.5d),
            Block.makeCuboidShape(13.5d, 0d, 13.5d, 15.5d, 2d, 15.5d),
            Block.makeCuboidShape(.5d, 0d, 13.5d, 2.5d, 2d, 15.5d),

            //base
            Block.makeCuboidShape(0d, 2d, 0d, 16d, 9d, 16d),
            Block.makeCuboidShape(1d, 9d, 1d, 15d, 10d, 15d),
            Block.makeCuboidShape(3d, 10d, 3d, 13d, 11d, 13d)
    ));

    private static final VoxelShape[] SHAPES_ROTATED = new VoxelShape[]{
            // chimney
            Block.makeCuboidShape(6d, 11d, 3d, 10d, 12d, 12d),
            Block.makeCuboidShape(6d, 12d, 3d, 10d, 14d, 7d),
            Block.makeCuboidShape(6.5d, 14d, 3.5d, 9.5d, 16d, 6.5d),
            Block.makeCuboidShape(5.5d, 16d, 2.5d, 10.5d, 16.5d, 7.5d),

            // logs
            Block.makeCuboidShape(.5d, 0d, 2.8d, 3.5d, 1d, 12.8d),
            Block.makeCuboidShape(4d, 0d, 1d, 7d, 1d, 14d),
            Block.makeCuboidShape(7.5d, 0d, 1.5d, 10.5d, 1d, 14.5d),
            Block.makeCuboidShape(11d, 0d, 3d, 14d, 1d, 13d)
    };

    public PizzaOvenBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, Boolean.FALSE).with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        List<VoxelShape> shapes = new ArrayList<>();
        shapes.add(SHAPE_STATIONARY);

        for (VoxelShape shape : SHAPES_ROTATED) {
            VoxelShape[] rotated = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(shape, Direction.SOUTH));
            shapes.add(rotated[state.get(FACING).getHorizontalIndex()]);
        }
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
            if (tile instanceof PizzaOvenTileEntity)
                ((PizzaOvenTileEntity) tile).setCustomName(stack.getDisplayName());
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
        if (!world.isRemote) {
            INamedContainerProvider provider = this.getContainer(state, world, pos);
            if (provider != null) {
                player.openContainer(provider);
                player.addStat(ModStats.INTERACT_WITH_PIZZA_OVEN);
            }
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new PizzaOvenTileEntity();
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
            double d0 = pos.getX();
            double d1 = (double) pos.getY() + 0.1d;
            double d2 = pos.getZ();
            if (rand.nextDouble() < 0.1d)
                world.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);

            double d3 = rand.nextDouble() * 0.8d + 0.2d;
            double d4 = rand.nextDouble() * 0.8d + 0.2d;
            world.addParticle(ParticleTypes.SMOKE, d0 + d3, d1, d2 + d4, 0.0d, 0.0d, 0.0d);
            world.addParticle(ParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0d, 0.0d, 0.0d);

            double d5;
            double d6;
            switch (state.get(FACING)) {
                default:
                case NORTH:
                    d5 = 0.5d;
                    d6 = 0.3d;
                    break;
                case SOUTH:
                    d5 = 0.5d;
                    d6 = 0.7d;
                    break;
                case EAST:
                    d5 = 0.7d;
                    d6 = 0.5d;
                    break;
                case WEST:
                    d5 = 0.3d;
                    d6 = 0.5d;
                    break;
            }
            world.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + 1d, d2 + d6, 0.0d, 0.0d, 0.0d);
        }
    }
}
