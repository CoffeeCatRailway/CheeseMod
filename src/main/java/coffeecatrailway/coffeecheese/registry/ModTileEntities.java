package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.tileentity.*;
import com.google.common.collect.Sets;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 2/08/2019
 */
public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, CheeseMod.MOD_ID);

    public static final RegistryObject<TileEntityType<FoodDrawTileEntity>> FOOD_DRAW = TILE_ENTITIES.register("food_draw",
            () -> new TileEntityType<>(FoodDrawTileEntity::new,
                    Sets.newHashSet(ModBlocks.CHEESE_DRAW.get(), ModBlocks.GRILLED_CHEESE_DRAW.get(), ModBlocks.HAM_RAW_DRAW.get(), ModBlocks.HAM_COOKED_DRAW.get()), null));

    public static final RegistryObject<TileEntityType<GrillTileEntity>> GRILL = TILE_ENTITIES.register("grill",
            () -> new TileEntityType<>(GrillTileEntity::new, Sets.newHashSet(ModBlocks.GRILL.get()), null));

    public static final RegistryObject<TileEntityType<MelterTileEntity>> MELTER = TILE_ENTITIES.register("melter",
            () -> new TileEntityType<>(MelterTileEntity::new, Sets.newHashSet(ModBlocks.MELTER.get()), null));

    public static final RegistryObject<TileEntityType<PizzaOvenTileEntity>> PIZZA_OVEN = TILE_ENTITIES.register("pizza_oven",
            () -> new TileEntityType<>(PizzaOvenTileEntity::new, Sets.newHashSet(ModBlocks.PIZZA_OVEN.get()), null));

    public static final RegistryObject<TileEntityType<FoodWorldPortalTileEntity>> FOOD_WORLD_PORTAL = TILE_ENTITIES.register("food_world_portal",
            () -> new TileEntityType<>(FoodWorldPortalTileEntity::new, Sets.newHashSet(ModBlocks.FOOD_PORTAL.get()), null));
}
