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
public class HamRawFoodie extends FoodieEntity {

    public HamRawFoodie(World world) {
        this(ModEntities.HAM_RAW_FOODIE.get(), world);
    }

    public HamRawFoodie(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean breedingItem(ItemStack stack) {
        return stack.getItem() == ModItems.HAM_RAW.get();
    }

    @Override
    public boolean foodItem(ItemStack stack) {
        return stack.getItem() == Items.PORKCHOP;
    }

    @Override
    public FoodieEntity createChild() {
        return ModEntities.HAM_RAW_FOODIE.get().create(world);
    }
}
