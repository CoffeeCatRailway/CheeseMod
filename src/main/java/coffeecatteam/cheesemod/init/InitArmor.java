package coffeecatteam.cheesemod.init;

import java.util.HashSet;
import java.util.Set;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.armor.ArmorCheese;
import coffeecatteam.cheesemod.objects.armor.ArmorCheeseSuit;
import coffeecatteam.cheesemod.objects.armor.ArmorHamCooked;
import coffeecatteam.cheesemod.objects.armor.ArmorHamRaw;
import coffeecatteam.cheesemod.objects.armor.ArmorHamSuit;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class InitArmor {

	public static final ArmorMaterial CHEESE_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("cheese", Reference.MODID + ":cheese", 17, new int[] { 2, 4, 4, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);
	public static final ArmorMaterial GRILLED_CHEESE_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("grilled_cheese", Reference.MODID + ":grilled_cheese", 20, new int[] { 4, 4, 4, 2 }, 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.8F);

	public static final ArmorMaterial CHEESE_HAT_MATERIAL = EnumHelper.addArmorMaterial("cheese_hat", Reference.MODID + ":cheese_hat", 17, new int[] { 2, 1, 1, 1 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5F);

	public static final ArmorMaterial HAM_RAW_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("ham_raw", Reference.MODID + ":ham_raw", 10, new int[] { 2, 2, 3, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.3F);
	public static final ArmorMaterial HAM_COOKED_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("ham_cooked", Reference.MODID + ":ham_cooked", 10, new int[] { 3, 3, 4, 3 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.4F);

	public static final Item CHEESE_HELMET = new ArmorCheese("cheese_helmet", CHEESE_ARMOR_MATERIAL, 1, EntityEquipmentSlot.HEAD);
	public static final Item CHEESE_CHESTPLATE = new ArmorCheese("cheese_chestplate", CHEESE_ARMOR_MATERIAL, 1, EntityEquipmentSlot.CHEST);
	public static final Item CHEESE_LEGGINGS = new ArmorCheese("cheese_leggings", CHEESE_ARMOR_MATERIAL, 2, EntityEquipmentSlot.LEGS);
	public static final Item CHEESE_BOOTS = new ArmorCheese("cheese_boots", CHEESE_ARMOR_MATERIAL, 1, EntityEquipmentSlot.FEET);
	
	public static final Item GRILLED_CHEESE_HELMET = new ArmorCheese("grilled_cheese_helmet", GRILLED_CHEESE_ARMOR_MATERIAL, 1, EntityEquipmentSlot.HEAD);
	public static final Item GRILLED_CHEESE_CHESTPLATE = new ArmorCheese("grilled_cheese_chestplate", GRILLED_CHEESE_ARMOR_MATERIAL, 1, EntityEquipmentSlot.CHEST);
	public static final Item GRILLED_CHEESE_LEGGINGS = new ArmorCheese("grilled_cheese_leggings", GRILLED_CHEESE_ARMOR_MATERIAL, 2, EntityEquipmentSlot.LEGS);
	public static final Item GRILLED_CHEESE_BOOTS = new ArmorCheese("grilled_cheese_boots", GRILLED_CHEESE_ARMOR_MATERIAL, 1, EntityEquipmentSlot.FEET);

	public static final Item HAM_RAW_HELMET = new ArmorHamRaw("ham_raw_helmet", HAM_RAW_ARMOR_MATERIAL, 1, EntityEquipmentSlot.HEAD);
	public static final Item HAM_RAW_CHESTPLATE = new ArmorHamRaw("ham_raw_chestplate", HAM_RAW_ARMOR_MATERIAL, 1, EntityEquipmentSlot.CHEST);
	public static final Item HAM_RAW_LEGGINGS = new ArmorHamRaw("ham_raw_leggings", HAM_RAW_ARMOR_MATERIAL, 2, EntityEquipmentSlot.LEGS);
	public static final Item HAM_RAW_BOOTS = new ArmorHamRaw("ham_raw_boots", HAM_RAW_ARMOR_MATERIAL, 1, EntityEquipmentSlot.FEET);
	
	public static final Item HAM_COOKED_HELMET = new ArmorHamCooked("ham_cooked_helmet", HAM_COOKED_ARMOR_MATERIAL, 1, EntityEquipmentSlot.HEAD);
	public static final Item HAM_COOKED_CHESTPLATE = new ArmorHamCooked("ham_cooked_chestplate", HAM_COOKED_ARMOR_MATERIAL, 1, EntityEquipmentSlot.CHEST);
	public static final Item HAM_COOKED_LEGGINGS = new ArmorHamCooked("ham_cooked_leggings", HAM_COOKED_ARMOR_MATERIAL, 2, EntityEquipmentSlot.LEGS);
	public static final Item HAM_COOKED_BOOTS = new ArmorHamCooked("ham_cooked_boots", HAM_COOKED_ARMOR_MATERIAL, 1, EntityEquipmentSlot.FEET);

	public static final Item CHEESE_SUIT_HAT = new ArmorCheeseSuit("cheese_suit_hat", CHEESE_HAT_MATERIAL, 1, EntityEquipmentSlot.HEAD);
	public static final Item CHEESE_SUIT_CHESTPLATE = new ArmorCheeseSuit("cheese_suit_chestplate", CHEESE_HAT_MATERIAL, 1, EntityEquipmentSlot.CHEST);

	public static final Item HAM_SUIT_HAT = new ArmorHamSuit("ham_suit_hat", HAM_RAW_ARMOR_MATERIAL, 1, EntityEquipmentSlot.HEAD);
	public static final Item HAM_SUIT_CHESTPLATE = new ArmorHamSuit("ham_suit_chestplate", HAM_RAW_ARMOR_MATERIAL, 1, EntityEquipmentSlot.CHEST);
	
	@EventBusSubscriber(modid = Reference.MODID)
	public static class ArmorRegistrationHandler {
		public static final Set<Item> ITEM_LIST = new HashSet<>();
		
		private static final Set<Item> registeredItemList = new HashSet<>();
		private static final Item[] items = { CHEESE_HELMET, CHEESE_CHESTPLATE, CHEESE_LEGGINGS, CHEESE_BOOTS,
				GRILLED_CHEESE_HELMET, GRILLED_CHEESE_CHESTPLATE, GRILLED_CHEESE_LEGGINGS, GRILLED_CHEESE_BOOTS,
				HAM_RAW_HELMET, HAM_RAW_CHESTPLATE, HAM_RAW_LEGGINGS, HAM_RAW_BOOTS, HAM_COOKED_HELMET,
				HAM_COOKED_CHESTPLATE, HAM_COOKED_LEGGINGS, HAM_COOKED_BOOTS, CHEESE_SUIT_HAT, CHEESE_SUIT_CHESTPLATE,
				HAM_SUIT_HAT, HAM_SUIT_CHESTPLATE };

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> reg = event.getRegistry();

			for (final Item item : items) {
				reg.register(item);
				ITEM_LIST.add(item);
			}
		}
		
		@SubscribeEvent
		public static void registerModels(final ModelRegistryEvent event) {

			for (Item item : ITEM_LIST)
				if (!registeredItemList.contains(item))
					registerItemModel(item);
		}

		private static void registerItemModel(final Item item) {
			final String registryName = item.getRegistryName().toString();
			final ModelResourceLocation location = new ModelResourceLocation(registryName, "inventory");
			registerItemModel(item, location);
		}

		private static void registerItemModel(final Item item, final ModelResourceLocation modelResourceLocation) {
			ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);
			registeredItemList.add(item);
		}
	}
}
