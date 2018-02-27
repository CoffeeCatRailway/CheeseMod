package coffeecatteam.cheesemod.objects.items.foods;

import java.util.List;

import coffeecatteam.cheesemod.objects.items.base.ItemBaseFood;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemPizza extends ItemBaseFood implements IOreDict {

	private static String name;
	private static boolean isCooked;

	public ItemPizza(String name, String oreDictName, int amount, boolean isWolfFood, boolean isCooked) {
		super(((isCooked) ? "cooked_" : "uncooked_") + name + "pizza", amount, isWolfFood);
		this.name = ((isCooked) ? "cooked" : "uncooked") + oreDictName + "Pizza";
		this.isCooked = isCooked;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("PIZZA PARTY!");
	}

	@Override
	public void registerOre() {
		OreDictionary.registerOre(name, this);
	}
}
