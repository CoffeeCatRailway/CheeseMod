package coffeecatrailway.cheesemod.world.dimension.biomeprovider;

import coffeecatrailway.cheesemod.core.ModBiomes;
import com.google.common.collect.Lists;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2019
 */
public class FoodBiomeProviderSettings implements IBiomeProviderSettings {

    private List<Biome> biome = Lists.newArrayList(ModBiomes.CHEESE_PLAINS);
    private World world;

    public FoodBiomeProviderSettings setBiomes(Biome... biomes) {
        this.biome = Lists.newArrayList(biomes);
        return this;
    }

    public List<Biome> getBiomes() {
        return this.biome;
    }

    public World getWorld() {
        return world;
    }

    public FoodBiomeProviderSettings setWorld(World world) {
        this.world = world;
        return this;
    }
}
