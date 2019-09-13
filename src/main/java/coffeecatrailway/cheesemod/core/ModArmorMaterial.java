package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.core.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 23/07/2019
 */
public enum ModArmorMaterial implements IArmorMaterial {

    /// Cheese ///
    CHEESE("cheese", 17, new int[]{2, 4, 4, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5f, () -> {
        return Ingredient.fromItems(ModItems.CHEESE_METAL_INGOT);
    }),
    GRILLED_CHEESE("grilled_cheese", 20, new int[]{4, 4, 4, 2}, 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.8f, () -> {
        return Ingredient.fromItems(ModItems.GRILLED_CHEESE_METAL_INGOT);
    }),

    /// Ham ///
    HAM_RAW("ham_raw", 10, new int[]{2, 2, 3, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.3f, () -> {
        return Ingredient.fromItems(ModItems.CHEESE_METAL_INGOT);
    }),
    HAM_COOKED("ham_cooked", 10, new int[]{3, 3, 4, 3}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.4f, () -> {
        return Ingredient.fromItems(ModItems.GRILLED_CHEESE_METAL_INGOT);
    }),

    /// Food Suit ///
    CHEESE_SUIT("cheese_suit", 15, new int[]{2, 1, 1, 1}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5f, () -> {
        return Ingredient.fromItems(ModItems.CHEESE_SLICE);
    }),
    HAM_SUIT("ham_suit", 15, new int[]{2, 2, 2, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5f, () -> {
        return Ingredient.fromItems(ModItems.CHEESE_SLICE);
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyLoadBase<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = new LazyLoadBase<>(repairMaterial);
    }

    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }
}
