package coffeecatteam.cheesemod.objects.armor;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorHamRaw extends ItemArmor {

	public ArmorHamRaw(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEARMORTOOLSTAB);
	}
}
