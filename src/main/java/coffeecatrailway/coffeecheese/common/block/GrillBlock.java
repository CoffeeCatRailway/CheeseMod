package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.common.tileentity.GrillTileEntity;
import coffeecatrailway.coffeecheese.registry.ModFluids;
import coffeecatrailway.coffeecheese.registry.ModItems;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 8/08/2019
 */
public class GrillBlock extends ContainerBaseBlock implements IWaterLoggable {

    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final BooleanProperty HAS_CATCHER = BooleanProperty.create("has_catcher");

    private static final VoxelShape[] SHAPES = createShapes();

    private static VoxelShape[] createShapes() {
        VoxelShape[] shapes = new VoxelShape[8];

        for (int i = 0; i < shapes.length; i++) {
            int index = i % 4;
            boolean hasCatcher = i >= 4;
            Direction dir = Direction.byHorizontalIndex(index);

            VoxelShapeHelper.Builder builder = new VoxelShapeHelper.Builder().append(
                    // Controls
                    VoxelShapeHelper.makeCuboidShape(16.0D, 12.25D, 2.5D, 17.0D, 14.25D, 13.5D, dir),

                    // Legs
                    VoxelShapeHelper.makeCuboidShape(1d, 0d, 1d, 2d, 11d, 2d, dir),
                    VoxelShapeHelper.makeCuboidShape(14d, 0d, 1d, 15d, 11d, 2d, dir),
                    VoxelShapeHelper.makeCuboidShape(14d, 0d, 14d, 15d, 11d, 15d, dir),
                    VoxelShapeHelper.makeCuboidShape(1d, 0d, 14d, 2d, 11d, 15d, dir),

                    // Base
                    VoxelShapeHelper.makeCuboidShape(0d, 11d, 0d, 16d, 16d, 16d, dir)
            );

            // Braces/Catcher
            if (hasCatcher)
                builder.append(VoxelShapeHelper.makeCuboidShape(1d, 5d, 1d, 15d, 8d, 15d, dir));
            else {
                builder.append(
                        VoxelShapeHelper.makeCuboidShape(15d, 5d, 2d, 14d, 6d, 14d, dir),
                        VoxelShapeHelper.makeCuboidShape(1d, 5d, 2d, 2d, 6d, 14d, dir)
                );
            }
            shapes[i] = builder.rotate(Direction.WEST).build();
        }

        return shapes;
    }

    public GrillBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(LIT, Boolean.FALSE).with(WATERLOGGED, Boolean.FALSE).with(HAS_CATCHER, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(HORIZONTAL_FACING).getHorizontalIndex() + (state.get(HAS_CATCHER) ? 4 : 0)];
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
        builder.add(HORIZONTAL_FACING, LIT, WATERLOGGED, HAS_CATCHER);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof GrillTileEntity)
                ((GrillTileEntity) tile).setCustomName(stack.getDisplayName());
        }
    }

    private void playEmptyBucketSound(World world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isRemote && hand == Hand.MAIN_HAND && world.getTileEntity(pos) instanceof GrillTileEntity) {
            ItemStack stack = player.getHeldItem(hand);
            GrillTileEntity tile = (GrillTileEntity) world.getTileEntity(pos);
            if (stack.getItem() == ModFluids.OIL.get().getFilledBucket()) {
                int oil = tile.getTankA().getFluidAmount();
                if (oil <= FluidAttributes.BUCKET_VOLUME) {
                    if (!player.abilities.isCreativeMode) {
                        stack.shrink(1);
                        player.addItemStackToInventory(new ItemStack(Items.BUCKET));
                    }

                    tile.getTankA().fill(new FluidStack(ModFluids.OIL.get(), FluidAttributes.BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE);
                    playEmptyBucketSound(world, pos);
                    return ActionResultType.SUCCESS;
                }
            } else if (stack.getItem() == Items.BUCKET && state.get(HAS_CATCHER)) {
                int oil = tile.getTankB().getFluidAmount();
                if (oil >= FluidAttributes.BUCKET_VOLUME) {
                    if (!player.abilities.isCreativeMode) {
                        stack.shrink(1);
                        player.addItemStackToInventory(new ItemStack(ModFluids.OIL.get().getFilledBucket()));
                    }

                    tile.getTankB().drain(new FluidStack(ModFluids.OIL.get(), FluidAttributes.BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE);
                    playEmptyBucketSound(world, pos);
                    return ActionResultType.SUCCESS;
                }
            } else if (stack.getItem() == ModItems.OIL_CATCHER.get() && !state.get(HAS_CATCHER)) {
                world.setBlockState(pos, state.with(HAS_CATCHER, Boolean.TRUE));
                if (!player.abilities.isCreativeMode)
                    stack.shrink(1);
            } else {
                INamedContainerProvider provider = this.getContainer(state, world, pos);
                if (provider != null) {
                    player.openContainer(provider);
                    player.addStat(ModStats.INTERACT_WITH_GRILL);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public TileEntity getTileEntity(BlockState state, IBlockReader world) {
        return new GrillTileEntity();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (state.get(LIT)) {
            double x = pos.getX();
            double y = (double) pos.getY() + 1.0d;
            double z = pos.getZ();
            if (rand.nextDouble() < 0.1D)
                world.playSound(x, y, z, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);

            double xo = rand.nextDouble() * 0.9D + 0.1D;
            double zo = rand.nextDouble() * 0.9D + 0.1D;
            world.addParticle(ParticleTypes.SMOKE, x + xo, y, z + zo, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, x + xo, y, z + zo, 0.0D, 0.0D, 0.0D);
        }
    }
}
