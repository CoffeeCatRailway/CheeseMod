package coffeecatteam.cheesemod.objects.items.foods;

import coffeecatteam.cheesemod.objects.items.base.ItemBaseFood;
import coffeecatteam.cheesemod.util.OreDictionaries;
import coffeecatteam.cheesemod.util.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Calendar;
import java.util.List;

public class ItemFoodGreen extends ItemBaseFood {
	
	public ItemFoodGreen(String name, int amount, boolean isWolfFood) {
		super(name, OreDictionaries.foodEgg, amount, isWolfFood);
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
        if (isGreen(stack)) {
			if (!worldIn.isRemote) {
				player.addPotionEffect(new PotionEffect(MobEffects.POISON, 400, 1));
                String msg = "You idiot why would you eat a " + TextFormatting.GREEN.toString() + TextFormatting.BOLD.toString() + "green " + TextFormatting.RESET.toString();
                msg += stack.getUnlocalizedName().contains("egg") ? "egg" : "ham";
				player.sendMessage(new TextComponentString(msg + "?!"));
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if (isGreen(stack)) {
			tooltip.add("Yay to Dr. Seuss for his green eggs and ham!");
			if (Utils.isDate(Calendar.MARCH, 2)) {
				tooltip.add("And a happy birthday to the great Dr. Seuss!");
				tooltip.add("Born 2 March 1904, Died 24 September 1991");
			}
		}
	}

	private boolean isGreen(ItemStack stack) {
        return stack.getUnlocalizedName().contains("green");
    }
}
