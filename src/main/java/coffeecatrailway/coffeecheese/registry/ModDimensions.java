package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.world.dimension.FoodWorldDimension;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiFunction;

/**
 * @author CoffeeCatRailway
 * Created: 15/01/2020
 */
public class ModDimensions {

    public static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, CheeseMod.MOD_ID);

    public static final RegistryObject<ModDimension> FOOD_WORLD = DIMENSIONS.register("foodworld", () -> new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return FoodWorldDimension::new;
        }
    });
    public static DimensionType FOOD_WORLD_TYPE;
}
