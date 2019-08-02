package coffeecatrailway.cheesemod;

import coffeecatrailway.cheesemod.commands.ChezCommand;
import coffeecatrailway.cheesemod.commands.ConfigCommand;
import coffeecatrailway.cheesemod.core.*;
import coffeecatrailway.cheesemod.world.ModWorldFeatures;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
public class SideProxy {

    public SideProxy() {
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.CONFIG, ModConfig.FILE);
        ModConfig.loadConfig(ModConfig.CONFIG, FMLPaths.CONFIGDIR.get().resolve(ModConfig.FILE).toString());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(SideProxy::commonSetup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModBlocks::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModItems::registerAll);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModFeatures::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModBiomes::registerAll);

        MinecraftForge.EVENT_BUS.addListener(SideProxy::serverStarting);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
        ModWorldFeatures.addFeatures();

        CheeseMod.LOGGER.debug("Common setup");
    }

    private static void serverStarting(FMLServerStartingEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();
        if (CheeseMod.isDevBuild()) {
            ConfigCommand.register(dispatcher);
        }
        ChezCommand.register(dispatcher);

        CheeseMod.LOGGER.info("Commands registered");
    }

    public static class Client extends SideProxy {

        Client() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Client::clientSetup);
        }

        private static void clientSetup(FMLClientSetupEvent event) {

        }
    }

    public static class Server extends SideProxy {

        Server() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Server::serverSetup);
        }

        private static void serverSetup(FMLDedicatedServerSetupEvent event) {

        }
    }
}
