package coffeecatteam.cheesemod.objects.blocks.tree;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.util.handlers.EnumHandler;
import coffeecatteam.cheesemod.util.interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCheesePlanks extends Block implements IMetaName {

	public static final PropertyEnum<EnumHandler.EnumWoodType> VARIANT = PropertyEnum.<EnumHandler.EnumWoodType>create(
			"variant", EnumHandler.EnumWoodType.class);

	public BlockCheesePlanks(String name, float hardness, float resistance, int harvestLevel) {
		super(Material.WOOD);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel("axe", harvestLevel);
		setSoundType(SoundType.WOOD);
		setCreativeTab(CheeseMod.CHEESETAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumWoodType.CHEESE));
	}
	
	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumHandler.EnumWoodType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumHandler.EnumWoodType planks$enumtype : EnumHandler.EnumWoodType.values()) {
			items.add(new ItemStack(this, 1, planks$enumtype.getMeta()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumWoodType.byMetaData(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumHandler.EnumWoodType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.EnumWoodType.values()[stack.getItemDamage()].getName();
	}
}
