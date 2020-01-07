package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.world.feature.BaconBallFeature;
import coffeecatrailway.coffeecheese.common.world.feature.structure.PineHutStructure;
import coffeecatrailway.coffeecheese.common.world.feature.structure.PineHutStructurePiece;
import coffeecatrailway.coffeecheese.common.world.feature.tree.CheeseTreeFeature;
import coffeecatrailway.coffeecheese.common.world.feature.tree.HamTreeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 30/07/2019
 */
public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, CheeseMod.MOD_ID);

    /// Trees ///
    public static final RegistryObject<AbstractTreeFeature<NoFeatureConfig>> CHEESE_TREE = FEATURES.register("cheese_tree", () ->
            new CheeseTreeFeature(false, NoFeatureConfig::deserialize, true));
    public static final RegistryObject<AbstractTreeFeature<NoFeatureConfig>> GRILLED_CHEESE_TREE = FEATURES.register("grilled_cheese_tree", () ->
            new CheeseTreeFeature(true, NoFeatureConfig::deserialize, true));
    public static final RegistryObject<AbstractTreeFeature<NoFeatureConfig>> HAM_RAW_TREE = FEATURES.register("ham_raw_tree", () ->
            new HamTreeFeature(false, NoFeatureConfig::deserialize, true));
    public static final RegistryObject<AbstractTreeFeature<NoFeatureConfig>> HAM_COOKED_TREE = FEATURES.register("ham_cooked_tree", () ->
            new HamTreeFeature(true, NoFeatureConfig::deserialize, true));

    /// Structures ///
    public static final RegistryObject<Structure<NoFeatureConfig>> PINE_HUT = FEATURES.register("pine_hut", () -> new PineHutStructure(NoFeatureConfig::deserialize));
    public static IStructurePieceType PINE_HUT_TYPE = Registry.register(Registry.STRUCTURE_PIECE, CheeseMod.getLocation("hut"), PineHutStructurePiece::new);

    /// Other ///
    public static final RegistryObject<BaconBallFeature> BACON_RAW_BALL = FEATURES.register("bacon_raw_ball", () -> new BaconBallFeature(false, NoFeatureConfig::deserialize));
    public static final RegistryObject<BaconBallFeature> BACON_COOKED_BALL = FEATURES.register("bacon_cooked_ball", () -> new BaconBallFeature(true, NoFeatureConfig::deserialize));
}
