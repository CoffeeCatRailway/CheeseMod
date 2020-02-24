package coffeecatrailway.coffeecheese.common.block;

import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 19/02/2020
 */
public class HamPressurePlateBlock extends PressurePlateBlock {

    public HamPressurePlateBlock(Properties properties) {
        super(Sensitivity.MOBS, properties);
    }

    @Override
    protected void playClickOnSound(IWorld world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.ENTITY_PIG_AMBIENT, SoundCategory.BLOCKS, 1f, (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2F + 1.0F);
    }

    @Override
    protected void playClickOffSound(IWorld world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.ENTITY_PIG_AMBIENT, SoundCategory.BLOCKS, 1f, (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.25F + 1.0F);
    }

    @Override
    protected int computeRedstoneStrength(World world, BlockPos pos) {
        AxisAlignedBB aabb = PRESSURE_AABB.offset(pos);
        List<? extends Entity> list = world.getEntitiesWithinAABB(PigEntity.class, aabb);

        if (list.isEmpty())
            return 0;

        for (Entity entity : list)
            if (!entity.doesEntityNotTriggerPressurePlate())
                return (entity instanceof PigEntity) ? (((PigEntity) entity).isChild() ? 7 : 15) : 0;

        return 0;
    }
}
