package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.common.dispenser.DispenserFoodBoatBehavior;
import coffeecatrailway.coffeecheese.common.entity.FoodBoatEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2020
 */
public class FoodBoatItem extends Item {

    private static final Predicate<Entity> field_219989_a = EntityPredicates.NOT_SPECTATING.and(Entity::canBeCollidedWith);
    private final FoodBoatEntity.Type type;

    public FoodBoatItem(FoodBoatEntity.Type typeIn, Item.Properties properties) {
        super(properties);
        this.type = typeIn;
        DispenserBlock.registerDispenseBehavior(this, new DispenserFoodBoatBehavior(typeIn));
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.ANY);
        if (raytraceresult.getType() == RayTraceResult.Type.MISS)
            return new ActionResult<>(ActionResultType.PASS, itemstack);
        else {
            Vector3d vec3d = playerIn.getLook(1.0F);
            List<Entity> list = worldIn.getEntitiesInAABBexcluding(playerIn, playerIn.getBoundingBox().expand(vec3d.scale(5.0D)).grow(1.0D), field_219989_a);
            if (!list.isEmpty()) {
                Vector3d vec3d1 = playerIn.getEyePosition(1.0F);

                for (Entity entity : list) {
                    AxisAlignedBB axisalignedbb = entity.getBoundingBox().grow((double) entity.getCollisionBorderSize());
                    if (axisalignedbb.contains(vec3d1))
                        return new ActionResult<>(ActionResultType.PASS, itemstack);
                }
            }

            if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {

                FoodBoatEntity boatentity = new FoodBoatEntity(worldIn, raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
                boatentity.setBoatModel(this.type);
                boatentity.rotationYaw = playerIn.rotationYaw;
                if (!worldIn.hasNoCollisions(boatentity, boatentity.getBoundingBox().grow(-0.1D)))
                    return new ActionResult<>(ActionResultType.FAIL, itemstack);
                else {
                    if (!worldIn.isRemote)
                        worldIn.addEntity(boatentity);

                    if (!playerIn.abilities.isCreativeMode)
                        itemstack.shrink(1);

                    playerIn.addStat(Stats.ITEM_USED.get(this));
                    return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
                }
            } else
                return new ActionResult<>(ActionResultType.PASS, itemstack);
        }
    }
}
