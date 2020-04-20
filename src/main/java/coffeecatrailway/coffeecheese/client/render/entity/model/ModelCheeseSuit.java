package coffeecatrailway.coffeecheese.client.render.entity.model;

import coffeecatrailway.coffeecheese.ModCheeseConfig;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * ModelCheeseSuit - CoffeeCatTeam - Duncan
 * Created using Tabula 7.0.0
 */
public class ModelCheeseSuit extends BipedModel<LivingEntity> {

    public ModelRenderer cheese_hat1;
    public ModelRenderer cheese_hat2;

    public ModelRenderer shoulder_right;
    public ModelRenderer fist_right;
    public ModelRenderer binding_right;

    public ModelRenderer shoulder_left;
    public ModelRenderer fist_left;
    public ModelRenderer binding_left;

    public ModelRenderer binding_shoulder1;
    public ModelRenderer binding_shoulder2;

    public ModelRenderer binding_waist1;
    public ModelRenderer binding_waist2;
    public ModelRenderer binding_waist3;
    public ModelRenderer binding_waist4;

    public ModelRenderer binding_chest1;
    public ModelRenderer binding_chest2;
    public ModelRenderer binding_chest3;
    public ModelRenderer binding_chest4;

    public ModelCheeseSuit(float scale) {
        super(scale, 0, 64, 32);

        float scaleSuit = (float) (scale * ModCheeseConfig.cheeseSuitScale.get());
        float scaleBinding = (float) (scale * (ModCheeseConfig.cheeseSuitScale.get() - ModCheeseConfig.cheeseSuitBindingScale.get()));

        // Hat
        cheese_hat1 = new ModelRenderer(this);
        cheese_hat1.setRotationPoint(-0.5f, -0.5f, -0.5f);
        cheese_hat1.setTextureOffset(0, 0).addBox(-4.0f, -8.5f, -4.0f, 9, 4, 9, scaleSuit);

        cheese_hat2 = new ModelRenderer(this);
        cheese_hat2.setRotationPoint(-0.5f, -0.5f, -0.5f);
        cheese_hat2.setTextureOffset(0, 0).addBox(-4.0f, -10.0f, -4.0f, 9, 4, 9, scaleBinding);

        // Right Arm
        shoulder_right = new ModelRenderer(this);
        shoulder_right.setRotationPoint(-5.0f, 0.0f, 0.0f);
        shoulder_right.setTextureOffset(44, 0).addBox(1.0f, -2.0f, -2.5f, 5, 4, 5, scaleSuit);
        shoulder_right.rotateAngleZ = -0.24434609527920614f;

        fist_right = new ModelRenderer(this);
        fist_right.setRotationPoint(-5.1f, 0.0f, 0.0f);
        fist_right.setTextureOffset(44, 0).addBox(1.5f, 6.5f, -2.5f, 5, 4, 5, scaleSuit);

        binding_right = new ModelRenderer(this);
        binding_right.setRotationPoint(-5.0f, -2.0f, 0.0f);
        binding_right.setTextureOffset(46, 13).addBox(7.5f, 2.0f, -0.5f, 1, 7, 1, scaleBinding);

        // Left Arm
        shoulder_left = new ModelRenderer(this, 44, 0);
        shoulder_left.setRotationPoint(5.0f, 0.0f, 0.0f);
        shoulder_left.setTextureOffset(44, 0).addBox(-6.0f, -2.0f, -2.5f, 5, 4, 5, scaleSuit);
        shoulder_left.rotateAngleZ = 0.24434609527920614f;

        fist_left = new ModelRenderer(this, 44, 0);
        fist_left.setRotationPoint(5.1f, 0.0f, 0.0f);
        fist_left.setTextureOffset(44, 0).addBox(-6.5f, 6.5f, -2.5f, 5, 4, 5, scaleSuit);

        binding_left = new ModelRenderer(this, 46, 13);
        binding_left.setRotationPoint(2.5f, -2.0f, 0.0f);
        binding_left.setTextureOffset(46, 13).addBox(-6.0f, 2.0f, -0.5f, 1, 7, 1, scaleBinding);

        // Chestplate
        binding_shoulder1 = new ModelRenderer(this);
        binding_shoulder1.setRotationPoint(0.0f, 0.0f, 0.0f);
        binding_shoulder1.setTextureOffset(40, 9).addBox(-5.0f, -1.0f, -1.0f, 10, 1, 2, scaleBinding);

        binding_shoulder2 = new ModelRenderer(this);
        binding_shoulder2.setRotationPoint(0.0f, 0.0f, 0.0f);
        binding_shoulder2.setTextureOffset(50, 13).addBox(-0.5f, -1.1f, -2.1f, 1, 1, 4, scaleBinding);

        binding_waist1 = new ModelRenderer(this);
        binding_waist1.setRotationPoint(-0.5f, 0.0f, -1.5f);
        binding_waist1.setTextureOffset(42, 9).addBox(-4.0f, 5.0f, -1.0f, 9, 1, 1, scaleBinding);

        binding_waist2 = new ModelRenderer(this);
        binding_waist2.setRotationPoint(-0.5f, 0.0f, 2.5f);
        binding_waist2.setTextureOffset(42, 9).addBox(-4.0f, 5.0f, -1.0f, 9, 1, 1, scaleBinding);

        binding_waist3 = new ModelRenderer(this);
        binding_waist3.setRotationPoint(-0.5f, 0.0f, -0.5f);
        binding_waist3.setTextureOffset(51, 14).addBox(-4.0f, 5.0f, -1.0f, 1, 1, 3, scaleBinding);

        binding_waist4 = new ModelRenderer(this);
        binding_waist4.setRotationPoint(-0.4f, 0.0f, -0.5f);
        binding_waist4.setTextureOffset(51, 14).addBox(3.9f, 5.0f, -1.0f, 1, 1, 3, scaleBinding);

        binding_chest1 = new ModelRenderer(this);
        binding_chest1.setRotationPoint(-0.5f, 0.0f, -0.5f);
        binding_chest1.setTextureOffset(60, 13).addBox(-4.0f, 0.0f, 0.0f, 1, 5, 1, scaleBinding);

        binding_chest2 = new ModelRenderer(this);
        binding_chest2.setRotationPoint(-0.5f, 0.0f, -0.5f);
        binding_chest2.setTextureOffset(60, 13).addBox(4.0f, 0.0f, 0.0f, 1, 5, 1, scaleBinding);

        binding_chest3 = new ModelRenderer(this);
        binding_chest3.setRotationPoint(-0.5f, 0.0f, -0.5f);
        binding_chest3.setTextureOffset(60, 13).addBox(0.0f, -1.0f, -2.0f, 1, 6, 1, scaleBinding);

        binding_chest4 = new ModelRenderer(this);
        binding_chest4.setRotationPoint(-0.5f, 0.0f, -0.5f);
        binding_chest4.setTextureOffset(60, 13).addBox(0.0f, -1.0f, 2.0f, 1, 6, 1, scaleBinding);

        // Hat
        this.bipedHead = new ModelRenderer(this);
        this.bipedHeadwear = new ModelRenderer(this);
        this.bipedHeadwear.addChild(cheese_hat1);
        this.bipedHeadwear.addChild(cheese_hat2);

        // Chestplate
        this.bipedBody = new ModelRenderer(this);
        this.bipedBody.addChild(binding_shoulder1);
        this.bipedBody.addChild(binding_shoulder2);

        this.bipedBody.addChild(binding_waist1);
        this.bipedBody.addChild(binding_waist2);
        this.bipedBody.addChild(binding_waist3);
        this.bipedBody.addChild(binding_waist4);

        this.bipedBody.addChild(binding_chest1);
        this.bipedBody.addChild(binding_chest2);
        this.bipedBody.addChild(binding_chest3);
        this.bipedBody.addChild(binding_chest4);

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
