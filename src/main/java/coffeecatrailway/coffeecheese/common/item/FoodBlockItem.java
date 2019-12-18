package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.ModItemGroups;
import coffeecatrailway.coffeecheese.core.ModFoods;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2019
 */
public class FoodBlockItem extends BlockItem {

    public FoodBlockItem(Block block, Food food, boolean grilled) {
        super(block, new Item.Properties().group(ModItemGroups.GROUP_ALL).food(ModFoods.buildCombo(grilled, food, food, food, food, food, food, food, food, food)));
    }
}
