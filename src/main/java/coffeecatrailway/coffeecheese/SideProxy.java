package coffeecatrailway.coffeecheese;

import coffeecatrailway.coffeecheese.client.render.entity.BoatRendererCM;
import coffeecatrailway.coffeecheese.client.render.entity.FoodieRenderer;
import coffeecatrailway.coffeecheese.client.render.tileentity.MelterTileEntityRenderer;
import coffeecatrailway.coffeecheese.common.command.ChezCommand;
import coffeecatrailway.coffeecheese.common.entity.*;
import coffeecatrailway.coffeecheese.common.entity.item.BoatEntityCM;
import coffeecatrailway.coffeecheese.common.tileentity.MelterTileEntity;
import coffeecatrailway.coffeecheese.common.world.ModWorldFeatures;
import coffeecatrailway.coffeecheese.core.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mrcrayfish.filters.Filters;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
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
            RenderingRegistry.registerEntityRenderingHandler(CheeseBallEntity.class, manager -> new SpriteRenderer<CheeseBallEntity>(manager, itemRenderer));

            RenderingRegistry.registerEntityRenderingHandler(CheeseFoodie.class, manager -> new FoodieRenderer<CheeseFoodie>(manager, CheeseMod.getLocation("textures/entity/foodie/cheese.png")));
            RenderingRegistry.registerEntityRenderingHandler(GrilledCheeseFoodie.class, manager -> new FoodieRenderer<GrilledCheeseFoodie>(manager, CheeseMod.getLocation("textures/entity/foodie/grilled_cheese.png")));
            RenderingRegistry.registerEntityRenderingHandler(HamRawFoodie.class, manager -> new FoodieRenderer<HamRawFoodie>(manager, CheeseMod.getLocation("textures/entity/foodie/ham_raw.png")));
            RenderingRegistry.registerEntityRenderingHandler(HamCookedFoodie.class, manager -> new FoodieRenderer<HamCookedFoodie>(manager, CheeseMod.getLocation("textures/entity/foodie/ham_cooked.png")));

            RenderingRegistry.registerEntityRenderingHandler(BoatEntityCM.class, BoatRendererCM::new);

            ClientRegistry.bindTileEntitySpecialRenderer(MelterTileEntity.class, new MelterTileEntityRenderer());

            if (ModList.get().isLoaded("filters")) {
                Filters.get().register(ModItemGroups.GROUP_ALL, CheeseMod.getLocation("filters/all/natural"), new ItemStack(ModBlocks.CHEESE_SAPLING));
                Filters.get().register(ModItemGroups.GROUP_ALL, CheeseMod.getLocation("filters/all/woods"), new ItemStack(ModBlocks.CHEESE_LOG));
                Filters.get().register(ModItemGroups.GROUP_ALL, CheeseMod.getLocation("filters/all/minerals"), new ItemStack(ModBlocks.CHEESE_METAL_BLOCK));

                Filters.get().register(ModItemGroups.GROUP_ARMOR_TOOLS, CheeseMod.getLocation("filters/armor_tools/weapons"), new ItemStack(ModItems.CHEESE_METAL_SWORD));
                Filters.get().register(ModItemGroups.GROUP_ARMOR_TOOLS, CheeseMod.getLocation("filters/armor_tools/tools"), new ItemStack(ModItems.CHEESE_METAL_AXE));
                Filters.get().register(ModItemGroups.GROUP_ARMOR_TOOLS, CheeseMod.getLocation("filters/armor_tools/armor"), new ItemStack(ModItems.CHEESE_METAL_CHESTPLATE));

                Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/cheese"), new ItemStack(ModItems.CHEESE_SLICE));
                Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/raw"), new ItemStack(ModItems.HAM_RAW));
                Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/cooked"), new ItemStack(ModItems.HAM_COOKED));
                Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/toasties"), new ItemStack(ModItems.TOASTIE_CHEESE));
                Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/pizzas"), new ItemStack(ModItems.PIZZA_CHEESE));
                Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/crackers"), new ItemStack(ModItems.CRACKER));
            }
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
