package coffeecatrailway.coffeecheese.common.world.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class BaseFoodBiome extends Biome implements IHasFeatures {

    private int grassColor;

    public BaseFoodBiome(Builder builder, int grassColor) {
        super(builder);
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
    public int getSkyColor() {
        return this.grassColor;
    }

    @Override
    public int getGrassColor(double posX, double posZ) {
        return this.grassColor;
    }

    @Override
    public int getFoliageColor() {
        return this.grassColor;
    }
}
