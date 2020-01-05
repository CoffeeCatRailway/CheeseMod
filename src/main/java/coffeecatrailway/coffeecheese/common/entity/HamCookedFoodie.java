package coffeecatrailway.coffeecheese.common.entity;

import coffeecatrailway.coffeecheese.registry.ModEntities;
import coffeecatrailway.coffeecheese.registry.ModItems;
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

    public HamCookedFoodie(World world) {
        this(ModEntities.HAM_COOKED_FOODIE.get(), world);
    }

    public HamCookedFoodie(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean breedingItem(ItemStack stack) {
        return stack.getItem() == ModItems.HAM_COOKED.get();
    }

    @Override
    public boolean foodItem(ItemStack stack) {
        return stack.getItem() == Items.COOKED_PORKCHOP;
    }

    @Override
    public FoodieEntity createChild() {
        return ModEntities.HAM_COOKED_FOODIE.get().create(world);
    }
}
