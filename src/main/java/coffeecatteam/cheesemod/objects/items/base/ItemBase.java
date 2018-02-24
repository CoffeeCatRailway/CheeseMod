package coffeecatteam.cheesemod.objects.items.base;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.item.Item;

public class ItemBase extends Item {

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESETAB);
	}
}
