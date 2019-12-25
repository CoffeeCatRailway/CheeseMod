package coffeecatrailway.coffeecheese.client.model;

import coffeecatrailway.coffeecheese.ModCheeseConfig;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * ModelHamSuit - CoffeeCatTeam - Duncan Created using Tabula 7.0.0
 */
public class ModelHamSuit extends BipedModel<LivingEntity> {

    public RendererModel pig_body;
    public RendererModel pig_head;
    public RendererModel pig_nose;
    public RendererModel shoulder_left;
    public RendererModel fist_left;
    public RendererModel binding_left;
    public RendererModel shoulder_right;
    public RendererModel fist_right;
    public RendererModel binding_right;

    public RendererModel binding;
    public RendererModel hat;
    public RendererModel arm_left;
    public RendererModel arm_right;

    public RendererModel leg_right;
    public RendererModel leg_left;

    private EquipmentSlotType armorSlot;

    public ModelHamSuit(float scale, EquipmentSlotType armorSlot) {
        super(scale, 0, 64, 32);
        this.armorSlot = armorSlot;

        textureWidth = 64;
        textureHeight = 32;

        binding = new RendererModel(this);
        hat = new RendererModel(this);
        arm_left = new RendererModel(this);
        arm_right = new RendererModel(this);

        leg_right = new RendererModel(this);
        leg_left = new RendererModel(this);

        double s = ModCheeseConfig.hamSuitScale.get();
        double s_binding = ModCheeseConfig.hamSuitBindingScale.get();

        // Hat
        pig_head = new RendererModel(this, 0, 0);
        pig_head.addBox(-3.5f, -7.5f, -3.5f, 8, 8, 8, (float) s);
        pig_head.setRotationPoint(-0.5f, -0.5f, -0.5f);
        setRotation(pig_head, 0.0f, 0.0f, 0.0f);

        pig_nose = new RendererModel(this, 26, 0);
        pig_nose.addBox(-1.5f, -2.5f, -5.0f, 4, 3, 1, (float) (s - s_binding));
        pig_nose.setRotationPoint(-0.5f, -0.5f, -0.5f);
        setRotation(pig_nose, 0.0f, 0.0f, 0.0f);

        // Right Arm
        shoulder_right = new RendererModel(this, 44, 0);
        shoulder_right.addBox(1.0f, -2.0f, -2.5f, 5, 4, 5, (float) s);
        shoulder_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        setRotation(shoulder_right, 0.0f, 0.0f, -0.24434609527920614f);

        fist_right = new RendererModel(this, 48, 10);
        fist_right.addBox(1.5f, 5.5f, -2.0f, 4, 6, 4, (float) s);
        fist_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        setRotation(fist_right, 0.0f, 0.0f, 0.0f);

        binding_right = new RendererModel(this, 39, 0);
        binding_right.addBox(7.5f, 1.0f, -0.5f, 1, 7, 1, (float) (s - s_binding));
        binding_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        setRotation(binding_right, 0.0f, 0.0f, 0.0f);

        // Left Arm
        shoulder_left = new RendererModel(this, 44, 0);
        shoulder_left.addBox(-6.0f, -2.0f, -2.5f, 5, 4, 5, (float) s);
        shoulder_left.setRotationPoint(5.0f, 0.0f, 0.0f);
        setRotation(shoulder_left, 0.0f, 0.0f, 0.24434609527920614f);

        fist_left = new RendererModel(this, 48, 10);
        fist_left.addBox(-5.5f, 5.5f, -2.0f, 4, 6, 4, (float) s);
        fist_left.setRotationPoint(5.0f, 0.0f, 0.0f);
        setRotation(fist_left, 0.0f, 0.0f, 0.0f);

        binding_left = new RendererModel(this, 39, 0);
        binding_left.addBox(-3.5f, 1.0f, -0.5f, 1, 7, 1, (float) (s - s_binding));
        binding_left.setRotationPoint(0.0f, 0.0f, 0.0f);
        setRotation(binding_left, 0.0f, 0.0f, 0.0f);

        // Chestplate
        pig_body = new RendererModel(this, 0, 17);
        pig_body.addBox(-3.0f, -5.0f, -2.5f, 6, 10, 5, 1.3f);
        pig_body.setRotationPoint(0.0f, 5.6f, 0.0f);
        setRotation(pig_body, 0.0f, 0.0f, 0.0f);

        // Legs
        leg_right = new RendererModel(this, 48, 10);
        leg_right.addBox(-2.0f, 6.0f, -2.0f, 4, 6, 4, (float) s);
        leg_right.setRotationPoint(-1.9f, 12.0f, 0.0f);
        setRotation(leg_right, 0.0f, 0.0f, 0.0f);

        leg_left = new RendererModel(this, 48, 10);
        leg_left.addBox(-2.0f, 6.0f, -2.0f, 4, 6, 4, (float) s);
        leg_left.setRotationPoint(1.9f, 12.0f, 0.0f);
        setRotation(leg_left, 0.0f, 0.0f, 0.0f);

        // Hat
        this.hat.addChild(pig_head);
        this.hat.addChild(pig_nose);

        // Chestplate
        this.binding.addChild(pig_body);

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

    @Override
    public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
                       float headPitch, float scale) {
        if (entity instanceof PlayerEntity) {
            super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            this.hat.copyModelAngles(this.bipedHeadwear);
            this.binding.copyModelAngles(this.bipedBody);

            this.arm_right.copyModelAngles(this.bipedRightArm);
            this.arm_left.copyModelAngles(this.bipedLeftArm);

            this.leg_right.copyModelAngles(this.bipedRightLeg);
            this.leg_left.copyModelAngles(this.bipedLeftLeg);
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

            // Right Leg
            this.leg_right.rotateAngleX = 0.017453292F * armorstand.getRightLegRotation().getX();
            this.leg_right.rotateAngleY = 0.017453292F * armorstand.getRightLegRotation().getY();
            this.leg_right.rotateAngleZ = 0.017453292F * armorstand.getRightLegRotation().getZ();

            // Left Leg
            this.leg_left.rotateAngleX = 0.017453292F * armorstand.getLeftLegRotation().getX();
            this.leg_left.rotateAngleY = 0.017453292F * armorstand.getLeftLegRotation().getY();
            this.leg_left.rotateAngleZ = 0.017453292F * armorstand.getLeftLegRotation().getZ();

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
            if (armorSlot == EquipmentSlotType.FEET) {
                this.leg_right.render(scale);
                this.leg_left.render(scale);
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
            if (armorSlot == EquipmentSlotType.FEET) {
                this.leg_right.render(scale);
                this.leg_left.render(scale);
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
