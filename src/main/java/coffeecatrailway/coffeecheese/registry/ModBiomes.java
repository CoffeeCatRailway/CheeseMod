package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.ModCheeseConfig;
import coffeecatrailway.coffeecheese.common.world.biome.FoodBiome;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, CheeseMod.MOD_ID);

    /// Biome Colors ///
    private static final int CHEESE_WATER_COLOR = 14402627;
    private static final int CHEESE_WATER_FOG_COLOR = 4800790;
    private static final int CHEESE_GRASS_COLOR = 16768849;

    private static final int GRILLED_CHEESE_WATER_COLOR = 9864494;
    private static final int GRILLED_CHEESE_WATER_FOG_COLOR = 7036449;
    private static final int GRILLED_CHEESE_GRASS_COLOR = 9534766;

    private static final int HAM_RAW_WATER_COLOR = 13920850;
    private static final int HAM_RAW_WATER_FOG_COLOR = 10506302;
    private static final int HAM_RAW_GRASS_COLOR = 16744291;

    private static final int HAM_COOKED_WATER_COLOR = 14729878;
    private static final int HAM_COOKED_WATER_FOG_COLOR = 10389865;
    private static final int HAM_COOKED_GRASS_COLOR = 16770746;

    /// Biomes ///
    public static final RegistryObject<FoodBiome> CHEESE_FOREST = BIOMES.register("cheese_forest", () ->
            new FoodBiome(CHEESE_WATER_COLOR, CHEESE_WATER_FOG_COLOR, CHEESE_GRASS_COLOR,
                    Blocks.GRASS_BLOCK.getDefaultState(), ModFeatures.CHEESE_TREE, ModFeatures.GRILLED_CHEESE_TREE));
    public static final RegistryObject<FoodBiome> CHEESE_PLAINS = BIOMES.register("cheese_plains", () ->
            new FoodBiome(CHEESE_WATER_COLOR, CHEESE_WATER_FOG_COLOR, CHEESE_GRASS_COLOR,
                    Blocks.GRASS_BLOCK.getDefaultState()));

    public static final RegistryObject<FoodBiome> GRILLED_CHEESE_FOREST = BIOMES.register("grilled_cheese_forest", () ->
            new FoodBiome(GRILLED_CHEESE_WATER_COLOR, GRILLED_CHEESE_WATER_FOG_COLOR, GRILLED_CHEESE_GRASS_COLOR,
                    Blocks.GRASS_BLOCK.getDefaultState(), ModFeatures.GRILLED_CHEESE_TREE, ModFeatures.CHEESE_TREE));
    public static final RegistryObject<FoodBiome> GRILLED_CHEESE_PLAINS = BIOMES.register("grilled_cheese_plains", () ->
            new FoodBiome(GRILLED_CHEESE_WATER_COLOR, GRILLED_CHEESE_WATER_FOG_COLOR, GRILLED_CHEESE_GRASS_COLOR,
                    Blocks.GRASS_BLOCK.getDefaultState()));

    public static final RegistryObject<FoodBiome> HAM_RAW_FOREST = BIOMES.register("ham_raw_forest", () ->
            new FoodBiome(HAM_RAW_WATER_COLOR, HAM_RAW_WATER_FOG_COLOR, HAM_RAW_GRASS_COLOR,
                    Blocks.GRASS_BLOCK.getDefaultState(), ModFeatures.HAM_RAW_TREE, ModFeatures.HAM_COOKED_TREE));
    public static final RegistryObject<FoodBiome> HAM_RAW_PLAINS = BIOMES.register("ham_raw_plains", () ->
            new FoodBiome(HAM_RAW_WATER_COLOR, HAM_RAW_WATER_FOG_COLOR, HAM_RAW_GRASS_COLOR,
                    Blocks.GRASS_BLOCK.getDefaultState()));

    public static final RegistryObject<FoodBiome> HAM_COOKED_FOREST = BIOMES.register("ham_cooked_forest", () ->
            new FoodBiome(HAM_COOKED_WATER_COLOR, HAM_COOKED_WATER_FOG_COLOR, HAM_COOKED_GRASS_COLOR,
                    Blocks.GRASS_BLOCK.getDefaultState(), ModFeatures.HAM_COOKED_TREE, ModFeatures.HAM_RAW_TREE));
    public static final RegistryObject<FoodBiome> HAM_COOKED_PLAINS = BIOMES.register("ham_cooked_plains", () ->
            new FoodBiome(HAM_COOKED_WATER_COLOR, HAM_COOKED_WATER_FOG_COLOR, HAM_COOKED_GRASS_COLOR,
                    Blocks.GRASS_BLOCK.getDefaultState()));

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(CHEESE_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(CHEESE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OVERWORLD);

        int w1 = ModCheeseConfig.cheeseBiomeWeight.get();
        if (w1 > 0) {
            BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(CHEESE_FOREST.get(), w1));
            BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(CHEESE_PLAINS.get(), w1));
        }

        BiomeDictionary.addTypes(GRILLED_CHEESE_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.RARE);
        BiomeDictionary.addTypes(GRILLED_CHEESE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.RARE);

        int w2 = ModCheeseConfig.grilledCheeseBiomeWeight.get();
        if (w2 > 0) {
            BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(GRILLED_CHEESE_FOREST.get(), w2));
            BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(GRILLED_CHEESE_PLAINS.get(), w2));
        }

        BiomeDictionary.addTypes(HAM_RAW_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(HAM_RAW_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OVERWORLD);

        int w3 = ModCheeseConfig.hamRawBiomeWeight.get();
        if (w3 > 0) {
            BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(HAM_RAW_FOREST.get(), w3));
            BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(HAM_RAW_PLAINS.get(), w3));
        }

        BiomeDictionary.addTypes(HAM_COOKED_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.RARE);
        BiomeDictionary.addTypes(HAM_COOKED_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.RARE);

        int w4 = ModCheeseConfig.hamCookedBiomeWeight.get();
        if (w4 > 0) {
            BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(HAM_COOKED_FOREST.get(), w4));
            BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(HAM_COOKED_PLAINS.get(), w4));
        }
    }
}
