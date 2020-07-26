package coffeecatrailway.coffeecheese.integration.registrate;

import coffeecatrailway.coffeecheese.CheeseItemGroup;
import coffeecatrailway.coffeecheese.common.item.StackableFoodItem;
import coffeecatrailway.coffeecheese.registry.CheeseItems;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.util.IItemProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 24/07/2020
 */
public class CheeseLang implements NonNullConsumer<RegistrateLangProvider> {

    public static final Map<CheeseItemGroup, String> GROUPS = new HashMap<>();

    @Override
    public void accept(RegistrateLangProvider provider) {
        GROUPS.forEach(provider::add);

        provider.add(getItemTranslationKey(CheeseItems.CHEESE_BALL) + ".info", "Shift + right-click to eat");
        provider.add(getItemTranslationKey(CheeseItems.OIL_CATCHER) + ".info", "Right-click on grill to gather excess oil");

        provider.add(getItemTranslationKey(CheeseItems.SANDWICH) + "." + StackableFoodItem.TAG_TOASTED.toLowerCase(), "Toasted");
        provider.add(getItemTranslationKey(CheeseItems.CRACKER) + "." + StackableFoodItem.TAG_TOASTED.toLowerCase(), "Grilled");
    }

    public static String getItemTranslationKey(Supplier<? extends IItemProvider> item) {
        return item.get().asItem().getTranslationKey();
    }
}
