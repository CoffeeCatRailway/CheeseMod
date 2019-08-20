package coffeecatrailway.cheesemod.item;

import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
public class ModToolItem extends ToolItem {

    public ModToolItem(float damage, float speed, IItemTier tier, Properties properties) {
        super(damage, speed, tier, Sets.newHashSet(Blocks.DIRT), properties);
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

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return material != Material.PLANTS && material != Material.TALL_PLANTS ? super.getDestroySpeed(stack, state) : this.efficiency;
    }
}
