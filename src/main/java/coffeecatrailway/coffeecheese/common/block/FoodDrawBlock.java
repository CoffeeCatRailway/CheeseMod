package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.common.tileentity.FoodDrawTileEntity;
import io.github.ocelot.common.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 2/08/2019
 */
public class FoodDrawBlock extends ContainerBaseBlock implements IWaterLoggable {

    private static final VoxelShape[] SHAPES = createShapes();

    private static VoxelShape[] createShapes() {
        VoxelShape[] shapes = new VoxelShape[4];

        for (int i = 0; i < shapes.length; i++) {
            Direction dir = Direction.byHorizontalIndex(i).getOpposite();

            VoxelShapeHelper.Builder builder = new VoxelShapeHelper.Builder().append(
                    VoxelShapeHelper.makeCuboidShape(0d, 14d, 0d, 16d, 16d, 16d, dir),
                    VoxelShapeHelper.makeCuboidShape(1d, 0d, 1d, 15d, 14d, 15d, dir),

                    VoxelShapeHelper.makeCuboidShape(5d, 3d, 0d, 11d, 6d, 1d, dir),
                    VoxelShapeHelper.makeCuboidShape(5d, 10d, 0d, 11d, 12d, 1d, dir)
            );

            shapes[i] = builder.build();
        }

        return shapes;
    }

    private IFoodStat stat;

    public FoodDrawBlock(Properties properties, IFoodStat stat) {
        super(properties);
        this.stat = stat;
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, Boolean.FALSE));
    }

    public interface IFoodStat {
        ResourceLocation getStat();
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
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, WATERLOGGED);
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
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (world.getTileEntity(pos) instanceof FoodDrawTileEntity) {
            if (!world.isRemote) {
                INamedContainerProvider provider = this.getContainer(state, world, pos);
                if (provider != null) {
                    player.openContainer(provider);
                    player.addStat(this.stat.getStat());
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public TileEntity getTileEntity(BlockState state, IBlockReader world) {
        return new FoodDrawTileEntity();
    }
}
