package coffeecatteam.cheesemod.objects.tileentity;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.config.Config;
import coffeecatteam.cheesemod.crafting.foodmakers.CrackerMaking;
import coffeecatteam.cheesemod.objects.blocks.BlockCrackerMaker;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityGrill.SetSpeedMultiplier;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCrackerMaker extends TileEntity implements IInventory, ITickable {
	
	private ItemStackHandler inventory = new ItemStackHandler(6);
	private NonNullList<ItemStack> inventoryStacks = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
	private String customName;

	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime;
	private static int speedMultiplier;
	private static int tick = 0;

	static {
		Config.load("cheesecore");
		setSpeedMultiplier(Config.getCrackerMakerSpeedMultiplier());

		MinecraftForge.EVENT_BUS.register(new SetSpeedMultiplier());
	}

	@EventBusSubscriber(modid = Reference.MODID)
	public static class SetSpeedMultiplier {

		@SubscribeEvent(priority = EventPriority.HIGH)
		public void scheduler(ServerTickEvent e) {
			if (tick != 20) {
				tick++;
				return;
			}
			tick = 0;
			Config.load("cheesecore");
			setSpeedMultiplier(Config.getCrackerMakerSpeedMultiplier());
		}
	}

	public static void setSpeedMultiplier(int speedMultiplier) {
		TileEntityCrackerMaker.speedMultiplier = speedMultiplier;
	}

	public static int getSpeedMultiplier() {
		return speedMultiplier;
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.cracker_maker";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName())
				: new TextComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() {
		return this.inventoryStacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : this.inventoryStacks) {
			if (!stack.isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return (ItemStack) this.inventoryStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.inventoryStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventoryStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack) this.inventoryStacks.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventoryStacks.set(index, stack);

		if (stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit());
		if (index == 0 && index + 1 == 1 && !flag) {
			ItemStack stack1 = (ItemStack) this.inventoryStacks.get(index + 1);
			this.totalCookTime = this.getCookTime(stack, stack1);
			this.cookTime = 0;
			this.markDirty();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = getItemBurnTime((ItemStack) this.inventoryStacks.get(4));
		inventory.deserializeNBT((NBTTagCompound) compound.getTag("CrackerMakerInventory"));
		ItemStackHelper.loadAllItems(compound, this.inventoryStacks);

		if (compound.hasKey("CustomName", 8))
			this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short) this.burnTime);
		compound.setInteger("CookTime", (short) this.cookTime);
		compound.setInteger("CookTimeTotal", (short) this.totalCookTime);
		compound.setTag("CrackerMakerInventory", this.inventory.serializeNBT());
		ItemStackHelper.saveAllItems(compound, this.inventoryStacks);

		if (this.hasCustomName())
			compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isBurning() {
		return this.burnTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) {
		return inventory.getField(0) > 0;
	}

	public void update() {
		boolean flag = this.isBurning();
		boolean flag1 = false;
		//this.burnTime = 50;

		if (this.isBurning())
			--this.burnTime;

		if (!this.world.isRemote) {
			ItemStack stack = (ItemStack) this.inventoryStacks.get(4);

			if (this.isBurning() || !stack.isEmpty() && !((((ItemStack) this.inventoryStacks.get(0)).isEmpty())
					|| ((ItemStack) this.inventoryStacks.get(1)).isEmpty())) {
				if (!this.isBurning() && this.canSmelt()) {
					this.burnTime = getItemBurnTime(stack);
					this.currentBurnTime = this.burnTime;

					if (this.isBurning()) {
						flag1 = true;

						if (!stack.isEmpty()) {
							Item item = stack.getItem();
							stack.shrink(1);

							if (stack.isEmpty()) {
								ItemStack item1 = item.getContainerItem(stack);
								this.inventoryStacks.set(2, item1);
							}
						}
					}
				}
				if (this.isBurning() && this.canSmelt()) {
					++this.cookTime;

					if (this.cookTime == this.totalCookTime) {
						this.cookTime = 0;
						this.totalCookTime = this.getCookTime((ItemStack) this.inventoryStacks.get(0),
								(ItemStack) this.inventoryStacks.get(1));
						this.smeltItem();
						flag1 = true;
					}
				} else
					this.cookTime = 0;
			} else if (!this.isBurning() && this.cookTime > 0) {
				this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
			}
			if (flag != this.isBurning()) {
				flag1 = true;
				BlockCrackerMaker.setState(this.isBurning(), this.world, this.pos);
			}
		}
		if (flag1)
			this.markDirty();
	}

	public int getCookTime(ItemStack input1, ItemStack input2) {
		return 200 / speedMultiplier;
	}

	private boolean canSmelt() {
		if (((ItemStack) this.inventoryStacks.get(0)).isEmpty() || ((ItemStack) this.inventoryStacks.get(1)).isEmpty() || ((ItemStack) this.inventoryStacks.get(2)).isEmpty() || ((ItemStack) this.inventoryStacks.get(3)).isEmpty())
			return false;
		else {
			ItemStack result = CrackerMaking.INSTANCE.getCrackerMakingResult((ItemStack) this.inventoryStacks.get(0), (ItemStack) this.inventoryStacks.get(1), (ItemStack) this.inventoryStacks.get(2), (ItemStack) this.inventoryStacks.get(3));
			if (result.isEmpty())
				return false;
			else {
				ItemStack output = (ItemStack) this.inventoryStacks.get(5);
				if (output.isEmpty())
					return true;
				if (!output.isItemEqual(result))
					return false;
				int res = output.getCount() + result.getCount();
				return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
			}
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack input1 = (ItemStack) this.inventoryStacks.get(0);
			ItemStack input2 = (ItemStack) this.inventoryStacks.get(1);
			ItemStack input3 = (ItemStack) this.inventoryStacks.get(2);
			ItemStack input4 = (ItemStack) this.inventoryStacks.get(3);
			ItemStack result = CrackerMaking.INSTANCE.getCrackerMakingResult(input1, input2, input3, input4);
			ItemStack output = (ItemStack) this.inventoryStacks.get(5);
			
			if (output.isEmpty())
				this.inventoryStacks.set(5, result.copy());
			else if (output.getItem() == result.getItem()) {
				output.grow(result.getCount());
			}

			this.inventoryStacks.set(0, checkForContainerItem(input1));
			this.inventoryStacks.set(1, checkForContainerItem(input2));
			this.inventoryStacks.set(2, checkForContainerItem(input3));
			this.inventoryStacks.set(3, checkForContainerItem(input4));
		}
	}
	
	private ItemStack checkForContainerItem(ItemStack itemStack) {
		ItemStack stack = itemStack.copy();
		Item item = stack.getItem();
		if (item instanceof ItemBucket) {
			stack =  new ItemStack(Items.BUCKET);
		} else {
			stack.shrink(1);
		}
		return stack.copy();
	}

	public static int getItemBurnTime(ItemStack fuel) {
		if (fuel.isEmpty())
			return 0;
		else {
			Item item = fuel.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.WOODEN_SLAB)
					return 150;
				if (block.getDefaultState().getMaterial() == Material.WOOD)
					return 300;
				if (block == Blocks.COAL_BLOCK)
					return 16000;
			}

			if (item instanceof ItemTool && "WOOD".equals(((ItemTool) item).getToolMaterialName()))
				return 200;
			if (item instanceof ItemSword && "WOOD".equals(((ItemSword) item).getToolMaterialName()))
				return 200;
			if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe) item).getMaterialName()))
				return 200;
			if (item == Items.STICK)
				return 100;
			if (item == Items.COAL)
				return 1600;
			if (item == Items.LAVA_BUCKET)
				return 20000;
			if (item == Item.getItemFromBlock(Blocks.SAPLING))
				return 100;
			if (item == Items.BLAZE_ROD)
				return 2400;

			return GameRegistry.getFuelValue(fuel);
		}
	}

	public static boolean isItemFuel(ItemStack fuel) {
		return getItemBurnTime(fuel) > 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false
				: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {

		if (index == 3)
			return false;
		else if (index != 2)
			return true;
		else {
			return isItemFuel(stack);
		}
	}

	public String getGuiID() {
		return "tm:sintering_furnace";
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		this.inventoryStacks.clear();
	}
}
