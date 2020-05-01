package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.item.crafting.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
public class ModRecipes {

    /// Recipe Types ///
    public static IRecipeType<GrillRecipe> GRILLING;
    public static IRecipeType<MelterRecipe> MELTING;
    public static IRecipeType<PizzaOvenRecipe> PIZZA_OVEN;

    /// Recipe Serializers ///
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, CheeseMod.MOD_ID);

    public static final RegistryObject<GrillRecipeSerializer<GrillRecipe>> GRILLING_SERIALIZER = RECIPE_SERIALIZERS.register("grilling", () -> new GrillRecipeSerializer<>(GrillRecipe::new, 200));
    public static final RegistryObject<MelterRecipeSerializer<MelterRecipe>> MELTING_SERIALIZER = RECIPE_SERIALIZERS.register("melting", () -> new MelterRecipeSerializer<>(MelterRecipe::new, 200));
    public static final RegistryObject<PizzaOvenRecipeSerializer<PizzaOvenRecipe>> PIZZA_OVEN_SERIALIZER = RECIPE_SERIALIZERS.register("pizza_oven", () -> new PizzaOvenRecipeSerializer<>(PizzaOvenRecipe::new, 200));

    public static final RegistryObject<SpecialRecipeSerializer<SandwichRecipe>> SANDWICH_SERIALIZER = RECIPE_SERIALIZERS.register("sandwich", () -> new SpecialRecipeSerializer<>(SandwichRecipe::new));

    public static void registerRecipeType() {
        GRILLING = register("grilling");
        MELTING = register("melting");
        PIZZA_OVEN = register("pizza_oven");
    }

    private static <R extends IRecipe<?>> IRecipeType<R> register(String name) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(name), new IRecipeType<R>() {
            public String toString() {
                return name;
            }
        });
    }
}
