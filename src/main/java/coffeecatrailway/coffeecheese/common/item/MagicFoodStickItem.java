package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.common.ModTags;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

/**
 * @author CoffeeCatRailway - Bagu_Chan https://github.com/pentantan & Andromander https://github.com/Andromander
 * Created: 19/01/2020
 */
public class MagicFoodStickItem extends Item {

    public MagicFoodStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (ModTags.Blocks.FOOD_BLOCKS.contains(context.getWorld().getBlockState(context.getPos()).getBlock())) {
            if (ModBlocks.FOOD_PORTAL.get().trySpawnPortal(context.getWorld(), context.getPos().offset(context.getFace()))) {
                context.getWorld().playSound(context.getPlayer(), context.getPos().offset(context.getFace()), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                if (!context.getPlayer().isCreative())
                    context.getItem().shrink(1);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
