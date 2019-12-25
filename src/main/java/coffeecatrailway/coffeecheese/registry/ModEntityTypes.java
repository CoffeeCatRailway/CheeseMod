package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.entity.*;
import coffeecatrailway.coffeecheese.common.entity.item.BoatEntityCM;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 9/09/2019
 */
public class ModEntityTypes {

    public static EntityType<CheeseBallEntity> CHEESE_BALL = build("cheese_ball", EntityType.Builder.<CheeseBallEntity>create(CheeseBallEntity::new, EntityClassification.MISC).size(0.25f, 0.25f)
            .setCustomClientFactory((packet, world) -> new CheeseBallEntity(world)));

    public static EntityType<CheeseFoodie> CHEESE_FOODIE = build("cheese_foodie", EntityType.Builder.<CheeseFoodie>create(CheeseFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
            .setCustomClientFactory((packet, world) -> new CheeseFoodie(world)));
    public static EntityType<GrilledCheeseFoodie> GRILLED_CHEESE_FOODIE = build("grilled_cheese_foodie", EntityType.Builder.<GrilledCheeseFoodie>create(GrilledCheeseFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
            .setCustomClientFactory((packet, world) -> new GrilledCheeseFoodie(world)));
    public static EntityType<HamRawFoodie> HAM_RAW_FOODIE = build("ham_raw_foodie", EntityType.Builder.<HamRawFoodie>create(HamRawFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
            .setCustomClientFactory((packet, world) -> new HamRawFoodie(world)));
    public static EntityType<HamCookedFoodie> HAM_COOKED_FOODIE = build("ham_cooked_foodie", EntityType.Builder.<HamCookedFoodie>create(HamCookedFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
            .setCustomClientFactory((packet, world) -> new HamCookedFoodie(world)));

    public static EntityType<? extends BoatEntity> BOAT = build("food_boat", EntityType.Builder.<BoatEntityCM>create(BoatEntityCM::new, EntityClassification.MISC).setTrackingRange(80)
            .setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).size(1.375f, 0.5625f).setCustomClientFactory(BoatEntityCM::new));

    public static void registerAll(RegistryEvent.Register<EntityType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.ENTITIES.getRegistryName())) return;

        register(CHEESE_BALL);

        register(CHEESE_FOODIE);
        EntitySpawnPlacementRegistry.register(CHEESE_FOODIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);

        register(GRILLED_CHEESE_FOODIE);
        EntitySpawnPlacementRegistry.register(GRILLED_CHEESE_FOODIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);

        register(HAM_RAW_FOODIE);
        EntitySpawnPlacementRegistry.register(HAM_RAW_FOODIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);

        register(HAM_COOKED_FOODIE);
        EntitySpawnPlacementRegistry.register(HAM_COOKED_FOODIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);

        register(BOAT);
        CheeseMod.LOGGER.info("Entity types registered");
    }

    public static <E extends Entity> EntityType<E> build(String name, EntityType.Builder<E> builder) {
        EntityType<E> type = builder.build(name);
        type.setRegistryName(CheeseMod.getLocation(name));
        return type;
    }

    public static void register(EntityType<?> type) {
        ForgeRegistries.ENTITIES.register(type);
    }
}
