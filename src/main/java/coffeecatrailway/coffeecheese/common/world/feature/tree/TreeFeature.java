package coffeecatrailway.coffeecheese.common.world.feature.tree;

import coffeecatrailway.coffeecheese.common.block.FoodGrassBlock;
import coffeecatrailway.coffeecheese.registry.ModEntities;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.GrassPathBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public abstract class TreeFeature<T extends IFeatureConfig> extends AbstractTreeFeature<T> {

    public TreeFeature(Function<Dynamic<?>, ? extends T> dynamicFunction, boolean doBlockNofityOnPlace) {
        super(dynamicFunction, doBlockNofityOnPlace);
    }

    protected boolean isSoil(IWorldGenerationBaseReader reader, BlockPos pos) {
        return reader.hasBlockState(pos, ModEntities::canSpawn);
    }
}
