package coffeecatrailway.coffeecheese.compat.registrate;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

/**
 * @author CoffeeCatRailway
 * Created: 24/04/2020
 */
public class ContainerBuilder<T extends Container, P> extends AbstractBuilder<ContainerType<?>, ContainerType<T>, P, ContainerBuilder<T, P>> {

    private final ContainerType.IFactory<T> factory;

    public ContainerBuilder(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback, ContainerType.IFactory<T> factory) {
        super(owner, parent, name, callback, ContainerType.class);
        this.factory = factory;
    }

    @Override
    protected ContainerType<T> createEntry() {
        return new ContainerType<>(factory);
    }
}
