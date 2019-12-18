package coffeecatrailway.coffeecheese.client.render.entity.model;

import coffeecatrailway.coffeecheese.common.entity.FoodieEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 13/09/2019
 */
@OnlyIn(Dist.CLIENT)
public class FoodieModel<E extends FoodieEntity> extends EntityModel<E> {

    public RendererModel head;

    public RendererModel chub_b;
    public RendererModel chub_m;
    public RendererModel chub_f;

    public RendererModel arm_r;
    public RendererModel arm_l;

    public RendererModel leg_r;
    public RendererModel leg_l;

    public FoodieModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.head.addBox(-2.5f, -5.0f, -2.5f, 5, 5, 5, 0.0f);

        this.chub_m = new RendererModel(this, 20, 0);
        this.chub_m.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.chub_m.addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4, 0.0f);

        this.chub_b = new RendererModel(this, 44, 0);
        this.chub_b.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.chub_b.addBox(-3.0f, 1.0f, 2.0f, 6, 6, 1, 0.0f);
        this.chub_m.addChild(this.chub_b);

        this.chub_f = new RendererModel(this, 44, 7);
        this.chub_f.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.chub_f.addBox(-3.0f, 1.0f, -3.0f, 6, 6, 1, 0.0f);
        this.chub_m.addChild(this.chub_f);

        this.arm_r = new RendererModel(this, 0, 10);
        this.arm_r.setRotationPoint(-4.0f, 13.5f, 0.0f);
        this.arm_r.addBox(-2.0f, 0.0f, -1.0f, 2, 5, 2, 0.0f);

        this.arm_l = new RendererModel(this, 8, 10);
        this.arm_l.setRotationPoint(4.0f, 13.5f, 0.0f);
        this.arm_l.addBox(0.0f, 0.0f, -1.0f, 2, 5, 2, 0.0f);

        this.leg_r = new RendererModel(this, 16, 12);
        this.leg_r.setRotationPoint(-2.0f, 20.0f, 0.0f);
        this.leg_r.addBox(-1.5f, 0.0f, -1.5f, 3, 4, 3, 0.0f);

        this.leg_l = new RendererModel(this, 28, 12);
        this.leg_l.setRotationPoint(2.0f, 20.0f, 0.0f);
        this.leg_l.addBox(-1.5f, 0.0f, -1.5f, 3, 4, 3, 0.0f);
    }

    @Override
    public void render(E entity, float limbSwing, float limbSwingAmount, float ageTicks, float headYaw, float headPitch, float scale) {
        super.render(entity, limbSwing, limbSwingAmount, ageTicks, headYaw, headPitch, scale);
        this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageTicks, headYaw, headPitch, scale);

        GlStateManager.pushMatrix();
        if (entity.isSitting() || entity.getRidingEntity() != null) {
            if (entity.getRidingEntity() != null) {
                GlStateManager.rotatef(entity.getRidingEntity().getRotationYawHead(), 0.0f, 1.0f, 0.0f);
                GlStateManager.translatef(0.0f, !entity.isChild() ? -0.125f : -0.275f, 0.0f);
            } else
                GlStateManager.translatef(0.0f, !entity.isChild() ? 0.225f : 0.125f, 0.0f);

            this.arm_r.rotateAngleX = -2.35619f;
            this.arm_l.rotateAngleX = -2.35619f;

            this.leg_r.setRotationPoint(-2.0f, 20.0f, -2.0f);
            this.leg_r.rotateAngleX = -1.5708f;
            this.leg_r.rotateAngleY = 0.336332f;

            this.leg_l.setRotationPoint(2.0f, 20.0f, -2.0f);
            this.leg_l.rotateAngleX = -1.5708f;
            this.leg_l.rotateAngleY = -0.336332f;
        } else {
            this.leg_r.setRotationPoint(-2.0f, 20.0f, 0.0f);
            this.leg_l.setRotationPoint(2.0f, 20.0f, 0.0f);
        }

        if (entity.isChild()) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0f, 6.0f * scale, 0.0f);
            this.head.renderWithRotation(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5f, 0.5f, 0.5f);
            GlStateManager.translatef(0.0f, 24.0f * scale, 0.0f);
            this.chub_m.render(scale);
            this.arm_r.render(scale);
            this.arm_l.render(scale);
            this.leg_r.render(scale);
            this.leg_l.render(scale);
            GlStateManager.popMatrix();
        } else {
            this.head.render(scale);
            this.chub_m.render(scale);
            this.arm_r.render(scale);
            this.arm_l.render(scale);
            this.leg_r.render(scale);
            this.leg_l.render(scale);
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(E entity, float limbSwing, float limbSwingAmount, float ageTicks, float headYaw, float headPitch, float scale) {
        super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageTicks, headYaw, headPitch, scale);
        this.head.rotateAngleX = headPitch * 0.017453292f;
        this.head.rotateAngleY = headYaw * 0.017453292f;
        this.head.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.25f;

        this.chub_m.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.1F;

        this.arm_r.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
        this.arm_r.rotateAngleZ = 0.0f;

        this.arm_l.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
        this.arm_l.rotateAngleZ = 0.0f;

        this.leg_r.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4F * limbSwingAmount;
        this.leg_r.rotateAngleY = 0.0f;
        this.leg_r.rotateAngleZ = 0.0f;

        this.leg_l.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927f) * 1.4F * limbSwingAmount;
        this.leg_l.rotateAngleY = 0.0f;
        this.leg_l.rotateAngleZ = 0.0f;
    }
}
