package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.world.feature.structure.PineHutStructure;
import coffeecatrailway.coffeecheese.common.world.feature.structure.PineHutStructurePiece;
import coffeecatrailway.coffeecheese.common.world.feature.tree.CheeseTreeFeature;
import coffeecatrailway.coffeecheese.common.world.feature.tree.HamTreeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class ModFeatures {

    /// Trees ///
    public static final AbstractTreeFeature<NoFeatureConfig> CHEESE_TREE = new CheeseTreeFeature(false, NoFeatureConfig::deserialize, true);
    public static final AbstractTreeFeature<NoFeatureConfig> GRILLED_CHEESE_TREE = new CheeseTreeFeature(true, NoFeatureConfig::deserialize, true);
    public static final AbstractTreeFeature<NoFeatureConfig> HAM_RAW_TREE = new HamTreeFeature(false, NoFeatureConfig::deserialize, true);
    public static final AbstractTreeFeature<NoFeatureConfig> HAM_COOKED_TREE = new HamTreeFeature(true, NoFeatureConfig::deserialize, true);

    /// Structures ///
    public static Structure<NoFeatureConfig> PINE_HUT;
    public static IStructurePieceType PINE_HUT_TYPE;

    public static void registerAll(RegistryEvent.Register<Feature<?>> event) {
        if (!event.getName().equals(ForgeRegistries.FEATURES.getRegistryName())) return;

        /// Trees ///
        register("cheese_tree", CHEESE_TREE);
        register("grilled_cheese_tree", GRILLED_CHEESE_TREE);
        register("ham_raw_tree", HAM_RAW_TREE);
        register("ham_cooked_tree", HAM_COOKED_TREE);

        /// Structures ///
        PINE_HUT_TYPE = Registry.register(Registry.STRUCTURE_PIECE, CheeseMod.getLocation("hut"), PineHutStructurePiece::new);
        PINE_HUT = register("pine_hut", new PineHutStructure(NoFeatureConfig::deserialize));

        CheeseMod.LOGGER.info("Features registered");
    }

    private static <C extends IFeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        feature.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.FEATURES.register(feature);
        return feature;
    }
}
