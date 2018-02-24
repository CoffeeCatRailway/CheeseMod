package coffeecatteam.cheesemod.crafting.foodmakers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrackerMaking {
	public static final CrackerMaking INSTANCE = new CrackerMaking();
	private final static List<ItemStack> crackerMakingList = new ArrayList<ItemStack>();
	
	private final static Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	public static void addRecipe(ItemStack result, float experience, ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4) {
		if (getCrackerMakingResult(input1, input2, input3, input4) != ItemStack.EMPTY)
			return;
		crackerMakingListPut(input1, input2, input3, input4, result);
		CrackerMaking.experienceList.put(result, Float.valueOf(experience));
	}
	
	public static void crackerMakingListPut(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack result) {
		CrackerMaking.crackerMakingList.add(input1);
		CrackerMaking.crackerMakingList.add(input2);
		CrackerMaking.crackerMakingList.add(input3);
		CrackerMaking.crackerMakingList.add(input4);
		CrackerMaking.crackerMakingList.add(result);
	}

	public static ItemStack getCrackerMakingResult(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4) {
//		int size = CrackerMaking.crackerMakingList.size();
//		for (int i = 0; i < size; i++) {
//			if (compareItemStacks(input1, (ItemStack) CrackerMaking.crackerMakingList.get(i))) {
//				for (int j = 0; j < size; j++) {
//					if (compareItemStacks(input2, (ItemStack) CrackerMaking.crackerMakingList.get(j))) {
//						for (int k = 0; k < size; k++) {
//							if (compareItemStacks(input3, (ItemStack) CrackerMaking.crackerMakingList.get(k))) {
//								for (int l = 0; l < size; l++) {
//									if (compareItemStacks(input4, (ItemStack) CrackerMaking.crackerMakingList.get(l))) {
//										return (ItemStack) CrackerMaking.crackerMakingList.get(l);
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		
		for (ItemStack stack : CrackerMaking.crackerMakingList) {
			if (compareItemStacks(input1, (ItemStack) stack)) {
				for (ItemStack stack1 : CrackerMaking.crackerMakingList) {
					if (compareItemStacks(input2, (ItemStack) stack1)) {
						for (ItemStack stack2 : CrackerMaking.crackerMakingList) {
							if (compareItemStacks(input3, (ItemStack) stack2)) {
								for (ItemStack stack3 : CrackerMaking.crackerMakingList) {
									if (compareItemStacks(input4, (ItemStack) stack3)) {
										//System.out.println(stack3.getItem().getUnlocalizedName());
										return (ItemStack) stack3;
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
