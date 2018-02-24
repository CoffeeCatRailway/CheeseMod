package coffeecatteam.cheesemod.objects.tabs;

import coffeecatteam.cheesemod.init.InitBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabCheese extends CreativeTabs {

	public TabCheese(String label) {
		super(label);
		this.setBackgroundImageName("cheese.png");
	}

	public ItemStack getTabIconItem() {
		return new ItemStack(InitBlock.CHEESE_DRAW);
	}
}
