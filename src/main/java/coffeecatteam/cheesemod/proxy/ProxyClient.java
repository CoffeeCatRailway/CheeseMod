package coffeecatteam.cheesemod.proxy;

import coffeecatteam.cheesemod.config.Config;
import coffeecatteam.cheesemod.util.handlers.RegistryHandler;
import coffeecatteam.cheesemod.util.handlers.RenderHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyClient extends ProxyCommon {

	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		Config.clientPreInit();
		RenderHandler.registerEntityRenderers();
	}

	public void init(FMLInitializationEvent event) {
		super.init(event);
		RegistryHandler.Client();
	}

	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
