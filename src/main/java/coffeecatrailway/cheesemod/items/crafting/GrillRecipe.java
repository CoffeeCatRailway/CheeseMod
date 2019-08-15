package coffeecatrailway.cheesemod.items.crafting;

import coffeecatrailway.cheesemod.core.registries.ModBlocks;
import coffeecatrailway.cheesemod.core.registries.ModRecipeTypes;
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

    private final int oil;

    public GrillRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, float experience, int cookTime, int oil) {
        super(ModRecipeTypes.GRILLING, id, group, ingredient, result, experience, cookTime);
        this.oil = oil;
    }

    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.GRILL);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.GRILLING_SERIALIZER;
    }

    public int getOil() {
        return oil;
    }
}
