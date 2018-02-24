package coffeecatteam.cheesemod.objects.tabs;

import coffeecatteam.cheesemod.init.InitTool;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabCheeseArmorTools extends CreativeTabs {

	public TabCheeseArmorTools(String label) {
		super(label);
		this.setBackgroundImageName("cheese.png");
	}

	public ItemStack getTabIconItem() {
		return new ItemStack(InitTool.CHEESE_METAL_SWORD);
	}
}
