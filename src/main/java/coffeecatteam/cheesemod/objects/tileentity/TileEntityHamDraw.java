package coffeecatteam.cheesemod.objects.tileentity;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityHamDraw extends TileEntity {

	private ItemStackHandler inventory = new ItemStackHandler(21);
	private NonNullList<ItemStack> inventoryStacks = NonNullList.<ItemStack>withSize(inventory.getSlots(),
			ItemStack.EMPTY);

	public void setInventoryStacks(NonNullList<ItemStack> inventoryStacks) {
		this.inventoryStacks = inventoryStacks;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		inventory.deserializeNBT(compound.getCompoundTag("HamDrawInventory"));
		ItemStackHelper.loadAllItems(compound, inventoryStacks);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("HamDrawInventory", inventory.serializeNBT());
		ItemStackHelper.saveAllItems(compound, inventoryStacks);
		return super.writeToNBT(compound);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory
				: super.getCapability(capability, facing);
	}
}
