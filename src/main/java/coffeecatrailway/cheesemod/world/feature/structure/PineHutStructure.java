package coffeecatrailway.cheesemod.world.feature.structure;

import coffeecatrailway.cheesemod.core.ModFeatures;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;

import java.util.Random;
import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 30/08/2019
 */
public class PineHutStructure extends ScatteredStructure<NoFeatureConfig> {

    private static final ResourceLocation STRUCTURE = new ResourceLocation("pine_hut");
    private static final BlockPos CENTER = new BlockPos(4, 1, 4);

    public PineHutStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> dynamicFunction) {
        super(dynamicFunction);
    }

    @Override
    public String getStructureName() {
        return "Pine_Hut";
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public IStartFactory getStartFactory() {
        return PineHutStructure.Start::new;
    }

    @Override
    protected int getSeedModifier() {
        return 62371617;
    }

    public static class Start extends StructureStart {

        public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, biome, bounds, reference, seed);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager template, int chunkX, int chunkZ, Biome biome) {
            BlockPos blockpos = new BlockPos(chunkX * 16, generator.getGroundHeight(), chunkZ * 16);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            this.components.add(new Piece(template, STRUCTURE, blockpos, rotation));
            this.recalculateStructureSize();
        }
    }

    public static class Piece extends TemplateStructurePiece {

        private final ResourceLocation id;
        private final Rotation rotation;

        public Piece(TemplateManager template, ResourceLocation id, BlockPos pos, Rotation rotation) {
            super(ModFeatures.PINE_HUT_TYPE, 0);
            this.id = id;
            this.templatePosition = pos.add(CENTER.getX(), CENTER.getY(), CENTER.getZ());
            this.rotation = rotation;
            this.setup(template);
        }

        public Piece(TemplateManager template, CompoundNBT nbt) {
            super(ModFeatures.PINE_HUT_TYPE, nbt);
            this.id = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.valueOf(nbt.getString("Rot"));
            this.setup(template);
        }

        private void setup(TemplateManager template) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(CENTER).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template.getTemplateDefaulted(this.id), this.templatePosition, placementsettings);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
            if ("chest".equals(function)) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                TileEntity tileentity = world.getTileEntity(pos.down());
                if (tileentity instanceof ChestTileEntity)
                    ((ChestTileEntity) tileentity).setLootTable(LootTables.CHESTS_IGLOO_CHEST, rand.nextLong()); // TODO: Add loot tables
            }
        }
    }
}
