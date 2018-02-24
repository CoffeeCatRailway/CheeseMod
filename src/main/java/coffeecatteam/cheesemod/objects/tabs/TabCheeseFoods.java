package coffeecatteam.cheesemod.objects.tabs;

import coffeecatteam.cheesemod.init.InitItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabCheeseFoods extends CreativeTabs {

	public TabCheeseFoods(String label) {
		super(label);
		this.setBackgroundImageName("cheese.png");
	}

	public ItemStack getTabIconItem() {
		return new ItemStack(InitItem.CHEESE_TOASTIE);
	}
}
