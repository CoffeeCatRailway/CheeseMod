package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.world.dimension.FoodWorldDimension;
import coffeecatrailway.coffeecheese.common.world.dimension.FoodWorldTeleporter;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;

/**
 * @author CoffeeCatRailway
 * Created: 15/01/2020
 */
public class ModDimensions {

    public static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, CheeseMod.MOD_ID);

    public static final RegistryObject<ModDimension> FOOD_WORLD = DIMENSIONS.register("foodworld", () -> new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return FoodWorldDimension::new;
        }
    });
    public static DimensionType FOOD_WORLD_TYPE;
    public static FoodWorldTeleporter FOOD_WORLD_TELEPORTER;

    @Mod.EventBusSubscriber(modid = CheeseMod.MOD_ID)
    public static class ForgeEventBus {

        @SubscribeEvent
        public static void registerToManager(final RegisterDimensionsEvent event) {
            ResourceLocation location = CheeseMod.getLocation("foodworld");

            if (DimensionType.byName(location) == null) {
                FOOD_WORLD_TYPE = DimensionManager.registerDimension(location, FOOD_WORLD.get(), new PacketBuffer(Unpooled.buffer()), true);
                DimensionManager.keepLoaded(FOOD_WORLD_TYPE, false);
            } else
                FOOD_WORLD_TYPE = DimensionType.byName(location);
        }

        @SubscribeEvent
        public static void onWorldLoad(WorldEvent.Load event) {
            if (!(event.getWorld() instanceof ServerWorld)) return;

            ServerWorld world = (ServerWorld) event.getWorld();
            if (world.dimension.getType() == DimensionType.OVERWORLD || world.dimension.getType() == FOOD_WORLD_TYPE)
                world.customTeleporters.add(FOOD_WORLD_TELEPORTER = new FoodWorldTeleporter(world));
        }
    }
}
