package coffeecatteam.cheesemod.objects.items.foods;

import java.util.List;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.objects.items.base.ItemBaseFood;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemToastie extends ItemBaseFood {

	private static String info;
	private static boolean grilled;

	public ItemToastie(String name, int amount, boolean isWolfFood, String info, boolean grilled) {
		super(name, amount, isWolfFood);
		this.info = info;
		this.grilled = grilled;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(this.info);
	}

	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		if (this.getUnlocalizedName().contains("grilled") && this.grilled == true) {
			if (!worldIn.isRemote) {
				player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 400, 1));
			}
		}
	}
}
