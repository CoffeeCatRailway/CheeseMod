package coffeecatrailway.cheesemod.world.dimension;

import coffeecatrailway.cheesemod.core.ModBiomes;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

/**
 * @author CoffeeCatRailway
 * Created: 1/08/2019
 */
public class HamDimension extends FoodDimension {

    public HamDimension(World world, DimensionType type) {
        super(world, type, ModBiomes.HAM_RAW_FOREST, ModBiomes.HAM_RAW_PLAINS, ModBiomes.HAM_COOKED_FOREST, ModBiomes.HAM_COOKED_PLAINS);
    }
}
