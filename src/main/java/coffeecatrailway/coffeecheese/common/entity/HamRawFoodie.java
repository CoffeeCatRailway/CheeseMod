package coffeecatrailway.coffeecheese.common.entity;

import coffeecatrailway.coffeecheese.core.ModEntityTypes;
import coffeecatrailway.coffeecheese.core.ModItems;
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
        this(ModEntityTypes.HAM_RAW_FOODIE, world);
    }

    public HamRawFoodie(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean breedingItem(ItemStack stack) {
        return stack.getItem() == ModItems.HAM_RAW;
    }

    @Override
    public boolean foodItem(ItemStack stack) {
        return stack.getItem() == Items.PORKCHOP;
    }

    @Override
    public FoodieEntity createChild() {
        return ModEntityTypes.HAM_RAW_FOODIE.create(world);
    }

    @Override
    public ItemStack getDroppedItem() {
        return new ItemStack(Items.PORKCHOP);
    }
}
