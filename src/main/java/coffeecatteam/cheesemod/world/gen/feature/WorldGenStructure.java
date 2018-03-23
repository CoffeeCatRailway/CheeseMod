package coffeecatteam.cheesemod.world.gen.feature;

import java.util.Random;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.util.LootTable;
import coffeecatteam.cheesemod.util.interfaces.IStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class WorldGenStructure extends WorldGenerator implements IStructure {
	
	public static String structureName;
	
	public WorldGenStructure(String structureName) {
		this.structureName = structureName;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		this.generateStructure(world, rand, pos);
		return true;
	}
	
	public static void generateStructure(World world, Random rand, BlockPos pos) {
		MinecraftServer mcServer = world.getMinecraftServer();
		TemplateManager manager = worldServer.getStructureTemplateManager();
		ResourceLocation location = new ResourceLocation(Reference.MODID, structureName);
		Template template = manager.get(mcServer, location);
		
		if (template != null) {
			IBlockState state = world.getBlockState(pos);
			
			Block block = state.getBlock();
			if (block instanceof BlockChest) {
				BlockChest blockChest = (BlockChest) block;
				TileEntityChest tileEntityChest = (TileEntityChest) blockChest.createNewTileEntity(world, blockChest.getMetaFromState(state));
				tileEntityChest.setLootTable(LootTable.CHEESE_RUIN, rand.nextLong());
				
				state = tileEntityChest.getBlockType().getDefaultState();
			}

			world.notifyBlockUpdate(pos, state, state, 3);
			settings.setRotation(getRotation());
			template.addBlocksToWorldChunk(world, pos, settings);
		}
	}
	
	private static Rotation getRotation() {
		int value = new Random().nextInt(3);
		return value == 0 ? Rotation.NONE : value == 1 ? Rotation.CLOCKWISE_90 : value == 2 ? Rotation.CLOCKWISE_180 : Rotation.COUNTERCLOCKWISE_90;
	}
}
