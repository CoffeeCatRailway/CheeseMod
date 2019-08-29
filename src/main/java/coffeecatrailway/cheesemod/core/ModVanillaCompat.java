package coffeecatrailway.cheesemod.core;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;

/**
 * @author CoffeeCatRailway
 * Created: 29/08/2019
 */
public class ModVanillaCompat {

    public static void setup() {
        registerStrippable(ModBlocks.CHEESE_LOG, ModBlocks.STRPPED_CHEESE_LOG);
        registerStrippable(ModBlocks.GRILLED_CHEESE_LOG, ModBlocks.STRPPED_GRILLED_CHEESE_LOG);
        registerStrippable(ModBlocks.HAM_RAW_LOG, ModBlocks.STRPPED_HAM_RAW_LOG);
        registerStrippable(ModBlocks.HAM_COOKED_LOG, ModBlocks.STRPPED_HAM_COOKED_LOG);

        registerStrippable(ModBlocks.CHEESE_WOOD, ModBlocks.STRPPED_CHEESE_WOOD);
        registerStrippable(ModBlocks.GRILLED_CHEESE_WOOD, ModBlocks.STRPPED_GRILLED_CHEESE_WOOD);
        registerStrippable(ModBlocks.HAM_RAW_WOOD, ModBlocks.STRPPED_HAM_RAW_WOOD);
        registerStrippable(ModBlocks.HAM_COOKED_WOOD, ModBlocks.STRPPED_HAM_COOKED_WOOD);
    }

    private static void registerStrippable(Block log, Block strippedLog) {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(log, strippedLog);
    }
}
