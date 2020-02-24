package coffeecatrailway.coffeecheese.common.block;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 19/02/2020
 */
public class HamButtonBlock extends AbstractButtonBlock {

    public HamButtonBlock(Properties properties) {
        super(false, properties);
    }

    @Override
    protected SoundEvent getSoundEvent(boolean on) {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

    @Override
    protected void playSound(@Nullable PlayerEntity player, IWorld world, BlockPos pos, boolean on) {
        world.playSound(on ? player : null, pos, this.getSoundEvent(on), SoundCategory.BLOCKS, 1f, (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * (on ? .2f : .25f) + 1.0F);
    }
}
