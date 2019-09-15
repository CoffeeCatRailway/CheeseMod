package coffeecatrailway.cheesemod.common.world.feature.structure;

import coffeecatrailway.cheesemod.CheeseMod;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 15/09/2019
 */
public class PineHutStructure extends Structure<NoFeatureConfig> {

    public static final ResourceLocation LOCATION = CheeseMod.getLocation("hut/pine_hut");
    public static final BlockPos CENTER = new BlockPos(4, 1, 3);

    public PineHutStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
        super(function);
    }

    @Override
    public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {
        return chunkPosX == 0 && chunkPosZ == 0;
    }

    @Override
    public String getStructureName() {
        return CheeseMod.getLocation("pine_hut").toString();
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public IStartFactory getStartFactory() {
        return PineHutStructure.Start::new;
    }

    public static class Start extends StructureStart {

        public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, biome, bounds, reference, seed);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager template, int chunkX, int chunkZ, Biome biome) {
            int i = chunkX * 16;
            int j = chunkZ * 16;
            BlockPos pos = new BlockPos(i, 64, j);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            this.components.add(new PineHutStructurePiece(template, PineHutStructure.LOCATION, pos, rotation, 0));
            this.recalculateStructureSize();
        }
    }
}
