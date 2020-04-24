package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.client.gui.container.FoodDrawContainer;
import coffeecatrailway.coffeecheese.client.gui.container.GrillContainer;
import coffeecatrailway.coffeecheese.client.gui.container.MelterContainer;
import coffeecatrailway.coffeecheese.client.gui.container.PizzaOvenContainer;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.inventory.container.ContainerType;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 4/08/2019
 */
public class ModContainers {

    public static final RegistryEntry<ContainerType<FoodDrawContainer>> FOOD_DRAW = REGISTRATE.containerType("food_draw", FoodDrawContainer::create).register();
    public static final RegistryEntry<ContainerType<GrillContainer>> GRILL = REGISTRATE.containerType("grill", GrillContainer::new).register();
    public static final RegistryEntry<ContainerType<MelterContainer>> MELTER = REGISTRATE.containerType("melter", MelterContainer::new).register();
    public static final RegistryEntry<ContainerType<PizzaOvenContainer>> PIZZA_OVEN = REGISTRATE.containerType("pizza_oven", PizzaOvenContainer::new).register();

    public static void load() {
    }
}
