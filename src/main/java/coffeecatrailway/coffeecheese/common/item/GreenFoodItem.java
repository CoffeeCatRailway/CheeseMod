package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.ModItemGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Calendar;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 22/07/2019
 */
public class GreenFoodItem extends Item {

    public GreenFoodItem(Food food, int stackSize) {
        super(new Item.Properties().group(ModItemGroups.GROUP_FOODS).food(food).maxStackSize(stackSize));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(new StringTextComponent("Yay to Dr. Seuss for his green eggs and ham!"));
        if (CheeseMod.isDate(Calendar.MARCH, 2)) {
            tooltip.add(new StringTextComponent("And a happy birthday to the great Dr. Seuss!"));
            tooltip.add(new StringTextComponent("Born 2 March 1904, Died 24 September 1991"));
        }
    }
}
