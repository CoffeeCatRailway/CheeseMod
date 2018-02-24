package coffeecatteam.cheesemod.objects.tools;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.item.ItemPickaxe;

public class ToolPickaxe extends ItemPickaxe {
	
	public ToolPickaxe(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEARMORTOOLSTAB);
	}
}
