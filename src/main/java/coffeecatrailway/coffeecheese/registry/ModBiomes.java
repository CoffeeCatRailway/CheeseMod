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
    private static final int CHEESE_WATER_COLOR = 0xdbc443;
    private static final int CHEESE_WATER_FOG_COLOR = 0x494116;
    private static final int CHEESE_GRASS_COLOR = 0xffdf51;

    private static final int GRILLED_CHEESE_WATER_COLOR = 0x96852e;
    private static final int GRILLED_CHEESE_WATER_FOG_COLOR = 0x6b5e21;
    private static final int GRILLED_CHEESE_GRASS_COLOR = 0x917d2e;

    private static final int HAM_RAW_WATER_COLOR = 0xd46a52;
    private static final int HAM_RAW_WATER_FOG_COLOR = 0xa0503e;
    private static final int HAM_RAW_GRASS_COLOR = 0xff7f63;

    private static final int HAM_COOKED_WATER_COLOR = 0xe0c296;
    private static final int HAM_COOKED_WATER_FOG_COLOR = 0x9e8969;
    private static final int HAM_COOKED_GRASS_COLOR = 0xffe6ba;

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(CHEESE_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD);
        BiomeDictionary.addTypes(CHEESE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(GRILLED_CHEESE_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT);
        BiomeDictionary.addTypes(GRILLED_CHEESE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);

        BiomeDictionary.addTypes(HAM_RAW_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD);
        BiomeDictionary.addTypes(HAM_RAW_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(HAM_COOKED_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT);
        BiomeDictionary.addTypes(HAM_COOKED_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);
    }

    public static void addBiomeFeatures() {
        for (Biome biome : ForgeRegistries.BIOMES.getValues())
            if (biome instanceof IHasFeatures)
                ((IHasFeatures) biome).addFeatures();
    }
}
