package coffeecatteam.cheesemod.objects.entity.ai.cheese;

import coffeecatteam.cheesemod.objects.entity.golem.EntityCheeseGolem;
import coffeecatteam.cheesemod.objects.entity.man.EntityCheeseMan;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAILookAtCheeseMan extends EntityAIBase {
	private final EntityCheeseGolem cheeseGolem;
	private EntityCheeseMan cheeseMan;
	private int lookTime;

	public EntityAILookAtCheeseMan(EntityCheeseGolem ironGolemIn) {
		this.cheeseGolem = ironGolemIn;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (!this.cheeseGolem.world.isDaytime()) {
			return false;
		} else if (this.cheeseGolem.getRNG().nextInt(8000) != 0) {
			return false;
		} else {
			this.cheeseMan = (EntityCheeseMan) this.cheeseGolem.world.findNearestEntityWithinAABB(EntityCheeseMan.class,
					this.cheeseGolem.getEntityBoundingBox().grow(6.0D, 2.0D, 6.0D), this.cheeseGolem);
			return this.cheeseMan != null;
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
		this.cheeseGolem.setHoldingRose(true);
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by
	 * another one
	 */
	public void resetTask() {
		this.cheeseGolem.setHoldingRose(false);
		this.cheeseMan = null;
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void updateTask() {
		this.cheeseGolem.getLookHelper().setLookPositionWithEntity(this.cheeseMan, 30.0F, 30.0F);
		--this.lookTime;
	}
}
