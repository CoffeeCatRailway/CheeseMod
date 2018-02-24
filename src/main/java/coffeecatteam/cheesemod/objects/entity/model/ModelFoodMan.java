package coffeecatteam.cheesemod.objects.entity.model;

import coffeecatteam.cheesemod.util.Utils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelVillager - Either Mojang or a mod author Created using Tabula 6.0.0
 */
public class ModelFoodMan extends ModelBase {
	public ModelRenderer food_man_head;
	public ModelRenderer food_man_nose;

	public ModelRenderer food_man_arm_r;
	public ModelRenderer food_man_arm_l;
	public ModelRenderer food_man_arm;

	public ModelRenderer food_man_body_upper;
	public ModelRenderer food_man_coat;

	public ModelRenderer food_man_body_lower;
	public ModelRenderer food_man_utter;

	public ModelRenderer food_man_leg_bl;
	public ModelRenderer food_man_leg_fl;
	public ModelRenderer food_man_leg_br;
	public ModelRenderer food_man_leg_fr;

	public ModelRenderer christmas_hat;
	public ModelRenderer ch1;
	public ModelRenderer ch2;
	public ModelRenderer ch3;
	public ModelRenderer ch4;
	public ModelRenderer ch5;
	public ModelRenderer ch6;
	public ModelRenderer ch7;

	public ModelFoodMan() {
		this.textureWidth = 105;
		this.textureHeight = 64;

		// Christmas Hat
		float xOff = 0.0f;
		float yOff = 10.0f;
		float zOff = 4.5f;

		this.ch1 = new ModelRenderer(this, 65, 0);
		this.ch1.addBox(-5.0F + xOff, -2.4F + yOff, -5.0F + zOff - 0.5f, 10, 3, 10);
		this.ch1.setRotationPoint(0.0F, -18.5F, -4.0F);

		this.ch2 = new ModelRenderer(this, 66, 19);
		this.ch2.addBox(-4.5F + xOff, -5.4F + yOff, -4.5F + zOff - 0.5f, 9, 3, 9);
		this.ch2.setRotationPoint(0.0F, -18.5F, -4.0F);

		this.ch3 = new ModelRenderer(this, 66, 19);
		this.ch3.addBox(-4.0F + xOff, -8.0F + yOff, -4.4F + zOff + 0.4f, 8, 3, 8);
		this.ch3.setRotationPoint(0.0F, -18.5F, -4.0F);
		this.setRotateAngle(ch3, -0.06981317007977318F, 0.0F, 0.0F);

		float offsetZ = -1.0f;
		zOff -= offsetZ;
		this.ch4 = new ModelRenderer(this, 66, 19);
		this.ch4.addBox(-3.5F + xOff, -11.4F + yOff, -4.3F + zOff, 7, 4, 7);
		this.ch4.setRotationPoint(0.0F, -18.5F, -4.0F);
		this.setRotateAngle(ch4, -0.13962634015954636F, 0.0F, 0.0F);

		this.ch5 = new ModelRenderer(this, 66, 19);
		this.ch5.addBox(-2.5F + xOff, -14.6F + yOff, -3.5F + zOff, 5, 4, 5);
		this.ch5.setRotationPoint(0.0F, -18.5F, -4.0F);
		this.setRotateAngle(ch5, -0.17453292519943295F, 0.0F, 0.0F);

		this.ch6 = new ModelRenderer(this, 66, 19);
		this.ch6.addBox(-1.5F + xOff, -17.7F + yOff, -3.0F + zOff, 3, 6, 3);
		this.ch6.setRotationPoint(0.0F, -18.5F, -4.0F);
		this.setRotateAngle(ch6, -0.22689280275926282F, 0.0F, 0.0F);

		this.ch7 = new ModelRenderer(this, 65, 0);
		this.ch7.addBox(-2.0F + xOff, -19.9F + yOff, -3.5F + zOff, 4, 4, 4);
		this.ch7.setRotationPoint(0.0F, -18.5F, -4.0F);
		this.setRotateAngle(ch7, -0.22689280275926282F, 0.0F, 0.0F);

		this.christmas_hat = new ModelRenderer(this);
		this.christmas_hat.addChild(this.ch1);
		this.christmas_hat.addChild(this.ch2);
		this.christmas_hat.addChild(this.ch3);
		this.christmas_hat.addChild(this.ch4);
		this.christmas_hat.addChild(this.ch5);
		this.christmas_hat.addChild(this.ch6);
		this.christmas_hat.addChild(this.ch7);

		// Villager
		this.food_man_head = new ModelRenderer(this, 0, 0);
		this.food_man_head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8);
		this.food_man_head.setRotationPoint(0.0F, -10.0F, -4.0F);

		this.food_man_nose = new ModelRenderer(this, 24, 0);
		this.food_man_nose.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2);
		this.food_man_nose.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.food_man_head.addChild(this.food_man_nose);
		if (Utils.IS_CHRISTMAS)
			this.food_man_head.addChild(this.christmas_hat);

		this.food_man_arm_r = new ModelRenderer(this, 0, 45);
		this.food_man_arm_r.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4);
		this.food_man_arm_r.setRotationPoint(0.0F, -7.0F, -4.0F);
		this.setRotateAngle(food_man_arm_r, -0.7499679795819634F, 0.0F, 0.0F);

		this.food_man_arm_l = new ModelRenderer(this, 0, 45);
		this.food_man_arm_l.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4);
		this.food_man_arm_l.setRotationPoint(0.0F, -7.0F, -4.0F);
		this.setRotateAngle(food_man_arm_l, -0.7499679795819634F, 0.0F, 0.0F);

		this.food_man_arm = new ModelRenderer(this, 16, 20);
		this.food_man_arm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4);
		this.food_man_arm.setRotationPoint(0.0F, -7.0F, -4.0F);
		this.setRotateAngle(food_man_arm, -0.7499679795819634F, 0.0F, 0.0F);

		this.food_man_body_upper = new ModelRenderer(this, 36, 0);
		this.food_man_body_upper.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6);
		this.food_man_body_upper.setRotationPoint(0.0F, -10.0F, -4.0F);

		this.food_man_coat = new ModelRenderer(this, 0, 18);
		this.food_man_coat.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
		this.food_man_coat.setRotationPoint(0.0F, -10.0F, -4.0F);

		// Cow
		this.food_man_body_lower = new ModelRenderer(this, 20, 36);
		this.food_man_body_lower.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10);
		this.food_man_body_lower.setRotationPoint(0.0F, 5.0F, 2.0F);
		this.setRotateAngle(food_man_body_lower, 1.5707963267948966F, 0.0F, 0.0F);

		this.food_man_utter = new ModelRenderer(this, 36, 29);
		this.food_man_utter.addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1);
		this.food_man_utter.setRotationPoint(0.0F, 5.0F, 2.0F);
		this.setRotateAngle(food_man_utter, 1.5707963267948966F, 0.0F, 0.0F);

		this.food_man_leg_bl = new ModelRenderer(this, 48, 19);
		this.food_man_leg_bl.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		this.food_man_leg_bl.setRotationPoint(4.0F, 12.0F, 7.0F);

		this.food_man_leg_fl = new ModelRenderer(this, 48, 19);
		this.food_man_leg_fl.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		this.food_man_leg_fl.setRotationPoint(4.0F, 12.0F, -6.0F);

		this.food_man_leg_br = new ModelRenderer(this, 48, 19);
		this.food_man_leg_br.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		this.food_man_leg_br.setRotationPoint(-4.0F, 12.0F, 7.0F);

		this.food_man_leg_fr = new ModelRenderer(this, 48, 19);
		this.food_man_leg_fr.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		this.food_man_leg_fr.setRotationPoint(-4.0F, 12.0F, -6.0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		if (this.isChild) {
			float f = 2.2F;
			float childYOffset = 8.0F;
			float childZOffset = 2.0F;

			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, f * childYOffset * scale, childZOffset * scale);
			this.food_man_head.render(scale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
			this.food_man_arm_l.render(scale);
			this.food_man_arm_r.render(scale);
			this.food_man_arm.render(scale);
			this.food_man_coat.render(scale);
			this.food_man_body_upper.render(scale);

			this.food_man_body_lower.render(scale);
			this.food_man_utter.render(scale);
			this.food_man_leg_bl.render(scale);
			this.food_man_leg_fl.render(scale);
			this.food_man_leg_br.render(scale);
			this.food_man_leg_fr.render(scale);
			GlStateManager.popMatrix();
		} else {
			this.food_man_utter.render(scale);
			this.food_man_leg_bl.render(scale);
			this.food_man_leg_fl.render(scale);
			this.food_man_arm_l.render(scale);
			this.food_man_arm_r.render(scale);
			this.food_man_body_lower.render(scale);
			this.food_man_coat.render(scale);
			this.food_man_head.render(scale);
			this.food_man_body_upper.render(scale);
			this.food_man_leg_br.render(scale);
			this.food_man_arm.render(scale);
			this.food_man_leg_fr.render(scale);
		}
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		// Head/Nose
		this.food_man_head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		this.food_man_head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);

		// Arms
		float body_rotate = 290f;
		this.food_man_arm_l.rotateAngleY = netHeadYaw / (body_rotate / (float) Math.PI);
		this.food_man_arm_r.rotateAngleY = netHeadYaw / (body_rotate / (float) Math.PI);
		this.food_man_arm.rotateAngleY = netHeadYaw / (body_rotate / (float) Math.PI);

		// Body
		this.food_man_body_upper.rotateAngleY = netHeadYaw / (body_rotate / (float) Math.PI);
		this.food_man_body_lower.rotateAngleX = ((float) Math.PI / 2F);
		this.food_man_coat.rotateAngleY = netHeadYaw / (body_rotate / (float) Math.PI);

		// Right legs
		this.food_man_leg_fr.rotateAngleX = getRotationLegs(limbSwing, limbSwingAmount, (float) Math.PI);
		this.food_man_leg_br.rotateAngleX = getRotationLegs(limbSwing, limbSwingAmount, (float) Math.PI);

		// Left legs
		this.food_man_leg_fl.rotateAngleX = getRotationLegs(limbSwing, limbSwingAmount);
		this.food_man_leg_bl.rotateAngleX = getRotationLegs(limbSwing, limbSwingAmount);
	}

	public float getRotationLegs(float f, float f1, float PI) {
		return MathHelper.cos(f * 0.6662F + PI) * 1.4F * f1;
	}

	public float getRotationLegs(float f, float f1) {
		return MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	}
}
