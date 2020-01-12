package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.client.gui.container.FoodDrawContainer;
import coffeecatrailway.coffeecheese.client.gui.container.GrillContainer;
import coffeecatrailway.coffeecheese.client.gui.container.MelterContainer;
import coffeecatrailway.coffeecheese.client.gui.container.PizzaOvenContainer;
import coffeecatrailway.coffeecheese.client.gui.screen.FoodDrawScreen;
import coffeecatrailway.coffeecheese.client.gui.screen.GrillScreen;
import coffeecatrailway.coffeecheese.client.gui.screen.MelterScreen;
import coffeecatrailway.coffeecheese.client.gui.screen.PizzaOvenScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 4/08/2019
 */
public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, CheeseMod.MOD_ID);

    public static final RegistryObject<ContainerType<FoodDrawContainer>> FOOD_DRAW = CONTAINERS.register("food_draw", () -> new ContainerType<>(FoodDrawContainer::create));
    public static final RegistryObject<ContainerType<GrillContainer>> GRILL = CONTAINERS.register("grill", () -> new ContainerType<>(GrillContainer::new));
    public static final RegistryObject<ContainerType<MelterContainer>> MELTER = CONTAINERS.register("melter", () -> new ContainerType<>(MelterContainer::new));
    public static final RegistryObject<ContainerType<PizzaOvenContainer>> PIZZA_OVEN = CONTAINERS.register("pizza_oven", () -> new ContainerType<>(PizzaOvenContainer::new));

    @OnlyIn(Dist.CLIENT)
    public static void registerScreens() {
        ScreenManager.registerFactory(FOOD_DRAW.get(), FoodDrawScreen::new);
        ScreenManager.registerFactory(GRILL.get(), GrillScreen::new);
        ScreenManager.registerFactory(MELTER.get(), MelterScreen::new);
        ScreenManager.registerFactory(PIZZA_OVEN.get(), PizzaOvenScreen::new);
    }
}
