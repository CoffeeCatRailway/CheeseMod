package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.client.gui.container.FoodDrawContainer;
import coffeecatrailway.cheesemod.client.gui.container.GrillContainer;
import coffeecatrailway.cheesemod.client.gui.container.MelterContainer;
import coffeecatrailway.cheesemod.client.gui.screen.FoodDrawScreen;
import coffeecatrailway.cheesemod.client.gui.screen.GrillScreen;
import coffeecatrailway.cheesemod.client.gui.screen.MelterScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 4/08/2019
 */
public class ModContainerTypes {

    public static ContainerType<FoodDrawContainer> FOOD_DRAW;
    public static ContainerType<GrillContainer> GRILL;
    public static ContainerType<MelterContainer> MELTER;

    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.CONTAINERS.getRegistryName())) return;

        FOOD_DRAW = register("food_draw", FoodDrawContainer::create);
        GRILL = register("grill", GrillContainer::new);
        MELTER = register("melter", MelterContainer::new);

        CheeseMod.LOGGER.info("Container types registered");
    }

    public static void registerScreens() {
        ScreenManager.registerFactory(FOOD_DRAW, FoodDrawScreen::new);
        ScreenManager.registerFactory(GRILL, GrillScreen::new);
        ScreenManager.registerFactory(MELTER, MelterScreen::new);

        CheeseMod.LOGGER.info("Gui screens registered");
    }

    private static <T extends Container> ContainerType<T> register(String name, ContainerType.IFactory<T> factory) {
        ContainerType<T> type = new ContainerType<>(factory);
        type.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.CONTAINERS.register(type);
        return type;
    }
}
