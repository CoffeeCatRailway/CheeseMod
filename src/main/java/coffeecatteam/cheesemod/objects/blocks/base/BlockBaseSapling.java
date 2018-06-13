package coffeecatteam.cheesemod.objects.blocks.base;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class BlockBaseSapling extends BlockBush implements IGrowable {

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D,
			0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    private WorldGenerator tree;

	public BlockBaseSapling(String name, float hardness, float resistance,  WorldGenerator tree) {
	    this.tree = tree;

		setUnlocalizedName(name);
		setRegistryName(name);

		setHardness(hardness);
		setResistance(resistance);

		setSoundType(SoundType.PLANT);
		setCreativeTab(CheeseMod.CHEESETAB);
		this.setDefaultState(this.blockState.getBaseState() .withProperty(STAGE, Integer.valueOf(0)));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SAPLING_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((Integer) state.getValue(STAGE)).intValue() << 3;
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE });
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return (double) worldIn.rand.nextFloat() < 0.45D;
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT
				|| state.getBlock() == Blocks.FARMLAND;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		if (((Integer) state.getValue(STAGE)).intValue() == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		} else {
			this.generateTree(worldIn, pos, state, rand);
		}
	}

	private void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!TerrainGen.saplingGrowTree(world, rand, pos))
			return;
		int i = 0, j = 0;
		boolean flag = false;

		IBlockState blockState = Blocks.AIR.getDefaultState();
		if (flag) {
			world.setBlockState(pos.add(i, 0, j), blockState, 4);
			world.setBlockState(pos.add(i + 1, 0, j), blockState, 4);
			world.setBlockState(pos.add(i, 0, j + 1), blockState, 4);
			world.setBlockState(pos.add(i + 1, 0, j + 1), blockState, 4);
		} else {
			world.setBlockState(pos, blockState, 4);
		}

		if (!tree.generate(world, rand, pos)) {
			if (flag) {
				world.setBlockState(pos.add(i, 0, j), blockState, 4);
				world.setBlockState(pos.add(i + 1, 0, j), blockState, 4);
				world.setBlockState(pos.add(i, 0, j + 1), blockState, 4);
				world.setBlockState(pos.add(i + 1, 0, j + 1), blockState, 4);
			} else {
				world.setBlockState(pos, blockState, 4);
			}
		}
	}
}
