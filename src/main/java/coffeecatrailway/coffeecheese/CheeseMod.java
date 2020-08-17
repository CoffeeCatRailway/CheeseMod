package coffeecatrailway.coffeecheese;

import coffeecatrailway.coffeecheese.integration.VanillaCompatability;
import coffeecatrailway.coffeecheese.integration.registrate.CheeseLang;
import coffeecatrailway.coffeecheese.integration.registrate.CheeseTags;
import coffeecatrailway.coffeecheese.registry.CheeseBlocks;
import coffeecatrailway.coffeecheese.registry.CheeseEntities;
import coffeecatrailway.coffeecheese.registry.CheeseItems;
import coffeecatrailway.coffeecheese.registry.CheeseParticles;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.DistExecutor;
import org.apache.commons.lang3.tuple.Pair;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
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

    public static final ItemGroup GROUP_FOODS = new CheeseItemGroup("foods", "CC's Cheese Foods") {
        private final long cooldown = 1500;
        private long last, now;
        private Item item = Items.STICK;

        @Override
        public ItemStack createIcon() {
            now += System.currentTimeMillis() - last;
            last = System.currentTimeMillis();

            if (now > cooldown) {
                item = CheeseItems.CHEESE_SLICE.get(); //CheeseItems.FOODS.get(new Random().nextInt(CheeseItems.FOODS.size() - 1));
                now = 0;
            }
            return new ItemStack(item);
        }
    };
    public static final ItemGroup GROUP_ARMOR_TOOLS = new CheeseItemGroup("armor_tools", "CC's Cheese Armor & Tools") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(CheeseItems.CHEESE_METAL_SWORD.get());
        }
    };
    public static final ItemGroup GROUP_ALL = new CheeseItemGroup("CoffeeCat's Cheese Mod") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.CHAIN);
        }
    };

    public static CheeseConfig.Client CLIENT_CONFIG;
    public static CheeseConfig.Server SERVER_CONFIG;

    public static Registrate REGISTRATE;

    public CheeseMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::setupCommon);

        final Pair<CheeseConfig.Client, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(CheeseConfig.Client::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, client.getRight());
        CLIENT_CONFIG = client.getLeft();

        final Pair<CheeseConfig.Server, ForgeConfigSpec> server = new ForgeConfigSpec.Builder().configure(CheeseConfig.Server::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, server.getRight());
        SERVER_CONFIG = server.getLeft();
        LOGGER.info("Register configs");

        MinecraftForge.EVENT_BUS.register(this);

        REGISTRATE = Registrate.create(MOD_ID);
        REGISTRATE.itemGroup(() -> GROUP_ALL);
        REGISTRATE.addDataGenerator(ProviderType.LANG, new CheeseLang());
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, new CheeseTags.Blocks());
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, new CheeseTags.Items());
        LOGGER.info("Add registrate data gens");

        CheeseBlocks.load();
        CheeseItems.load();
//        CheeseFluids.load();
//        CheeseTileEntities.load();
        CheeseEntities.load();
//        CheeseContainers.load();
//        CheeseFeatures.load();
//        CheeseBiomes.load();
//        modEventBus.addListener(ModStats::registerAll);
//        CheeseRecipes.registerRecipeType();
//        CheeseRecipes.RECIPE_SERIALIZERS.register(modEventBus);
//        modEventBus.addListener(ModEntities::registerSpawnPlacements);
        CheeseParticles.load(modEventBus);
//        CheeseDimensions.DIMENSIONS.register(modEventBus);
//        CheeseEnchantments.ENCHANTMENTS.register(modEventBus);
    }

    @OnlyIn(Dist.CLIENT)
    public void setupClient(FMLClientSetupEvent event) {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::itemColors);
//        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::renderLayers);
//        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::containerScreens);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::particleFactories);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::entityRenderers);
//        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::tileEntityRenderers);
        LOGGER.info("Client events");

//        if (ModList.get().isLoaded(Patchouli.MOD_ID)) {
//            ModPageTypes.registerPageTypes();
        LOGGER.info("Register Patchouli pagetypes");
//      }
    }

    public void setupCommon(FMLCommonSetupEvent event) {
//        ModBiomes.addBiomeTypes();
//        ModBiomes.addBiomeFeatures();
//        ModWorldFeatures.addFeatures();
        VanillaCompatability.setup();
        LOGGER.info("Common events");

//        if (ModList.get().isLoaded("theoneprobe")) {
//            InterModComms.sendTo("theoneprobe", "getTheOneProbe", TOPCompatibility::new);
        LOGGER.info("Register TheOneProbe compatibility");
//      }
    }

    @SubscribeEvent
    public static void onEggBroken(ProjectileImpactEvent.Throwable event) {
        if (event.getThrowable() instanceof EggEntity && event.getRayTraceResult().getType() != RayTraceResult.Type.MISS) {
            EggEntity egg = (EggEntity) event.getThrowable();
            World world = egg.world;
            if (!world.isRemote)
                if (world.rand.nextInt(2) == 0)
                    world.addEntity(new ItemEntity(world, egg.getPosX(), egg.getPosY(), egg.getPosZ(), new ItemStack(CheeseItems.EGG_CRACKED.get())));
        }
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
        if (!event.getWorld().isRemote()) {
            if ((event.getPlayer().getHeldItemMainhand().getItem() != Items.SHEARS) || (!event.getPlayer().isCreative())) {
                if (event.getState().getBlock() == Blocks.GRASS || event.getState().getBlock() == Blocks.TALL_GRASS || event.getState().getBlock() == Blocks.FERN || event.getState().getBlock() instanceof TallGrassBlock) {
                    if (Math.random() <= (double) SERVER_CONFIG.pineappleDropChance.get() / 100) {
                        event.getWorld().setBlockState(event.getPos(), Blocks.AIR.getDefaultState(), 2);
                        event.getWorld().addEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX(),
                                event.getPos().getY(), event.getPos().getZ(), new ItemStack(CheeseItems.PINEAPPLE_PLANT.get(), 1)));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void serverStarting(FMLServerStartingEvent event) {
//        ChezCommand.register(event.getCommandDispatcher());
        LOGGER.info("Register /chez command");
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
