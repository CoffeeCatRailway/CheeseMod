package coffeecatteam.cheesemod.objects.entity.render.golem;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.entity.EntityHamGolem;
import coffeecatteam.cheesemod.objects.entity.model.ModelHamGolem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHamGolem extends RenderLiving<EntityHamGolem> {

	public RenderHamGolem(RenderManager manager) {
		super(manager, new ModelHamGolem(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHamGolem entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/golem/ham_golem.png");
	}

	protected void applyRotations(EntityHamGolem entityLiving, float p_77043_2_, float rotationYaw,
			float partialTicks) {
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

		if ((double) entityLiving.limbSwingAmount >= 0.01D) {
			float f = 13.0F;
			float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
			float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
			GlStateManager.rotate(6.5F * f2, 0.0F, 0.0F, 1.0F);
		}
	}
}
