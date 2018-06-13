package coffeecatteam.cheesemod.objects.blocks.base;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockBaseLeaves extends BlockLeaves {

    public BlockBaseLeaves(String name) {
        setUnlocalizedName("leaves_" + name);
        setRegistryName("leaves_" + name);
        setCreativeTab(CheeseMod.CHEESETAB);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return NonNullList.withSize(1, new ItemStack(this));
    }
}
