package coffeecatrailway.coffeecheese.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

/**
 * @author CoffeeCatRailway
 * Help from Bagu_Chan (https://github.com/pentantan) & Andromander (https://github.com/Andromander)
 * Created: 25/07/2020
 */
public class MagicFoodStickItem extends Item {

    public MagicFoodStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
//        if (CheeseTags.Blocks.FOOD_BLOCKS.contains(context.getWorld().getBlockState(context.getPos()).getBlock())) {
//            if (CheeseBlocks.FOOD_PORTAL.get().trySpawnPortal(context.getWorld(), context.getPos().offset(context.getFace()))) {
//                context.getWorld().playSound(context.getPlayer(), context.getPos().offset(context.getFace()), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1f, random.nextFloat() * .4f + .8f);
//                if (!context.getPlayer().isCreative())
//                    context.getItem().damageItem(1, context.getPlayer(), (entity) -> entity.sendBreakAnimation(context.getHand()));
//                return ActionResultType.SUCCESS;
//            }
//        }
        return ActionResultType.FAIL;
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
