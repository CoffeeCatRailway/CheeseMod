package coffeecatrailway.coffeecheese.common.world.feature;

import coffeecatrailway.coffeecheese.registry.ModBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraftforge.common.IPlantable;

import java.util.List;

/**
 * @author CoffeeCatRailway - Androsa https://github.com/Andromander
 * Created: 4/03/2020
 */
public class FoodTreeFeatureConfig extends BaseTreeFeatureConfig {

    public FoodTreeFeatureConfig(BlockStateProvider trunk, BlockStateProvider leaves, List<TreeDecorator> decorators, int height) {
        super(trunk, leaves, decorators, height);
    }

    @Override
    protected FoodTreeFeatureConfig setSapling(IPlantable value) {
        super.setSapling(value);
        return this;
    }

    public static <T> FoodTreeFeatureConfig deserialize(Dynamic<T> data) {
        BaseTreeFeatureConfig config = BaseTreeFeatureConfig.deserialize(data);
        return new FoodTreeFeatureConfig(config.trunkProvider, config.leavesProvider, config.decorators, config.baseHeight);
    }

    public static <T> FoodTreeFeatureConfig deserializeCheese(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.CHEESE_SAPLING.get());
    }

    public static <T> FoodTreeFeatureConfig deserializeGrilledCheese(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.GRILLED_CHEESE_SAPLING.get());
    }

    public static <T> FoodTreeFeatureConfig deserializeHamRaw(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.HAM_RAW_SAPLING.get());
    }

    public static <T> FoodTreeFeatureConfig deserializeHamCooked(Dynamic<T> data) {
        return deserialize(data).setSapling(ModBlocks.HAM_COOKED_SAPLING.get());
    }

    public static class Builder extends BaseTreeFeatureConfig.Builder {
        private List<TreeDecorator> decorators = ImmutableList.of();
        private int height;

        public Builder(BlockStateProvider trunk, BlockStateProvider leaves) {
            super(trunk, leaves);
        }

        @Override
        public FoodTreeFeatureConfig.Builder baseHeight(int height) {
            this.height = height;
            return this;
        }

        @Override
        public FoodTreeFeatureConfig.Builder setSapling(IPlantable sapling) {
            super.setSapling(sapling);
            return this;
        }

        @Override
        public FoodTreeFeatureConfig build() {
            return new FoodTreeFeatureConfig(trunkProvider, leavesProvider, decorators, height);
        }
    }
}
