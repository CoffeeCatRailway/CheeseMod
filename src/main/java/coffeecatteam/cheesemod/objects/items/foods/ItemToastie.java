package coffeecatteam.cheesemod.objects.items.foods;

import java.util.List;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.util.handlers.EnumHandler.EnumToastieType;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemToastie extends ItemFood implements IOreDict {

	private String oreDict;
	
	public ItemToastie(String name, String oreDict) {
		super(0, false);
		this.oreDict = oreDict;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEFOODSTAB);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getHealAmount(ItemStack stack) {
		int i = stack.getMetadata();
		return EnumToastieType.byMetaData(i).getHealAmount();
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int i = stack.getMetadata();
		return super.getUnlocalizedName() + "." + EnumToastieType.byMetaData(i).getUnlocalizedName();
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			for (int i = 0; i < EnumToastieType.values().length; i++) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		int i = stack.getMetadata();
		boolean grilled = EnumToastieType.byMetaData(i).isGrilled();
		
		String infoGrilled = "Tastes better grilled, right..?";
		String infoNotGrilled = "So, Do you want it grilled anyone..?";
		tooltip.add(grilled ? infoGrilled : infoNotGrilled);
	}

	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		int i = stack.getMetadata();
		boolean grilled = EnumToastieType.byMetaData(i).isGrilled();
		
		if (this.getUnlocalizedName().contains("grilled") && grilled == true) {
			if (!worldIn.isRemote) {
				player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 400, 1));
			}
		}
	}

	@Override
	public String registerOre() {
		return oreDict;
	}
}
