package coffeecatrailway.coffeecheese.compat.jei;

import coffeecatrailway.coffeecheese.common.item.StackableFoodItem;
import coffeecatrailway.coffeecheese.common.item.crafting.SandwichRecipe;
import coffeecatrailway.coffeecheese.registry.ModItems;
import com.google.common.collect.ImmutableList;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICustomCraftingCategoryExtension;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 4/05/2020
 */
@Deprecated
public class SandwichRecipeExtention implements ICustomCraftingCategoryExtension {

    private final List<List<ItemStack>> inputs;
    private final List<ItemStack> foods;

    public SandwichRecipeExtention(@Nonnull SandwichRecipe recipe) {
        foods = ForgeRegistries.ITEMS.getValues().stream()
                .filter(item -> !(item instanceof StackableFoodItem))
                .map(ItemStack::new)
                .filter(ItemStack::isFood)
                .collect(Collectors.toList());
        inputs = ImmutableList.of(foods, ImmutableList.of(new ItemStack(ModItems.BREAD_SLICE.get())), foods);
    }

    @Override
    public void setIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, inputs);
    }

    @Override
    public void setRecipe(IRecipeLayout layout, IIngredients ingredients) {
        layout.setShapeless();
        IGuiItemStackGroup stackGroup = layout.getItemStacks();
        stackGroup.set(ingredients);

        List<ItemStack> outputs = new ArrayList<>();
        for (ItemStack stack : foods) {
            if (!stack.isEmpty()) {
                ItemStack sandwich = new ItemStack(ModItems.SANDWICH.get());
                StackableFoodItem.addIngredient(sandwich, stack);
                outputs.add(sandwich.copy());
            }
        }
        stackGroup.set(0, outputs);
    }
}
