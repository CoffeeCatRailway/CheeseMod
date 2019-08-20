package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.item.crafting.GrillRecipe;
import coffeecatrailway.cheesemod.item.crafting.GrillRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
public class ModRecipeTypes {

    /// Recipe Types ///
    public static IRecipeType<GrillRecipe> GRILLING;

    /// Recipe Serializers ///
    public static GrillRecipeSerializer<GrillRecipe> GRILLING_SERIALIZER;

    public static void registerAll(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        if (!event.getName().equals(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryName())) return;

        GRILLING = register("grilling");

        CheeseMod.LOGGER.info("Recipe types registered");

        GRILLING_SERIALIZER = register("grilling", new GrillRecipeSerializer<>(GrillRecipe::new, 200));

        CheeseMod.LOGGER.info("Recipe serializers registered");
    }

    private static <S extends IRecipeSerializer<R>, R extends IRecipe<?>> S register(String name, S serializer) {
        serializer.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.RECIPE_SERIALIZERS.register(serializer);
        return serializer;
    }

    private static <R extends IRecipe<?>> IRecipeType<R> register(String name) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(name), new IRecipeType<R>() {
            public String toString() {
                return name;
            }
        });
    }
}
