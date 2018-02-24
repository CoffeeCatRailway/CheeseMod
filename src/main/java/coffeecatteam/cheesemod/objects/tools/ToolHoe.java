package coffeecatteam.cheesemod.objects.tools;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.item.ItemHoe;

public class ToolHoe extends ItemHoe {

	public ToolHoe(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEARMORTOOLSTAB);
	}
}
