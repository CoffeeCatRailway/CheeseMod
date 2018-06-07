package coffeecatteam.cheesemod.world.biomes;

import java.util.Random;

import coffeecatteam.cheesemod.config.Config;
import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.objects.blocks.food.BlockFoodGround;
import coffeecatteam.cheesemod.objects.entity.golem.EntityHamGolem;
import coffeecatteam.cheesemod.objects.entity.man.EntityHamMan;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeHamCooked;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeHamRaw;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeHam extends Biome {

	protected static final WorldGenAbstractTree TREE_HAM_RAW = new WorldGenTreeHamRaw();
	protected static final WorldGenAbstractTree TREE_HAM_COOKED = new WorldGenTreeHamCooked();

	public BiomeHam() {
		super(new BiomeProperties("Ham").setBaseHeight(0.5f).setHeightVariation(0.2f).setTemperature(0.6f)
				.setWaterColor(0xD46A52).setSnowEnabled());
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);

		topBlock = InitBlock.FOOD_GROUND_BLOCK.getDefaultState().withProperty(BlockFoodGround.VARIANT,
				EnumHandler.EnumGroundType.HAM);

		this.decorator.treesPerChunk = 1;
		this.decorator.extraTreeChance = 0.2f;

		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();

		this.decorator.coalGen = new WorldGenMinable(InitBlock.HAM_RAW_METAL_ORE.getDefaultState(), 4);
		this.decorator.goldGen = new WorldGenMinable(InitBlock.HAM_COOKED_METAL_ORE.getDefaultState(), 4);

		Config.load("cheesemod");
		if (Config.getEntityHamManSpawn())
			this.spawnableMonsterList
					.add(new SpawnListEntry(EntityHamMan.class, Config.getEntityHamManSpawnWeight(), 0, 2));
		if (Config.getEntityHamGolemSpawn())
			this.spawnableCreatureList
					.add(new SpawnListEntry(EntityHamGolem.class, Config.getEntityHamGolemSpawnWeight(), 0, 2));
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return (WorldGenAbstractTree) (rand.nextInt(10) <= 1 ? TREE_HAM_COOKED : TREE_HAM_RAW);
	}
}
