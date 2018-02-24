package coffeecatteam.cheesemod.objects.entity.ai.ham;

import coffeecatteam.cheesemod.objects.entity.EntityHamGolem;
import coffeecatteam.cheesemod.objects.entity.EntityHamMan;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAILookAtHamMan extends EntityAIBase {
	private final EntityHamGolem hamGolem;
	private EntityHamMan hamMan;
	private int lookTime;

	public EntityAILookAtHamMan(EntityHamGolem ironGolemIn) {
		this.hamGolem = ironGolemIn;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (!this.hamGolem.world.isDaytime()) {
			return false;
		} else if (this.hamGolem.getRNG().nextInt(8000) != 0) {
			return false;
		} else {
			this.hamMan = (EntityHamMan) this.hamGolem.world.findNearestEntityWithinAABB(EntityHamMan.class,
					this.hamGolem.getEntityBoundingBox().grow(6.0D, 2.0D, 6.0D), this.hamGolem);
			return this.hamMan != null;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean shouldContinueExecuting() {
		return this.lookTime > 0;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.lookTime = 400;
		this.hamGolem.setHoldingRose(true);
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by
	 * another one
	 */
	public void resetTask() {
		this.hamGolem.setHoldingRose(false);
		this.hamMan = null;
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void updateTask() {
		this.hamGolem.getLookHelper().setLookPositionWithEntity(this.hamMan, 30.0F, 30.0F);
		--this.lookTime;
	}
}
