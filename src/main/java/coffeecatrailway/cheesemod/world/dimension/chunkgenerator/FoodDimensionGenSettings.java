package coffeecatrailway.cheesemod.world.dimension.chunkgenerator;

import coffeecatrailway.cheesemod.core.ModBiomes;
import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationSettings;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2019
 */
public class FoodDimensionGenSettings extends GenerationSettings {

    private List<Biome> BIOMES = Lists.newArrayList(ModBiomes.CHEESE_FOREST, ModBiomes.GRILLED_CHEESE_FOREST);

    public List<Biome> getBiomes() {
        return BIOMES;
    }

    public void setBiomes(Biome... biomes) {
        this.BIOMES = Lists.newArrayList(biomes);
    }
}
