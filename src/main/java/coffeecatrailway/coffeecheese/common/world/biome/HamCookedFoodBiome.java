package coffeecatrailway.coffeecheese.common.world.biome;

import coffeecatrailway.coffeecheese.registry.ModBiomes;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;

/**
 * @author CoffeeCatRailway
 * Created: 3/01/2020
 */
public class HamCookedFoodBiome extends BaseFoodBiome {

    public HamCookedFoodBiome(Category category) {
        super(ModBiomes.HAM_COOKED_WATER_COLOR, ModBiomes.HAM_COOKED_WATER_FOG_COLOR, ModBiomes.HAM_COOKED_GRASS_COLOR, ModBlocks.HAM_COOKED_GRASS_BLOCK.get().getDefaultState(), category);
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        ModFeatures.addFoodGrass(this, ModFeatures.HAM_COOKED_GRASS_CONFIG);
        ModFeatures.addTallFoodGrass(this, ModFeatures.TALL_HAM_COOKED_GRASS_CONFIG);
        if (this.category == Category.FOREST)
            ModFeatures.addFoodTrees(this, ModFeatures.HAM_COOKED_TREE.get().withConfiguration(ModFeatures.HAM_COOKED_TREE_CONFIG), ModFeatures.HAM_RAW_TREE.get().withConfiguration(ModFeatures.HAM_RAW_TREE_CONFIG));
    }
}
