package coffeecatteam.cheesemod.objects.armor;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorCheese extends ItemArmor {

	public ArmorCheese(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEARMORTOOLSTAB);
	}
	
//	@SuppressWarnings("null")
//	@Override
//	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
//			ModelBiped _default) {
//		if (itemStack != null) {
//			if (itemStack.getItem() instanceof ItemArmor) {
//
//				ModelBiped armorModel = null;
//
//				armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
//				armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
//				armorModel.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST)
//						|| (armorSlot == EntityEquipmentSlot.CHEST);
//				armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
//				armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
//				armorModel.bipedRightLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
//						|| (armorSlot == EntityEquipmentSlot.FEET);
//				armorModel.bipedLeftLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
//						|| (armorSlot == EntityEquipmentSlot.FEET);
//
//				armorModel.isSneak = _default.isSneak;
//				armorModel.isRiding = _default.isRiding;
//				armorModel.isChild = _default.isChild;
//				armorModel.rightArmPose = _default.rightArmPose;
//				armorModel.leftArmPose = _default.leftArmPose;
//
//				return armorModel;
//			}
//		}
//		return null;
//	}
}
