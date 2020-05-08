package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.registry.ModFoods;
import coffeecatrailway.coffeecheese.registry.ModItems;
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
                        new Supplier[]{ModItems.CHEESE_SLICE},
                        new Supplier[]{ModItems.HAM_RAW, ModItems.CHEESE_SLICE},
                        new Supplier[]{ModItems.HAM_COOKED, ModItems.CHEESE_SLICE},
                        new Supplier[]{ModItems.EGG_GREEN, ModItems.HAM_RAW}
                ),
                ModFoods.BREAD_SLICE,
                ModFoods.TOAST,
                ModItems.BREAD_SLICE,
                ModItems.TOAST,
                true
        ));
    }
}
