package coffeecatteam.cheesemod.objects.blocks.slab;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.objects.blocks.base.BlockBaseSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockHalfSlab extends BlockBaseSlab {

	public BlockHalfSlab(String name, float hardness, float resistance, Material material, int harvestLevel) {
		super(name, hardness, resistance, material);
		setHarvestLevel("axe", harvestLevel);
		setCreativeTab(CheeseMod.CHEESETAB);
		this.setSoundType(SoundType.SNOW);
	}

	@Override
	public boolean isDouble() {
		return false;
	}
}
