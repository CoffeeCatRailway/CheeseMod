package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.ModCheeseConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
public class FoodBlock extends Block {

    private final boolean definiteStick;

    public FoodBlock(boolean definiteStick) {
        super(Block.Properties.create(Material.WOOL).sound(SoundType.SLIME).hardnessAndResistance(1.0f).harvestLevel(1).harvestTool(ToolType.AXE));
        this.definiteStick = definiteStick;
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return ModCheeseConfig.stickyFoodBlock.get() && this.definiteStick;
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.isCrouching()) {
            super.onFallenUpon(world, pos, entity, fallDistance);
        } else {
            entity.onLivingFall(fallDistance, 0.0f);
        }
    }

    @Override
    public void onLanded(IBlockReader reader, Entity entity) {
        if (entity.isCrouching()) {
            super.onLanded(reader, entity);
        } else {
            Vec3d vec3d = entity.getMotion();
            if (vec3d.y < 0.0D) {
                double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;
                entity.setMotion(vec3d.x, -vec3d.y * d0, vec3d.z);
            }
        }
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        double d0 = Math.abs(entity.getMotion().y);
        if (d0 < 0.1D && !entity.isCrouching()) {
            double d1 = 0.4D + d0 * 0.2D;
            entity.setMotion(entity.getMotion().mul(d1, 1.0D, d1));
        }

        super.onEntityWalk(world, pos, entity);
    }
}
