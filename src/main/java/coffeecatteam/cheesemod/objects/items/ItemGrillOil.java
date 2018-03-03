package coffeecatteam.cheesemod.objects.items;

import coffeecatteam.cheesemod.objects.items.base.ItemBaseTool;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemGrillOil extends ItemBaseTool implements IOreDict {
	
	private String oreDict;

	public ItemGrillOil(String name, String oreDict, ToolMaterial material, boolean isRepairable, ItemStack repairItem) {
		super(name, material, isRepairable, repairItem);
		this.oreDict = oreDict;
	}

	@Override
	public String registerOre() {
		return oreDict;
	}
}
