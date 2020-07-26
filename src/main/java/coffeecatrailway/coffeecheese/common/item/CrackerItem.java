package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.registry.CheeseItems;
import coffeecatrailway.coffeecheese.registry.nonregistries.CheeseFoods;
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
                        new Supplier[]{CheeseItems.CHEESE_SLICE},
                        new Supplier[]{CheeseItems.HAM_RAW},
                        new Supplier[]{CheeseItems.HAM_RAW, CheeseItems.CHEESE_SLICE}
                ),
                CheeseFoods.CRACKER,
                CheeseFoods.CRACKER_TOASTED,
                CheeseItems.CRACKER_DUMMY_ITEM,
                CheeseItems.CRACKER_TOASTED_DUMMY_ITEM,
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
