package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.entity.CheeseBallEntity;
import coffeecatrailway.coffeecheese.integration.registrate.CheeseLang;
import coffeecatrailway.coffeecheese.registry.CheeseItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2020
 */
public class CheeseBallItem extends Item {

    public CheeseBallItem(Properties properties) {
        super(properties);
    }

    @Override
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
                return new ActionResult<>(ActionResultType.PASS, stack);
            }
        } else {
            if (!player.abilities.isCreativeMode)
                stack.shrink(1);

            world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, .5f, .4f / (random.nextFloat() * .4f + .8f));
            if (!world.isRemote) {
                CheeseBallEntity entity = new CheeseBallEntity(world, player);
                entity.setItem(stack);
                entity.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0f, 1.5f, 1.0f);
                world.addEntity(entity);
            }

            player.addStat(Stats.ITEM_USED.get(this));
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(new TranslationTextComponent(CheeseLang.getItemTranslationKey(CheeseItems.CHEESE_BALL) + ".info"));
    }
}
