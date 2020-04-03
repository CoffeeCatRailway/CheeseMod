package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.common.tileentity.*;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.tileentity.TileEntityType;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 2/08/2019
 */
public class ModTileEntities {

    public static final RegistryEntry<TileEntityType<FoodDrawTileEntity>> FOOD_DRAW = REGISTRATE.tileEntity("food_draw", FoodDrawTileEntity::new)
            .validBlock(ModBlocks.CHEESE_DRAW).validBlock(ModBlocks.GRILLED_CHEESE_DRAW).validBlock(ModBlocks.HAM_RAW_DRAW).validBlock(ModBlocks.HAM_COOKED_DRAW).register();

    public static final RegistryEntry<TileEntityType<GrillTileEntity>> GRILL = REGISTRATE.tileEntity("grill", GrillTileEntity::new).validBlock(ModBlocks.GRILL).register();

    public static final RegistryEntry<TileEntityType<MelterTileEntity>> MELTER = REGISTRATE.tileEntity("melter", MelterTileEntity::new).validBlock(ModBlocks.MELTER).register();

    public static final RegistryEntry<TileEntityType<PizzaOvenTileEntity>> PIZZA_OVEN = REGISTRATE.tileEntity("pizza_oven", PizzaOvenTileEntity::new).validBlock(ModBlocks.MELTER).register();

    public static final RegistryEntry<TileEntityType<FoodWorldPortalTileEntity>> FOOD_WORLD_PORTAL = REGISTRATE.tileEntity("food_world_portal", FoodWorldPortalTileEntity::new).validBlock(ModBlocks.FOOD_PORTAL).register();

    public static void load() {
    }
}
