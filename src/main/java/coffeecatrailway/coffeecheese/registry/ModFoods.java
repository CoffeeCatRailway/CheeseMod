package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.ModCheeseConfig;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
public class ModFoods {

    private static final double grilledMul = ModCheeseConfig.grilledFoodMultiplier.get();

    public static final Food INGOT = new Food.Builder().hunger(1).saturation(0f).effect(() -> new EffectInstance(Effects.INSTANT_DAMAGE, 1, 1), 1f).build();

    public static final Food BLOCK_O_CHEESE = new Food.Builder().hunger(6).saturation(1f).fastToEat().build();
    public static final Food CHEESE_BALL = new Food.Builder().hunger(BLOCK_O_CHEESE.getHealing() / 2).saturation(BLOCK_O_CHEESE.getSaturation() / 2.0f).fastToEat().build();
    public static final Food CHEESE_SLICE = new Food.Builder().hunger(BLOCK_O_CHEESE.getHealing() / 3).saturation(BLOCK_O_CHEESE.getSaturation() / 2.0f).fastToEat().build();

    public static final Food INGREDIENT = new Food.Builder().hunger(1).saturation(0.5f).build();

    public static final Food BACON = new Food.Builder().hunger(2).saturation(1.0f).meat().build();
    public static final Food BACON_COOKED = new Food.Builder().hunger(BACON.getHealing() + 3).saturation(BACON.getSaturation() + 0.5f).meat().fastToEat().build();

    public static final Food BREAD_SLICE = new Food.Builder().hunger(Foods.BREAD.getHealing() / 3).saturation(Foods.BREAD.getSaturation() / 3.0f).build();

    public static final Food DOUGH = new Food.Builder().hunger(INGREDIENT.getHealing() * 3).saturation(INGREDIENT.getSaturation() * 3.0f).build();

    public static final Food EGG = new Food.Builder().hunger(3).saturation(1.0f).build();
    public static final Food EGG_COOKED = new Food.Builder().hunger(EGG.getHealing() * 2).saturation(EGG.getSaturation() * 2.0f).build();
    public static final Food EGG_GREEN = new Food.Builder().hunger(EGG.getHealing()).saturation(EGG.getSaturation()).effect(() -> new EffectInstance(Effects.HUNGER, 400, 1), 1.0f).build();

    public static final Food HAM = new Food.Builder().hunger(Foods.PORKCHOP.getHealing() / 3).saturation(Foods.PORKCHOP.getSaturation() / 3.0f).meat().build();
    public static final Food HAM_COOKED = new Food.Builder().hunger(HAM.getHealing() * 2).saturation(HAM.getSaturation() * 2.0f).meat().build();
    public static final Food HAM_GREEN = new Food.Builder().hunger(HAM.getHealing()).saturation(HAM.getSaturation()).effect(() -> new EffectInstance(Effects.HUNGER, 400, 1), 1.0f).meat().build();

    public static final Food TOAST = new Food.Builder().hunger(BREAD_SLICE.getHealing() * 3).saturation(BREAD_SLICE.getSaturation() * 3.0f).build();
    public static final Food TOAST_FRENCH = buildCombo(false, TOAST, EGG, EGG);
    public static final Food TOAST_BACON = buildCombo(false, TOAST, BACON_COOKED);

    public static final Food CRACKER = new Food.Builder().hunger(5).saturation(2.0f).build();
    public static final Food CRACKER_CHEESE = buildCombo(false, CRACKER, CHEESE_SLICE);
    public static final Food CRACKER_CHEESE_GRILLED = buildCombo(true, CRACKER_CHEESE);
    public static final Food CRACKER_HAM = buildCombo(false, CRACKER, HAM);
    public static final Food CRACKER_HAM_GRILLED = buildCombo(true, CRACKER_HAM);
    public static final Food CRACKER_CHEESE_HAM = buildCombo(false, CRACKER, CHEESE_SLICE, HAM);
    public static final Food CRACKER_CHEESE_HAM_GRILLED = buildCombo(true, CRACKER_CHEESE_HAM);

    public static final Food TOASTIE_CHEESE = buildCombo(false, TOAST, TOAST, CHEESE_SLICE);
    public static final Food TOASTIE_CHEESE_GRILLED = buildCombo(true, TOASTIE_CHEESE);
    public static final Food TOASTIE_CHEESE_HAM = buildCombo(false, TOAST, TOAST, CHEESE_SLICE, HAM);
    public static final Food TOASTIE_CHEESE_HAM_GRILLED = buildCombo(true, TOASTIE_CHEESE_HAM);
    public static final Food TOASTIE_CHEESE_HAM_COOKED = buildCombo(false, TOAST, TOAST, CHEESE_SLICE, HAM_COOKED);
    public static final Food TOASTIE_CHEESE_HAM_COOKED_GRILLED = buildCombo(true, TOASTIE_CHEESE_HAM_COOKED);

    public static final Food PINEAPPLE = new Food.Builder().hunger(12).saturation(5.0f).build();
    public static final Food PINEAPPLE_PLANT = new Food.Builder().hunger(PINEAPPLE.getHealing() / 8).saturation(PINEAPPLE.getSaturation() / 8.0f).effect(() -> new EffectInstance(Effects.NAUSEA, 400, 1), 1.0f).build();
    public static final Food PINEAPPLE_RING = new Food.Builder().hunger(PINEAPPLE.getHealing() / 4).saturation(PINEAPPLE.getSaturation() / 4.0f).build();
    public static final Food PINEAPPLE_BIT = new Food.Builder().hunger(PINEAPPLE_RING.getHealing() / 3).saturation(PINEAPPLE_RING.getSaturation() / 3.0f).build();

    public static final Food PIZZA_CHEESE = buildCombo(false, DOUGH, CHEESE_SLICE);
    public static final Food PIZZA_CHEESE_COOKED = buildCombo(true, PIZZA_CHEESE);
    public static final Food PIZZA_CHEESE_HAM = buildCombo(false, DOUGH, CHEESE_SLICE, HAM_COOKED);
    public static final Food PIZZA_CHEESE_HAM_COOKED = buildCombo(true, PIZZA_CHEESE_HAM);
    public static final Food PIZZA_CHEESE_HAM_PINEAPPLE = buildCombo(false, DOUGH, CHEESE_SLICE, HAM_COOKED, PINEAPPLE_RING);
    public static final Food PIZZA_CHEESE_HAM_PINEAPPLE_COOKED = buildCombo(true, PIZZA_CHEESE_HAM_PINEAPPLE);

    public static Food buildCombo(boolean grilled, Food... foods) {
        if (foods.length == 1) {
            Food food = foods[0];
            Food.Builder newFood = new Food.Builder().hunger(food.getHealing() * grilledI(grilled)).saturation(food.getSaturation() * grilledF(grilled));
            newFood = applyEffects(food, newFood);

            return newFood.build();
        }

        double foodCombo = ModCheeseConfig.foodCombo.get();
        Food.Builder newFood = new Food.Builder().hunger((int) ((foods[0].getHealing() / foodCombo) * grilledI(grilled))).saturation((float) ((foods[0].getSaturation() / foodCombo) * grilledF(grilled)));

        for (int i = 1; i < foods.length; i++) {
            Food food = foods[i];
            Food foodB = newFood.build();
            newFood = newFood.hunger((int) ((food.getHealing() / foodCombo) + foodB.getHealing())).saturation((float) ((food.getSaturation() / foodCombo) + foodB.getSaturation()));
            newFood = applyEffects(food, newFood);
        }

        Food foodB = newFood.build();
        return newFood.hunger(foodB.getHealing() * grilledI(grilled)).saturation(foodB.getSaturation() * grilledF(grilled)).build();
    }

    private static Food.Builder applyEffects(Food fooda, Food.Builder foodb) {
        Food.Builder newFood = foodb;
        if (fooda.isMeat()) newFood = newFood.meat();
        if (fooda.isFastEating()) newFood = newFood.fastToEat();
        if (fooda.canEatWhenFull()) newFood = newFood.setAlwaysEdible();

        if (fooda.getEffects().size() > 0)
            for (Pair<EffectInstance, Float> effect : fooda.getEffects())
                newFood = newFood.effect(effect.getLeft(), effect.getRight());

        return newFood;
    }

    private static float grilledF(boolean isGrilled) {
        return (float) (isGrilled ? grilledMul : 1.0f);
    }

    private static int grilledI(boolean isGrilled) {
        return (int) grilledF(isGrilled);
    }
}
