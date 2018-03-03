package coffeecatteam.cheesemod.objects.blocks.base;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBase extends Block {

	public BlockBase(String name, float hardness, float resistance, Material material, int harvestLevel, CreativeTabs tab) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel("pickaxe", harvestLevel);
		setCreativeTab(tab);
	}
}
