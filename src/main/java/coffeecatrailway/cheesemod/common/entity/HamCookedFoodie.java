package coffeecatrailway.cheesemod.common.entity;

import coffeecatrailway.cheesemod.core.ModEntityTypes;
import coffeecatrailway.cheesemod.core.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 14/09/2019
 */
public class HamCookedFoodie extends FoodieEntity {

    public HamCookedFoodie(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean breedingItem(ItemStack stack) {
        return stack.getItem() == ModItems.HAM_COOKED;
    }

    @Override
    public boolean foodItem(ItemStack stack) {
        return stack.getItem() == Items.COOKED_PORKCHOP;
    }

    @Override
    public FoodieEntity createChild() {
        return ModEntityTypes.HAM_COOKED_FOODIE.create(world);
    }

    @Override
    public ItemStack getDroppedItem() {
        return new ItemStack(Items.COOKED_PORKCHOP);
    }
}
