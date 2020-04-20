package coffeecatrailway.coffeecheese.common.tileentity;

import coffeecatrailway.coffeecheese.common.fluids.capability.DuelFluidTank;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 31/08/2019
 */
public abstract class LockableTileFluidHandler extends ModLockableTileEntity {

    protected DuelFluidTank duelTank;
    private LazyOptional<DuelFluidTank> fluidHandler;
    protected boolean useTankB = true;

    public LockableTileFluidHandler(TileEntityType<?> type) {
        this(type, 0);
    }

    public LockableTileFluidHandler(TileEntityType<?> type, int fluidCapacity) {
        super(type);
        duelTank = new DuelFluidTank(fluidCapacity);
        fluidHandler = LazyOptional.of(() -> this.duelTank);
    }

    public FluidTank getTankA() {
        return this.duelTank.getTankA();
    }

    public FluidTank getTankB() {
        return this.duelTank.getTankB();
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.readTankNBT(compound, "TankA", this.getTankA());
        if (useTankB)
            this.readTankNBT(compound, "TankB", this.getTankB());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        this.writeTankNBT(compound, "TankA", this.getTankA());
        if (useTankB)
            this.writeTankNBT(compound, "TankB", this.getTankB());
        return super.write(compound);
    }

    protected FluidTank readTankNBT(CompoundNBT compound, String key, FluidTank tank) {
        return tank.readFromNBT((CompoundNBT) compound.get(key));
    }

    protected INBT writeTankNBT(CompoundNBT compound, String key, FluidTank tank) {
        CompoundNBT tankNBT = new CompoundNBT();
        tank.writeToNBT(tankNBT);
        return compound.put(key, tankNBT);
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return fluidHandler.cast();
        return super.getCapability(capability, facing);
    }
}
