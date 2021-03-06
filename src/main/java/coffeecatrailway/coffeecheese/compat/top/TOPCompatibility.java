package coffeecatrailway.coffeecheese.compat.top;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.ModCheeseConfig;
import coffeecatrailway.coffeecheese.common.block.GrillBlock;
import coffeecatrailway.coffeecheese.common.tileentity.GrillTileEntity;
import coffeecatrailway.coffeecheese.common.tileentity.MelterTileEntity;
import coffeecatrailway.coffeecheese.common.tileentity.PizzaOvenTileEntity;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModFluids;
import com.google.common.base.Function;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.resources.I18n;
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

                probeInfo.horizontal().text(I18n.format("top." + CheeseMod.MOD_ID + ".grill.oil"));
                probeInfo.horizontal().item(new ItemStack(ModFluids.OIL.get().getFilledBucket())).progress(tile.getTankA().getFluidAmount(), tile.getTankA().getCapacity());

                if (tile.getBlockState().get(GrillBlock.HAS_CATCHER)) {
                    probeInfo.horizontal().text(I18n.format("top." + CheeseMod.MOD_ID + ".grill.oil_catcher"));
                    probeInfo.horizontal().item(new ItemStack(ModFluids.OIL.get().getFilledBucket())).progress(tile.getTankB().getFluidAmount(), tile.getTankB().getCapacity());
                }
            }
            if (block == ModBlocks.MELTER.get()) {
                MelterTileEntity tile = (MelterTileEntity) world.getTileEntity(hitData.getPos());
                if (!tile.getStackInSlot(0).isEmpty()) {
                    probeInfo.horizontal().text(I18n.format("top." + CheeseMod.MOD_ID + ".melter.stack"));
                    probeInfo.horizontal().item(tile.getStackInSlot(0)).progress(tile.data.get(2), tile.data.get(3));
                }

                if (tile.getTankA().getFluidAmount() > 0) {
                    String fluidName = I18n.format("block." + tile.getTankA().getFluid().getFluid().getRegistryName().toString().replace(":", "."));
                    if (fluidName.contains("empty"))
                        fluidName = "Empty";
                    probeInfo.horizontal().text(I18n.format("top." + CheeseMod.MOD_ID + ".melter.fluid", fluidName));
                    probeInfo.horizontal().item(new ItemStack(tile.getTankA().getFluid().getFluid().getFilledBucket())).progress(tile.getTankA().getFluidAmount(), tile.getTankA().getCapacity());
                }
            }
            if (block == ModBlocks.PIZZA_OVEN.get()) {
                PizzaOvenTileEntity tile = (PizzaOvenTileEntity) world.getTileEntity(hitData.getPos());
                if (tile.hasItems() && tile.data.get(2) > 0)
                    probeInfo.horizontal().progress(tile.data.get(2), tile.data.get(3));
            }
        }
    }
}
