package coffeecatrailway.coffeecheese.client.gui.container;

import coffeecatrailway.coffeecheese.common.item.crafting.MelterRecipe;
import coffeecatrailway.coffeecheese.common.tileentity.MelterTileEntity;
import coffeecatrailway.coffeecheese.registry.ModContainers;
import coffeecatrailway.coffeecheese.registry.ModRecipes;
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
 * Created: 4/09/2019
 */
public class MelterContainer extends Container {

    private final IInventory inventory;
    private final IIntArray data;
    private final World world;
    private final IRecipeType<MelterRecipe> recipeType;

    public MelterContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new Inventory(2), new IntArray(MelterTileEntity.DATA_ARRAY_SIZE));
    }

    public MelterContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray data) {
        super(ModContainers.MELTER.get(), id);
        assertInventorySize(inventory, inventory.getSizeInventory());
        assertIntArraySize(data, data.size());
        this.inventory = inventory;
        this.data = data;
        this.world = playerInventory.player.world;
        recipeType = ModRecipes.MELTING;

        this.addSlot(new Slot(this.inventory, 0, 44, 13));
        this.addSlot(new FuelSlot(this.inventory, 1, 44, 51));

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (int k = 0; k < 9; ++k)
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
            if (index == 1) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
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
                } else if (index >= 2 && index < 29) {
                    if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 29 && index < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
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
    public int getFluid() {
        return this.data.get(5);
    }

    @OnlyIn(Dist.CLIENT)
    public int getFluidAmount() {
        return this.data.get(4);
    }

    @OnlyIn(Dist.CLIENT)
    public int getFluidColor() {
        return this.data.get(6);
    }
}
