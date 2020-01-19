package coffeecatrailway.coffeecheese.common.enchantment;

import coffeecatrailway.coffeecheese.common.ModTags;
import coffeecatrailway.coffeecheese.common.item.CheeseSuitArmorItem;
import coffeecatrailway.coffeecheese.common.item.HamSuitArmorItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.Tag;

import java.util.Iterator;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 18/01/2020
 */
public class EdibleEnchantment extends Enchantment {

    public EdibleEnchantment(Rarity rarity, EquipmentSlotType[] slots) {
        super(rarity, EnchantmentType.ARMOR, slots);
    }

    @Override
    public void onUserHurt(LivingEntity user, Entity attacker, int level) {
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            PlayerInventory inventory = player.inventory;
            this.getEntityEquipment(player).forEach((equipmentSlot, armorStack) -> {
                Item armorPiece = armorStack.getItem();
                if (this.canApply(armorStack)) {
                    Tag<Item> tag = (armorPiece instanceof CheeseSuitArmorItem) ? ModTags.Items.CHEESE : (armorPiece instanceof HamSuitArmorItem) ? ModTags.Items.HAM : null;
                    if (tag != null) {
                        ItemStack tagStack = this.hasTag(player.inventory, tag);
                        if (tagStack != ItemStack.EMPTY && tagStack.isFood() && player.shouldHeal()) {
                            player.heal(tagStack.getItem().getFood().getHealing() * .5f * level);
                            inventory.decrStackSize(inventory.getSlotFor(tagStack), level);
                            if (user.world.rand.nextInt(10) < 5)
                                inventory.getStackInSlot(equipmentSlot.getSlotIndex()).damageItem((int) (level + user.world.rand.nextFloat() * level * 1.5f), user, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.HEAD));
                        }
                    }
                }
            });
        }
    }

    private ItemStack hasTag(PlayerInventory inventory, Tag<Item> itemTag) {
        label23:
        for (List<ItemStack> list : inventory.allInventories) {
            Iterator iterator = list.iterator();
            while (true) {
                if (!iterator.hasNext())
                    continue label23;

                ItemStack itemstack = (ItemStack) iterator.next();
                if (!itemstack.isEmpty() && itemTag.contains(itemstack.getItem()))
                    return itemstack;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof CheeseSuitArmorItem || item instanceof HamSuitArmorItem;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 30;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
