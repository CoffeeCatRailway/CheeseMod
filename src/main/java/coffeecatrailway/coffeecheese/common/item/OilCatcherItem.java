package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.integration.registrate.CheeseLang;
import coffeecatrailway.coffeecheese.registry.CheeseItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2020
 */
public class OilCatcherItem extends Item {

    public OilCatcherItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent(CheeseLang.getItemTranslationKey(CheeseItems.OIL_CATCHER) + ".info"));
    }
}
