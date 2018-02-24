package coffeecatteam.cheesemod.gui.container.slots;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotHamDraw extends SlotItemHandler {

	public SlotHamDraw(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		Item[] items = SlotItems.hamItems;

		for (int i = 0; i < items.length; i++) {
			if (stack.getItem() == items[i]) {
				return true;
			}
		}
		return false;
	}
}
