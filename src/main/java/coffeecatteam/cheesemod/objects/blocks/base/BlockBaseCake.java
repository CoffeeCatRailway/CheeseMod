package coffeecatteam.cheesemod.objects.blocks.base;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBaseCake extends BlockCake {

	public BlockBaseCake(String name, float hardness) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setSoundType(SoundType.CLOTH);
		setCreativeTab(CheeseMod.CHEESEFOODSTAB);
		disableStats();
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}
}
