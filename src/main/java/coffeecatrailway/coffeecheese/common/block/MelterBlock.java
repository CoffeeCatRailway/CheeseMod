package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.common.tileentity.GrillTileEntity;
import coffeecatrailway.coffeecheese.common.tileentity.MelterTileEntity;
import coffeecatrailway.coffeecheese.registry.ModStats;
import io.github.ocelot.common.VoxelShapeHelper;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 8/08/2019
 */
public class MelterBlock extends ContainerBaseBlock implements IWaterLoggable {

    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    private static final VoxelShape[] SHAPES = createShapes();

    private static VoxelShape[] createShapes() {
        VoxelShape[] shapes = new VoxelShape[4];

        for (int i = 0; i < shapes.length; i++) {
            Direction dir = Direction.byHorizontalIndex(i).getOpposite();

            VoxelShapeHelper.Builder builder = new VoxelShapeHelper.Builder().append(
                    VoxelShapeHelper.makeCuboidShape(0.0d, 3.0d, 0.0d, 16.0d, 7.0d, 16.0d, dir),
                    VoxelShapeHelper.makeCuboidShape(2.5d, 0.0d, 2.5d, 13.5d, 3.0d, 13.5d, dir),

                    VoxelShapeHelper.makeCuboidShape(1.5d, 0.0d, 1.5d, 2.5d, 3.1D, 2.5d, dir),
                    VoxelShapeHelper.makeCuboidShape(14.5d, 0.0d, 1.5d, 13.5d, 3.1D, 2.5d, dir),
                    VoxelShapeHelper.makeCuboidShape(14.5d, 0.0d, 14.5d, 13.5d, 3.1D, 13.5d, dir),
                    VoxelShapeHelper.makeCuboidShape(1.5d, 0.0d, 14.5d, 2.5d, 3.1D, 13.5d, dir),

                    VoxelShapeHelper.makeCuboidShape(16.0d, 4.6d, 6.0d, 17.0d, 6.0d, 10.0d, dir)
            );

            shapes[i] = builder.rotate(Direction.EAST).build();
        }

        return shapes;
    }

    public MelterBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(LIT, Boolean.FALSE).with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(HORIZONTAL_FACING).getHorizontalIndex()];
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public int getLightValue(BlockState state) {
        return state.get(LIT) ? super.getLightValue(state) : 0;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, LIT, WATERLOGGED);
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
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isRemote && hand == Hand.MAIN_HAND) {
            ItemStack stack = player.getHeldItem(hand);
            if (stack.getItem() == Items.BUCKET) {
                if (world.getTileEntity(pos) instanceof MelterTileEntity) {
                    MelterTileEntity tile = (MelterTileEntity) world.getTileEntity(pos);
                    int fluidAmount = tile.getTankA().getFluidAmount();
                    if (fluidAmount >= FluidAttributes.BUCKET_VOLUME) {
                        if (!player.abilities.isCreativeMode) {
                            player.inventory.addItemStackToInventory(new ItemStack(tile.getTankA().getFluid().getFluid().getFilledBucket()));
                            stack.shrink(1);
                        }

                        tile.getTankA().drain(FluidAttributes.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
                        world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        return ActionResultType.SUCCESS;
                    }
                }
            } else {
                INamedContainerProvider provider = this.getContainer(state, world, pos);
                if (provider != null) {
                    player.openContainer(provider);
                    player.addStat(ModStats.INTERACT_WITH_GRILL);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public TileEntity getTileEntity(BlockState state, IBlockReader world) {
        return new MelterTileEntity();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (state.get(LIT)) {
            double x = pos.getX();
            double y = (double) pos.getY() + 0.2d;
            double z = pos.getZ();
            if (rand.nextDouble() < 0.1D)
                world.playSound(x, y, z, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);

            double xo = rand.nextDouble() * 0.9D + 0.1D;
            double zo = rand.nextDouble() * 0.9D + 0.1D;
            world.addParticle(ParticleTypes.SMOKE, x + xo, y, z + zo, 0.0d, 0.0d, 0.0d);
            world.addParticle(ParticleTypes.FLAME, x + xo, y, z + zo, 0.0d, 0.0d, 0.0d);
        }
    }
}
