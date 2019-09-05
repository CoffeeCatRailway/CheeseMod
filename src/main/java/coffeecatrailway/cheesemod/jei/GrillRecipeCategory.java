package coffeecatrailway.cheesemod.jei;

import coffeecatrailway.cheesemod.client.gui.screen.GrillScreen;
import coffeecatrailway.cheesemod.core.ModBlocks;
import coffeecatrailway.cheesemod.core.ModFluids;
import coffeecatrailway.cheesemod.core.ModItems;
import coffeecatrailway.cheesemod.item.crafting.GrillRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 26/08/2019
 */
public class GrillRecipeCategory implements IRecipeCategory<GrillRecipe> {

    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawableAnimated flame;
    private final IDrawableAnimated arrow;

    private final IDrawableAnimated oil;
    private final IDrawable oilMetor;

    private final String localizedName;

    public GrillRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(GrillScreen.GUI_TEXTURE, 33, 6, 107, 68);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.GRILL));

        flame = guiHelper.drawableBuilder(GrillScreen.GUI_TEXTURE, 176, 0, 14, 14)
                .buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
        arrow = guiHelper.drawableBuilder(GrillScreen.GUI_TEXTURE, 176, 14, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);

        oil = guiHelper.drawableBuilder(GrillScreen.GUI_TEXTURE, 176, 97, 16, 64)
                .buildAnimated(100, IDrawableAnimated.StartDirection.TOP, true);
        oilMetor = guiHelper.createDrawable(GrillScreen.GUI_TEXTURE, 176, 32, 16, 64);

        localizedName = ModBlocks.GRILL.getNameTextComponent().getString();
    }

    @Override
    public ResourceLocation getUid() {
        return CheeseModPlugin.GRILL;
    }

    @Override
    public Class<? extends GrillRecipe> getRecipeClass() {
        return GrillRecipe.class;
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
    public void setIngredients(GrillRecipe recipe, IIngredients ingredients) {
        List<Ingredient> inputs = new ArrayList<>(recipe.getIngredients());
        inputs.add(Ingredient.fromItems(ModItems.OIL_BUCKET));
        ingredients.setInputIngredients(inputs);
        ingredients.setInput(VanillaTypes.FLUID, new FluidStack(ModFluids.OIL_SOURCE, recipe.getOil()));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResult());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, GrillRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        guiItemStacks.init(0, true, 27, 6);
        guiItemStacks.init(2, false, 85, 26);
        guiItemStacks.set(ingredients);
    }

    @Override
    public void draw(GrillRecipe recipe, double mouseX, double mouseY) {
        flame.draw(28, 28);
        arrow.draw(51, 26);

        float experience = recipe.getExperience();
        if (experience > 0.0F) {
            String experienceString = I18n.format("gui.jei.category.smelting.experience", experience);
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.fontRenderer;
            int stringWidth = fontRenderer.getStringWidth(experienceString);
            fontRenderer.drawString(experienceString, (float) (this.background.getWidth() - stringWidth), 0.0F, -8355712);
        }

        oil.draw(2, 2);
        oilMetor.draw(2, 2);
    }

    @Override
    public List<String> getTooltipStrings(GrillRecipe recipe, double mouseX, double mouseY) {
        List<String> tooltips = new ArrayList<>();
        if (mouseX >= 2 && mouseY >= 2)
            if (mouseX <= 17 && mouseY <= 65)
                tooltips.add(GrillScreen.getFormatedOilString(recipe.getOil()));
        return tooltips;
    }
}
