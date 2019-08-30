package coffeecatrailway.cheesemod.core;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.world.feature.structure.PineHutStructure;
import coffeecatrailway.cheesemod.world.feature.tree.CheeseTreeFeature;
import coffeecatrailway.cheesemod.world.feature.tree.HamTreeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class ModFeatures {

    /// Trees ///
    public static AbstractTreeFeature<NoFeatureConfig> CHEESE_TREE = new CheeseTreeFeature(false, NoFeatureConfig::deserialize, true);
    public static AbstractTreeFeature<NoFeatureConfig> GRILLED_CHEESE_TREE = new CheeseTreeFeature(true, NoFeatureConfig::deserialize, true);
    public static AbstractTreeFeature<NoFeatureConfig> HAM_RAW_TREE = new HamTreeFeature(false, NoFeatureConfig::deserialize, true);
    public static AbstractTreeFeature<NoFeatureConfig> HAM_COOKED_TREE = new HamTreeFeature(true, NoFeatureConfig::deserialize, true);

    /// Structures ///
    public static Structure<NoFeatureConfig> PINE_HUT = new PineHutStructure(NoFeatureConfig::deserialize);
    public static Structure<?> PINE_HUT_STRUCT = registerStructure("Pine_Hut", PINE_HUT);
    public static IStructurePieceType PINE_HUT_TYPE = registerStructureTYPE("PineHut", PineHutStructure.HutPiece::new);

    public static void registerAll(RegistryEvent.Register<Feature<?>> event) {
        if (!event.getName().equals(ForgeRegistries.FEATURES.getRegistryName())) return;

        /// Trees ///
        register("cheese_tree", CHEESE_TREE);
        register("grilled_cheese_tree", GRILLED_CHEESE_TREE);
        register("ham_raw_tree", HAM_RAW_TREE);
        register("ham_cooked_tree", HAM_COOKED_TREE);

        /// Structures ///
        register("pine_hut", PINE_HUT);

        CheeseMod.LOGGER.info("Features registered");
    }

    private static Structure<?> registerStructure(String key, Structure<?> structure) {
        return Registry.register(Registry.STRUCTURE_FEATURE, key.toLowerCase(Locale.ROOT), structure);
    }

    static IStructurePieceType registerStructureTYPE(String key, IStructurePieceType type) {
        return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), type);
    }

    private static <C extends IFeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        feature.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.FEATURES.register(feature);
        return feature;
    }
}
