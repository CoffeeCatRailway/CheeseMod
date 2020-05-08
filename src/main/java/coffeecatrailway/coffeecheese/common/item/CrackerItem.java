package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.registry.ModFoods;
import coffeecatrailway.coffeecheese.registry.ModItems;
import com.google.common.collect.ImmutableSet;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 2/05/2020
 */
public class CrackerItem extends StackableFoodItem {

    public CrackerItem(Properties properties) {
        super(properties, new FoodProperties(
                ImmutableSet.of(
                        new Supplier[]{ModItems.CHEESE_SLICE},
                        new Supplier[]{ModItems.HAM_RAW},
                        new Supplier[]{ModItems.HAM_RAW, ModItems.CHEESE_SLICE}
                ),
                ModFoods.CRACKER,
                ModFoods.CRACKER_TOASTED,
                ModItems.CRACKER_DUMMY_ITEM,
                ModItems.CRACKER_TOASTED_DUMMY_ITEM,
                false
        ));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group))
            items.add(new ItemStack(this));
        super.fillItemGroup(group, items);
    }
}
