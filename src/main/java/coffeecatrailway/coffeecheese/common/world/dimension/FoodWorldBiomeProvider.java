package coffeecatrailway.coffeecheese.common.world.dimension;

import coffeecatrailway.coffeecheese.common.world.biome.layer.ModLayerUtil;
import coffeecatrailway.coffeecheese.registry.ModBiomes;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;

import java.util.Set;

/**
 * @author CoffeeCatRailway - Bagu_Chan https://github.com/pentantan
 * Created: 15/01/2020
 */
public class FoodWorldBiomeProvider extends BiomeProvider {

    private final Layer genBiomes;

    public static final Set<Biome> BIOMES = ImmutableSet.of(
            ModBiomes.CHEESE_FOREST.get(), ModBiomes.CHEESE_FOREST_HILLS.get(), ModBiomes.CHEESE_PLAINS.get(),
            ModBiomes.GRILLED_CHEESE_FOREST.get(), ModBiomes.GRILLED_CHEESE_FOREST_HILLS.get(), ModBiomes.GRILLED_CHEESE_PLAINS.get(),
            ModBiomes.HAM_RAW_FOREST.get(), ModBiomes.HAM_RAW_FOREST_HILLS.get(), ModBiomes.HAM_RAW_PLAINS.get(),
            ModBiomes.HAM_COOKED_FOREST.get(), ModBiomes.HAM_COOKED_FOREST_HILLS.get(), ModBiomes.HAM_COOKED_PLAINS.get()
    );

    static {
        BIOMES_TO_SPAWN_IN.clear();
        BIOMES_TO_SPAWN_IN.addAll(BIOMES);
    }

    public FoodWorldBiomeProvider(World world) {
        super(BIOMES);
        this.genBiomes = ModLayerUtil.makeLayers(world.getWorldInfo().getSeed());
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return this.genBiomes.func_215738_a(x, z);
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (this.topBlocksCache.isEmpty())
            for (Biome biome : BIOMES)
                this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
        return this.topBlocksCache;
    }
}
