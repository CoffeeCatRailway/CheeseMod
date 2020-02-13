package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.common.ModTags;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 24/12/2019
 */
public class ModFlowingFluidBlock extends FlowingFluidBlock {

    public ModFlowingFluidBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid, properties.doesNotBlockMovement().hardnessAndResistance(100.0f).noDrops());
    }

    @Override
    public boolean reactWithNeighbors(World world, BlockPos pos, BlockState state) {
        if (this.getFluid().isIn(ModTags.Fluids.MELTED_CHEESE)) {
            for (Direction side : Direction.values()) {
                if (side != Direction.DOWN) {
                    IFluidState offset = world.getFluidState(pos.offset(side));

                    if (offset.getFluid().isIn(FluidTags.WATER)) {
                        if (world.getFluidState(pos).isSource()) {
                            world.setBlockState(pos, ModBlocks.CHEESE_BLOCK.get().getDefaultState());
                            this.triggerMixEffects(world, pos);
                            return false;
                        }
                    }
                }
            }
        } else if (this.getFluid().isIn(ModTags.Fluids.MELTED_GRILLED_CHEESE)) {
            for (Direction side : Direction.values()) {
                if (side != Direction.DOWN) {
                    IFluidState offset = world.getFluidState(pos.offset(side));

                    if (offset.getFluid().isIn(FluidTags.WATER)) {
                        if (world.getFluidState(pos).isSource()) {
                            world.setBlockState(pos, ModBlocks.GRILLED_CHEESE_BLOCK.get().getDefaultState());
                            this.triggerMixEffects(world, pos);
                            return false;
                        }
                    }
                }
            }
        } else if (this.getFluid().isIn(ModTags.Fluids.MELTED_HAM_RAW)) {
            for (Direction side : Direction.values()) {
                if (side != Direction.DOWN) {
                    IFluidState offset = world.getFluidState(pos.offset(side));

                    if (offset.getFluid().isIn(FluidTags.WATER)) {
                        if (world.getFluidState(pos).isSource()) {
                            world.setBlockState(pos, ModBlocks.HAM_RAW_BLOCK.get().getDefaultState());
                            this.triggerMixEffects(world, pos);
                            return false;
                        }
                    }
                }
            }
        } else if (this.getFluid().isIn(ModTags.Fluids.MELTED_HAM_COOKED)) {
            for (Direction side : Direction.values()) {
                if (side != Direction.DOWN) {
                    IFluidState offset = world.getFluidState(pos.offset(side));

                    if (offset.getFluid().isIn(FluidTags.WATER)) {
                        if (world.getFluidState(pos).isSource()) {
                            world.setBlockState(pos, ModBlocks.HAM_COOKED_BLOCK.get().getDefaultState());
                            this.triggerMixEffects(world, pos);
                            return false;
                        }
                    }
                }
            }
        } else if (this.getFluid().isIn(ModTags.Fluids.MILK)) {
            for (Direction side : Direction.values()) {
                if (side != Direction.DOWN) {
                    IFluidState offset = world.getFluidState(pos.offset(side));

                    if (offset.getFluid().isIn(ModTags.Fluids.VINEGAR)) {
                        if (world.getFluidState(pos).isSource()) {
                            world.setBlockState(pos, ModBlocks.CHEESE_BLOCK.get().getDefaultState());
                            this.triggerMixEffects(world, pos);
                            return false;
                        }
                    }
                }
            }
        }

        return super.reactWithNeighbors(world, pos, state);
    }

    private void triggerMixEffects(IWorld worldIn, BlockPos pos) {
        worldIn.playEvent(Constants.WorldEvents.LAVA_EXTINGUISH, pos, 0);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (this.getFluid().isIn(ModTags.Fluids.MELTED_CHEESE) || this.getFluid().isIn(ModTags.Fluids.MELTED_GRILLED_CHEESE)
                || this.getFluid().isIn(ModTags.Fluids.MELTED_HAM_RAW) || this.getFluid().isIn(ModTags.Fluids.MELTED_HAM_COOKED)) {
            if (!entityIn.isImmuneToFire())
                entityIn.attackEntityFrom(DamageSource.IN_FIRE, 5.0F);
            entityIn.setInLava();
        }
    }
}
