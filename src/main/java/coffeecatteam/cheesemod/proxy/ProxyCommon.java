package coffeecatteam.cheesemod.proxy;

import org.apache.logging.log4j.Logger;

import coffeecatteam.cheesemod.crafting.Smelting;
import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.crafting.CraftingToolsArmor;
import coffeecatteam.cheesemod.crafting.foodmakers.Recipes;
import coffeecatteam.cheesemod.util.Utils;
import coffeecatteam.cheesemod.util.handlers.RegistryHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyCommon {

	public static Logger logger = Utils.getLogger();

	public void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.Common();
	}

	public void init(FMLInitializationEvent event) {
		Smelting.register();
		CraftingToolsArmor.register();
		Recipes.initGrill();
		Recipes.initCrackerMaker();
		logger.info("Recipes Initialized");
	}

	public void postInit(FMLPostInitializationEvent event) {

	}
}
