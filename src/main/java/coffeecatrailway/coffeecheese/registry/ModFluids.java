package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.block.ModFlowingFluidBlock;
import coffeecatrailway.coffeecheese.common.item.MilkBottleItem;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
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

    /// MILK
    public static final RegistryObject<FlowingFluid> MILK_S = FLUIDS.register("milk_still", () -> new ForgeFlowingFluid.Source(ModFluids.MILK_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MILK_F = FLUIDS.register("milk_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.MILK_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> MILK = BLOCKS.register("milk", () ->
            new ModFlowingFluidBlock(MILK_S, Block.Properties.create(Material.WATER, MaterialColor.YELLOW_TERRACOTTA)
                    .doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static final Item MILK_BUCKET = Registry.ITEM.register(new ResourceLocation("milk_bucket"),
            new BucketItem(MILK_S, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)));
    public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_bottle", () ->
            new MilkBottleItem(new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(1).group(ItemGroup.MISC)));

    public static final ForgeFlowingFluid.Properties MILK_PROPERTIES =
            new ForgeFlowingFluid.Properties(MILK_S, MILK_F, FluidAttributes.builder(CheeseMod.getLocation("fluid/milk_still"), CheeseMod.getLocation("fluid/milk_flowing"))
                    .overlay(CheeseMod.getLocation("fluid/milk_overlay")).density(1000).viscosity(1000))
                    .bucket(() -> MILK_BUCKET).block(MILK).canMultiply();

    /// VINEGAR
    public static final RegistryObject<FlowingFluid> VINEGAR_S = FLUIDS.register("vinegar_still", () -> new ForgeFlowingFluid.Source(ModFluids.VINEGAR_PROPERTIES));
    public static final RegistryObject<FlowingFluid> VINEGAR_F = FLUIDS.register("vinegar_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.VINEGAR_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> VINEGAR = BLOCKS.register("vinegar", () ->
            new ModFlowingFluidBlock(VINEGAR_S, Block.Properties.create(Material.WATER, MaterialColor.YELLOW_TERRACOTTA)
                    .doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static final RegistryObject<Item> VINEGAR_BUCKET = ITEMS.register("vinegar_bucket", () ->
            new BucketItem(VINEGAR_S, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)));

    public static final ForgeFlowingFluid.Properties VINEGAR_PROPERTIES =
            new ForgeFlowingFluid.Properties(VINEGAR_S, VINEGAR_F, FluidAttributes.builder(CheeseMod.getLocation("fluid/vinegar_still"), CheeseMod.getLocation("fluid/vinegar_flowing"))
                    .overlay(CheeseMod.getLocation("fluid/vinegar_overlay")).density(1000).viscosity(750))
                    .bucket(VINEGAR_BUCKET).block(VINEGAR).canMultiply();

    /// OIL
    public static final RegistryObject<FlowingFluid> OIL_S = FLUIDS.register("oil_still", () -> new ForgeFlowingFluid.Source(ModFluids.OIL_PROPERTIES));
    public static final RegistryObject<FlowingFluid> OIL_F = FLUIDS.register("oil_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.OIL_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> OIL = BLOCKS.register("oil", () ->
            new ModFlowingFluidBlock(OIL_S, Block.Properties.create(Material.WATER, MaterialColor.YELLOW_TERRACOTTA)
                    .doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket", () ->
            new BucketItem(OIL_S, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)));

    public static final ForgeFlowingFluid.Properties OIL_PROPERTIES =
            new ForgeFlowingFluid.Properties(OIL_S, OIL_F, FluidAttributes.builder(CheeseMod.getLocation("fluid/oil_still"), CheeseMod.getLocation("fluid/oil_flowing"))
                    .overlay(CheeseMod.getLocation("fluid/oil_overlay")).density(1000).viscosity(1000))
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

    public static final ForgeFlowingFluid.Properties MELTED_CHEESE_PROPERTIES = new ForgeFlowingFluid.Properties(MELTED_CHEESE_S, MELTED_CHEESE_F,
            FluidAttributes.builder(CheeseMod.getLocation("fluid/melted_cheese_still"), CheeseMod.getLocation("fluid/melted_cheese_flowing"))
                    .density(1000).viscosity(750).temperature(500).luminosity(3))
            .bucket(MELTED_CHEESE_BUCKET).block(MELTED_CHEESE);

    /// MELTED GRILLED CHEESE
    public static final RegistryObject<FlowingFluid> MELTED_GRILLED_CHEESE_S = FLUIDS.register("melted_grilled_cheese_still", () ->
            new ForgeFlowingFluid.Source(ModFluids.MELTED_GRILLED_CHEESE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MELTED_GRILLED_CHEESE_F = FLUIDS.register("melted_grilled_cheese_flowing", () ->
            new ForgeFlowingFluid.Flowing(ModFluids.MELTED_GRILLED_CHEESE_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> MELTED_GRILLED_CHEESE = BLOCKS.register("melted_grilled_cheese", () ->
            new ModFlowingFluidBlock(MELTED_GRILLED_CHEESE_S, Block.Properties.create(Material.WATER, MaterialColor.YELLOW)
                    .doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static final RegistryObject<Item> MELTED_GRILLED_CHEESE_BUCKET = ITEMS.register("melted_grilled_cheese_bucket", () ->
            new BucketItem(MELTED_GRILLED_CHEESE_S, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)));

    public static final ForgeFlowingFluid.Properties MELTED_GRILLED_CHEESE_PROPERTIES = new ForgeFlowingFluid.Properties(MELTED_GRILLED_CHEESE_S, MELTED_GRILLED_CHEESE_F,
            FluidAttributes.builder(CheeseMod.getLocation("fluid/melted_grilled_cheese_still"), CheeseMod.getLocation("fluid/melted_grilled_cheese_flowing"))
                    .density(1000).viscosity(750).temperature(500).luminosity(3))
            .bucket(MELTED_GRILLED_CHEESE_BUCKET).block(MELTED_GRILLED_CHEESE);

    /// MELTED HAM RAW
    public static final RegistryObject<FlowingFluid> MELTED_HAM_RAW_S = FLUIDS.register("melted_ham_raw_still", () ->
            new ForgeFlowingFluid.Source(ModFluids.MELTED_HAM_RAW_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MELTED_HAM_RAW_F = FLUIDS.register("melted_ham_raw_flowing", () ->
            new ForgeFlowingFluid.Flowing(ModFluids.MELTED_HAM_RAW_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> MELTED_HAM_RAW = BLOCKS.register("melted_ham_raw", () ->
            new ModFlowingFluidBlock(MELTED_HAM_RAW_S, Block.Properties.create(Material.WATER, MaterialColor.YELLOW)
                    .doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static final RegistryObject<Item> MELTED_HAM_RAW_BUCKET = ITEMS.register("melted_ham_raw_bucket", () ->
            new BucketItem(MELTED_HAM_RAW_S, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)));

    public static final ForgeFlowingFluid.Properties MELTED_HAM_RAW_PROPERTIES = new ForgeFlowingFluid.Properties(MELTED_HAM_RAW_S, MELTED_HAM_RAW_F,
            FluidAttributes.builder(CheeseMod.getLocation("fluid/melted_ham_raw_still"), CheeseMod.getLocation("fluid/melted_ham_raw_flowing"))
                    .density(1000).viscosity(750).temperature(500).luminosity(3))
            .bucket(MELTED_HAM_RAW_BUCKET).block(MELTED_HAM_RAW);

    /// MELTED HAM COOKED
    public static final RegistryObject<FlowingFluid> MELTED_HAM_COOKED_S = FLUIDS.register("melted_ham_cooked_still", () ->
            new ForgeFlowingFluid.Source(ModFluids.MELTED_HAM_COOKED_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MELTED_HAM_COOKED_F = FLUIDS.register("melted_ham_cooked_flowing", () ->
            new ForgeFlowingFluid.Flowing(ModFluids.MELTED_HAM_COOKED_PROPERTIES));

    public static final RegistryObject<FlowingFluidBlock> MELTED_HAM_COOKED = BLOCKS.register("melted_ham_cooked", () ->
            new ModFlowingFluidBlock(MELTED_HAM_COOKED_S, Block.Properties.create(Material.WATER, MaterialColor.YELLOW)
                    .doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static final RegistryObject<Item> MELTED_HAM_COOKED_BUCKET = ITEMS.register("melted_ham_cooked_bucket", () ->
            new BucketItem(MELTED_HAM_COOKED_S, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC)));

    public static final ForgeFlowingFluid.Properties MELTED_HAM_COOKED_PROPERTIES = new ForgeFlowingFluid.Properties(MELTED_HAM_COOKED_S, MELTED_HAM_COOKED_F,
            FluidAttributes.builder(CheeseMod.getLocation("fluid/melted_ham_cooked_still"), CheeseMod.getLocation("fluid/melted_ham_cooked_flowing"))
                    .density(1000).viscosity(750).temperature(500).luminosity(3))
            .bucket(MELTED_HAM_COOKED_BUCKET).block(MELTED_HAM_COOKED);

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        FLUIDS.register(modEventBus);
    }
}
