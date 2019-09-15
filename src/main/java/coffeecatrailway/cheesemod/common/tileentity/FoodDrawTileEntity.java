package coffeecatrailway.cheesemod.common.tileentity;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.client.gui.container.FoodDrawContainer;
import coffeecatrailway.cheesemod.common.block.FoodDrawBlock;
import coffeecatrailway.cheesemod.core.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 3/08/2019
 */
public class FoodDrawTileEntity extends LockableLootTileEntity implements ITickableTileEntity {

    private NonNullList<ItemStack> inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
    private int numPlayersUsing;
    private int ticksSinceSync;

    public FoodDrawTileEntity() {
        super(ModTileEntityTypes.FOOD_DRAW);
    }

    @Override
    public int getSizeInventory() {
        return 21;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.inventory)
            if (!itemstack.isEmpty())
                return false;
        return true;
    }

    @Override
    protected ITextComponent getDefaultName() {
        Block block = this.world.getBlockState(this.pos).getBlock();
        return new TranslationTextComponent("container." + CheeseMod.MOD_ID + "." + block.getRegistryName().getPath());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound))
            ItemStackHelper.loadAllItems(compound, this.inventory);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound))
            ItemStackHelper.saveAllItems(compound, this.inventory);
        return compound;
    }

    @Override
    public void tick() {
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        ++this.ticksSinceSync;
        this.numPlayersUsing = getPlayersUsing(this.world, this, this.ticksSinceSync, i, j, k, this.numPlayersUsing);
    }

    private int getPlayersUsing(World world, LockableTileEntity tileEntity, int ticks, int x, int y, int z, int players) {
        if (!world.isRemote && players != 0 && (ticks + x + y + z) % 200 == 0)
            players = getPlayersUsing(world, tileEntity, x, y, z);

        return players;
    }

    private int getPlayersUsing(World world, LockableTileEntity tileEntity, int x, int y, int z) {
        int i = 0;
        for (PlayerEntity playerentity : world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB((double) ((float) x - 5.0f), (double) ((float) y - 5.0f), (double) ((float) z - 5.0f), (double) ((float) (x + 1) + 5.0f), (double) ((float) (y + 1) + 5.0f), (double) ((float) (z + 1) + 5.0f)))) {
            if (playerentity.openContainer instanceof FoodDrawContainer) {
                IInventory iinventory = ((FoodDrawContainer) playerentity.openContainer).getFoodInventory();
                if (iinventory == tileEntity)
                    ++i;
            }
        }
        return i;
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else
            return super.receiveClientEvent(id, type);
    }

    @Override
    public void openInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0)
                this.numPlayersUsing = 0;

            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    protected void onOpenOrClose() {
        Block block = this.getBlockState().getBlock();
        if (block instanceof FoodDrawBlock) {
            this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, block);
            super.markDirty();

            if (this.numPlayersUsing > 0)
                this.playSound(SoundEvents.BLOCK_BARREL_OPEN);

            if (this.numPlayersUsing == 0)
                this.playSound(SoundEvents.BLOCK_BARREL_CLOSE);
        }
    }

    private void playSound(SoundEvent soundIn) {
        double d0 = (double) this.pos.getX() + 0.5D;
        double d1 = (double) this.pos.getY() + 0.5D;
        double d2 = (double) this.pos.getZ() + 0.5D;
        this.world.playSound(null, d0, d1, d2, soundIn, SoundCategory.BLOCKS, 0.5f, this.world.rand.nextFloat() * 0.1F + 0.9f);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.inventory = items;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return FoodDrawContainer.create(id, player, this);
    }
}
