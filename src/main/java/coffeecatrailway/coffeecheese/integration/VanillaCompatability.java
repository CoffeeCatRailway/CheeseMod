package coffeecatrailway.coffeecheese.integration;

import coffeecatrailway.coffeecheese.common.entity.CheeseBallEntity;
import coffeecatrailway.coffeecheese.registry.CheeseBlocks;
import coffeecatrailway.coffeecheese.registry.CheeseItems;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Util;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2020
 */
public class VanillaCompatability {

    public static void setup() {
        // Parrot - Cracker
        ParrotEntity.TAME_ITEMS.add(CheeseItems.CRACKER.get());

        // Dispenser
        DispenserBlock.registerDispenseBehavior(CheeseItems.CHEESE_BALL.get(), new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectileEntity(World world, IPosition pos, ItemStack stack) {
                return Util.make(new CheeseBallEntity(world, pos.getX(), pos.getY(), pos.getZ()), (entity) -> entity.setItem(stack));
            }
        });

        // Hoe - Farmland
        registerHoeable(CheeseBlocks.CHEESE_GRASS_BLOCK.get());
        registerHoeable(CheeseBlocks.GRILLED_CHEESE_GRASS_BLOCK.get());
        registerHoeable(CheeseBlocks.HAM_RAW_GRASS_BLOCK.get());
        registerHoeable(CheeseBlocks.HAM_COOKED_GRASS_BLOCK.get());

        // Log Stripping
        registerStrippable(CheeseBlocks.CHEESE_LOG.get(), CheeseBlocks.STRIPPED_CHEESE_LOG.get());
        registerStrippable(CheeseBlocks.CHEESE_WOOD.get(), CheeseBlocks.STRIPPED_CHEESE_WOOD.get());

        registerStrippable(CheeseBlocks.GRILLED_CHEESE_LOG.get(), CheeseBlocks.STRIPPED_GRILLED_CHEESE_LOG.get());
        registerStrippable(CheeseBlocks.GRILLED_CHEESE_WOOD.get(), CheeseBlocks.STRIPPED_GRILLED_CHEESE_WOOD.get());

        registerStrippable(CheeseBlocks.HAM_RAW_LOG.get(), CheeseBlocks.STRIPPED_HAM_RAW_LOG.get());
        registerStrippable(CheeseBlocks.HAM_RAW_WOOD.get(), CheeseBlocks.STRIPPED_HAM_RAW_WOOD.get());

        registerStrippable(CheeseBlocks.HAM_COOKED_LOG.get(), CheeseBlocks.STRIPPED_HAM_COOKED_LOG.get());
        registerStrippable(CheeseBlocks.HAM_COOKED_WOOD.get(), CheeseBlocks.STRIPPED_HAM_COOKED_WOOD.get());

        // Flammability
        registerFlammable(CheeseBlocks.CHEESE_LEAVES.get(), 30, 60);
        registerFlammable(CheeseBlocks.CHEESE_LOG.get(), 5, 5);
        registerFlammable(CheeseBlocks.CHEESE_WOOD.get(), 5, 5);
        registerFlammable(CheeseBlocks.STRIPPED_CHEESE_LOG.get(), 5, 5);
        registerFlammable(CheeseBlocks.STRIPPED_CHEESE_WOOD.get(), 5, 5);
        registerFlammable(CheeseBlocks.CHEESE_PLANKS.get(), 5, 20);

        registerFlammable(CheeseBlocks.GRILLED_CHEESE_LEAVES.get(), 30, 60);
        registerFlammable(CheeseBlocks.GRILLED_CHEESE_LOG.get(), 5, 5);
        registerFlammable(CheeseBlocks.GRILLED_CHEESE_WOOD.get(), 5, 5);
        registerFlammable(CheeseBlocks.STRIPPED_GRILLED_CHEESE_LOG.get(), 5, 5);
        registerFlammable(CheeseBlocks.STRIPPED_GRILLED_CHEESE_WOOD.get(), 5, 5);
        registerFlammable(CheeseBlocks.GRILLED_CHEESE_PLANKS.get(), 5, 20);

        registerFlammable(CheeseBlocks.HAM_RAW_LEAVES.get(), 30, 60);
        registerFlammable(CheeseBlocks.HAM_RAW_LOG.get(), 5, 5);
        registerFlammable(CheeseBlocks.HAM_RAW_WOOD.get(), 5, 5);
        registerFlammable(CheeseBlocks.STRIPPED_HAM_RAW_LOG.get(), 5, 5);
        registerFlammable(CheeseBlocks.STRIPPED_HAM_RAW_WOOD.get(), 5, 5);
        registerFlammable(CheeseBlocks.HAM_RAW_PLANKS.get(), 5, 20);

        registerFlammable(CheeseBlocks.HAM_COOKED_LEAVES.get(), 30, 60);
        registerFlammable(CheeseBlocks.HAM_COOKED_LOG.get(), 5, 5);
        registerFlammable(CheeseBlocks.HAM_COOKED_WOOD.get(), 5, 5);
        registerFlammable(CheeseBlocks.STRIPPED_HAM_COOKED_LOG.get(), 5, 5);
        registerFlammable(CheeseBlocks.STRIPPED_HAM_COOKED_WOOD.get(), 5, 5);
        registerFlammable(CheeseBlocks.HAM_COOKED_PLANKS.get(), 5, 20);

        // Compostable Blocks
//        registerCompostable(0.3F, CheeseBlocks.CHEESE_SAPLING.get());
        registerCompostable(0.3F, CheeseBlocks.CHEESE_LEAVES.get());
//        registerCompostable(1.0F, CheeseBlocks.CHEESE_CAKE.get());

//        registerCompostable(0.3F, CheeseBlocks.GRILLED_CHEESE_SAPLING.get());
        registerCompostable(0.3F, CheeseBlocks.GRILLED_CHEESE_LEAVES.get());
//        registerCompostable(1.0F, CheeseBlocks.GRILLED_CHEESE_CAKE.get());

//        registerCompostable(0.3F, CheeseBlocks.HAM_RAW_SAPLING.get());
        registerCompostable(0.3F, CheeseBlocks.HAM_RAW_LEAVES.get());
//        registerCompostable(1.0F, CheeseBlocks.HAM_RAW_CAKE.get());

//        registerCompostable(0.3F, CheeseBlocks.HAM_COOKED_SAPLING.get());
        registerCompostable(0.3F, CheeseBlocks.HAM_COOKED_LEAVES.get());
//        registerCompostable(1.0F, CheeseBlocks.HAM_COOKED_CAKE.get());
    }

    private static void registerStrippable(Block log, Block strippedLog) {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(log, strippedLog);
    }

    private static void registerHoeable(Block block) {
        HoeItem.HOE_LOOKUP = Maps.newHashMap(HoeItem.HOE_LOOKUP);
        HoeItem.HOE_LOOKUP.put(block, Blocks.FARMLAND.getDefaultState());
    }

    private static void registerCompostable(float chance, IItemProvider itemProvider) {
        ComposterBlock.CHANCES.put(itemProvider.asItem(), chance);
    }

    private static void registerFlammable(Block block, int encouragement, int flammability) {
        FireBlock fireblock = (FireBlock) Blocks.FIRE;
        fireblock.setFireInfo(block, encouragement, flammability);
    }
}
