package coffeecatteam.cheesemod.objects.items.foods;

import java.util.List;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseFood;
import coffeecatteam.cheesemod.util.handlers.EnumHandler.EnumPizzaType;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemPizza extends ItemFood implements IOreDict {
	
	private String oreDict;

	public ItemPizza(String name, String oreDict) {
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
		return EnumPizzaType.byMetaData(i).getHealAmount();
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int i = stack.getMetadata();
		return super.getUnlocalizedName() + "." + EnumPizzaType.byMetaData(i).getUnlocalizedName();
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			for (int i = 0; i < EnumPizzaType.values().length; i++) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("PIZZA PARTY!");
	}

	@Override
	public String registerOre() {
		return oreDict;
	}
}
