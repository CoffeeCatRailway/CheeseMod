package coffeecatrailway.cheesemod.items;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2019
 */
public class FoodBlockItem extends BlockItem {

    public FoodBlockItem(Block block, Food food, boolean grilled) {
        super(block, new Item.Properties().group(CheeseMod.GROUP_ITEMS).food(genBlockFood(food, grilled)));
    }

    private static Food genBlockFood(Food food, boolean grilled) {
        double grilledMul = ModConfig.MODIFIERS.grilledFoodMultiplier.get();
        Food.Builder newFood = (new Food.Builder()).hunger((int) (food.getHealing() * 9 * grilledMul)).saturation((float) (food.getSaturation() * 9.0f * grilledMul));

        for (Pair<EffectInstance, Float> effect : food.getEffects())
            newFood = newFood.effect(effect.getKey(), effect.getValue());

        if (food.canEatWhenFull())
            newFood = newFood.setAlwaysEdible();
        if (food.isFastEating())
            newFood = newFood.fastToEat();
        if (food.isMeat())
            newFood = newFood.meat();

        return newFood.build();
    }
}
