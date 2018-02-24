package coffeecatteam.cheesemod.objects.blocks.base;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

public class BlockBaseStairs extends BlockStairs {

	public BlockBaseStairs(String name, IBlockState modelState) {
		super(modelState);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CheeseMod.CHEESETAB);
		this.setSoundType(SoundType.SNOW);
		this.useNeighborBrightness = true;
	}
}
