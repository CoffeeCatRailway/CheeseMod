package coffeecatrailway.coffeecheese.common.enchantment;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.ModTags;
import coffeecatrailway.coffeecheese.common.item.CheeseSuitArmorItem;
import coffeecatrailway.coffeecheese.common.item.HamSuitArmorItem;
import coffeecatrailway.coffeecheese.registry.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.Tag;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 18/01/2020
 */
@Mod.EventBusSubscriber(modid = CheeseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EdibleEnchantment extends Enchantment {

    public EdibleEnchantment(Rarity rarity, EquipmentSlotType[] slots) {
        super(rarity, EnchantmentType.ARMOR, slots);
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

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof CheeseSuitArmorItem || item instanceof HamSuitArmorItem;
    }

    @SubscribeEvent
    public static void applyHunger(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        if (player.world.isRemote || player.world.getWorldInfo().getGameTime() % 20 != 0) return;

        for (EquipmentSlotType slot : ModEnchantments.ARMOR_SLOTS) {
            ItemStack armorStack = player.getItemStackFromSlot(slot);
            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.EDIBLE.get(), armorStack);
            if (level == 0) continue;

            if (ModEnchantments.EDIBLE.get().canApply(armorStack)) {
                if (player.getFoodStats().needFood()) {
                    Tag<Item> tag = (armorStack.getItem() instanceof CheeseSuitArmorItem) ? ModTags.Items.CHEESE : (armorStack.getItem() instanceof HamSuitArmorItem) ? ModTags.Items.HAM : null;
                    if (tag != null) {
                        ItemStack tagStack = hasTag(player.inventory, tag);
                        if (tagStack != ItemStack.EMPTY && tagStack.isFood()) {
                            if (20 - player.getFoodStats().getFoodLevel() >= tagStack.getItem().getFood().getHealing()) {
                                if (level == 1)
                                    player.getFoodStats().consume(tagStack.getItem(), tagStack);
                                else {
                                    int hunger = tagStack.getItem().getFood().getHealing() * level;
                                    float saturation = tagStack.getItem().getFood().getSaturation() * (level * 1f);
                                    player.getFoodStats().addStats(hunger, saturation);
                                }
                                player.inventory.decrStackSize(player.inventory.getSlotFor(tagStack), level);
                                if (player.world.rand.nextInt(10) < 5)
                                    armorStack.damageItem(level, player, (entity) -> entity.sendBreakAnimation(slot));
                            }
                        } else {
                            player.getFoodStats().addStats(level, (level * 1f) * 2);
                            armorStack.damageItem(level, player, (entity) -> entity.sendBreakAnimation(slot));
                        }
                    }
                }
            }
        }
    }

    private static ItemStack hasTag(PlayerInventory inventory, Tag<Item> itemTag) {
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
}
