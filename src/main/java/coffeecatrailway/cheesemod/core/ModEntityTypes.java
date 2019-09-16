package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.common.entity.*;
import coffeecatrailway.cheesemod.common.entity.item.BoatEntityCM;
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

    public static EntityType<CheeseBallEntity> CHEESE_BALL;

    public static EntityType<CheeseFoodie> CHEESE_FOODIE;
    public static EntityType<GrilledCheeseFoodie> GRILLED_CHEESE_FOODIE;
    public static EntityType<HamRawFoodie> HAM_RAW_FOODIE;
    public static EntityType<HamCookedFoodie> HAM_COOKED_FOODIE;

    public static EntityType<? extends BoatEntity> BOAT;

    public static void registerAll(RegistryEvent.Register<EntityType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.ENTITIES.getRegistryName())) return;

        CHEESE_BALL = register("cheese_ball", EntityType.Builder.<CheeseBallEntity>create(CheeseBallEntity::new, EntityClassification.MISC).size(0.25f, 0.25f)
                .setCustomClientFactory((packet, world) -> new CheeseBallEntity(CHEESE_BALL, world)));

        CHEESE_FOODIE = register("cheese_foodie", EntityType.Builder.<CheeseFoodie>create(CheeseFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
                .setCustomClientFactory((packet, world) -> new CheeseFoodie(CHEESE_FOODIE, world)));
        EntitySpawnPlacementRegistry.register(CHEESE_FOODIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);

        GRILLED_CHEESE_FOODIE = register("grilled_cheese_foodie", EntityType.Builder.<GrilledCheeseFoodie>create(GrilledCheeseFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
                .setCustomClientFactory((packet, world) -> new GrilledCheeseFoodie(GRILLED_CHEESE_FOODIE, world)));
        EntitySpawnPlacementRegistry.register(GRILLED_CHEESE_FOODIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);

        HAM_RAW_FOODIE = register("ham_raw_foodie", EntityType.Builder.<HamRawFoodie>create(HamRawFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
                .setCustomClientFactory((packet, world) -> new HamRawFoodie(CHEESE_FOODIE, world)));
        EntitySpawnPlacementRegistry.register(HAM_RAW_FOODIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);

        HAM_COOKED_FOODIE = register("ham_cooked_foodie", EntityType.Builder.<HamCookedFoodie>create(HamCookedFoodie::new, EntityClassification.CREATURE).size(0.5f, 1.15f)
                .setCustomClientFactory((packet, world) -> new HamCookedFoodie(GRILLED_CHEESE_FOODIE, world)));
        EntitySpawnPlacementRegistry.register(HAM_COOKED_FOODIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::func_223316_b);

        BOAT = register("food_boat", EntityType.Builder.<BoatEntityCM>create(BoatEntityCM::new, EntityClassification.MISC).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).size(1.375f, 0.5625f).setCustomClientFactory(BoatEntityCM::new));
    }

    public static <E extends Entity> EntityType<E> register(String name, EntityType.Builder<E> builder) {
        EntityType<E> type = builder.build(name);
        type.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.ENTITIES.register(type);
        return type;
    }
}
