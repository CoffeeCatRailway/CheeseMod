package coffeecatrailway.cheesemod.world.dimensions;

import coffeecatrailway.cheesemod.core.registries.ModBiomes;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

/**
 * @author CoffeeCatRailway
 * Created: 1/08/2019
 */
public class CheeseDimension extends FoodDimension {

    public CheeseDimension(World world, DimensionType type) {
        super(world, type, ModBiomes.CHEESE_FOREST, ModBiomes.CHEESE_PLAINS, ModBiomes.GRILLED_CHEESE_FOREST, ModBiomes.GRILLED_CHEESE_PLAINS);
    }
}
