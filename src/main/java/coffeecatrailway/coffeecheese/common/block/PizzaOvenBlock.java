package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.common.tileentity.PizzaOvenTileEntity;
import coffeecatrailway.coffeecheese.registry.ModStats;
import io.github.ocelot.common.VoxelShapeHelper;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
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

import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 10/01/2020
 */
public class PizzaOvenBlock extends ContainerBaseBlock implements IWaterLoggable {

    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    private static final VoxelShape[] SHAPES = createShapes();

    private static VoxelShape[] createShapes() {
        VoxelShape[] shapes = new VoxelShape[4];

        for (int i = 0; i < shapes.length; i++) {
            Direction dir = Direction.byHorizontalIndex(i).getOpposite();

            VoxelShapeHelper.Builder builder = new VoxelShapeHelper.Builder().append(
                    VoxelShapeHelper.makeCuboidShape(0.0d, 2.0d, 0.0d, 16.0d, 9.0d, 16.0d, dir),
                    VoxelShapeHelper.makeCuboidShape(1.0d, 9.0d, 1.0d, 15.0d, 11.0d, 15.0d, dir),

                    VoxelShapeHelper.makeCuboidShape(5.5d, 11.0d, 2.5d, 10.5d, 16.5d, 7.5d, dir),

                    VoxelShapeHelper.makeCuboidShape(0.5d, 0.0d, 0.5d, 2.5d, 2.0d, 2.5d, dir),
                    VoxelShapeHelper.makeCuboidShape(13.5d, 0.0d, 0.5d, 15.5d, 2.0d, 2.5d, dir),
                    VoxelShapeHelper.makeCuboidShape(13.5d, 0.0d, 13.5d, 15.5d, 2.0d, 15.5d, dir),
                    VoxelShapeHelper.makeCuboidShape(0.5d, 0.0d, 13.5d, 2.5d, 2.0d, 15.5d, dir),

                    VoxelShapeHelper.makeCuboidShape(2.5d, 0.0d, 2.5d, 13.5d, 2.0d, 13.5d, dir)
            );

            shapes[i] = builder.build();
        }

        return shapes;
    }

    public PizzaOvenBlock(Properties properties) {
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
            if (tile instanceof PizzaOvenTileEntity)
                ((PizzaOvenTileEntity) tile).setCustomName(stack.getDisplayName());
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isRemote) {
            INamedContainerProvider provider = this.getContainer(state, world, pos);
            if (provider != null) {
                player.openContainer(provider);
                player.addStat(ModStats.INTERACT_WITH_PIZZA_OVEN);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public TileEntity getTileEntity(BlockState state, IBlockReader world) {
        return new PizzaOvenTileEntity();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (state.get(LIT)) {
            double x = pos.getX();
            double y = (double) pos.getY() + 0.1d;
            double z = pos.getZ();
            if (rand.nextDouble() < 0.1d)
                world.playSound(x, y, z, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);

            double xo1 = rand.nextDouble() * 0.8d + 0.2d;
            double zo1 = rand.nextDouble() * 0.8d + 0.2d;
            world.addParticle(ParticleTypes.SMOKE, x + xo1, y, z + zo1, 0.0d, 0.0d, 0.0d);
            world.addParticle(ParticleTypes.FLAME, x + xo1, y, z + zo1, 0.0d, 0.0d, 0.0d);

            double xo2;
            double zo2;
            switch (state.get(HORIZONTAL_FACING)) {
                default:
                case NORTH:
                    xo2 = 0.5d;
                    zo2 = 0.3d;
                    break;
                case SOUTH:
                    xo2 = 0.5d;
                    zo2 = 0.7d;
                    break;
                case EAST:
                    xo2 = 0.7d;
                    zo2 = 0.5d;
                    break;
                case WEST:
                    xo2 = 0.3d;
                    zo2 = 0.5d;
                    break;
            }
            world.addParticle(ParticleTypes.SMOKE, x + xo2, y + 1d, z + zo2, 0.0d, 0.0d, 0.0d);
        }
    }
}
