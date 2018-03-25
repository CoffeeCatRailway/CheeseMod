package coffeecatteam.cheesemod.objects.tabs;

import java.util.Random;

import coffeecatteam.cheesemod.init.InitItem;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabCheeseFoods extends CreativeTabs {

	public TabCheeseFoods(String label) {
		super(label);
		this.setBackgroundImageName("cheese.png");
	}

	public ItemStack getTabIconItem() {
		return new ItemStack(InitItem.TOASTIE, 1, new Random().nextInt(EnumHandler.EnumToastieType.values().length));
	}
}
