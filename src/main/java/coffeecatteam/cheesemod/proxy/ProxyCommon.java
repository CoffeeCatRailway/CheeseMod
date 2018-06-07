package coffeecatteam.cheesemod.proxy;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.crafting.CraftingToolsArmor;
import coffeecatteam.cheesemod.crafting.Smelting;
import coffeecatteam.cheesemod.crafting.foodmakers.Recipes;
import coffeecatteam.cheesemod.init.RegistrationHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyCommon {

	public void preInit(FMLPreInitializationEvent event) {
        RegistrationHandler.init();
	}

	public void init(FMLInitializationEvent event) {
		Smelting.register();
		CraftingToolsArmor.register();
		Recipes.initGrill();
		Recipes.initCrackerMaker();
		CheeseMod.logger.info("Recipes Initialized");
	}

	public void postInit(FMLPostInitializationEvent event) {

	}
}
