package coffeecatrailway.coffeecheese.common.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

/**
 * @author CoffeeCatRailway
 * Created: 29/12/2019
 */
public class CraftToolItem extends SwordItem {

    public CraftToolItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        stack.setCount(1);
        stack.setDamage(stack.getDamage() + 1 + random.nextInt(3));

        if (stack.getDamage() > stack.getMaxDamage())
            return ItemStack.EMPTY;
        return stack;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
