package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.block.FoodGrassBlock;
import coffeecatrailway.coffeecheese.common.entity.*;
import coffeecatrailway.coffeecheese.common.entity.item.BoatEntityCM;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.GrassPathBlock;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.state.IProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 9/09/2019
 */
public class ModEntities {

    public static final RegistryEntry<EntityType<CheeseBallEntity>> CHEESE_BALL = REGISTRATE.<CheeseBallEntity>entity("cheese_ball", CheeseBallEntity::new, EntityClassification.MISC)
            .properties(prop -> prop.size(0.25f, 0.25f)).register();

    public static final RegistryEntry<EntityType<CheeseFoodie>> CHEESE_FOODIE = REGISTRATE.entity("cheese_foodie", CheeseFoodie::new, EntityClassification.AMBIENT)
            .properties(prop -> prop.size(0.5f, 1.15f)).defaultSpawnEgg(11445876, 9404726).register();

    public static final RegistryEntry<EntityType<GrilledCheeseFoodie>> GRILLED_CHEESE_FOODIE = REGISTRATE.entity("grilled_cheese_foodie", GrilledCheeseFoodie::new, EntityClassification.AMBIENT)
            .properties(prop -> prop.size(0.5f, 1.15f)).defaultSpawnEgg(8484425, 8877339).register();

    public static final RegistryEntry<EntityType<HamRawFoodie>> HAM_RAW_FOODIE = REGISTRATE.entity("ham_raw_foodie", HamRawFoodie::new, EntityClassification.AMBIENT)
            .properties(prop -> prop.size(0.5f, 1.15f)).defaultSpawnEgg(12152929, 13920850).register();

    public static final RegistryEntry<EntityType<HamCookedFoodie>> HAM_COOKED_FOODIE = REGISTRATE.entity("ham_cooked_foodie", HamCookedFoodie::new, EntityClassification.AMBIENT)
            .properties(prop -> prop.size(0.5f, 1.15f)).defaultSpawnEgg(13218964, 16770746).register();

    public static final RegistryEntry<EntityType<BoatEntityCM>> BOAT = REGISTRATE.<BoatEntityCM>entity("food_boat", BoatEntityCM::new, EntityClassification.MISC)
            .properties(prop -> prop.size(1.375f, 0.5625f).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true)).register();

    public static void registerSpawnPlacements(RegistryEvent.Register<EntityType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.ENTITIES.getRegistryName())) return;

        EntitySpawnPlacementRegistry.register(CHEESE_FOODIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, ModEntities::canSpawn);
        EntitySpawnPlacementRegistry.register(GRILLED_CHEESE_FOODIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, ModEntities::canSpawn);
        EntitySpawnPlacementRegistry.register(HAM_RAW_FOODIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, ModEntities::canSpawn);
        EntitySpawnPlacementRegistry.register(HAM_COOKED_FOODIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, ModEntities::canSpawn);
    }

    public static boolean canSpawn(EntityType<? extends AnimalEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
        return canSpawn(world.getBlockState(pos.down())) && world.getLightSubtracted(pos, 0) > 8;
    }

    public static boolean canSpawn(BlockState state) {
        Block block = state.getBlock();
        return block instanceof GrassBlock || block instanceof GrassPathBlock || Tags.Blocks.DIRT.contains(block) || block instanceof FoodGrassBlock;
    }

    public static void load() {
    }
}
