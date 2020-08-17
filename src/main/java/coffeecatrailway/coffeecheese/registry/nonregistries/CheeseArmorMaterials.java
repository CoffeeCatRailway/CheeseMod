package coffeecatrailway.coffeecheese.registry.nonregistries;

import coffeecatrailway.coffeecheese.registry.CheeseItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 24/07/2020
 */
public enum CheeseArmorMaterials implements IArmorMaterial {

    CHEESE("cheese", 17, new int[]{2, 4, 4, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, .5f, 0f, () -> {
        return Ingredient.fromItems(CheeseItems.CHEESE_METAL_INGOT.get());
    }),
    GRILLED_CHEESE("grilled_cheese", 20, new int[]{4, 4, 4, 2}, 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.8f, 0f, () -> {
        return Ingredient.fromItems(CheeseItems.GRILLED_CHEESE_METAL_INGOT.get());
    }),

    /// Ham ///
    HAM_RAW("ham_raw", 10, new int[]{2, 2, 3, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, .3f, 0f, () -> {
        return Ingredient.fromItems(CheeseItems.CHEESE_METAL_INGOT.get());
    }),
    HAM_COOKED("ham_cooked", 10, new int[]{3, 3, 4, 3}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, .4f, 0f, () -> {
        return Ingredient.fromItems(CheeseItems.GRILLED_CHEESE_METAL_INGOT.get());
    }),

    CHEESE_SUIT("cheese_suit", 15, new int[]{2, 1, 1, 1}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, .5f, 0f, () -> {
        return Ingredient.fromItems(CheeseItems.CHEESE_SLICE.get());
    }),
    HAM_SUIT("ham_suit", 15, new int[]{2, 2, 2, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, .5f, 0f, () -> {
        return Ingredient.fromItems(CheeseItems.HAM_RAW.get());
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;

    CheeseArmorMaterials(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
}
