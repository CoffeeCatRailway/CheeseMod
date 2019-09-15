package coffeecatrailway.cheesemod.common.world.biome;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class FoodBiome extends Biome {

    private int grassColor;

    public FoodBiome(int waterColor, int waterFogColor, int grassColor, BlockState surface, String parent) {
        this(waterColor, waterFogColor, grassColor, surface, null, null, parent);
    }

    public FoodBiome(int waterColor, int waterFogColor, int grassColor, BlockState surface, Feature mainTree, Feature secondTree, String parent) {
        super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(surface, SurfaceBuilder.DIRT, SurfaceBuilder.SAND)).precipitation(Biome.RainType.RAIN)
                .category((mainTree != null && secondTree != null) ? Biome.Category.FOREST : Category.PLAINS).depth(0.1f).scale(0.2f).temperature(0.7f).downfall(0.8f)
                .waterColor(waterColor).waterFogColor(waterFogColor).parent(parent));
        this.grassColor = grassColor;

        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        this.addStructure(Feature.VILLAGE, new VillageConfig("village/plains/town_centers", 10));
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addExtraEmeraldOre(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addDefaultFlowers(this);
        DefaultBiomeFeatures.addGrass(this);
        DefaultBiomeFeatures.addExtraDefaultFlowers(this);

        if (mainTree != null && secondTree != null)
            this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(
                            new Feature[]{secondTree}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.2F}, mainTree, IFeatureConfig.NO_FEATURE_CONFIG),
                    Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(10, 0.1f, 1)));

        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
    }

    public void addSpawn(EntityClassification type, Biome.SpawnListEntry spawnListEntry) {
        super.addSpawn(type, spawnListEntry);
    }

    @Override
    public int getGrassColor(BlockPos pos) {
        return grassColor;
    }

    public static String getParent(FoodBiome foodBiome) {
        return foodBiome.getRegistryName().getPath();
    }
}
