package coffeecatrailway.coffeecheese;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Date;

@Mod(CheeseMod.MOD_ID)
@Mod.EventBusSubscriber(modid = CheeseMod.MOD_ID)
public class CheeseMod {

    public static final String MOD_ID = "coffeecheese";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
//    public static ModCheeseConfig.ClientConfig CLIENT_CFG;
//    public static ModCheeseConfig.ServerConfig SERVER_CFG;
//
//    public static ModRegistrate REGISTRATE;

    public CheeseMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::setupCommon);
        MinecraftForge.EVENT_BUS.register(this);

//        final Pair<ModCheeseConfig.ClientConfig, ForgeConfigSpec> specPairC = new ForgeConfigSpec.Builder().configure(ModCheeseConfig.ClientConfig::new);
//        final Pair<ModCheeseConfig.ServerConfig, ForgeConfigSpec> specPairS = new ForgeConfigSpec.Builder().configure(ModCheeseConfig.ServerConfig::new);
//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPairC.getRight());
//        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, specPairS.getRight());
//        CLIENT_CFG = specPairC.getLeft();
//        SERVER_CFG = specPairS.getLeft();
//
//        REGISTRATE = ModRegistrate.create(MOD_ID, modEventBus);
//
//        ModBlocks.load();
//        ModItems.load();
//        ModFluids.load();
//        ModTileEntities.load();
//        ModEntities.load();
//        ModContainers.load();
//        ModFeatures.load();
//        ModBiomes.load();
//        modEventBus.addListener(ModStats::registerAll);
//        ModRecipes.registerRecipeType();
//        ModRecipes.RECIPE_SERIALIZERS.register(modEventBus);
//        modEventBus.addListener(ModEntities::registerSpawnPlacements);
//        ModParticles.PARTICLE_TYPES.register(modEventBus);
//        ModDimensions.DIMENSIONS.register(modEventBus);
//        ModEnchantments.ENCHANTMENTS.register(modEventBus);
    }

    @OnlyIn(Dist.CLIENT)
    public void setupClient(FMLClientSetupEvent event) {
//        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::itemColors);
//        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::renderLayers);
//        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::containerScreens);
//        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::particleFactories);
//        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::entityRenderers);
//        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientEvents::tileEntityRenderers);
//
//        if (ModList.get().isLoaded(Patchouli.MOD_ID))
//            ModPageTypes.registerPageTypes();
    }

    public void setupCommon(FMLCommonSetupEvent event) {
//        ModBiomes.addBiomeTypes();
//        ModBiomes.addBiomeFeatures();
//        ModWorldFeatures.addFeatures();
//        ModVanillaCompat.setup();
//
//        if (ModList.get().isLoaded("theoneprobe"))
//            InterModComms.sendTo("theoneprobe", "getTheOneProbe", TOPCompatibility::new);
//
//        CheeseMod.LOGGER.debug("Common setup");
    }

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
//        if (event.getCrafting().getItem() == ModFluids.OIL.get().getFilledBucket())
//            event.getInventory().decrStackSize(getSlotFor(event, new ItemStack(Items.WATER_BUCKET)), 1);
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
//        if (!event.getWorld().isRemote()) {
//            if ((event.getPlayer().getHeldItemMainhand().getItem() != Items.SHEARS) || (!event.getPlayer().isCreative())) {
//                if (event.getState().getBlock() == Blocks.GRASS || event.getState().getBlock() == Blocks.TALL_GRASS || event.getState().getBlock() == Blocks.FERN || event.getState().getBlock() instanceof TallFoodGrassBlock) {
//                    if (Math.random() <= (double) ModCheeseConfig.pineappleDropChance.get() / 100) {
//                        event.getWorld().setBlockState(event.getPos(), Blocks.AIR.getDefaultState(), 2);
//                        event.getWorld().addEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX(),
//                                event.getPos().getY(), event.getPos().getZ(), new ItemStack(ModItems.PINEAPPLE_PLANT.get(), 1)));
//                    }
//                }
//            }
//        }
    }

    @SubscribeEvent
    public static void serverStarting(FMLServerStartingEvent event) {
//        ChezCommand.register(event.getCommandDispatcher());
    }

//    @SubscribeEvent
//    public static void registerToManager(final RegisterDimensionsEvent event) {
//        ResourceLocation location = CheeseMod.getLocation("foodworld");
//        if (DimensionType.byName(location) == null) {
//            ModDimensions.FOOD_WORLD_TYPE = DimensionManager.registerDimension(location, ModDimensions.FOOD_WORLD.get(), new PacketBuffer(Unpooled.buffer()), true);
//            DimensionManager.keepLoaded(ModDimensions.FOOD_WORLD_TYPE, false);
//        } else
//            ModDimensions.FOOD_WORLD_TYPE = DimensionType.byName(location);
//    }

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
