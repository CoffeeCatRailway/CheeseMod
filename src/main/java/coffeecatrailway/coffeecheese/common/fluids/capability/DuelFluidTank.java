package coffeecatrailway.coffeecheese.common.fluids.capability;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

/**
 * @author CoffeeCatRailway
 * Created: 12/04/2020
 */
public class DuelFluidTank implements IFluidHandler {

    private final FluidTank tankA, tankB;

    public DuelFluidTank(int capacity) {
        this(capacity, stack -> true, capacity, stack -> true);
    }

    public DuelFluidTank(int capacityA, int capacityB) {
        this(capacityA, stack -> true, capacityB, stack -> true);
    }

    public DuelFluidTank(int capacity, Predicate<FluidStack> valid) {
        this(capacity, valid, capacity, valid);
    }

    public DuelFluidTank(int capacityA, Predicate<FluidStack> validA, int capacityB, Predicate<FluidStack> validB) {
        this.tankA = new FluidTank(capacityA, validA);
        this.tankB = new FluidTank(capacityB, validB);
    }

    public FluidTank getTankA() {
        return tankA;
    }

    public FluidTank getTankB() {
        return tankB;
    }

    @Override
    public int getTanks() {
        return 2;
    }

    @Nonnull
    @Override
    public FluidStack getFluidInTank(int tank) {
        switch (tank) {
            default:
            case 0:
                return this.tankA.getFluid();
            case 1:
                return this.tankB.getFluid();
        }
    }

    @Override
    public int getTankCapacity(int tank) {
        switch (tank) {
            default:
            case 0:
                return this.tankA.getCapacity();
            case 1:
                return this.tankB.getCapacity();
        }
    }

    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
        switch (tank) {
            default:
            case 0:
                return this.tankA.isFluidValid(stack);
            case 1:
                return this.tankB.isFluidValid(stack);
        }
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        this.tankB.fill(resource, action);
        return this.tankA.fill(resource, action);
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        this.tankB.drain(resource, action);
        return this.tankA.drain(resource, action);
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        this.tankB.drain(maxDrain, action);
        return this.tankA.drain(maxDrain, action);
    }
}
