package coffeecatrailway.coffeecheese;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

/**
 * @author CoffeeCatRailway
 * Created: 20/07/2019
 */
@Mod.EventBusSubscriber(modid = CheeseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfig {

    public static final String FILE = CheeseMod.MOD_ID + ".toml";

    // Config
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    /// Properties ///
    public static ForgeConfigSpec.BooleanValue playerLoggedIn;
    public static ForgeConfigSpec.BooleanValue stickyFoodBlock;

    static {
        playerLoggedIn = BUILDER.comment("If false it means the player has not logged in before")
                .define(CheeseMod.MOD_ID + ".loggedIn", false);

        stickyFoodBlock = BUILDER.comment("If true all food blocks (cheese, grilled cheese & ham raw or cooked) will act like slime blocks when pushed by a piston")
                .define(CheeseMod.MOD_ID + ".stickyFoodBlock", true);
    }

    public static final ModifiersConfig MODIFIERS = new ModifiersConfig(BUILDER);
    public static final BiomeConfig BIOME = new BiomeConfig(BUILDER);

    public static final ForgeConfigSpec CONFIG = BUILDER.build();

    public static class ModifiersConfig {

        public ForgeConfigSpec.DoubleValue grilledFoodMultiplier;
        public ForgeConfigSpec.IntValue grillSpeed;
        public ForgeConfigSpec.IntValue melterSpeed;

        public ForgeConfigSpec.DoubleValue cheeseSuitScale;
        public ForgeConfigSpec.DoubleValue cheeseSuitBindingScale;

        public ForgeConfigSpec.DoubleValue hamSuitScale;
        public ForgeConfigSpec.DoubleValue hamSuitBindingScale;

        public ModifiersConfig(ForgeConfigSpec.Builder config) {
            config.comment("CheeseMod modifier settings");

            grilledFoodMultiplier = config.comment("The amount of how much the saturation changes when grilled")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.grilledSaturation", 1.5d, 0.5d, 10.0d);

            int minSpeed = 1;
            int maxSpeed = 20;
            grillSpeed = config.comment("Speed multiplier for the grill")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.grillSpeed", 5, minSpeed, maxSpeed);

            melterSpeed = config.comment("Speed multiplier for the melter")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.melterSpeed", 4, minSpeed, maxSpeed);

            double minScale = (double) 0.0f;
            double maxScale = (double) 10.0f;
            cheeseSuitScale = config.comment("Cheese suit scale")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.cheeseSuitScale", 0.4d, minScale, maxScale);

            cheeseSuitBindingScale = config.comment("Cheese suit binding scale")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.cheeseSuitBindingScale", 0.3d, minScale, maxScale);

            hamSuitScale = config.comment("Ham suit scale")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.hamSuitScale", 0.6d, minScale, maxScale);

            hamSuitBindingScale = config.comment("Ham suit binding scale")
                    .defineInRange(CheeseMod.MOD_ID + ".modifier.hamSuitBindingScale", 0.3d, minScale, maxScale);
        }
    }

    public static class BiomeConfig {

        public ForgeConfigSpec.IntValue pineappleSpawnCount;
        public ForgeConfigSpec.DoubleValue pineappleSpawnChance;

        public ForgeConfigSpec.BooleanValue spawnCheeseVillager;
        public ForgeConfigSpec.IntValue cheeseVillagerWeight;

        public ForgeConfigSpec.BooleanValue spawnHamVillager;
        public ForgeConfigSpec.IntValue hamVillagerWeight;

        public ForgeConfigSpec.BooleanValue spawnCheeseGolem;
        public ForgeConfigSpec.IntValue cheeseGolemWeight;

        public ForgeConfigSpec.BooleanValue spawnHamGolem;
        public ForgeConfigSpec.IntValue hamGolemWeight;

        public BiomeConfig(ForgeConfigSpec.Builder config) {
            config.comment("CheeseMod biome settings");

            pineappleSpawnCount = config.comment("The amount of pineapples that spawn together")
                    .defineInRange(CheeseMod.MOD_ID + ".biome.pineappleSpawnChance", 1, 0, 10);
            pineappleSpawnChance = config.comment("If you like pineapples don't set the value to 0!")
                    .defineInRange(CheeseMod.MOD_ID + ".biome.pineappleSpawnCount", 0.025, 0.0, 1.0);

            int minWeight = 1;
            int maxWeight = 20;

            config.comment("- Food villager");

            spawnCheeseVillager = config.comment("Allows the cheese villager to spawn in the cheese biome")
                    .define(CheeseMod.MOD_ID + ".biome.spawnCheeseVillager", true);

            cheeseVillagerWeight = config.comment("The weight/rate of cheese villager spawns")
                    .defineInRange(CheeseMod.MOD_ID + ".biome.cheeseVillagerWeight", 1, minWeight, maxWeight);

            spawnHamVillager = config.comment("Allows the ham villager to spawn in the ham biome")
                    .define(CheeseMod.MOD_ID + ".biome.spawnHamVillager", true);

            hamVillagerWeight = config.comment("The weight/rate of ham villager spawns")
                    .defineInRange(CheeseMod.MOD_ID + ".biome.hamVillagerWeight", 1, minWeight, maxWeight);

            config.comment("- Food golem");

            spawnCheeseGolem = config.comment("Allows the cheese golem to spawn in the cheese biome")
                    .define(CheeseMod.MOD_ID + ".biome.spawnCheeseGolem", true);

            cheeseGolemWeight = config.comment("The weight/rate of cheese golem spawns")
                    .defineInRange(CheeseMod.MOD_ID + ".biome.cheeseGolemWeight", 1, minWeight, maxWeight);

            spawnHamGolem = config.comment("Allows the ham golem to spawn in the ham biome")
                    .define(CheeseMod.MOD_ID + ".biome.spawnHamGolem", true);

            hamGolemWeight = config.comment("The weight/rate of ham golem spawns")
                    .defineInRange(CheeseMod.MOD_ID + ".biome.hamGolemWeight", 1, minWeight, maxWeight);
        }
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CheeseMod.LOGGER.info("Config: " + path);
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        CheeseMod.LOGGER.info("Config built: " + path);
        file.load();
        CheeseMod.LOGGER.info("Loaded Config: " + path);
        config.setConfig(file);
    }
}
