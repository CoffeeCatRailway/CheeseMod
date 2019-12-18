package coffeecatrailway.coffeecheese.common.world.feature.structure;

import coffeecatrailway.coffeecheese.CheeseMod;
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

    public static final ResourceLocation VARIANT_1 = CheeseMod.getLocation("hut/pine_camp_roof");
    public static final ResourceLocation VARIANT_2 = CheeseMod.getLocation("hut/pine_slab_roof");
    public static final ResourceLocation VARIANT_3 = CheeseMod.getLocation("hut/pine_trap_roof");
    public static final BlockPos CENTER = new BlockPos(1, 0, 3);

    public PineHutStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
        super(function);
    }

    @Override
    public boolean hasStartAt(ChunkGenerator<?> generator, Random rand, int chunkX, int chunkZ) {
        return rand.nextInt(550) == 0 && canSpawn(generator.getBiomeProvider().getBiome(chunkX * 16, chunkZ * 16));
    }

    public static boolean canSpawn(Biome biome) {
        return biome.getCategory() != Biome.Category.OCEAN && biome.getCategory() != Biome.Category.RIVER && biome.getCategory() != Biome.Category.FOREST;
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
            BlockPos pos = new BlockPos(i, 0, j);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            this.components.add(new PineHutStructurePiece(template, getVariant(), pos, rotation));
            this.recalculateStructureSize();
        }

        public ResourceLocation getVariant() {
            int v = this.rand.nextInt(4);
            if (v == 0)
                return PineHutStructure.VARIANT_3;
            else if (v == 1)
                return PineHutStructure.VARIANT_2;
            else
                return PineHutStructure.VARIANT_1;
        }
    }
}
