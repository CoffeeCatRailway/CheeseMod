package coffeecatrailway.coffeecheese.common.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

/**
 * @author CoffeeCatRailway
 * Created: 24/07/2020
 */
public class CraftToolItem extends SwordItem {

    public CraftToolItem(IItemTier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties.maxStackSize(1));
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
