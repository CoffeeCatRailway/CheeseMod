package coffeecatteam.cheesemod.objects.blocks.tree;

import java.util.Random;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.util.interfaces.IMetaName;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeCheese;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeGrilledCheese;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeHamCooked;
import coffeecatteam.cheesemod.world.gen.feature.tree.WorldGenTreeHamRaw;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockCheeseSapling extends BlockBush implements IGrowable, IMetaName {

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D,
			0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

	public static final PropertyEnum<EnumHandler.EnumWoodType> VARIANT = PropertyEnum.<EnumHandler.EnumWoodType>create(
			"variant", EnumHandler.EnumWoodType.class, apply -> {
					return apply.getMeta() < 4;
			});

	public BlockCheeseSapling(String name, float hardness, float resistance) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setResistance(resistance);
		setSoundType(SoundType.PLANT);
		setCreativeTab(CheeseMod.CHEESETAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumWoodType.CHEESE)
				.withProperty(STAGE, Integer.valueOf(0)));
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
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumHandler.EnumWoodType sapling$enumtype : EnumHandler.EnumWoodType.values()) {
			items.add(new ItemStack(this, 1, sapling$enumtype.getMeta()));
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumHandler.EnumWoodType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.EnumWoodType.values()[stack.getItemDamage()].getName();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumWoodType.byMetaData(meta % 4))
				.withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumHandler.EnumWoodType) state.getValue(VARIANT)).getMeta();
		i = i | ((Integer) state.getValue(STAGE)).intValue() << 3;
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT, STAGE });
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
		WorldGenerator gen = (WorldGenerator) (rand.nextInt(10) == 0 ? new WorldGenBigTree(false)
				: new WorldGenTrees(false));
		int i = 0, j = 0;
		boolean flag = false;

		switch ((EnumHandler.EnumWoodType) state.getValue(VARIANT)) {
		case CHEESE:
			gen = new WorldGenTreeCheese();
			break;
		case GRILLED_CHEESE:
			gen = new WorldGenTreeGrilledCheese();
			break;
		case HAM_RAW:
			gen = new WorldGenTreeHamRaw();
			break;
		case HAM_COOKED:
			gen = new WorldGenTreeHamCooked();
			break;
		}

		IBlockState blockState = Blocks.AIR.getDefaultState();
		if (flag) {
			world.setBlockState(pos.add(i, 0, j), blockState, 4);
			world.setBlockState(pos.add(i + 1, 0, j), blockState, 4);
			world.setBlockState(pos.add(i, 0, j + 1), blockState, 4);
			world.setBlockState(pos.add(i + 1, 0, j + 1), blockState, 4);
		} else {
			world.setBlockState(pos, blockState, 4);
		}

		if (!gen.generate(world, rand, pos)) {
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
