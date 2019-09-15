package coffeecatrailway.cheesemod.common.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * @author CoffeeCatRailway
 * Created: 13/09/2019
 */
public abstract class FoodieEntity extends TameableEntity {

    private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.createKey(WolfEntity.class, DataSerializers.FLOAT);

    public FoodieEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
        this.setTamed(false);
    }

    @Override
    protected void registerGoals() {
        this.sitGoal = new SitGoal(this);
        this.goalSelector.addGoal(1, this.sitGoal);
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0f, 2.0f));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.3f);
        if (this.isTamed())
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        else
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);

        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn);
        if (entitylivingbaseIn == null)
            this.setAngry(false);
        else if (!this.isTamed())
            this.setAngry(true);
    }

    public void setAngry(boolean angry) {
        byte b0 = this.dataManager.get(TAMED);
        if (angry)
            this.dataManager.set(TAMED, (byte) (b0 | 2));
        else
            this.dataManager.set(TAMED, (byte) (b0 & -3));
    }

    public boolean isAngry() {
        return (this.dataManager.get(TAMED) & 2) != 0;
    }

    @Override
    protected void updateAITasks() {
        this.dataManager.set(DATA_HEALTH_ID, this.getHealth());
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_HEALTH_ID, this.getHealth());
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.BLOCK_SNOW_PLACE, 0.15f, 1.0f);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Angry", this.isAngry());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setAngry(compound.getBoolean("Angry"));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_SNOW_PLACE;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_SNOW_BREAK;
    }

    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry())
            this.setAngry(false);
    }

    @Override
    public void onDeath(DamageSource cause) {
        this.entityDropItem(this.getDroppedItem());
        super.onDeath(cause);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.8F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source))
            return false;
        else {
            Entity entity = source.getTrueSource();
            if (this.sitGoal != null)
                this.sitGoal.setSitting(false);

            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof AbstractArrowEntity))
                amount = (amount + 1.0f) / 2.0f;

            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue()));
        if (flag)
            this.applyEnchantments(this, entity);
        return flag;
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed)
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        else
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);

        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (this.isTamed()) {
            if (!itemstack.isEmpty()) {
                if (this.foodItem(itemstack) && this.dataManager.get(DATA_HEALTH_ID) < 20.0f) {
                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }

                    this.heal((float) item.getFood().getHealing());
                    return true;
                }
            }

            if (itemstack.isEmpty() && player.isSneaking()) {
                if (this.getRidingEntity() != null) {
                    this.stopRiding();
                    return true;
                } else {
                    if (this.startRiding(player))
                        return true;
                }
            }

            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
                this.sitGoal.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget(null);
            }
        } else if (this.foodItem(itemstack) && !this.isAngry()) {
            if (!player.abilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            if (!this.world.isRemote) {
                if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.setTamedBy(player);
                    this.navigator.clearPath();
                    this.setAttackTarget(null);
                    this.sitGoal.setSitting(true);
                    this.setHealth(20.0f);
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte) 7);
                } else {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte) 6);
                }
            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return this.breedingItem(stack);
    }

    public abstract boolean breedingItem(ItemStack stack);

    public abstract boolean foodItem(ItemStack stack);

    public abstract FoodieEntity createChild();

    public abstract ItemStack getDroppedItem();

    @Override
    public int getMaxSpawnedInChunk() {
        return 3;
    }

    @Nullable
    @Override
    public FoodieEntity createChild(AgeableEntity entity) {
        FoodieEntity foodie = this.createChild();
        UUID uuid = this.getOwnerId();
        if (uuid != null) {
            foodie.setOwnerId(uuid);
            foodie.setTamed(true);
        }

        return foodie;
    }

    @Override
    public boolean canMateWith(AnimalEntity other) {
        if (other == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(other instanceof FoodieEntity)) {
            return false;
        } else {
            FoodieEntity foodie = (FoodieEntity) other;
            if (!foodie.isTamed()) {
                return false;
            } else if (foodie.isSitting()) {
                return false;
            } else {
                return this.isInLove() && foodie.isInLove();
            }
        }
    }

    @Override
    public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
        return target instanceof PlayerEntity && owner instanceof PlayerEntity && ((PlayerEntity) owner).canAttackPlayer((PlayerEntity) target);
    }

    @Override
    public boolean canBeLeashedTo(PlayerEntity player) {
        return !this.isAngry() && super.canBeLeashedTo(player);
    }
}
