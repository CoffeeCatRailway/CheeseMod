package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.entity.CheeseBallEntity;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

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
        if (player.isCrouching()) {
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

            world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4F / (random.nextFloat() * 0.4F + 0.8f));
            if (!world.isRemote) {
                CheeseBallEntity entity = new CheeseBallEntity(world, player);
                entity.setItem(stack);
                entity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 1.5f, 1.0f);
                world.addEntity(entity);
            }

            player.addStat(Stats.ITEM_USED.get(this));
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(new StringTextComponent(I18n.format("item." + CheeseMod.MOD_ID + ".cheese_ball.info")));
    }
}
