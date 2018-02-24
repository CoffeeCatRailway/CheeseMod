package coffeecatteam.cheesemod.world.gen;

import java.util.Random;

import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.objects.blocks.food.BlockFood;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class GenBlock implements IWorldGenerator {

	private WorldGenerator cheese_block, grilled_cheese_block;
	private WorldGenerator ham_raw_block, ham_cooked_block;

	public GenBlock() {
		cheese_block = new WorldGenMinable(
				InitBlock.FOOD_BLOCK.getDefaultState().withProperty(BlockFood.VARIANT, EnumHandler.EnumWoodType.CHEESE),
				4, BlockMatcher.forBlock(InitBlock.CHEESE_LEAVES.getDefaultState()
						.withProperty(BlockFood.VARIANT, EnumHandler.EnumWoodType.CHEESE).getBlock()));

		grilled_cheese_block = new WorldGenMinable(
				InitBlock.FOOD_BLOCK.getDefaultState().withProperty(BlockFood.VARIANT,
						EnumHandler.EnumWoodType.GRILLED_CHEESE),
				4, BlockMatcher.forBlock(InitBlock.CHEESE_LEAVES.getDefaultState()
						.withProperty(BlockFood.VARIANT, EnumHandler.EnumWoodType.GRILLED_CHEESE).getBlock()));

		ham_raw_block = new WorldGenMinable(
				InitBlock.FOOD_BLOCK.getDefaultState().withProperty(BlockFood.VARIANT,
						EnumHandler.EnumWoodType.HAM_RAW),
				4, BlockMatcher.forBlock(InitBlock.CHEESE_LEAVES.getDefaultState()
						.withProperty(BlockFood.VARIANT, EnumHandler.EnumWoodType.HAM_RAW).getBlock()));

		ham_cooked_block = new WorldGenMinable(
				InitBlock.FOOD_BLOCK.getDefaultState().withProperty(BlockFood.VARIANT,
						EnumHandler.EnumWoodType.HAM_COOKED),
				4, BlockMatcher.forBlock(InitBlock.CHEESE_LEAVES.getDefaultState()
						.withProperty(BlockFood.VARIANT, EnumHandler.EnumWoodType.HAM_COOKED).getBlock()));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case 0: // Overworld
			renGenerator(cheese_block, world, random, chunkX, chunkZ, 10, 0, 255);
			renGenerator(grilled_cheese_block, world, random, chunkX, chunkZ, 10, 0, 255);

			renGenerator(ham_raw_block, world, random, chunkX, chunkZ, 10, 0, 255);
			renGenerator(ham_cooked_block, world, random, chunkX, chunkZ, 10, 0, 255);
			break;
		case 1: // End

			break;
		case -1: // Nether

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
