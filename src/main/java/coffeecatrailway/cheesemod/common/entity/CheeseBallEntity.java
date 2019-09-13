package coffeecatrailway.cheesemod.common.entity;

import coffeecatrailway.cheesemod.core.ModEntityTypes;
import coffeecatrailway.cheesemod.core.ModItems;
import coffeecatrailway.cheesemod.core.ModParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
 * Created: 9/09/2019
 */
public class CheeseBallEntity extends ProjectileItemEntity {

    public CheeseBallEntity(EntityType<? extends CheeseBallEntity> type, World world) {
        super(type, world);
    }

    public CheeseBallEntity(World world, LivingEntity entity) {
        super(ModEntityTypes.CHEESE_BALL, entity, world);
    }

    public CheeseBallEntity(World world, double x, double y, double z) {
        super(ModEntityTypes.CHEESE_BALL, x, y, z, world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected Item func_213885_i() {
        return ModItems.CHEESE_BALL;
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData func_213887_n() {
        ItemStack stack = this.func_213882_k();
        return stack.isEmpty() ? ModParticles.ITEM_CHEESE_BALL : new ItemParticleData(ParticleTypes.ITEM, stack);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte flags) {
        if (flags == 3) {
            IParticleData data = this.func_213887_n();
            for (int lvt_3_1_ = 0; lvt_3_1_ < 8; ++lvt_3_1_)
                this.world.addParticle(data, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void onImpact(RayTraceResult traceResult) {
        if (traceResult.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult) traceResult).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.5f);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
    }
}
