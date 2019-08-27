package coffeecatrailway.cheesemod.item.crafting;

import coffeecatrailway.cheesemod.core.ModBlocks;
import coffeecatrailway.cheesemod.core.ModRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
public class GrillRecipe extends AbstractCookingRecipe {

    private final ItemStack result;
    private final int oil;

    public GrillRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, float experience, int cookTime, int oil) {
        super(ModRecipeTypes.GRILLING, id, group, ingredient, result, experience, cookTime);
        this.result = result;
        this.oil = oil;
    }

    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.GRILL);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.GRILLING_SERIALIZER;
    }

    public ItemStack getResult() {
        return result;
    }

    public int getOil() {
        return oil;
    }
}
