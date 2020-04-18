package coffeecatrailway.coffeecheese.common.world.dimension;

import coffeecatrailway.coffeecheese.registry.ModFluids;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.server.ServerWorld;

/**
 * @author CoffeeCatRailway - Bagu_Chan https://github.com/pentantan
 * Created: 15/01/2020
 */
public class FoodWorldChunkGenerator extends NoiseChunkGenerator<FoodWorldChunkGenerator.FoodWorldGenSettings> {

    private static final float[] biomeWeights = Util.make(new float[25], (weight) -> {
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
                weight[i + 2 + (j + 2) * 5] = f;
            }
        }
    });

    private final OctavesNoiseGenerator depthNoise;
    private final boolean isAmplified;
    private final World world;

    public FoodWorldChunkGenerator(World world, BiomeProvider biomeProvider, FoodWorldGenSettings foodWorldGenSettings) {
        super(world, biomeProvider, 4, 10, 256, foodWorldGenSettings, true);
        this.randomSeed.skip(2620);
        this.world = world;
        this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 15, 0);
        this.isAmplified = world.getWorldInfo().getGenerator() == WorldType.AMPLIFIED;
    }

    @Override
    public void spawnMobs(WorldGenRegion region) {
        int chunkX = region.getMainChunkX();
        int chunkZ = region.getMainChunkZ();

        SharedSeedRandom random = new SharedSeedRandom();
        random.setDecorationSeed(region.getSeed(), chunkX << 4, chunkZ << 4);

        if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING))
            super.spawnMobs(region);
    }

    @Override
    public void spawnMobs(ServerWorld world, boolean p_203222_2_, boolean p_203222_3_) {
        if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
            super.spawnMobs(world, p_203222_2_, p_203222_3_);
        }
    }

    @Override
    protected void fillNoiseColumn(double[] adouble, int noiseX, int noiseZ) {
        this.calcNoiseColumn(adouble, noiseX, noiseZ, 684.412f, 684.412f, 8.555149841308594d, 4.277574920654297d, -10, 3);
    }

    @Override
    protected double func_222545_a(double xVal, double zVal, int yVal) {
        double d1 = ((double) yVal - (8.5d + xVal * 8.5d / 8.0d * 4.0d)) * 12.0d * 128.0d / 256.0d / zVal;
        if (d1 < 0.0d)
            d1 *= 4.0d;

        return d1;
    }

    @Override
    protected double[] getBiomeNoiseColumn(int chunkX, int chunkZ) {
        double[] adouble = new double[2];
        float f = 0.0f;
        float f1 = 0.0f;
        float f2 = 0.0f;
        int sea = this.getSeaLevel();
        float f3 = this.biomeProvider.getNoiseBiome(chunkX, sea, chunkZ).getDepth();

        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                Biome biome = this.biomeProvider.getNoiseBiome(chunkX + j, sea, chunkZ + k);
                float f4 = biome.getDepth();
                float f5 = biome.getScale();
                if (this.isAmplified && f4 > 0.0f) {
                    f4 = 1.0f + f4 * 2.0f;
                    f5 = 1.0f + f5 * 4.0f;
                }

                float f6 = biomeWeights[j + 2 + (k + 2) * 5] / (f4 + 2.0f);
                if (biome.getDepth() > f3)
                    f6 /= 2.0f;

                f += f5 * f6;
                f1 += f4 * f6;
                f2 += f6;
            }
        }

        f = f / f2;
        f1 = f1 / f2;
        f = f * 0.9f + 0.1f;
        f1 = (f1 * 4.0f - 1.0f) / 8.0f;
        adouble[0] = f1 + this.getDepthNoise(chunkX, chunkZ);
        adouble[1] = f;
        return adouble;
    }

    private double getDepthNoise(int noiseX, int noiseZ) {
        double d0 = this.depthNoise.getValue(noiseX * 200.0d, 10.0d, noiseZ * 200.0d, 1.0d, 0.0d, true) / 8000.0d;
        if (d0 < 0.0D)
            d0 = -d0 * 0.3D;

        d0 = d0 * 3.0D - 2.0D;
        if (d0 < 0.0D)
            d0 = d0 / 28.0D;
        else {
            if (d0 > 1.0D)
                d0 = 1.0D;
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
        return FoodWorldGenSettings.SEA_LEVEL;
    }

    public static class FoodWorldGenSettings extends GenerationSettings {

        public static final int SEA_LEVEL = 65;

        public static FoodWorldGenSettings createDefault() {
            FoodWorldGenSettings foodWorldGenSettings = new FoodWorldGenSettings();
            foodWorldGenSettings.setDefaultBlock(Blocks.STONE.getDefaultState());
            foodWorldGenSettings.setDefaultFluid(ModFluids.VINEGAR.get().getDefaultState().getBlockState());

            return foodWorldGenSettings;
        }

        public int getBedrockFloorHeight() {
            return 0;
        }
    }
}
