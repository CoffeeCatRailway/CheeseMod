package coffeecatrailway.cheesemod.client.jei;

import coffeecatrailway.cheesemod.client.gui.screen.MelterScreen;
import coffeecatrailway.cheesemod.core.ModBlocks;
import coffeecatrailway.cheesemod.common.item.crafting.MelterRecipe;
import com.mojang.blaze3d.platform.GlStateManager;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 5/09/2019
 */
public class MelterRecipeCategory implements IRecipeCategory<MelterRecipe> {

    private final IGuiHelper guiHelper;

    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawableAnimated flame;
    private final IDrawableAnimated arrow;

    private final IDrawable fluidMetor;

    private final String localizedName;

    public MelterRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;

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
        this.flame.draw(2, 28);
        this.arrow.draw(25, 26);

        FluidStack fluidStack = recipe.getResult();
        ResourceLocation fluidTexture = MelterScreen.getFluidTexture(fluidStack.getFluid());
        if (fluidTexture != null) {
            if (fluidStack.getFluid().isEquivalentTo(Fluids.WATER))
                GlStateManager.color4f(0.23922F, 0.42745F, 1.0F, 1.0F);

            int k = (int) MelterScreen.mapFluid(fluidStack.getAmount(), 0, 64) + 2;
            int h = MelterScreen.getFluidTextureHeight(fluidTexture);
            IDrawableStatic fluidDraw = this.guiHelper.drawableBuilder(fluidTexture, 0, 0, 32, k)
                    .setTextureSize(16, h - k).build();
            fluidDraw.draw(55, 2 + this.fluidMetor.getHeight() - k);

            if (fluidStack.getFluid().isEquivalentTo(Fluids.WATER))
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        }

        this.fluidMetor.draw(55, 2);
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
