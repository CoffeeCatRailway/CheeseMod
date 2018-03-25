package coffeecatteam.cheesemod.objects.armor.model;

import coffeecatteam.cheesemod.config.Config;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemDye;

/**
 * ModelCheeseSuit - CoffeeCatTeam - Duncan
 * Created using Tabula 7.0.0
 */
public class ModelCheeseSuit extends ModelBiped {

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
    
    public ModelRenderer binding;
    public ModelRenderer hat;
    public ModelRenderer arm_left;
    public ModelRenderer arm_right;

	private EntityEquipmentSlot armorSlot;

	public ModelCheeseSuit(float scale, EntityEquipmentSlot armorSlot) {
		super(scale, 0, 64, 32);
		this.armorSlot = armorSlot;

		textureWidth = 64;
		textureHeight = 32;
		
		binding = new ModelRenderer(this);
		hat = new ModelRenderer(this);
		arm_left = new ModelRenderer(this);
		arm_right = new ModelRenderer(this);
		
		Config.load("cheesemod");
		float s = Config.getCheeseSuitScale();
		float s_binding = Config.getCheeseSuitBindingScale();

		// Hat
		cheese_hat1 = new ModelRenderer(this, 0, 0);
		cheese_hat1.addBox(-4.0F, -8.5F, -4.0F, 9, 4, 9, s);
		cheese_hat1.setRotationPoint(-0.5F, -0.5F, -0.5F);
		cheese_hat1.mirror = true;
		setRotation(cheese_hat1, 0F, 0F, 0F);

        cheese_hat2 = new ModelRenderer(this, 0, 0);
        cheese_hat2.addBox(-4.0F, -9.5F, -4.0F, 9, 4, 9, s-s_binding);
        cheese_hat2.setRotationPoint(-0.5F, -0.5F, -0.5F);
        cheese_hat2.mirror = true;
		setRotation(cheese_hat2, 0F, 0F, 0F);

		// Right Arm
		shoulder_right = new ModelRenderer(this, 44, 0);
		shoulder_right.addBox(1.0F, -2.0F, -2.5F, 5, 4, 5, s);
		shoulder_right.setRotationPoint(-5.0F, 0.0F, 0.0F);
		shoulder_right.mirror = true;
		setRotation(shoulder_right, 0.0F, 0.0F, -0.24434609527920614F);
        
        fist_right = new ModelRenderer(this, 44, 0);
        fist_right.addBox(1.5F, 6.5F, -2.5F, 5, 4, 5, s);
        fist_right.setRotationPoint(-5.1F, 0.0F, 0.0F);
        fist_right.mirror = true;
		setRotation(fist_right, 0.0F, 0.0F, 0.0F);
        
        binding_right = new ModelRenderer(this, 46, 13);
        binding_right.addBox(7.5F, 2.0F, -0.5F, 1, 7, 1, s-s_binding);
        binding_right.setRotationPoint(-5.0F, -2.0F, 0.0F);
        binding_right.mirror = true;
		setRotation(binding_right, 0.0F, 0.0F, 0.0F);

		// Left Arm
		shoulder_left = new ModelRenderer(this, 44, 0);
		shoulder_left.addBox(-6.0F, -2.0F, -2.5F, 5, 4, 5, s);
		shoulder_left.setRotationPoint(5.0F, 0.0F, 0.0F);
		shoulder_left.mirror = true;
		setRotation(shoulder_left, 0.0F, 0.0F, 0.24434609527920614F);
        
        fist_left = new ModelRenderer(this, 44, 0);
        fist_left.addBox(-6.5F, 6.5F, -2.5F, 5, 4, 5, s);
        fist_left.setRotationPoint(5.1F, 0.0F, 0.0F);
        fist_left.mirror = true;
		setRotation(fist_left, 0.0F, 0.0F, 0.0F);
        
        binding_left = new ModelRenderer(this, 46, 13);
        binding_left.addBox(-6.0F, 2.0F, -0.5F, 1, 7, 1, s-s_binding);
        binding_left.setRotationPoint(2.5F, -2.0F, 0.0F);
        binding_left.mirror = true;
		setRotation(binding_left, 0.0F, 0.0F, 0.0F);
		
		// Chestplate
		binding_shoulder1 = new ModelRenderer(this, 40, 9);
		binding_shoulder1.addBox(-5.0F, -1.0F, -1.0F, 10, 1, 2, s-s_binding);
		binding_shoulder1.setRotationPoint(0.0F, 0.0F, 0.0F);
		binding_shoulder1.mirror = true;
		setRotation(binding_shoulder1, 0.0F, 0.0F, 0.0F);
        
        binding_shoulder2 = new ModelRenderer(this, 50, 13);
        binding_shoulder2.addBox(-0.5F, -1.1F, -2.1F, 1, 1, 4, s-s_binding);
        binding_shoulder2.setRotationPoint(0.0F, 0.0F, 0.0F);
        binding_shoulder2.mirror = true;
		setRotation(binding_shoulder2, 0.0F, 0.0F, 0.0F);
        
        binding_waist1 = new ModelRenderer(this, 42, 9);
        binding_waist1.addBox(-4.0F, 5.0F, -1.0F, 9, 1, 1, s-s_binding);
        binding_waist1.setRotationPoint(-0.5F, 0.0F, -1.5F);
        binding_waist1.mirror = true;
		setRotation(binding_waist1, 0.0F, 0.0F, 0.0F);
        
        binding_waist2 = new ModelRenderer(this, 42, 9);
        binding_waist2.addBox(-4.0F, 5.0F, -1.0F, 9, 1, 1, s-s_binding);
        binding_waist2.setRotationPoint(-0.5F, 0.0F, 2.5F);
        binding_waist2.mirror = true;
		setRotation(binding_waist2, 0.0F, 0.0F, 0.0F);
        
        binding_waist3 = new ModelRenderer(this, 51, 14);
        binding_waist3.addBox(-4.0F, 5.0F, -1.0F, 1, 1, 3, s-s_binding);
        binding_waist3.setRotationPoint(-0.5F, 0.0F, -0.5F);
        binding_waist3.mirror = true;
		setRotation(binding_waist3, 0.0F, 0.0F, 0.0F);
        
        binding_waist4 = new ModelRenderer(this, 51, 14);
        binding_waist4.addBox(3.9F, 5.0F, -1.0F, 1, 1, 3, s-s_binding);
        binding_waist4.setRotationPoint(-0.4F, 0.0F, -0.5F);
        binding_waist4.mirror = true;
		setRotation(binding_waist4, 0.0F, 0.0F, 0.0F);
        
        binding_chest1 = new ModelRenderer(this, 60, 13);
        binding_chest1.addBox(-4.0F, 0.0F, 0.0F, 1, 5, 1, s-s_binding);
        binding_chest1.setRotationPoint(-0.5F, 0.0F, -0.5F);
        binding_chest1.mirror = true;
		setRotation(binding_chest1, 0.0F, 0.0F, 0.0F);
        
        binding_chest2 = new ModelRenderer(this, 60, 13);
        binding_chest2.addBox(4.0F, 0.0F, 0.0F, 1, 5, 1, s-s_binding);
        binding_chest2.setRotationPoint(-0.5F, 0.0F, -0.5F);
        binding_chest2.mirror = true;
		setRotation(binding_chest2, 0.0F, 0.0F, 0.0F);
        
        binding_chest3 = new ModelRenderer(this, 60, 13);
        binding_chest3.addBox(0.0F, -1.0F, -2.0F, 1, 6, 1, s-s_binding);
        binding_chest3.setRotationPoint(-0.5F, 0.0F, -0.5F);
        binding_chest3.mirror = true;
		setRotation(binding_chest3, 0.0F, 0.0F, 0.0F);
        
        binding_chest4 = new ModelRenderer(this, 60, 13);
        binding_chest4.addBox(0.0F, -1.0F, 2.0F, 1, 6, 1, s-s_binding);
        binding_chest4.setRotationPoint(-0.5F, 0.0F, -0.5F);
        binding_chest4.mirror = true;
		setRotation(binding_chest4, 0.0F, 0.0F, 0.0F);

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

	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		if (entity instanceof EntityPlayer) {
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
			copyModelAngles(this.bipedHeadwear, this.hat);
			copyModelAngles(this.bipedBody, this.binding);

			copyModelAngles(this.bipedRightArm, this.arm_right);
			copyModelAngles(this.bipedLeftArm, this.arm_left);
		}
		if (entity instanceof EntityArmorStand) {
			EntityArmorStand armorstand = (EntityArmorStand)entity;
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
			
			copyModelAngles(this.bipedHead, this.bipedHeadwear);
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
