package coffeecatrailway.coffeecheese.client.gui.container;

import coffeecatrailway.coffeecheese.registry.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author CoffeeCatRailway
 * Created: 4/08/2019
 */
public class FoodDrawContainer extends Container {

    private static final int rows = 3, columns = 7;
    public static final int size = columns * rows;
    public IInventory foodInventory;

    public FoodDrawContainer(int id, PlayerInventory playerInventory, IInventory foodInventory) {
        super(ModContainers.FOOD_DRAW.get(), id);
        assertInventorySize(foodInventory, size);
        this.foodInventory = foodInventory;
        foodInventory.openInventory(playerInventory.player);
        int i = (rows - 4) * 18;

        for (int j = 0; j < rows; ++j)
            for (int k = 0; k < columns; ++k)
                this.addSlot(new Slot(foodInventory, k + j * columns, 8 + (k + 1) * 18, 17 + j * 18));

        for (int l = 0; l < 3; ++l)
            for (int j1 = 0; j1 < 9; ++j1)
                this.addSlot(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 102 + l * 18 + i));

        for (int i1 = 0; i1 < 9; ++i1)
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 160 + i));
    }

    public static FoodDrawContainer create(int id, PlayerInventory player) {
        return create(id, player, new Inventory(size));
    }

    public static FoodDrawContainer create(int id, PlayerInventory player, IInventory inventory) {
        return new FoodDrawContainer(id, player, inventory);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return this.foodInventory.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < size) {
                if (!this.mergeItemStack(itemstack1, size, this.inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!this.mergeItemStack(itemstack1, 0, size, false))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(PlayerEntity player) {
        this.foodInventory.closeInventory(player);
        super.onContainerClosed(player);
    }

    public IInventory getFoodInventory() {
        return foodInventory;
    }
}
