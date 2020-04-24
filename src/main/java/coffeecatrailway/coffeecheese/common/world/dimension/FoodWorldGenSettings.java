package coffeecatrailway.coffeecheese.common.world.dimension;

import coffeecatrailway.coffeecheese.registry.ModFluids;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationSettings;

/**
 * @author CoffeeCatRailway
 * Created: 24/04/2020
 */
public class FoodWorldGenSettings extends GenerationSettings {

    public static final int SEA_LEVEL = 63;

    public static coffeecatrailway.coffeecheese.common.world.dimension.FoodWorldGenSettings createDefault() {
        coffeecatrailway.coffeecheese.common.world.dimension.FoodWorldGenSettings foodWorldGenSettings = new coffeecatrailway.coffeecheese.common.world.dimension.FoodWorldGenSettings();
        foodWorldGenSettings.setDefaultBlock(Blocks.STONE.getDefaultState());
        foodWorldGenSettings.setDefaultFluid(ModFluids.VINEGAR.get().getStillFluid().getDefaultState().getBlockState());

        return foodWorldGenSettings;
    }

    public int getBedrockFloorHeight() {
        return 0;
    }
}
