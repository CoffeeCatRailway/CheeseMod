package coffeecatteam.cheesemod.objects.entity.render.man;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.entity.man.EntityCheeseMan;
import coffeecatteam.cheesemod.objects.entity.model.ModelFoodMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderCheeseMan extends RenderLiving<EntityCheeseMan> {

	public RenderCheeseMan(RenderManager manager) {
		super(manager, new ModelFoodMan(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCheeseMan entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/food_man/cheese_man.png");
	}
}
