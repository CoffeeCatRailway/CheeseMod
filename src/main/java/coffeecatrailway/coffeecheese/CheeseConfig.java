package coffeecatrailway.coffeecheese;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * @author CoffeeCatRailway
 * Created: 24/07/2020
 */
public class CheeseConfig {

    private static final String CONFIG = CheeseMod.MOD_ID + ".";
    private static final String CONFIG_MODIFIER = CONFIG + "modifier.";
    private static final String CONFIG_ORES = CONFIG + "ores.";
    private static final String CONFIG_MOBS = CONFIG + "mobs.";

    public static class Client {

        public ForgeConfigSpec.DoubleValue cheeseSuitScale;
        public ForgeConfigSpec.DoubleValue cheeseSuitBindingScale;

        public ForgeConfigSpec.DoubleValue hamSuitScale;
        public ForgeConfigSpec.DoubleValue hamSuitBindingScale;

        public ForgeConfigSpec.BooleanValue topEnabled;

        public Client(ForgeConfigSpec.Builder builder) {
            double minScale = 0.0f;
            double maxScale = 10.0f;
            cheeseSuitScale = builder.comment("Cheese suit scale").defineInRange(CONFIG_MODIFIER + "cheeseSuitScale", 0.4d, minScale, maxScale);
            cheeseSuitBindingScale = builder.comment("Cheese suit binding scale").defineInRange(CONFIG_MODIFIER + "cheeseSuitBindingScale", 0.3d, minScale, maxScale);

            hamSuitScale = builder.comment("Ham suit scale").defineInRange(CONFIG_MODIFIER + "hamSuitScale", 0.6d, minScale, maxScale);
            hamSuitBindingScale = builder.comment("Ham suit binding scale").defineInRange(CONFIG_MODIFIER + "hamSuitBindingScale", 0.3d, minScale, maxScale);

            topEnabled = builder.comment("Use the one probe").define(CONFIG + "topEnabled", true);
        }
    }

    public static class Server {

        public ForgeConfigSpec.BooleanValue stickyFoodBlock;

        public ForgeConfigSpec.IntValue pineappleDropChance;

        /// Modifiers ///
        public ForgeConfigSpec.DoubleValue grilledFoodMultiplier;
        public ForgeConfigSpec.DoubleValue foodCombo;
        public ForgeConfigSpec.IntValue toastedSandwichOilDrain;
        public ForgeConfigSpec.IntValue stackableFoodIngredientCount;

        public ForgeConfigSpec.IntValue grillSpeed;
        public ForgeConfigSpec.IntValue melterSpeed;
        public ForgeConfigSpec.IntValue pizzaOvenSpeed;

        /// Ores ///
        public ForgeConfigSpec.BooleanValue cheeseOreNetherGen;
        public ForgeConfigSpec.IntValue cheeseOreNetherSize;
        public ForgeConfigSpec.IntValue cheeseOreNetherRegionSize;
        public ForgeConfigSpec.BooleanValue cheeseOreEndGen;
        public ForgeConfigSpec.IntValue cheeseOreEndSize;
        public ForgeConfigSpec.IntValue cheeseOreEndRegionSize;
        public ForgeConfigSpec.IntValue cheeseOreSize;
        public ForgeConfigSpec.IntValue cheeseOreRegionSize;

        public ForgeConfigSpec.BooleanValue grilledCheeseOreNetherGen;
        public ForgeConfigSpec.IntValue grilledCheeseOreNetherSize;
        public ForgeConfigSpec.IntValue grilledCheeseOreNetherRegionSize;
        public ForgeConfigSpec.BooleanValue grilledCheeseOreEndGen;
        public ForgeConfigSpec.IntValue grilledCheeseOreEndSize;
        public ForgeConfigSpec.IntValue grilledCheeseOreEndRegionSize;
        public ForgeConfigSpec.IntValue grilledCheeseOreSize;
        public ForgeConfigSpec.IntValue grilledCheeseOreRegionSize;

        public ForgeConfigSpec.BooleanValue hamRawOreNetherGen;
        public ForgeConfigSpec.IntValue hamRawOreNetherSize;
        public ForgeConfigSpec.IntValue hamRawOreNetherRegionSize;
        public ForgeConfigSpec.BooleanValue hamRawOreEndGen;
        public ForgeConfigSpec.IntValue hamRawOreEndSize;
        public ForgeConfigSpec.IntValue hamRawOreEndRegionSize;
        public ForgeConfigSpec.IntValue hamRawOreSize;
        public ForgeConfigSpec.IntValue hamRawOreRegionSize;

        public ForgeConfigSpec.BooleanValue hamCookedOreNetherGen;
        public ForgeConfigSpec.IntValue hamCookedOreNetherSize;
        public ForgeConfigSpec.IntValue hamCookedOreNetherRegionSize;
        public ForgeConfigSpec.BooleanValue hamCookedOreEndGen;
        public ForgeConfigSpec.IntValue hamCookedOreEndSize;
        public ForgeConfigSpec.IntValue hamCookedOreEndRegionSize;
        public ForgeConfigSpec.IntValue hamCookedOreSize;
        public ForgeConfigSpec.IntValue hamCookedOreRegionSize;

        /// Mobs ///
        public ForgeConfigSpec.IntValue cheeseFoodieWeight;
        public ForgeConfigSpec.IntValue cheeseFoodieMin;
        public ForgeConfigSpec.IntValue cheeseFoodieMax;

        public ForgeConfigSpec.IntValue grilledCheeseFoodieWeight;
        public ForgeConfigSpec.IntValue grilledCheeseFoodieMin;
        public ForgeConfigSpec.IntValue grilledCheeseFoodieMax;

        public ForgeConfigSpec.IntValue hamRawFoodieWeight;
        public ForgeConfigSpec.IntValue hamRawFoodieMin;
        public ForgeConfigSpec.IntValue hamRawFoodieMax;

        public ForgeConfigSpec.IntValue hamCookedFoodieWeight;
        public ForgeConfigSpec.IntValue hamCookedFoodieMin;
        public ForgeConfigSpec.IntValue hamCookedFoodieMax;

        public Server(ForgeConfigSpec.Builder builder) {
            stickyFoodBlock = builder.comment("If true all food blocks (cheese, grilled cheese & ham raw or cooked) will act like slime blocks when pushed by a piston")
                    .define(CONFIG + "stickyFoodBlock", true);

            pineappleDropChance = builder.comment("Chance for a pineapple plant to drop from tall grass")
                    .defineInRange(CONFIG + "pineappleDropChance", 8, 0, 100);

            /// Modifiers ///
            builder.comment("\nCheeseMod modifier settings");

            grilledFoodMultiplier = builder.comment("The amount of how much the saturation changes when grilled")
                    .defineInRange(CONFIG_MODIFIER + "grilledSaturation", 1.5d, 0.5d, 10.0d);
            foodCombo = builder.comment("Food combo add amount (pizza, toasted sandwich, etc.)")
                    .defineInRange(CONFIG_MODIFIER + "foodCombo", 1.2d, 0.5d, 2.0d);
            toastedSandwichOilDrain = builder.comment("The amount oil drained from the grill when cooking a sandwich")
                    .defineInRange(CONFIG_MODIFIER + "toastedSandwichOilDrain", 10, 1, 2000); // GrillTileEntity.FLUID_CAPTACITY
            stackableFoodIngredientCount = builder.comment("The amount of foods that can be crafted in a sandwich, cracker or pizza")
                    .defineInRange(CONFIG_MODIFIER + "stackableFoodIngredientCount", 4, 1, 9);

            int minSpeed = 1;
            int maxSpeed = 20;
            grillSpeed = builder.comment("Speed multiplier for the grill")
                    .defineInRange(CONFIG_MODIFIER + "grillSpeed", 5, minSpeed, maxSpeed);
            melterSpeed = builder.comment("Speed multiplier for the melter")
                    .defineInRange(CONFIG_MODIFIER + "melterSpeed", 4, minSpeed, maxSpeed);
            pizzaOvenSpeed = builder.comment("Speed multiplier for the pizza oven")
                    .defineInRange(CONFIG_MODIFIER + "pizzaOvenSpeed", 4, minSpeed, maxSpeed);

            /// Ores ///
            builder.comment("\nCheeseMod ore settings");

            cheeseOreNetherGen = builder.comment("Cheese metal ore (nether)")
                    .define(CONFIG_ORES + "nether.cheeseOreGen", true);
            cheeseOreNetherSize = builder.defineInRange(CONFIG_ORES + "nether.cheeseOreSize", 10, 0, 100);
            cheeseOreNetherRegionSize = builder.defineInRange(CONFIG_ORES + "nether.cheeseOreRegionSize", 8, 0, 100);

            cheeseOreEndGen = builder.comment("Cheese metal ore (end)")
                    .define(CONFIG_ORES + "end.cheeseOreGen", true);
            cheeseOreEndSize = builder.defineInRange(CONFIG_ORES + "end.cheeseOreSize", 10, 0, 100);
            cheeseOreEndRegionSize = builder.defineInRange(CONFIG_ORES + "end.cheeseOreRegionSize", 8, 0, 100);

            cheeseOreSize = builder.comment("Cheese metal ore (overworld)")
                    .defineInRange(CONFIG_ORES + "cheeseOreSize", 10, 0, 100);
            cheeseOreRegionSize = builder.defineInRange(CONFIG_ORES + "cheeseOreRegionSize", 8, 0, 100);

            grilledCheeseOreNetherGen = builder.comment("Grilled cheese metal ore (nether)")
                    .define(CONFIG_ORES + "nether.grilledCheeseOreGen", true);
            grilledCheeseOreNetherSize = builder.defineInRange(CONFIG_ORES + "nether.grilledCheeseOreSize", 10, 0, 100);
            grilledCheeseOreNetherRegionSize = builder.defineInRange(CONFIG_ORES + "nether.grilledCheeseOreRegionSize", 8, 0, 100);

            grilledCheeseOreEndGen = builder.comment("Grilled cheese metal ore (end)")
                    .define(CONFIG_ORES + "end.grilledCheeseOreGen", true);
            grilledCheeseOreEndSize = builder.defineInRange(CONFIG_ORES + "end.grilledCheeseOreSize", 10, 0, 100);
            grilledCheeseOreEndRegionSize = builder.defineInRange(CONFIG_ORES + "end.grilledCheeseOreRegionSize", 8, 0, 100);

            grilledCheeseOreSize = builder.comment("Grilled cheese metal ore (overworld)")
                    .defineInRange(CONFIG_ORES + "grilledCheeseOreSize", 10, 0, 100);
            grilledCheeseOreRegionSize = builder.defineInRange(CONFIG_ORES + "grilledCheeseOreRegionSize", 8, 0, 100);

            hamRawOreNetherGen = builder.comment("Ham raw metal ore (nether)")
                    .define(CONFIG_ORES + "nether.hamRawOreGen", true);
            hamRawOreNetherSize = builder.defineInRange(CONFIG_ORES + "nether.hamRawOreSize", 10, 0, 100);
            hamRawOreNetherRegionSize = builder.defineInRange(CONFIG_ORES + "nether.hamRawOreRegionSize", 8, 0, 100);

            hamRawOreEndGen = builder.comment("Ham raw metal ore (end)")
                    .define(CONFIG_ORES + "end.hamRawOreGen", true);
            hamRawOreEndSize = builder.defineInRange(CONFIG_ORES + "end.hamRawOreSize", 10, 0, 100);
            hamRawOreEndRegionSize = builder.defineInRange(CONFIG_ORES + "end.hamRawOreRegionSize", 8, 0, 100);

            hamRawOreSize = builder.comment("Ham raw metal ore (overworld)")
                    .defineInRange(CONFIG_ORES + "hamRawOreSize", 10, 0, 100);
            hamRawOreRegionSize = builder.defineInRange(CONFIG_ORES + "hamRawOreRegionSize", 8, 0, 100);

            hamCookedOreNetherGen = builder.comment("Ham cooked metal ore (nether)")
                    .define(CONFIG_ORES + "nether.hamCookedOreGen", true);
            hamCookedOreNetherSize = builder.defineInRange(CONFIG_ORES + "nether.hamCookedOreSize", 10, 0, 100);
            hamCookedOreNetherRegionSize = builder.defineInRange(CONFIG_ORES + "nether.hamCookedOreRegionSize", 8, 0, 100);

            hamCookedOreEndGen = builder.comment("Ham cooked metal ore (end)")
                    .define(CONFIG_ORES + "end.hamCookedOreGen", true);
            hamCookedOreEndSize = builder.defineInRange(CONFIG_ORES + "end.hamCookedOreSize", 10, 0, 100);
            hamCookedOreEndRegionSize = builder.defineInRange(CONFIG_ORES + "end.hamCookedOreRegionSize", 8, 0, 100);

            hamCookedOreSize = builder.comment("Ham cooked metal ore (overworld)")
                    .defineInRange(CONFIG_ORES + "hamCookedOreSize", 10, 0, 100);
            hamCookedOreRegionSize = builder.defineInRange(CONFIG_ORES + "hamCookedOreRegionSize", 8, 0, 100);

            /// Mobs ///
            builder.comment("\nCheeseMod mob settings");

            cheeseFoodieWeight = builder.comment("Cheese foodie weight")
                    .defineInRange(CONFIG_MOBS + "cheeseFoodieWeight", 100, 0, 100);
            cheeseFoodieMin = builder.comment("Cheese foodie min count")
                    .defineInRange(CONFIG_MOBS + "cheeseFoodieMin", 1, 0, 10);
            cheeseFoodieMax = builder.comment("Cheese foodie max count")
                    .defineInRange(CONFIG_MOBS + "cheeseFoodieMax", 5, 1, 10);

            grilledCheeseFoodieWeight = builder.comment("Grilled cheese foodie weight")
                    .defineInRange(CONFIG_MOBS + "grilledCheeseFoodieWeight", 100, 0, 100);
            grilledCheeseFoodieMin = builder.comment("Grilled cheese foodie min count")
                    .defineInRange(CONFIG_MOBS + "grilledCheeseFoodieMin", 1, 0, 10);
            grilledCheeseFoodieMax = builder.comment("Grilled cheese foodie max count")
                    .defineInRange(CONFIG_MOBS + "grilledCheeseFoodieMax", 5, 1, 10);

            hamRawFoodieWeight = builder.comment("Ham raw foodie weight")
                    .defineInRange(CONFIG_MOBS + "hamRawFoodieWeight", 100, 0, 100);
            hamRawFoodieMin = builder.comment("Ham raw foodie min count")
                    .defineInRange(CONFIG_MOBS + "hamRawFoodieMin", 1, 0, 10);
            hamRawFoodieMax = builder.comment("Ham raw foodie max count")
                    .defineInRange(CONFIG_MOBS + "hamRawFoodieMax", 5, 1, 10);

            hamCookedFoodieWeight = builder.comment("Ham cooked foodie weight")
                    .defineInRange(CONFIG_MOBS + "hamCookedFoodieWeight", 100, 0, 100);
            hamCookedFoodieMin = builder.comment("Ham cooked foodie min count")
                    .defineInRange(CONFIG_MOBS + "hamCookedFoodieMin", 1, 0, 10);
            hamCookedFoodieMax = builder.comment("Ham cooked foodie max count")
                    .defineInRange(CONFIG_MOBS + "hamCookedFoodieMax", 5, 1, 10);
        }
    }
}
