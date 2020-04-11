package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.ModTags;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 30/08/2019
 */
public class ModFluids {

    private static NonNullUnaryOperator<Item.Properties> BUCKET_PROPS = prop -> prop.maxStackSize(1).group(ItemGroup.MISC).containerItem(Items.BUCKET);

    /// MILK
    public static final RegistryEntry<ForgeFlowingFluid.Flowing> MILK = REGISTRATE
            .fluid("milk", CheeseMod.getLocation("fluid/milk_still"), CheeseMod.getLocation("fluid/milk_flowing"))
            .properties(ForgeFlowingFluid.Properties::canMultiply)
            .attributes(builder -> builder.overlay(CheeseMod.getLocation("fluid/milk_overlay")).density(1000).viscosity(1000))
            .defaultBlock().bucket().properties(BUCKET_PROPS).build().defaultSource().register();

    /// VINEGAR
    public static final RegistryEntry<ForgeFlowingFluid.Flowing> VINEGAR = REGISTRATE
            .fluid("vinegar", CheeseMod.getLocation("fluid/vinegar_still"), CheeseMod.getLocation("fluid/vinegar_flowing"))
            .attributes(builder -> builder.overlay(CheeseMod.getLocation("fluid/vinegar_overlay")).density(1000).viscosity(750))
            .defaultBlock().bucket().properties(BUCKET_PROPS).build().defaultSource().register();

    /// OIL
    public static final RegistryEntry<ForgeFlowingFluid.Flowing> OIL = REGISTRATE
            .fluid("oil", CheeseMod.getLocation("fluid/oil_still"), CheeseMod.getLocation("fluid/oil_flowing"))
            .attributes(builder -> builder.overlay(CheeseMod.getLocation("fluid/oil_overlay")).density(1000).viscosity(1000))
            .defaultBlock().bucket().properties(BUCKET_PROPS).build().defaultSource().register();

    /// MELTED CHEESE
    public static final RegistryEntry<ForgeFlowingFluid.Flowing> MELTED_CHEESE = REGISTRATE
            .fluid("melted_cheese", CheeseMod.getLocation("fluid/melted_cheese_still"), CheeseMod.getLocation("fluid/melted_cheese_flowing"))
            .attributes(builder -> builder.density(1000).viscosity(750).temperature(500).luminosity(3))
            .defaultBlock().bucket().properties(BUCKET_PROPS).build().defaultSource().register();

    /// MELTED GRILLED CHEESE
    public static final RegistryEntry<ForgeFlowingFluid.Flowing> MELTED_GRILLED_CHEESE = REGISTRATE
            .fluid("melted_grilled_cheese", CheeseMod.getLocation("fluid/melted_grilled_cheese_still"), CheeseMod.getLocation("fluid/melted_grilled_cheese_flowing"))
            .attributes(builder -> builder.density(1000).viscosity(750).temperature(500).luminosity(3))
            .defaultBlock().bucket().properties(BUCKET_PROPS).build().defaultSource().register();

    /// MELTED HAM RAW
    public static final RegistryEntry<ForgeFlowingFluid.Flowing> MELTED_HAM_RAW = REGISTRATE
            .fluid("melted_ham_raw", CheeseMod.getLocation("fluid/melted_ham_raw_still"), CheeseMod.getLocation("fluid/melted_ham_raw_flowing"))
            .attributes(builder -> builder.density(1000).viscosity(750).temperature(500).luminosity(3))
            .defaultBlock().bucket().properties(BUCKET_PROPS).build().defaultSource().register();

    /// MELTED HAM COOKED
    public static final RegistryEntry<ForgeFlowingFluid.Flowing> MELTED_HAM_COOKED = REGISTRATE
            .fluid("melted_ham_cooked", CheeseMod.getLocation("fluid/melted_ham_cooked_still"), CheeseMod.getLocation("fluid/melted_ham_cooked_flowing"))
            .attributes(builder -> builder.density(1000).viscosity(750).temperature(500).luminosity(3))
            .defaultBlock().bucket().properties(BUCKET_PROPS).build().defaultSource().register();

    public static void load() {
    }
}
