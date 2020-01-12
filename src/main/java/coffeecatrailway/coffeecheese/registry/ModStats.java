package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
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
    public static ResourceLocation INTERACT_WITH_PIZZA_OVEN;

    public static ResourceLocation INTERACT_WITH_CHEESE_DRAW;
    public static ResourceLocation INTERACT_WITH_HAM_DRAW;

    public static void registerAll(RegistryEvent.Register<StatType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.STAT_TYPES.getRegistryName())) return;

        INTERACT_WITH_GRILL = register("interact_with_grill", IStatFormatter.DEFAULT);
        INTERACT_WITH_PIZZA_OVEN = register("interact_with_pizza_oven", IStatFormatter.DEFAULT);

        INTERACT_WITH_CHEESE_DRAW = register("interact_with_cheese_draw", IStatFormatter.DEFAULT);
        INTERACT_WITH_HAM_DRAW = register("interact_with_ham_draw", IStatFormatter.DEFAULT);

        CheeseMod.LOGGER.info("Stat types registered");
    }

    private static ResourceLocation register(String name, IStatFormatter formatter) {
        ResourceLocation id = CheeseMod.getLocation(name);
        Registry.register(Registry.CUSTOM_STAT, name, id);
        Stats.CUSTOM.get(id, formatter);
        return id;
    }
}
