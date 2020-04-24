package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.common.world.biome.*;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.ForgeRegistries;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class ModBiomes {

    /// Biome Colors & SurfaceBuilders ///
    private static final int CHEESE_WATER_COLOR = 0xdbc443;
    private static final int CHEESE_WATER_FOG_COLOR = 0x494116;
    private static final int CHEESE_GRASS_COLOR = 0xffdf51;
    private static final NonNullSupplier<SurfaceBuilderConfig> CHEESE_SURFACE = () -> new SurfaceBuilderConfig(ModBlocks.CHEESE_GRASS_BLOCK.get().getDefaultState(), SurfaceBuilder.DIRT, SurfaceBuilder.SAND);

    private static final int GRILLED_CHEESE_WATER_COLOR = 0x96852e;
    private static final int GRILLED_CHEESE_WATER_FOG_COLOR = 0x6b5e21;
    private static final int GRILLED_CHEESE_GRASS_COLOR = 0x917d2e;
    private static final NonNullSupplier<SurfaceBuilderConfig> GRILLED_CHEESE_SURFACE = () -> new SurfaceBuilderConfig(ModBlocks.GRILLED_CHEESE_GRASS_BLOCK.get().getDefaultState(), SurfaceBuilder.DIRT, SurfaceBuilder.SAND);

    private static final int HAM_RAW_WATER_COLOR = 0xd46a52;
    private static final int HAM_RAW_WATER_FOG_COLOR = 0xa0503e;
    private static final int HAM_RAW_GRASS_COLOR = 0xff7f63;
    private static final NonNullSupplier<SurfaceBuilderConfig> HAM_RAW_SURFACE = () -> new SurfaceBuilderConfig(ModBlocks.HAM_RAW_GRASS_BLOCK.get().getDefaultState(), SurfaceBuilder.DIRT, SurfaceBuilder.SAND);

    private static final int HAM_COOKED_WATER_COLOR = 0xe0c296;
    private static final int HAM_COOKED_WATER_FOG_COLOR = 0x9e8969;
    private static final int HAM_COOKED_GRASS_COLOR = 0xffe6ba;
    private static final NonNullSupplier<SurfaceBuilderConfig> HAM_COOKED_SURFACE = () -> new SurfaceBuilderConfig(ModBlocks.HAM_COOKED_GRASS_BLOCK.get().getDefaultState(), SurfaceBuilder.DIRT, SurfaceBuilder.SAND);

    // Cheese
    public static final RegistryEntry<CheeseFoodBiome> CHEESE_FOREST = REGISTRATE.biome("cheese_forest", prop -> new CheeseFoodBiome(prop, CHEESE_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.FOREST).depth(0.25f).scale(0.05f).temperature(0.5f).downfall(0.5f)
            .waterColor(CHEESE_WATER_COLOR).waterFogColor(CHEESE_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, CHEESE_SURFACE).register();

    public static final RegistryEntry<CheeseFoodBiome> CHEESE_FOREST_HILLS = REGISTRATE.biome("cheese_forest_hills", prop -> new CheeseFoodBiome(prop, CHEESE_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.FOREST).depth(0.45f).scale(0.3f).temperature(0.6f).downfall(0.6f)
            .waterColor(CHEESE_WATER_COLOR).waterFogColor(CHEESE_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, CHEESE_SURFACE).register();

    public static final RegistryEntry<CheeseFoodBiome> CHEESE_PLAINS = REGISTRATE.biome("cheese_plains", prop -> new CheeseFoodBiome(prop, CHEESE_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(0.125f).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .waterColor(CHEESE_WATER_COLOR).waterFogColor(CHEESE_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, CHEESE_SURFACE).register();

    // Grilled Cheese
    public static final RegistryEntry<GrilledCheeseFoodBiome> GRILLED_CHEESE_FOREST = REGISTRATE.biome("grilled_cheese_forest", prop -> new GrilledCheeseFoodBiome(prop, GRILLED_CHEESE_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.FOREST).depth(0.25f).scale(0.05f).temperature(0.5f).downfall(0.5f)
            .waterColor(GRILLED_CHEESE_WATER_COLOR).waterFogColor(GRILLED_CHEESE_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, GRILLED_CHEESE_SURFACE).register();

    public static final RegistryEntry<GrilledCheeseFoodBiome> GRILLED_CHEESE_FOREST_HILLS = REGISTRATE.biome("grilled_cheese_forest_hills", prop -> new GrilledCheeseFoodBiome(prop, GRILLED_CHEESE_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.FOREST).depth(0.45f).scale(0.3f).temperature(0.6f).downfall(0.6f)
            .waterColor(GRILLED_CHEESE_WATER_COLOR).waterFogColor(GRILLED_CHEESE_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, GRILLED_CHEESE_SURFACE).register();

    public static final RegistryEntry<GrilledCheeseFoodBiome> GRILLED_CHEESE_PLAINS = REGISTRATE.biome("grilled_cheese_plains", prop -> new GrilledCheeseFoodBiome(prop, GRILLED_CHEESE_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(0.125f).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .waterColor(GRILLED_CHEESE_WATER_COLOR).waterFogColor(GRILLED_CHEESE_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, GRILLED_CHEESE_SURFACE).register();

    // Ham Raw
    public static final RegistryEntry<HamRawFoodBiome> HAM_RAW_FOREST = REGISTRATE.biome("ham_raw_forest", prop -> new HamRawFoodBiome(prop, HAM_RAW_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.FOREST).depth(0.25f).scale(0.05f).temperature(0.5f).downfall(0.5f)
            .waterColor(HAM_RAW_WATER_COLOR).waterFogColor(HAM_RAW_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, HAM_RAW_SURFACE).register();

    public static final RegistryEntry<HamRawFoodBiome> HAM_RAW_FOREST_HILLS = REGISTRATE.biome("ham_raw_forest_hills", prop -> new HamRawFoodBiome(prop, HAM_RAW_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.FOREST).depth(0.45f).scale(0.3f).temperature(0.6f).downfall(0.6f)
            .waterColor(HAM_RAW_WATER_COLOR).waterFogColor(HAM_RAW_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, HAM_RAW_SURFACE).register();

    public static final RegistryEntry<HamRawFoodBiome> HAM_RAW_PLAINS = REGISTRATE.biome("ham_raw_plains", prop -> new HamRawFoodBiome(prop, HAM_RAW_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(0.125f).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .waterColor(HAM_RAW_WATER_COLOR).waterFogColor(HAM_RAW_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, HAM_RAW_SURFACE).register();

    // Ham Cooked
    public static final RegistryEntry<HamCookedFoodBiome> HAM_COOKED_FOREST = REGISTRATE.biome("ham_cooked_forest", prop -> new HamCookedFoodBiome(prop, HAM_COOKED_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.FOREST).depth(0.25f).scale(0.05f).temperature(0.5f).downfall(0.5f)
            .waterColor(HAM_COOKED_WATER_COLOR).waterFogColor(HAM_COOKED_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, HAM_COOKED_SURFACE).register();

    public static final RegistryEntry<HamCookedFoodBiome> HAM_COOKED_FOREST_HILLS = REGISTRATE.biome("ham_cooked_forest_hills", prop -> new HamCookedFoodBiome(prop, HAM_COOKED_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.FOREST).depth(0.45f).scale(0.3f).temperature(0.6f).downfall(0.6f)
            .waterColor(HAM_COOKED_WATER_COLOR).waterFogColor(HAM_COOKED_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, HAM_COOKED_SURFACE).register();

    public static final RegistryEntry<HamCookedFoodBiome> HAM_COOKED_PLAINS = REGISTRATE.biome("ham_cooked_plains", prop -> new HamCookedFoodBiome(prop, HAM_COOKED_GRASS_COLOR))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(0.125f).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .waterColor(HAM_COOKED_WATER_COLOR).waterFogColor(HAM_COOKED_WATER_FOG_COLOR).surfaceBuilder(SurfaceBuilder.DEFAULT, HAM_COOKED_SURFACE).register();

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(CHEESE_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD);
        BiomeDictionary.addTypes(CHEESE_FOREST_HILLS.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS);
        BiomeDictionary.addTypes(CHEESE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(GRILLED_CHEESE_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT);
        BiomeDictionary.addTypes(GRILLED_CHEESE_FOREST_HILLS.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS);
        BiomeDictionary.addTypes(GRILLED_CHEESE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);

        BiomeDictionary.addTypes(HAM_RAW_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD);
        BiomeDictionary.addTypes(HAM_RAW_FOREST_HILLS.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS);
        BiomeDictionary.addTypes(HAM_RAW_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(HAM_COOKED_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT);
        BiomeDictionary.addTypes(HAM_COOKED_FOREST_HILLS.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS);
        BiomeDictionary.addTypes(HAM_COOKED_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);
    }

    public static void addBiomeFeatures() {
        for (Biome biome : ForgeRegistries.BIOMES.getValues())
            if (biome instanceof IHasFeatures)
                ((IHasFeatures) biome).addFeatures();
    }

    public static void load() {
    }
}
