package coffeecatteam.cheesemod.util.handlers;

import coffeecatteam.cheesemod.objects.entity.EntityCheeseGolem;
import coffeecatteam.cheesemod.objects.entity.EntityCheeseMan;
import coffeecatteam.cheesemod.objects.entity.EntityHamGolem;
import coffeecatteam.cheesemod.objects.entity.EntityHamMan;
import coffeecatteam.cheesemod.objects.entity.render.RenderCheeseMan;
import coffeecatteam.cheesemod.objects.entity.render.RenderHamMan;
import coffeecatteam.cheesemod.objects.entity.render.golem.RenderCheeseGolem;
import coffeecatteam.cheesemod.objects.entity.render.golem.RenderHamGolem;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

	public static void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseMan.class,
				renderManager -> new RenderCheeseMan(renderManager, "cheese"));
		RenderingRegistry.registerEntityRenderingHandler(EntityHamMan.class,
				renderManager -> new RenderHamMan(renderManager, "ham"));

		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseGolem.class,
				renderManager -> new RenderCheeseGolem(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EntityHamGolem.class,
				renderManager -> new RenderHamGolem(renderManager));
	}
}
