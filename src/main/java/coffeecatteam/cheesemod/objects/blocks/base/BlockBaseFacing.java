package coffeecatteam.cheesemod.objects.blocks.base;

import coffeecatteam.cheesemod.CheeseMod;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBaseFacing extends BlockLog {

    private boolean isBouncy;
    private boolean isWood;

	public BlockBaseFacing(String name, float hardness, float resistance, int harvestLevel, boolean isBouncy, boolean isWood) {
        this.isBouncy = isBouncy;
        this.isWood = isWood;

		setUnlocalizedName(name);
		setRegistryName(name);

		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel("axe", harvestLevel);

		setCreativeTab(CheeseMod.CHEESETAB);
		setSoundType(isWood ? SoundType.WOOD : SoundType.SNOW);

		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
	}

	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos) {
		return isWood;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.values()[meta >> 2]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(LOG_AXIS).ordinal() * 4;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS });
	}

	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
	    if (isBouncy) {
            if (entity.isSneaking()) {
                super.onFallenUpon(world, pos, entity, fallDistance);
            } else {
                entity.fall(fallDistance, 0.0F);
            }
        }
	}

	@Override
	public void onLanded(World world, Entity entity) {
	    if (isBouncy) {
            if (entity.isSneaking()) {
                super.onLanded(world, entity);
            } else if (entity.motionY < 0.0D) {
                entity.motionY = -entity.motionY;

                if (!(entity instanceof EntityLivingBase)) {
                    entity.motionY *= 0.5D;
                }
            }
        }
	}
}
