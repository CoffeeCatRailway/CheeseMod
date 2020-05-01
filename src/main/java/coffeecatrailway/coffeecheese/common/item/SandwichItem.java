package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.registry.ModFoods;
import coffeecatrailway.coffeecheese.registry.ModItems;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway - Xero: https://github.com/Squishling
 * Created: 28/04/2020
 */
public class SandwichItem extends Item {

    public static final String TAG_INGREDIENTS = "Ingredients";
    public static final String TAG_TOASTED = "Toasted";

    public SandwichItem(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            Set<ItemStack[]> sandwiches = ImmutableSet.of(
                    new ItemStack[]{new ItemStack(ModItems.CHEESE_SLICE.get())},
                    new ItemStack[]{new ItemStack(ModItems.HAM_RAW.get()), new ItemStack(ModItems.CHEESE_SLICE.get())},
                    new ItemStack[]{new ItemStack(ModItems.HAM_COOKED.get()), new ItemStack(ModItems.CHEESE_SLICE.get())},
                    new ItemStack[]{new ItemStack(ModItems.EGG_GREEN.get()), new ItemStack(ModItems.HAM_RAW.get())}
            );

            for (ItemStack[] ingredients : sandwiches) {
                ItemStack sandwich = new ItemStack(ModItems.SANDWICH.get());
                for (ItemStack ingredient : ingredients)
                    addIngredient(sandwich, ingredient);
                items.add(sandwich);

                ItemStack sandwichToasted = sandwich.copy();
                sandwichToasted.getOrCreateTag().putBoolean(TAG_TOASTED, true);
                items.add(sandwichToasted);
            }
        }
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        CompoundNBT nbt = stack.getOrCreateTag();
        ListNBT ingredients = nbt.getList(TAG_INGREDIENTS, Constants.NBT.TAG_COMPOUND);
        ITextComponent name = super.getDisplayName(stack);
        if (ingredients.size() > 0)
            name = ItemStack.read((CompoundNBT) ingredients.get(0)).getDisplayName().appendText(" ").appendSibling(super.getDisplayName(stack));
        if (nbt.getBoolean(TAG_TOASTED))
            name = new StringTextComponent(I18n.format(this.getTranslationKey() + "." + TAG_TOASTED.toLowerCase()) + " ").appendSibling(name);
        return name;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        ListNBT ingredients = stack.getOrCreateTag().getList(TAG_INGREDIENTS, Constants.NBT.TAG_COMPOUND);
        for (INBT ingredient : ingredients)
            tooltip.add(ItemStack.read((CompoundNBT) ingredient).getDisplayName().applyTextStyle(TextFormatting.GRAY));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
        if (!world.isRemote) {
            SoundEvent sound = SoundEvents.ENTITY_GENERIC_EAT;
            Supplier<Float> pitch = () -> 1.0f + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4f;

            entity.playSound(sound, 1.0f, pitch.get());
            world.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), sound, SoundCategory.NEUTRAL, 1.0f, pitch.get());

            Food food = this.getFood(stack);
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;
                if (!player.isCreative()) stack.shrink(1);
                player.getFoodStats().addStats(food.getHealing(), food.getSaturation());
            }

            for (Pair<EffectInstance, Float> pair : food.getEffects())
                if (pair.getLeft() != null && world.rand.nextFloat() < pair.getRight())
                    entity.addPotionEffect(new EffectInstance(pair.getLeft()));
        }

        return stack;
    }

    @Override
    public boolean isFood() {
        return true;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT stackNbt = stack.getOrCreateTag();

        if (!stackNbt.contains(TAG_INGREDIENTS))
            stackNbt.put(TAG_INGREDIENTS, new ListNBT());

        if (!stackNbt.contains(TAG_TOASTED))
            stackNbt.putBoolean(TAG_TOASTED, false);
        return super.initCapabilities(stack, nbt);
    }

    public static ItemStack addIngredient(ItemStack sandwich, ItemStack ingredient) {
        if (ingredient.isFood() && !(ingredient.getItem() instanceof SandwichItem)) {
            ItemStack added = ingredient.copy();
            added.setCount(1);

            ListNBT ingredients = sandwich.getOrCreateTag().getList(TAG_INGREDIENTS, Constants.NBT.TAG_COMPOUND);
            if (ingredients.size() < 4)
                ingredients.add(added.write(new CompoundNBT()));
        }
        return sandwich;
    }

    public static boolean areSandwichesEqual(ItemStack sandwich1, ItemStack sandwich2) {
        if (!(sandwich1.getItem() instanceof SandwichItem) || !(sandwich2.getItem() instanceof SandwichItem))
            return false;
        return ItemStack.areItemStackTagsEqual(sandwich1, sandwich2);
    }

    private Food getFood(ItemStack stack) {
        List<Food> foods = new ArrayList<>();
        CompoundNBT nbt = stack.getOrCreateTag();

        Food bread = ModFoods.BREAD_SLICE;
        if (nbt.getBoolean(TAG_TOASTED))
            bread = ModFoods.TOAST;
        foods.add(bread);
        foods.add(bread);

        ListNBT ingredients = nbt.getList(TAG_INGREDIENTS, Constants.NBT.TAG_COMPOUND);
        for (INBT ingredient : ingredients) {
            ItemStack foodStack = ItemStack.read((CompoundNBT) ingredient);
            if (foodStack.isFood())
                foods.add(foodStack.getItem().getFood());
        }

        return ModFoods.buildCombo(nbt.getBoolean(TAG_TOASTED), foods.toArray(new Food[]{}));
    }
}
