package coffeecatrailway.coffeecheese.common.world.dimension;

import coffeecatrailway.coffeecheese.common.world.biome.layer.ModLayerUtil;
import coffeecatrailway.coffeecheese.registry.ModBiomes;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.layer.Layer;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author CoffeeCatRailway - Bagu_Chan https://github.com/pentantan
 * Created: 15/01/2020
 */
public class FoodWorldBiomeProvider extends BiomeProvider {

    private final World world;
    private final Layer genBiomes;
    private final Layer biomeFactoryLayer;
    public static final Biome[] BIOMES = new Biome[]{
            ModBiomes.CHEESE_FOREST.get(), ModBiomes.CHEESE_PLAINS.get(),
            ModBiomes.GRILLED_CHEESE_FOREST.get(), ModBiomes.GRILLED_CHEESE_PLAINS.get(),
            ModBiomes.HAM_RAW_FOREST.get(), ModBiomes.HAM_RAW_PLAINS.get(),
            ModBiomes.HAM_COOKED_FOREST.get(), ModBiomes.HAM_COOKED_PLAINS.get()
    };

    public FoodWorldBiomeProvider(World world, OverworldGenSettings settings) {
        this.world = world;
        Layer[] alayer = ModLayerUtil.buildProcedure(world.getWorldInfo().getSeed(), world.getWorldType(), settings);
        this.genBiomes = alayer[0];
        this.biomeFactoryLayer = alayer[1];
    }

    @Override
    public Biome getBiome(int x, int y) {
        return this.biomeFactoryLayer.func_215738_a(x, y);
    }

    public Biome func_222366_b(int p_222366_1_, int p_222366_2_) {
        return this.genBiomes.func_215738_a(p_222366_1_, p_222366_2_);
    }

    @Override
    public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
        return this.biomeFactoryLayer.generateBiomes(x, z, width, length);
    }

    @Override
    public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
        int i = centerX - sideLength >> 2;
        int j = centerZ - sideLength >> 2;
        int k = centerX + sideLength >> 2;
        int l = centerZ + sideLength >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        Set<Biome> set = Sets.newHashSet();
        Collections.addAll(set, this.genBiomes.generateBiomes(i, j, i1, j1));
        return set;
    }

    @Nullable
    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        Biome[] abiome = this.genBiomes.generateBiomes(i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;

        for (int l1 = 0; l1 < i1 * j1; ++l1) {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            if (biomes.contains(abiome[l1])) {
                if (blockpos == null || random.nextInt(k1 + 1) == 0)
                    blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }

        return blockpos;
    }

    @Override
    public boolean hasStructure(Structure<?> structureIn) {
        return this.hasStructureCache.computeIfAbsent(structureIn, (p_205006_1_) -> {
            for (Biome biome : BIOMES)
                if (biome.hasStructure(p_205006_1_))
                    return true;

            return false;
        });
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (this.topBlocksCache.isEmpty())
            for (Biome biome : BIOMES)
                this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());

        return this.topBlocksCache;
    }
}
