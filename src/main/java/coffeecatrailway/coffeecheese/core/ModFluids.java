package coffeecatrailway.coffeecheese.core;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.fluid.MeltedCheeseFluid;
import coffeecatrailway.coffeecheese.common.fluid.OilFluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 30/08/2019
 */
public class ModFluids {

    public static final FlowingFluid OIL_SOURCE = new OilFluid.Source();
    public static final Fluid OIL_FLOWING = new OilFluid.Flowing();
    public static final Tag<Fluid> OIL_TAG = makeWrapperTag("oil");

    public static final FlowingFluid MELTED_CHEESE_SOURCE = new MeltedCheeseFluid.Source();
    public static final Fluid MELTED_CHEESE_FLOWING = new MeltedCheeseFluid.Flowing();
    public static final Tag<Fluid> MELTED_CHEESE_TAG = makeWrapperTag("cheese");

    public static void registerAll(RegistryEvent.Register<Fluid> event) {
        if (!event.getName().equals(ForgeRegistries.FLUIDS.getRegistryName())) return;

        register("oil", OIL_SOURCE);
        register("oil_flowing", OIL_FLOWING);

        register("melted_cheese", MELTED_CHEESE_SOURCE);
        register("melted_cheese_flowing", MELTED_CHEESE_FLOWING);

        CheeseMod.LOGGER.info("Fluids registered");
    }

    private static Tag<Fluid> makeWrapperTag(String id) {
        return new FluidTags.Wrapper(CheeseMod.getLocation(id));
    }

    private static <F extends Fluid> F register(String name, F fluid) {
        fluid.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.FLUIDS.register(fluid);
        return fluid;
    }
}
