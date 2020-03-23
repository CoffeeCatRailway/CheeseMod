package coffeecatrailway.coffeecheese.common.world.feature.tree;

import coffeecatrailway.coffeecheese.common.world.feature.FoodTreeFeatureConfig;
import coffeecatrailway.coffeecheese.registry.ModEntities;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public abstract class TreeFeature extends AbstractTreeFeature<FoodTreeFeatureConfig> {

    public TreeFeature(Function<Dynamic<?>, ? extends FoodTreeFeatureConfig> function) {
        super(function);
    }

    protected boolean isSoil(IWorldGenerationBaseReader reader, BlockPos pos) {
        return reader.hasBlockState(pos, ModEntities::canSpawn);
    }
}
