package coffeecatrailway.cheesemod.world.dimensions.chunkgenerator;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.spawner.WorldEntitySpawner;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2019
 */
public class FoodDimensionChunkGen extends NoiseChunkGenerator<FoodDimensionGenSettings> {

    private static final float[] field_222576_h = Util.make(new float[25], (p_222575_0_) -> {
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
                p_222575_0_[i + 2 + (j + 2) * 5] = f;
            }
        }

    });

    private final OctavesNoiseGenerator depthNoise;

    public FoodDimensionChunkGen(IWorld world, BiomeProvider provider, FoodDimensionGenSettings settings) {
        super(world, provider, 4, 8, 256, settings, true);
        this.randomSeed.skip(2620);
        this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 16);
    }

    @Override
    public void spawnMobs(WorldGenRegion region) {
        int i = region.getMainChunkX();
        int j = region.getMainChunkZ();
        Biome biome = region.getChunk(i, j).getBiomes()[0];
        SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
        sharedseedrandom.setDecorationSeed(region.getSeed(), i << 4, j << 4);
        WorldEntitySpawner.performWorldGenSpawning(region, biome, i, j, sharedseedrandom);
    }

    @Override
    protected void func_222548_a(double[] p_222548_1_, int p_222548_2_, int p_222548_3_) {
        this.func_222546_a(p_222548_1_, p_222548_2_, p_222548_3_, (double) 684.412F, (double) 684.412F, 8.555149841308594D, 4.277574920654297D, 3, -10);
    }

    @Override
    protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
        double d1 = ((double) p_222545_5_ - (8.5D + p_222545_1_ * 8.5D / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / p_222545_3_;
        if (d1 < 0.0D) {
            d1 *= 4.0D;
        }

        return d1;
    }

    @Override
    protected double[] func_222549_a(int p_222549_1_, int p_222549_2_) {
        double[] adouble = new double[2];
        float f = 0.0F;
        float f1 = 0.0F;
        float f2 = 0.0F;
        float f3 = this.biomeProvider.func_222366_b(p_222549_1_, p_222549_2_).getDepth();

        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                Biome biome = this.biomeProvider.func_222366_b(p_222549_1_ + j, p_222549_2_ + k);
                float f4 = biome.getDepth();
                float f5 = biome.getScale();

                float f6 = field_222576_h[j + 2 + (k + 2) * 5] / (f4 + 2.0F);
                if (biome.getDepth() > f3) {
                    f6 /= 2.0F;
                }

                f += f5 * f6;
                f1 += f4 * f6;
                f2 += f6;
            }
        }

        f = f / f2;
        f1 = f1 / f2;
        f = f * 0.9F + 0.1F;
        f1 = (f1 * 4.0F - 1.0F) / 8.0F;
        adouble[0] = (double) f1 + this.func_222574_c(p_222549_1_, p_222549_2_);
        adouble[1] = (double) f;
        return adouble;
    }

    private double func_222574_c(int p_222574_1_, int p_222574_2_) {
        double d0 = this.depthNoise.func_215462_a((double) (p_222574_1_ * 200), 10.0D, (double) (p_222574_2_ * 200), 1.0D, 0.0D, true) / 8000.0D;
        if (d0 < 0.0D) {
            d0 = -d0 * 0.3D;
        }

        d0 = d0 * 3.0D - 2.0D;
        if (d0 < 0.0D) {
            d0 = d0 / 28.0D;
        } else {
            if (d0 > 1.0D) {
                d0 = 1.0D;
            }

            d0 = d0 / 40.0D;
        }

        return d0;
    }

    @Override
    public int getGroundHeight() {
        return this.world.getSeaLevel() + 1;
    }

    @Override
    public int getSeaLevel() {
        return 63;
    }
}
