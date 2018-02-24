package coffeecatteam.cheesemod.world.gen;

import java.util.Random;

import coffeecatteam.cheesemod.init.InitBlock;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class GenOre implements IWorldGenerator {

	// Overworld
	private WorldGenerator cheese_metal_ore, grilled_cheese_metal_ore;
	private WorldGenerator ham_raw_metal_ore, ham_cooked_metal_ore;

	// Nether
	private WorldGenerator nether_cheese_metal_ore, nether_grilled_cheese_metal_ore;
	private WorldGenerator nether_ham_raw_metal_ore, nether_ham_cooked_metal_ore;

	// End
	private WorldGenerator end_cheese_metal_ore, end_grilled_cheese_metal_ore;
	private WorldGenerator end_ham_raw_metal_ore, end_ham_cooked_metal_ore;

	public GenOre() {
		// Overworld
		cheese_metal_ore = new WorldGenMinable(InitBlock.CHEESE_METAL_ORE.getDefaultState(), 4);
		grilled_cheese_metal_ore = new WorldGenMinable(InitBlock.GRILLED_CHEESE_METAL_ORE.getDefaultState(), 4);

		ham_raw_metal_ore = new WorldGenMinable(InitBlock.HAM_RAW_METAL_ORE.getDefaultState(), 4);
		ham_cooked_metal_ore = new WorldGenMinable(InitBlock.HAM_COOKED_METAL_ORE.getDefaultState(), 4);

		// Nether
		nether_cheese_metal_ore = new WorldGenMinable(InitBlock.NETHER_CHEESE_METAL_ORE.getDefaultState(), 5,
				BlockMatcher.forBlock(Blocks.NETHERRACK));
		nether_grilled_cheese_metal_ore = new WorldGenMinable(InitBlock.NETHER_GRILLED_CHEESE_METAL_ORE.getDefaultState(), 5,
				BlockMatcher.forBlock(Blocks.NETHERRACK));

		nether_ham_raw_metal_ore = new WorldGenMinable(InitBlock.NETHER_HAM_RAW_METAL_ORE.getDefaultState(), 5,
				BlockMatcher.forBlock(Blocks.NETHERRACK));
		nether_ham_cooked_metal_ore = new WorldGenMinable(InitBlock.NETHER_HAM_COOKED_METAL_ORE.getDefaultState(), 5,
				BlockMatcher.forBlock(Blocks.NETHERRACK));

		// End
		end_cheese_metal_ore = new WorldGenMinable(InitBlock.END_CHEESE_METAL_ORE.getDefaultState(), 5,
				BlockMatcher.forBlock(Blocks.END_STONE));
		end_grilled_cheese_metal_ore = new WorldGenMinable(InitBlock.END_GRILLED_CHEESE_METAL_ORE.getDefaultState(), 5,
				BlockMatcher.forBlock(Blocks.END_STONE));

		end_ham_raw_metal_ore = new WorldGenMinable(InitBlock.END_HAM_RAW_METAL_ORE.getDefaultState(), 5,
				BlockMatcher.forBlock(Blocks.END_STONE));
		end_ham_cooked_metal_ore = new WorldGenMinable(InitBlock.END_HAM_COOKED_METAL_ORE.getDefaultState(), 5,
				BlockMatcher.forBlock(Blocks.END_STONE));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case 1: // End
			renGenerator(end_cheese_metal_ore, world, random, chunkX, chunkZ, 85, 0, 100);
			renGenerator(end_grilled_cheese_metal_ore, world, random, chunkX, chunkZ, 85, 0, 100);

			renGenerator(end_ham_raw_metal_ore, world, random, chunkX, chunkZ, 75, 0, 100);
			renGenerator(end_ham_cooked_metal_ore, world, random, chunkX, chunkZ, 75, 0, 100);
			break;
		case 0: // Overworld
			renGenerator(cheese_metal_ore, world, random, chunkX, chunkZ, 80, 0, 50);
			renGenerator(grilled_cheese_metal_ore, world, random, chunkX, chunkZ, 40, 0, 12);

			renGenerator(ham_raw_metal_ore, world, random, chunkX, chunkZ, 75, 0, 50);
			renGenerator(ham_cooked_metal_ore, world, random, chunkX, chunkZ, 75, 0, 50);
			break;
		case -1: // Nether
			renGenerator(nether_cheese_metal_ore, world, random, chunkX, chunkZ, 85, 0, 100);
			renGenerator(nether_grilled_cheese_metal_ore, world, random, chunkX, chunkZ, 85, 0, 100);

			renGenerator(ham_raw_metal_ore, world, random, chunkX, chunkZ, 75, 0, 100);
			renGenerator(ham_cooked_metal_ore, world, random, chunkX, chunkZ, 75, 0, 100);
			break;
		}
	}

	private void renGenerator(WorldGenerator gen, World world, Random random, int chunkX, int chunkZ, int chance,
			int minHeight, int maxHeight) {
		if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256)
			throw new IllegalArgumentException("Cheese went to space!");
		int heightDiff = maxHeight - minHeight + 1;

		for (int i = 0; i < chance; i++) {
			int x = chunkX * 16 + random.nextInt(16);
			int y = minHeight + random.nextInt(heightDiff);
			int z = chunkZ * 16 + random.nextInt(16);

			gen.generate(world, random, new BlockPos(x, y, z));
		}
	}
}
