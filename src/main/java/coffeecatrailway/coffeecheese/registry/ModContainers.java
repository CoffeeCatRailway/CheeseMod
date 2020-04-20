package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.client.gui.container.FoodDrawContainer;
import coffeecatrailway.coffeecheese.client.gui.container.GrillContainer;
import coffeecatrailway.coffeecheese.client.gui.container.MelterContainer;
import coffeecatrailway.coffeecheese.client.gui.container.PizzaOvenContainer;
import net.minecraft.inventory.container.ContainerType;
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
}
