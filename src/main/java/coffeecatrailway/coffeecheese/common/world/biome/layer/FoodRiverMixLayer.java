package coffeecatrailway.coffeecheese.common.world.biome.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

/**
 * @author CoffeeCatRailway
 * Created: 24/04/2020
 */
public enum FoodRiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer {

    INSTANCE;

    FoodRiverMixLayer() {
    }

    @Override
    public int apply(INoiseRandom random, IArea area1, IArea area2, int val1, int val2) {
        int i = area1.getValue(this.func_215721_a(val1), this.func_215722_b(val2));
        int j = area2.getValue(this.func_215721_a(val1), this.func_215722_b(val2));

        if (j == ModLayerUtil.RIVER.getAsInt()) {
            return j;
        } else {
            return i;
        }
    }
}
