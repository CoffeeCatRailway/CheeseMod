package coffeecatrailway.cheesemod.client.gui.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.FurnaceFuelSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;

/**
 * @author CoffeeCatRailway
 * Created: 11/08/2019
 */
public class FuelSlot extends FurnaceFuelSlot {

    public FuelSlot(IInventory inventory, int index, int xPosition, int yPosition) {
        super(null, inventory, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return AbstractFurnaceTileEntity.isFuel(stack) || isBucket(stack);
    }
}
