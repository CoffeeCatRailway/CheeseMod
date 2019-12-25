package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.common.entity.CheeseBallEntity;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Util;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 29/08/2019
 */
public class ModVanillaCompat {

    public static void setup() {
        // Dispenser
        DispenserBlock.registerDispenseBehavior(ModItems.CHEESE_BALL.get(), new ProjectileDispenseBehavior() {
            @Override
            protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
                return Util.make(new CheeseBallEntity(world, pos.getX(), pos.getY(), pos.getZ()), (entity) -> entity.entityDropItem(stack));
            }
        });

        // Log Stripping
        registerStrippable(ModBlocks.CHEESE_LOG.get(), ModBlocks.STRPPED_CHEESE_LOG.get());
        registerStrippable(ModBlocks.CHEESE_WOOD.get(), ModBlocks.STRPPED_CHEESE_WOOD.get());

        registerStrippable(ModBlocks.GRILLED_CHEESE_LOG.get(), ModBlocks.STRPPED_GRILLED_CHEESE_LOG.get());
        registerStrippable(ModBlocks.GRILLED_CHEESE_WOOD.get(), ModBlocks.STRPPED_GRILLED_CHEESE_WOOD.get());

        registerStrippable(ModBlocks.HAM_RAW_LOG.get(), ModBlocks.STRPPED_HAM_RAW_LOG.get());
        registerStrippable(ModBlocks.HAM_RAW_WOOD.get(), ModBlocks.STRPPED_HAM_RAW_WOOD.get());

        registerStrippable(ModBlocks.HAM_COOKED_LOG.get(), ModBlocks.STRPPED_HAM_COOKED_LOG.get());
        registerStrippable(ModBlocks.HAM_COOKED_WOOD.get(), ModBlocks.STRPPED_HAM_COOKED_WOOD.get());

        // Flammability
        registerFlammable(ModBlocks.CHEESE_LEAVES.get(), 30, 60);
        registerFlammable(ModBlocks.CHEESE_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.CHEESE_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.STRPPED_CHEESE_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.STRPPED_CHEESE_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.CHEESE_PLANKS.get(), 5, 20);
        registerFlammable(ModBlocks.CHEESE_SLAB.get(), 5, 20);
        registerFlammable(ModBlocks.CHEESE_STAIRS.get(), 5, 20);
        registerFlammable(ModBlocks.CHEESE_FENCE.get(), 5, 20);
        registerFlammable(ModBlocks.CHEESE_FENCE_GATE.get(), 5, 20);

        registerFlammable(ModBlocks.GRILLED_CHEESE_LEAVES.get(), 30, 60);
        registerFlammable(ModBlocks.GRILLED_CHEESE_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.GRILLED_CHEESE_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.STRPPED_GRILLED_CHEESE_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.STRPPED_GRILLED_CHEESE_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.GRILLED_CHEESE_PLANKS.get(), 5, 20);
        registerFlammable(ModBlocks.GRILLED_CHEESE_SLAB.get(), 5, 20);
        registerFlammable(ModBlocks.GRILLED_CHEESE_STAIRS.get(), 5, 20);
        registerFlammable(ModBlocks.GRILLED_CHEESE_FENCE.get(), 5, 20);
        registerFlammable(ModBlocks.GRILLED_CHEESE_FENCE_GATE.get(), 5, 20);

        registerFlammable(ModBlocks.HAM_RAW_LEAVES.get(), 30, 60);
        registerFlammable(ModBlocks.HAM_RAW_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.HAM_RAW_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.STRPPED_HAM_RAW_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.STRPPED_HAM_RAW_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.HAM_RAW_PLANKS.get(), 5, 20);
        registerFlammable(ModBlocks.HAM_RAW_SLAB.get(), 5, 20);
        registerFlammable(ModBlocks.HAM_RAW_STAIRS.get(), 5, 20);
        registerFlammable(ModBlocks.HAM_RAW_FENCE.get(), 5, 20);
        registerFlammable(ModBlocks.HAM_RAW_FENCE_GATE.get(), 5, 20);

        registerFlammable(ModBlocks.HAM_COOKED_LEAVES.get(), 30, 60);
        registerFlammable(ModBlocks.HAM_COOKED_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.HAM_COOKED_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.STRPPED_HAM_COOKED_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.STRPPED_HAM_COOKED_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.HAM_COOKED_PLANKS.get(), 5, 20);
        registerFlammable(ModBlocks.HAM_COOKED_SLAB.get(), 5, 20);
        registerFlammable(ModBlocks.HAM_COOKED_STAIRS.get(), 5, 20);
        registerFlammable(ModBlocks.HAM_COOKED_FENCE.get(), 5, 20);
        registerFlammable(ModBlocks.HAM_COOKED_FENCE_GATE.get(), 5, 20);

        // Compostable Blocks
        registerCompostable(0.3F, ModBlocks.CHEESE_SAPLING.get());
        registerCompostable(0.3F, ModBlocks.CHEESE_LEAVES.get());
        registerCompostable(1.0F, ModBlocks.CHEESE_CAKE.get());

        registerCompostable(0.3F, ModBlocks.GRILLED_CHEESE_SAPLING.get());
        registerCompostable(0.3F, ModBlocks.GRILLED_CHEESE_LEAVES.get());

        registerCompostable(0.3F, ModBlocks.HAM_RAW_SAPLING.get());
        registerCompostable(0.3F, ModBlocks.HAM_RAW_LEAVES.get());

        registerCompostable(0.3F, ModBlocks.HAM_COOKED_SAPLING.get());
        registerCompostable(0.3F, ModBlocks.HAM_COOKED_LEAVES.get());
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
