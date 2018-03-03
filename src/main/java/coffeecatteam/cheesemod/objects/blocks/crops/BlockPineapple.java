package coffeecatteam.cheesemod.objects.blocks.crops;

import java.util.Random;

import coffeecatteam.cheesemod.init.InitItem;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPineapple extends BlockCrops {

	public BlockPineapple(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	@Override
	protected Item getSeed() {
		return InitItem.PINEAPPLE_PLANT;
	}

	@Override
	protected Item getCrop() {
		return InitItem.PINEAPPLE;
	}
	
	@Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		int seedAmount = 1 + RANDOM.nextInt((3 - 1) + 1);
        drops.add(new ItemStack(InitItem.PINEAPPLE_PLANT, seedAmount, 0));
        drops.add(new ItemStack(InitItem.PINEAPPLE, 1, 0));
    }
}
