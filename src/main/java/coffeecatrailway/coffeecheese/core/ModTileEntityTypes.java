package coffeecatrailway.coffeecheese.core;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.tileentity.FoodDrawTileEntity;
import coffeecatrailway.coffeecheese.common.tileentity.GrillTileEntity;
import coffeecatrailway.coffeecheese.common.tileentity.MelterTileEntity;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 2/08/2019
 */
public class ModTileEntityTypes {

    public static TileEntityType<FoodDrawTileEntity> FOOD_DRAW;
    public static TileEntityType<GrillTileEntity> GRILL;
    public static TileEntityType<MelterTileEntity> MELTER;

    public static void registerAll(RegistryEvent.Register<TileEntityType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.TILE_ENTITIES.getRegistryName())) return;

        FOOD_DRAW = register("food_draw", TileEntityType.Builder.create(FoodDrawTileEntity::new, ModBlocks.CHEESE_DRAW, ModBlocks.GRILLED_CHEESE_DRAW, ModBlocks.HAM_RAW_DRAW, ModBlocks.HAM_COOKED_DRAW));
        GRILL = register("grill", TileEntityType.Builder.create(GrillTileEntity::new, ModBlocks.GRILL));
        MELTER = register("melter", TileEntityType.Builder.create(MelterTileEntity::new, ModBlocks.MELTER));

        CheeseMod.LOGGER.info("Tile entity types registered");
    }

    private static <T extends TileEntity> TileEntityType<T> register(String name, TileEntityType.Builder<T> builder) {
        Type<?> type = null;
        try {
            type = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(SharedConstants.getVersion().getWorldVersion())).getChoiceType(TypeReferences.BLOCK_ENTITY, name);
        } catch (IllegalArgumentException illegalstateexception) {
            if (SharedConstants.developmentMode) {
                throw illegalstateexception;
            }
            CheeseMod.LOGGER.warn("No data fixer registered for block entity {}", name);
        }

        TileEntityType<T> tile = builder.build(type);
        tile.setRegistryName(CheeseMod.getLocation(name));
        ForgeRegistries.TILE_ENTITIES.register(tile);
        return tile;
    }
}
