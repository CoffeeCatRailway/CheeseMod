package coffeecatrailway.coffeecheese.registry;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.block.DoubleFoodPlantBlock;
import coffeecatrailway.coffeecheese.common.block.FoodGrassBlock;
import coffeecatrailway.coffeecheese.common.block.TallFoodGrassBlock;
import coffeecatrailway.coffeecheese.integration.registrate.CheeseTags;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.*;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

import static coffeecatrailway.coffeecheese.CheeseMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 26/07/2020
 */
public class CheeseBlocks {

    private static final Logger LOGGER = LogManager.getLogger(CheeseMod.MOD_ID + "-Blocks");

    /// METAL ///
    private static final NonNullUnaryOperator<Block.Properties> ORE_PROPS = prop -> prop.sound(SoundType.STONE).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE);

    public static final RegistryEntry<Block> CHEESE_METAL_ORE = REGISTRATE.object("cheese_metal_ore").block(Block::new).properties(ORE_PROPS).tag(CheeseTags.Blocks.FOOD_METAL_ORE_CHEESE)
            .defaultBlockstate().defaultLoot().item().tag(CheeseTags.Items.FOOD_METAL_ORE_CHEESE).build().register();
    public static final RegistryEntry<Block> CHEESE_METAL_ORE_NETHER = REGISTRATE.object("cheese_metal_ore_nether").block(Block::new).properties(ORE_PROPS).tag(CheeseTags.Blocks.FOOD_METAL_ORE_CHEESE)
            .defaultBlockstate().defaultLoot().lang("Nether Cheese Metal Ore").item().tag(CheeseTags.Items.FOOD_METAL_ORE_CHEESE).build().register();

    public static final RegistryEntry<Block> GRILLED_CHEESE_METAL_ORE = REGISTRATE.object("grilled_cheese_metal_ore").block(Block::new).properties(ORE_PROPS).tag(CheeseTags.Blocks.FOOD_METAL_ORE_GRILLED_CHEESE)
            .defaultBlockstate().defaultLoot().item().tag(CheeseTags.Items.FOOD_METAL_ORE_GRILLED_CHEESE).build().register();
    public static final RegistryEntry<Block> GRILLED_CHEESE_METAL_ORE_NETHER = REGISTRATE.object("grilled_cheese_metal_ore_nether").block(Block::new).properties(ORE_PROPS).tag(CheeseTags.Blocks.FOOD_METAL_ORE_GRILLED_CHEESE)
            .defaultBlockstate().defaultLoot().lang("Nether Grilled Cheese Metal Ore").item().tag(CheeseTags.Items.FOOD_METAL_ORE_GRILLED_CHEESE).build().register();

    public static final RegistryEntry<Block> HAM_RAW_METAL_ORE = REGISTRATE.object("ham_raw_metal_ore").block(Block::new).properties(ORE_PROPS).tag(CheeseTags.Blocks.FOOD_METAL_ORE_HAM_RAW)
            .defaultBlockstate().defaultLoot().lang("Ham Metal Ore").item().tag(CheeseTags.Items.FOOD_METAL_ORE_HAM_RAW).build().register();
    public static final RegistryEntry<Block> HAM_RAW_METAL_ORE_NETHER = REGISTRATE.object("ham_raw_metal_ore_nether").block(Block::new).properties(ORE_PROPS).tag(CheeseTags.Blocks.FOOD_METAL_ORE_HAM_RAW)
            .defaultBlockstate().defaultLoot().lang("Nether Ham Metal Ore").item().tag(CheeseTags.Items.FOOD_METAL_ORE_HAM_RAW).build().register();

    public static final RegistryEntry<Block> HAM_COOKED_METAL_ORE = REGISTRATE.object("ham_cooked_metal_ore").block(Block::new).properties(ORE_PROPS).tag(CheeseTags.Blocks.FOOD_METAL_ORE_HAM_COOKED)
            .defaultBlockstate().defaultLoot().lang("Cooked Ham Metal Ore").item().tag(CheeseTags.Items.FOOD_METAL_ORE_HAM_COOKED).build().register();
    public static final RegistryEntry<Block> HAM_COOKED_METAL_ORE_NETHER = REGISTRATE.object("ham_cooked_metal_ore_nether").block(Block::new).properties(ORE_PROPS).tag(CheeseTags.Blocks.FOOD_METAL_ORE_HAM_COOKED)
            .defaultBlockstate().defaultLoot().lang("Nether Cooked Ham Metal Ore").item().tag(CheeseTags.Items.FOOD_METAL_ORE_HAM_COOKED).build().register();

    private static final NonNullUnaryOperator<Block.Properties> METAL_PROPS = prop -> prop.sound(SoundType.METAL).hardnessAndResistance(5.0f, 6.0f).harvestTool(ToolType.PICKAXE);

    public static final RegistryEntry<Block> CHEESE_METAL_BLOCK = REGISTRATE.object("cheese_metal_block").block(Block::new).properties(METAL_PROPS)
            .recipe((ctx, provider) -> provider.square(DataIngredient.items(CheeseItems.CHEESE_METAL_INGOT.get()), ctx::getEntry, false)).tag(BlockTags.BEACON_BASE_BLOCKS)
            .defaultBlockstate().defaultLoot().item().build().register();
    public static final RegistryEntry<Block> GRILLED_CHEESE_METAL_BLOCK = REGISTRATE.object("grilled_cheese_metal_block").block(Block::new).properties(METAL_PROPS)
            .recipe((ctx, provider) -> provider.square(DataIngredient.items(CheeseItems.GRILLED_CHEESE_METAL_INGOT.get()), ctx::getEntry, false)).tag(BlockTags.BEACON_BASE_BLOCKS)
            .defaultBlockstate().defaultLoot().item().build().register();
    public static final RegistryEntry<Block> HAM_RAW_METAL_BLOCK = REGISTRATE.object("ham_raw_metal_block").block(Block::new).properties(METAL_PROPS)
            .recipe((ctx, provider) -> provider.square(DataIngredient.items(CheeseItems.HAM_RAW_METAL_INGOT.get()), ctx::getEntry, false)).tag(BlockTags.BEACON_BASE_BLOCKS)
            .lang("Ham Metal Block").defaultBlockstate().defaultLoot().item().build().register();
    public static final RegistryEntry<Block> HAM_COOKED_METAL_BLOCK = REGISTRATE.object("ham_cooked_metal_block").block(Block::new).properties(METAL_PROPS)
            .recipe((ctx, provider) -> provider.square(DataIngredient.items(CheeseItems.HAM_COOKED_METAL_INGOT.get()), ctx::getEntry, false)).tag(BlockTags.BEACON_BASE_BLOCKS)
            .lang("Cooked Ham Metal Block").defaultBlockstate().defaultLoot().item().build().register();

    /// NATURE ///
    public static final RegistryEntry<TallFoodGrassBlock> CHEESE_GRASS = registerGrass("cheese_grass", "Cheese Grass", () -> CheeseBlocks.TALL_CHEESE_GRASS);
    public static final RegistryEntry<TallFoodGrassBlock> GRILLED_CHEESE_GRASS = registerGrass("grilled_cheese_grass", "Grilled Cheese Grass", () -> CheeseBlocks.TALL_GRILLED_CHEESE_GRASS);
    public static final RegistryEntry<TallFoodGrassBlock> HAM_RAW_GRASS = registerGrass("ham_raw_grass", "Ham Grass", () -> CheeseBlocks.TALL_HAM_RAW_GRASS);
    public static final RegistryEntry<TallFoodGrassBlock> HAM_COOKED_GRASS = registerGrass("ham_cooked_grass", "Cooked Ham Grass", () -> CheeseBlocks.TALL_HAM_COOKED_GRASS);

    public static final RegistryEntry<DoubleFoodPlantBlock> TALL_CHEESE_GRASS = registerTallGrass("tall_cheese_grass", "Tall Cheese Grass", CHEESE_GRASS);
    public static final RegistryEntry<DoubleFoodPlantBlock> TALL_GRILLED_CHEESE_GRASS = registerTallGrass("tall_grilled_cheese_grass", "Tall Grilled Cheese Grass", GRILLED_CHEESE_GRASS);
    public static final RegistryEntry<DoubleFoodPlantBlock> TALL_HAM_RAW_GRASS = registerTallGrass("tall_ham_raw_grass", "Tall Ham Grass", HAM_RAW_GRASS);
    public static final RegistryEntry<DoubleFoodPlantBlock> TALL_HAM_COOKED_GRASS = registerTallGrass("tall_ham_cooked_grass", "Tall Cooked Ham Grass", HAM_COOKED_GRASS);

    public static final RegistryEntry<FoodGrassBlock> CHEESE_GRASS_BLOCK = registerGrassBlock("cheese_grass_block", "Cheese Grass Block", "holes", CHEESE_GRASS);
    public static final RegistryEntry<FoodGrassBlock> GRILLED_CHEESE_GRASS_BLOCK = registerGrassBlock("grilled_cheese_grass_block", "Grilled Cheese Grass Block", "holes", GRILLED_CHEESE_GRASS);
    public static final RegistryEntry<FoodGrassBlock> HAM_RAW_GRASS_BLOCK = registerGrassBlock("ham_raw_grass_block", "Ham Grass Block", "shine", HAM_RAW_GRASS);
    public static final RegistryEntry<FoodGrassBlock> HAM_COOKED_GRASS_BLOCK = registerGrassBlock("ham_cooked_grass_block", "Cooked Ham Grass Block", "shine", HAM_COOKED_GRASS);

    public static final RegistryEntry<LeavesBlock> CHEESE_LEAVES = registerLeaves("cheese_leaves", "Cheese Leaves", CHEESE_GRASS_BLOCK);
    public static final RegistryEntry<LeavesBlock> GRILLED_CHEESE_LEAVES = registerLeaves("grilled_cheese_leaves", "Grilled Cheese Leaves", GRILLED_CHEESE_GRASS_BLOCK);
    public static final RegistryEntry<LeavesBlock> HAM_RAW_LEAVES = registerLeaves("ham_raw_leaves", "Ham Leaves", HAM_RAW_GRASS_BLOCK);
    public static final RegistryEntry<LeavesBlock> HAM_COOKED_LEAVES = registerLeaves("ham_cooked_leaves", "Cooked Ham Leaves", HAM_COOKED_GRASS_BLOCK);

    /// WOOD -- Logs ///
    public static final RegistryEntry<RotatedPillarBlock> CHEESE_LOG = registerLog("cheese_log", "Cheese Log");
    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_CHEESE_LOG = registerLog("stripped_cheese_log", "Stripped Cheese Log");
    public static final RegistryEntry<RotatedPillarBlock> CHEESE_WOOD = registerLog("cheese_wood", "Cheese Wood", "cheese_log", CHEESE_LOG);
    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_CHEESE_WOOD = registerLog("stripped_cheese_wood", "Stripped Cheese Wood", "stripped_cheese_log", STRIPPED_CHEESE_LOG);

    public static final RegistryEntry<RotatedPillarBlock> GRILLED_CHEESE_LOG = registerLog("grilled_cheese_log", "Grilled Cheese Log");
    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_GRILLED_CHEESE_LOG = registerLog("stripped_grilled_cheese_log", "Stripped Grilled Cheese Log");
    public static final RegistryEntry<RotatedPillarBlock> GRILLED_CHEESE_WOOD = registerLog("grilled_cheese_wood", "Grilled Cheese Wood", "grilled_cheese_log", GRILLED_CHEESE_LOG);
    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_GRILLED_CHEESE_WOOD = registerLog("stripped_grilled_cheese_wood", "Stripped Grilled Cheese Wood", "stripped_grilled_cheese_log", STRIPPED_GRILLED_CHEESE_LOG);

    public static final RegistryEntry<RotatedPillarBlock> HAM_RAW_LOG = registerLog("ham_raw_log", "Ham Log");
    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_HAM_RAW_LOG = registerLog("stripped_ham_raw_log", "Stripped Ham Log");
    public static final RegistryEntry<RotatedPillarBlock> HAM_RAW_WOOD = registerLog("ham_raw_wood", "Ham Wood", "ham_raw_log", HAM_RAW_LOG);
    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_HAM_RAW_WOOD = registerLog("stripped_ham_raw_wood", "Stripped Ham Wood", "stripped_ham_raw_log", STRIPPED_HAM_RAW_LOG);

    public static final RegistryEntry<RotatedPillarBlock> HAM_COOKED_LOG = registerLog("ham_cooked_log", "Cooked Ham Log");
    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_HAM_COOKED_LOG = registerLog("stripped_ham_cooked_log", "Stripped Cooked Ham Log");
    public static final RegistryEntry<RotatedPillarBlock> HAM_COOKED_WOOD = registerLog("ham_cooked_wood", "Cooked Ham Wood", "ham_cooked_log", HAM_COOKED_LOG);
    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_HAM_COOKED_WOOD = registerLog("stripped_ham_cooked_wood", "Stripped Cooked Ham Wood", "stripped_ham_cooked_log", STRIPPED_HAM_COOKED_LOG);

    // Planks
    public static final RegistryEntry<Block> CHEESE_PLANKS = registerPlanks("cheese_planks", "Cheese Planks", CHEESE_LOG, STRIPPED_CHEESE_LOG, CHEESE_WOOD, STRIPPED_CHEESE_WOOD);
    public static final RegistryEntry<Block> GRILLED_CHEESE_PLANKS = registerPlanks("grilled_cheese_planks", "Grilled Cheese Planks", GRILLED_CHEESE_LOG, STRIPPED_GRILLED_CHEESE_LOG, GRILLED_CHEESE_WOOD, STRIPPED_GRILLED_CHEESE_WOOD);
    public static final RegistryEntry<Block> HAM_RAW_PLANKS = registerPlanks("ham_raw_planks", "Ham Planks", HAM_RAW_LOG, STRIPPED_HAM_RAW_LOG, HAM_RAW_WOOD, STRIPPED_HAM_RAW_WOOD);
    public static final RegistryEntry<Block> HAM_COOKED_PLANKS = registerPlanks("ham_cooked_planks", "Cooked Ham Planks", HAM_COOKED_LOG, STRIPPED_HAM_COOKED_LOG, HAM_COOKED_WOOD, STRIPPED_HAM_COOKED_WOOD);

    // Doors
    public static final RegistryEntry<DoorBlock> CHEESE_WOOD_DOOR = registerDoor("cheese_wood_door", "Wooden Cheese Door", CHEESE_PLANKS);
    public static final RegistryEntry<DoorBlock> GRILLED_CHEESE_WOOD_DOOR = registerDoor("grilled_cheese_wood_door", "Wooden Grilled Cheese Door", GRILLED_CHEESE_PLANKS);
    public static final RegistryEntry<DoorBlock> HAM_RAW_WOOD_DOOR = registerDoor("ham_raw_door", "Wooden Ham Door", HAM_RAW_PLANKS);
    public static final RegistryEntry<DoorBlock> HAM_COOKED_WOOD_DOOR = registerDoor("ham_cooked_door", "Wooden Cooked Ham Door", HAM_COOKED_PLANKS);

    // Trapdoors
    public static final RegistryEntry<TrapDoorBlock> CHEESE_WOOD_TRAPDOOR = registerTrapdoor("cheese_wood_trapdoor", "Cheese Wood Trapdoor", CHEESE_PLANKS);
    public static final RegistryEntry<TrapDoorBlock> GRILLED_CHEESE_WOOD_TRAPDOOR = registerTrapdoor("grilled_cheese_wood_trapdoor", "Grilled Cheese Wood Trapdoor", GRILLED_CHEESE_PLANKS);
    public static final RegistryEntry<TrapDoorBlock> HAM_RAW_WOOD_TRAPDOOR = registerTrapdoor("ham_raw_wood_trapdoor", "Ham Wood Trapdoor", HAM_RAW_PLANKS);
    public static final RegistryEntry<TrapDoorBlock> HAM_COOKED_WOOD_TRAPDOOR = registerTrapdoor("ham_cooked_wood_trapdoor", "Cooked Ham Wood Trapdoor", HAM_COOKED_PLANKS);

    // Pressure Plate
    public static final RegistryEntry<PressurePlateBlock> CHEESE_WOOD_PRESSURE_PLATE = registerPressurePlate("cheese_wood_pressure_plate", "Cheese Wood Pressure Plate", CHEESE_PLANKS);

    /// FOOD ///

    /// OTHER ///

    private static RegistryEntry<PressurePlateBlock> registerPressurePlate(String id, String lang, NonNullSupplier<Block> planks) {
        return REGISTRATE.object(id).block(prop -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, prop)).initialProperties(planks).lang(lang)
                .properties(prop -> prop.doesNotBlockMovement().hardnessAndResistance(5f).notSolid())
                .blockstate((ctx, provider) -> provider.)
                .item().build().register();
    }

    private static RegistryEntry<TrapDoorBlock> registerTrapdoor(String id, String lang, NonNullSupplier<Block> planks) {
        return REGISTRATE.object(id).block(TrapDoorBlock::new).initialProperties(planks)
                .properties(prop -> prop.hardnessAndResistance(3f).notSolid()).lang(lang)
                .blockstate((ctx, provider) -> provider.trapdoorBlock(ctx.getEntry(), CheeseMod.getLocation("block/" + id), true))
                .defaultLoot().recipe((ctx, provider) -> provider.trapDoor(DataIngredient.items(planks), ctx::getEntry, "wooden_trapdoor"))
                .item().model((ctx, provider) -> provider.withExistingParent(ctx.getName(), CheeseMod.getLocation("block/" + id + "_bottom"))).build().register();
    }

    private static RegistryEntry<DoorBlock> registerDoor(String id, String lang, NonNullSupplier<Block> planks) {
        return REGISTRATE.object(id).block(DoorBlock::new).initialProperties(planks).properties(prop -> prop.hardnessAndResistance(3f).notSolid())
                .addLayer(() -> RenderType::getCutoutMipped).tag(BlockTags.WOODEN_DOORS, BlockTags.DOORS).lang(lang)
                .recipe((ctx, provider) -> provider.door(DataIngredient.items(planks), ctx::getEntry, "wooden_door"))
                .blockstate((ctx, provider) -> provider.doorBlock(ctx.getEntry(), CheeseMod.getLocation("block/" + id + "_bottom"), CheeseMod.getLocation("block/" + id + "_top")))
                .loot((tables, block) -> tables.registerLootTable(block, LootTable.builder()
                        .addLootPool(LootPool.builder().rolls(new RandomValueRange(1)).addEntry(ItemLootEntry.builder(block)
                                .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(DoorBlock.HALF, DoubleBlockHalf.LOWER))))
                                .acceptCondition(SurvivesExplosion.builder()))))
                .item().tag(ItemTags.WOODEN_DOORS, ItemTags.DOORS).model((ctx, provider) -> provider.generated(ctx::getEntry)).build().register();
    }

    private static RegistryEntry<LeavesBlock> registerLeaves(String id, String lang, Supplier<? extends IItemProvider> sapling) {
        return REGISTRATE.object(id).block(LeavesBlock::new)
                .initialProperties(Material.LEAVES, Material.LEAVES.getColor()).properties(prop -> prop.hardnessAndResistance(0.2f).tickRandomly().sound(SoundType.PLANT).notSolid())
                .addLayer(() -> RenderType::getCutoutMipped).defaultBlockstate().tag(BlockTags.LEAVES).lang(lang)
                .loot((lootTables, block) ->
                        lootTables.registerLootTable(block, LootTable.builder()
                                .addLootPool(LootPool.builder()
                                        .rolls(new RandomValueRange(1))
                                        .addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(block)
                                                .acceptCondition(Alternative.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS)))
                                                        .alternative(MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))))))))
                                        .addEntry(ItemLootEntry.builder(sapling.get())
                                                .acceptCondition(SurvivesExplosion.builder())
                                                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.05f, 0.0625f, 0.083333336f, 0.1f)))
                                ).addLootPool(LootPool.builder()
                                        .rolls(new RandomValueRange(1))
                                        .addEntry(ItemLootEntry.builder(Items.STICK)
                                                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f))
                                                .acceptFunction(SetCount.builder(new RandomValueRange(1.0f, 2.0f))).acceptFunction(ExplosionDecay.builder()))
                                        .acceptCondition(Inverted.builder(Alternative.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS)))
                                                .alternative(MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))))))))
                ).item().tag(ItemTags.LEAVES).build().register();
    }

    @SafeVarargs
    private static RegistryEntry<Block> registerPlanks(String id, String lang, NonNullSupplier<RotatedPillarBlock> log, NonNullSupplier<RotatedPillarBlock>... others) {
        return REGISTRATE.object(id).block(Block::new).initialProperties(Material.WOOD, Material.WOOD.getColor()).tag(BlockTags.PLANKS).lang(lang)
                .properties(prop -> prop.hardnessAndResistance(2.0f, 3.0f).sound(SoundType.WOOD)).defaultBlockstate().defaultLoot()
                .recipe((ctx, provider) -> provider.planks(DataIngredient.items(log, others), ctx::getEntry)).item().tag(ItemTags.PLANKS).build().register();
    }

    private static RegistryEntry<RotatedPillarBlock> registerLog(String id, String lang) {
        return registerLog(id, lang, id, null);
    }

    private static RegistryEntry<RotatedPillarBlock> registerLog(String id, String lang, String texture, Supplier<RotatedPillarBlock> log) {
        boolean onlySideTexture = !id.equals(texture);
        return REGISTRATE.object(id).block(RotatedPillarBlock::new).lang(lang)
                .initialProperties(Material.WOOD, Material.WOOD.getColor()).properties(prop -> prop.hardnessAndResistance(2.0f).sound(SoundType.WOOD)).defaultLoot()
                .tag(BlockTags.LOGS).blockstate((ctx, provider) -> {
                    ResourceLocation side = CheeseMod.getLocation("block/" + texture + "_side");
                    ResourceLocation top = onlySideTexture ? side : CheeseMod.getLocation("block/" + texture + "_top");
                    provider.axisBlock(ctx.getEntry(), side, top);
                })
                .recipe(onlySideTexture ? (ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 3).patternLine("ll").patternLine("ll").key('l', log.get())
                        .addCriterion("has_log", RegistrateRecipeProvider.hasItem(log.get())).build(provider) : NonNullBiConsumer.noop())
                .item().tag(ItemTags.LOGS).build().register();
    }

    private static RegistryEntry<TallFoodGrassBlock> registerGrass(String id, String lang, Supplier<Supplier<DoubleFoodPlantBlock>> doublePlantBlock) {
        return REGISTRATE.object(id).block(prop -> new TallFoodGrassBlock(prop, doublePlantBlock)).lang(lang).initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor())
                .properties(prop -> prop.sound(SoundType.PLANT).doesNotBlockMovement().zeroHardnessAndResistance().notSolid()).addLayer(() -> RenderType::getCutoutMipped)
                .blockstate((ctx, provider) -> provider.simpleBlock(ctx.getEntry(), provider.models().cross(ctx.getName(), CheeseMod.getLocation("block/" + ctx.getName()))))
                .loot((tables, block) -> tables.registerLootTable(block, LootTable.builder()
                        .addLootPool(LootPool.builder().rolls(new RandomValueRange(1))
                                .addEntry(ItemLootEntry.builder(block).acceptCondition(MatchTool.builder(ItemPredicate.Builder.create().tag(Tags.Items.SHEARS)))))))
                .item().model((ctx, provider) -> provider.generated(ctx::getEntry, CheeseMod.getLocation("block/" + ctx.getName()))).build().register();
    }

    private static RegistryEntry<DoubleFoodPlantBlock> registerTallGrass(String id, String lang, Supplier<TallFoodGrassBlock> grassDrop) {
        return REGISTRATE.object(id).block(DoubleFoodPlantBlock::new).lang(lang).initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor())
                .properties(prop -> prop.sound(SoundType.PLANT).doesNotBlockMovement().zeroHardnessAndResistance().notSolid()).addLayer(() -> RenderType::getCutoutMipped)
                .blockstate((ctx, provider) -> {
                    BlockModelBuilder bottom = provider.models().cross(ctx.getName() + "_bottom", CheeseMod.getLocation("block/" + ctx.getName() + "_bottom"));
                    BlockModelBuilder top = provider.models().cross(ctx.getName() + "_top", CheeseMod.getLocation("block/" + ctx.getName() + "_top"));
                    provider.getVariantBuilder(ctx.getEntry())
                            .partialState().with(DoubleFoodPlantBlock.HALF, DoubleBlockHalf.LOWER)
                            .modelForState().modelFile(bottom).addModel()
                            .partialState().with(DoubleFoodPlantBlock.HALF, DoubleBlockHalf.UPPER)
                            .modelForState().modelFile(top).addModel();
                })
                .loot((tables, block) -> tables.registerLootTable(block, LootTable.builder()
                        .addLootPool(LootPool.builder()
                                .rolls(new RandomValueRange(1))
                                .addEntry(ItemLootEntry.builder(grassDrop.get())
                                        .acceptCondition(MatchTool.builder(ItemPredicate.Builder.create().tag(Tags.Items.SHEARS)))
                                        .acceptFunction(SetCount.builder(new RandomValueRange(2))))
                                .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(DoubleFoodPlantBlock.HALF, DoubleBlockHalf.UPPER)))
                                .acceptCondition(LocationCheck.func_241547_a_(LocationPredicate.Builder.builder()
                                        .block(BlockPredicate.Builder.createBuilder().setBlock(block)
                                                .setStatePredicate(StatePropertiesPredicate.Builder.newBuilder().withProp(DoubleFoodPlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))))
                        .addLootPool(LootPool.builder()
                                .rolls(new RandomValueRange(1))
                                .addEntry(ItemLootEntry.builder(grassDrop.get())
                                        .acceptCondition(MatchTool.builder(ItemPredicate.Builder.create().tag(Tags.Items.SHEARS)))
                                        .acceptFunction(SetCount.builder(new RandomValueRange(2))))
                                .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(DoubleFoodPlantBlock.HALF, DoubleBlockHalf.LOWER)))
                                .acceptCondition(LocationCheck.func_241547_a_(LocationPredicate.Builder.builder()
                                        .block(BlockPredicate.Builder.createBuilder().setBlock(block)
                                                .setStatePredicate(StatePropertiesPredicate.Builder.newBuilder().withProp(DoubleFoodPlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0))))))
                .item().model((ctx, provider) -> provider.generated(ctx::getEntry, CheeseMod.getLocation("block/" + ctx.getName() + "_bottom"))).build().register();
    }

    private static RegistryEntry<FoodGrassBlock> registerGrassBlock(String id, String lang, String alternateSuffix, Supplier<TallFoodGrassBlock> tallstate) {
        return REGISTRATE.object(id).block(prop -> new FoodGrassBlock(prop, tallstate)).tag(CheeseTags.Blocks.FOOD_GRASS_BLOCKS).lang(lang)
                .initialProperties(Material.ORGANIC, Material.ORGANIC.getColor())
                .properties(prop -> prop.sound(SoundType.PLANT).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT))
                .loot((tables, block) -> tables.registerLootTable(block, RegistrateBlockLootTables.droppingWithSilkTouch(block, Blocks.DIRT)))
                .blockstate((ctx, provider) -> {
                    ResourceLocation dirt = new ResourceLocation("block/dirt");
                    String side = "block/" + ctx.getName() + "_side";
                    String top = "block/" + ctx.getName() + "_top";
                    BlockModelBuilder normal = provider.models().cubeBottomTop(ctx.getName(), CheeseMod.getLocation(side), dirt, CheeseMod.getLocation(top));
                    BlockModelBuilder holes = provider.models().cubeBottomTop(ctx.getName() + "_" + alternateSuffix, CheeseMod.getLocation(side + "_" + alternateSuffix), dirt, CheeseMod.getLocation(top + "_" + alternateSuffix));

                    provider.simpleBlock(ctx.getEntry(), ConfiguredModel.builder()
                            .modelFile(normal).weight(10).nextModel()
                            .modelFile(normal).weight(10).rotationY(90).nextModel()
                            .modelFile(normal).weight(10).rotationY(180).nextModel()
                            .modelFile(normal).weight(10).rotationY(270).nextModel()

                            .modelFile(holes).weight(5).nextModel()
                            .modelFile(holes).weight(5).rotationY(90).nextModel()
                            .modelFile(holes).weight(5).rotationY(180).nextModel()
                            .modelFile(holes).weight(5).rotationY(270)
                            .build());
                }).item().tag(CheeseTags.Items.FOOD_GRASS_BLOCKS).build().register();
    }

    public static void load() {
        LOGGER.info("Blocks registered");
    }
}
