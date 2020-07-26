package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.entity.CheeseBallEntity;
import coffeecatrailway.coffeecheese.common.entity.FoodBoatEntity;
import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2020
 * <p>
 * Spawn Egg code snipets: https://github.com/MysticMods/MysticalWorld/blob/1.14/src/main/java/epicsquid/mysticalworld/init/ModEntities.java
 * </p>
 */
public class CheeseEntities {

    public static final Logger LOGGER = LogManager.getLogger(CheeseMod.MOD_ID + "-Entities");

    private static <E extends Entity> NonNullFunction<Item.Properties, LazySpawnEggItem<E>> spawnEgg(RegistryEntry<EntityType<E>> entity, int primaryColor, int seconadryColor) {
        return prop -> new LazySpawnEggItem<>(entity, primaryColor, seconadryColor, prop);
    }

    public static List<RegistryEntry<? extends LazySpawnEggItem<?>>> SPAWN_EGGS = new ArrayList<>();

    public static final RegistryEntry<EntityType<CheeseBallEntity>> CHEESE_BALL = REGISTRATE.<CheeseBallEntity>entity("cheese_ball", CheeseBallEntity::new, EntityClassification.MISC)
            .properties(prop -> prop.size(.25f, .25f)).register();

    public static final RegistryEntry<EntityType<FoodBoatEntity>> BOAT = REGISTRATE.<FoodBoatEntity>entity("food_boat", FoodBoatEntity::new, EntityClassification.MISC)
            .properties(prop -> prop.size(1.375f, .562f).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true)).register();

    public static void load() {
        LOGGER.info("Entities registered");
    }
}
