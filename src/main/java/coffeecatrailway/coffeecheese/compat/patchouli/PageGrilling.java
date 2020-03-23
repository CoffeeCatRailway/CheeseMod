package coffeecatrailway.coffeecheese.compat.patchouli;

import coffeecatrailway.coffeecheese.client.gui.screen.GrillScreen;
import coffeecatrailway.coffeecheese.common.item.crafting.GrillRecipe;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModRecipes;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;
import vazkii.patchouli.client.book.gui.GuiBook;
import vazkii.patchouli.client.book.page.abstr.PageDoubleRecipeRegistry;

/**
 * @author CoffeeCatRailway
 * Created: 20/02/2020
 */
public class PageGrilling extends PageDoubleRecipeRegistry<GrillRecipe> {

    public PageGrilling() {
        super(ModRecipes.GRILLING);
    }

    @Override
    protected void drawRecipe(GrillRecipe recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {
        mc.textureManager.bindTexture(book.craftingTexture);
        GlStateManager.enableBlend();
        AbstractGui.blit(recipeX, recipeY + 10, 11, 71, 96, 24, 128, 128);

        AbstractGui.blit(recipeX + 35, recipeY + 29, 76f, 19F, 26, 26, 128, 128);
        this.parent.renderItemStack(recipeX + 40, recipeY + 34, mouseX, mouseY, new ItemStack(ModBlocks.GRILL.get()));

        parent.drawCenteredStringNoShadow(getTitle(second), GuiBook.PAGE_WIDTH / 2, recipeY - 10, book.headerColor);
        String oil = GrillScreen.getFormatedOilString(recipe.getOil());
        parent.drawCenteredStringNoShadow(oil.substring(0, oil.length() - 7), GuiBook.PAGE_WIDTH / 2, recipeY, book.headerColor);

        parent.renderIngredient(recipeX + 4, recipeY + 14, mouseX, mouseY, recipe.getIngredients().get(0));
        parent.renderItemStack(recipeX + 76, recipeY + 14, mouseX, mouseY, recipe.getRecipeOutput());
    }

    @Override
    protected ItemStack getRecipeOutput(GrillRecipe recipe) {
        return recipe == null ? ItemStack.EMPTY : recipe.getRecipeOutput();
    }

    @Override
    protected int getRecipeHeight() {
        return 68;
    }
}
