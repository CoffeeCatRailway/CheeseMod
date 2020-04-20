package coffeecatrailway.coffeecheese.client.render.entity.model;

import coffeecatrailway.coffeecheese.common.entity.FoodieEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 13/09/2019
 */
@OnlyIn(Dist.CLIENT)
public class FoodieModel<E extends FoodieEntity> extends BipedModel<E> {

    public FoodieModel() {
        super(0.0f);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-2.5f, -5.0f, -2.5f, 5, 5, 5, 0.0f);
        this.bipedHead.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.bipedHeadwear = new ModelRenderer(this);

        this.bipedBody = new ModelRenderer(this);
        this.bipedBody.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.bipedBody.setTextureOffset(20, 0).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4, 0.0f);
        this.bipedBody.setTextureOffset(44, 0).addBox(-3.0f, 1.0f, 2.0f, 6, 6, 1, 0.0f);
        this.bipedBody.setTextureOffset(44, 7).addBox(-3.0f, 1.0f, -3.0f, 6, 6, 1, 0.0f);

        this.bipedRightArm = new ModelRenderer(this, 0, 10);
        this.bipedRightArm.setRotationPoint(-4.0f, 13.5f, 0.0f);
        this.bipedRightArm.addBox(-2.0f, 0.0f, -1.0f, 2, 5, 2, 0.0f);

        this.bipedLeftArm = new ModelRenderer(this, 8, 10);
        this.bipedLeftArm.setRotationPoint(4.0f, 13.5f, 0.0f);
        this.bipedLeftArm.addBox(0.0f, 0.0f, -1.0f, 2, 5, 2, 0.0f);

        this.bipedRightLeg = new ModelRenderer(this, 16, 12);
        this.bipedRightLeg.setRotationPoint(-2.0f, 20.0f, 0.0f);
        this.bipedRightLeg.addBox(-1.5f, 0.0f, -1.5f, 3, 4, 3, 0.0f);

        this.bipedLeftLeg = new ModelRenderer(this, 28, 12);
        this.bipedLeftLeg.setRotationPoint(2.0f, 20.0f, 0.0f);
        this.bipedLeftLeg.addBox(-1.5f, 0.0f, -1.5f, 3, 4, 3, 0.0f);
    }

    @Override
    public void setRotationAngles(FoodieEntity entity, float limbSwing, float limbSwingAmount, float ageTicks, float headYaw, float headPitch) {
        this.bipedHead.rotateAngleX = headPitch * 0.017453292f;
        this.bipedHead.rotateAngleY = headYaw * 0.017453292f;
        this.bipedHead.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.25f;

        this.bipedBody.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.1F;

        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
        this.bipedRightArm.rotateAngleZ = 0.0f;

        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
        this.bipedLeftArm.rotateAngleZ = 0.0f;

        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4F * limbSwingAmount;
        this.bipedRightLeg.rotateAngleY = 0.0f;
        this.bipedRightLeg.rotateAngleZ = 0.0f;

        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927f) * 1.4F * limbSwingAmount;
        this.bipedLeftLeg.rotateAngleY = 0.0f;
        this.bipedLeftLeg.rotateAngleZ = 0.0f;
    }
}
