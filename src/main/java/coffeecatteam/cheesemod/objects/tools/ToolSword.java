package coffeecatteam.cheesemod.objects.tools;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.item.ItemSword;

public class ToolSword extends ItemSword {

	public ToolSword(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEARMORTOOLSTAB);
	}
}
