package coffeecatrailway.cheesemod.client.gui.container;

import coffeecatrailway.cheesemod.core.ModContainerTypes;
import coffeecatrailway.cheesemod.core.ModRecipeTypes;
import coffeecatrailway.cheesemod.item.crafting.GrillRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
public class GrillContainer extends Container {

    private final IInventory inventory;
    private final IIntArray data;
    private final World world;
    private final IRecipeType<GrillRecipe> recipeType;

    public GrillContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new Inventory(3), new IntArray(5));
    }

    public GrillContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray data) {
        super(ModContainerTypes.GRILL, id);
        this.recipeType = ModRecipeTypes.GRILLING;
        assertInventorySize(inventory, inventory.getSizeInventory());
        assertIntArraySize(data, data.size());
        this.inventory = inventory;
        this.data = data;
        this.world = playerInventory.player.world;

        this.addSlot(new Slot(this.inventory, 0, 61, 13));
        this.addSlot(new FuelSlot(this.inventory, 1, 61, 51));
        this.addSlot(new ResultSlot(playerInventory.player, this.inventory, 2, 118, 32));

        for(int i = 0; i < 3; ++i)
            for(int j = 0; j < 9; ++j)
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for(int k = 0; k < 9; ++k)
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));

        this.trackIntArray(this.data);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return this.inventory.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (this.recipeHasStack(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (AbstractFurnaceTileEntity.isFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    private boolean recipeHasStack(ItemStack stack) {
        return this.world.getRecipeManager().getRecipe(this.recipeType, new Inventory(stack), this.world).isPresent();
    }

    @OnlyIn(Dist.CLIENT)
    public int getCookProgressionScaled() {
        int cookTime = this.data.get(2);
        int cookTimeTotal = this.data.get(3);
        return cookTimeTotal != 0 && cookTime != 0 ? cookTime * 24 / cookTimeTotal : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled() {
        int recipesUsed = this.data.get(1);
        if (recipesUsed == 0)
            recipesUsed = 200;

        return this.data.get(0) * 13 / recipesUsed;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.data.get(0) > 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getOil() {
        return this.data.get(4);
    }
}
