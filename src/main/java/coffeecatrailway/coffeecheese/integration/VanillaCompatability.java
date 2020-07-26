package coffeecatrailway.coffeecheese.integration;

import coffeecatrailway.coffeecheese.common.entity.CheeseBallEntity;
import coffeecatrailway.coffeecheese.registry.CheeseItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2020
 */
public class VanillaCompatability {

    public static void setup() {
        // Dispenser
        DispenserBlock.registerDispenseBehavior(CheeseItems.CHEESE_BALL.get(), new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectileEntity(World world, IPosition pos, ItemStack stack) {
                return Util.make(new CheeseBallEntity(world, pos.getX(), pos.getY(), pos.getZ()), (entity) -> entity.setItem(stack));
            }
        });
    }
}
