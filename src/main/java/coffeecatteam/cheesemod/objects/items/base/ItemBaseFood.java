package coffeecatteam.cheesemod.objects.items.base;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.item.ItemFood;
import net.minecraftforge.oredict.OreDictionary;

public class ItemBaseFood extends ItemFood {

	public ItemBaseFood(String name, int amount, boolean isWolfFood) {
		super(amount, isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEFOODSTAB);
  	}
}
