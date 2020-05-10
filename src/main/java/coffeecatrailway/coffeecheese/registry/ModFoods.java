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

    public static final Food INGOT = new Food.Builder().hunger(1).saturation(0f).effect(() -> new EffectInstance(Effects.INSTANT_DAMAGE, 1, 1), 1f).build();

    public static final Food BLOCK_O_CHEESE = new Food.Builder().hunger(6).saturation(1f).fastToEat().build();
    public static final Food CHEESE_BALL = new Food.Builder().hunger(BLOCK_O_CHEESE.getHealing() / 2).saturation(BLOCK_O_CHEESE.getSaturation() / 2.0f).fastToEat().build();
    public static final Food CHEESE_SLICE = new Food.Builder().hunger(BLOCK_O_CHEESE.getHealing() / 3).saturation(BLOCK_O_CHEESE.getSaturation() / 2.0f).fastToEat().build();

    public static final Food INGREDIENT = new Food.Builder().hunger(1).saturation(0.5f).build();

    public static final Food BACON = new Food.Builder().hunger(2).saturation(1.0f).meat().build();
    public static final Food BACON_COOKED = new Food.Builder().hunger(BACON.getHealing() + 3).saturation(BACON.getSaturation() + 0.5f).meat().fastToEat().build();

    public static final Food BREAD_SLICE = new Food.Builder().hunger(Foods.BREAD.getHealing() / 3).saturation(Foods.BREAD.getSaturation() / 3.0f).build();
    public static final Food CRACKER = new Food.Builder().hunger(5).saturation(2.0f).build();
    public static final Food CRACKER_TOASTED = buildCombo(true, CRACKER);

    public static final Food DOUGH = new Food.Builder().hunger(INGREDIENT.getHealing() * 3).saturation(INGREDIENT.getSaturation() * 3.0f).build();

    public static final Food EGG = new Food.Builder().hunger(3).saturation(1.0f).build();
    public static final Food EGG_COOKED = new Food.Builder().hunger(EGG.getHealing() * 2).saturation(EGG.getSaturation() * 2.0f).build();
    public static final Food EGG_GREEN = new Food.Builder().hunger(EGG.getHealing()).saturation(EGG.getSaturation()).effect(() -> new EffectInstance(Effects.HUNGER, 400, 1), 1.0f).build();

    public static final Food HAM = new Food.Builder().hunger(Foods.PORKCHOP.getHealing() / 3).saturation(Foods.PORKCHOP.getSaturation() / 3.0f).meat().build();
    public static final Food HAM_COOKED = new Food.Builder().hunger(HAM.getHealing() * 2).saturation(HAM.getSaturation() * 2.0f).meat().build();
    public static final Food HAM_GREEN = new Food.Builder().hunger(HAM.getHealing()).saturation(HAM.getSaturation()).effect(() -> new EffectInstance(Effects.HUNGER, 400, 1), 1.0f).meat().build();

    public static final Food TOAST = buildCombo(true, BREAD_SLICE);
    public static final Food TOAST_FRENCH = buildCombo(false, TOAST, EGG, EGG);
    public static final Food TOAST_BACON = buildCombo(false, TOAST, BACON_COOKED);

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
        return buildCombo(0.0f, grilled, foods);
    }

    public static Food buildCombo(float foodComboOffset, boolean grilled, Food... foods) {
        float foodCombo = (float) ((foods.length > 1 ? ModCheeseConfig.foodCombo.get() : 1f) + foodComboOffset);
        int hunger = 0;
        float saturation = 0.0f;
        Food.Builder builder = new Food.Builder();

        for (Food food : foods) {
            hunger += (int) (food.getHealing() * foodCombo);
            saturation += food.getSaturation() * foodCombo;
            applyEffects(food, builder);
        }

        return builder.hunger((int) (hunger * grilled(grilled))).saturation(saturation * grilled(grilled) * 0.5f).build();
    }

    private static Food.Builder applyEffects(Food apply, Food.Builder to) {
        Food tmpBuild = to.build();
        if (apply.isMeat() && !tmpBuild.isMeat()) to.meat();
        if (apply.isFastEating() && !tmpBuild.isFastEating()) to.fastToEat();
        if (apply.canEatWhenFull() && !tmpBuild.canEatWhenFull()) to.setAlwaysEdible();

        for (Pair<EffectInstance, Float> effect : apply.getEffects())
            if (!tmpBuild.getEffects().contains(effect))
                to.effect(effect::getLeft, effect.getRight());

        return to;
    }

    private static float grilled(boolean grilled) {
        return (float) (grilled ? ModCheeseConfig.grilledFoodMultiplier.get() : 1.0f);
    }
}
