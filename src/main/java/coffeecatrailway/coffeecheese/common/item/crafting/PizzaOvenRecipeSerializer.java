package coffeecatrailway.coffeecheese.common.item.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
public class PizzaOvenRecipeSerializer<T extends PizzaOvenRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final PizzaOvenRecipeSerializer.IFactory<T> factory;
    private final int cookTime;

    public PizzaOvenRecipeSerializer(PizzaOvenRecipeSerializer.IFactory<T> factory, int cookTime) {
        this.factory = factory;
        this.cookTime = cookTime;
    }

    @Override
    public T read(ResourceLocation id, JsonObject json) {
        JsonArray ingredientJson = JSONUtils.getJsonArray(json, "ingredients");
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (JsonElement element : ingredientJson)
            ingredients.add(Ingredient.deserialize(element));

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
        float experience = JSONUtils.getFloat(json, "experience", 0.0f);
        int cookTime = JSONUtils.getInt(json, "cookingtime", this.cookTime);
        return this.factory.create(id, ingredients, stack, experience, cookTime);
    }

    @Override
    public T read(ResourceLocation id, PacketBuffer buffer) {
        int size = buffer.readVarInt();
        NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
        for (int i = 0; i < size; i++)
            ingredients.add(Ingredient.read(buffer));

        ItemStack stack = buffer.readItemStack();
        float experience = buffer.readFloat();
        int cookTime = buffer.readVarInt();
        return this.factory.create(id, ingredients, stack, experience, cookTime);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeVarInt(recipe.getIngredients().size());
        for (int i = 0; i < recipe.getIngredients().size(); i++)
            recipe.getIngredients().get(i).write(buffer);

        buffer.writeItemStack(recipe.getRecipeOutput());
        buffer.writeFloat(recipe.getExperience());
        buffer.writeVarInt(recipe.getCookTime());
    }

    public interface IFactory<T extends PizzaOvenRecipe> {
        T create(ResourceLocation id, NonNullList<Ingredient> ingredient, ItemStack stack, float experience, int cookTime);
    }
}
