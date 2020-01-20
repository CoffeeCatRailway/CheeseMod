package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.world.biome.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
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
    public static final int CHEESE_WATER_COLOR = 14402627;
    public static final int CHEESE_WATER_FOG_COLOR = 4800790;
    public static final int CHEESE_GRASS_COLOR = 16768849;

    public static final int GRILLED_CHEESE_WATER_COLOR = 9864494;
    public static final int GRILLED_CHEESE_WATER_FOG_COLOR = 7036449;
    public static final int GRILLED_CHEESE_GRASS_COLOR = 9534766;

    public static final int HAM_RAW_WATER_COLOR = 13920850;
    public static final int HAM_RAW_WATER_FOG_COLOR = 10506302;
    public static final int HAM_RAW_GRASS_COLOR = 16744291;

    public static final int HAM_COOKED_WATER_COLOR = 14729878;
    public static final int HAM_COOKED_WATER_FOG_COLOR = 10389865;
    public static final int HAM_COOKED_GRASS_COLOR = 16770746;

    /// Biomes ///
    public static final RegistryObject<BaseFoodBiome> CHEESE_FOREST = BIOMES.register("cheese_forest", () ->
            new CheeseFoodBiome(Biome.Category.FOREST));
    public static final RegistryObject<BaseFoodBiome> CHEESE_PLAINS = BIOMES.register("cheese_plains", () ->
            new CheeseFoodBiome(Biome.Category.PLAINS));

    public static final RegistryObject<BaseFoodBiome> GRILLED_CHEESE_FOREST = BIOMES.register("grilled_cheese_forest", () ->
            new GrilledCheeseFoodBiome(Biome.Category.FOREST));
    public static final RegistryObject<BaseFoodBiome> GRILLED_CHEESE_PLAINS = BIOMES.register("grilled_cheese_plains", () ->
            new GrilledCheeseFoodBiome(Biome.Category.PLAINS));

    public static final RegistryObject<BaseFoodBiome> HAM_RAW_FOREST = BIOMES.register("ham_raw_forest", () ->
            new HamRawFoodBiome(Biome.Category.FOREST));
    public static final RegistryObject<BaseFoodBiome> HAM_RAW_PLAINS = BIOMES.register("ham_raw_plains", () ->
            new HamRawFoodBiome(Biome.Category.PLAINS));

    public static final RegistryObject<BaseFoodBiome> HAM_COOKED_FOREST = BIOMES.register("ham_cooked_forest", () ->
            new HamCookedFoodBiome(Biome.Category.FOREST));
    public static final RegistryObject<BaseFoodBiome> HAM_COOKED_PLAINS = BIOMES.register("ham_cooked_plains", () ->
            new HamCookedFoodBiome(Biome.Category.PLAINS));

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(CHEESE_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD);
        BiomeDictionary.addTypes(CHEESE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(GRILLED_CHEESE_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT, BiomeDictionary.Type.RARE);
        BiomeDictionary.addTypes(GRILLED_CHEESE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.RARE);

        BiomeDictionary.addTypes(HAM_RAW_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD);
        BiomeDictionary.addTypes(HAM_RAW_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(HAM_COOKED_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT, BiomeDictionary.Type.RARE);
        BiomeDictionary.addTypes(HAM_COOKED_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.RARE);
    }

    public static void addBiomeFeatures() {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome instanceof BaseFoodBiome) {
                ((BaseFoodBiome) biome).addFeatures();
            }
        }
    }
}
