package coffeecatrailway.cheesemod.client.model;

import coffeecatrailway.cheesemod.ModConfig;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * ModelCheeseSuit - CoffeeCatTeam - Duncan
 * Created using Tabula 7.0.0
 */
public class ModelCheeseSuit extends BipedModel<LivingEntity> {

    public RendererModel cheese_hat1;
    public RendererModel cheese_hat2;

    public RendererModel shoulder_right;
    public RendererModel fist_right;
    public RendererModel binding_right;

    public RendererModel shoulder_left;
    public RendererModel fist_left;
    public RendererModel binding_left;

    public RendererModel binding_shoulder1;
    public RendererModel binding_shoulder2;

    public RendererModel binding_waist1;
    public RendererModel binding_waist2;
    public RendererModel binding_waist3;
    public RendererModel binding_waist4;

    public RendererModel binding_chest1;
    public RendererModel binding_chest2;
    public RendererModel binding_chest3;
    public RendererModel binding_chest4;

    public RendererModel binding;
    public RendererModel hat;
    public RendererModel arm_left;
    public RendererModel arm_right;

    private EquipmentSlotType armorSlot;

    public ModelCheeseSuit(float scale, EquipmentSlotType armorSlot) {
        super(scale, 0, 64, 32);
        this.armorSlot = armorSlot;

        textureWidth = 64;
        textureHeight = 32;

        binding = new RendererModel(this);
        hat = new RendererModel(this);
        arm_left = new RendererModel(this);
        arm_right = new RendererModel(this);

        double s = ModConfig.MODIFIERS.cheeseSuitScale.get();
        double s_binding = ModConfig.MODIFIERS.cheeseSuitBindingScale.get();

        // Hat
        cheese_hat1 = new RendererModel(this, 0, 0);
        cheese_hat1.addBox(-4.0f, -8.5f, -4.0f, 9, 4, 9, (float) s);
        cheese_hat1.setRotationPoint(-0.5f, -0.5f, -0.5f);
        cheese_hat1.mirror = true;
        setRotation(cheese_hat1, 0f, 0f, 0f);

        cheese_hat2 = new RendererModel(this, 0, 0);
        cheese_hat2.addBox(-4.0f, -10.0f, -4.0f, 9, 4, 9, (float) (s - s_binding));
        cheese_hat2.setRotationPoint(-0.5f, -0.5f, -0.5f);
        cheese_hat2.mirror = true;
        setRotation(cheese_hat2, 0f, 0f, 0f);

        // Right Arm
        shoulder_right = new RendererModel(this, 44, 0);
        shoulder_right.addBox(1.0f, -2.0f, -2.5f, 5, 4, 5, (float) s);
        shoulder_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        shoulder_right.mirror = true;
        setRotation(shoulder_right, 0.0f, 0.0f, -0.24434609527920614f);

        fist_right = new RendererModel(this, 44, 0);
        fist_right.addBox(1.5f, 6.5f, -2.5f, 5, 4, 5, (float) s);
        fist_right.setRotationPoint(-5.1f, 0.0f, 0.0f);
        fist_right.mirror = true;
        setRotation(fist_right, 0.0f, 0.0f, 0.0f);

        binding_right = new RendererModel(this, 46, 13);
        binding_right.addBox(7.5f, 2.0f, -0.5f, 1, 7, 1, (float) (s - s_binding));
        binding_right.setRotationPoint(-5.0f, -2.0f, 0.0f);
        binding_right.mirror = true;
        setRotation(binding_right, 0.0f, 0.0f, 0.0f);

        // Left Arm
        shoulder_left = new RendererModel(this, 44, 0);
        shoulder_left.addBox(-6.0f, -2.0f, -2.5f, 5, 4, 5, (float) s);
        shoulder_left.setRotationPoint(5.0f, 0.0f, 0.0f);
        shoulder_left.mirror = true;
        setRotation(shoulder_left, 0.0f, 0.0f, 0.24434609527920614f);

        fist_left = new RendererModel(this, 44, 0);
        fist_left.addBox(-6.5f, 6.5f, -2.5f, 5, 4, 5, (float) s);
        fist_left.setRotationPoint(5.1f, 0.0f, 0.0f);
        fist_left.mirror = true;
        setRotation(fist_left, 0.0f, 0.0f, 0.0f);

        binding_left = new RendererModel(this, 46, 13);
        binding_left.addBox(-6.0f, 2.0f, -0.5f, 1, 7, 1, (float) (s - s_binding));
        binding_left.setRotationPoint(2.5f, -2.0f, 0.0f);
        binding_left.mirror = true;
        setRotation(binding_left, 0.0f, 0.0f, 0.0f);

        // Chestplate
        binding_shoulder1 = new RendererModel(this, 40, 9);
        binding_shoulder1.addBox(-5.0f, -1.0f, -1.0f, 10, 1, 2, (float) (s - s_binding));
        binding_shoulder1.setRotationPoint(0.0f, 0.0f, 0.0f);
        binding_shoulder1.mirror = true;
        setRotation(binding_shoulder1, 0.0f, 0.0f, 0.0f);

        binding_shoulder2 = new RendererModel(this, 50, 13);
        binding_shoulder2.addBox(-0.5f, -1.1f, -2.1f, 1, 1, 4, (float) (s - s_binding));
        binding_shoulder2.setRotationPoint(0.0f, 0.0f, 0.0f);
        binding_shoulder2.mirror = true;
        setRotation(binding_shoulder2, 0.0f, 0.0f, 0.0f);

        binding_waist1 = new RendererModel(this, 42, 9);
        binding_waist1.addBox(-4.0f, 5.0f, -1.0f, 9, 1, 1, (float) (s - s_binding));
        binding_waist1.setRotationPoint(-0.5f, 0.0f, -1.5f);
        binding_waist1.mirror = true;
        setRotation(binding_waist1, 0.0f, 0.0f, 0.0f);

        binding_waist2 = new RendererModel(this, 42, 9);
        binding_waist2.addBox(-4.0f, 5.0f, -1.0f, 9, 1, 1, (float) (s - s_binding));
        binding_waist2.setRotationPoint(-0.5f, 0.0f, 2.5f);
        binding_waist2.mirror = true;
        setRotation(binding_waist2, 0.0f, 0.0f, 0.0f);

        binding_waist3 = new RendererModel(this, 51, 14);
        binding_waist3.addBox(-4.0f, 5.0f, -1.0f, 1, 1, 3, (float) (s - s_binding));
        binding_waist3.setRotationPoint(-0.5f, 0.0f, -0.5f);
        binding_waist3.mirror = true;
        setRotation(binding_waist3, 0.0f, 0.0f, 0.0f);

        binding_waist4 = new RendererModel(this, 51, 14);
        binding_waist4.addBox(3.9f, 5.0f, -1.0f, 1, 1, 3, (float) (s - s_binding));
        binding_waist4.setRotationPoint(-0.4f, 0.0f, -0.5f);
        binding_waist4.mirror = true;
        setRotation(binding_waist4, 0.0f, 0.0f, 0.0f);

        binding_chest1 = new RendererModel(this, 60, 13);
        binding_chest1.addBox(-4.0f, 0.0f, 0.0f, 1, 5, 1, (float) (s - s_binding));
        binding_chest1.setRotationPoint(-0.5f, 0.0f, -0.5f);
        binding_chest1.mirror = true;
        setRotation(binding_chest1, 0.0f, 0.0f, 0.0f);

        binding_chest2 = new RendererModel(this, 60, 13);
        binding_chest2.addBox(4.0f, 0.0f, 0.0f, 1, 5, 1, (float) (s - s_binding));
        binding_chest2.setRotationPoint(-0.5f, 0.0f, -0.5f);
        binding_chest2.mirror = true;
        setRotation(binding_chest2, 0.0f, 0.0f, 0.0f);

        binding_chest3 = new RendererModel(this, 60, 13);
        binding_chest3.addBox(0.0f, -1.0f, -2.0f, 1, 6, 1, (float) (s - s_binding));
        binding_chest3.setRotationPoint(-0.5f, 0.0f, -0.5f);
        binding_chest3.mirror = true;
        setRotation(binding_chest3, 0.0f, 0.0f, 0.0f);

        binding_chest4 = new RendererModel(this, 60, 13);
        binding_chest4.addBox(0.0f, -1.0f, 2.0f, 1, 6, 1, (float) (s - s_binding));
        binding_chest4.setRotationPoint(-0.5f, 0.0f, -0.5f);
        binding_chest4.mirror = true;
        setRotation(binding_chest4, 0.0f, 0.0f, 0.0f);

        // Hat
        this.hat.addChild(cheese_hat1);
        this.hat.addChild(cheese_hat2);
        this.bipedHeadwear.addChild(hat);

        // Chestplate
        this.binding.addChild(binding_shoulder1);
        this.binding.addChild(binding_shoulder2);

        this.binding.addChild(binding_waist1);
        this.binding.addChild(binding_waist2);
        this.binding.addChild(binding_waist3);
        this.binding.addChild(binding_waist4);

        this.binding.addChild(binding_chest1);
        this.binding.addChild(binding_chest2);
        this.binding.addChild(binding_chest3);
        this.binding.addChild(binding_chest4);
        this.bipedBody.addChild(binding);

        // Arms
        arm_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        this.arm_right.addChild(shoulder_right);
        this.arm_right.addChild(fist_right);
        this.arm_right.addChild(binding_left);
        this.bipedRightArm.addChild(arm_right);

        arm_left.setRotationPoint(5.0f, 0.0f, 0.0f);
        this.arm_left.addChild(shoulder_left);
        this.arm_left.addChild(fist_left);
        this.arm_left.addChild(binding_right);
        this.bipedLeftArm.addChild(arm_left);
    }

    public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (entity instanceof PlayerEntity) {
            super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            this.hat.copyModelAngles(this.bipedHeadwear);
            this.binding.copyModelAngles(this.bipedBody);

            this.arm_right.copyModelAngles(this.bipedRightArm);
            this.arm_left.copyModelAngles(this.bipedLeftArm);
        }
        if (entity instanceof ArmorStandEntity) {
            ArmorStandEntity armorstand = (ArmorStandEntity) entity;
            // Head
            this.hat.rotateAngleX = 0.017453292F * armorstand.getHeadRotation().getX();
            this.hat.rotateAngleY = 0.017453292F * armorstand.getHeadRotation().getY();
            this.hat.rotateAngleZ = 0.017453292F * armorstand.getHeadRotation().getZ();

            // Body
            this.binding.rotateAngleX = 0.017453292F * armorstand.getBodyRotation().getX();
            this.binding.rotateAngleY = 0.017453292F * armorstand.getBodyRotation().getY();
            this.binding.rotateAngleZ = 0.017453292F * armorstand.getBodyRotation().getZ();

            // Right Arm
            this.arm_right.rotateAngleX = 0.017453292F * armorstand.getRightArmRotation().getX();
            this.arm_right.rotateAngleY = 0.017453292F * armorstand.getRightArmRotation().getY();
            this.arm_right.rotateAngleZ = 0.017453292F * armorstand.getRightArmRotation().getZ();

            // Left Arm
            this.arm_left.rotateAngleX = 0.017453292F * armorstand.getLeftArmRotation().getX();
            this.arm_left.rotateAngleY = 0.017453292F * armorstand.getLeftArmRotation().getY();
            this.arm_left.rotateAngleZ = 0.017453292F * armorstand.getLeftArmRotation().getZ();

            this.bipedHeadwear.copyModelAngles(this.bipedHead);
        }

        GlStateManager.pushMatrix();
        if (this.isChild) {
            if (armorSlot == EquipmentSlotType.HEAD) {
                this.hat.render(scale);
            }
            if (armorSlot == EquipmentSlotType.CHEST) {
                this.binding.render(scale);
                this.arm_right.render(scale);
                this.arm_left.render(scale);
            }
        } else {
            if (entity.isSneaking())
                GlStateManager.translatef(0.0f, 0.2f, 0.0f);
            if (armorSlot == EquipmentSlotType.HEAD) {
                this.hat.render(scale);
            }
            if (armorSlot == EquipmentSlotType.CHEST) {
                this.binding.render(scale);
                this.arm_right.render(scale);
                this.arm_left.render(scale);
            }
        }
        GlStateManager.popMatrix();
    }

    private void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
