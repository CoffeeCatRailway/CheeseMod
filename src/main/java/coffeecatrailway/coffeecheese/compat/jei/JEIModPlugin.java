package coffeecatrailway.coffeecheese.compat.jei;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.client.gui.screen.GrillScreen;
import coffeecatrailway.coffeecheese.client.gui.screen.MelterScreen;
import coffeecatrailway.coffeecheese.client.gui.screen.PizzaOvenScreen;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 26/08/2019
 */
@JeiPlugin
public class JEIModPlugin implements IModPlugin {

    private static final ResourceLocation UID = CheeseMod.getLocation("plugin/main");
    static final ResourceLocation GRILL = CheeseMod.getLocation("category/grill");
    static final ResourceLocation MELTER = CheeseMod.getLocation("category/melter");
    static final ResourceLocation PIZZA_OVEN = CheeseMod.getLocation("category/pizza_oven");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration reg) {
        IGuiHelper guiHelper = reg.getJeiHelpers().getGuiHelper();
        reg.addRecipeCategories(
                new GrillRecipeCategory(guiHelper),
                new MelterRecipeCategory(guiHelper),
                new PizzaOvenRecipeCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration reg) {
        reg.addRecipes(getRecipesOfType(ModRecipes.GRILLING), GRILL);
        reg.addRecipes(getRecipesOfType(ModRecipes.MELTING), MELTER);
        reg.addRecipes(getRecipesOfType(ModRecipes.PIZZA_OVEN), PIZZA_OVEN);
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration reg) {
        //reg.getCraftingCategory().addCategoryExtension(SandwichRecipe.class, SandwichRecipeExtention::new);
    }

    private static List<IRecipe<?>> getRecipesOfType(IRecipeType<?> type) {
        return Minecraft.getInstance().world.getRecipeManager().getRecipes().stream().filter(rec -> rec.getType() == type).collect(Collectors.toList());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration reg) {
        reg.addRecipeCatalyst(new ItemStack(ModBlocks.GRILL.get()), GRILL);
        reg.addRecipeCatalyst(new ItemStack(ModBlocks.MELTER.get()), MELTER);
        reg.addRecipeCatalyst(new ItemStack(ModBlocks.PIZZA_OVEN.get()), PIZZA_OVEN);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration reg) {
        reg.addRecipeClickArea(GrillScreen.class, 85, 33, 24, 17, GRILL);
        reg.addRecipeClickArea(MelterScreen.class, 68, 33, 24, 17, MELTER);
        reg.addRecipeClickArea(PizzaOvenScreen.class, 101, 33, 24, 17, PIZZA_OVEN);
    }
}
