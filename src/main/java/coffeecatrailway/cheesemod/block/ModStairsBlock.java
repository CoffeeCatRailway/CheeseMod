package coffeecatrailway.cheesemod.block;

import coffeecatrailway.cheesemod.ModConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 29/07/2019
 */
public class ModStairsBlock extends StairsBlock {

    private boolean isBoundy;

    public ModStairsBlock(BlockState state, Properties properties, boolean isBoundy) {
        super(state, properties);
        this.isBoundy = isBoundy;
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return ModConfig.stickyFoodBlock.get() && isBoundy;
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
        if (isBoundy) {
            if (entity.isSneaking()) {
                super.onFallenUpon(world, pos, entity, fallDistance);
            } else {
                entity.fall(fallDistance, 0.0F);
            }
        } else {
            super.onFallenUpon(world, pos, entity, fallDistance);
        }
    }

    @Override
    public void onLanded(IBlockReader reader, Entity entity) {
        if (isBoundy) {
            if (entity.isSneaking()) {
                super.onLanded(reader, entity);
            } else {
                Vec3d vec3d = entity.getMotion();
                if (vec3d.y < 0.0D) {
                    double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;
                    entity.setMotion(vec3d.x, -vec3d.y * d0, vec3d.z);
                }
            }
        } else {
            super.onLanded(reader, entity);
        }
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        if (isBoundy) {
            double d0 = Math.abs(entity.getMotion().y);
            if (d0 < 0.1D && !entity.isSneaking()) {
                double d1 = 0.4D + d0 * 0.2D;
                entity.setMotion(entity.getMotion().mul(d1, 1.0D, d1));
            }
        }

        super.onEntityWalk(world, pos, entity);
    }
}
