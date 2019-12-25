package coffeecatrailway.coffeecheese.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 20/07/2019
 */
public enum ModItemTier implements IItemTier {

    CHEESE_METAL(1, 150, 4.0f, 2.0f, 5, () -> Ingredient.fromItems(ModItems.CHEESE_METAL_INGOT.get())),
    GRILLED_CHEESE_METAL(2, 200, 5.2f, 2.5f, 8, () -> Ingredient.fromItems(ModItems.GRILLED_CHEESE_METAL_INGOT.get())),

    HAM_RAW_METAL(1, 100, 3.5f, 2.3f, 6, () -> Ingredient.fromItems(ModItems.HAM_RAW_METAL_INGOT.get())),
    HAM_COOKED_METAL(2, 170, 4.0f, 2.6f, 8, () -> Ingredient.fromItems(ModItems.HAM_COOKED_METAL_INGOT.get()));

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadBase<Ingredient> repairMaterial;

    ModItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyLoadBase<>(repairMaterialIn);
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
}
