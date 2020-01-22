package coffeecatrailway.coffeecheese.common.tileentity;

import coffeecatrailway.coffeecheese.registry.ModTileEntities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

/**
 * @author CoffeeCatRailway
 * Created: 22/01/2020
 */
public class FoodWorldPortalTileEntity extends TileEntity {

    public FoodWorldPortalTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public FoodWorldPortalTileEntity() {
        this(ModTileEntities.FOOD_WORLD_PORTAL.get());
    }
}
