package coffeecatrailway.coffeecheese.common.world.biome;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class BaseFoodBiome extends Biome implements IHasFeatures {

    private int grassColor;

    public BaseFoodBiome(int waterColor, int waterFogColor, int grassColor, BlockState surface, Category category) {
        super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(surface, SurfaceBuilder.DIRT, SurfaceBuilder.SAND)).precipitation(Biome.RainType.RAIN)
                .category(category).depth(0.8f).scale(0.2f).temperature(0.7f).downfall(0.8f)
                .waterColor(waterColor).waterFogColor(waterFogColor));
        this.grassColor = grassColor;

        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
    }

    @Override
    public void addFeatures() {
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addExtraEmeraldOre(this);
        DefaultBiomeFeatures.addSprings(this);
    }

    public void addSpawn(EntityClassification type, Biome.SpawnListEntry spawnListEntry) {
        super.addSpawn(type, spawnListEntry);
    }

    @Override
    public int getGrassColor(double posX, double posZ) {
        return this.grassColor;
    }
}
