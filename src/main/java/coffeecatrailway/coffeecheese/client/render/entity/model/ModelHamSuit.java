package coffeecatrailway.coffeecheese.client.render.entity.model;

import coffeecatrailway.coffeecheese.ModCheeseConfig;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * ModelHamSuit - CoffeeCatTeam - Duncan Created using Tabula 7.0.0
 */
public class ModelHamSuit extends BipedModel<LivingEntity> {

    public ModelRenderer pig_head;
    public ModelRenderer pig_nose;

    public ModelRenderer shoulder_right;
    public ModelRenderer fist_right;
    public ModelRenderer binding_right;

    public ModelRenderer shoulder_left;
    public ModelRenderer fist_left;
    public ModelRenderer binding_left;

    public ModelHamSuit(float scale, EquipmentSlotType armorSlot) {
        super(scale, 0, 64, 32);

        float scaleSuit = (float) (scale * ModCheeseConfig.hamSuitScale.get());
        float scaleBinding = (float) (scale * (ModCheeseConfig.hamSuitScale.get() - ModCheeseConfig.hamSuitBindingScale.get()));

        // Hat
        pig_head = new ModelRenderer(this);
        pig_head.setRotationPoint(-0.5f, -0.5f, -0.5f);
        pig_head.setTextureOffset(0, 0).addBox(-3.5f, -7.5f, -3.5f, 8, 8, 8, scaleSuit);

        pig_nose = new ModelRenderer(this);
        pig_nose.setRotationPoint(-0.5f, -0.5f, -0.5f);
        pig_nose.setTextureOffset(26, 0).addBox(-1.5f, -2.5f, -5.0f, 4, 3, 1, scaleBinding);

        // Right Arm
        shoulder_right = new ModelRenderer(this);
        shoulder_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        shoulder_right.setTextureOffset(44, 0).addBox(1.0f, -2.0f, -2.5f, 5, 4, 5, scaleSuit);
        shoulder_right.rotateAngleZ = -0.24434609527920614f;

        fist_right = new ModelRenderer(this);
        fist_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        fist_right.setTextureOffset(48, 10).addBox(1.5f, 5.5f, -2.0f, 4, 6, 4, scaleSuit);

        binding_right = new ModelRenderer(this);
        binding_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        binding_right.setTextureOffset(39, 0).addBox(7.5f, 1.0f, -0.5f, 1, 7, 1, scaleBinding);

        // Left Arm
        shoulder_left = new ModelRenderer(this);
        shoulder_left.setRotationPoint(5.0f, 0.0f, 0.0f);
        shoulder_left.setTextureOffset(44, 0).addBox(-6.0f, -2.0f, -2.5f, 5, 4, 5, scaleSuit);
        shoulder_left.rotateAngleZ = 0.24434609527920614f;

        fist_left = new ModelRenderer(this);
        fist_left.setRotationPoint(5.0f, 0.0f, 0.0f);
        fist_left.setTextureOffset(48, 10).addBox(-5.5f, 5.5f, -2.0f, 4, 6, 4, scaleSuit);

        binding_left = new ModelRenderer(this);
        binding_left.setRotationPoint(0.0f, 0.0f, 0.0f);
        binding_left.setTextureOffset(39, 0).addBox(-3.5f, 1.0f, -0.5f, 1, 7, 1, scaleBinding);

        // Chestplate
        this.bipedBody = new ModelRenderer(this);
        this.bipedBody.setTextureOffset(0, 17).addBox(-3.0f, 0.0f, -2.5f, 6, 10, 5, 1.3f);

        // Legs
        if (armorSlot == EquipmentSlotType.FEET) {
            this.bipedLeftLeg = new ModelRenderer(this);
            this.bipedLeftLeg.setTextureOffset(48, 10).addBox(0.0f, 6.0f, -2.0f, 4, 6, 4, scaleSuit);

            this.bipedRightLeg = new ModelRenderer(this);
            this.bipedRightLeg.setTextureOffset(48, 10).addBox(-4.0f, 6.0f, -2.0f, 4, 6, 4, scaleSuit);
        }

        // Hat
        this.bipedHead = new ModelRenderer(this);
        this.bipedHeadwear = new ModelRenderer(this);
        this.bipedHeadwear.addChild(pig_head);
        this.bipedHeadwear.addChild(pig_nose);

        // Arms
        this.bipedRightArm = new ModelRenderer(this);
        this.bipedRightArm.addChild(shoulder_right);
        this.bipedRightArm.addChild(fist_right);
        this.bipedRightArm.addChild(binding_left);

        this.bipedLeftArm = new ModelRenderer(this);
        this.bipedLeftArm.addChild(shoulder_left);
        this.bipedLeftArm.addChild(fist_left);
        this.bipedLeftArm.addChild(binding_right);
    }
}
