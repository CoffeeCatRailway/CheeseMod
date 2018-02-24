package coffeecatteam.cheesemod.objects.blocks.tree;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.util.interfaces.IMetaName;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCheeseLog extends BlockLog implements IMetaName {

	public static final PropertyEnum<EnumHandler.EnumWoodType> VARIANT = PropertyEnum.<EnumHandler.EnumWoodType>create(
			"variant", EnumHandler.EnumWoodType.class, new Predicate<EnumHandler.EnumWoodType>() {
				public boolean apply(@Nullable EnumHandler.EnumWoodType apply) {
					return apply.getMeta() < 4;
				}
			});

	public BlockCheeseLog(String name, float hardness, float resistance, int harvestLevel) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel("axe", harvestLevel);
		setSoundType(SoundType.WOOD);
		setCreativeTab(CheeseMod.CHEESETAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumWoodType.CHEESE)
				.withProperty(LOG_AXIS, EnumAxis.Y));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumHandler.EnumWoodType log$enumtype : EnumHandler.EnumWoodType.values()) {
			items.add(new ItemStack(this, 1, log$enumtype.getMeta()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState().withProperty(VARIANT,
				EnumHandler.EnumWoodType.byMetaData(meta % 4));

		switch (meta & 6) {
		case 0:
			state = state.withProperty(LOG_AXIS, EnumAxis.Y);
		case 2:
			state = state.withProperty(LOG_AXIS, EnumAxis.X);
		case 4:
			state = state.withProperty(LOG_AXIS, EnumAxis.Z);
		default:
			state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
		}
		return state;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumHandler.EnumWoodType) state.getValue(VARIANT)).getMeta();

		switch ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)) {
		case X:
			i |= 2;
			break;
		case Y:
			i |= 4;
			break;
		case Z:
			i |= 6;
			break;
		}
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT, LOG_AXIS });
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1,
				((EnumHandler.EnumWoodType) state.getValue(VARIANT)).getMeta());
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumHandler.EnumWoodType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.EnumWoodType.values()[stack.getItemDamage()].getName();
	}
}
