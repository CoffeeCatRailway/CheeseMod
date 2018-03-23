package coffeecatteam.cheesemod.world.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.world.biomes.BiomeCheese;
import coffeecatteam.cheesemod.world.gen.feature.WorldGenStructure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.IWorldGenerator;
import scala.actors.threadpool.Arrays;

public class GenStructure implements IWorldGenerator {

	public static final WorldGenerator CHEESE_HUT_RUIN = new WorldGenStructure("cheese_hut_ruin");
	public static final WorldGenerator HAM_HUT_RUIN = new WorldGenStructure("ham_hut_ruin");

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case 1: // End
			break;
		case 0: // Overworld
			generateStructure(CHEESE_HUT_RUIN, world, random, chunkX, chunkZ, 200, Blocks.GRASS, BiomePlains.class, BiomeForest.class, BiomeHills.class);
			generateStructure(HAM_HUT_RUIN, world, random, chunkX, chunkZ, 200, Blocks.GRASS, BiomePlains.class, BiomeForest.class, BiomeHills.class);
			break;
		case -1: // Nether
			break;
		}
	}

	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ,
			int chance, Block topBlock, Class<?>... classes) {
		//System.out.println(((WorldGenStructure) generator).structureName);
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));

		int x = (chunkX * 16) + random.nextInt(15) + 8;
		int z = (chunkZ * 16) + random.nextInt(15) + 8;
		int y = calculateGenerationHeight(world, x, z, topBlock);
		BlockPos pos = new BlockPos(x, y, z);

		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

		if (world.getWorldType() != WorldType.FLAT) {
			if (classesList.contains(biome)) {
				if (random.nextInt(chance) == 0) {
					generator.generate(world, random, pos);
				}
			}
		}
	}

	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
		int y = world.getHeight();
		boolean foundGround = false;

		while (!foundGround && y-- >= 0) {
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			foundGround = block == topBlock;
		}

		return y;
	}
}
