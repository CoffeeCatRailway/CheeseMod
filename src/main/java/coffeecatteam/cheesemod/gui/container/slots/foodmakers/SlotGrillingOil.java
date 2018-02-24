package coffeecatteam.cheesemod.gui.container.slots.foodmakers;

import coffeecatteam.cheesemod.gui.container.slots.SlotItems;
import coffeecatteam.cheesemod.init.InitItem;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityGrill;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotGrillingOil extends Slot {

	public SlotGrillingOil(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return (stack.getItem() == InitItem.GRILLING_OIL);
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
}
