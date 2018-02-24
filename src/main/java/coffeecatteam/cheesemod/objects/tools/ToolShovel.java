package coffeecatteam.cheesemod.objects.tools;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.item.ItemSpade;

public class ToolShovel extends ItemSpade {

	public ToolShovel(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEARMORTOOLSTAB);
	}
}
