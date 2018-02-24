package coffeecatteam.cheesemod.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeCheese;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeHamRaw;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class GenTree implements IWorldGenerator {

	private final WorldGenerator CHEESE = new WorldGenTreeCheese();
	private final WorldGenerator HAM_RAW = new WorldGenTreeHamRaw();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case 1:
			break;
		case 0:
			runGenerator(CHEESE, world, random, chunkX, chunkZ, 1, -1, 0, BiomeForest.class);
			runGenerator(HAM_RAW, world, random, chunkX, chunkZ, 1, -1, 0, BiomeForest.class);
			break;
		case -1:
			break;
		}
	}

	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, double chance,
			int minHeight, int maxHeight, Class<?>... classes) {
		if (chance < 1) {
			if (rand.nextDouble() < chance)
				chance = 1;
			else
				chance = 0;
		}

		ArrayList<Class<?>> classList = new ArrayList<Class<?>>(Arrays.asList(classes));
		int heightDiff = maxHeight - minHeight + 1;

		for (int i = 0; i < chance; i++) {
			BlockPos pos = new BlockPos(chunkX * 16 + 10 + rand.nextInt(15), minHeight + rand.nextInt(heightDiff),
					chunkZ * 16 + 10 + rand.nextInt(15));
			if (minHeight < 0)
				pos = world.getHeight(pos);
			Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
			if (classList.contains(biome) || classes.length == 0)
				if (rand.nextInt(4) < 2)
					generator.generate(world, rand, pos);
		}
	}
}
