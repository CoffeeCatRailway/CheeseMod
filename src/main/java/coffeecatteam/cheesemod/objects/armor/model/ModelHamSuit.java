package coffeecatteam.cheesemod.objects.armor.model;

import coffeecatteam.cheesemod.config.Config;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * ModelHamSuit - CoffeeCatTeam - Duncan Created using Tabula 7.0.0
 */
public class ModelHamSuit extends ModelBiped {

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

	private EntityEquipmentSlot armorSlot;

	public ModelHamSuit(float scale, EntityEquipmentSlot armorSlot) {
		super(scale, 0, 64, 32);
		this.armorSlot = armorSlot;

		textureWidth = 64;
		textureHeight = 32;
		
		binding = new ModelRenderer(this);
		hat = new ModelRenderer(this);
		arm_left = new ModelRenderer(this);
		arm_right = new ModelRenderer(this);
		
		Config.load("cheesemod");
		float s = Config.getHamSuitScale();
		float s_binding = Config.getHamSuitBindingScale();

		// Hat
		pig_head = new ModelRenderer(this, 0, 0);
		pig_head.addBox(-3.5F, -7.5F, -3.5F, 8, 8, 8, s);
		pig_head.setRotationPoint(-0.5F, -0.5F, -0.5F);
		setRotation(pig_head, 0.0F, 0.0F, 0.0F);

		pig_nose = new ModelRenderer(this, 26, 0);
		pig_nose.addBox(-1.5F, -2.5F, -5.0F, 4, 3, 1, s-s_binding);
		pig_nose.setRotationPoint(-0.5F, -0.5F, -0.5F);
		setRotation(pig_nose, 0.0F, 0.0F, 0.0F);

		// Right Arm
		shoulder_right = new ModelRenderer(this, 44, 0);
		shoulder_right.addBox(1.0F, -2.0F, -2.5F, 5, 4, 5, s);
		shoulder_right.setRotationPoint(-5.0F, 0.0F, 0.0F);
		setRotation(shoulder_right, 0.0F, 0.0F, -0.24434609527920614F);

		fist_right = new ModelRenderer(this, 48, 10);
		fist_right.addBox(1.5F, 5.5F, -2.0F, 4, 6, 4, s);
		fist_right.setRotationPoint(-5.0F, 0.0F, 0.0F);
		setRotation(fist_right, 0.0F, 0.0F, 0.0F);

		binding_right = new ModelRenderer(this, 39, 0);
		binding_right.addBox(7.5F, 1.0F, -0.5F, 1, 7, 1, s-s_binding);
		binding_right.setRotationPoint(-5.0F, 0.0F, 0.0F);
		setRotation(binding_right, 0.0F, 0.0F, 0.0F);

		// Left Arm
		shoulder_left = new ModelRenderer(this, 44, 0);
		shoulder_left.addBox(-6.0F, -2.0F, -2.5F, 5, 4, 5, s);
		shoulder_left.setRotationPoint(5.0F, 0.0F, 0.0F);
		setRotation(shoulder_left, 0.0F, 0.0F, 0.24434609527920614F);

		fist_left = new ModelRenderer(this, 48, 10);
		fist_left.addBox(-5.5F, 5.5F, -2.0F, 4, 6, 4, s);
		fist_left.setRotationPoint(5.0F, 0.0F, 0.0F);
		setRotation(fist_left, 0.0F, 0.0F, 0.0F);

		binding_left = new ModelRenderer(this, 39, 0);
		binding_left.addBox(-3.5F, 1.0F, -0.5F, 1, 7, 1, s-s_binding);
		binding_left.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(binding_left, 0.0F, 0.0F, 0.0F);

		// Chestplate
		pig_body = new ModelRenderer(this, 0, 17);
		pig_body.addBox(-3.0F, -5.0F, -2.5F, 6, 10, 5, 1.3f);
		pig_body.setRotationPoint(0.0F, 5.6F, 0.0F);
		setRotation(pig_body, 0.0F, 0.0F, 0.0F);
		
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

	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		if (entity instanceof EntityPlayer) {
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
			copyModelAngles(this.bipedHeadwear, this.hat);
			copyModelAngles(this.bipedBody, this.binding);

			copyModelAngles(this.bipedRightArm, this.arm_right);
			copyModelAngles(this.bipedLeftArm, this.arm_left);
		}

		GlStateManager.pushMatrix();
		if (this.isChild) {
			if (armorSlot == EntityEquipmentSlot.HEAD) {
				this.hat.render(scale);
			}
			if (armorSlot == EntityEquipmentSlot.CHEST) {
				this.binding.render(scale);
				this.arm_right.render(scale);
				this.arm_left.render(scale);
			}
		} else {
			if (entity.isSneaking())
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			if (armorSlot == EntityEquipmentSlot.HEAD) {
				this.hat.render(scale);
			}
			if (armorSlot == EntityEquipmentSlot.CHEST) {
				this.binding.render(scale);
				this.arm_right.render(scale);
				this.arm_left.render(scale);
			}
		}
		GlStateManager.popMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
