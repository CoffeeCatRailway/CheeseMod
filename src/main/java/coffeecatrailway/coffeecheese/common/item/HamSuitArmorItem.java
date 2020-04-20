package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.client.render.entity.model.ModelHamSuit;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 26/07/2019
 */
public class HamSuitArmorItem extends ArmorItem {

    public HamSuitArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (slot != EquipmentSlotType.LEGS)
            return CheeseMod.MOD_ID + ":textures/models/armor/ham_suit.png";
        else
            return "";
    }

    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        if (!itemStack.isEmpty())
            if (itemStack.getItem() instanceof HamSuitArmorItem) {
                ModelHamSuit armorModel = new ModelHamSuit(1.0f, armorSlot);

                armorModel.bipedHeadwear.showModel = armorSlot == EquipmentSlotType.HEAD;

                armorModel.bipedBody.showModel = armorSlot == EquipmentSlotType.CHEST;
                armorModel.bipedRightArm.showModel = armorSlot == EquipmentSlotType.CHEST;
                armorModel.bipedLeftArm.showModel = armorSlot == EquipmentSlotType.CHEST;

                armorModel.isSneak = _default.isSneak;
                armorModel.isChild = _default.isChild;

                return (A) armorModel;
            }
        return null;
    }
}
