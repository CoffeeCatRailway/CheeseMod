package coffeecatrailway.coffeecheese;

import coffeecatrailway.coffeecheese.client.render.entity.BoatRendererCM;
import coffeecatrailway.coffeecheese.client.render.entity.FoodieRenderer;
import coffeecatrailway.coffeecheese.client.render.tileentity.MelterTileEntityRenderer;
import coffeecatrailway.coffeecheese.common.command.ChezCommand;
import coffeecatrailway.coffeecheese.common.entity.*;
import coffeecatrailway.coffeecheese.common.entity.item.BoatEntityCM;
import coffeecatrailway.coffeecheese.common.tileentity.MelterTileEntity;
import coffeecatrailway.coffeecheese.common.world.ModWorldFeatures;
import coffeecatrailway.coffeecheese.registry.*;
import com.mrcrayfish.filters.Filters;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Date;

@Mod(CheeseMod.MOD_ID)
public class CheeseMod {

    public static final String MOD_ID = "coffeecheese";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static ModCheeseConfig.ClientConfig CLIENT_CFG;
    public static ModCheeseConfig.ServerConfig SERVER_CFG;

    public static final ResourceLocation PINE_HUT_LOOT_TABLE = CheeseMod.getLocation("chests/pine_hut");

    public CheeseMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::setupCommon);

        final Pair<ModCheeseConfig.ClientConfig, ForgeConfigSpec> specPairC = new ForgeConfigSpec.Builder().configure(ModCheeseConfig.ClientConfig::new);
        final Pair<ModCheeseConfig.ServerConfig, ForgeConfigSpec> specPairS = new ForgeConfigSpec.Builder().configure(ModCheeseConfig.ServerConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPairC.getRight());
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, specPairS.getRight());
        CLIENT_CFG = specPairC.getLeft();
        SERVER_CFG = specPairS.getLeft();

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModFluids.register(modEventBus);
        ModTileEntities.TILE_ENTITIES.register(modEventBus);
        ModContainers.CONTAINERS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        modEventBus.addListener(ModStats::registerAll);
        ModRecipes.registerRecipeType();
        ModRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        modEventBus.addListener(ModEntities::registerSpawnPlacements);
        ModParticles.PARTICLE_TYPES.register(modEventBus);
    }

    public void setupClient(FMLClientSetupEvent event) {
        ModContainers.registerScreens();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> CheeseMod::registerParticleFactories);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> CheeseMod::registerEntityRenderers);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> CheeseMod::registerTileEntityRenderers);
        CheeseMod.LOGGER.debug("Common setup - Renderers");

        DistExecutor.runWhenOn(Dist.CLIENT, () -> CheeseMod::registerFilters);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerFilters() {
        if (ModList.get().isLoaded("filters")) {
            Filters.get().register(ModItemGroups.GROUP_ALL, CheeseMod.getLocation("filters/all/natural"), new ItemStack(ModBlocks.CHEESE_SAPLING.get()));
            Filters.get().register(ModItemGroups.GROUP_ALL, CheeseMod.getLocation("filters/all/woods"), new ItemStack(ModBlocks.CHEESE_LOG.get()));
            Filters.get().register(ModItemGroups.GROUP_ALL, CheeseMod.getLocation("filters/all/minerals"), new ItemStack(ModBlocks.CHEESE_METAL_BLOCK.get()));

            Filters.get().register(ModItemGroups.GROUP_ARMOR_TOOLS, CheeseMod.getLocation("filters/armor_tools/weapons"), new ItemStack(ModItems.CHEESE_METAL_SWORD.get()));
            Filters.get().register(ModItemGroups.GROUP_ARMOR_TOOLS, CheeseMod.getLocation("filters/armor_tools/tools"), new ItemStack(ModItems.CHEESE_METAL_AXE.get()));
            Filters.get().register(ModItemGroups.GROUP_ARMOR_TOOLS, CheeseMod.getLocation("filters/armor_tools/armor"), new ItemStack(ModItems.CHEESE_METAL_CHESTPLATE.get()));

            Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/cheese"), new ItemStack(ModItems.CHEESE_SLICE.get()));
            Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/raw"), new ItemStack(ModItems.HAM_RAW.get()));
            Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/cooked"), new ItemStack(ModItems.HAM_COOKED.get()));
            Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/toasties"), new ItemStack(ModItems.TOASTIE_CHEESE.get()));
            Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/pizzas"), new ItemStack(ModItems.PIZZA_CHEESE.get()));
            Filters.get().register(ModItemGroups.GROUP_FOODS, CheeseMod.getLocation("filters/foods/crackers"), new ItemStack(ModItems.CRACKER.get()));
            CheeseMod.LOGGER.debug("Common setup - MrCrayfish Filters support");
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerParticleFactories() {
        Minecraft.getInstance().particles.registerFactory(ModParticles.ITEM_CHEESE_BALL.get(), (type, world, v, v1, v2, v3, v4, v5) -> new BreakingParticle(world, v, v1, v2, new ItemStack(ModItems.CHEESE_BALL.get())));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerEntityRenderers() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(CheeseBallEntity.class, manager -> new SpriteRenderer<>(manager, itemRenderer));

        RenderingRegistry.registerEntityRenderingHandler(CheeseFoodie.class, manager -> new FoodieRenderer<>(manager, CheeseMod.getLocation("textures/entity/foodie/cheese.png")));
        RenderingRegistry.registerEntityRenderingHandler(GrilledCheeseFoodie.class, manager -> new FoodieRenderer<>(manager, CheeseMod.getLocation("textures/entity/foodie/grilled_cheese.png")));
        RenderingRegistry.registerEntityRenderingHandler(HamRawFoodie.class, manager -> new FoodieRenderer<>(manager, CheeseMod.getLocation("textures/entity/foodie/ham_raw.png")));
        RenderingRegistry.registerEntityRenderingHandler(HamCookedFoodie.class, manager -> new FoodieRenderer<>(manager, CheeseMod.getLocation("textures/entity/foodie/ham_cooked.png")));

        RenderingRegistry.registerEntityRenderingHandler(BoatEntityCM.class, BoatRendererCM::new);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(MelterTileEntity.class, new MelterTileEntityRenderer());
    }

    public void setupCommon(FMLCommonSetupEvent event) {
        ModBiomes.addBiomeTypes();
        ModWorldFeatures.addFeatures();
        ModVanillaCompat.setup();
        CheeseMod.LOGGER.debug("Common setup");
    }

    @SubscribeEvent
    public void serverStarting(FMLServerStartingEvent event) {
        ChezCommand.register(event.getCommandDispatcher());
    }

    public static boolean isDate(int month, int day) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        int curentMonth = cal.get(Calendar.MONTH);
        int curentDay = cal.get(Calendar.DAY_OF_MONTH);
        boolean inRange = curentDay == day;

        return (curentMonth == month && inRange);
    }

    public static ResourceLocation getLocation(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static float map(float from, float fromMin, float fromMax, float toMin, float toMax) {
        float fromAbs = from - fromMin;
        float fromMaxAbs = fromMax - fromMin;

        float normal = fromAbs / fromMaxAbs;

        float toMaxAbs = toMax - toMin;
        float toAbs = toMaxAbs * normal;

        return toAbs + toMin;
    }
}
