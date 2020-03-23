package coffeecatrailway.coffeecheese.client.model;

import coffeecatrailway.coffeecheese.ModCheeseConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * ModelHamSuit - CoffeeCatTeam - Duncan Created using Tabula 7.0.0
 */
public class ModelHamSuit extends BipedModel<LivingEntity> {

    public ModelRenderer pig_body;
    public ModelRenderer pig_head;
    public ModelRenderer pig_nose;
    public ModelRenderer shoulder_left;
    public ModelRenderer fist_left;
    public ModelRenderer binding_left;
    public ModelRenderer shoulder_right;
    public ModelRenderer fist_right;
    public ModelRenderer binding_right;

    public ModelRenderer binding;
    public ModelRenderer hat;
    public ModelRenderer arm_left;
    public ModelRenderer arm_right;

    public ModelRenderer leg_right;
    public ModelRenderer leg_left;

    private EquipmentSlotType armorSlot;

    public ModelHamSuit(float scale, EquipmentSlotType armorSlot) {
        super(scale, 0, 64, 32);
        this.armorSlot = armorSlot;

        textureWidth = 64;
        textureHeight = 32;

        binding = new ModelRenderer(this);
        hat = new ModelRenderer(this);
        arm_left = new ModelRenderer(this);
        arm_right = new ModelRenderer(this);

        leg_right = new ModelRenderer(this);
        leg_left = new ModelRenderer(this);

        double s = ModCheeseConfig.hamSuitScale.get();
        double s_binding = ModCheeseConfig.hamSuitBindingScale.get();

        // Hat
        pig_head = new ModelRenderer(this, 0, 0);
        pig_head.addBox(-3.5f, -7.5f, -3.5f, 8, 8, 8, (float) s);
        pig_head.setRotationPoint(-0.5f, -0.5f, -0.5f);
        setRotation(pig_head, 0.0f, 0.0f, 0.0f);

        pig_nose = new ModelRenderer(this, 26, 0);
        pig_nose.addBox(-1.5f, -2.5f, -5.0f, 4, 3, 1, (float) (s - s_binding));
        pig_nose.setRotationPoint(-0.5f, -0.5f, -0.5f);
        setRotation(pig_nose, 0.0f, 0.0f, 0.0f);

        // Right Arm
        shoulder_right = new ModelRenderer(this, 44, 0);
        shoulder_right.addBox(1.0f, -2.0f, -2.5f, 5, 4, 5, (float) s);
        shoulder_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        setRotation(shoulder_right, 0.0f, 0.0f, -0.24434609527920614f);

        fist_right = new ModelRenderer(this, 48, 10);
        fist_right.addBox(1.5f, 5.5f, -2.0f, 4, 6, 4, (float) s);
        fist_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        setRotation(fist_right, 0.0f, 0.0f, 0.0f);

        binding_right = new ModelRenderer(this, 39, 0);
        binding_right.addBox(7.5f, 1.0f, -0.5f, 1, 7, 1, (float) (s - s_binding));
        binding_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        setRotation(binding_right, 0.0f, 0.0f, 0.0f);

        // Left Arm
        shoulder_left = new ModelRenderer(this, 44, 0);
        shoulder_left.addBox(-6.0f, -2.0f, -2.5f, 5, 4, 5, (float) s);
        shoulder_left.setRotationPoint(5.0f, 0.0f, 0.0f);
        setRotation(shoulder_left, 0.0f, 0.0f, 0.24434609527920614f);

        fist_left = new ModelRenderer(this, 48, 10);
        fist_left.addBox(-5.5f, 5.5f, -2.0f, 4, 6, 4, (float) s);
        fist_left.setRotationPoint(5.0f, 0.0f, 0.0f);
        setRotation(fist_left, 0.0f, 0.0f, 0.0f);

        binding_left = new ModelRenderer(this, 39, 0);
        binding_left.addBox(-3.5f, 1.0f, -0.5f, 1, 7, 1, (float) (s - s_binding));
        binding_left.setRotationPoint(0.0f, 0.0f, 0.0f);
        setRotation(binding_left, 0.0f, 0.0f, 0.0f);

        // Chestplate
        pig_body = new ModelRenderer(this, 0, 17);
        pig_body.addBox(-3.0f, -5.0f, -2.5f, 6, 10, 5, 1.3f);
        pig_body.setRotationPoint(0.0f, 5.6f, 0.0f);
        setRotation(pig_body, 0.0f, 0.0f, 0.0f);

        // Legs
        leg_right = new ModelRenderer(this, 48, 10);
        leg_right.addBox(-2.0f, 6.0f, -2.0f, 4, 6, 4, (float) s);
        leg_right.setRotationPoint(-1.9f, 12.0f, 0.0f);
        setRotation(leg_right, 0.0f, 0.0f, 0.0f);

        leg_left = new ModelRenderer(this, 48, 10);
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
    protected Iterable<ModelRenderer> getHeadParts() {
        return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.hat));
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.leg_left, this.leg_right, this.arm_left, this.arm_right, this.binding));
    }

    @Override
    public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.leg_left.copyModelAngles(this.bipedLeftLeg);
        this.leg_right.copyModelAngles(this.bipedRightLeg);
        this.arm_left.copyModelAngles(this.bipedLeftArm);
        this.arm_right.copyModelAngles(this.bipedRightArm);
        this.binding.copyModelAngles(this.bipedBody);
        this.hat.copyModelAngles(this.bipedHead);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        this.leg_left.showModel = visible;
        this.leg_right.showModel = visible;
        this.arm_left.showModel = visible;
        this.arm_right.showModel = visible;
        this.binding.showModel = visible;
        this.hat.showModel = visible;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
