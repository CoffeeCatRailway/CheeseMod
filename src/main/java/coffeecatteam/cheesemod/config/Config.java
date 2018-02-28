package coffeecatteam.cheesemod.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import coffeecatteam.cheesemod.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {

	private static Configuration config;

    public static final String CATEGORY_OTHER = "other";
	private static boolean playerHasLoggedIn;
	
    private static int grillSpeedMultiplier;
    private static int crackerMakerSpeedMultiplier;

	public static final String CATEGORY_BIOME_SPAWNING = "biome_spawning";
	private static boolean entityCheeseManSpawn;
	private static int entityCheeseManSpawnWeight;

	private static boolean entityCheeseGolemSpawn;
	private static int entityCheeseGolemSpawnWeight;

	private static boolean entityHamManSpawn;
	private static int entityHamManSpawnWeight;

	private static boolean entityHamGolemSpawn;
	private static int entityHamGolemSpawnWeight;

	public static final String CATEGORY_FOOD_SUIT_SCALE = "food_suit_scale";
	private static float cheese_suit_scale;
	private static float cheese_suit_binding_scale;

	private static float ham_suit_scale;
	private static float ham_suit_binding_scale;

	public static void load(String file) {
		File configFile = new File(Loader.instance().getConfigDir(), file + ".cfg");
		config = new Configuration(configFile);
		init();
	}

	public static Configuration getConfig(String file) {
		load(file);
		return config;
	}

	private static void init() {
		/*
		 * CATEGORY_GRILL
		 */
        playerHasLoggedIn = config.get(CATEGORY_OTHER, "Player Has Logged In", false, "If false it means the player has not logged in before.").getBoolean();
        
        int foodMakerMin = 1;
        int foodMakerMax = 20;
        grillSpeedMultiplier = config.get(CATEGORY_OTHER, "Grill Speed Multiplier", 5, "Speed multiplier for the grill.").setMinValue(foodMakerMin).setMaxValue(foodMakerMax).getInt();
        crackerMakerSpeedMultiplier = config.get(CATEGORY_OTHER, "Cracker Maker Speed Multiplier", 5, "Speed multiplier for the cracker maker.").setMinValue(foodMakerMin).setMaxValue(foodMakerMax).getInt();
        
		/*
		 * CATEGORY_BIOME_SPAWNING
		 */
		int minWeight = 1;
		int maxWeight = 20;

		entityCheeseManSpawn = config.get(CATEGORY_BIOME_SPAWNING, "Chesee Man", true,
				"Allows entity cheese man to spawn in the cheese biome.").getBoolean();
		entityCheeseManSpawnWeight = config.get(CATEGORY_BIOME_SPAWNING, "Chesee Man Spawn Weight", 1,
				"The weight/rate of witch the entity spawns.", minWeight, maxWeight).getInt();

		entityCheeseGolemSpawn = config.get(CATEGORY_BIOME_SPAWNING, "Chesee Golem", true,
				"Allows entity cheese golem to spawn in the cheese biome.").getBoolean();
		entityCheeseGolemSpawnWeight = config.get(CATEGORY_BIOME_SPAWNING, "Chesee Golem Spawn Weight", 1,
				"The weight/rate of witch the entity spawns.", minWeight, maxWeight).getInt();

		entityHamManSpawn = config
				.get(CATEGORY_BIOME_SPAWNING, "Ham Man", true, "Allows entity ham man to spawn in the ham biome.")
				.getBoolean();
		entityHamManSpawnWeight = config.get(CATEGORY_BIOME_SPAWNING, "Ham Man Spawn Weight", 1,
				"The weight/rate of witch the entity spawns.", minWeight, maxWeight).getInt();

		entityHamGolemSpawn = config.get(CATEGORY_BIOME_SPAWNING, "Ham Golem", true,
				"Allows entity cheese golem to spawn in the ham biome.").getBoolean();
		entityHamGolemSpawnWeight = config.get(CATEGORY_BIOME_SPAWNING, "Ham Golem Spawn Weight", 1,
				"The weight/rate of witch the entity spawns.", minWeight, maxWeight).getInt();

		/*
		 * CATEGORY_FOOD_SUIT_SCALE
		 */
		double minValue = (double) 0.0f;
		double maxValue = (double) 10.0f;

		cheese_suit_scale = (float) config.get(CATEGORY_FOOD_SUIT_SCALE, "Cheese Suit Scale", 0.4D,
				"Determines the size of the cheese suit.", minValue, maxValue).getDouble();

		cheese_suit_binding_scale = (float) config
				.get(CATEGORY_FOOD_SUIT_SCALE, "Cheese Suit Binding Scale", 0.3D,
						"Determines the size of the cheese suit binding (sticks/wood).", minValue, maxValue)
				.getDouble();

		ham_suit_scale = (float) config.get(CATEGORY_FOOD_SUIT_SCALE, "Ham Suit Scale", 0.6D,
				"Determines the size of the ham suit.", minValue, maxValue).getDouble();

		ham_suit_binding_scale = (float) config.get(CATEGORY_FOOD_SUIT_SCALE, "Ham Suit Binding Scale", 0.3D,
				"Determines the size of the ham suit binding (sticks/wood).", minValue, maxValue).getDouble();

		// Set Categorys
		setPropOrder();
	}
	
	private static void setPropOrder() {
		List<String> propOrder = new ArrayList<>();
        config.setCategoryPropertyOrder(CATEGORY_OTHER, propOrder);
		config.setCategoryPropertyOrder(CATEGORY_BIOME_SPAWNING, propOrder);
		config.setCategoryPropertyOrder(CATEGORY_FOOD_SUIT_SCALE, propOrder);

		config.save();
	}

	public static void clientPreInit() {
		MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
	}

	@EventBusSubscriber(modid = Reference.MODID)
	public static class ConfigEventHandler {

		@SubscribeEvent(priority = EventPriority.LOWEST)
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MODID))
				init();
		}
	}

	public static String getConfigLangFormat() {
		return "config." + Reference.MODID + ".";
	}

	// CATEGORY_OTHER
    public static boolean getPlayerHasLoggedIn() {
		return playerHasLoggedIn;
	}
    
    public static int getGrillSpeedMultiplier() {
        return grillSpeedMultiplier;
    }
    
    public static int getCrackerMakerSpeedMultiplier() {
		return crackerMakerSpeedMultiplier;
	}
    
    public static void setPlayerHasLoggedIn(boolean value) {
    	playerHasLoggedIn = config.get(CATEGORY_OTHER, "Player Has Logged In", value, "If false it means the player has not logged in before.").getBoolean();
		setPropOrder();
    }

	// CATEGORY_BIOME_SPAWNING
	public static boolean getEntityCheeseManSpawn() {
		return entityCheeseManSpawn;
	}

	public static int getEntityCheeseManSpawnWeight() {
		return entityCheeseManSpawnWeight;
	}

	public static boolean getEntityCheeseGolemSpawn() {
		return entityCheeseGolemSpawn;
	}

	public static int getEntityCheeseGolemSpawnWeight() {
		return entityCheeseGolemSpawnWeight;
	}

	public static boolean getEntityHamManSpawn() {
		return entityHamManSpawn;
	}

	public static int getEntityHamManSpawnWeight() {
		return entityHamManSpawnWeight;
	}

	public static boolean getEntityHamGolemSpawn() {
		return entityHamGolemSpawn;
	}

	public static int getEntityHamGolemSpawnWeight() {
		return entityHamGolemSpawnWeight;
	}

	// CATEGORY_FOOD_SUIT_SCALE
	public static float getCheeseSuitScale() {
		return cheese_suit_scale;
	}

	public static float getCheeseSuitBindingScale() {
		return cheese_suit_binding_scale;
	}

	public static float getHamSuitScale() {
		return ham_suit_scale;
	}

	public static float getHamSuitBindingScale() {
		return ham_suit_binding_scale;
	}
}
