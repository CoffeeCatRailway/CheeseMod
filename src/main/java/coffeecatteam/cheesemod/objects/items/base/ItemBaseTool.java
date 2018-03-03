package coffeecatteam.cheesemod.objects.items.base;

import com.google.common.collect.Sets;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemBaseTool extends ItemTool {
	
	private boolean isRepairable;
	private ItemStack repairItem;

	public ItemBaseTool(String name, ToolMaterial material, boolean isRepairable, ItemStack repairItem) {
		super(material, Sets.newHashSet(Blocks.DIRT));
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESEARMORTOOLSTAB);
		this.isRepairable = isRepairable;
		this.repairItem = repairItem;
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
		return (repair == repairItem); // (toRepair == new ItemStack(this, 1, this.getDamage(toRepair))) && 
	}
}
