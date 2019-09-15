package coffeecatrailway.cheesemod.common.world;

import coffeecatrailway.cheesemod.ModConfig;
import coffeecatrailway.cheesemod.common.world.biome.FoodBiome;
import coffeecatrailway.cheesemod.common.world.feature.ModOreFeature;
import coffeecatrailway.cheesemod.common.world.feature.ModOreFeatureConfig;
import coffeecatrailway.cheesemod.core.ModBiomes;
import coffeecatrailway.cheesemod.core.ModBlocks;
import coffeecatrailway.cheesemod.core.ModEntityTypes;
import coffeecatrailway.cheesemod.core.ModFeatures;
import com.google.common.base.Predicate;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 26/07/2019
 */
public class ModWorldFeatures {

    private static final CountRangeConfig RANGE_FULL = new CountRangeConfig(20, 0, 0, 256);

    private static final Predicate<BlockState> TARGET_NETHER = s -> s.getBlock() == Blocks.NETHERRACK;
    private static final Predicate<BlockState> TARGET_END = s -> s.getBlock() == Blocks.END_STONE;
    private static final Predicate<BlockState> TARGET_OVERWORLD = s -> Tags.Blocks.STONE.contains(s.getBlock());

    public static void addFeatures() {
        ForgeRegistries.BIOMES.forEach(biome -> {
            if (biome.getCategory() == Biome.Category.NETHER) { /// NETHER ///
                addOre(biome, ModBlocks.CHEESE_METAL_ORE_NETHER.getDefaultState(), 10, 8, TARGET_NETHER, RANGE_FULL);
                addOre(biome, ModBlocks.GRILLED_CHEESE_METAL_ORE_NETHER.getDefaultState(), 10, 8, TARGET_NETHER, RANGE_FULL);
                addOre(biome, ModBlocks.HAM_RAW_METAL_ORE_NETHER.getDefaultState(), 10, 8, TARGET_NETHER, RANGE_FULL);
                addOre(biome, ModBlocks.HAM_COOKED_METAL_ORE_NETHER.getDefaultState(), 10, 8, TARGET_NETHER, RANGE_FULL);

            } else if (biome.getCategory() == Biome.Category.THEEND) { /// END ///
                addOre(biome, ModBlocks.CHEESE_METAL_ORE_END.getDefaultState(), 10, 8, TARGET_END, RANGE_FULL);
                addOre(biome, ModBlocks.GRILLED_CHEESE_METAL_ORE_END.getDefaultState(), 10, 8, TARGET_END, RANGE_FULL);
                addOre(biome, ModBlocks.HAM_RAW_METAL_ORE_END.getDefaultState(), 10, 8, TARGET_END, RANGE_FULL);
                addOre(biome, ModBlocks.HAM_COOKED_METAL_ORE_END.getDefaultState(), 10, 8, TARGET_END, RANGE_FULL);

            } else { /// OVERWORLD ///
                boolean isCheese = biome == ModBiomes.CHEESE_FOREST || biome == ModBiomes.CHEESE_PLAINS;
                boolean isGrilledCheese = biome == ModBiomes.GRILLED_CHEESE_FOREST || biome == ModBiomes.GRILLED_CHEESE_PLAINS;
                boolean justCheese = isCheese || isGrilledCheese;

                boolean isHamRaw = biome == ModBiomes.HAM_RAW_FOREST || biome == ModBiomes.HAM_RAW_PLAINS;
                boolean isHamCooked = biome == ModBiomes.HAM_COOKED_FOREST || biome == ModBiomes.HAM_COOKED_PLAINS;
                boolean justHam = isHamRaw || isHamCooked;

                // Foodies
                if (biome instanceof FoodBiome) {
                    if (isCheese)
                        ((FoodBiome) biome).addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityTypes.CHEESE_FOODIE, 100, 1, 3));
                    if (isGrilledCheese)
                        ((FoodBiome) biome).addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityTypes.GRILLED_CHEESE_FOODIE, 100, 1, 3));
                    if (isHamRaw)
                        ((FoodBiome) biome).addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityTypes.HAM_RAW_FOODIE, 100, 1, 3));
                    if (isHamCooked)
                        ((FoodBiome) biome).addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityTypes.HAM_COOKED_FOODIE, 100, 1, 3));
                }

                // Ores
                if (justCheese) {
                    addOre(biome, ModBlocks.CHEESE_METAL_ORE.getDefaultState(), 10, 8, TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
                    addOre(biome, ModBlocks.GRILLED_CHEESE_METAL_ORE.getDefaultState(), 10, 8, TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
                }
                if (justHam) {
                    addOre(biome, ModBlocks.HAM_RAW_METAL_ORE.getDefaultState(), 10, 8, TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
                    addOre(biome, ModBlocks.HAM_COOKED_METAL_ORE.getDefaultState(), 10, 8, TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
                }

                // Extra Trees
                if (!justCheese && !justHam) {
                    if (biome.getCategory() == Biome.Category.FOREST) {
                        addCheeseTrees(biome);
                        addHamTrees(biome);
                    }
                    addPineapples(biome);
                }

                if (biome.getCategory() == Biome.Category.PLAINS) {
                    if (justCheese)
                        addCheeseTrees(biome);
                    if (justHam)
                        addHamTrees(biome);
                }

                // Structures
                if (biome.getCategory() != Biome.Category.OCEAN && biome.getCategory() != Biome.Category.RIVER) {
                    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModFeatures.PINE_HUT, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
                    biome.addStructure(ModFeatures.PINE_HUT, IFeatureConfig.NO_FEATURE_CONFIG);
                }
            }
        });
    }

    private static void addPineapples(Biome biome) {
        double chance = ModConfig.BIOME.pineappleSpawnChance.get();
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.PINEAPPLE.getDefaultState()), Placement.COUNT_CHANCE_HEIGHTMAP, new HeightWithChanceConfig(ModConfig.BIOME.pineappleSpawnCount.get(), (float) chance)));
    }

    private static void addCheeseTrees(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(
                        new Feature[]{ModFeatures.GRILLED_CHEESE_TREE}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.2F}, ModFeatures.CHEESE_TREE, IFeatureConfig.NO_FEATURE_CONFIG),
                Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.05F, 1)));
    }

    private static void addHamTrees(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(
                        new Feature[]{ModFeatures.HAM_COOKED_TREE}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.2F}, ModFeatures.HAM_RAW_TREE, IFeatureConfig.NO_FEATURE_CONFIG),
                Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.05F, 0)));
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
