package coffeecatteam.cheesemod.gui.container;

import coffeecatteam.cheesemod.crafting.foodmakers.CrackerMaking;
import coffeecatteam.cheesemod.gui.container.slots.foodmakers.SlotFoodMakerFuel;
import coffeecatteam.cheesemod.gui.container.slots.foodmakers.SlotFoodMakerOutput;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityCrackerMaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCrackerMaker extends Container {
	private final TileEntityCrackerMaker tileentity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;

	public ContainerCrackerMaker(InventoryPlayer player, TileEntityCrackerMaker tileentity) {
		this.tileentity = tileentity;
		this.addSlotToContainer(new Slot(tileentity, 0, 44, 15));
		this.addSlotToContainer(new Slot(tileentity, 1, 62, 15));
		this.addSlotToContainer(new Slot(tileentity, 2, 44, 33));
		this.addSlotToContainer(new Slot(tileentity, 3, 62, 33));
		this.addSlotToContainer(new SlotFoodMakerFuel(tileentity, 4, 85, 61));
		this.addSlotToContainer(new SlotFoodMakerOutput(player.player, tileentity, 5, 114, 23));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlotToContainer(new Slot(player, k, 8 + k * 18, 142));
		}
	}

	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileentity);
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = (IContainerListener) this.listeners.get(i);

			if (this.cookTime != this.tileentity.getField(2))
				listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if (this.burnTime != this.tileentity.getField(0))
				listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if (this.currentBurnTime != this.tileentity.getField(1))
				listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if (this.totalCookTime != this.tileentity.getField(3))
				listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
		}

		this.cookTime = this.tileentity.getField(2);
		this.burnTime = this.tileentity.getField(0);
		this.currentBurnTime = this.tileentity.getField(1);
		this.totalCookTime = this.tileentity.getField(3);
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.tileentity.setField(id, data);
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileentity.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if (index == 3) {
				if (!this.mergeItemStack(stack1, 4, 40, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			} else if (index != 2 && index != 1 && index != 0) {
				Slot slot1 = (Slot) this.inventorySlots.get(index + 1);
				Slot slot2 = (Slot) this.inventorySlots.get(index + 2);
				Slot slot3 = (Slot) this.inventorySlots.get(index + 3);

				if (!CrackerMaking.INSTANCE.getCrackerMakingResult(stack1, slot1.getStack(), slot2.getStack(), slot3.getStack()).isEmpty()) {
					if (!this.mergeItemStack(stack1, 0, 2, false)) {
						return ItemStack.EMPTY;
					} else if (TileEntityCrackerMaker.isItemFuel(stack1)) {
						if (!this.mergeItemStack(stack1, 2, 3, false))
							return ItemStack.EMPTY;
					} else if (TileEntityCrackerMaker.isItemFuel(stack1)) {
						if (!this.mergeItemStack(stack1, 2, 3, false))
							return ItemStack.EMPTY;
					} else if (TileEntityCrackerMaker.isItemFuel(stack1)) {
						if (!this.mergeItemStack(stack1, 2, 3, false))
							return ItemStack.EMPTY;
					} else if (index >= 4 && index < 31) {
						if (!this.mergeItemStack(stack1, 31, 40, false))
							return ItemStack.EMPTY;
					} else if (index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.mergeItemStack(stack1, 4, 40, false)) {
				return ItemStack.EMPTY;
			}
			if (stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();

			}
			if (stack1.getCount() == stack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
}
