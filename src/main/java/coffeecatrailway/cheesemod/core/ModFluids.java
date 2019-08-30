package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.fluid.OilFluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 30/08/2019
 */
public class ModFluids {

    public static FlowingFluid OIL_SOURCE = new OilFluid.Source();
    public static Fluid OIL_FLOWING = new OilFluid.Flowing();

    public static void registerAll(RegistryEvent.Register<Fluid> event) {
        if (!event.getName().equals(ForgeRegistries.FLUIDS.getRegistryName())) return;

        register("oil", OIL_SOURCE);
        register("oil_flowing", OIL_FLOWING);

        CheeseMod.LOGGER.info("Fluids registered");
    }

    private static <F extends Fluid> F register(String name, F fluid) {
        fluid.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.FLUIDS.register(fluid);
        return fluid;
    }
}
