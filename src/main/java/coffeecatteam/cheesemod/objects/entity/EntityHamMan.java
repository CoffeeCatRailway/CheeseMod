package coffeecatteam.cheesemod.objects.entity;

import javax.annotation.Nullable;

import coffeecatteam.cheesemod.init.InitItem;
import coffeecatteam.cheesemod.util.LootTable;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityHamMan extends EntityAnimal {

	public EntityHamMan(World worldIn) {
		super(worldIn);
		this.height = 2.5f;
		this.setSize(0.9f, this.height);
		this.canMateWith(this);
	}

	@Override
	public boolean isBreedingItem(ItemStack itemStack) {
		return itemStack != null && itemStack.getItem() == InitItem.HAM_RAW;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25D, InitItem.HAM_RAW, false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0d);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224d);
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
		return LootTable.HAM_MAN;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack.getItem() == InitItem.BREAD_SLICE && !player.capabilities.isCreativeMode && !this.isChild()) {
			// player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
			itemstack.shrink(1);

			if (itemstack.isEmpty()) {
				player.setHeldItem(hand, new ItemStack(InitItem.TOASTIE, 1, 2));
			} else if (!player.inventory.addItemStackToInventory(new ItemStack(InitItem.TOASTIE, 1, 2))) {
				player.dropItem(new ItemStack(InitItem.TOASTIE, 1, 2), false);
			}

			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	public EntityHamMan createChild(EntityAgeable ageable) {
		return new EntityHamMan(world);
	}

	@Override
	public float getEyeHeight() {
		return this.isChild() ? this.height-0.2f : 2.3f;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}
}
