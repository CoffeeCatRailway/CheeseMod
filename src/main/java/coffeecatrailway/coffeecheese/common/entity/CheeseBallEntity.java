package coffeecatrailway.coffeecheese.common.entity;

import coffeecatrailway.coffeecheese.registry.CheeseEntities;
import coffeecatrailway.coffeecheese.registry.CheeseItems;
import coffeecatrailway.coffeecheese.registry.CheeseParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2020
 */
public class CheeseBallEntity extends ProjectileItemEntity {

    public CheeseBallEntity(EntityType<CheeseBallEntity> type, World world) {
        super(type, world);
    }

    public CheeseBallEntity(World world, LivingEntity entity) {
        super(CheeseEntities.CHEESE_BALL.get(), entity, world);
    }

    public CheeseBallEntity(World world, double x, double y, double z) {
        super(CheeseEntities.CHEESE_BALL.get(), x, y, z, world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected Item getDefaultItem() {
        return CheeseItems.CHEESE_BALL.get();
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData makeParticle() {
        ItemStack stack = this.func_213882_k();
        return stack.isEmpty() ? CheeseParticles.ITEM_CHEESE_BALL.get() : new ItemParticleData(ParticleTypes.ITEM, stack);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte flags) {
        if (flags == 3) {
            IParticleData data = this.makeParticle();
            for (int i = 0; i < 8; i++)
                this.world.addParticle(data, this.getPosX(), this.getPosY(), this.getPosZ(), 0d, 0d, 0d);
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult traceResult) {
        super.onEntityHit(traceResult);
        Entity entity = traceResult.getEntity();
        float i = entity instanceof BlazeEntity ? 3f : 0f; //TODO: Check if foodie
        entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), i);
    }

    @Override
    protected void onImpact(RayTraceResult traceResult) {
        super.onImpact(traceResult);
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
    }
}
