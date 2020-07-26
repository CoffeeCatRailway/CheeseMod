package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.registry.CheeseItems;
import coffeecatrailway.coffeecheese.registry.nonregistries.CheeseFoods;
import com.google.common.collect.ImmutableSet;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 28/04/2020
 */
public class SandwichItem extends StackableFoodItem {

    public SandwichItem(Properties properties) {
        super(properties, new FoodProperties(
                ImmutableSet.of(
                        new Supplier[]{CheeseItems.CHEESE_SLICE},
                        new Supplier[]{CheeseItems.HAM_RAW, CheeseItems.CHEESE_SLICE},
                        new Supplier[]{CheeseItems.HAM_COOKED, CheeseItems.CHEESE_SLICE},
                        new Supplier[]{CheeseItems.EGG_GREEN, CheeseItems.HAM_RAW}
                ),
                CheeseFoods.BREAD_SLICE,
                CheeseFoods.TOAST,
                CheeseItems.BREAD_SLICE,
                CheeseItems.TOAST,
                true
        ));
    }
}
