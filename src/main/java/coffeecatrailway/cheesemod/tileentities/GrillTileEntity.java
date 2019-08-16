package coffeecatrailway.cheesemod.tileentities;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.ModConfig;
import coffeecatrailway.cheesemod.blocks.GrillBlock;
import coffeecatrailway.cheesemod.client.gui.container.GrillContainer;
import coffeecatrailway.cheesemod.core.registries.ModRecipeTypes;
import coffeecatrailway.cheesemod.core.registries.ModTileEntityTypes;
import coffeecatrailway.cheesemod.items.crafting.GrillRecipe;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
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
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 8/08/2019
 */
public class GrillTileEntity extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {

    private static final int[] SLOTS_UP = new int[]{0};
    private static final int[] SLOTS_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_HORIZONTAL = new int[]{1};
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    private int burnTime;
    private int recipesUsed;
    private int cookTime;
    private int cookTimeTotal;
    private int oil;
    private final IIntArray data = new IIntArray() {
        public int get(int index) {
            switch (index) {
                case 0:
                    return GrillTileEntity.this.burnTime;
                case 1:
                    return GrillTileEntity.this.recipesUsed;
                case 2:
                    return GrillTileEntity.this.cookTime;
                case 3:
                    return GrillTileEntity.this.cookTimeTotal;
                case 4:
                    return GrillTileEntity.this.oil;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch (index) {
                case 0:
                    GrillTileEntity.this.burnTime = value;
                    break;
                case 1:
                    GrillTileEntity.this.recipesUsed = value;
                    break;
                case 2:
                    GrillTileEntity.this.cookTime = value;
                    break;
                case 3:
                    GrillTileEntity.this.cookTimeTotal = value;
                    break;
                case 4:
                    GrillTileEntity.this.oil = value;
                    break;
            }

        }

        public int size() {
            return 5;
        }
    };
    private final Map<ResourceLocation, Integer> recipeAmounts = Maps.newHashMap();
    private final IRecipeType<GrillRecipe> recipeType;

    public GrillTileEntity() {
        super(ModTileEntityTypes.GRILL);
        this.recipeType = ModRecipeTypes.GRILLING;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + CheeseMod.MOD_ID + ".grill");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new GrillContainer(id, player, this, this.data);
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    public void read(CompoundNBT compound) {
        super.read(compound);
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.items);
        this.burnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.cookTimeTotal = compound.getInt("CookTimeTotal");
        this.oil = compound.getInt("Oil");
        this.recipesUsed = this.getBurnTime(this.items.get(1));
        int i = compound.getShort("RecipesUsedSize");

        for (int j = 0; j < i; ++j) {
            ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
            int k = compound.getInt("RecipeAmount" + j);
            this.recipeAmounts.put(resourcelocation, k);
        }

    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.cookTimeTotal);
        compound.putInt("Oil", this.oil);
        ItemStackHelper.saveAllItems(compound, this.items);
        compound.putShort("RecipesUsedSize", (short) this.recipeAmounts.size());
        int i = 0;

        for (Map.Entry<ResourceLocation, Integer> entry : this.recipeAmounts.entrySet()) {
            compound.putString("RecipeLocation" + i, entry.getKey().toString());
            compound.putInt("RecipeAmount" + i, entry.getValue());
            ++i;
        }

        return compound;
    }

    public void tick() {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning())
            this.burnTime--;

        if (!this.world.isRemote) {
            ItemStack fuelStack = this.items.get(1);
            if (this.isBurning() || !fuelStack.isEmpty() && !this.items.get(0).isEmpty()) {
                IRecipe<?> iRecipe = this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).orElse(null);
                if (!this.isBurning() && this.canSmelt(iRecipe)) {
                    this.burnTime = this.getBurnTime(fuelStack);
                    this.recipesUsed = this.burnTime;
                    if (this.isBurning()) {
                        flag1 = true;
                        if (fuelStack.hasContainerItem())
                            this.items.set(1, fuelStack.getContainerItem());
                        else {
                            if (!fuelStack.isEmpty()) {
                                fuelStack.shrink(1);
                                if (fuelStack.isEmpty())
                                    this.items.set(1, fuelStack.getContainerItem());
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt(iRecipe)) {
                    this.cookTime += ModConfig.MODIFIERS.grillSpeed.get();
                    if (this.cookTime >= this.cookTimeTotal) {
                        this.oil -= this.getOil();
                        if (this.oil < 0)
                            this.oil = 0;
                        this.smeltRecipe(iRecipe);
                        this.cookTime = 0;
                        this.cookTimeTotal = this.getCookTimeTotal();
                        flag1 = true;
                    }
                } else
                    this.cookTime = 0;
            } else if (!this.isBurning() && this.cookTime > 0)
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);

            if (flag != this.isBurning()) {
                flag1 = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(GrillBlock.LIT, this.isBurning()), 3);
            }
        }

        if (flag1)
            this.markDirty();
    }

    private boolean canSmelt(@Nullable IRecipe<?> iRecipe) {
        if (!this.items.get(0).isEmpty() && iRecipe != null && this.oil > 0 && this.oil >= this.getOil()) {
            ItemStack recipeOutStack = iRecipe.getRecipeOutput();
            if (recipeOutStack.isEmpty())
                return false;
            else {
                ItemStack outStack = this.items.get(2);
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
            ItemStack ingredientStack = this.items.get(0);
            ItemStack recipeOutStack = iRecipe.getRecipeOutput();
            ItemStack outStack = this.items.get(2);
            if (outStack.isEmpty())
                this.items.set(2, recipeOutStack.copy());
            else if (outStack.getItem() == recipeOutStack.getItem())
                outStack.grow(recipeOutStack.getCount());

            if (!this.world.isRemote)
                this.setRecipeUsed(iRecipe);

            ingredientStack.shrink(1);
        }
    }

    private int getBurnTime(ItemStack fuelStack) {
        if (fuelStack.isEmpty())
            return 0;
        else {
            Item fuelItem = fuelStack.getItem();
            int ret = fuelStack.getBurnTime();
            return ForgeEventFactory.getItemBurnTime(fuelStack, ret == -1 ? AbstractFurnaceTileEntity.getBurnTimes().getOrDefault(fuelItem, 0) : ret);
        }
    }

    private int getCookTimeTotal() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(GrillRecipe::getCookTime).orElse(200);
    }

    private int getOil() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(GrillRecipe::getOil).orElse(0);
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
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items)
            if (!itemstack.isEmpty())
                return false;

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.items, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.items.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.items.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        if (index == 0 && !flag) {
            this.cookTimeTotal = this.getCookTimeTotal();
            this.cookTime = 0;
            this.markDirty();
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
        if (index == 2)
            return false;
        else if (index != 1)
            return true;
        else {
            ItemStack itemstack = this.items.get(1);
            return AbstractFurnaceTileEntity.isFuel(stack) || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
        }
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
        if (recipe != null)
            this.recipeAmounts.compute(recipe.getId(), (p_214004_0_, p_214004_1_) -> 1 + (p_214004_1_ == null ? 0 : p_214004_1_));
    }

    @Nullable
    @Override
    public IRecipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void onCrafting(PlayerEntity player) {
    }

    public void giveExperience(PlayerEntity player) {
        List<IRecipe<?>> list = Lists.newArrayList();

        for (Map.Entry<ResourceLocation, Integer> entry : this.recipeAmounts.entrySet()) {
            player.world.getRecipeManager().getRecipe(entry.getKey()).ifPresent((iRecipe) -> {
                list.add(iRecipe);
                giveExperience(player, entry.getValue(), ((GrillRecipe) iRecipe).getExperience());
            });
        }

        player.unlockRecipes(list);
        this.recipeAmounts.clear();
    }

    private void giveExperience(PlayerEntity player, int amount, float experience) {
        if (experience == 0.0F) {
            amount = 0;
        } else if (experience < 1.0F) {
            int i = MathHelper.floor((float) amount * experience);
            if (i < MathHelper.ceil((float) amount * experience) && Math.random() < (double) ((float) amount * experience - (float) i)) {
                ++i;
            }

            amount = i;
        }

        while (amount > 0) {
            int j = ExperienceOrbEntity.getXPSplit(amount);
            amount -= j;
            player.world.addEntity(new ExperienceOrbEntity(player.world, player.posX, player.posY + 0.5D, player.posZ + 0.5D, j));
        }

    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for (ItemStack itemstack : this.items)
            helper.accountStack(itemstack);
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
