package coffeecatrailway.coffeecheese.common.world.biome.layer;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;

import java.util.function.LongFunction;

/**
 * @author CoffeeCatRailway - Andromander https://github.com/Andromander/Gaia-Dimension/blob/1.15.x/src/main/java/androsa/gaiadimension/world/layer/GaiaLayerUtil.java
 * Created: 15/01/2020
 */
public class ModLayerUtil {

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> contextFactory) {
        IAreaFactory<T> biomes = ModLayer.INSTANCE.apply(contextFactory.apply(1L));

        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1000), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1001), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1004), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1005), biomes);

        biomes = LayerUtil.repeat(1000L, ZoomLayer.NORMAL, biomes, 1, contextFactory);

        IAreaFactory<T> riverLayer = RiverLayer.INSTANCE.apply(contextFactory.apply(1L), biomes);
        riverLayer = SmoothLayer.INSTANCE.apply(contextFactory.apply(7000L), riverLayer);
        biomes = MixRiverLayer.INSTANCE.apply(contextFactory.apply(100L), biomes, riverLayer);

        return biomes;
    }

    public static Layer makeLayers(long seed) {
        IAreaFactory<LazyArea> areaFactory = makeLayers((seedModifier) -> new LazyAreaLayerContext(25, seed, seedModifier));
        return new Layer(areaFactory);
    }
}
