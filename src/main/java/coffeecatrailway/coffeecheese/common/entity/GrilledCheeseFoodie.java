package coffeecatrailway.coffeecheese.common.entity;

import coffeecatrailway.coffeecheese.core.ModEntityTypes;
import coffeecatrailway.coffeecheese.core.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 14/09/2019
 */
public class GrilledCheeseFoodie extends FoodieEntity {

    public GrilledCheeseFoodie(World world) {
        this(ModEntityTypes.GRILLED_CHEESE_FOODIE, world);
    }

    public GrilledCheeseFoodie(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean breedingItem(ItemStack stack) {
        return stack.getItem() == ModItems.CHEESE_SLICE;
    }

    @Override
    public boolean foodItem(ItemStack stack) {
        return stack.getItem() == ModItems.BLOCK_O_CHEESE;
    }

    @Override
    public FoodieEntity createChild() {
        return ModEntityTypes.GRILLED_CHEESE_FOODIE.create(world);
    }

    @Override
    public ItemStack getDroppedItem() {
        return new ItemStack(ModItems.BLOCK_O_CHEESE);
    }
}
