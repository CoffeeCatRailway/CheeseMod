package coffeecatrailway.coffeecheese.common.item.crafting;

import coffeecatrailway.coffeecheese.ModCheeseConfig;
import coffeecatrailway.coffeecheese.common.ModTags;
import coffeecatrailway.coffeecheese.common.item.SandwichItem;
import coffeecatrailway.coffeecheese.registry.ModItems;
import coffeecatrailway.coffeecheese.registry.ModRecipes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 29/04/2020
 */
public class SandwichRecipe extends SpecialRecipe {

    public SandwichRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inv, World world) {
        boolean oneBreadFlag = false;
        boolean bothBreadFlag = false;
        boolean nonFoodFlag = false;
        int itemCount = 0;

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (ModTags.Items.BREAD_SLICE.contains(stack.getItem()) && !oneBreadFlag) {
                    oneBreadFlag = true;
                } else if (ModTags.Items.BREAD_SLICE.contains(stack.getItem()) && oneBreadFlag && !bothBreadFlag) {
                    bothBreadFlag = true;
                } else if (!stack.isFood()) {
                    nonFoodFlag = true;
                } else {
                    itemCount += ModTags.Items.BREAD_SLICE.contains(stack.getItem()) ? 0 : 1;
                }
            }
        }

        return oneBreadFlag && bothBreadFlag && itemCount > 0 && itemCount <= ModCheeseConfig.sandwichIngredientCount.get() && !nonFoodFlag;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        ItemStack sandwich = new ItemStack(ModItems.SANDWICH.get());
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty() && stack.isFood()) {
                if (!ModTags.Items.BREAD_SLICE.contains(stack.getItem()))
                    sandwich = SandwichItem.addIngredient(sandwich, stack);
            }
        }
        return sandwich;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.SANDWICH_SERIALIZER.get();
    }
}
