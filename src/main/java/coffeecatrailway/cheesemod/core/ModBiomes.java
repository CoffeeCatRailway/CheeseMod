package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.world.biomes.FoodBiome;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class ModBiomes {

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
    public static FoodBiome CHEESE_FOREST;
    public static FoodBiome CHEESE_PLAINS;
    public static FoodBiome CHEESE_FOREST_DIM;
    public static FoodBiome CHEESE_PLAINS_DIM;

    public static FoodBiome GRILLED_CHEESE_FOREST;
    public static FoodBiome GRILLED_CHEESE_PLAINS;
    public static FoodBiome GRILLED_CHEESE_FOREST_DIM;
    public static FoodBiome GRILLED_CHEESE_PLAINS_DIM;

    public static FoodBiome HAM_RAW_FOREST;
    public static FoodBiome HAM_RAW_PLAINS;
    public static FoodBiome HAM_RAW_FOREST_DIM;
    public static FoodBiome HAM_RAW_PLAINS_DIM;

    public static FoodBiome HAM_COOKED_FOREST;
    public static FoodBiome HAM_COOKED_PLAINS;
    public static FoodBiome HAM_COOKED_FOREST_DIM;
    public static FoodBiome HAM_COOKED_PLAINS_DIM;

    public static void registerAll(RegistryEvent.Register<Biome> event) {
        if (!event.getName().equals(ForgeRegistries.BIOMES.getRegistryName())) return;

        CHEESE_FOREST = register("cheese_forest", new FoodBiome(CHEESE_WATER_COLOR, CHEESE_WATER_FOG_COLOR, CHEESE_GRASS_COLOR, Blocks.GRASS_BLOCK.getDefaultState(), ModFeatures.CHEESE_TREE, ModFeatures.GRILLED_CHEESE_TREE, null).noCustomGrass());
        CHEESE_PLAINS = register("cheese_plains", new FoodBiome(CHEESE_WATER_COLOR, CHEESE_WATER_FOG_COLOR, CHEESE_GRASS_COLOR, Blocks.GRASS_BLOCK.getDefaultState(), FoodBiome.getParent(CHEESE_FOREST)).noCustomGrass());
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(CHEESE_FOREST, 35));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(CHEESE_PLAINS, 35));
        CHEESE_FOREST_DIM = register("cheese_forest_dim", new FoodBiome(CHEESE_WATER_COLOR, CHEESE_WATER_FOG_COLOR, CHEESE_GRASS_COLOR, ModBlocks.CHEESE_GRASS_BLOCK.getDefaultState(), ModFeatures.CHEESE_TREE, ModFeatures.GRILLED_CHEESE_TREE, null).hasCustomGrass(true, false));
        CHEESE_PLAINS_DIM = register("cheese_plains_dim", new FoodBiome(CHEESE_WATER_COLOR, CHEESE_WATER_FOG_COLOR, CHEESE_GRASS_COLOR, ModBlocks.CHEESE_GRASS_BLOCK.getDefaultState(), FoodBiome.getParent(CHEESE_FOREST_DIM)).hasCustomGrass(true, false));

        GRILLED_CHEESE_FOREST = register("grilled_cheese_forest", new FoodBiome(GRILLED_CHEESE_WATER_COLOR, GRILLED_CHEESE_WATER_FOG_COLOR, GRILLED_CHEESE_GRASS_COLOR, Blocks.GRASS_BLOCK.getDefaultState(), ModFeatures.GRILLED_CHEESE_TREE, ModFeatures.CHEESE_TREE, null).noCustomGrass());
        GRILLED_CHEESE_PLAINS = register("grilled_cheese_plains", new FoodBiome(GRILLED_CHEESE_WATER_COLOR, GRILLED_CHEESE_WATER_FOG_COLOR, GRILLED_CHEESE_GRASS_COLOR, Blocks.GRASS_BLOCK.getDefaultState(), FoodBiome.getParent(GRILLED_CHEESE_FOREST)).noCustomGrass());
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(GRILLED_CHEESE_FOREST, 25));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(GRILLED_CHEESE_PLAINS, 25));
        GRILLED_CHEESE_FOREST_DIM = register("grilled_cheese_forest_dim", new FoodBiome(GRILLED_CHEESE_WATER_COLOR, GRILLED_CHEESE_WATER_FOG_COLOR, GRILLED_CHEESE_GRASS_COLOR, ModBlocks.GRILLED_CHEESE_GRASS_BLOCK.getDefaultState(), ModFeatures.GRILLED_CHEESE_TREE, ModFeatures.CHEESE_TREE, null).hasCustomGrass(true, true));
        GRILLED_CHEESE_PLAINS_DIM = register("grilled_cheese_plains_dim", new FoodBiome(GRILLED_CHEESE_WATER_COLOR, GRILLED_CHEESE_WATER_FOG_COLOR, GRILLED_CHEESE_GRASS_COLOR, ModBlocks.GRILLED_CHEESE_GRASS_BLOCK.getDefaultState(), FoodBiome.getParent(GRILLED_CHEESE_FOREST_DIM)).hasCustomGrass(true, true));

        HAM_RAW_FOREST = register("ham_raw_forest", new FoodBiome(HAM_RAW_WATER_COLOR, HAM_RAW_WATER_FOG_COLOR, HAM_RAW_GRASS_COLOR, Blocks.GRASS_BLOCK.getDefaultState(), ModFeatures.HAM_RAW_TREE, ModFeatures.HAM_COOKED_TREE, null).noCustomGrass());
        HAM_RAW_PLAINS = register("ham_raw_plains", new FoodBiome(HAM_RAW_WATER_COLOR, HAM_RAW_WATER_FOG_COLOR, HAM_RAW_GRASS_COLOR, Blocks.GRASS_BLOCK.getDefaultState(), FoodBiome.getParent(HAM_RAW_FOREST)).noCustomGrass());
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(HAM_RAW_FOREST, 35));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(HAM_RAW_PLAINS, 35));
        HAM_RAW_FOREST_DIM = register("ham_raw_forest_dim", new FoodBiome(HAM_RAW_WATER_COLOR, HAM_RAW_WATER_FOG_COLOR, HAM_RAW_GRASS_COLOR, ModBlocks.HAM_RAW_GRASS_BLOCK.getDefaultState(), ModFeatures.HAM_RAW_TREE, ModFeatures.HAM_COOKED_TREE, null).hasCustomGrass(false, false));
        HAM_RAW_PLAINS_DIM = register("ham_raw_plains_dim", new FoodBiome(HAM_RAW_WATER_COLOR, HAM_RAW_WATER_FOG_COLOR, HAM_RAW_GRASS_COLOR, ModBlocks.HAM_RAW_GRASS_BLOCK.getDefaultState(), FoodBiome.getParent(HAM_RAW_FOREST_DIM)).hasCustomGrass(false, false));

        HAM_COOKED_FOREST = register("ham_cooked_forest", new FoodBiome(HAM_COOKED_WATER_COLOR, HAM_COOKED_WATER_FOG_COLOR, HAM_COOKED_GRASS_COLOR, Blocks.GRASS_BLOCK.getDefaultState(), ModFeatures.HAM_COOKED_TREE, ModFeatures.HAM_RAW_TREE, null).noCustomGrass());
        HAM_COOKED_PLAINS = register("ham_cooked_plains", new FoodBiome(HAM_COOKED_WATER_COLOR, HAM_COOKED_WATER_FOG_COLOR, HAM_COOKED_GRASS_COLOR, Blocks.GRASS_BLOCK.getDefaultState(), FoodBiome.getParent(HAM_COOKED_FOREST)).noCustomGrass());
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(HAM_COOKED_FOREST, 25));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(HAM_COOKED_PLAINS, 25));
        HAM_COOKED_FOREST_DIM = register("ham_cooked_forest_dim", new FoodBiome(HAM_COOKED_WATER_COLOR, HAM_COOKED_WATER_FOG_COLOR, HAM_COOKED_GRASS_COLOR, ModBlocks.HAM_COOKED_GRASS_BLOCK.getDefaultState(), ModFeatures.HAM_COOKED_TREE, ModFeatures.HAM_RAW_TREE, null).hasCustomGrass(false, true));
        HAM_COOKED_PLAINS_DIM = register("ham_cooked_plains_dim", new FoodBiome(HAM_COOKED_WATER_COLOR, HAM_COOKED_WATER_FOG_COLOR, HAM_COOKED_GRASS_COLOR, ModBlocks.HAM_COOKED_GRASS_BLOCK.getDefaultState(), FoodBiome.getParent(HAM_COOKED_FOREST_DIM)).hasCustomGrass(false, true));

        CheeseMod.LOGGER.info("Biomes registered");

    }

    private static <B extends Biome> B register(String name, B biome) {
        ResourceLocation id = new ResourceLocation(CheeseMod.MOD_ID, name);
        biome.setRegistryName(id);
        ForgeRegistries.BIOMES.register(biome);
        return biome;
    }
}
