package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.world.dimension.CheeseDimension;
import coffeecatrailway.cheesemod.world.dimension.HamDimension;
import coffeecatrailway.cheesemod.world.dimension.biomeprovider.FoodBiomeProvider;
import coffeecatrailway.cheesemod.world.dimension.biomeprovider.FoodBiomeProviderSettings;
import coffeecatrailway.cheesemod.world.dimension.chunkgenerator.FoodDimensionChunkGen;
import coffeecatrailway.cheesemod.world.dimension.chunkgenerator.FoodDimensionGenSettings;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2019
 */
public class ModDimensions {

    /// Dimensions ///
    public static ModDimension CHEESE;
    public static ModDimension HAM;

    /// Chunk Generator Types ///
    public static ChunkGeneratorType<FoodDimensionGenSettings, FoodDimensionChunkGen> FOOD_CHUNK_GEN_TYPE;

    /// Biome Providers ///
    public static BiomeProviderType<FoodBiomeProviderSettings, FoodBiomeProvider> FOOD_BIOME_PROVIDER_TYPE;

    public static void registerDimensions(RegistryEvent.Register<ModDimension> event) {
        if (!event.getName().equals(ForgeRegistries.MOD_DIMENSIONS.getRegistryName())) return;

        CHEESE = new ModDimension() {
            @Override
            public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
                return CheeseDimension::new;
            }
        };
        registerDimension("cheese_dim", CHEESE);
        HAM = new ModDimension() {
            @Override
            public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
                return HamDimension::new;
            }
        };
        registerDimension("ham_dim", HAM);

        CheeseMod.LOGGER.info("Dimensions registered");
    }

    public static void registerChunkGeneratorTypes(RegistryEvent.Register<ChunkGeneratorType<?, ?>> event) {
        if (!event.getName().equals(ForgeRegistries.CHUNK_GENERATOR_TYPES.getRegistryName())) return;

        FOOD_CHUNK_GEN_TYPE = registerChunkGeneratorType("food_dim_type", new ChunkGeneratorType<>(FoodDimensionChunkGen::new, false, FoodDimensionGenSettings::new));

        CheeseMod.LOGGER.info("Chunk generator types registered");
    }

    public static void registerBiomeProviderTypes(RegistryEvent.Register<BiomeProviderType<?, ?>> event) {
        if (!event.getName().equals(ForgeRegistries.BIOME_PROVIDER_TYPES.getRegistryName())) return;

        FOOD_BIOME_PROVIDER_TYPE = registerBiomeProviderType("food_dim_type", new BiomeProviderType<>(FoodBiomeProvider::new, FoodBiomeProviderSettings::new));

        CheeseMod.LOGGER.info("Biome provider types registered");
    }

    private static void registerDimension(String name, ModDimension dimension) {
        ResourceLocation id = CheeseMod.getLocation(name);
        dimension.setRegistryName(id);
        ForgeRegistries.MOD_DIMENSIONS.register(dimension);
        DimensionManager.registerDimension(id, dimension, new PacketBuffer(Unpooled.buffer()), true);
    }

    private static <T extends ChunkGeneratorType<?, ?>> T registerChunkGeneratorType(String name, T generatorType) {
        generatorType.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.CHUNK_GENERATOR_TYPES.register(generatorType);
        return generatorType;
    }

    private static <T extends BiomeProviderType<?, ?>> T registerBiomeProviderType(String name, T generatorType) {
        generatorType.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.BIOME_PROVIDER_TYPES.register(generatorType);
        return generatorType;
    }
}
