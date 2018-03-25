package coffeecatteam.cheesemod.objects.items.foods;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.util.handlers.EnumHandler.EnumToastType;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemToast extends ItemFood implements IOreDict {
	
	private String oreDict;
	
	public ItemToast(String name, String oreDict) {
		super(0, false);
		this.oreDict = oreDict;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEFOODSTAB);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getHealAmount(ItemStack stack) {
		int i = stack.getMetadata();
		return EnumToastType.byMetaData(i).getHealAmount();
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int i = stack.getMetadata();
		return super.getUnlocalizedName() + "." + EnumToastType.byMetaData(i).getUnlocalizedName();
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			for (int i = 0; i < EnumToastType.values().length; i++) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public String registerOre() {
		return oreDict;
	}
}
