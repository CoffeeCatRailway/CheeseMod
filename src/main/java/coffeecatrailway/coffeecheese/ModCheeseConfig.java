package coffeecatrailway.coffeecheese;

import coffeecatrailway.coffeecheese.common.tileentity.GrillTileEntity;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * @author CoffeeCatRailway
 * Created: 22/12/2019
 */
public class ModCheeseConfig {

    private static final String config = CheeseMod.MOD_ID + ".";
    private static final String configModifier = config + "modifier.";
    private static final String configOres = config + "ores.";
    private static final String configMobs = config + "mobs.";

    public static ForgeConfigSpec.DoubleValue cheeseSuitScale;
    public static ForgeConfigSpec.DoubleValue cheeseSuitBindingScale;

    public static ForgeConfigSpec.DoubleValue hamSuitScale;
    public static ForgeConfigSpec.DoubleValue hamSuitBindingScale;

    public static ForgeConfigSpec.BooleanValue topEnabled;

    public static class ClientConfig {

        public ClientConfig(ForgeConfigSpec.Builder builder) {
            double minScale = 0.0f;
            double maxScale = 10.0f;
            cheeseSuitScale = builder.comment("Cheese suit scale")
                    .defineInRange(configModifier + "cheeseSuitScale", 0.4d, minScale, maxScale);

            cheeseSuitBindingScale = builder.comment("Cheese suit binding scale")
                    .defineInRange(configModifier + "cheeseSuitBindingScale", 0.3d, minScale, maxScale);

            hamSuitScale = builder.comment("Ham suit scale")
                    .defineInRange(configModifier + "hamSuitScale", 0.6d, minScale, maxScale);

            hamSuitBindingScale = builder.comment("Ham suit binding scale")
                    .defineInRange(configModifier + "hamSuitBindingScale", 0.3d, minScale, maxScale);

            topEnabled = builder.comment("Use the one probe")
                    .define(config + "topEnabled", true);
        }
    }

    public static ForgeConfigSpec.BooleanValue stickyFoodBlock;

    public static ForgeConfigSpec.IntValue pineappleDropChance;

    /// Modifiers ///
    public static ForgeConfigSpec.DoubleValue grilledFoodMultiplier;
    public static ForgeConfigSpec.DoubleValue foodCombo;
    public static ForgeConfigSpec.IntValue toastedSandwichOilDrain;

    public static ForgeConfigSpec.IntValue grillSpeed;
    public static ForgeConfigSpec.IntValue melterSpeed;
    public static ForgeConfigSpec.IntValue pizzaOvenSpeed;

    /// Ores ///
    public static ForgeConfigSpec.BooleanValue cheeseOreNetherGen;
    public static ForgeConfigSpec.IntValue cheeseOreNetherSize;
    public static ForgeConfigSpec.IntValue cheeseOreNetherRegionSize;
    public static ForgeConfigSpec.BooleanValue cheeseOreEndGen;
    public static ForgeConfigSpec.IntValue cheeseOreEndSize;
    public static ForgeConfigSpec.IntValue cheeseOreEndRegionSize;
    public static ForgeConfigSpec.IntValue cheeseOreSize;
    public static ForgeConfigSpec.IntValue cheeseOreRegionSize;

    public static ForgeConfigSpec.BooleanValue grilledCheeseOreNetherGen;
    public static ForgeConfigSpec.IntValue grilledCheeseOreNetherSize;
    public static ForgeConfigSpec.IntValue grilledCheeseOreNetherRegionSize;
    public static ForgeConfigSpec.BooleanValue grilledCheeseOreEndGen;
    public static ForgeConfigSpec.IntValue grilledCheeseOreEndSize;
    public static ForgeConfigSpec.IntValue grilledCheeseOreEndRegionSize;
    public static ForgeConfigSpec.IntValue grilledCheeseOreSize;
    public static ForgeConfigSpec.IntValue grilledCheeseOreRegionSize;

    public static ForgeConfigSpec.BooleanValue hamRawOreNetherGen;
    public static ForgeConfigSpec.IntValue hamRawOreNetherSize;
    public static ForgeConfigSpec.IntValue hamRawOreNetherRegionSize;
    public static ForgeConfigSpec.BooleanValue hamRawOreEndGen;
    public static ForgeConfigSpec.IntValue hamRawOreEndSize;
    public static ForgeConfigSpec.IntValue hamRawOreEndRegionSize;
    public static ForgeConfigSpec.IntValue hamRawOreSize;
    public static ForgeConfigSpec.IntValue hamRawOreRegionSize;

    public static ForgeConfigSpec.BooleanValue hamCookedOreNetherGen;
    public static ForgeConfigSpec.IntValue hamCookedOreNetherSize;
    public static ForgeConfigSpec.IntValue hamCookedOreNetherRegionSize;
    public static ForgeConfigSpec.BooleanValue hamCookedOreEndGen;
    public static ForgeConfigSpec.IntValue hamCookedOreEndSize;
    public static ForgeConfigSpec.IntValue hamCookedOreEndRegionSize;
    public static ForgeConfigSpec.IntValue hamCookedOreSize;
    public static ForgeConfigSpec.IntValue hamCookedOreRegionSize;

    /// Mobs ///
    public static ForgeConfigSpec.IntValue cheeseFoodieWeight;
    public static ForgeConfigSpec.IntValue cheeseFoodieMin;
    public static ForgeConfigSpec.IntValue cheeseFoodieMax;

    public static ForgeConfigSpec.IntValue grilledCheeseFoodieWeight;
    public static ForgeConfigSpec.IntValue grilledCheeseFoodieMin;
    public static ForgeConfigSpec.IntValue grilledCheeseFoodieMax;

    public static ForgeConfigSpec.IntValue hamRawFoodieWeight;
    public static ForgeConfigSpec.IntValue hamRawFoodieMin;
    public static ForgeConfigSpec.IntValue hamRawFoodieMax;

    public static ForgeConfigSpec.IntValue hamCookedFoodieWeight;
    public static ForgeConfigSpec.IntValue hamCookedFoodieMin;
    public static ForgeConfigSpec.IntValue hamCookedFoodieMax;

    public static class ServerConfig {

        public ServerConfig(ForgeConfigSpec.Builder builder) {
            stickyFoodBlock = builder.comment("If true all food blocks (cheese, grilled cheese & ham raw or cooked) will act like slime blocks when pushed by a piston")
                    .define(config + "stickyFoodBlock", true);

            pineappleDropChance = builder.comment("Chance for a pineapple plant to drop from tall grass")
                    .defineInRange(config + "pineappleDropChance", 8, 0, 100);

            /// Modifiers ///
            builder.comment("\nCheeseMod modifier settings");

            grilledFoodMultiplier = builder.comment("The amount of how much the saturation changes when grilled")
                    .defineInRange(configModifier + "grilledSaturation", 1.5d, 0.5d, 10.0d);
            foodCombo = builder.comment("Food combo add amount (pizza, toastie, etc.)")
                    .defineInRange(configModifier + "foodCombo", .75d, 0.5d, 5.0d);
            toastedSandwichOilDrain = builder.comment("The amount oil drained from the grill when cooking a sandwich")
                    .defineInRange(configModifier + "toastedSandwichOilDrain", 10, 1, GrillTileEntity.FLUID_CAPTACITY);

            int minSpeed = 1;
            int maxSpeed = 20;
            grillSpeed = builder.comment("Speed multiplier for the grill")
                    .defineInRange(configModifier + "grillSpeed", 5, minSpeed, maxSpeed);
            melterSpeed = builder.comment("Speed multiplier for the melter")
                    .defineInRange(configModifier + "melterSpeed", 4, minSpeed, maxSpeed);
            pizzaOvenSpeed = builder.comment("Speed multiplier for the pizza oven")
                    .defineInRange(configModifier + "pizzaOvenSpeed", 4, minSpeed, maxSpeed);

            /// Ores ///
            builder.comment("\nCheeseMod ore settings");

            cheeseOreNetherGen = builder.comment("Cheese metal ore (nether)")
                    .define(configOres + "nether.cheeseOreGen", true);
            cheeseOreNetherSize = builder.defineInRange(configOres + "nether.cheeseOreSize", 10, 0, 100);
            cheeseOreNetherRegionSize = builder.defineInRange(configOres + "nether.cheeseOreRegionSize", 8, 0, 100);

            cheeseOreEndGen = builder.comment("Cheese metal ore (end)")
                    .define(configOres + "end.cheeseOreGen", true);
            cheeseOreEndSize = builder.defineInRange(configOres + "end.cheeseOreSize", 10, 0, 100);
            cheeseOreEndRegionSize = builder.defineInRange(configOres + "end.cheeseOreRegionSize", 8, 0, 100);

            cheeseOreSize = builder.comment("Cheese metal ore (overworld)")
                    .defineInRange(configOres + "cheeseOreSize", 10, 0, 100);
            cheeseOreRegionSize = builder.defineInRange(configOres + "cheeseOreRegionSize", 8, 0, 100);

            grilledCheeseOreNetherGen = builder.comment("Grilled cheese metal ore (nether)")
                    .define(configOres + "nether.grilledCheeseOreGen", true);
            grilledCheeseOreNetherSize = builder.defineInRange(configOres + "nether.grilledCheeseOreSize", 10, 0, 100);
            grilledCheeseOreNetherRegionSize = builder.defineInRange(configOres + "nether.grilledCheeseOreRegionSize", 8, 0, 100);

            grilledCheeseOreEndGen = builder.comment("Grilled cheese metal ore (end)")
                    .define(configOres + "end.grilledCheeseOreGen", true);
            grilledCheeseOreEndSize = builder.defineInRange(configOres + "end.grilledCheeseOreSize", 10, 0, 100);
            grilledCheeseOreEndRegionSize = builder.defineInRange(configOres + "end.grilledCheeseOreRegionSize", 8, 0, 100);

            grilledCheeseOreSize = builder.comment("Grilled cheese metal ore (overworld)")
                    .defineInRange(configOres + "grilledCheeseOreSize", 10, 0, 100);
            grilledCheeseOreRegionSize = builder.defineInRange(configOres + "grilledCheeseOreRegionSize", 8, 0, 100);

            hamRawOreNetherGen = builder.comment("Ham raw metal ore (nether)")
                    .define(configOres + "nether.hamRawOreGen", true);
            hamRawOreNetherSize = builder.defineInRange(configOres + "nether.hamRawOreSize", 10, 0, 100);
            hamRawOreNetherRegionSize = builder.defineInRange(configOres + "nether.hamRawOreRegionSize", 8, 0, 100);

            hamRawOreEndGen = builder.comment("Ham raw metal ore (end)")
                    .define(configOres + "end.hamRawOreGen", true);
            hamRawOreEndSize = builder.defineInRange(configOres + "end.hamRawOreSize", 10, 0, 100);
            hamRawOreEndRegionSize = builder.defineInRange(configOres + "end.hamRawOreRegionSize", 8, 0, 100);

            hamRawOreSize = builder.comment("Ham raw metal ore (overworld)")
                    .defineInRange(configOres + "hamRawOreSize", 10, 0, 100);
            hamRawOreRegionSize = builder.defineInRange(configOres + "hamRawOreRegionSize", 8, 0, 100);

            hamCookedOreNetherGen = builder.comment("Ham cooked metal ore (nether)")
                    .define(configOres + "nether.hamCookedOreGen", true);
            hamCookedOreNetherSize = builder.defineInRange(configOres + "nether.hamCookedOreSize", 10, 0, 100);
            hamCookedOreNetherRegionSize = builder.defineInRange(configOres + "nether.hamCookedOreRegionSize", 8, 0, 100);

            hamCookedOreEndGen = builder.comment("Ham cooked metal ore (end)")
                    .define(configOres + "end.hamCookedOreGen", true);
            hamCookedOreEndSize = builder.defineInRange(configOres + "end.hamCookedOreSize", 10, 0, 100);
            hamCookedOreEndRegionSize = builder.defineInRange(configOres + "end.hamCookedOreRegionSize", 8, 0, 100);

            hamCookedOreSize = builder.comment("Ham cooked metal ore (overworld)")
                    .defineInRange(configOres + "hamCookedOreSize", 10, 0, 100);
            hamCookedOreRegionSize = builder.defineInRange(configOres + "hamCookedOreRegionSize", 8, 0, 100);

            /// Mobs ///
            builder.comment("\nCheeseMod mob settings");

            cheeseFoodieWeight = builder.comment("Cheese foodie weight")
                    .defineInRange(configMobs + "cheeseFoodieWeight", 100, 0, 100);
            cheeseFoodieMin = builder.comment("Cheese foodie min count")
                    .defineInRange(configMobs + "cheeseFoodieMin", 1, 0, 10);
            cheeseFoodieMax = builder.comment("Cheese foodie max count")
                    .defineInRange(configMobs + "cheeseFoodieMax", 5, 1, 10);

            grilledCheeseFoodieWeight = builder.comment("Grilled cheese foodie weight")
                    .defineInRange(configMobs + "grilledCheeseFoodieWeight", 100, 0, 100);
            grilledCheeseFoodieMin = builder.comment("Grilled cheese foodie min count")
                    .defineInRange(configMobs + "grilledCheeseFoodieMin", 1, 0, 10);
            grilledCheeseFoodieMax = builder.comment("Grilled cheese foodie max count")
                    .defineInRange(configMobs + "grilledCheeseFoodieMax", 5, 1, 10);

            hamRawFoodieWeight = builder.comment("Ham raw foodie weight")
                    .defineInRange(configMobs + "hamRawFoodieWeight", 100, 0, 100);
            hamRawFoodieMin = builder.comment("Ham raw foodie min count")
                    .defineInRange(configMobs + "hamRawFoodieMin", 1, 0, 10);
            hamRawFoodieMax = builder.comment("Ham raw foodie max count")
                    .defineInRange(configMobs + "hamRawFoodieMax", 5, 1, 10);

            hamCookedFoodieWeight = builder.comment("Ham cooked foodie weight")
                    .defineInRange(configMobs + "hamCookedFoodieWeight", 100, 0, 100);
            hamCookedFoodieMin = builder.comment("Ham cooked foodie min count")
                    .defineInRange(configMobs + "hamCookedFoodieMin", 1, 0, 10);
            hamCookedFoodieMax = builder.comment("Ham cooked foodie max count")
                    .defineInRange(configMobs + "hamCookedFoodieMax", 5, 1, 10);
        }
    }
}
