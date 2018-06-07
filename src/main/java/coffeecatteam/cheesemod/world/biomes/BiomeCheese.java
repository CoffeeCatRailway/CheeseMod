package coffeecatteam.cheesemod.world.biomes;

import java.util.Random;

import coffeecatteam.cheesemod.config.Config;
import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.objects.blocks.food.BlockFoodGround;
import coffeecatteam.cheesemod.objects.entity.golem.EntityCheeseGolem;
import coffeecatteam.cheesemod.objects.entity.man.EntityCheeseMan;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeCheese;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeGrilledCheese;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeCheese extends Biome {

	protected static final WorldGenAbstractTree TREE_CHEESE = new WorldGenTreeCheese();
	protected static final WorldGenAbstractTree TREE_GRILLED_CHEESE = new WorldGenTreeGrilledCheese();

	public BiomeCheese() {
		super(new BiomeProperties("Cheese").setBaseHeight(0.5f).setHeightVariation(0.2f).setTemperature(0.6f)
				.setWaterColor(0xCCBF6C).setSnowEnabled());
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);

		topBlock = InitBlock.FOOD_GROUND_BLOCK.getDefaultState().withProperty(BlockFoodGround.VARIANT,
				EnumHandler.EnumGroundType.CHEESE);

		this.decorator.treesPerChunk = 1;
		this.decorator.extraTreeChance = 0.2f;

		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();

		this.decorator.coalGen = new WorldGenMinable(InitBlock.CHEESE_METAL_ORE.getDefaultState(), 4);
		this.decorator.goldGen = new WorldGenMinable(InitBlock.GRILLED_CHEESE_METAL_ORE.getDefaultState(), 4);

		Config.load("cheesemod");
		if (Config.getEntityCheeseManSpawn())
			this.spawnableMonsterList
					.add(new SpawnListEntry(EntityCheeseMan.class, Config.getEntityCheeseManSpawnWeight(), 0, 2));
		if (Config.getEntityCheeseGolemSpawn())
			this.spawnableCreatureList.add(
					new SpawnListEntry(EntityCheeseGolem.class, Config.getEntityCheeseGolemSpawnWeight(), 0, 2));
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return (WorldGenAbstractTree) (rand.nextInt(10) <= 1 ? TREE_GRILLED_CHEESE : TREE_CHEESE);
	}
}
