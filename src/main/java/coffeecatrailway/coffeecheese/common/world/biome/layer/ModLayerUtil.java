package coffeecatrailway.coffeecheese.common.world.biome.layer;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

/**
 * @author CoffeeCatRailway - Bagu_Chan https://github.com/pentantan
 * Created: 15/01/2020
 */
public class ModLayerUtil {

    private List<BiomeManager.BiomeEntry>[] biomes = new ArrayList[BiomeManager.BiomeType.values().length];

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> repeat(long seed, IAreaTransformer1 parent, IAreaFactory<T> areaFactory, int count, LongFunction<C> contextFactory) {
        IAreaFactory<T> iareafactory = areaFactory;

        for (int i = 0; i < count; ++i)
            iareafactory = parent.apply(contextFactory.apply(seed + (long) i), iareafactory);

        return iareafactory;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> ImmutableList<IAreaFactory<T>> buildProcedure(WorldType type, OverworldGenSettings settings, LongFunction<C> contextFactory) {
        IAreaFactory<T> areaFactory = ModLayer.INSTANCE.apply(contextFactory.apply(1L));
        areaFactory = ZoomLayer.FUZZY.apply(contextFactory.apply(2000L), areaFactory);
        areaFactory = AddIslandLayer.INSTANCE.apply(contextFactory.apply(1L), areaFactory);
        areaFactory = ZoomLayer.NORMAL.apply(contextFactory.apply(2001L), areaFactory);
        areaFactory = AddIslandLayer.INSTANCE.apply(contextFactory.apply(2L), areaFactory);
        areaFactory = AddIslandLayer.INSTANCE.apply(contextFactory.apply(50L), areaFactory);
        areaFactory = AddIslandLayer.INSTANCE.apply(contextFactory.apply(70L), areaFactory);
        areaFactory = AddIslandLayer.INSTANCE.apply(contextFactory.apply(3L), areaFactory);
        areaFactory = EdgeLayer.CoolWarm.INSTANCE.apply(contextFactory.apply(2L), areaFactory);
        areaFactory = EdgeLayer.HeatIce.INSTANCE.apply(contextFactory.apply(2L), areaFactory);
        areaFactory = EdgeLayer.Special.INSTANCE.apply(contextFactory.apply(3L), areaFactory);
        areaFactory = ZoomLayer.NORMAL.apply(contextFactory.apply(2002L), areaFactory);
        areaFactory = ZoomLayer.NORMAL.apply(contextFactory.apply(2003L), areaFactory);
        areaFactory = repeat(1000L, ZoomLayer.NORMAL, areaFactory, 0, contextFactory);
        int i = 4;
        int j = i;

        if (type == WorldType.LARGE_BIOMES)
            i = 6;

        i = getModdedBiomeSize(type, i);

        IAreaFactory<T> lvt_7_1_ = repeat(1000L, ZoomLayer.NORMAL, areaFactory, 0, contextFactory);
        lvt_7_1_ = StartRiverLayer.INSTANCE.apply((IExtendedNoiseRandom) contextFactory.apply(100L), lvt_7_1_);
        IAreaFactory<T> lvt_8_1_ = getBiomeLayer(type, areaFactory, settings, contextFactory);
        IAreaFactory<T> lvt_9_1_ = repeat(1000L, ZoomLayer.NORMAL, lvt_7_1_, 2, contextFactory);
        lvt_8_1_ = HillsLayer.INSTANCE.apply((IExtendedNoiseRandom) contextFactory.apply(1000L), lvt_8_1_, lvt_9_1_);
        lvt_7_1_ = repeat(1000L, ZoomLayer.NORMAL, lvt_7_1_, 2, contextFactory);
        lvt_7_1_ = repeat(1000L, ZoomLayer.NORMAL, lvt_7_1_, j, contextFactory);
        lvt_7_1_ = RiverLayer.INSTANCE.apply((IExtendedNoiseRandom) contextFactory.apply(1L), lvt_7_1_);
        lvt_7_1_ = SmoothLayer.INSTANCE.apply((IExtendedNoiseRandom) contextFactory.apply(1000L), lvt_7_1_);

        for (int k = 0; k < i; ++k) {
            lvt_8_1_ = ZoomLayer.NORMAL.apply((IExtendedNoiseRandom) contextFactory.apply((long) (1000 + k)), lvt_8_1_);
            if (k == 0) {
                lvt_8_1_ = AddIslandLayer.INSTANCE.apply((IExtendedNoiseRandom) contextFactory.apply(3L), lvt_8_1_);
            }

            if (k == 1 || i == 1) {
                lvt_8_1_ = ShoreLayer.INSTANCE.apply((IExtendedNoiseRandom) contextFactory.apply(1000L), lvt_8_1_);
            }
        }

        lvt_8_1_ = SmoothLayer.INSTANCE.apply((IExtendedNoiseRandom) contextFactory.apply(1000L), lvt_8_1_);
        lvt_8_1_ = MixRiverLayer.INSTANCE.apply((IExtendedNoiseRandom) contextFactory.apply(100L), lvt_8_1_, lvt_7_1_);
        IAreaFactory<T> iareafactory5 = ZoomLayer.NORMAL.apply(contextFactory.apply(10L), lvt_8_1_);
        return ImmutableList.of(lvt_8_1_, iareafactory5, lvt_8_1_);
    }

    private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> getBiomeLayer(WorldType worldType, IAreaFactory<T> parentLayer, OverworldGenSettings settings, LongFunction<C> contextFactory) {
        parentLayer = (new ModBiomeLayer(worldType, settings)).apply(contextFactory.apply(200L), parentLayer);
        parentLayer = LayerUtil.repeat(1000L, ZoomLayer.NORMAL, parentLayer, 2, contextFactory);
        parentLayer = EdgeBiomeLayer.INSTANCE.apply(contextFactory.apply(1000L), parentLayer);
        return parentLayer;
    }

    public static Layer[] buildProcedure(long seed, WorldType typeIn, OverworldGenSettings settings) {
        int i = 25;
        ImmutableList<IAreaFactory<LazyArea>> immutablelist = buildProcedure(typeIn, settings, (seedModifier) -> {
            return new LazyAreaLayerContext(25, seed, seedModifier);
        });
        Layer layer = new Layer(immutablelist.get(0));
        Layer layer1 = new Layer(immutablelist.get(1));
        Layer layer2 = new Layer(immutablelist.get(2));
        return new Layer[]{layer, layer1, layer2};
    }

    public static int getModdedBiomeSize(WorldType worldType, int original) {
        WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
        MinecraftForge.EVENT_BUS.post(event);
        return event.getNewSize();
    }
}
