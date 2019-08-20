package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 8/08/2019
 */
public class ModStats {

    public static ResourceLocation INTERACT_WITH_GRILL;

    public static void registerAll(RegistryEvent.Register<StatType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.STAT_TYPES.getRegistryName())) return;

        INTERACT_WITH_GRILL = register("interact_with_grill", IStatFormatter.DEFAULT);

        CheeseMod.LOGGER.info("Stat types registered");
    }

    private static ResourceLocation register(String name, IStatFormatter formatter) {
        ResourceLocation id = CheeseMod.getLocation(name);
        Registry.register(Registry.CUSTOM_STAT, name, id);
        Stats.CUSTOM.get(id, formatter);
        return id;
    }
}
