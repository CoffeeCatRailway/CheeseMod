package coffeecatrailway.coffeecheese.common.tileentity;

import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;

/**
 * @author CoffeeCatRailway
 * Created: 10/01/2020
 */
public abstract class ModLockableTileEntity extends LockableTileEntity {

    public ModLockableTileEntity(TileEntityType<?> type) {
        super(type);
    }

    protected void sendUpdates() {
        world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), 2);
        world.markAndNotifyBlock(pos, world.getChunkAt(pos), this.getBlockState(), this.getBlockState(), 3);
        super.markDirty();
    }
}
