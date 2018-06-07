package coffeecatteam.cheesemod.objects.items.foods;

import coffeecatteam.cheesemod.objects.items.base.ItemBaseFood;
import coffeecatteam.cheesemod.util.OreDictionaries;
import coffeecatteam.cheesemod.util.Utils;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Calendar;
import java.util.List;

public class ItemCracker extends ItemBaseFood implements IOreDict {
	
	public ItemCracker(String name, int amount) {
        super("cracker_" + name, OreDictionaries.foodCracker, amount, false);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if (stack.getUnlocalizedName().contains("crayfish")) {
            tooltip.add("Credit to MrCrayfish for the insperation!");
            if (Utils.isDate(Calendar.SEPTEMBER, 29))
                tooltip.add("And a happy birthday to MrCrayfish!");
        }
	}
}
