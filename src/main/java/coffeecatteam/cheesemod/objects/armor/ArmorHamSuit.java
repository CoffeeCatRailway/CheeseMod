package coffeecatteam.cheesemod.objects.armor;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.armor.model.ModelHamSuit;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorHamSuit extends ItemArmor {

	public ArmorHamSuit(String name, ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEARMORTOOLSTAB);
		setMaxStackSize(1);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		if (slot != EntityEquipmentSlot.LEGS)
			return Reference.MODID+":textures/models/armor/ham_suit.png";
		else
			return "";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
			ModelBiped _default) {
		if (!itemStack.isEmpty()) {
			if (itemStack.getItem() instanceof ItemArmor) {

				ModelBiped armorModel = new ModelHamSuit(1.0f, armorSlot);

				armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				
				armorModel.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
				armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;

				armorModel.isSneak = _default.isSneak;
				armorModel.isRiding = _default.isRiding;
				armorModel.isChild = _default.isChild;

				return armorModel;
			}
		}
		return null;
	}
}
