package coffeecatteam.cheesemod.gui.container.slots.foodmakers;

import coffeecatteam.cheesemod.objects.tileentity.TileEntityGrill;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFoodMakerFuel extends Slot {

	public SlotFoodMakerFuel(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityGrill.isItemFuel(stack);
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
}
