package coffeecatrailway.cheesemod.common.tileentity;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.ModConfig;
import coffeecatrailway.cheesemod.client.gui.container.MelterContainer;
import coffeecatrailway.cheesemod.common.block.MelterBlock;
import coffeecatrailway.cheesemod.common.item.crafting.MelterRecipe;
import coffeecatrailway.cheesemod.core.ModRecipeTypes;
import coffeecatrailway.cheesemod.core.ModTileEntityTypes;
import com.google.common.collect.Maps;
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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 3/09/2019
 */
public class MelterTileEntity extends LockableTileFluidHandler implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {

    private static final int[] SLOTS_UP = new int[]{0};
    private static final int[] SLOTS_HORIZONTAL = new int[]{1};
    public static final int FLUID_CAPTACITY = FluidAttributes.BUCKET_VOLUME * 10;
    public static final int DATA_ARRAY_SIZE = 7;

    private NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    private int burnTime;
    private int recipesUsed;
    private int cookTime;
    private int cookTimeTotal;
    private final IIntArray data = new IIntArray() {
        public int get(int index) {
            switch (index) {
                case 0:
                    return MelterTileEntity.this.burnTime;
                case 1:
                    return MelterTileEntity.this.recipesUsed;
                case 2:
                    return MelterTileEntity.this.cookTime;
                case 3:
                    return MelterTileEntity.this.cookTimeTotal;
                case 4:
                    return MelterTileEntity.this.tank.getFluidAmount();
                case 5:
                    return Registry.FLUID.getId(MelterTileEntity.this.tank.getFluid().getFluid());
                case 6:
                    return MelterTileEntity.this.tank.getFluid().getFluid().getAttributes().getColor(MelterTileEntity.this.world, MelterTileEntity.this.getPos());
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch (index) {
                case 0:
                    MelterTileEntity.this.burnTime = value;
                    break;
                case 1:
                    MelterTileEntity.this.recipesUsed = value;
                    break;
                case 2:
                    MelterTileEntity.this.cookTime = value;
                    break;
                case 3:
                    MelterTileEntity.this.cookTimeTotal = value;
                    break;
                case 4:
                    MelterTileEntity.this.tank.setFluid(new FluidStack(MelterTileEntity.this.tank.getFluid().getFluid(), value));
                    break;
                case 5:
                    MelterTileEntity.this.tank.setFluid(new FluidStack(Registry.FLUID.getByValue(value), 1));
                    break;
                case 6:
                    break;
            }

        }

        public int size() {
            return DATA_ARRAY_SIZE;
        }
    };
    private final Map<ResourceLocation, Integer> recipeAmounts = Maps.newHashMap();
    private final IRecipeType<MelterRecipe> recipeType;

    public MelterTileEntity() {
        super(ModTileEntityTypes.MELTER, FLUID_CAPTACITY);
        recipeType = ModRecipeTypes.MELTING;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + CheeseMod.MOD_ID + ".melter");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new MelterContainer(id, player, this, this.data);
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventory);
        this.burnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.cookTimeTotal = compound.getInt("CookTimeTotal");
        this.recipesUsed = GrillTileEntity.getBurnTime(this.inventory.get(1));
        int i = compound.getShort("RecipesUsedSize");

        for (int j = 0; j < i; ++j) {
            ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
            int k = compound.getInt("RecipeAmount" + j);
            this.recipeAmounts.put(resourcelocation, k);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("BurnTime", this.burnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.cookTimeTotal);
        ItemStackHelper.saveAllItems(compound, this.inventory);
        compound.putShort("RecipesUsedSize", (short) this.recipeAmounts.size());
        int i = 0;

        for (Map.Entry<ResourceLocation, Integer> entry : this.recipeAmounts.entrySet()) {
            compound.putString("RecipeLocation" + i, entry.getKey().toString());
            compound.putInt("RecipeAmount" + i, entry.getValue());
            ++i;
        }

        return compound;
    }

    @Override
    public void tick() {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning()) {
            this.burnTime--;
            this.sendUpdates();
        }

        if (!this.world.isRemote) {
            ItemStack fuelStack = this.inventory.get(1);
            if (this.isBurning() || !fuelStack.isEmpty() && !this.inventory.get(0).isEmpty()) {
                IRecipe<?> iRecipe = this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).orElse(null);
                if (!this.isBurning() && this.canSmelt(iRecipe)) {
                    this.burnTime = GrillTileEntity.getBurnTime(fuelStack);
                    this.recipesUsed = this.burnTime;
                    if (this.isBurning()) {
                        flag1 = true;
                        if (fuelStack.hasContainerItem())
                            this.inventory.set(1, fuelStack.getContainerItem());
                        else {
                            if (!fuelStack.isEmpty()) {
                                fuelStack.shrink(1);
                                if (fuelStack.isEmpty())
                                    this.inventory.set(1, fuelStack.getContainerItem());
                            }
                        }
                    }
                    this.sendUpdates();
                }

                if (this.isBurning() && this.canSmelt(iRecipe)) {
                    this.cookTime += ModConfig.MODIFIERS.melterSpeed.get();
                    if (this.cookTime >= this.cookTimeTotal) {
                        this.smeltRecipe(iRecipe);
                        this.cookTime = 0;
                        this.cookTimeTotal = this.getCookTimeTotal();
                        flag1 = true;
                        this.sendUpdates();
                    }
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
                this.sendUpdates();
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(MelterBlock.LIT, this.isBurning()), 3);
                super.sendUpdates();
            }
        }

        if (flag1)
            this.sendUpdates();
    }

    private boolean canSmelt(@Nullable IRecipe<?> iRecipe) {
        if (!(iRecipe instanceof MelterRecipe))
            return false;

        if (!this.inventory.get(0).isEmpty() && this.tank.getFluidAmount() < this.tank.getCapacity() && (this.tank.getCapacity() - this.tank.getFluidAmount()) >= this.getRecipeResult().getAmount()) {
            MelterRecipe recipe = (MelterRecipe) iRecipe;
            FluidStack outStack = recipe.getResult();
            if (outStack.isEmpty())
                return false;
            else {
                if (this.tank.isEmpty())
                    return true;
                else if (!outStack.isFluidEqual(this.tank.getFluid()))
                    return false;
                else
                    return outStack.getAmount() + this.tank.getFluidAmount() <= this.tank.getCapacity();
            }
        } else
            return false;
    }

    private void smeltRecipe(@Nullable IRecipe<?> iRecipe) {
        if (!(iRecipe instanceof MelterRecipe))
            return;

        if (this.canSmelt(iRecipe)) {
            MelterRecipe recipe = (MelterRecipe) iRecipe;
            ItemStack ingredientStack = this.inventory.get(0);
            FluidStack recipeOutStack = recipe.getResult();
            FluidStack outStack = this.tank.getFluid();
            if (outStack.isEmpty())
                this.tank.setFluid(recipeOutStack.copy());
            else if (outStack.getFluid() == recipeOutStack.getFluid())
                this.tank.fill(recipeOutStack, IFluidHandler.FluidAction.EXECUTE);

            if (!this.world.isRemote)
                this.setRecipeUsed(recipe);

            ingredientStack.shrink(1);
        }
    }

    private int getCookTimeTotal() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(MelterRecipe::getCookTime).orElse(200);
    }

    private FluidStack getRecipeResult() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(MelterRecipe::getResult).orElse(FluidStack.EMPTY);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
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
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.inventory)
            if (!itemstack.isEmpty())
                return false;

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.inventory.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.inventory.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        if (index == 0 && !flag) {
            this.cookTimeTotal = this.getCookTimeTotal();
            this.cookTime = 0;
            this.sendUpdates();
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
        if (index != 1)
            return true;
        else {
            ItemStack itemstack = this.inventory.get(1);
            return AbstractFurnaceTileEntity.isFuel(stack) || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
        }
    }

    @Override
    public void clear() {
        this.inventory.clear();
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
    public void fillStackedContents(RecipeItemHelper helper) {
        for (ItemStack itemstack : this.inventory)
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

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        super.write(nbt);
        return new SUpdateTileEntityPacket(getPos(), 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(pkt.getNbtCompound());
    }
}
