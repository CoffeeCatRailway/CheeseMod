package coffeecatrailway.cheesemod.common.world.feature.structure;

import coffeecatrailway.cheesemod.core.ModFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 15/09/2019
 */
public class PineHutStructurePiece extends TemplateStructurePiece {

    private final ResourceLocation location;
    private final Rotation rotation;
    private BlockPos pos = BlockPos.ZERO;

    public PineHutStructurePiece(TemplateManager template, ResourceLocation location, BlockPos pos, Rotation rotation) {
        super(ModFeatures.PINE_HUT_TYPE, 0);
        this.location = location;
        this.rotation = rotation;
        this.pos = pos;
        this.templatePosition = this.pos;
        this.func_207614_a(template);
    }

    public PineHutStructurePiece(TemplateManager template, CompoundNBT compound) {
        super(ModFeatures.PINE_HUT_TYPE, compound);
        this.location = new ResourceLocation(compound.getString("Template"));
        this.rotation = Rotation.valueOf(compound.getString("Rot"));
        this.func_207614_a(template);
    }

    private void func_207614_a(TemplateManager templateIn) {
        Template template = templateIn.getTemplateDefaulted(this.location);
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(PineHutStructure.CENTER).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
        this.setup(template, this.templatePosition, placementsettings);
    }

    @Override
    protected void readAdditional(CompoundNBT tagCompound) {
        super.readAdditional(tagCompound);
        tagCompound.putString("Template", this.location.toString());
        tagCompound.putString("Rot", this.rotation.name());
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox bounds) {
        if ("chest".equals(function)) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
            TileEntity tileentity = worldIn.getTileEntity(pos.down());
            if (tileentity instanceof ChestTileEntity)
                ((ChestTileEntity) tileentity).setLootTable(rand.nextInt(1) == 0 ? LootTables.CHESTS_BURIED_TREASURE : LootTables.CHESTS_VILLAGE_VILLAGE_TOOLSMITH, rand.nextLong());
        }
    }

    @Override
    public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox bounds, ChunkPos chunkPos) {
        this.templatePosition = pos.add(PineHutStructure.CENTER.getX(), PineHutStructure.CENTER.getY() + world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, chunkPos.x * 16, chunkPos.z * 16), PineHutStructure.CENTER.getZ());
        return super.addComponentParts(world, random, bounds, chunkPos);
    }
}
