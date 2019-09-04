package coffeecatrailway.cheesemod.item.crafting;

import coffeecatrailway.cheesemod.core.ModBlocks;
import coffeecatrailway.cheesemod.core.ModRecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
public class MelterRecipe implements IRecipe<IInventory> {

    protected final IRecipeType<?> type;
    protected final ResourceLocation id;
    protected final Ingredient ingredient;
    private final FluidStack result;
    protected final int cookTime;

    public MelterRecipe(ResourceLocation id, Ingredient ingredient, FluidStack result, int cookTime) {
        type = ModRecipeTypes.MELTING;
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.cookTime = cookTime;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public FluidStack getResult() {
        return result;
    }

    @Override
    public boolean matches(IInventory inv, World world) {
        return this.ingredient.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeType<?> getType() {
        return this.type;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.MELTER);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.MELTING_SERIALIZER;
    }
}
