package coffeecatteam.cheesemod.objects.armor;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorHamCooked extends ItemArmor {

	public ArmorHamCooked(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEARMORTOOLSTAB);
	}
}
