package coffeecatrailway.coffeecheese.common.world;

import coffeecatrailway.coffeecheese.ModCheeseConfig;
import coffeecatrailway.coffeecheese.common.world.biome.BaseFoodBiome;
import coffeecatrailway.coffeecheese.common.world.feature.ModOreFeature;
import coffeecatrailway.coffeecheese.common.world.feature.ModOreFeatureConfig;
import coffeecatrailway.coffeecheese.registry.ModBiomes;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModEntities;
import coffeecatrailway.coffeecheese.registry.ModFeatures;
import com.google.common.base.Predicate;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
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
                if (ModCheeseConfig.cheeseOreNetherGen.get())
                    addOre(biome, ModBlocks.CHEESE_METAL_ORE_NETHER.get().getDefaultState(), ModCheeseConfig.cheeseOreNetherSize.get(), ModCheeseConfig.cheeseOreNetherRegionSize.get(),
                            TARGET_NETHER, RANGE_FULL);

                if (ModCheeseConfig.grilledCheeseOreNetherGen.get())
                    addOre(biome, ModBlocks.GRILLED_CHEESE_METAL_ORE_NETHER.get().getDefaultState(), ModCheeseConfig.grilledCheeseOreNetherSize.get(), ModCheeseConfig.grilledCheeseOreNetherRegionSize.get(),
                            TARGET_NETHER, RANGE_FULL);

                if (ModCheeseConfig.hamRawOreNetherGen.get())
                    addOre(biome, ModBlocks.HAM_RAW_METAL_ORE_NETHER.get().getDefaultState(), ModCheeseConfig.hamRawOreNetherSize.get(), ModCheeseConfig.hamRawOreNetherRegionSize.get(),
                            TARGET_NETHER, RANGE_FULL);

                if (ModCheeseConfig.hamCookedOreNetherGen.get())
                    addOre(biome, ModBlocks.HAM_COOKED_METAL_ORE_NETHER.get().getDefaultState(), ModCheeseConfig.hamCookedOreNetherSize.get(), ModCheeseConfig.hamCookedOreNetherRegionSize.get(),
                            TARGET_NETHER, RANGE_FULL);

            } else if (biome.getCategory() == Biome.Category.THEEND) { /// END ///
                if (ModCheeseConfig.cheeseOreEndGen.get())
                    addOre(biome, ModBlocks.CHEESE_METAL_ORE_END.get().getDefaultState(), ModCheeseConfig.cheeseOreEndSize.get(), ModCheeseConfig.cheeseOreEndRegionSize.get(),
                            TARGET_END, RANGE_FULL);

                if (ModCheeseConfig.grilledCheeseOreEndGen.get())
                    addOre(biome, ModBlocks.GRILLED_CHEESE_METAL_ORE_END.get().getDefaultState(), ModCheeseConfig.grilledCheeseOreEndSize.get(), ModCheeseConfig.grilledCheeseOreEndRegionSize.get(),
                            TARGET_END, RANGE_FULL);

                if (ModCheeseConfig.hamRawOreEndGen.get())
                    addOre(biome, ModBlocks.HAM_RAW_METAL_ORE_END.get().getDefaultState(), ModCheeseConfig.hamRawOreEndSize.get(), ModCheeseConfig.hamRawOreEndRegionSize.get(),
                            TARGET_END, RANGE_FULL);

                if (ModCheeseConfig.hamCookedOreEndGen.get())
                    addOre(biome, ModBlocks.HAM_COOKED_METAL_ORE_END.get().getDefaultState(), ModCheeseConfig.hamCookedOreEndSize.get(), ModCheeseConfig.hamCookedOreEndRegionSize.get(),
                            TARGET_END, RANGE_FULL);

            } else if (biome instanceof BaseFoodBiome) { // FOOD WORLD
                boolean isCheese = biome == ModBiomes.CHEESE_FOREST.get() || biome == ModBiomes.CHEESE_PLAINS.get();
                boolean isGrilledCheese = biome == ModBiomes.GRILLED_CHEESE_FOREST.get() || biome == ModBiomes.GRILLED_CHEESE_PLAINS.get();
                boolean justCheese = isCheese || isGrilledCheese;

                boolean isHamRaw = biome == ModBiomes.HAM_RAW_FOREST.get() || biome == ModBiomes.HAM_RAW_PLAINS.get();
                boolean isHamCooked = biome == ModBiomes.HAM_COOKED_FOREST.get() || biome == ModBiomes.HAM_COOKED_PLAINS.get();
                boolean justHam = isHamRaw || isHamCooked;

                // Foodies
                if (isCheese && ModCheeseConfig.cheeseFoodieWeight.get() > 0)
                    ((BaseFoodBiome) biome).addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntities.CHEESE_FOODIE.get(),
                            ModCheeseConfig.cheeseFoodieWeight.get(), ModCheeseConfig.cheeseFoodieMin.get(), ModCheeseConfig.cheeseFoodieMax.get()));

                if (isGrilledCheese && ModCheeseConfig.grilledCheeseFoodieWeight.get() > 0)
                    ((BaseFoodBiome) biome).addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntities.GRILLED_CHEESE_FOODIE.get(),
                            ModCheeseConfig.grilledCheeseFoodieWeight.get(), ModCheeseConfig.grilledCheeseFoodieMin.get(), ModCheeseConfig.grilledCheeseFoodieMax.get()));

                if (isHamRaw && ModCheeseConfig.hamRawFoodieWeight.get() > 0)
                    ((BaseFoodBiome) biome).addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntities.HAM_RAW_FOODIE.get(),
                            ModCheeseConfig.hamRawFoodieWeight.get(), ModCheeseConfig.hamRawFoodieMin.get(), ModCheeseConfig.hamRawFoodieMax.get()));

                if (isHamCooked && ModCheeseConfig.hamCookedFoodieWeight.get() > 0)
                    ((BaseFoodBiome) biome).addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntities.HAM_COOKED_FOODIE.get(),
                            ModCheeseConfig.hamCookedFoodieWeight.get(), ModCheeseConfig.hamCookedFoodieMin.get(), ModCheeseConfig.hamCookedFoodieMax.get()));

                // Ores
                if (justCheese) {
                    addOre(biome, ModBlocks.CHEESE_METAL_ORE.get().getDefaultState(), ModCheeseConfig.cheeseOreSize.get(), ModCheeseConfig.cheeseOreRegionSize.get(),
                            TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
                    addOre(biome, ModBlocks.GRILLED_CHEESE_METAL_ORE.get().getDefaultState(), ModCheeseConfig.grilledCheeseOreSize.get(), ModCheeseConfig.grilledCheeseOreRegionSize.get(),
                            TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
                }
                if (justHam) {
                    addOre(biome, ModBlocks.HAM_RAW_METAL_ORE.get().getDefaultState(), ModCheeseConfig.hamRawOreSize.get(), ModCheeseConfig.hamRawOreRegionSize.get(),
                            TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));
                    addOre(biome, ModBlocks.HAM_COOKED_METAL_ORE.get().getDefaultState(), ModCheeseConfig.hamCookedOreSize.get(), ModCheeseConfig.hamCookedOreRegionSize.get(),
                            TARGET_OVERWORLD, new CountRangeConfig(20, 16, 20, 80));

                    if (isHamRaw)
                        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModFeatures.BACON_RAW_BALL.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(3)));
                    if (isHamCooked)
                        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModFeatures.BACON_COOKED_BALL.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(3)));
                }
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
