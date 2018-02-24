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

public class EntityCheeseMan extends EntityAnimal {

	public EntityCheeseMan(World worldIn) {
		super(worldIn);
		this.setSize(0.9f, 3.0f);
		this.canMateWith(this);
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	@Override
	public boolean isBreedingItem(ItemStack itemStack) {
		return itemStack != null && itemStack.getItem() == InitItem.CHEESE_SLICE;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0d));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0d));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25d, InitItem.CHEESE_SLICE, false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25d));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
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
		return LootTable.CHEESE_MAN;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack.getItem() == coffeecatteam.cheesemod.init.InitItem.TOAST_SLICE
				&& !player.capabilities.isCreativeMode && !this.isChild()) {
			itemstack.shrink(1);

			if (itemstack.isEmpty()) {
				player.setHeldItem(hand, new ItemStack(coffeecatteam.cheesemod.init.InitItem.CHEESE_TOASTIE));
			} else if (!player.inventory
					.addItemStackToInventory(new ItemStack(coffeecatteam.cheesemod.init.InitItem.CHEESE_TOASTIE))) {
				player.dropItem(new ItemStack(coffeecatteam.cheesemod.init.InitItem.CHEESE_TOASTIE), false);
			}

			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	public EntityCheeseMan createChild(EntityAgeable ageable) {
		return new EntityCheeseMan(this.world);
	}

	@Override
	public float getEyeHeight() {
		return this.isChild() ? this.height - 0.2f : 2.3f;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}
}
