package coffeecatrailway.coffeecheese.compat.jei;

import coffeecatrailway.coffeecheese.client.gui.screen.PizzaOvenScreen;
import coffeecatrailway.coffeecheese.common.item.crafting.PizzaOvenRecipe;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 26/08/2019
 */
public class PizzaOvenRecipeCategory implements IRecipeCategory<PizzaOvenRecipe> {

    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawableAnimated flame;
    private final IDrawableAnimated arrow;

    private final String localizedName;

    public PizzaOvenRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(PizzaOvenScreen.GUI_TEXTURE, 10, 13, 146, 56);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.PIZZA_OVEN.get()));

        flame = guiHelper.drawableBuilder(PizzaOvenScreen.GUI_TEXTURE, 176, 0, 14, 14)
                .buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
        arrow = guiHelper.drawableBuilder(PizzaOvenScreen.GUI_TEXTURE, 176, 14, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);

        localizedName = ModBlocks.PIZZA_OVEN.get().getNameTextComponent().getString();
    }

    @Override
    public ResourceLocation getUid() {
        return JEIModPlugin.PIZZA_OVEN;
    }

    @Override
    public Class<? extends PizzaOvenRecipe> getRecipeClass() {
        return PizzaOvenRecipe.class;
    }

    @Override
    public String getTitle() {
        return this.localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(PizzaOvenRecipe recipe, IIngredients ingredients) {
        List<Ingredient> inputs = new ArrayList<>(recipe.getIngredients());
        ingredients.setInputIngredients(inputs);
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResult());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, PizzaOvenRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                guiItemStacks.init(j * 3 + i, true, 1 + j * 18, 1 + i * 18);
        guiItemStacks.init(10, false, 123, 18);
        guiItemStacks.set(ingredients);
    }

    @Override
    public void draw(PizzaOvenRecipe recipe, double mouseX, double mouseY) {
        flame.draw(67, 21);
        arrow.draw(91, 20);
    }
}
