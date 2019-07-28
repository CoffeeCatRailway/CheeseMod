package coffeecatrailway.cheesemod.items;

import coffeecatrailway.cheesemod.CheeseMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 22/07/2019
 */
public class ToastieFoodItem extends Item {

    private boolean grilled = false;

    public ToastieFoodItem(Food food, int stackSize) {
        super(new Item.Properties().group(CheeseMod.GROUP_ITEMS).food(food).maxStackSize(stackSize));
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        ITextComponent grilledText = new StringTextComponent("Tastes better grilled right..?");
        ITextComponent notGrilledText = new StringTextComponent("What about grilling it?");
        tooltip.add(grilled ? grilledText : notGrilledText);
    }

    public ToastieFoodItem grilled() {
        grilled = true;
        return this;
    }
}
