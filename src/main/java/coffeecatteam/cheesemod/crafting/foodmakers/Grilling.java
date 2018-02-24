package coffeecatteam.cheesemod.crafting.foodmakers;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Grilling {
	public static final Grilling INSTANCE = new Grilling();
	private final static Table<ItemStack, ItemStack, ItemStack> grillingList = HashBasedTable
			.<ItemStack, ItemStack, ItemStack>create();
	private final static Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	public static void addRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		if (getGrillingResult(input1, input2) != ItemStack.EMPTY)
			return;
		Grilling.grillingList.put(input1, input2, result);
		Grilling.experienceList.put(result, Float.valueOf(experience));
	}

	public static ItemStack getGrillingResult(ItemStack input1, ItemStack input2) {
		for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : Grilling.grillingList.columnMap().entrySet()) {
			if (compareItemStacks(input1, (ItemStack) entry.getKey())) {
				for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					if (compareItemStacks(input2, (ItemStack) ent.getKey())) {
						return (ItemStack) ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}

	private static boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem()
				&& (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Table<ItemStack, ItemStack, ItemStack> getGrillingList() {
		return this.grillingList;
	}

	public float getGrillingExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (this.compareItemStacks(stack, (ItemStack) entry.getKey())) {
				return ((Float) entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
