package coffeecatrailway.cheesemod.common.entity;

import coffeecatrailway.cheesemod.core.ModEntityTypes;
import coffeecatrailway.cheesemod.core.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 14/09/2019
 */
public class CheeseFoodie extends FoodieEntity {

    public CheeseFoodie(World world) {
        this(ModEntityTypes.CHEESE_FOODIE, world);
    }

    public CheeseFoodie(EntityType<? extends TameableEntity> type, World world) {
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
        return ModEntityTypes.CHEESE_FOODIE.create(world);
    }

    @Override
    public ItemStack getDroppedItem() {
        return new ItemStack(ModItems.BLOCK_O_CHEESE);
    }
}
