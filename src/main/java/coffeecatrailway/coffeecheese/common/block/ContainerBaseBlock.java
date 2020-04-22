package coffeecatrailway.coffeecheese.common.block;

import io.github.ocelot.common.BaseBlock;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 22/04/2020
 */
public abstract class ContainerBaseBlock extends BaseBlock {

    public ContainerBaseBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider) tileentity : null;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    abstract TileEntity getTileEntity(BlockState state, IBlockReader world);

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return getTileEntity(state, world);
    }
}
