package coffeecatrailway.coffeecheese.common.item.crafting;

import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
public class PizzaOvenRecipe implements IRecipe<IInventory> {

    private final ResourceLocation id;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;
    private final float experience;
    private final int cookTime;
    private final boolean isSimple;

    public PizzaOvenRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, ItemStack result, float experience, int cookTime) {
        this.id = id;
        this.ingredients = ingredients;
        this.result = result;
        this.experience = experience;
        this.cookTime = cookTime;
        this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
    }

    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.PIZZA_OVEN.get());
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.PIZZA_OVEN_SERIALIZER.get();
    }

    public ItemStack getResult() {
        return this.result;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for (int j = 0; j < 9; ++j) {
            ItemStack itemstack = inv.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (isSimple)
                    recipeitemhelper.func_221264_a(itemstack, 1);
                else inputs.add(itemstack);
            }
        }

        return i == this.ingredients.size() && (isSimple ? recipeitemhelper.canCraft(this, null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs, this.ingredients) != null);
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.result.copy();
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
//        NonNullList<Ingredient> nonnulllist = NonNullList.create();
//        for (int i = 0; i < 9; i++)
//            if (i < ingredients.size())
//                nonnulllist.add(ingredients.get(i));
//            else
//                nonnulllist.add(Ingredient.EMPTY);
        return this.ingredients;
    }

    /**
     * Gets the experience of this recipe
     */
    public float getExperience() {
        return this.experience;
    }

    /**
     * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
     * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
     */
    @Override
    public ItemStack getRecipeOutput() {
        return this.result;
    }

    /**
     * Recipes with equal group are combined into one button in the recipe book
     */
    @Override
    public String getGroup() {
        return "";
    }

    /**
     * Gets the cook time in ticks
     */
    public int getCookTime() {
        return this.cookTime;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.PIZZA_OVEN;
    }
}
