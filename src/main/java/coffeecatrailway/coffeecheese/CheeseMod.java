package coffeecatrailway.coffeecheese;

import coffeecatrailway.coffeecheese.client.render.entity.BoatRendererCM;
import coffeecatrailway.coffeecheese.client.render.entity.FoodieRenderer;
import coffeecatrailway.coffeecheese.client.render.tileentity.FoodWorldPortalTileEntityRenderer;
import coffeecatrailway.coffeecheese.client.render.tileentity.GrillTileEntityRenderer;
import coffeecatrailway.coffeecheese.client.render.tileentity.MelterTileEntityRenderer;
import coffeecatrailway.coffeecheese.client.render.tileentity.PizzaOvenTileEntityRenderer;
import coffeecatrailway.coffeecheese.common.command.ChezCommand;
import coffeecatrailway.coffeecheese.common.entity.*;
import coffeecatrailway.coffeecheese.common.entity.item.BoatEntityCM;
import coffeecatrailway.coffeecheese.common.tileentity.FoodWorldPortalTileEntity;
import coffeecatrailway.coffeecheese.common.tileentity.GrillTileEntity;
import coffeecatrailway.coffeecheese.common.tileentity.MelterTileEntity;
import coffeecatrailway.coffeecheese.common.tileentity.PizzaOvenTileEntity;
import coffeecatrailway.coffeecheese.common.world.ModWorldFeatures;
import coffeecatrailway.coffeecheese.common.world.dimension.FoodWorldTeleporter;
import coffeecatrailway.coffeecheese.compat.jer.JEResourcesCompat;
import coffeecatrailway.coffeecheese.compat.top.TOPCompatibility;
import coffeecatrailway.coffeecheese.registry.*;
import com.mrcrayfish.filters.Filters;
import io.netty.buffer.Unpooled;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
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

    public CheeseMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::setupCommon);
        modEventBus.addListener(this::interModEvent);

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
        ModDimensions.DIMENSIONS.register(modEventBus);
        ModEnchantments.ENCHANTMENTS.register(modEventBus);
    }

    public void interModEvent(InterModProcessEvent event) {
        if (ModList.get().isLoaded(jeresources.JEResources.ID))
            JEResourcesCompat.register();
    }

    public void setupClient(FMLClientSetupEvent event) {
        ModContainers.registerScreens();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> CheeseMod::registerParticleFactories);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> CheeseMod::registerEntityRenderers);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> CheeseMod::registerTileEntityRenderers);
        CheeseMod.LOGGER.debug("Client setup - Renderers");

        DistExecutor.runWhenOn(Dist.CLIENT, () -> CheeseMod::registerFilters);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerFilters() {
        if (ModList.get().isLoaded(com.mrcrayfish.filters.Reference.MOD_ID)) {
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

        CheeseMod.LOGGER.debug("Particles");
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

        CheeseMod.LOGGER.debug("Entity renderers");
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(GrillTileEntity.class, new GrillTileEntityRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(MelterTileEntity.class, new MelterTileEntityRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(PizzaOvenTileEntity.class, new PizzaOvenTileEntityRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(FoodWorldPortalTileEntity.class, new FoodWorldPortalTileEntityRenderer());

        CheeseMod.LOGGER.debug("Tile entity renderers");
    }

    public void setupCommon(FMLCommonSetupEvent event) {
        ModBiomes.addBiomeTypes();
        ModBiomes.addBiomeFeatures();
        ModWorldFeatures.addFeatures();
        ModVanillaCompat.setup();

        if (ModList.get().isLoaded(mcjty.theoneprobe.TheOneProbe.MODID))
            InterModComms.sendTo(mcjty.theoneprobe.TheOneProbe.MODID, "getTheOneProbe", TOPCompatibility::new);

        CheeseMod.LOGGER.debug("Common setup");
    }

    @Mod.EventBusSubscriber(modid = CheeseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEventBus {

        @SubscribeEvent
        public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
            if (event.getCrafting().getItem() == ModFluids.OIL_BUCKET.get())
                event.getInventory().decrStackSize(getSlotFor(event, new ItemStack(Items.WATER_BUCKET)), 1);
        }

        /**
         * Copied from {@link net.minecraft.entity.player.PlayerInventory}
         */
        private static int getSlotFor(PlayerEvent.ItemCraftedEvent event, ItemStack stack) {
            for (int i = 0; i < event.getInventory().getSizeInventory(); ++i)
                if (!event.getInventory().getStackInSlot(i).isEmpty() && stackEqualExact(stack, event.getInventory().getStackInSlot(i)))
                    return i;
            return -1;
        }

        /**
         * Copied from {@link net.minecraft.entity.player.PlayerInventory}
         */
        private static boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
            return stack1.getItem() == stack2.getItem() && ItemStack.areItemStackTagsEqual(stack1, stack2);
        }

        @SubscribeEvent
        public static void onBlockBroken(BlockEvent.BreakEvent event) {
            if (!event.getWorld().isRemote()) {
                if ((event.getPlayer().getHeldItemMainhand().getItem() != Items.SHEARS) || (!event.getPlayer().isCreative())) {
                    if (event.getState().getBlock() == Blocks.GRASS || event.getState().getBlock() == Blocks.TALL_GRASS || event.getState().getBlock() == Blocks.FERN) {
                        if (Math.random() <= (double) ModCheeseConfig.pineappleDropChance.get() / 100) {
                            event.getWorld().setBlockState(event.getPos(), Blocks.AIR.getDefaultState(), 2);
                            event.getWorld().addEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX(),
                                    event.getPos().getY(), event.getPos().getZ(), new ItemStack(ModItems.PINEAPPLE_PLANT.get(), 1)));
                        }
                    }
                }
            }
        }

        @SubscribeEvent
        public static void serverStarting(FMLServerStartingEvent event) {
            ChezCommand.register(event.getCommandDispatcher());

            CheeseMod.LOGGER.debug("Command registered");
        }

        @SubscribeEvent
        public static void registerToManager(final RegisterDimensionsEvent event) {
            ResourceLocation location = CheeseMod.getLocation("foodworld");

            if (DimensionType.byName(location) == null) {
                ModDimensions.FOOD_WORLD_TYPE = DimensionManager.registerDimension(location, ModDimensions.FOOD_WORLD.get(), new PacketBuffer(Unpooled.buffer()), true);
                DimensionManager.keepLoaded(ModDimensions.FOOD_WORLD_TYPE, false);
            } else
                ModDimensions.FOOD_WORLD_TYPE = DimensionType.byName(location);
        }

        @SubscribeEvent
        public static void onWorldLoad(WorldEvent.Load event) {
            if (!(event.getWorld() instanceof ServerWorld)) return;

            ServerWorld world = (ServerWorld) event.getWorld();
            if (world.dimension.getType() == DimensionType.OVERWORLD || world.dimension.getType() == ModDimensions.FOOD_WORLD_TYPE)
                world.customTeleporters.add(ModDimensions.FOOD_WORLD_TELEPORTER = new FoodWorldTeleporter(world));
        }
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
        return new ResourceLocation(CheeseMod.MOD_ID, path);
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
