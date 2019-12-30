package coffeecatrailway.coffeecheese.common.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;

/**
 * @author CoffeeCatRailway
 * Created: 30/12/2019
 */
public class MeSpawnEggItem extends SpawnEggItem {

    public MeSpawnEggItem(EntityType<?> type, int primaryColor, int secondaryColor) {
        super(type, primaryColor, secondaryColor, new Item.Properties().group(ItemGroup.MISC));
    }
}
