package coffeecatteam.cheesemod.objects.entity.render;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.entity.EntityCheeseMan;
import coffeecatteam.cheesemod.objects.entity.model.ModelFoodMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCheeseMan extends RenderLiving<EntityCheeseMan> {

	private static String type;

	public RenderCheeseMan(RenderManager manager, String type) {
		super(manager, new ModelFoodMan(), 0.5f);
		this.type = type;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCheeseMan entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/food_man/"+type+"_man.png");
	}
}
