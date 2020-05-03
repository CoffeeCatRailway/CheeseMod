package coffeecatrailway.coffeecheese.common.item.crafting;

import coffeecatrailway.coffeecheese.ModCheeseConfig;
import coffeecatrailway.coffeecheese.common.item.StackableFoodItem;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 2/05/2020
 */
public abstract class StackableFoodRecipe extends SpecialRecipe {

    private final Tag<Item> sideTag;
    private final Supplier<? extends Item> defaultItem;

    public StackableFoodRecipe(ResourceLocation id, Tag<Item> sideTag, Supplier<? extends Item> defaultItem) {
        super(id);
        this.sideTag = sideTag;
        this.defaultItem = defaultItem;
    }

    @Override
    public boolean matches(CraftingInventory inv, World world) {
        ItemStack hasStack = this.hasSideStack(inv);
        if (hasStack != ItemStack.EMPTY) {
            StackableFoodItem side = (StackableFoodItem) this.getCraftingResult(inv).getItem();
            int maxSideCount = side.foodProperties.hasTwoSides() ? 2 : 1;
            int sideCount = 0;
            int ingredientCount = 0;

            for (int i = 0; i < inv.getSizeInventory(); i++) {
                ItemStack stack = inv.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    if (!stack.isFood()) {
                        return false;
                    } else {
                        if (this.sideTag.contains(stack.getItem()))
                            sideCount++;
                        else
                            ingredientCount++;
                    }
                }
            }

            return sideCount == maxSideCount && ingredientCount > 0 && ingredientCount <= ModCheeseConfig.stackableFoodIngredientCount.get();
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        ItemStack hasStack = this.hasSideStack(inv);
        if (hasStack != ItemStack.EMPTY) {
            ItemStack stack = new ItemStack(this.defaultItem.get());
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                ItemStack ingredient = inv.getStackInSlot(i);
                if (!ingredient.isEmpty() && ingredient.isFood())
                    if (!this.sideTag.contains(ingredient.getItem()))
                        stack = StackableFoodItem.addIngredient(stack, ingredient);
            }
            return stack;
        }
        return hasStack;
    }

    private ItemStack hasSideStack(CraftingInventory inv) {
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (this.sideTag.contains(stack.getItem()))
                return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= 2 && height >= 2;
    }
}
