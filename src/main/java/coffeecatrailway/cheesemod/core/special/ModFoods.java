package coffeecatrailway.cheesemod.core.special;

import coffeecatrailway.cheesemod.ModConfig;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
public class ModFoods {

    private static final double grilledMul = ModConfig.MODIFIERS.grilledFoodMultiplier.get();

    public static final Food BLOCK_O_CHEESE = (new Food.Builder()).hunger(6).saturation(1f).fastToEat().build();
    public static final Food CHEESE_SLICE = (new Food.Builder()).hunger(2).saturation(0.5f).fastToEat().build();

    public static final Food INGREDIENT = (new Food.Builder()).hunger(1).saturation(0.5f).build();

    public static final Food BACON = (new Food.Builder()).hunger(2).saturation(1.0f).meat().build();
    public static final Food BACON_COOKED = (new Food.Builder()).hunger(BACON.getHealing() + 3).saturation(BACON.getSaturation() + 0.5f).meat().fastToEat().build();

    public static final Food BREAD_SLICE = (new Food.Builder()).hunger(Foods.BREAD.getHealing() / 3).saturation(Foods.BREAD.getSaturation() / 3.0f).build();

    public static final Food DOUGH = (new Food.Builder()).hunger(INGREDIENT.getHealing() * 3).saturation(INGREDIENT.getSaturation() * 3.0f).build();

    public static final Food EGG = (new Food.Builder()).hunger(3).saturation(1.0f).build();
    public static final Food EGG_COOKED = (new Food.Builder()).hunger(EGG.getHealing() * 2).saturation(EGG.getSaturation() * 2.0f).build();
    public static final Food EGG_GREEN = (new Food.Builder()).hunger(EGG.getHealing()).saturation(EGG.getSaturation()).effect(new EffectInstance(Effects.HUNGER, 400, 1), 1.0f).build();

    public static final Food HAM = (new Food.Builder()).hunger(Foods.PORKCHOP.getHealing() / 3).saturation(Foods.PORKCHOP.getSaturation() / 3.0f).meat().build();
    public static final Food HAM_COOKED = (new Food.Builder()).hunger(HAM.getHealing() * 2).saturation(HAM.getSaturation() * 2.0f).meat().build();
    public static final Food HAM_GREEN = (new Food.Builder()).hunger(HAM.getHealing()).saturation(HAM.getSaturation()).effect(new EffectInstance(Effects.HUNGER, 400, 1), 1.0f).meat().build();

    public static final Food TOAST = (new Food.Builder()).hunger(BREAD_SLICE.getHealing() * 3).saturation(BREAD_SLICE.getSaturation() * 3.0f).build();
    public static final Food TOAST_FRENCH = (new Food.Builder()).hunger(TOAST.getHealing() + EGG.getHealing()).saturation(TOAST.getSaturation() + EGG.getSaturation()).build();
    public static final Food TOAST_BACON = (new Food.Builder()).hunger(TOAST.getHealing() + BACON_COOKED.getHealing()).saturation(TOAST.getSaturation() + BACON_COOKED.getSaturation()).build();

    public static final Food CRACKER = (new Food.Builder()).hunger(5).saturation(2.0f).build();
    public static final Food CRACKER_CHEESE = (new Food.Builder()).hunger(CRACKER.getHealing() + CHEESE_SLICE.getHealing()).saturation(CRACKER.getSaturation() + CHEESE_SLICE.getSaturation()).build();
    public static final Food CRACKER_CHEESE_GRILLED = (new Food.Builder()).hunger((int) (CRACKER_CHEESE.getHealing() * grilledMul)).saturation((float) (CRACKER_CHEESE.getSaturation() * grilledMul)).build();
    public static final Food CRACKER_HAM = (new Food.Builder()).hunger(CRACKER.getHealing() + HAM.getHealing()).saturation(CRACKER.getSaturation() + HAM.getSaturation()).build();
    public static final Food CRACKER_HAM_GRILLED = (new Food.Builder()).hunger((int) (CRACKER_HAM.getHealing() * grilledMul)).saturation((float) (CRACKER_HAM.getSaturation() * grilledMul)).build();
    public static final Food CRACKER_CHEESE_HAM = (new Food.Builder()).hunger(CRACKER_CHEESE.getHealing() + CRACKER_HAM.getHealing()).saturation(CRACKER_CHEESE.getSaturation() + CRACKER_HAM.getSaturation()).build();
    public static final Food CRACKER_CHEESE_HAM_GRILLED = (new Food.Builder()).hunger(CRACKER_CHEESE_GRILLED.getHealing() + CRACKER_HAM_GRILLED.getHealing()).saturation(CRACKER_CHEESE_GRILLED.getSaturation() + CRACKER_HAM_GRILLED.getSaturation()).build();

    public static final Food TOASTIE_CHEESE = (new Food.Builder()).hunger(TOAST.getHealing() + CHEESE_SLICE.getHealing()).saturation(TOAST.getSaturation() + CHEESE_SLICE.getSaturation()).build();
    public static final Food TOASTIE_CHEESE_GRILLED = (new Food.Builder()).hunger((int) (TOASTIE_CHEESE.getHealing() * grilledMul)).saturation((float) (TOASTIE_CHEESE.getSaturation() * grilledMul)).build();
    public static final Food TOASTIE_CHEESE_HAM = (new Food.Builder()).hunger(TOASTIE_CHEESE.getHealing() + HAM.getHealing()).saturation(TOASTIE_CHEESE.getSaturation() + HAM.getSaturation()).build();
    public static final Food TOASTIE_CHEESE_HAM_GRILLED = (new Food.Builder()).hunger((int) (TOASTIE_CHEESE_HAM.getHealing() * grilledMul)).saturation((float) (TOASTIE_CHEESE_HAM.getHealing() * grilledMul)).build();
    public static final Food TOASTIE_CHEESE_HAM_COOKED = (new Food.Builder()).hunger(TOASTIE_CHEESE.getHealing() + HAM_COOKED.getHealing()).saturation(TOASTIE_CHEESE.getSaturation() + HAM_COOKED.getSaturation()).build();
    public static final Food TOASTIE_CHEESE_HAM_COOKED_GRILLED = (new Food.Builder()).hunger((int) (TOASTIE_CHEESE_HAM_COOKED.getHealing() * grilledMul)).saturation((float) (TOASTIE_CHEESE_HAM_COOKED.getHealing() * grilledMul)).build();

    public static final Food PINEAPPLE = (new Food.Builder()).hunger(12).saturation(5.0f).build();
    public static final Food PINEAPPLE_RING = (new Food.Builder()).hunger(PINEAPPLE.getHealing() / 4).saturation(PINEAPPLE.getSaturation() / 4.0f).build();
    public static final Food PINEAPPLE_BIT = (new Food.Builder()).hunger(PINEAPPLE_RING.getHealing() / 3).saturation(PINEAPPLE_RING.getSaturation() / 3.0f).build();

    public static final Food PIZZA_CHEESE = (new Food.Builder()).hunger(DOUGH.getHealing() + CHEESE_SLICE.getHealing() * 2).saturation(DOUGH.getSaturation() + CHEESE_SLICE.getSaturation() * 2.0f).build();
    public static final Food PIZZA_CHEESE_COOKED = (new Food.Builder()).hunger((int) (PIZZA_CHEESE.getHealing() * grilledMul)).saturation((float) (PIZZA_CHEESE.getSaturation() * grilledMul)).build();
    public static final Food PIZZA_CHEESE_HAM = (new Food.Builder()).hunger(PIZZA_CHEESE.getHealing() + HAM.getHealing()).saturation(PIZZA_CHEESE.getSaturation() + HAM.getSaturation()).build();
    public static final Food PIZZA_CHEESE_HAM_COOKED = (new Food.Builder()).hunger((int) (PIZZA_CHEESE_HAM.getHealing() * grilledMul)).saturation((float) (PIZZA_CHEESE_HAM.getSaturation() * grilledMul)).build();
    public static final Food PIZZA_CHEESE_HAM_PINEAPPLE = (new Food.Builder()).hunger(PIZZA_CHEESE_HAM.getHealing() + PINEAPPLE.getHealing()).saturation(PIZZA_CHEESE_HAM.getSaturation() + PINEAPPLE.getSaturation()).build();
    public static final Food PIZZA_CHEESE_HAM_PINEAPPLE_COOKED = (new Food.Builder()).hunger((int) (PIZZA_CHEESE_HAM_PINEAPPLE.getHealing() * grilledMul)).saturation((float) (PIZZA_CHEESE_HAM_PINEAPPLE.getSaturation() * grilledMul)).build();
}
