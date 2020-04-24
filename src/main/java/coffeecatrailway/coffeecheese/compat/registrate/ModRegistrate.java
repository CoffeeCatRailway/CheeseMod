package coffeecatrailway.coffeecheese.compat.registrate;

import coffeecatrailway.coffeecheese.ModItemGroups;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * @author CoffeeCatRailway
 * Created: 23/04/2020
 */
public class ModRegistrate extends AbstractRegistrate<ModRegistrate> {

    public ModRegistrate(String modid) {
        super(modid);
    }

    public static ModRegistrate create(String modid, IEventBus eventBus) {
        return new ModRegistrate(modid).registerEventListeners(eventBus).itemGroup(() -> ModItemGroups.GROUP_ALL);
    }

    // Biomes
    public <T extends Biome> BiomeBuilder<T, ModRegistrate> biome(NonNullFunction<Biome.Builder, T> factory) {
        return this.biome(this, factory);
    }

    public <T extends Biome> BiomeBuilder<T, ModRegistrate> biome(String name, NonNullFunction<Biome.Builder, T> factory) {
        return this.biome(this, name, factory);
    }

    public <T extends Biome, P> BiomeBuilder<T, P> biome(P parent, NonNullFunction<Biome.Builder, T> factory) {
        return this.biome(parent, this.currentName(), factory);
    }

    public <T extends Biome, P> BiomeBuilder<T, P> biome(P parent, String name, NonNullFunction<Biome.Builder, T> factory) {
        return this.entry(name, callback -> new BiomeBuilder<>(this, parent, name, callback, factory));
    }

    // Containers
    public <T extends Container> ContainerBuilder<T, ModRegistrate> containerType(String name, ContainerType.IFactory<T> factory) {
        return containerType(this, name, factory);
    }

    public <T extends Container> ContainerBuilder<T, ModRegistrate> containerType(ContainerType.IFactory<T> factory) {
        return containerType(this, factory);
    }

    public <T extends Container, P> ContainerBuilder<T, P> containerType(P parent, ContainerType.IFactory<T> factory) {
        return containerType(parent, this.currentName(), factory);
    }

    public <T extends Container, P> ContainerBuilder<T, P> containerType(P parent, String name, ContainerType.IFactory<T> factory) {
        return this.entry(name, callback -> new ContainerBuilder<>(this, parent, name, callback, factory));
    }

    // Features
    public <T extends Feature<?>> FeatureBuilder<T, ModRegistrate> feature(String name, NonNullSupplier<T> factory) {
        return feature(this, name, factory);
    }

    public <T extends Feature<?>> FeatureBuilder<T, ModRegistrate> feature(NonNullSupplier<T> factory) {
        return feature(this, factory);
    }

    public <T extends Feature<?>, P> FeatureBuilder<T, P> feature(P parent, NonNullSupplier<T> factory) {
        return feature(parent, this.currentName(), factory);
    }

    public <T extends Feature<?>, P> FeatureBuilder<T, P> feature(P parent, String name, NonNullSupplier<T> factory) {
        return this.entry(name, callback -> new FeatureBuilder<>(this, parent, name, callback, factory));
    }
}
