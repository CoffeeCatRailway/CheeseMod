package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.block.ModFlowingFluidBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 30/08/2019
 */
public class ModFluids {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, CheeseMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, CheeseMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, CheeseMod.MOD_ID);

    /// OIL
    public static final RegistryObject<FlowingFluid> OIL_S = FLUIDS.register("oil_still", () -> new ForgeFlowingFluid.Source(ModFluids.OIL_PROPERTIES));
    public static final RegistryObject<FlowingFluid> OIL_F = FLUIDS.register("oil_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.OIL_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> OIL = BLOCKS.register("oil", () ->
            new ModFlowingFluidBlock(OIL_S, Block.Properties.create(Material.WATER, MaterialColor.YELLOW_TERRACOTTA)
                    .doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket", () ->
            new BucketItem(OIL_S, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)));

    public static final ForgeFlowingFluid.Properties OIL_PROPERTIES =
            new ForgeFlowingFluid.Properties(OIL_S, OIL_F, FluidAttributes.builder(CheeseMod.getLocation("block/oil_still"), CheeseMod.getLocation("block/oil_flowing"))
                    .overlay(CheeseMod.getLocation("block/oil_overlay")).density(1000).viscosity(1000))
                    .bucket(OIL_BUCKET).block(OIL);

    /// MELTED CHEESE
    public static final RegistryObject<FlowingFluid> MELTED_CHEESE_S = FLUIDS.register("melted_cheese_still", () ->
            new ForgeFlowingFluid.Source(ModFluids.MELTED_CHEESE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MELTED_CHEESE_F = FLUIDS.register("melted_cheese_flowing", () ->
            new ForgeFlowingFluid.Flowing(ModFluids.MELTED_CHEESE_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> MELTED_CHEESE = BLOCKS.register("melted_cheese", () ->
            new ModFlowingFluidBlock(MELTED_CHEESE_S, Block.Properties.create(Material.WATER, MaterialColor.YELLOW)
                    .doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static final RegistryObject<Item> MELTED_CHEESE_BUCKET = ITEMS.register("melted_cheese_bucket", () ->
            new BucketItem(MELTED_CHEESE_S, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)));

    public static final ForgeFlowingFluid.Properties MELTED_CHEESE_PROPERTIES =
            new ForgeFlowingFluid.Properties(MELTED_CHEESE_S, MELTED_CHEESE_F, FluidAttributes.builder(CheeseMod.getLocation("block/melted_cheese_still"), CheeseMod.getLocation("block/melted_cheese_flowing"))
                    .density(1000).viscosity(750).temperature(500).luminosity(3))
                    .bucket(MELTED_CHEESE_BUCKET).block(MELTED_CHEESE);

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        FLUIDS.register(modEventBus);
    }
}
