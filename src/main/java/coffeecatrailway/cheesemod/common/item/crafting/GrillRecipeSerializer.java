package coffeecatrailway.cheesemod.common.item.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
public class GrillRecipeSerializer<T extends GrillRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final GrillRecipeSerializer.IFactory<T> factory;
    private final int cookTime;

    public GrillRecipeSerializer(GrillRecipeSerializer.IFactory<T> factory, int cookTime) {
        this.factory = factory;
        this.cookTime = cookTime;
    }

    @Override
    public T read(ResourceLocation id, JsonObject json) {
        JsonElement ingredientJson = JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(ingredientJson);

        if (!json.has("result"))
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack stack;
        if (json.get("result").isJsonObject())
            stack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
        else {
            String result = JSONUtils.getString(json, "result");
            ResourceLocation resultId = new ResourceLocation(result);
            stack = new ItemStack(Registry.ITEM.getValue(resultId).orElseThrow(() -> new IllegalStateException("Item: " + result + " does not exist")));
        }
        float experience = JSONUtils.getFloat(json, "experience", 0.0F);
        int cookTime = JSONUtils.getInt(json, "cookingtime", this.cookTime);
        int oil = JSONUtils.getInt(json, "oil", 0);
        return this.factory.create(id, ingredient, stack, experience, cookTime, oil);
    }

    @Override
    public T read(ResourceLocation id, PacketBuffer buffer) {
        Ingredient ingredient = Ingredient.read(buffer);
        ItemStack stack = buffer.readItemStack();
        float experience = buffer.readFloat();
        int cookTime = buffer.readVarInt();
        int oil = buffer.readVarInt();
        return this.factory.create(id, ingredient, stack, experience, cookTime, oil);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        recipe.getIngredients().get(0).write(buffer);
        buffer.writeItemStack(recipe.getRecipeOutput());
        buffer.writeFloat(recipe.getExperience());
        buffer.writeVarInt(recipe.getCookTime());
        buffer.writeVarInt(recipe.getOil());
    }

    public interface IFactory<T extends AbstractCookingRecipe> {
        T create(ResourceLocation id, Ingredient ingredient, ItemStack stack, float experience, int cookTime, int oil);
    }
}
