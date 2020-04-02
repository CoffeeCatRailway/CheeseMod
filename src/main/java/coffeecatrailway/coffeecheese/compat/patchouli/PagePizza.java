//package coffeecatrailway.coffeecheese.compat.patchouli;
//
//import coffeecatrailway.coffeecheese.common.item.crafting.PizzaOvenRecipe;
//import coffeecatrailway.coffeecheese.registry.ModBlocks;
//import coffeecatrailway.coffeecheese.registry.ModRecipes;
//import com.mojang.blaze3d.platform.GlStateManager;
//import net.minecraft.client.gui.AbstractGui;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.crafting.Ingredient;
//import net.minecraft.util.NonNullList;
//import vazkii.patchouli.client.book.page.abstr.PageDoubleRecipeRegistry;
//
///**
// * @author CoffeeCatRailway
// * Created: 20/02/2020
// */
//public class PagePizza extends PageDoubleRecipeRegistry<PizzaOvenRecipe> {
//
//    public PagePizza() {
//        super(ModRecipes.PIZZA_OVEN);
//    }
//
//    @Override
//    protected void drawRecipe(PizzaOvenRecipe recipe, int recipeX, int recipeY, int mouseX, int mouseY, boolean second) {
//        this.mc.textureManager.bindTexture(this.book.craftingTexture);
//        GlStateManager.enableBlend();
//        AbstractGui.blit(recipeX - 2, recipeY - 2, 0f, 0f, 100, 62, 128, 128);
//
//        AbstractGui.blit(recipeX + 74, recipeY - 2, 76f, 19F, 26, 26, 128, 128);
//        this.parent.renderItemStack(recipeX + 79, recipeY + 2, mouseX, mouseY, new ItemStack(ModBlocks.PIZZA_OVEN.get()));
//
//        this.parent.drawCenteredStringNoShadow(this.getTitle(second), 58, recipeY - 10, this.book.headerColor);
//        this.parent.renderItemStack(recipeX + 79, recipeY + 22, mouseX, mouseY, recipe.getRecipeOutput());
//        NonNullList<Ingredient> ingredients = recipe.getIngredients();
//
//        for (int i = 0; i < ingredients.size(); ++i)
//            this.parent.renderIngredient(recipeX + i % 3 * 19 + 3, recipeY + i / 3 * 19 + 3, mouseX, mouseY, ingredients.get(i));
//    }
//
//    @Override
//    protected ItemStack getRecipeOutput(PizzaOvenRecipe recipe) {
//        if (recipe == null)
//            return ItemStack.EMPTY;
//
//        return recipe.getRecipeOutput();
//    }
//
//    @Override
//    protected int getRecipeHeight() {
//        return 78;
//    }
//}
