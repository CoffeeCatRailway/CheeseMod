package coffeecatrailway.coffeecheese.common.world.biome;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

/**
 * @author CoffeeCatRailway
 * Created: 15/02/2020
 */
public class FoodOceanBiome extends Biome implements IHasFeatures {

    private int grassColor;
    private BlockState surface;
    private BlockState underWater;

    public FoodOceanBiome(int waterColor, int waterFogColor, int grassColor, BlockState surface, BlockState underWater) {
        super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(surface, Blocks.DIRT.getDefaultState(), Blocks.SAND.getDefaultState()))
                .precipitation(Biome.RainType.RAIN).category(Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.5F).downfall(0.5F).waterColor(waterColor).waterFogColor(waterFogColor));
        this.grassColor = grassColor;
        this.surface = surface;
        this.underWater = underWater;
    }

    @Override
    public void addFeatures() {
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);

        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.DISK,
                new SphereReplaceConfig(this.underWater, 7, 10, Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.SAND.getDefaultState(), Blocks.GRAVEL.getDefaultState(), this.surface)),
                Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(3)));
    }

    @Override
    public int getGrassColor(BlockPos pos) {
        return grassColor;
    }
}
