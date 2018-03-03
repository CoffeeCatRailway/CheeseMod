package coffeecatteam.cheesemod.objects.items.base;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IOreDict {
	
	private String oreDict;

	public ItemBase(String name, String oreDict) {
		this.oreDict = oreDict;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESETAB);
	}

	@Override
	public String registerOre() {
		return oreDict;
	}
}
