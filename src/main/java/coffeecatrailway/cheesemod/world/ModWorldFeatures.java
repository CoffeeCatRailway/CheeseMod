package coffeecatrailway.cheesemod.world;

import coffeecatrailway.cheesemod.core.ModBlocks;
import coffeecatrailway.cheesemod.world.feature.ModOreFeature;
import coffeecatrailway.cheesemod.world.feature.ModOreFeatureConfig;
import com.google.common.base.Predicate;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 26/07/2019
 */
public class ModWorldFeatures {

    private static final CountRangeConfig RANGE_FULL = new CountRangeConfig(20, 0, 0, 256);

    private static final Predicate<BlockState> TARGET_NETHER = s -> s.getBlock() == Blocks.NETHERRACK;
    private static final Predicate<BlockState> TARGET_END = s -> s.getBlock() == Blocks.END_STONE;
    private static final Predicate<BlockState> TARGET_OVERWORLD = s -> s.getBlock() == Blocks.STONE;

    public static void addFeatures() {
        ForgeRegistries.BIOMES.forEach(biome -> {
            if (biome.getCategory() == Biome.Category.NETHER) {         /// NETHER ///
                addOre(biome, ModBlocks.CHEESE_METAL_ORE_NETHER.getDefaultState(), 10, 8, TARGET_NETHER, RANGE_FULL);
                addOre(biome, ModBlocks.GRILLED_CHEESE_METAL_ORE_NETHER.getDefaultState(), 10, 8, TARGET_NETHER, RANGE_FULL);
                addOre(biome, ModBlocks.HAM_RAW_METAL_ORE_NETHER.getDefaultState(), 10, 8, TARGET_NETHER, RANGE_FULL);
                addOre(biome, ModBlocks.HAM_COOKED_METAL_ORE_NETHER.getDefaultState(), 10, 8, TARGET_NETHER, RANGE_FULL);

            } else if (biome.getCategory() == Biome.Category.THEEND) {  /// END ///
                addOre(biome, ModBlocks.CHEESE_METAL_ORE_END.getDefaultState(), 10, 8, TARGET_END, RANGE_FULL);
                addOre(biome, ModBlocks.GRILLED_CHEESE_METAL_ORE_END.getDefaultState(), 10, 8, TARGET_END, RANGE_FULL);
                addOre(biome, ModBlocks.HAM_RAW_METAL_ORE_END.getDefaultState(), 10, 8, TARGET_END, RANGE_FULL);
                addOre(biome, ModBlocks.HAM_COOKED_METAL_ORE_END.getDefaultState(), 10, 8, TARGET_END, RANGE_FULL);

            } else {                                                    /// OVERWORLD ///
                addOre(biome, ModBlocks.CHEESE_METAL_ORE.getDefaultState(), 10, 8, TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
                addOre(biome, ModBlocks.GRILLED_CHEESE_METAL_ORE.getDefaultState(), 10, 8, TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
                addOre(biome, ModBlocks.HAM_RAW_METAL_ORE.getDefaultState(), 10, 8, TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
                addOre(biome, ModBlocks.HAM_COOKED_METAL_ORE.getDefaultState(), 10, 8, TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
            }
        });
    }

    private static void addOre(Biome biome, BlockState state, int size, int regionSize, Predicate<BlockState> target, CountRangeConfig range) {
        addOre(biome, new ModOreFeatureConfig(state, size, regionSize, target), range);
    }

    private static void addOre(Biome biome, ModOreFeatureConfig config, CountRangeConfig rangeConfig) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(
                ModOreFeature.INSTANCE,
                config,
                Placement.COUNT_RANGE,
                rangeConfig
        ));
    }
}
