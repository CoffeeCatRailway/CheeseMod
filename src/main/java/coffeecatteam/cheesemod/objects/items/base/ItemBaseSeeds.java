package coffeecatteam.cheesemod.objects.items.base;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;

public class ItemBaseSeeds extends ItemSeeds implements IOreDict {
	
	private String oreDict;

	public ItemBaseSeeds(String name, String oreDict, Block crops, Block soil) {
		super(crops, soil);
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
