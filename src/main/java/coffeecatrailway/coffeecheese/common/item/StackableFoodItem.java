package coffeecatrailway.coffeecheese.common.item;

import coffeecatrailway.coffeecheese.ModCheeseConfig;
import coffeecatrailway.coffeecheese.registry.ModFoods;
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
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway - Xero: https://github.com/Squishling
 * Created: 2/05/2020
 */
public class StackableFoodItem extends Item {

    public static final String TAG_INGREDIENTS = "Ingredients";
    public static final String TAG_TOASTED = "Toasted";

    public final FoodProperties foodProperties;

    private ItemStack foodStack;
    private Food food;

    public StackableFoodItem(Properties properties, FoodProperties foodProperties) {
        super(properties.food(foodProperties.getBaseFood()));
        this.foodProperties = foodProperties;
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

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            for (Supplier<? extends Item>[] ingredients : this.foodProperties.getFillCreativeTab()) {
                ItemStack stack = new ItemStack(this);
                for (Supplier<? extends Item> ingredient : ingredients)
                    addIngredient(stack, new ItemStack(ingredient.get()));
                items.add(stack);

                ItemStack sandwichToasted = stack.copy();
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
        if (ModList.get().isLoaded("appleskin")) {
            ItemStack copy = stack.copy();
            if (this.foodStack != copy)
                this.foodStack = copy;
        }

        ListNBT ingredients = stack.getOrCreateTag().getList(TAG_INGREDIENTS, Constants.NBT.TAG_COMPOUND);
        for (INBT ingredient : ingredients)
            tooltip.add(ItemStack.read((CompoundNBT) ingredient).getDisplayName().applyTextStyle(TextFormatting.GRAY));
    }

    @Nullable
    @Override
    public Food getFood() {
        if (ModList.get().isLoaded("appleskin")) {
            Food food = this.getFood(foodStack);
            if (this.food != food)
                this.food = food;
            return this.food;
        } else
            return super.getFood();
    }

    @Override
    public boolean isFood() {
        return true;
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

    private Food getFood(ItemStack stack) {
        List<Food> foods = new ArrayList<>();
        CompoundNBT nbt = stack.getOrCreateTag();

        Food bread = this.foodProperties.getBaseFood();
        if (nbt.getBoolean(TAG_TOASTED))
            bread = this.foodProperties.getToastedFood();
        foods.add(bread);
        if (this.foodProperties.hasTwoSides())
            foods.add(bread);

        ListNBT ingredients = nbt.getList(TAG_INGREDIENTS, Constants.NBT.TAG_COMPOUND);
        for (INBT ingredient : ingredients) {
            ItemStack foodStack = ItemStack.read((CompoundNBT) ingredient);
            if (foodStack.isFood())
                foods.add(foodStack.getItem().getFood());
        }

        return ModFoods.buildCombo(0.2d, nbt.getBoolean(TAG_TOASTED), foods.toArray(new Food[]{}));
    }

    public static ItemStack addIngredient(ItemStack stack, ItemStack ingredient) {
        if (ingredient.isFood() && !(ingredient.getItem() instanceof StackableFoodItem)) {
            ItemStack added = ingredient.copy();
            added.setCount(1);

            ListNBT ingredients = stack.getOrCreateTag().getList(TAG_INGREDIENTS, Constants.NBT.TAG_COMPOUND);
            if (ingredients.size() < ModCheeseConfig.stackableFoodIngredientCount.get())
                ingredients.add(added.write(new CompoundNBT()));
        }
        return stack;
    }

    public static boolean areStacksEqual(ItemStack stack1, ItemStack stack2) {
        if (!(stack1.getItem() instanceof StackableFoodItem) || !(stack2.getItem() instanceof StackableFoodItem))
            return false;
        return ItemStack.areItemStackTagsEqual(stack1, stack2);
    }

    public static class FoodProperties {

        private final Set<Supplier<? extends Item>[]> fillCreativeTab;

        private final Food baseFood;
        private final Food toastedFood;
        private final Supplier<? extends Item> baseSide;
        private final Supplier<? extends Item> toastedSide;

        private final boolean hasTwoSides;

        public FoodProperties(Set<Supplier<? extends Item>[]> fillCreativeTab, Food baseFood, Food toastedFood, Supplier<? extends Item> baseSide, Supplier<? extends Item> toastedSide, boolean hasTwoSides) {
            this.fillCreativeTab = fillCreativeTab;

            this.baseFood = baseFood;
            this.toastedFood = toastedFood;
            this.baseSide = baseSide;
            this.toastedSide = toastedSide;

            this.hasTwoSides = hasTwoSides;
        }

        public Set<Supplier<? extends Item>[]> getFillCreativeTab() {
            return fillCreativeTab;
        }

        public Food getBaseFood() {
            return baseFood;
        }

        public Food getToastedFood() {
            return toastedFood;
        }

        public Supplier<? extends Item> getBaseSide() {
            return baseSide;
        }

        public Supplier<? extends Item> getToastedSide() {
            return toastedSide;
        }

        public boolean hasTwoSides() {
            return hasTwoSides;
        }
    }
}
