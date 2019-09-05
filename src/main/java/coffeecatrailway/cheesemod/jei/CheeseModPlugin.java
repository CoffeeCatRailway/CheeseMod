package coffeecatrailway.cheesemod.jei;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.client.gui.screen.GrillScreen;
import coffeecatrailway.cheesemod.client.gui.screen.MelterScreen;
import coffeecatrailway.cheesemod.core.ModBlocks;
import coffeecatrailway.cheesemod.core.ModRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
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
public class CheeseModPlugin implements IModPlugin {

    private static final ResourceLocation UID = CheeseMod.getLocation("plugin/main");
    static final ResourceLocation GRILL = CheeseMod.getLocation("category/grill");
    static final ResourceLocation MELTER = CheeseMod.getLocation("category/melter");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration reg) {
        IGuiHelper guiHelper = reg.getJeiHelpers().getGuiHelper();
        reg.addRecipeCategories(
                new GrillRecipeCategory(guiHelper),
                new MelterRecipeCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration reg) {
        reg.addRecipes(getRecipesOfType(ModRecipeTypes.GRILLING), GRILL);
        reg.addRecipes(getRecipesOfType(ModRecipeTypes.MELTING), MELTER);
    }

    private static List<IRecipe<?>> getRecipesOfType(IRecipeType<?> type) {
        return Minecraft.getInstance().world.getRecipeManager().getRecipes().stream().filter(rec -> rec.getType() == type).collect(Collectors.toList());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration reg) {
        reg.addRecipeCatalyst(new ItemStack(ModBlocks.GRILL), GRILL);
        reg.addRecipeCatalyst(new ItemStack(ModBlocks.MELTER), MELTER);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration reg) {
        reg.addRecipeClickArea(GrillScreen.class, 85, 33, 24, 17, GRILL);
        reg.addRecipeClickArea(MelterScreen.class, 68, 33, 24, 17, MELTER);
    }
}
