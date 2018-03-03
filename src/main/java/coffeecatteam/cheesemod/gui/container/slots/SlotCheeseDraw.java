package coffeecatteam.cheesemod.gui.container.slots;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotCheeseDraw extends SlotItemHandler {

	public SlotCheeseDraw(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		String itemName = stack.getDisplayName().toString().toLowerCase();
		return (itemName.contains("cheese"));
	}
}
