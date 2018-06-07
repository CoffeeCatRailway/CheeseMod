package coffeecatteam.cheesemod.objects.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBase extends Block {

	public BlockBase(String name, float hardness, float resistance, Material material, boolean isWooden, int harvestLevel, CreativeTabs tab) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(isWooden ? "axe" : "pickaxe", harvestLevel);
		if (isWooden)
		    setSoundType(SoundType.WOOD);
		setCreativeTab(tab);
	}
}
