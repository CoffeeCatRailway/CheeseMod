package coffeecatrailway.cheesemod.common.item;

import coffeecatrailway.cheesemod.common.entity.CheeseBallEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 9/09/2019
 */
public class CheeseBallItem extends Item {

    public CheeseBallItem(Properties properties) {
        super(properties);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.isSneaking()) {
            if (this.isFood()) {
                if (player.canEat(this.getFood().canEatWhenFull())) {
                    player.setActiveHand(hand);
                    return new ActionResult<>(ActionResultType.SUCCESS, stack);
                } else {
                    return new ActionResult<>(ActionResultType.FAIL, stack);
                }
            } else {
                return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
            }
        } else {
            if (!player.abilities.isCreativeMode) {
                stack.shrink(1);
            }

            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4F / (random.nextFloat() * 0.4F + 0.8f));
            if (!world.isRemote) {
                CheeseBallEntity entity = new CheeseBallEntity(world, player);
                entity.func_213884_b(stack);
                entity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 1.5f, 1.0f);
                world.addEntity(entity);
            }

            player.addStat(Stats.ITEM_USED.get(this));
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }
    }
}
