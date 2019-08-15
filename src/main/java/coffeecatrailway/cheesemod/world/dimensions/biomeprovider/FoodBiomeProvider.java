package coffeecatrailway.cheesemod.world.dimensions.biomeprovider;

import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.SimplexNoiseGenerator;
import net.minecraft.world.gen.feature.structure.Structure;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2019
 */
public class FoodBiomeProvider extends BiomeProvider {

    private final List<Biome> biomes;
    private final World world;
    private final SimplexNoiseGenerator generator;

    public FoodBiomeProvider(FoodBiomeProviderSettings settings) {
        this.biomes = settings.getBiomes();
        this.world = settings.getWorld();
        SharedSeedRandom random = new SharedSeedRandom(this.world.getSeed());
        random.skip(17292);
        this.generator = new SimplexNoiseGenerator(random);
    }

    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return biomes;
    }

    @Override
    public Biome getBiome(int x, int y) {
        int i = x >> 4;
        int j = y >> 4;
        float f = this.func_222365_c(i * 2 + 1, j * 2 + 1);
        f += this.generator.getValue(i, j) * 100;
        f = MathHelper.clamp(f, -100.0f, 80.0f);
        return this.biomes.get((int) map(f, -100.0f, 80.0f, 0, this.biomes.size() - 1));
    }

    private float map(float from, float fromMin, float fromMax, float toMin, float toMax) {
        float fromAbs = from - fromMin;
        float fromMaxAbs = fromMax - fromMin;

        float normal = fromAbs / fromMaxAbs;

        float toMaxAbs = toMax - toMin;
        float toAbs = toMaxAbs * normal;

        return toAbs + toMin;
    }

    public float func_222365_c(int p_222365_1_, int p_222365_2_) {
        int i = p_222365_1_ / 2;
        int j = p_222365_2_ / 2;
        int k = p_222365_1_ % 2;
        int l = p_222365_2_ % 2;
        float f = 100.0F - MathHelper.sqrt((float) (p_222365_1_ * p_222365_1_ + p_222365_2_ * p_222365_2_)) * 8.0F;
        f = MathHelper.clamp(f, -100.0F, 80.0F);

        for (int i1 = -12; i1 <= 12; ++i1) {
            for (int j1 = -12; j1 <= 12; ++j1) {
                long k1 = (long) (i + i1);
                long l1 = (long) (j + j1);
                if (k1 * k1 + l1 * l1 > 4096L && this.generator.getValue((double) k1, (double) l1) < (double) -0.9F) {
                    float f1 = (MathHelper.abs((float) k1) * 3439.0F + MathHelper.abs((float) l1) * 147.0F) % 13.0F + 9.0F;
                    float f2 = (float) (k - i1 * 2);
                    float f3 = (float) (l - j1 * 2);
                    float f4 = 100.0F - MathHelper.sqrt(f2 * f2 + f3 * f3) * f1;
                    f4 = MathHelper.clamp(f4, -100.0F, 80.0F);
                    f = Math.max(f, f4);
                }
            }
        }

        return f;
    }

    @Override
    public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
        Biome[] biomes = new Biome[width * length];

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < length; ++j) {
                int k = i + x;
                int l = j + z;
                Biome biome = this.getBiome(k, l);

                biomes[i + j * width] = biome;
            }
        }

        return biomes;
    }

    @Override
    public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
        return Sets.newHashSet(this.biomes);
    }

    @Nullable
    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
        boolean contains = false;
        for (Biome biome : this.biomes)
            if (biomes.contains(biome))
                contains = true;
        return contains ? new BlockPos(x - range + random.nextInt(range * 2 + 1), 0, z - range + random.nextInt(range * 2 + 1)) : null;
    }

    @Override
    public boolean hasStructure(Structure<?> structureIn) {
        return false;
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (this.topBlocksCache.isEmpty())
            this.biomes.forEach(biome -> this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop()));

        return this.topBlocksCache;
    }
}
