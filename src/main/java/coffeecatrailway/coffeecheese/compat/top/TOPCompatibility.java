package coffeecatrailway.coffeecheese.compat.top;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.ModCheeseConfig;
import coffeecatrailway.coffeecheese.common.tileentity.GrillTileEntity;
import coffeecatrailway.coffeecheese.common.tileentity.MelterTileEntity;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModFluids;
import com.google.common.base.Function;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 5/01/2020
 */
public class TOPCompatibility implements Function<ITheOneProbe, Void>, IProbeInfoProvider {

    private static ITheOneProbe probe;

    @Nullable
    @Override
    public Void apply(@Nullable ITheOneProbe theOneProbe) {
        if (ModCheeseConfig.topEnabled.get()) {
            probe = theOneProbe;
            CheeseMod.LOGGER.info("Enabled support for The One Probe");
            probe.registerProvider(this);
        } else {
            CheeseMod.LOGGER.info("Disabled support for The One Probe");
        }
        return null;
    }

    @Override
    public String getID() {
        return CheeseMod.getLocation("default").toString();
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, BlockState blockState, IProbeHitData hitData) {
        if (probeInfo != null && blockState != null) {
            if (mode == ProbeMode.DEBUG) {
                probeInfo.text(blockState.toString());
            }

            Block block = blockState.getBlock();
            if (block == ModBlocks.GRILL.get()) {
                GrillTileEntity tile = (GrillTileEntity) world.getTileEntity(hitData.getPos());
                if (!tile.getStackInSlot(0).isEmpty())
                    probeInfo.horizontal().item(tile.getStackInSlot(0)).progress(tile.data.get(2), tile.data.get(3));
                probeInfo.horizontal().item(new ItemStack(ModFluids.OIL_BUCKET.get())).progress(tile.getTank().getFluidAmount(), tile.getTank().getCapacity());
            }
            if (block == ModBlocks.MELTER.get()) {
                MelterTileEntity tile = (MelterTileEntity) world.getTileEntity(hitData.getPos());
                if (!tile.getStackInSlot(0).isEmpty())
                    probeInfo.horizontal().item(tile.getStackInSlot(0)).progress(tile.data.get(2), tile.data.get(3));
                if (tile.getTank().getFluidAmount() > 0)
                    probeInfo.horizontal().item(new ItemStack(tile.getTank().getFluid().getFluid().getFilledBucket())).progress(tile.getTank().getFluidAmount(), tile.getTank().getCapacity());
            }
        }
    }
}
