package coffeecatteam.cheesemod.objects.entity.model;

import coffeecatteam.cheesemod.objects.entity.EntityHamGolem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHamGolem extends ModelBase {
	/** The head model for the iron golem. */
	public ModelRenderer foodGolemHead;
	/** The body model for the iron golem. */
	public ModelRenderer foodGolemBody;
	/** The right arm model for the iron golem. */
	public ModelRenderer foodGolemRightArm;
	/** The left arm model for the iron golem. */
	public ModelRenderer foodGolemLeftArm;
	/** The left leg model for the Iron Golem. */
	public ModelRenderer foodGolemLeftLeg;
	/** The right leg model for the Iron Golem. */
	public ModelRenderer foodGolemRightLeg;

	public ModelHamGolem() {
		this(0.0F);
	}

	public ModelHamGolem(float scale) {
		this(scale, -7.0F);
	}

	public ModelHamGolem(float scale, float rotationPointY) {
		int i = 128;
		int j = 128;
		this.foodGolemHead = (new ModelRenderer(this)).setTextureSize(128, 128);
		this.foodGolemHead.setRotationPoint(0.0F, 0.0F + rotationPointY, -2.0F);
		this.foodGolemHead.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, scale);
		this.foodGolemHead.setTextureOffset(24, 0).addBox(-1.0F, -5.0F, -7.5F, 2, 4, 2, scale);
		
		this.foodGolemBody = (new ModelRenderer(this)).setTextureSize(128, 128);
		this.foodGolemBody.setRotationPoint(0.0F, 0.0F + rotationPointY, 0.0F);
		this.foodGolemBody.setTextureOffset(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18, 12, 11, scale);
		this.foodGolemBody.setTextureOffset(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9, 5, 6, scale + 0.5F);
		
		this.foodGolemRightArm = (new ModelRenderer(this)).setTextureSize(128, 128);
		this.foodGolemRightArm.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.foodGolemRightArm.setTextureOffset(60, 21).addBox(-13.0F, -2.5F, -3.0F, 4, 30, 6, scale);
		
		this.foodGolemLeftArm = (new ModelRenderer(this)).setTextureSize(128, 128);
		this.foodGolemLeftArm.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.foodGolemLeftArm.setTextureOffset(60, 58).addBox(9.0F, -2.5F, -3.0F, 4, 30, 6, scale);
		
		this.foodGolemLeftLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(128, 128);
		this.foodGolemLeftLeg.setRotationPoint(-4.0F, 18.0F + rotationPointY, 0.0F);
		this.foodGolemLeftLeg.setTextureOffset(37, 0).addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, scale);
		
		this.foodGolemRightLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(128, 128);
		this.foodGolemRightLeg.mirror = true;
		this.foodGolemRightLeg.setTextureOffset(60, 0).setRotationPoint(5.0F, 18.0F + rotationPointY, 0.0F);
		this.foodGolemRightLeg.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, scale);
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		this.foodGolemHead.render(scale);
		this.foodGolemBody.render(scale);
		this.foodGolemLeftLeg.render(scale);
		this.foodGolemRightLeg.render(scale);
		this.foodGolemRightArm.render(scale);
		this.foodGolemLeftArm.render(scale);
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are
	 * used for animating the movement of arms and legs, where par1 represents
	 * the time(so that arms and legs swing back and forth) and par2 represents
	 * how "far" arms and legs can swing at most.
	 */
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		this.foodGolemHead.rotateAngleY = netHeadYaw * 0.017453292F;
		this.foodGolemHead.rotateAngleX = headPitch * 0.017453292F;
		this.foodGolemLeftLeg.rotateAngleX = -1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.foodGolemRightLeg.rotateAngleX = 1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.foodGolemLeftLeg.rotateAngleY = 0.0F;
		this.foodGolemRightLeg.rotateAngleY = 0.0F;
	}

	/**
	 * Used for easily adding entity-dependent animations. The second and third
	 * float params here are the same second and third as in the
	 * setRotationAngles method.
	 */
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTickTime) {
		EntityHamGolem golem = (EntityHamGolem) entitylivingbaseIn;
		int i = golem.getAttackTimer();

		if (i > 0) {
			this.foodGolemRightArm.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float) i - partialTickTime, 10.0F);
			this.foodGolemLeftArm.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float) i - partialTickTime, 10.0F);
		} else {
			int j = golem.getHoldRoseTick();

			if (j > 0) {
				this.foodGolemRightArm.rotateAngleX = -0.8F + 0.025F * this.triangleWave((float) j, 70.0F);
				this.foodGolemLeftArm.rotateAngleX = 0.0F;
			} else {
				this.foodGolemRightArm.rotateAngleX = (-0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F))
						* limbSwingAmount;
				this.foodGolemLeftArm.rotateAngleX = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F))
						* limbSwingAmount;
			}
		}
	}

	private float triangleWave(float p_78172_1_, float p_78172_2_) {
		return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
	}
}
