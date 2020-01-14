package coffeecatrailway.coffeecheese.common.tileentity;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.ModCheeseConfig;
import coffeecatrailway.coffeecheese.client.gui.container.PizzaOvenContainer;
import coffeecatrailway.coffeecheese.common.block.PizzaOvenBlock;
import coffeecatrailway.coffeecheese.common.item.crafting.PizzaOvenRecipe;
import coffeecatrailway.coffeecheese.registry.ModRecipes;
import coffeecatrailway.coffeecheese.registry.ModTileEntities;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 10/01/2020
 */
public class PizzaOvenTileEntity extends ModLockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {

    private static final int[] SLOTS_UP = new int[]{
            0, 1, 2,
            3, 4, 5,
            6, 7, 8
    };
    private static final int[] SLOTS_DOWN = new int[]{10};
    private static final int[] SLOTS_HORIZONTAL = new int[]{9};

    public ItemStackHandler inventory = new ItemStackHandler(11) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            PizzaOvenTileEntity.this.sendUpdates(PizzaOvenTileEntity.this);
        }
    };
    private int burnTime;
    private int recipesUsed;
    private int cookTime;
    private int cookTimeTotal;
    public final IIntArray data = new IIntArray() {
        public int get(int index) {
            switch (index) {
                case 0:
                    return PizzaOvenTileEntity.this.burnTime;
                case 1:
                    return PizzaOvenTileEntity.this.recipesUsed;
                case 2:
                    return PizzaOvenTileEntity.this.cookTime;
                case 3:
                    return PizzaOvenTileEntity.this.cookTimeTotal;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch (index) {
                case 0:
                    PizzaOvenTileEntity.this.burnTime = value;
                    break;
                case 1:
                    PizzaOvenTileEntity.this.recipesUsed = value;
                    break;
                case 2:
                    PizzaOvenTileEntity.this.cookTime = value;
                    break;
                case 3:
                    PizzaOvenTileEntity.this.cookTimeTotal = value;
                    break;
            }

        }

        public int size() {
            return 4;
        }
    };
    private final Map<ResourceLocation, Integer> recipeAmounts = Maps.newHashMap();
    private final IRecipeType<PizzaOvenRecipe> recipeType;

    public PizzaOvenTileEntity() {
        super(ModTileEntities.PIZZA_OVEN.get());
        this.recipeType = ModRecipes.PIZZA_OVEN;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + CheeseMod.MOD_ID + ".pizza_oven");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new PizzaOvenContainer(id, player, this, this.data);
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        inventory.deserializeNBT(compound.getCompound("inventory"));

        this.burnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.cookTimeTotal = compound.getInt("CookTimeTotal");
        this.recipesUsed = PizzaOvenTileEntity.getBurnTime(this.inventory.getStackInSlot(9));
        int i = compound.getShort("RecipesUsedSize");

        for (int j = 0; j < i; ++j) {
            ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
            int k = compound.getInt("RecipeAmount" + j);
            this.recipeAmounts.put(resourcelocation, k);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inventory", inventory.serializeNBT());

        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.cookTimeTotal);
        compound.putShort("RecipesUsedSize", (short) this.recipeAmounts.size());

        int i = 0;
        for (Map.Entry<ResourceLocation, Integer> entry : this.recipeAmounts.entrySet()) {
            compound.putString("RecipeLocation" + i, entry.getKey().toString());
            compound.putInt("RecipeAmount" + i, entry.getValue());
            ++i;
        }

        return super.write(compound);
    }

    public boolean hasItems() {
        for (int i = 0; i < 9; i++)
            if (!this.inventory.getStackInSlot(i).isEmpty())
                return true;
        return false;
    }

    public void tick() {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning()) {
            this.burnTime--;
            this.sendUpdates(PizzaOvenTileEntity.this);
        }

        if (!this.world.isRemote) {
            ItemStack fuelStack = this.inventory.getStackInSlot(9);
            if (this.isBurning() || !fuelStack.isEmpty() && hasItems()) {
                IRecipe<?> iRecipe = this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).orElse(null);
                if (!this.isBurning() && this.canSmelt(iRecipe)) {
                    this.burnTime = PizzaOvenTileEntity.getBurnTime(fuelStack);
                    this.recipesUsed = this.burnTime;
                    if (this.isBurning()) {
                        flag1 = true;
                        if (fuelStack.hasContainerItem()) {
                            this.inventory.setStackInSlot(9, fuelStack.getContainerItem());
                            this.sendUpdates(PizzaOvenTileEntity.this);
                        } else {
                            if (!fuelStack.isEmpty()) {
                                fuelStack.shrink(1);
                                if (fuelStack.isEmpty())
                                    this.inventory.setStackInSlot(9, fuelStack.getContainerItem());
                                this.sendUpdates(PizzaOvenTileEntity.this);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt(iRecipe)) {
                    this.cookTime += ModCheeseConfig.pizzaOvenSpeed.get();
                    if (this.cookTime >= this.cookTimeTotal) {
                        this.smeltRecipe(iRecipe);
                        this.cookTime = 0;
                        this.cookTimeTotal = this.getCookTimeTotal();
                        flag1 = true;
                        this.sendUpdates(PizzaOvenTileEntity.this);
                    }
                } else {
                    this.cookTime = 0;
                    this.sendUpdates(PizzaOvenTileEntity.this);
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
                this.sendUpdates(PizzaOvenTileEntity.this);
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(PizzaOvenBlock.LIT, this.isBurning()), 3);
                this.sendUpdates(PizzaOvenTileEntity.this);
            }
        }

        if (flag1)
            this.sendUpdates(PizzaOvenTileEntity.this);
    }

    private boolean canSmelt(@Nullable IRecipe<?> iRecipe) {
        if (iRecipe != null && hasItems()) {
            ItemStack recipeOutStack = iRecipe.getRecipeOutput();
            if (recipeOutStack.isEmpty())
                return false;
            else {
                ItemStack outStack = this.inventory.getStackInSlot(10);
                if (outStack.isEmpty())
                    return true;
                else if (!outStack.isItemEqual(recipeOutStack))
                    return false;
                else if (outStack.getCount() + recipeOutStack.getCount() <= this.getInventoryStackLimit() && outStack.getCount() + recipeOutStack.getCount() <= outStack.getMaxStackSize())
                    return true;
                else
                    return outStack.getCount() + recipeOutStack.getCount() <= outStack.getMaxStackSize();
            }
        } else
            return false;
    }

    private void smeltRecipe(@Nullable IRecipe<?> iRecipe) {
        if (iRecipe != null && this.canSmelt(iRecipe)) {
            ItemStack[] ingredients = new ItemStack[9];
            for (int i = 0; i < 9; i++)
                ingredients[i] = this.inventory.getStackInSlot(i);

            ItemStack recipeOutStack = iRecipe.getRecipeOutput();
            ItemStack outStack = this.inventory.getStackInSlot(10);
            if (outStack.isEmpty())
                this.inventory.setStackInSlot(10, recipeOutStack.copy());
            else if (outStack.getItem() == recipeOutStack.getItem())
                outStack.grow(recipeOutStack.getCount());

            if (!this.world.isRemote)
                this.setRecipeUsed(iRecipe);

            for (ItemStack ingredient : ingredients)
                if (!ingredient.isEmpty())
                    ingredient.shrink(1);
        }
    }

    public static int getBurnTime(ItemStack fuelStack) {
        if (fuelStack.isEmpty())
            return 0;
        else {
            Item fuelItem = fuelStack.getItem();
            int ret = fuelStack.getBurnTime();
            return ForgeEventFactory.getItemBurnTime(fuelStack, ret == -1 ? AbstractFurnaceTileEntity.getBurnTimes().getOrDefault(fuelItem, 0) : ret);
        }
    }

    private int getCookTimeTotal() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(PizzaOvenRecipe::getCookTime).orElse(200);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN)
            return SLOTS_DOWN;
        else
            return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, @Nullable Direction direction) {
        return this.isItemValidForSlot(index, stack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            Item item = stack.getItem();
            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
                return false;
        }

        return true;
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < getSizeInventory(); i++)
            if (!this.inventory.getStackInSlot(i).isEmpty())
                return false;

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inventory.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return index >= 0 && index < getSizeInventory() && !this.inventory.getStackInSlot(index).isEmpty() && count > 0 ? this.inventory.getStackInSlot(index).split(count) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (index >= 0 && index < getSizeInventory()) {
            ItemStack stack = this.inventory.getStackInSlot(index);
            this.inventory.setStackInSlot(index, ItemStack.EMPTY);
            return stack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.inventory.getStackInSlot(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.inventory.setStackInSlot(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        if (index == 0 && !flag) {
            this.cookTimeTotal = this.getCookTimeTotal();
            this.cookTime = 0;
            this.sendUpdates(PizzaOvenTileEntity.this);
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this)
            return false;
        else
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == 10)
            return false;
        else if (index != 9)
            return true;
        else {
            ItemStack itemstack = this.inventory.getStackInSlot(9);
            return AbstractFurnaceTileEntity.isFuel(stack) || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < getSizeInventory(); i++)
            this.inventory.setStackInSlot(i, ItemStack.EMPTY);
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
        if (recipe != null)
            this.recipeAmounts.compute(recipe.getId(), (location, integer) -> 1 + (integer == null ? 0 : integer));
    }

    @Nullable
    @Override
    public IRecipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void giveExperience(PlayerEntity player) {
        List<IRecipe<?>> list = Lists.newArrayList();

        for (Map.Entry<ResourceLocation, Integer> entry : this.recipeAmounts.entrySet()) {
            player.world.getRecipeManager().getRecipe(entry.getKey()).ifPresent((iRecipe) -> {
                list.add(iRecipe);
                giveExperience(player, entry.getValue(), ((PizzaOvenRecipe) iRecipe).getExperience());
            });
        }

        player.unlockRecipes(list);
        this.recipeAmounts.clear();
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for (int i = 0; i < getSizeInventory(); i++)
            helper.accountStack(this.inventory.getStackInSlot(i));
    }

    LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.removed && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void remove() {
        super.remove();
        for (int x = 0; x < handlers.length; x++)
            handlers[x].invalidate();
    }
}
