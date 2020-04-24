package coffeecatrailway.coffeecheese.common.world.biome.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

/**
 * @author CoffeeCatRailway - Andromander https://github.com/Andromander/Gaia-Dimension/blob/1.15.x/src/main/java/androsa/gaiadimension/world/layer/MineralRiverLayer.java
 * Created: 24/04/2020
 */
public enum FoodRiverLayer implements ICastleTransformer {

    INSTANCE;

    FoodRiverLayer() {
    }

    @Override
    public int apply(INoiseRandom random, int north, int west, int south, int east, int center) {
        if (shouldRiver(center, west, south, east, north)) {
            return ModLayerUtil.RIVER.getAsInt();
        } else {
            return -1;
        }
    }

    boolean shouldRiver(int center, int west, int south, int east, int north) {
        return shouldRiver(center, west) || shouldRiver(center, east) || shouldRiver(center, south) || shouldRiver(center, north);
    }

    private boolean shouldRiver(int id1, int id2) {
        if (id1 == id2 || id1 == -id2) return false;

        if ((id1 == ModLayerUtil.GRILLED_CHEESE_PLAINS.getAsInt() && id2 == ModLayerUtil.CHEESE_PLAINS.getAsInt())
                || (id1 == ModLayerUtil.CHEESE_PLAINS.getAsInt() && id2 == ModLayerUtil.GRILLED_CHEESE_PLAINS.getAsInt()))
            return false;

        if ((id1 == ModLayerUtil.HAM_COOKED_PLAINS.getAsInt() && id2 == ModLayerUtil.HAM_RAW_PLAINS.getAsInt())
                || (id1 == ModLayerUtil.HAM_RAW_PLAINS.getAsInt() && id2 == ModLayerUtil.HAM_COOKED_PLAINS.getAsInt()))
            return false;

        if ((id1 == ModLayerUtil.CHEESE_FOREST.getAsInt() && id2 == ModLayerUtil.CHEESE_PLAINS.getAsInt())
                || (id1 == ModLayerUtil.CHEESE_PLAINS.getAsInt() && id2 == ModLayerUtil.CHEESE_FOREST.getAsInt()))
            return false;
        if ((id1 == ModLayerUtil.GRILLED_CHEESE_FOREST.getAsInt() && id2 == ModLayerUtil.GRILLED_CHEESE_PLAINS.getAsInt())
                || (id1 == ModLayerUtil.GRILLED_CHEESE_PLAINS.getAsInt() && id2 == ModLayerUtil.GRILLED_CHEESE_FOREST.getAsInt()))
            return false;
        if ((id1 == ModLayerUtil.HAM_RAW_FOREST.getAsInt() && id2 == ModLayerUtil.HAM_RAW_PLAINS.getAsInt())
                || (id1 == ModLayerUtil.HAM_RAW_PLAINS.getAsInt() && id2 == ModLayerUtil.HAM_RAW_FOREST.getAsInt()))
            return false;
        if ((id1 == ModLayerUtil.HAM_COOKED_FOREST.getAsInt() && id2 == ModLayerUtil.HAM_COOKED_PLAINS.getAsInt())
                || (id1 == ModLayerUtil.HAM_COOKED_PLAINS.getAsInt() && id2 == ModLayerUtil.HAM_COOKED_FOREST.getAsInt()))
            return false;

        return true;
    }
}
