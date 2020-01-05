package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.entity.*;
import coffeecatrailway.coffeecheese.common.entity.item.BoatEntityCM;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 9/09/2019
 */
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, CheeseMod.MOD_ID);

    public static final RegistryObject<EntityType<CheeseBallEntity>> CHEESE_BALL = ENTITIES.register("cheese_ball", () -> EntityType.Builder.<CheeseBallEntity>create(CheeseBallEntity::new, EntityClassification.MISC).size(0.25f, 0.25f)
            .setCustomClientFactory((packet, world) -> new CheeseBallEntity(world)).build("cheese_ball"));

    public static final RegistryObject<EntityType<CheeseFoodie>> CHEESE_FOODIE = ENTITIES.register("cheese_foodie", () -> EntityType.Builder.<CheeseFoodie>create(CheeseFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
            .setCustomClientFactory((packet, world) -> new CheeseFoodie(world)).build("cheese_foodie"));

    public static final RegistryObject<EntityType<GrilledCheeseFoodie>> GRILLED_CHEESE_FOODIE = ENTITIES.register("grilled_cheese_foodie", () -> EntityType.Builder.<GrilledCheeseFoodie>create(GrilledCheeseFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
            .setCustomClientFactory((packet, world) -> new GrilledCheeseFoodie(world)).build("grilled_cheese_foodie"));

    public static final RegistryObject<EntityType<HamRawFoodie>> HAM_RAW_FOODIE = ENTITIES.register("ham_raw_foodie", () -> EntityType.Builder.<HamRawFoodie>create(HamRawFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
            .setCustomClientFactory((packet, world) -> new HamRawFoodie(world)).build("ham_raw_foodie"));

    public static final RegistryObject<EntityType<HamCookedFoodie>> HAM_COOKED_FOODIE = ENTITIES.register("ham_cooked_foodie", () -> EntityType.Builder.<HamCookedFoodie>create(HamCookedFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
            .setCustomClientFactory((packet, world) -> new HamCookedFoodie(world)).build("ham_cooked_foodie"));

    public static final RegistryObject<EntityType<? extends BoatEntity>> BOAT = ENTITIES.register("food_boat", () -> EntityType.Builder.<BoatEntityCM>create(BoatEntityCM::new, EntityClassification.MISC).setTrackingRange(80)
            .setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).size(1.375f, 0.5625f).setCustomClientFactory(BoatEntityCM::new).build("food_boat"));

    public static void registerSpawnPlacements(RegistryEvent.Register<EntityType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.ENTITIES.getRegistryName())) return;

        EntitySpawnPlacementRegistry.register(CHEESE_FOODIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);
        EntitySpawnPlacementRegistry.register(GRILLED_CHEESE_FOODIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);
        EntitySpawnPlacementRegistry.register(HAM_RAW_FOODIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);
        EntitySpawnPlacementRegistry.register(HAM_COOKED_FOODIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);
    }
}
