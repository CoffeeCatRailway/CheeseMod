package coffeecatteam.cheesemod.proxy;

import coffeecatteam.cheesemod.config.Config;
import coffeecatteam.cheesemod.objects.entity.EntityCrayfish;
import coffeecatteam.cheesemod.objects.entity.golem.EntityCheeseGolem;
import coffeecatteam.cheesemod.objects.entity.golem.EntityHamGolem;
import coffeecatteam.cheesemod.objects.entity.man.EntityCheeseMan;
import coffeecatteam.cheesemod.objects.entity.man.EntityHamMan;
import coffeecatteam.cheesemod.objects.entity.render.RenderCrayfish;
import coffeecatteam.cheesemod.objects.entity.render.golem.RenderCheeseGolem;
import coffeecatteam.cheesemod.objects.entity.render.golem.RenderHamGolem;
import coffeecatteam.cheesemod.objects.entity.render.man.RenderCheeseMan;
import coffeecatteam.cheesemod.objects.entity.render.man.RenderHamMan;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyClient extends ProxyCommon {

	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		Config.clientPreInit();

        RenderingRegistry.registerEntityRenderingHandler(EntityCheeseMan.class, RenderCheeseMan::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHamMan.class,  RenderHamMan::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityCheeseGolem.class, RenderCheeseGolem::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHamGolem.class, RenderHamGolem::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityCrayfish.class, RenderCrayfish::new);
	}

	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
