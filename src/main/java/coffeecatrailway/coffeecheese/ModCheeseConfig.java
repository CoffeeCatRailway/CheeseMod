package coffeecatrailway.coffeecheese;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * @author CoffeeCatRailway
 * Created: 22/12/2019
 */
public class ModCheeseConfig {

    private static final String config = CheeseMod.MOD_ID + ".";
    private static final String configModifier = config + "modifier.";
    private static final String configBiome = config + "biome.";

    public static ForgeConfigSpec.DoubleValue cheeseSuitScale;
    public static ForgeConfigSpec.DoubleValue cheeseSuitBindingScale;

    public static ForgeConfigSpec.DoubleValue hamSuitScale;
    public static ForgeConfigSpec.DoubleValue hamSuitBindingScale;

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
        }
    }

    public static ForgeConfigSpec.BooleanValue stickyFoodBlock;

    public static ForgeConfigSpec.DoubleValue grilledFoodMultiplier;
    public static ForgeConfigSpec.IntValue grillSpeed;
    public static ForgeConfigSpec.IntValue melterSpeed;

    public static ForgeConfigSpec.IntValue cheeseBiomeWeight;

    public static ForgeConfigSpec.IntValue grilledCheeseBiomeWeight;

    public static ForgeConfigSpec.IntValue hamRawBiomeWeight;

    public static ForgeConfigSpec.IntValue hamCookedBiomeWeight;

    public static class ServerConfig {

        public ServerConfig(ForgeConfigSpec.Builder builder) {
            stickyFoodBlock = builder.comment("If true all food blocks (cheese, grilled cheese & ham raw or cooked) will act like slime blocks when pushed by a piston")
                    .define(config + "stickyFoodBlock", true);

            builder.comment("\nCheeseMod modifier settings");

            grilledFoodMultiplier = builder.comment("The amount of how much the saturation changes when grilled")
                    .defineInRange(configModifier + "grilledSaturation", 1.5d, 0.5d, 10.0d);

            int minSpeed = 1;
            int maxSpeed = 20;
            grillSpeed = builder.comment("Speed multiplier for the grill")
                    .defineInRange(configModifier + "grillSpeed", 5, minSpeed, maxSpeed);

            melterSpeed = builder.comment("Speed multiplier for the melter")
                    .defineInRange(configModifier + "melterSpeed", 4, minSpeed, maxSpeed);

            builder.comment("\nCheeseMod biome settings");
            int minWeight = 0;
            int maxWeight = 1000;

            cheeseBiomeWeight = builder.comment("Cheese weight")
                    .defineInRange(configBiome + "cheeseBiomeWeight", 10, minWeight, maxWeight);

            grilledCheeseBiomeWeight = builder.comment("Grilled cheese weight")
                    .defineInRange(configBiome + "grilledCheeseBiomeWeight", 7, minWeight, maxWeight);

            hamRawBiomeWeight = builder.comment("Ham raw weight")
                    .defineInRange(configBiome + "hamRawBiomeWeight", 10, minWeight, maxWeight);

            hamCookedBiomeWeight = builder.comment("Ham cooked weight")
                    .defineInRange(configBiome + "hamCookedBiomeWeight", 7, minWeight, maxWeight);
        }
    }
}
