package coffeecatteam.cheesemod.objects.entity.ai.ham;

import coffeecatteam.cheesemod.objects.entity.EntityHamGolem;
import coffeecatteam.cheesemod.objects.entity.EntityHamMan;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.village.Village;

public class EntityAIDefendHamMan extends EntityAITarget {
	EntityHamGolem hamGolem;
	/**
	 * The aggressor of the iron golem's village which is now the golem's attack
	 * target.
	 */
	EntityLivingBase villageAgressorTarget;

	public EntityAIDefendHamMan(EntityHamGolem hamGolem) {
		super(hamGolem, false, true);
		this.hamGolem = hamGolem;
		this.setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		Village village = this.hamGolem.getVillage();

		if (village == null) {
			return false;
		} else {
			this.villageAgressorTarget = village.findNearestVillageAggressor(this.hamGolem);

			if (this.villageAgressorTarget instanceof EntityCreeper) {
				return false;
			} else if (this.villageAgressorTarget instanceof EntityHamMan) {
				return true;
			} else if (this.isSuitableTarget(this.villageAgressorTarget, false)) {
				return true;
			} else if (this.taskOwner.getRNG().nextInt(20) == 0) {
				this.villageAgressorTarget = village.getNearestTargetPlayer(this.hamGolem);
				return this.isSuitableTarget(this.villageAgressorTarget, false);
			} else {
				return false;
			}
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.hamGolem.setAttackTarget(this.villageAgressorTarget);
		super.startExecuting();
	}
}
