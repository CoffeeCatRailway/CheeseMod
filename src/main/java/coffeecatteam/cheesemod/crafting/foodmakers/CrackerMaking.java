package coffeecatteam.cheesemod.crafting.foodmakers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import scala.collection.mutable.HashEntry;

public class CrackerMaking {
	public static final CrackerMaking INSTANCE = new CrackerMaking();
	private final static List<ItemStack> crackerMakingList = new ArrayList<ItemStack>();
	
	private final static Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	public static void addRecipe(ItemStack result, float experience, ItemStack input1, ItemStack input2,
			ItemStack input3, ItemStack input4) {
		if (input1.isEmpty())
			input1 = new ItemStack(Blocks.AIR, 1, 0);
		if (input2.isEmpty())
			input2 = new ItemStack(Blocks.AIR, 1, 0);
		if (input3.isEmpty())
			input3 = new ItemStack(Blocks.AIR, 1, 0);
		if (input4.isEmpty())
			input4 = new ItemStack(Blocks.AIR, 1, 0);
		
		if (getCrackerMakingResult(input1, input2, input3, input4) != ItemStack.EMPTY)
			return;
		crackerMakingListPut(input1, input2, input3, input4, result);
		CrackerMaking.experienceList.put(result, Float.valueOf(experience));
	}

	public static void crackerMakingListPut(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4,
			ItemStack result) {
		CrackerMaking.crackerMakingList.add(input1);
		CrackerMaking.crackerMakingList.add(input2);
		CrackerMaking.crackerMakingList.add(input3);
		CrackerMaking.crackerMakingList.add(input4);
		CrackerMaking.crackerMakingList.add(result);
	}

	public static ItemStack getCrackerMakingResult(ItemStack input1, ItemStack input2, ItemStack input3,
			ItemStack input4) {
		int len = CrackerMaking.crackerMakingList.size();
		for (int i = 0; i < len; i++) {
			if (compareItemStacks(input1, CrackerMaking.crackerMakingList.get(i))) {
				for (int j = 1; j < len; j++) {
					if (compareItemStacks(input2, CrackerMaking.crackerMakingList.get(j))) {
						for (int k = 2; k < len; k++) {
							if (compareItemStacks(input3, CrackerMaking.crackerMakingList.get(k))) {
								for (int l = 3; l < len; l++) {
									if (compareItemStacks(input4, CrackerMaking.crackerMakingList.get(l))) {
										return CrackerMaking.crackerMakingList.get(l+1);
									}
								}
							}
						}
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

	public List<ItemStack> getCrackerMakingList() {
		return this.crackerMakingList;
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
