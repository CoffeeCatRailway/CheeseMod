package coffeecatrailway.coffeecheese.common.tileentity;

import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.MathHelper;

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
            player.world.addEntity(new ExperienceOrbEntity(player.world, player.posX, player.posY + 0.5D, player.posZ + 0.5D, j));
        }
    }
}
