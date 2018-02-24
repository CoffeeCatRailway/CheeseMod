package coffeecatteam.cheesemod.objects.entity.render;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.entity.EntityHamMan;
import coffeecatteam.cheesemod.objects.entity.model.ModelFoodMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHamMan extends RenderLiving<EntityHamMan> {

	private static String type;

	public RenderHamMan(RenderManager manager, String type) {
		super(manager, new ModelFoodMan(), 0.5f);
		this.type = type;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHamMan entity) {
		return new ResourceLocation(Reference.MODID + ":textures/entity/food_man/"+type+"_man.png");
	}
}
