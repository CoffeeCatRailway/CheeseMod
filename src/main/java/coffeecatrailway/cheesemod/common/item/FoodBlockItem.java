package coffeecatrailway.cheesemod.common.item;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.core.ModFoods;
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
        super(block, new Item.Properties().group(CheeseMod.GROUP_ALL).food(ModFoods.buildCombo(grilled, food, food, food, food, food, food, food, food, food)));
    }
}
