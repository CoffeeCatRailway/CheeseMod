package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2020
 */
public class CheeseParticles {

    public static final Logger LOGGER = LogManager.getLogger(CheeseMod.MOD_ID + "-Particles");

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, CheeseMod.MOD_ID);

    public static final RegistryObject<BasicParticleType> ITEM_CHEESE_BALL = PARTICLE_TYPES.register("item_cheese_ball", () -> new BasicParticleType(false));

    public static void load(IEventBus bus) {
        PARTICLE_TYPES.register(bus);
        LOGGER.info("Particles registered");
    }
}
