package coffeecatrailway.coffeecheese.core;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.util.IItemProvider;

/**
 * @author CoffeeCatRailway
 * Created: 29/08/2019
 */
public class ModVanillaCompat {

    public static void setup() {
        // Log Stripping
        registerStrippable(ModBlocks.CHEESE_LOG, ModBlocks.STRPPED_CHEESE_LOG);
        registerStrippable(ModBlocks.CHEESE_WOOD, ModBlocks.STRPPED_CHEESE_WOOD);

        registerStrippable(ModBlocks.GRILLED_CHEESE_LOG, ModBlocks.STRPPED_GRILLED_CHEESE_LOG);
        registerStrippable(ModBlocks.GRILLED_CHEESE_WOOD, ModBlocks.STRPPED_GRILLED_CHEESE_WOOD);

        registerStrippable(ModBlocks.HAM_RAW_LOG, ModBlocks.STRPPED_HAM_RAW_LOG);
        registerStrippable(ModBlocks.HAM_RAW_WOOD, ModBlocks.STRPPED_HAM_RAW_WOOD);

        registerStrippable(ModBlocks.HAM_COOKED_LOG, ModBlocks.STRPPED_HAM_COOKED_LOG);
        registerStrippable(ModBlocks.HAM_COOKED_WOOD, ModBlocks.STRPPED_HAM_COOKED_WOOD);

        // Flammability
        registerFlammable(ModBlocks.CHEESE_LEAVES, 30, 60);
        registerFlammable(ModBlocks.CHEESE_LOG, 5, 5);
        registerFlammable(ModBlocks.CHEESE_WOOD, 5, 5);
        registerFlammable(ModBlocks.STRPPED_CHEESE_LOG, 5, 5);
        registerFlammable(ModBlocks.STRPPED_CHEESE_WOOD, 5, 5);
        registerFlammable(ModBlocks.CHEESE_PLANKS, 5, 20);
        registerFlammable(ModBlocks.CHEESE_SLAB, 5, 20);
        registerFlammable(ModBlocks.CHEESE_STAIRS, 5, 20);
        registerFlammable(ModBlocks.CHEESE_FENCE, 5, 20);
        registerFlammable(ModBlocks.CHEESE_FENCE_GATE, 5, 20);

        registerFlammable(ModBlocks.GRILLED_CHEESE_LEAVES, 30, 60);
        registerFlammable(ModBlocks.GRILLED_CHEESE_LOG, 5, 5);
        registerFlammable(ModBlocks.GRILLED_CHEESE_WOOD, 5, 5);
        registerFlammable(ModBlocks.STRPPED_GRILLED_CHEESE_LOG, 5, 5);
        registerFlammable(ModBlocks.STRPPED_GRILLED_CHEESE_WOOD, 5, 5);
        registerFlammable(ModBlocks.GRILLED_CHEESE_PLANKS, 5, 20);
        registerFlammable(ModBlocks.GRILLED_CHEESE_SLAB, 5, 20);
        registerFlammable(ModBlocks.GRILLED_CHEESE_STAIRS, 5, 20);
        registerFlammable(ModBlocks.GRILLED_CHEESE_FENCE, 5, 20);
        registerFlammable(ModBlocks.GRILLED_CHEESE_FENCE_GATE, 5, 20);

        registerFlammable(ModBlocks.HAM_RAW_LEAVES, 30, 60);
        registerFlammable(ModBlocks.HAM_RAW_LOG, 5, 5);
        registerFlammable(ModBlocks.HAM_RAW_WOOD, 5, 5);
        registerFlammable(ModBlocks.STRPPED_HAM_RAW_LOG, 5, 5);
        registerFlammable(ModBlocks.STRPPED_HAM_RAW_WOOD, 5, 5);
        registerFlammable(ModBlocks.HAM_RAW_PLANKS, 5, 20);
        registerFlammable(ModBlocks.HAM_RAW_SLAB, 5, 20);
        registerFlammable(ModBlocks.HAM_RAW_STAIRS, 5, 20);
        registerFlammable(ModBlocks.HAM_RAW_FENCE, 5, 20);
        registerFlammable(ModBlocks.HAM_RAW_FENCE_GATE, 5, 20);

        registerFlammable(ModBlocks.HAM_COOKED_LEAVES, 30, 60);
        registerFlammable(ModBlocks.HAM_COOKED_LOG, 5, 5);
        registerFlammable(ModBlocks.HAM_COOKED_WOOD, 5, 5);
        registerFlammable(ModBlocks.STRPPED_HAM_COOKED_LOG, 5, 5);
        registerFlammable(ModBlocks.STRPPED_HAM_COOKED_WOOD, 5, 5);
        registerFlammable(ModBlocks.HAM_COOKED_PLANKS, 5, 20);
        registerFlammable(ModBlocks.HAM_COOKED_SLAB, 5, 20);
        registerFlammable(ModBlocks.HAM_COOKED_STAIRS, 5, 20);
        registerFlammable(ModBlocks.HAM_COOKED_FENCE, 5, 20);
        registerFlammable(ModBlocks.HAM_COOKED_FENCE_GATE, 5, 20);

        // Compostable Blocks
        registerCompostable(0.3F, ModBlocks.CHEESE_SAPLING);
        registerCompostable(0.3F, ModBlocks.CHEESE_LEAVES);
        registerCompostable(1.0F, ModBlocks.CHEESE_CAKE);

        registerCompostable(0.3F, ModBlocks.GRILLED_CHEESE_SAPLING);
        registerCompostable(0.3F, ModBlocks.GRILLED_CHEESE_LEAVES);

        registerCompostable(0.3F, ModBlocks.HAM_RAW_SAPLING);
        registerCompostable(0.3F, ModBlocks.HAM_RAW_LEAVES);

        registerCompostable(0.3F, ModBlocks.HAM_COOKED_SAPLING);
        registerCompostable(0.3F, ModBlocks.HAM_COOKED_LEAVES);
    }

    private static void registerStrippable(Block log, Block strippedLog) {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(log, strippedLog);
    }

    private static void registerCompostable(float chance, IItemProvider itemProvider) {
        ComposterBlock.CHANCES.put(itemProvider.asItem(), chance);
    }

    private static void registerFlammable(Block block, int encouragement, int flammability) {
        FireBlock fireblock = (FireBlock) Blocks.FIRE;
        fireblock.setFireInfo(block, encouragement, flammability);
    }
}
