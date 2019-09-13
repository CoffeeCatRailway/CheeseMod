package coffeecatrailway.cheesemod;

import coffeecatrailway.cheesemod.client.render.tileentity.MelterTileEntityRenderer;
import coffeecatrailway.cheesemod.common.command.ChezCommand;
import coffeecatrailway.cheesemod.core.*;
import coffeecatrailway.cheesemod.common.entity.CheeseBallEntity;
import coffeecatrailway.cheesemod.common.tileentity.MelterTileEntity;
import coffeecatrailway.cheesemod.common.world.ModWorldFeatures;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModFluids::registerAll);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModTileEntityTypes::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModContainerTypes::registerContainers);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModFeatures::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModBiomes::registerAll);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModStats::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModRecipeTypes::registerAll);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModEntityTypes::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModParticles::registerAll);

        MinecraftForge.EVENT_BUS.addListener(SideProxy::serverStarting);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
        ModWorldFeatures.addFeatures();
        ModVanillaCompat.setup();

        CheeseMod.LOGGER.debug("Common setup");
    }

    private static void serverStarting(FMLServerStartingEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();
        ChezCommand.register(dispatcher);

        CheeseMod.LOGGER.info("Commands registered");
    }

    public static class Client extends SideProxy {

        Client() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Client::clientSetup);
        }

        private static void clientSetup(FMLClientSetupEvent event) {
            ModContainerTypes.registerScreens();
            Minecraft.getInstance().particles.registerFactory(ModParticles.ITEM_CHEESE_BALL, (type, world, v, v1, v2, v3, v4, v5) -> new BreakingParticle(world, v, v1, v2, new ItemStack(ModItems.CHEESE_BALL)));

            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            RenderingRegistry.registerEntityRenderingHandler(CheeseBallEntity.class, (manager) -> new SpriteRenderer<CheeseBallEntity>(manager, itemRenderer));
            ClientRegistry.bindTileEntitySpecialRenderer(MelterTileEntity.class, new MelterTileEntityRenderer());
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
