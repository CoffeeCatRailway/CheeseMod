package coffeecatrailway.cheesemod.world.dimension;

import coffeecatrailway.cheesemod.core.ModDimensions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2019
 */
public class FoodDimension extends Dimension {

    private Biome[] biomes;

    public FoodDimension(World world, DimensionType type, Biome... biomes) {
        super(world, type);
        this.biomes = biomes;
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        return ModDimensions.FOOD_CHUNK_GEN_TYPE.create(this.world, ModDimensions.FOOD_BIOME_PROVIDER_TYPE.create(ModDimensions.FOOD_BIOME_PROVIDER_TYPE.createSettings()
                .setBiomes(this.biomes).setWorld(this.world)), ModDimensions.FOOD_CHUNK_GEN_TYPE.createSettings());
    }

    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos chunkPos, boolean checkValid) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        return null;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        double d0 = MathHelper.frac((double) worldTime / 24000.0D - 0.25D);
        double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
        return (float) (d0 * 2.0D + d1) / 3.0F;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        float f = MathHelper.cos(celestialAngle * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 0.7529412F;
        float f2 = 0.84705883F;
        float f3 = 1.0F;
        f1 = f1 * (f * 0.94F + 0.06F);
        f2 = f2 * (f * 0.94F + 0.06F);
        f3 = f3 * (f * 0.91F + 0.09F);
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    public boolean canRespawnHere() {
        return true;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    public boolean hasSkyLight() {
        return true;
    }
}
