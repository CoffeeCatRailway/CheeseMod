package coffeecatrailway.coffeecheese.core;

import coffeecatrailway.coffeecheese.CheeseMod;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 12/09/2019
 */
public class ModParticles {

    public static BasicParticleType ITEM_CHEESE_BALL;

    public static void registerAll(RegistryEvent.Register<ParticleType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.PARTICLE_TYPES.getRegistryName())) return;

        ITEM_CHEESE_BALL = register("item_cheese_ball", false);

        CheeseMod.LOGGER.info("<Particle types registered");
    }

    private static BasicParticleType register(String name, boolean alwaysShow) {
        BasicParticleType type = new BasicParticleType(alwaysShow);
        type.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.PARTICLE_TYPES.register(type);
        return type;
    }
}
