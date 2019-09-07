package coffeecatrailway.cheesemod.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 31/08/2019
 */
public abstract class LockableTileFluidHandler extends LockableTileEntity {

    protected final FluidTank tank;
    private final LazyOptional<IFluidHandler> holder;

    public LockableTileFluidHandler(TileEntityType<?> type, int fluidCapacity) {
        super(type);
        tank = new FluidTank(fluidCapacity);
        holder = LazyOptional.of(() -> tank);
    }

    public FluidTank getTank() {
        return tank;
    }

    @Override
    public void read(CompoundNBT tag) {
        super.read(tag);
        tank.readFromNBT(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag = super.write(tag);
        tank.writeToNBT(tag);
        return tag;
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return holder.cast();
        return super.getCapability(capability, facing);
    }

    protected void sendUpdates() {
        world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), 2);
        world.markAndNotifyBlock(pos, world.getChunkAt(pos), this.getBlockState(), this.getBlockState(), 3);
        super.markDirty();
    }
}
