package coffeecatteam.cheesemod.objects.blocks.tree;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.util.interfaces.IMetaName;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCheeseLeaves extends BlockLeaves implements IMetaName {

	public static final PropertyEnum<EnumHandler.EnumWoodType> VARIANT = PropertyEnum.<EnumHandler.EnumWoodType>create(
			"variant", EnumHandler.EnumWoodType.class, new Predicate<EnumHandler.EnumWoodType>() {
				public boolean apply(@Nullable EnumHandler.EnumWoodType apply) {
					return apply.getMeta() < 4;
				}
			});

	public BlockCheeseLeaves(String name, float hardness, float resistance) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setResistance(resistance);
		setSoundType(SoundType.PLANT);
		setCreativeTab(CheeseMod.CHEESETAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumWoodType.CHEESE)
				.withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumWoodType.byMetaData(meta % 4));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = ((EnumHandler.EnumWoodType) state.getValue(VARIANT)).getMeta();

		if (!((Boolean) state.getValue(DECAYABLE)).booleanValue())
			i |= 2;
		if (!((Boolean) state.getValue(CHECK_DECAY)).booleanValue())
			i |= 4;
		return i;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumHandler.EnumWoodType log$enumtype : EnumHandler.EnumWoodType.values()) {
			items.add(new ItemStack(this, 1, log$enumtype.getMeta()));
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
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
		return;
	}

	@Override
	protected int getSaplingDropChance(IBlockState state) {
		return 25;
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).getMeta()));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT, DECAYABLE, CHECK_DECAY });
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(InitBlock.CHEESE_SAPLING);
    }
}
