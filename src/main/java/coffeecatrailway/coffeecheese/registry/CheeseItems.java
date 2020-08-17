package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.client.item.StackableFoodRenderer;
import coffeecatrailway.coffeecheese.common.entity.FoodBoatEntity;
import coffeecatrailway.coffeecheese.common.item.*;
import coffeecatrailway.coffeecheese.integration.registrate.CheeseTags;
import coffeecatrailway.coffeecheese.registry.nonregistries.CheeseArmorMaterials;
import coffeecatrailway.coffeecheese.registry.nonregistries.CheeseFoods;
import coffeecatrailway.coffeecheese.registry.nonregistries.CheeseItemTier;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 24/07/2020
 */
public class CheeseItems {

    private static final Logger LOGGER = LogManager.getLogger(CheeseMod.MOD_ID + "-Items");

    /// Food Tools ///
    public static final RegistryEntry<CraftToolItem> CHEESE_CUTTER = REGISTRATE.item("cheese_cutter", prop -> new CraftToolItem(ItemTier.IRON, 1, 2.5f, prop))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).key('s', Tags.Items.RODS_WOODEN).key('i', Tags.Items.INGOTS_IRON)
                    .key('c', CheeseTags.Items.FOOD_SLICE_CHEESE).patternLine("i  ").patternLine("ci ").patternLine("  s")
                    .addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(Tags.Items.INGOTS_IRON)).build(provider))
            .tag(CheeseTags.Items.KNIVES_IRON).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<CraftToolItem> GRINDING_STONES = REGISTRATE.item("grinding_stones", prop -> new CraftToolItem(ItemTier.STONE, 2, 2.7f, prop))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry).addIngredient(Tags.Items.COBBLESTONE)
                    .addCriterion("has_stone", RegistrateRecipeProvider.hasItem(Tags.Items.COBBLESTONE)).build(provider))
            .group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<CraftToolItem> MILK_CURDLER = REGISTRATE.item("milk_curdler", prop -> new CraftToolItem(ItemTier.WOOD, 2, 2.5f, prop))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).key('s', Tags.Items.RODS_WOODEN).key('g', CheeseTags.Items.GEARS_WOODEN).patternLine("g")
                    .patternLine("s").patternLine("s").addCriterion("has_gear", RegistrateRecipeProvider.hasItem(CheeseTags.Items.GEARS_WOODEN)).build(provider))
            .group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<CraftToolItem> ROLLING_PIN = REGISTRATE.item("rolling_pin", prop -> new CraftToolItem(ItemTier.WOOD, 1, 2.5f, prop))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).key('s', Tags.Items.RODS_WOODEN).key('p', ItemTags.PLANKS).patternLine("sps")
                    .addCriterion("has_planks", RegistrateRecipeProvider.hasItem(ItemTags.PLANKS)).build(provider))
            .group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();

    /// Foods ///
    public static final RegistryEntry<Item> BLOCK_O_CHEESE = REGISTRATE.item("block_o_cheese", Item::new).properties(prop -> prop.food(CheeseFoods.BLOCK_O_CHEESE).maxStackSize(16))
            .recipe((ctx, provider) -> {
                ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 2).addIngredient(Items.MILK_BUCKET).addIngredient(MILK_CURDLER.get())
                        .addCriterion("has_milk", RegistrateRecipeProvider.hasItem(Items.MILK_BUCKET)).build(provider);
                ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 9).addIngredient(CheeseTags.Items.CHEESE_BLOCKS)
                        .addCriterion("has_cheese_block", RegistrateRecipeProvider.hasItem(CheeseTags.Items.CHEESE_BLOCKS)).build(provider, CheeseMod.getLocation("block_o_cheese_from_cheese_block"));
            })
            .lang("Block O' Cheese").tag(CheeseTags.Items.CHEESE).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<CheeseBallItem> CHEESE_BALL = REGISTRATE.item("cheese_ball", CheeseBallItem::new).properties(prop -> prop.food(CheeseFoods.CHEESE_BALL).maxStackSize(16))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 2).addIngredient(BLOCK_O_CHEESE.get())
                    .addCriterion("has_cheese", RegistrateRecipeProvider.hasItem(BLOCK_O_CHEESE.get())).build(provider))
            .tag(CheeseTags.Items.CHEESE).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();

    public static final RegistryEntry<Item> CHEESE_SLICE = REGISTRATE.item("cheese_slice", Item::new).properties(prop -> prop.food(CheeseFoods.CHEESE_SLICE).maxStackSize(32))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 3).addIngredient(BLOCK_O_CHEESE.get()).addIngredient(CheeseTags.Items.KNIVES)
                    .addCriterion("has_cheese", RegistrateRecipeProvider.hasItem(BLOCK_O_CHEESE.get())).build(provider))
            .tag(CheeseTags.Items.CHEESE).tag(CheeseTags.Items.FOOD_SLICE_CHEESE).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();

    public static final RegistryEntry<Item> SALT = REGISTRATE.item("salt", Item::new).properties(prop -> prop.food(CheeseFoods.INGREDIENT)).tag(CheeseTags.Items.SALT)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 2).addIngredient(GRINDING_STONES.get())
                    .addIngredient(Ingredient.fromItemListStream(Stream.of(new Ingredient.TagList(Tags.Items.STONE), new Ingredient.TagList(Tags.Items.COBBLESTONE))))
                    .addCriterion("has_gind_stones", RegistrateRecipeProvider.hasItem(GRINDING_STONES.get())).build(provider))
            .group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> FLOUR = REGISTRATE.item("flour", Item::new).properties(prop -> prop.food(CheeseFoods.INGREDIENT)).tag(CheeseTags.Items.FLOUR)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 3).addIngredient(GRINDING_STONES.get()).addIngredient(CheeseTags.Items.WHEAT)
                    .addCriterion("has_wheat", RegistrateRecipeProvider.hasItem(CheeseTags.Items.WHEAT)).build(provider))
            .group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();

    public static final RegistryEntry<Item> BACON_RAW = REGISTRATE.item("bacon_raw", Item::new).properties(prop -> prop.food(CheeseFoods.BACON).maxStackSize(32)).lang("Raw Bacon")
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 2).addIngredient(CheeseTags.Items.KNIVES).addIngredient(CheeseTags.Items.FOOD_SLICE_HAM_RAW)
                    .addCriterion("has_ham", RegistrateRecipeProvider.hasItem(CheeseTags.Items.FOOD_SLICE_HAM_RAW)).build(provider))
            .tag(CheeseTags.Items.BACON).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> BACON_COOKED = REGISTRATE.item("bacon_cooked", Item::new).properties(prop -> prop.food(CheeseFoods.BACON_COOKED).maxStackSize(32)).lang("Bacon")
            .recipe((ctx, provider) -> {
                provider.smelting(DataIngredient.items(BACON_RAW.get()), ctx::getEntry, .2f, 100);
                provider.smoking(DataIngredient.items(BACON_RAW.get()), ctx::getEntry, .2f, 50);
                provider.campfire(DataIngredient.items(BACON_RAW.get()), ctx::getEntry, .2f, 300);
                ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 2).addIngredient(CheeseTags.Items.KNIVES).addIngredient(CheeseTags.Items.FOOD_SLICE_HAM_COOKED)
                        .addCriterion("has_ham", RegistrateRecipeProvider.hasItem(CheeseTags.Items.FOOD_SLICE_HAM_COOKED)).build(provider);
            }).tag(CheeseTags.Items.BACON).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();

    public static final RegistryEntry<Item> BREAD_SLICE = REGISTRATE.item("bread_slice", Item::new).tag(CheeseTags.Items.BREAD)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 3).addIngredient(CheeseTags.Items.KNIVES).addIngredient(Items.BREAD)
                    .addCriterion("has_bread", RegistrateRecipeProvider.hasItem(Items.BREAD)).build(provider))
            .properties(prop -> prop.food(CheeseFoods.BREAD_SLICE)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<SandwichItem> SANDWICH = REGISTRATE.item("sandwich", SandwichItem::new).tag(CheeseTags.Items.BREAD)
            .properties(prop -> prop.maxStackSize(4).setISTER(() -> StackableFoodRenderer::new)).group(() -> CheeseMod.GROUP_FOODS).model(NonNullBiConsumer.noop()).register();

    public static final RegistryEntry<CrackerItem> CRACKER = REGISTRATE.item("cracker", CrackerItem::new).tag(CheeseTags.Items.CRACKER)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 2).addIngredient(CheeseTags.Items.WHEAT).addIngredient(CheeseTags.Items.SALT)
                    .addIngredient(CheeseTags.Items.FLOUR).addCriterion("has_wheat", RegistrateRecipeProvider.hasItem(CheeseTags.Items.WHEAT)).build(provider, CheeseMod.getLocation("cracker_with_ingredients")))
            .properties(prop -> prop.maxStackSize(16).setISTER(() -> StackableFoodRenderer::new)).group(() -> CheeseMod.GROUP_FOODS).model(NonNullBiConsumer.noop()).register();
    public static final RegistryEntry<Item> CRACKER_DUMMY_ITEM = REGISTRATE.item("cracker_dummy_item", Item::new).lang("Dummy Item - Why do you have this?")
            .properties(prop -> prop.food(CheeseFoods.CRACKER).maxStackSize(1).group(null)).model(NonNullBiConsumer.noop()).register();
    public static final RegistryEntry<Item> CRACKER_TOASTED_DUMMY_ITEM = REGISTRATE.item("cracker_toasted_dummy_item", Item::new).lang("Dummy Item - Why do you have this?")
            .properties(prop -> prop.food(CheeseFoods.CRACKER_TOASTED).maxStackSize(1).group(null)).model(NonNullBiConsumer.noop()).register();

    public static final RegistryEntry<Item> DOUGH = REGISTRATE.item("dough", Item::new).tag(CheeseTags.Items.DOUGH)
            .recipe((ctx, provider) -> {
                ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 2).addIngredient(CheeseTags.Items.WHEAT).addIngredient(CheeseTags.Items.SUGAR).addIngredient(CheeseTags.Items.SALT).
                        addIngredient(CheeseTags.Items.FLOUR).addCriterion("has_wheat", RegistrateRecipeProvider.hasItem(CheeseTags.Items.WHEAT)).build(provider);
                provider.smoking(DataIngredient.items(ctx.getEntry()), () -> Items.BREAD, .5f, 50);
            }).properties(prop -> prop.food(CheeseFoods.DOUGH)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();

    public static final RegistryEntry<Item> EGG_CRACKED = REGISTRATE.item("egg_cracked", Item::new).lang("Egg Yolk").tag(Tags.Items.EGGS)
            .properties(prop -> prop.food(CheeseFoods.EGG).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> EGG_COOKED = REGISTRATE.item("egg_cooked", Item::new).lang("Cooked Egg").tag(Tags.Items.EGGS)
            .recipe((ctx, provider) -> {
                provider.smelting(DataIngredient.items(EGG_CRACKED.get()), ctx::getEntry, .15f, 100);
                provider.smoking(DataIngredient.items(EGG_CRACKED.get()), ctx::getEntry, .15f, 50);
                provider.campfire(DataIngredient.items(EGG_CRACKED.get()), ctx::getEntry, .15f, 300);
            })
            .properties(prop -> prop.food(CheeseFoods.EGG_COOKED).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<GreenFoodItem> EGG_GREEN = REGISTRATE.item("egg_green", prop -> new GreenFoodItem(CheeseFoods.EGG_GREEN, 32)).tag(Tags.Items.EGGS)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry).addIngredient(Tags.Items.DYES_GREEN).addIngredient(EGG_CRACKED.get())
                    .addCriterion("has_egg", RegistrateRecipeProvider.hasItem(EGG_CRACKED.get())).build(provider))
            .defaultModel().lang("Green Egg Yolk").register();

    public static final RegistryEntry<Item> HAM_RAW = REGISTRATE.item("ham_raw", Item::new).properties(prop -> prop.food(CheeseFoods.HAM).maxStackSize(32)).lang("Raw Ham")
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 3).addIngredient(CheeseTags.Items.KNIVES).addIngredient(Items.PORKCHOP)
                    .addCriterion("has_knife", RegistrateRecipeProvider.hasItem(CheeseTags.Items.KNIVES)).build(provider))
            .tag(CheeseTags.Items.HAM).tag(CheeseTags.Items.FOOD_SLICE_HAM_RAW).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> HAM_COOKED = REGISTRATE.item("ham_cooked", Item::new).properties(prop -> prop.food(CheeseFoods.HAM_COOKED).maxStackSize(32)).lang("Ham")
            .recipe((ctx, provider) -> {
                ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 3).addIngredient(CheeseTags.Items.KNIVES).addIngredient(Items.COOKED_PORKCHOP)
                        .addCriterion("has_knife", RegistrateRecipeProvider.hasItem(CheeseTags.Items.KNIVES)).build(provider);
                provider.smelting(DataIngredient.tag(CheeseTags.Items.FOOD_SLICE_HAM_RAW), ctx::getEntry, .35f, 200);
                provider.smoking(DataIngredient.tag(CheeseTags.Items.FOOD_SLICE_HAM_RAW), ctx::getEntry, .35f);
                provider.campfire(DataIngredient.tag(CheeseTags.Items.FOOD_SLICE_HAM_RAW), ctx::getEntry, .35f, 600);
            })
            .tag(CheeseTags.Items.HAM).tag(CheeseTags.Items.FOOD_SLICE_HAM_COOKED).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<GreenFoodItem> HAM_GREEN = REGISTRATE.item("ham_green", prop -> new GreenFoodItem(CheeseFoods.HAM_GREEN, 32))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry).addIngredient(Tags.Items.DYES_GREEN).addIngredient(HAM_RAW.get())
                    .addCriterion("has_ham", RegistrateRecipeProvider.hasItem(HAM_RAW.get())).build(provider))
            .tag(CheeseTags.Items.HAM).tag(CheeseTags.Items.FOOD_SLICE_HAM_RAW).lang("Green Ham").defaultModel().register();

    public static final RegistryEntry<Item> TOAST = REGISTRATE.item("toast", Item::new).tag(CheeseTags.Items.TOAST)
            .recipe((ctx, provider) -> {
                provider.smelting(DataIngredient.items(BREAD_SLICE.get()), ctx::getEntry, .2f, 200);
                provider.smoking(DataIngredient.items(BREAD_SLICE.get()), ctx::getEntry, .2f);
                provider.campfire(DataIngredient.items(BREAD_SLICE.get()), ctx::getEntry, .2f, 300);
            }).properties(prop -> prop.food(CheeseFoods.TOAST)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> TOAST_FRENCH = REGISTRATE.item("toast_french", Item::new).lang("French Toast").tag(CheeseTags.Items.TOAST)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry).addIngredient(Tags.Items.EGGS).addIngredient(TOAST.get()).addIngredient(Items.MILK_BUCKET)
                    .addCriterion("has_toast", RegistrateRecipeProvider.hasItem(TOAST.get())).build(provider))
            .properties(prop -> prop.food(CheeseFoods.TOAST_FRENCH)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> TOAST_BACON = REGISTRATE.item("toast_bacon", Item::new).lang("Bacon On Toast").tag(CheeseTags.Items.TOAST)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry).addIngredient(CheeseTags.Items.BACON).addIngredient(TOAST.get())
                    .addCriterion("has_toast", RegistrateRecipeProvider.hasItem(TOAST.get())).build(provider))
            .properties(prop -> prop.food(CheeseFoods.TOAST_BACON)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();

    public static final RegistryEntry<BlockNamedItem> PINEAPPLE_PLANT = REGISTRATE.item("pineapple_plant", prop -> new BlockNamedItem(Blocks.DIRT, prop)) // CheeseBlocks.PINEAPPLE.get()
            .properties(prop -> prop.food(CheeseFoods.PINEAPPLE_PLANT)).group(() -> CheeseMod.GROUP_FOODS).tag(CheeseTags.Items.FOODS_TAB)
            .model((ctx, provider) -> provider.generated(ctx::getEntry, CheeseMod.getLocation("block/pineapple_stage0"))).register();
    public static final RegistryEntry<Item> PINEAPPLE = REGISTRATE.item("pineapple", Item::new).tag(CheeseTags.Items.FOODS_TAB)
            .properties(prop -> prop.food(CheeseFoods.PINEAPPLE).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS)
            .model((ctx, provider) -> provider.generated(ctx::getEntry, CheeseMod.getLocation("block/pineapple_stage4"))).register();
    public static final RegistryEntry<Item> PINEAPPLE_RING = REGISTRATE.item("pineapple_ring", Item::new).tag(CheeseTags.Items.FOODS_TAB)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 4).addIngredient(CheeseTags.Items.KNIVES).addIngredient(PINEAPPLE.get())
                    .addCriterion("has_pineapple", RegistrateRecipeProvider.hasItem(PINEAPPLE.get())).build(provider))
            .properties(prop -> prop.food(CheeseFoods.PINEAPPLE_RING).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> PINEAPPLE_BIT = REGISTRATE.item("pineapple_bit", Item::new).tag(CheeseTags.Items.FOODS_TAB)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 4).addIngredient(CheeseTags.Items.KNIVES).addIngredient(PINEAPPLE_RING.get())
                    .addCriterion("has_pineapple", RegistrateRecipeProvider.hasItem(PINEAPPLE_RING.get())).build(provider))
            .properties(prop -> prop.food(CheeseFoods.PINEAPPLE_BIT).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();

    public static final RegistryEntry<Item> PIZZA = REGISTRATE.item("pizza", Item::new).tag(CheeseTags.Items.PIZZA).tag(CheeseTags.Items.DOUGH)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry).addIngredient(ROLLING_PIN.get()).addIngredient(DOUGH.get())
                    .addCriterion("has_dough", RegistrateRecipeProvider.hasItem(DOUGH.get())).build(provider))
            .properties(prop -> prop.food(CheeseFoods.DOUGH).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> PIZZA_CHEESE = REGISTRATE.item("pizza_cheese", Item::new).lang("Cold All Cheese Pizza").tag(CheeseTags.Items.PIZZA).tag(CheeseTags.Items.CHEESE)
            .properties(prop -> prop.food(CheeseFoods.PIZZA_CHEESE).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> PIZZA_CHEESE_COOKED = REGISTRATE.item("pizza_cheese_cooked", Item::new).lang("All Cheese Pizza").tag(CheeseTags.Items.PIZZA).tag(CheeseTags.Items.CHEESE)
            .properties(prop -> prop.food(CheeseFoods.PIZZA_CHEESE_COOKED).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> PIZZA_CHEESE_HAM = REGISTRATE.item("pizza_cheese_ham", Item::new).lang("Cold Meat Lovers Pizza").tag(CheeseTags.Items.PIZZA).tag(CheeseTags.Items.CHEESE).tag(CheeseTags.Items.HAM)
            .properties(prop -> prop.food(CheeseFoods.PIZZA_CHEESE_HAM).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> PIZZA_CHEESE_HAM_COOKED = REGISTRATE.item("pizza_cheese_ham_cooked", Item::new).lang("Meat Lovers Pizza").tag(CheeseTags.Items.PIZZA).tag(CheeseTags.Items.CHEESE).tag(CheeseTags.Items.HAM)
            .properties(prop -> prop.food(CheeseFoods.PIZZA_CHEESE_HAM_COOKED).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> PIZZA_CHEESE_HAM_PINEAPPLE = REGISTRATE.item("pizza_cheese_ham_pineapple", Item::new).lang("Cold Hawaiian Pizza").tag(CheeseTags.Items.PIZZA).tag(CheeseTags.Items.CHEESE).tag(CheeseTags.Items.HAM)
            .properties(prop -> prop.food(CheeseFoods.PIZZA_CHEESE_HAM_PINEAPPLE).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();
    public static final RegistryEntry<Item> PIZZA_CHEESE_HAM_PINEAPPLE_COOKED = REGISTRATE.item("pizza_cheese_ham_pineapple_cooked", Item::new).lang("Hawaiian Pizza").tag(CheeseTags.Items.PIZZA).tag(CheeseTags.Items.CHEESE).tag(CheeseTags.Items.HAM)
            .properties(prop -> prop.food(CheeseFoods.PIZZA_CHEESE_HAM_PINEAPPLE_COOKED).maxStackSize(32)).group(() -> CheeseMod.GROUP_FOODS).defaultModel().register();

    /// Ingots ///
    public static final RegistryEntry<Item> CHEESE_METAL_INGOT = REGISTRATE.item("cheese_metal_ingot", Item::new).properties(prop -> prop.food(CheeseFoods.INGOT)).defaultModel()
            .recipe((ctx, provider) -> {
                foodMetalIngotRecipe(CheeseTags.Items.FOOD_METAL_ORE_CHEESE).accept(ctx, provider);
                provider.singleItem(DataIngredient.items(CheeseBlocks.CHEESE_METAL_BLOCK.get()), ctx::getEntry, 1, 1);
            }).tag(Tags.Items.INGOTS).register();
    public static final RegistryEntry<Item> GRILLED_CHEESE_METAL_INGOT = REGISTRATE.item("grilled_cheese_metal_ingot", Item::new).properties(prop -> prop.food(CheeseFoods.INGOT)).defaultModel()
            .recipe((ctx, provider) -> {
                foodMetalIngotRecipe(CheeseTags.Items.FOOD_METAL_ORE_GRILLED_CHEESE).accept(ctx, provider);
                provider.singleItem(DataIngredient.items(CheeseBlocks.CHEESE_METAL_BLOCK.get()), ctx::getEntry, 1, 1);
            }).tag(Tags.Items.INGOTS).register();
    public static final RegistryEntry<Item> HAM_RAW_METAL_INGOT = REGISTRATE.item("ham_raw_metal_ingot", Item::new).properties(prop -> prop.food(CheeseFoods.INGOT)).defaultModel()
            .recipe((ctx, provider) -> {
                foodMetalIngotRecipe(CheeseTags.Items.FOOD_METAL_ORE_HAM_RAW).accept(ctx, provider);
                provider.singleItem(DataIngredient.items(CheeseBlocks.CHEESE_METAL_BLOCK.get()), ctx::getEntry, 1, 1);
            }).tag(Tags.Items.INGOTS).register();
    public static final RegistryEntry<Item> HAM_COOKED_METAL_INGOT = REGISTRATE.item("ham_cooked_metal_ingot", Item::new).properties(prop -> prop.food(CheeseFoods.INGOT)).defaultModel()
            .recipe((ctx, provider) -> {
                foodMetalIngotRecipe(CheeseTags.Items.FOOD_METAL_ORE_HAM_COOKED).accept(ctx, provider);
                provider.singleItem(DataIngredient.items(CheeseBlocks.CHEESE_METAL_BLOCK.get()), ctx::getEntry, 1, 1);
            }).tag(Tags.Items.INGOTS).register();

    /// Tools ///
    public static final RegistryEntry<CraftToolItem> KNIFE = REGISTRATE.item("knife", prop -> new CraftToolItem(ItemTier.IRON, 4, -2.1f, prop))
            .recipe(knifeRecipe(Tags.Items.INGOTS_IRON)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).tag(CheeseTags.Items.KNIVES_IRON).register();
    public static final RegistryEntry<CraftToolItem> CHEESE_KNIFE = REGISTRATE.item("cheese_knife", prop -> new CraftToolItem(CheeseItemTier.CHEESE_METAL, 5, -2.7f, prop))
            .recipe(knifeRecipe(CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).tag(CheeseTags.Items.KNIVES_CHEESE).register();
    public static final RegistryEntry<CraftToolItem> GRILLED_CHEESE_KNIFE = REGISTRATE.item("grilled_cheese_knife", prop -> new CraftToolItem(CheeseItemTier.GRILLED_CHEESE_METAL, 6, -3.5f, prop))
            .recipe(knifeRecipe(GRILLED_CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).tag(CheeseTags.Items.KNIVES_CHEESE).register();
    public static final RegistryEntry<CraftToolItem> HAM_RAW_KNIFE = REGISTRATE.item("ham_raw_knife", prop -> new CraftToolItem(CheeseItemTier.HAM_RAW_METAL, 5, -2.9f, prop))
            .recipe(knifeRecipe(HAM_RAW_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).tag(CheeseTags.Items.KNIVES_HAM).register();
    public static final RegistryEntry<CraftToolItem> HAM_COOKED_KNIFE = REGISTRATE.item("ham_cooked_knife", prop -> new CraftToolItem(CheeseItemTier.HAM_COOKED_METAL, 6, -3.0f, prop))
            .recipe(knifeRecipe(HAM_COOKED_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).tag(CheeseTags.Items.KNIVES_HAM).register();

    public static final RegistryEntry<PickaxeItem> CHEESE_METAL_PICKAXE = REGISTRATE.item("cheese_metal_pickaxe", prop -> new PickaxeItem(CheeseItemTier.CHEESE_METAL, 1, -2.8f, prop))
            .recipe(pickaxeRecipe(CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<AxeItem> CHEESE_METAL_AXE = REGISTRATE.item("cheese_metal_axe", prop -> new AxeItem(CheeseItemTier.CHEESE_METAL, 6, -3.1f, prop))
            .recipe(axeRecipe(CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<HoeItem> CHEESE_METAL_HOE = REGISTRATE.item("cheese_metal_hoe", prop -> new HoeItem(CheeseItemTier.CHEESE_METAL, -2, -1.0f, prop))
            .recipe(hoeRecipe(CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<ShovelItem> CHEESE_METAL_SHOVEL = REGISTRATE.item("cheese_metal_shovel", prop -> new ShovelItem(CheeseItemTier.CHEESE_METAL, 1.5f, -3.0f, prop))
            .recipe(shovelRecipe(CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<SwordItem> CHEESE_METAL_SWORD = REGISTRATE.item("cheese_metal_sword", prop -> new SwordItem(CheeseItemTier.CHEESE_METAL, 3, -2.4f, prop))
            .recipe(swordRecipe(CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();

    public static final RegistryEntry<PickaxeItem> GRILLED_CHEESE_METAL_PICKAXE = REGISTRATE.item("grilled_cheese_metal_pickaxe", prop -> new PickaxeItem(CheeseItemTier.GRILLED_CHEESE_METAL, 2, -2.0f, prop))
            .recipe(pickaxeRecipe(GRILLED_CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<AxeItem> GRILLED_CHEESE_METAL_AXE = REGISTRATE.item("grilled_cheese_metal_axe", prop -> new AxeItem(CheeseItemTier.GRILLED_CHEESE_METAL, 7, -2.3f, prop))
            .recipe(axeRecipe(GRILLED_CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<HoeItem> GRILLED_CHEESE_METAL_HOE = REGISTRATE.item("grilled_cheese_metal_hoe", prop -> new HoeItem(CheeseItemTier.GRILLED_CHEESE_METAL, -2, -1.0f, prop))
            .recipe(hoeRecipe(GRILLED_CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<ShovelItem> GRILLED_CHEESE_METAL_SHOVEL = REGISTRATE.item("grilled_cheese_metal_shovel", prop -> new ShovelItem(CheeseItemTier.GRILLED_CHEESE_METAL, 2.5f, -2.2f, prop))
            .recipe(shovelRecipe(GRILLED_CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<SwordItem> GRILLED_CHEESE_METAL_SWORD = REGISTRATE.item("grilled_cheese_metal_sword", prop -> new SwordItem(CheeseItemTier.GRILLED_CHEESE_METAL, 4, -1.6f, prop))
            .recipe(swordRecipe(GRILLED_CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();

    public static final RegistryEntry<PickaxeItem> HAM_RAW_METAL_PICKAXE = REGISTRATE.item("ham_raw_metal_pickaxe", prop -> new PickaxeItem(CheeseItemTier.HAM_RAW_METAL, 1, -2.7f, prop))
            .recipe(pickaxeRecipe(HAM_RAW_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<AxeItem> HAM_RAW_METAL_AXE = REGISTRATE.item("ham_raw_metal_axe", prop -> new AxeItem(CheeseItemTier.HAM_RAW_METAL, 6, -3.0f, prop))
            .recipe(axeRecipe(HAM_RAW_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<HoeItem> HAM_RAW_METAL_HOE = REGISTRATE.item("ham_raw_metal_hoe", prop -> new HoeItem(CheeseItemTier.HAM_RAW_METAL, -2, -1.0f, prop))
            .recipe(hoeRecipe(HAM_RAW_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<ShovelItem> HAM_RAW_METAL_SHOVEL = REGISTRATE.item("ham_raw_metal_shovel", prop -> new ShovelItem(CheeseItemTier.HAM_RAW_METAL, 1.8f, -2.9f, prop))
            .recipe(shovelRecipe(HAM_RAW_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<SwordItem> HAM_RAW_METAL_SWORD = REGISTRATE.item("ham_raw_metal_sword", prop -> new SwordItem(CheeseItemTier.HAM_RAW_METAL, 3, -2.3f, prop))
            .recipe(swordRecipe(HAM_RAW_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();

    public static final RegistryEntry<PickaxeItem> HAM_COOKED_METAL_PICKAXE = REGISTRATE.item("ham_cooked_metal_pickaxe", prop -> new PickaxeItem(CheeseItemTier.HAM_COOKED_METAL, 2, -2.6f, prop))
            .recipe(pickaxeRecipe(HAM_COOKED_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<AxeItem> HAM_COOKED_METAL_AXE = REGISTRATE.item("ham_cooked_metal_axe", prop -> new AxeItem(CheeseItemTier.HAM_COOKED_METAL, 7, -2.9f, prop))
            .recipe(axeRecipe(HAM_COOKED_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<HoeItem> HAM_COOKED_METAL_HOE = REGISTRATE.item("ham_cooked_metal_hoe", prop -> new HoeItem(CheeseItemTier.HAM_COOKED_METAL, -2, -1.0f, prop))
            .recipe(hoeRecipe(HAM_COOKED_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<ShovelItem> HAM_COOKED_METAL_SHOVEL = REGISTRATE.item("ham_cooked_metal_shovel", prop -> new ShovelItem(CheeseItemTier.HAM_COOKED_METAL, 2.9f, -2.8f, prop))
            .recipe(shovelRecipe(HAM_COOKED_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();
    public static final RegistryEntry<SwordItem> HAM_COOKED_METAL_SWORD = REGISTRATE.item("ham_cooked_metal_sword", prop -> new SwordItem(CheeseItemTier.HAM_COOKED_METAL, 4, -2.2f, prop))
            .recipe(swordRecipe(HAM_COOKED_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).model((ctx, provider) -> provider.handheld(ctx::getEntry)).register();

    public static final RegistryEntry<Item> CHEESE_BIT = REGISTRATE.item("cheese_bit", Item::new).defaultModel()
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 2).addCriterion("has_cutter", RegistrateRecipeProvider.hasItem(CHEESE_CUTTER.get()))
                    .addIngredient(CHEESE_CUTTER.get()).addIngredient(BLOCK_O_CHEESE.get()).build(provider)).register();
    public static final RegistryEntry<Item> HAM_BIT = REGISTRATE.item("ham_bit", Item::new).defaultModel()
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx::getEntry, 2).addCriterion("has_cutter", RegistrateRecipeProvider.hasItem(CHEESE_CUTTER.get()))
                    .addIngredient(CHEESE_CUTTER.get()).addIngredient(Ingredient.fromItems(Items.PORKCHOP, Items.COOKED_PORKCHOP)).build(provider)).register();

    public static final RegistryEntry<Item> WOODEN_GEAR = REGISTRATE.item("wooden_gear", Item::new).defaultModel()
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_sticks", RegistrateRecipeProvider.hasItem(Tags.Items.RODS_WOODEN))
                    .key('s', Tags.Items.RODS_WOODEN).key('p', ItemTags.PLANKS).patternLine(" s ").patternLine("sps").patternLine(" s ").build(provider)).tag(CheeseTags.Items.GEARS_WOODEN).register();

    public static final RegistryEntry<OilCatcherItem> OIL_CATCHER = REGISTRATE.item("oil_catcher", OilCatcherItem::new).group(() -> CheeseMod.GROUP_ARMOR_TOOLS)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_bucket", RegistrateRecipeProvider.hasItem(Items.BUCKET))
                    .key('i', Items.IRON_INGOT).key('b', Items.BUCKET).patternLine("i i").patternLine("ibi").build(provider))
            .model(NonNullBiConsumer.noop()).register();

    public static final RegistryEntry<MagicFoodStickItem> MAGIC_FOOD_STICK = REGISTRATE.item("magic_food_stick", MagicFoodStickItem::new).properties(prop -> prop.rarity(Rarity.RARE).maxStackSize(1).maxDamage(20))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_food_blocks", RegistrateRecipeProvider.hasItem(CheeseTags.Items.FOOD_BLOCKS))
                    .key('s', Tags.Items.RODS_WOODEN).key('l', ItemTags.LOGS).key('c', CheeseTags.Items.CHEESE_BLOCKS).key('g', CheeseTags.Items.GRILLED_CHEESE_BLOCKS)
                    .key('r', CheeseTags.Items.HAM_RAW_BLOCKS).key('h', CheeseTags.Items.HAM_COOKED_BLOCKS)
                    .patternLine("clr").patternLine("hsg").patternLine(" s ").build(provider))
            .model(NonNullBiConsumer.noop()).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).register();

    /// Armor ///
    public static final RegistryEntry<ArmorItem> CHEESE_METAL_HELMET = REGISTRATE.item("cheese_metal_helmet", prop -> new ArmorItem(CheeseArmorMaterials.CHEESE, EquipmentSlotType.HEAD, prop))
            .recipe(helmetRecipe(CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> CHEESE_METAL_CHESTPLATE = REGISTRATE.item("cheese_metal_chestplate", prop -> new ArmorItem(CheeseArmorMaterials.CHEESE, EquipmentSlotType.CHEST, prop))
            .recipe(chestplateRecipe(CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> CHEESE_METAL_LEGGINGS = REGISTRATE.item("cheese_metal_leggings", prop -> new ArmorItem(CheeseArmorMaterials.CHEESE, EquipmentSlotType.LEGS, prop))
            .recipe(leggingsRecipe(CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> CHEESE_METAL_BOOTS = REGISTRATE.item("cheese_metal_boots", prop -> new ArmorItem(CheeseArmorMaterials.CHEESE, EquipmentSlotType.FEET, prop))
            .recipe(bootsRecipe(CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();

    public static final RegistryEntry<ArmorItem> GRILLED_CHEESE_METAL_HELMET = REGISTRATE.item("grilled_cheese_metal_helmet", prop -> new ArmorItem(CheeseArmorMaterials.GRILLED_CHEESE, EquipmentSlotType.HEAD, prop))
            .recipe(helmetRecipe(GRILLED_CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> GRILLED_CHEESE_METAL_CHESTPLATE = REGISTRATE.item("grilled_cheese_metal_chestplate", prop -> new ArmorItem(CheeseArmorMaterials.GRILLED_CHEESE, EquipmentSlotType.CHEST, prop))
            .recipe(chestplateRecipe(GRILLED_CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> GRILLED_CHEESE_METAL_LEGGINGS = REGISTRATE.item("grilled_cheese_metal_leggings", prop -> new ArmorItem(CheeseArmorMaterials.GRILLED_CHEESE, EquipmentSlotType.LEGS, prop))
            .recipe(leggingsRecipe(GRILLED_CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> GRILLED_CHEESE_METAL_BOOTS = REGISTRATE.item("grilled_cheese_metal_boots", prop -> new ArmorItem(CheeseArmorMaterials.GRILLED_CHEESE, EquipmentSlotType.FEET, prop))
            .recipe(bootsRecipe(GRILLED_CHEESE_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();

    public static final RegistryEntry<ArmorItem> HAM_RAW_METAL_HELMET = REGISTRATE.item("ham_raw_metal_helmet", prop -> new ArmorItem(CheeseArmorMaterials.HAM_RAW, EquipmentSlotType.HEAD, prop))
            .recipe(helmetRecipe(HAM_RAW_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> HAM_RAW_METAL_CHESTPLATE = REGISTRATE.item("ham_raw_metal_chestplate", prop -> new ArmorItem(CheeseArmorMaterials.HAM_RAW, EquipmentSlotType.CHEST, prop))
            .recipe(chestplateRecipe(HAM_RAW_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> HAM_RAW_METAL_LEGGINGS = REGISTRATE.item("ham_raw_metal_leggings", prop -> new ArmorItem(CheeseArmorMaterials.HAM_RAW, EquipmentSlotType.LEGS, prop))
            .recipe(leggingsRecipe(HAM_RAW_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> HAM_RAW_METAL_BOOTS = REGISTRATE.item("ham_raw_metal_boots", prop -> new ArmorItem(CheeseArmorMaterials.HAM_RAW, EquipmentSlotType.FEET, prop))
            .recipe(bootsRecipe(HAM_RAW_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();

    public static final RegistryEntry<ArmorItem> HAM_COOKED_METAL_HELMET = REGISTRATE.item("ham_cooked_metal_helmet", prop -> new ArmorItem(CheeseArmorMaterials.HAM_COOKED, EquipmentSlotType.HEAD, prop))
            .recipe(helmetRecipe(HAM_COOKED_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> HAM_COOKED_METAL_CHESTPLATE = REGISTRATE.item("ham_cooked_metal_chestplate", prop -> new ArmorItem(CheeseArmorMaterials.HAM_COOKED, EquipmentSlotType.CHEST, prop))
            .recipe(chestplateRecipe(HAM_COOKED_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> HAM_COOKED_METAL_LEGGINGS = REGISTRATE.item("ham_cooked_metal_leggings", prop -> new ArmorItem(CheeseArmorMaterials.HAM_COOKED, EquipmentSlotType.LEGS, prop))
            .recipe(leggingsRecipe(HAM_COOKED_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<ArmorItem> HAM_COOKED_METAL_BOOTS = REGISTRATE.item("ham_cooked_metal_boots", prop -> new ArmorItem(CheeseArmorMaterials.HAM_COOKED, EquipmentSlotType.FEET, prop))
            .recipe(bootsRecipe(HAM_COOKED_METAL_INGOT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();

    public static final RegistryEntry<CheeseSuitItem> CHEESE_SUIT_HAT = REGISTRATE.item("cheese_suit_hat", prop -> new CheeseSuitItem(CheeseArmorMaterials.CHEESE_SUIT, EquipmentSlotType.HEAD, prop))
            .recipe(helmetRecipe(CHEESE_BIT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<CheeseSuitItem> CHEESE_SUIT_CHESTPLATE = REGISTRATE.item("cheese_suit_chestplate", prop -> new CheeseSuitItem(CheeseArmorMaterials.CHEESE_SUIT, EquipmentSlotType.CHEST, prop))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_bit", RegistrateRecipeProvider.hasItem(CHEESE_BIT.get()))
                    .key('s', Tags.Items.RODS_WOODEN).key('b', CHEESE_BIT.get()).patternLine("bsb").patternLine("s s").patternLine("bsb").build(provider))
            .group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();

    public static final RegistryEntry<HamSuitItem> HAM_SUIT_HAT = REGISTRATE.item("ham_suit_hat", prop -> new HamSuitItem(CheeseArmorMaterials.HAM_SUIT, EquipmentSlotType.HEAD, prop))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_bit", RegistrateRecipeProvider.hasItem(HAM_BIT.get()))
                    .key('b', HAM_BIT.get()).patternLine("bb").patternLine("bb").build(provider))
            .group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<HamSuitItem> HAM_SUIT_CHESTPLATE = REGISTRATE.item("ham_suit_chestplate", prop -> new HamSuitItem(CheeseArmorMaterials.HAM_SUIT, EquipmentSlotType.CHEST, prop))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_bit", RegistrateRecipeProvider.hasItem(HAM_BIT.get()))
                    .key('s', Tags.Items.RODS_WOODEN).key('b', HAM_BIT.get()).patternLine("b b").patternLine("sbs").patternLine("bbb").build(provider))
            .group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();
    public static final RegistryEntry<HamSuitItem> HAM_SUIT_BOOTS = REGISTRATE.item("ham_suit_boots", prop -> new HamSuitItem(CheeseArmorMaterials.HAM_SUIT, EquipmentSlotType.FEET, prop))
            .recipe(bootsRecipe(HAM_BIT)).group(() -> CheeseMod.GROUP_ARMOR_TOOLS).defaultModel().register();

    /// Boats ///
    public static final RegistryEntry<FoodBoatItem> BOAT_CHEESE = REGISTRATE.item("cheese_boat", prop -> new FoodBoatItem(FoodBoatEntity.Type.CHEESE, prop))
            .properties(prop -> prop.maxStackSize(1)).defaultModel().recipe(boatRecipe(CheeseBlocks.CHEESE_PLANKS)).tag(ItemTags.BOATS).register();
    public static final RegistryEntry<FoodBoatItem> BOAT_GRILLED_CHEESE = REGISTRATE.item("grilled_cheese_boat", prop -> new FoodBoatItem(FoodBoatEntity.Type.GRILLED_CHEESE, prop))
            .properties(prop -> prop.maxStackSize(1)).defaultModel().recipe(boatRecipe(CheeseBlocks.GRILLED_CHEESE_PLANKS)).tag(ItemTags.BOATS).register();
    public static final RegistryEntry<FoodBoatItem> BOAT_HAM_RAW = REGISTRATE.item("ham_raw_boat", prop -> new FoodBoatItem(FoodBoatEntity.Type.HAM_RAW, prop))
            .properties(prop -> prop.maxStackSize(1)).defaultModel().recipe(boatRecipe(CheeseBlocks.HAM_RAW_PLANKS)).tag(ItemTags.BOATS).register();
    public static final RegistryEntry<FoodBoatItem> BOAT_HAM_COOKED = REGISTRATE.item("ham_cooked_boat", prop -> new FoodBoatItem(FoodBoatEntity.Type.HAM_COOKED, prop))
            .properties(prop -> prop.maxStackSize(1)).defaultModel().recipe(boatRecipe(CheeseBlocks.HAM_COOKED_PLANKS)).tag(ItemTags.BOATS).register();

    private static NonNullBiConsumer<DataGenContext<Item, Item>, RegistrateRecipeProvider> foodMetalIngotRecipe(ITag.INamedTag<Item> ore) {
        return (ctx, provider) -> provider.smeltingAndBlasting(DataIngredient.tag(ore), ctx::getEntry, .7f);
    }

    private static NonNullBiConsumer<DataGenContext<Item, FoodBoatItem>, RegistrateRecipeProvider> boatRecipe(Supplier<Block> planks) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_planks", RegistrateRecipeProvider.hasItem(planks.get()))
                .key('b', planks.get())
                .patternLine("b b")
                .patternLine("bbb").build(provider);
    }

    private static NonNullBiConsumer<DataGenContext<Item, CraftToolItem>, RegistrateRecipeProvider> knifeRecipe(ITag.INamedTag<Item> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot))
                .key('s', Tags.Items.RODS_WOODEN).key('i', ingot)
                .patternLine("i")
                .patternLine("s").build(provider);
    }

    private static NonNullBiConsumer<DataGenContext<Item, CraftToolItem>, RegistrateRecipeProvider> knifeRecipe(Supplier<? extends IItemProvider> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot.get()))
                .key('s', Tags.Items.RODS_WOODEN).key('i', ingot.get())
                .patternLine("i")
                .patternLine("s").build(provider);
    }

    private static NonNullBiConsumer<DataGenContext<Item, PickaxeItem>, RegistrateRecipeProvider> pickaxeRecipe(Supplier<? extends IItemProvider> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot.get()))
                .key('s', Tags.Items.RODS_WOODEN).key('i', ingot.get())
                .patternLine("iii")
                .patternLine(" s ")
                .patternLine(" s ").build(provider);
    }

    private static NonNullBiConsumer<DataGenContext<Item, AxeItem>, RegistrateRecipeProvider> axeRecipe(Supplier<? extends IItemProvider> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot.get()))
                .key('s', Tags.Items.RODS_WOODEN).key('i', ingot.get())
                .patternLine("ii")
                .patternLine("is")
                .patternLine(" s").build(provider);
    }

    private static NonNullBiConsumer<DataGenContext<Item, HoeItem>, RegistrateRecipeProvider> hoeRecipe(Supplier<? extends IItemProvider> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot.get()))
                .key('s', Tags.Items.RODS_WOODEN).key('i', ingot.get())
                .patternLine("ii")
                .patternLine(" s")
                .patternLine(" s").build(provider);
    }

    private static NonNullBiConsumer<DataGenContext<Item, ShovelItem>, RegistrateRecipeProvider> shovelRecipe(Supplier<? extends IItemProvider> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot.get()))
                .key('s', Tags.Items.RODS_WOODEN).key('i', ingot.get())
                .patternLine("i")
                .patternLine("s")
                .patternLine("s").build(provider);
    }

    private static NonNullBiConsumer<DataGenContext<Item, SwordItem>, RegistrateRecipeProvider> swordRecipe(Supplier<? extends IItemProvider> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot.get()))
                .key('s', Tags.Items.RODS_WOODEN).key('i', ingot.get())
                .patternLine("i")
                .patternLine("i")
                .patternLine("s").build(provider);
    }

    private static <T extends ArmorItem> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> helmetRecipe(Supplier<? extends IItemProvider> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot.get())).key('i', ingot.get())
                .patternLine("iii")
                .patternLine("i i").build(provider);
    }

    private static <T extends ArmorItem> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> chestplateRecipe(Supplier<? extends IItemProvider> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot.get())).key('i', ingot.get())
                .patternLine("i i")
                .patternLine("iii")
                .patternLine("iii").build(provider);
    }

    private static <T extends ArmorItem> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> leggingsRecipe(Supplier<? extends IItemProvider> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot.get())).key('i', ingot.get())
                .patternLine("iii")
                .patternLine("i i")
                .patternLine("i i").build(provider);
    }

    private static <T extends ArmorItem> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> bootsRecipe(Supplier<? extends IItemProvider> ingot) {
        return (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx::getEntry).addCriterion("has_ingot", RegistrateRecipeProvider.hasItem(ingot.get())).key('i', ingot.get())
                .patternLine("i i")
                .patternLine("i i").build(provider);
    }

    public static void load() {
        LOGGER.info("Items registered");
    }
}
