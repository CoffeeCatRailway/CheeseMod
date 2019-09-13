package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.common.entity.CheeseBallEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 9/09/2019
 */
public class ModEntityTypes {

    public static EntityType<CheeseBallEntity> CHEESE_BALL;

    public static void registerAll(RegistryEvent.Register<EntityType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.ENTITIES.getRegistryName())) return;

        CHEESE_BALL = register("cheese_ball", EntityType.Builder.<CheeseBallEntity>create(CheeseBallEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).setCustomClientFactory((packet, world) -> new CheeseBallEntity(CHEESE_BALL, world)));
    }

    public static <E extends Entity> EntityType<E> register(String name, EntityType.Builder<E> builder) {
        EntityType<E> type = builder.build(name);
        type.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.ENTITIES.register(type);
        return type;
    }
}
