package coffeecatteam.cheesemod.objects.items.base;

import com.google.common.collect.Sets;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemBaseTool extends ItemTool {

	public ItemBaseTool(String name, ToolMaterial material) {
		super(material, Sets.newHashSet(Blocks.DIRT));
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEARMORTOOLSTAB);

		setMaxDamage(20);

		this.setContainerItem(this);
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stackContainer) {
		ItemStack stack = stackContainer.copy();
		stack.setCount(1);
		stack.setItemDamage(stack.getItemDamage() + 1);

		if (stack.getItemDamage() > stack.getMaxDamage())
			return ItemStack.EMPTY;
		return stack;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return true; //super.getIsRepairable(toRepair, repair);
	}
}
