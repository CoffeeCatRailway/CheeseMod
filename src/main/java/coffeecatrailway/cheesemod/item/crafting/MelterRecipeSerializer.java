package coffeecatrailway.cheesemod.item.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
public class MelterRecipeSerializer<T extends MelterRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final MelterRecipeSerializer.IFactory<T> factory;
    private final int cookTime;

    public MelterRecipeSerializer(MelterRecipeSerializer.IFactory<T> factory, int cookTime) {
        this.factory = factory;
        this.cookTime = cookTime;
    }

    @Override
    public T read(ResourceLocation id, JsonObject json) {
        JsonElement ingredientJson = JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(ingredientJson);

        if (!json.has("result"))
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a object");

        JsonObject resultJson = JSONUtils.getJsonObject(json, "result");
        String fluidKey = JSONUtils.getString(resultJson, "fluid");

        Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidKey));
        if (fluid == null)
            throw new com.google.gson.JsonSyntaxException("Melter recipe result is null! Recipe is: " + id.toString());
        FluidStack result = new FluidStack(fluid, JSONUtils.getInt(resultJson, "amount"));

        int cookTime = JSONUtils.getInt(json, "cookingtime", this.cookTime);
        return this.factory.create(id, ingredient, result, cookTime);
    }

    @Override
    public T read(ResourceLocation id, PacketBuffer buffer) {
        Ingredient ingredient = Ingredient.read(buffer);

        Fluid fluid = ForgeRegistries.FLUIDS.getValue(buffer.readResourceLocation());
        if (fluid == null)
            throw new com.google.gson.JsonSyntaxException("Melter recipe result is null! Recipe is: " + id.toString());
        int amount = buffer.readVarInt();
        FluidStack result = new FluidStack(fluid, amount);

        int cookTime = buffer.readVarInt();
        return this.factory.create(id, ingredient, result, cookTime);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        recipe.getIngredients().get(0).write(buffer);
        buffer.writeResourceLocation(recipe.getResult().getFluid().getRegistryName());
        buffer.writeVarInt(recipe.getResult().getAmount());
        buffer.writeVarInt(recipe.getCookTime());
    }

    public interface IFactory<T extends MelterRecipe> {
        T create(ResourceLocation id, Ingredient ingredient, FluidStack result, int cookTime);
    }
}
