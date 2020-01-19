package coffeecatrailway.coffeecheese.common.world.biome.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.layer.traits.IC0Transformer;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoffeeCatRailway - Bagu_Chan https://github.com/pentantan
 * Created: 15/01/2020
 */
public class ModBiomeLayer implements IC0Transformer {

    private static final int RIVER = Registry.BIOME.getId(Biomes.RIVER);
    @SuppressWarnings("unchecked")
    private List<BiomeManager.BiomeEntry>[] biomes = new ArrayList[BiomeManager.BiomeType.values().length];
    private final OverworldGenSettings settings;

    public ModBiomeLayer(WorldType worldType, OverworldGenSettings settings) {
        for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values()) {
            int idx = type.ordinal();

            if (biomes[idx] == null)
                biomes[idx] = new java.util.ArrayList<BiomeManager.BiomeEntry>();
        }

        if (worldType == WorldType.DEFAULT_1_1)
            this.settings = null;
        else
            this.settings = settings;
    }

    public int apply(INoiseRandom context, int value) {
        if (this.settings != null && this.settings.getBiomeId() >= 0) {
            return this.settings.getBiomeId();
        } else {
            value = value & -3841;
            return value;

        }
    }

    protected static boolean isRiver(int biome) {
        return biome == RIVER;
    }
}
