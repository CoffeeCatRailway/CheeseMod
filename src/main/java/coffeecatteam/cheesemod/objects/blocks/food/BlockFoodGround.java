package coffeecatteam.cheesemod.objects.blocks.food;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.util.interfaces.IMetaName;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockFoodGround extends BlockGrass implements IMetaName {

	public static final PropertyEnum<EnumHandler.EnumGroundType> VARIANT = PropertyEnum.<EnumHandler.EnumGroundType>create(
			"variant", EnumHandler.EnumGroundType.class, new Predicate<EnumHandler.EnumGroundType>() {
				public boolean apply(@Nullable EnumHandler.EnumGroundType apply) {
					return apply.getMeta() < 2;
				}
			});

	public BlockFoodGround(String name, float hardness, float resistance, int harvestLevel) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel("axe", harvestLevel);
		setCreativeTab(CheeseMod.CHEESETAB);
		setSoundType(SoundType.GROUND);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumGroundType.CHEESE));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (worldIn.getLightFromNeighbors(pos.up()) < 4
					&& worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2) {
				worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
			} else {
				if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

						if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos)) {
							return;
						}

						IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
						IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

						if (iblockstate1.getBlock() == Blocks.DIRT
								&& iblockstate1.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT
								&& worldIn.getLightFromNeighbors(blockpos.up()) >= 4
								&& iblockstate.getLightOpacity(worldIn, pos.up()) <= 2) {
							worldIn.setBlockState(blockpos, InitBlock.FOOD_GROUND_BLOCK.getDefaultState().withProperty(
									VARIANT, EnumHandler.EnumGroundType.byMetaData(getMetaFromState(state))));
						}
					}
				}
			}
		}
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			IPlantable plantable) {
		return true;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumHandler.EnumGroundType log$enumtype : EnumHandler.EnumGroundType.values()) {
			items.add(new ItemStack(this, 1, log$enumtype.getMeta()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumGroundType.byMetaData(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumHandler.EnumGroundType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT, SNOWY });
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1,
				((EnumHandler.EnumGroundType) state.getValue(VARIANT)).getMeta());
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumHandler.EnumGroundType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.EnumGroundType.values()[stack.getItemDamage()].getName();
	}
}
