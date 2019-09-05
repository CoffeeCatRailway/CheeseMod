package coffeecatrailway.cheesemod.jei;

import coffeecatrailway.cheesemod.client.gui.screen.MelterScreen;
import coffeecatrailway.cheesemod.core.ModBlocks;
import coffeecatrailway.cheesemod.item.crafting.MelterRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 5/09/2019
 */
public class MelterRecipeCategory implements IRecipeCategory<MelterRecipe> {

    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawableAnimated flame;
    private final IDrawableAnimated arrow;

    private final IDrawable fluidMetor;

    private final String localizedName;

    public MelterRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(MelterScreen.GUI_TEXTURE, 42, 6, 89, 68);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.MELTER));

        flame = guiHelper.drawableBuilder(MelterScreen.GUI_TEXTURE, 176, 0, 14, 14)
                .buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
        arrow = guiHelper.drawableBuilder(MelterScreen.GUI_TEXTURE, 176, 14, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);

        fluidMetor = guiHelper.createDrawable(MelterScreen.GUI_TEXTURE, 176, 32, 32, 64);

        localizedName = ModBlocks.MELTER.getNameTextComponent().getString();
    }

    @Override
    public ResourceLocation getUid() {
        return CheeseModPlugin.MELTER;
    }

    @Override
    public Class<? extends MelterRecipe> getRecipeClass() {
        return MelterRecipe.class;
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
    public void setIngredients(MelterRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(recipe.getResult().getFluid().getFilledBucket()));
        ingredients.setOutput(VanillaTypes.FLUID, recipe.getResult());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, MelterRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        guiItemStacks.init(0, true, 1, 6);
        guiItemStacks.set(ingredients);
    }

    @Override
    public void draw(MelterRecipe recipe, double mouseX, double mouseY) {
        flame.draw(2, 28);
        arrow.draw(25, 26);

        fluidMetor.draw(55, 2);
    }

    @Override
    public List<String> getTooltipStrings(MelterRecipe recipe, double mouseX, double mouseY) {
        List<String> tooltips = new ArrayList<>();
        if (mouseX >= 55 && mouseY >= 2)
            if (mouseX <= 86 && mouseY <= 65)
                tooltips.add(MelterScreen.getFormatedFluidString(recipe.getResult().getFluid(), recipe.getResult().getAmount()));
        return tooltips;
    }
}
