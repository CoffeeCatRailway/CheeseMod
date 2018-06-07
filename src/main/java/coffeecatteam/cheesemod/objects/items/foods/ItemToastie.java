package coffeecatteam.cheesemod.objects.items.foods;

import coffeecatteam.cheesemod.objects.items.base.ItemBaseFood;
import coffeecatteam.cheesemod.util.OreDictionaries;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemToastie extends ItemBaseFood {

    private boolean isGrilled;
	
	public ItemToastie(String name, int amount, boolean isGrilled) {
        super("toastie_" + name, OreDictionaries.foodToastie, amount, false);
        this.isGrilled = isGrilled;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		String grilled = "Tastes better grilled, right..?";
		String notGrilled = "So, Do you want it grilled..?";
		tooltip.add(isGrilled ? grilled : notGrilled);
	}
}
