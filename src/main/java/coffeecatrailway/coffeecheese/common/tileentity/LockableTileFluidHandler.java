package coffeecatrailway.coffeecheese.common.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
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
public abstract class LockableTileFluidHandler extends ModLockableTileEntity {

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
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.readTankNBT(compound, "FluidTank", this.tank);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        this.writeTankNBT(compound, "FluidTank", this.tank);
        return super.write(compound);
    }

    protected INBT writeTankNBT(CompoundNBT compound, String key, FluidTank tank) {
        CompoundNBT tankNBT = new CompoundNBT();
        tank.writeToNBT(tankNBT);
        return compound.put(key, tankNBT);
    }

    protected FluidTank readTankNBT(CompoundNBT compound, String key, FluidTank tank) {
        return tank.readFromNBT((CompoundNBT) compound.get(key));
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return holder.cast();
        return super.getCapability(capability, facing);
    }
}
