package coffeecatrailway.coffeecheese.common.world.biome.layer;

import coffeecatrailway.coffeecheese.registry.ModBiomes;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLModIdMappingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

/**
 * @author CoffeeCatRailway - Andromander https://github.com/Andromander/Gaia-Dimension/blob/1.15.x/src/main/java/androsa/gaiadimension/world/layer/GaiaLayerUtil.java
 * Created: 15/01/2020
 */
public class ModLayerUtil {

    private static final List<LazyInt> CACHES = new ArrayList<>();

    protected static final LazyInt RIVER = lazyId(ModBiomes.FOOD_RIVER_BIOME);

    protected static final LazyInt CHEESE_PLAINS = lazyId(ModBiomes.CHEESE_PLAINS);
    protected static final LazyInt GRILLED_CHEESE_PLAINS = lazyId(ModBiomes.GRILLED_CHEESE_PLAINS);
    protected static final LazyInt HAM_RAW_PLAINS = lazyId(ModBiomes.HAM_RAW_PLAINS);
    protected static final LazyInt HAM_COOKED_PLAINS = lazyId(ModBiomes.HAM_COOKED_PLAINS);

    protected static final LazyInt CHEESE_FOREST = lazyId(ModBiomes.CHEESE_FOREST);
    protected static final LazyInt GRILLED_CHEESE_FOREST = lazyId(ModBiomes.GRILLED_CHEESE_FOREST);
    protected static final LazyInt HAM_RAW_FOREST = lazyId(ModBiomes.HAM_RAW_FOREST);
    protected static final LazyInt HAM_COOKED_FOREST = lazyId(ModBiomes.HAM_COOKED_FOREST);

    static LazyInt lazyId(RegistryEntry<? extends Biome> biome) {
        LazyInt lazyInt = new LazyInt(biome.lazyMap(Registry.BIOME::getId));
        CACHES.add(lazyInt);
        return lazyInt;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> contextFactory) {
        IAreaFactory<T> biomes = ModLayer.INSTANCE.apply(contextFactory.apply(1L));

        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1000), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1001), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1004), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1005), biomes);

        biomes = LayerUtil.repeat(1000L, ZoomLayer.NORMAL, biomes, 1, contextFactory);

        IAreaFactory<T> riverLayer = FoodRiverLayer.INSTANCE.apply(contextFactory.apply(1L), biomes);
        riverLayer = SmoothLayer.INSTANCE.apply(contextFactory.apply(7000L), riverLayer);
        biomes = FoodRiverMixLayer.INSTANCE.apply(contextFactory.apply(100L), biomes, riverLayer);

        return biomes;
    }

    public static Layer makeLayers(long seed) {
        IAreaFactory<LazyArea> areaFactory = makeLayers((seedModifier) -> new LazyAreaLayerContext(25, seed, seedModifier));
        return new Layer(areaFactory);
    }

    @SubscribeEvent
    public static void onModIdMapped(FMLModIdMappingEvent event) {
        CACHES.forEach(LazyInt::invalidate);
    }
}
