package coffeecatrailway.coffeecheese.common.world.biome.layer;

import coffeecatrailway.coffeecheese.common.world.dimension.FoodWorldBiomeProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

/**
 * @author CoffeeCatRailway
 * Created: 15/01/2020
 */
public enum ModLayer implements IAreaTransformer0 {
    INSTANCE;

    @Override
    public int apply(INoiseRandom rand, int p_215735_2_, int p_215735_3_) {
        return Registry.BIOME.getId(FoodWorldBiomeProvider.BIOMES[rand.random(FoodWorldBiomeProvider.BIOMES.length)]);
    }
}
