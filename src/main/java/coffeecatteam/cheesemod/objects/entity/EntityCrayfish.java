package coffeecatteam.cheesemod.objects.entity;

import coffeecatteam.cheesemod.util.LootTable;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityCrayfish extends EntityAnimal {

	public EntityCrayfish(World worldIn) {
		super(worldIn);
		this.setSize(0.9f, 0.5f);
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0d));
		this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0d));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityCrayfish.class, 6.0f));
		this.tasks.addTask(4, new EntityAILookIdle(this));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_VILLAGER_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_VILLAGER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_VILLAGER_DEATH;
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_PLAYER_SWIM;
	}
	
	@Override
	protected SoundEvent getSplashSound() {
		return SoundEvents.ENTITY_PLAYER_SPLASH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
	}

	@Override
	protected float getSoundVolume() {
		return 0.4f;
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return LootTable.CRAYFISH;
	}
	
	@Override
	public float getEyeHeight() {
		return this.height;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        return false;
    }
}
