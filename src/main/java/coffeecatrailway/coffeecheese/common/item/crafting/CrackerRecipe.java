package coffeecatrailway.coffeecheese.common.item.crafting;

import coffeecatrailway.coffeecheese.common.ModTags;
import coffeecatrailway.coffeecheese.registry.ModItems;
import coffeecatrailway.coffeecheese.registry.ModRecipes;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;

/**
 * @author CoffeeCatRailway
 * Created: 29/04/2020
 */
public class CrackerRecipe extends StackableFoodRecipe {

    public CrackerRecipe(ResourceLocation id) {
        super(id, ModTags.Items.CRACKER, ModItems.CRACKER);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.CRACKER_SERIALIZER.get();
    }
}
