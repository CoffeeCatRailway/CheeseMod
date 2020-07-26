package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 26/07/2020
 */
public class CheeseBlocks {

    public static final Logger LOGGER = LogManager.getLogger(CheeseMod.MOD_ID + "-Blocks");

    /// METAL ///

    /// NATURE ///

    /// WOOD ///
    private static NonNullUnaryOperator<Block.Properties> PLANKS_PROPS = prop -> prop.hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD);

    public static final RegistryEntry<Block> CHEESE_PLANKS = REGISTRATE.object("cheese_planks").block(Block::new).initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(PLANKS_PROPS)
            .item().build().register();
    public static final RegistryEntry<Block> GRILLED_CHEESE_PLANKS = REGISTRATE.object("grilled_cheese_planks").block(Block::new).initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(PLANKS_PROPS)
            .item().build().register();
    public static final RegistryEntry<Block> HAM_RAW_PLANKS = REGISTRATE.object("ham_raw_planks").block(Block::new).initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(PLANKS_PROPS)
            .item().build().register();
    public static final RegistryEntry<Block> HAM_COOKED_PLANKS = REGISTRATE.object("ham_cooked_planks").block(Block::new).initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(PLANKS_PROPS)
            .item().build().register();

    /// FOOD ///

    /// OTHER ///

    public static void load() {
        LOGGER.info("Blocks registered");
    }
}
