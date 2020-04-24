package coffeecatrailway.coffeecheese.common.world.biome;

import coffeecatrailway.coffeecheese.registry.ModBiomes;
import net.minecraft.world.biome.Biome;

/**
 * @author CoffeeCatRailway
 * Created: 24/04/2020
 */
public class FoodRiverBiome extends BaseFoodBiome {

    public FoodRiverBiome(Builder builder) {
        super(builder, ModBiomes.CHEESE_GRASS_COLOR);
    }
}
