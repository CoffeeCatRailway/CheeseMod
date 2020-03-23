package coffeecatrailway.coffeecheese.common.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 10/01/2020
 */
public abstract class ModLockableTileEntity extends LockableTileEntity {

    public ModLockableTileEntity(TileEntityType<?> type) {
        super(type);
    }

    protected void sendUpdates(TileEntity tile) {
        super.markDirty();
        if (tile.getWorld() != null) {
            BlockState state = tile.getWorld().getBlockState(tile.getPos());
            tile.getWorld().notifyBlockUpdate(tile.getPos(), state, state, 8);
        }
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        read(pkt.getNbtCompound());
    }

    abstract public void giveExperience(PlayerEntity player);

    protected void giveExperience(PlayerEntity player, int amount, float experience) {
        if (experience == 0.0f) {
            amount = 0;
        } else if (experience < 1.0f) {
            int i = MathHelper.floor((float) amount * experience);
            if (i < MathHelper.ceil((float) amount * experience) && Math.random() < (double) ((float) amount * experience - (float) i)) {
                ++i;
            }

            amount = i;
        }

        while (amount > 0) {
            int j = ExperienceOrbEntity.getXPSplit(amount);
            amount -= j;
            player.world.addEntity(new ExperienceOrbEntity(player.world, player.getPosX(), player.getPosY() + 0.5D, player.getPosZ() + 0.5D, j));
        }
    }
}
