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
        super(world, type, ModBiomes.CHEESE_FOREST_DIM, ModBiomes.CHEESE_PLAINS_DIM, ModBiomes.GRILLED_CHEESE_FOREST_DIM, ModBiomes.GRILLED_CHEESE_PLAINS_DIM);
    }
}
