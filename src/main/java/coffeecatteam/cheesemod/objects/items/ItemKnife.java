package coffeecatteam.cheesemod.objects.items;

import coffeecatteam.cheesemod.init.InitItem;
import coffeecatteam.cheesemod.init.InitTool;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseTool;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.IStringSerializable;

public class ItemKnife extends ItemBaseTool implements IOreDict {
	
	private String oreDict;

	public ItemKnife(String name, String oreDict, ToolMaterial material, boolean isRepairable, ItemStack repairItem) {
		super(name, material, isRepairable, repairItem);
		this.oreDict = oreDict;
	}
	
	@Override
	public String registerOre() {
		return oreDict;
	}
}
