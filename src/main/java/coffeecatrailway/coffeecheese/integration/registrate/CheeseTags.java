package coffeecatrailway.coffeecheese.integration.registrate;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.registry.CheeseItems;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2020
 */
public class CheeseTags {

    public static class Blocks implements NonNullConsumer<RegistrateTagsProvider<Block>> {

        public static final ITag.INamedTag<Block> FOOD_BLOCKS = tagForge("food_blocks");
        public static final ITag.INamedTag<Block> CHEESE_BLOCKS = tagForge("food_blocks/cheese");
        public static final ITag.INamedTag<Block> GRILLED_CHEESE_BLOCKS = tagForge("food_blocks/grilled_cheese");
        public static final ITag.INamedTag<Block> HAM_RAW_BLOCKS = tagForge("food_blocks/ham_raw");
        public static final ITag.INamedTag<Block> HAM_COOKED_BLOCKS = tagForge("food_blocks/ham_cooked");

        @Override
        public void accept(RegistrateTagsProvider<Block> provider) {
//            provider.getOrCreateBuilder(FOOD_BLOCKS).addTag(CHEESE_BLOCKS).addTag(GRILLED_CHEESE_BLOCKS).addTag(HAM_RAW_BLOCKS).addTag(HAM_COOKED_BLOCKS);
        }

        private static ITag.INamedTag<Block> tagMod(String name) {
            return BlockTags.makeWrapperTag(CheeseMod.getLocation(name).toString());
        }

        private static ITag.INamedTag<Block> tag(String id, String name) {
            return BlockTags.makeWrapperTag(new ResourceLocation(id, name).toString());
        }

        private static ITag.INamedTag<Block> tagForge(String name) {
            return tag("forge", name);
        }
    }

    public static class Items implements NonNullConsumer<RegistrateTagsProvider<Item>> {

        public static final ITag.INamedTag<Item> BACON = tagForge("bacon");

        public static final ITag.INamedTag<Item> BREAD = tagForge("bread");

        public static final ITag.INamedTag<Item> DOUGH = tagForge("dough");

        public static final ITag.INamedTag<Item> CHEESE = tagForge("cheese");

        public static final ITag.INamedTag<Item> CRACKER = tagForge("cracker");

        public static final ITag.INamedTag<Item> FLOUR = tagForge("flour");

        public static final ITag.INamedTag<Item> FOOD_SLICE = tagForge("food_slice");
        public static final ITag.INamedTag<Item> FOOD_SLICE_CHEESE = tagForge("food_slice/cheese");
        public static final ITag.INamedTag<Item> FOOD_SLICE_HAM = tagForge("food_slice/ham");
        public static final ITag.INamedTag<Item> FOOD_SLICE_HAM_RAW = tagForge("food_slice/ham_raw");
        public static final ITag.INamedTag<Item> FOOD_SLICE_HAM_COOKED = tagForge("food_slice/ham_cooked");

        public static final ITag.INamedTag<Item> GEARS = tagForge("gears");
        public static final ITag.INamedTag<Item> GEARS_WOODEN = tagForge("gears/wooden");

        public static final ITag.INamedTag<Item> HAM = tagForge("ham");

        public static final ITag.INamedTag<Item> KNIVES = tagForge("knives");
        public static final ITag.INamedTag<Item> KNIVES_IRON = tagForge("knives/iron");
        public static final ITag.INamedTag<Item> KNIVES_CHEESE = tagForge("knives/cheese");
        public static final ITag.INamedTag<Item> KNIVES_HAM = tagForge("knives/ham");

        public static final ITag.INamedTag<Item> PIZZA = tagForge("pizza");

        public static final ITag.INamedTag<Item> SALT = tagForge("salt");

        public static final ITag.INamedTag<Item> SUGAR = tagForge("sugar");

        public static final ITag.INamedTag<Item> TOAST = tagForge("toast");

        public static final ITag.INamedTag<Item> WHEAT = tagForge("wheat");

        /// Blocks ///
        public static final ITag.INamedTag<Item> FOOD_BLOCKS = tagForge("food_blocks");
        public static final ITag.INamedTag<Item> CHEESE_BLOCKS = tagForge("food_blocks/cheese");
        public static final ITag.INamedTag<Item> GRILLED_CHEESE_BLOCKS = tagForge("food_blocks/grilled_cheese");
        public static final ITag.INamedTag<Item> HAM_RAW_BLOCKS = tagForge("food_blocks/ham_raw");
        public static final ITag.INamedTag<Item> HAM_COOKED_BLOCKS = tagForge("food_blocks/ham_cooked");

        @Override
        public void accept(RegistrateTagsProvider<Item> provider) {
            provider.getOrCreateBuilder(BREAD).add(net.minecraft.item.Items.BREAD).addTag(TOAST);

            provider.getOrCreateBuilder(CHEESE).addTags(FOOD_SLICE_CHEESE); // , CHEESE_BLOCKS, GRILLED_CHEESE_BLOCKS

            provider.getOrCreateBuilder(FOOD_SLICE_HAM).addTags(FOOD_SLICE_HAM_RAW, FOOD_SLICE_HAM_COOKED);
            provider.getOrCreateBuilder(FOOD_SLICE).addTags(FOOD_SLICE_CHEESE, FOOD_SLICE_HAM);

            provider.getOrCreateBuilder(GEARS).addTag(GEARS_WOODEN);

            provider.getOrCreateBuilder(HAM).addTags(FOOD_SLICE_HAM); // , HAM_RAW_BLOCKS, HAM_COOKED_BLOCKS

            provider.getOrCreateBuilder(KNIVES).addTags(KNIVES_IRON, KNIVES_CHEESE, KNIVES_HAM);

            provider.getOrCreateBuilder(SUGAR).add(net.minecraft.item.Items.SUGAR);

            provider.getOrCreateBuilder(WHEAT).add(net.minecraft.item.Items.WHEAT);

            /// Blocks ///
//            provider.getOrCreateBuilder(FOOD_BLOCKS).addTags(CHEESE_BLOCKS, GRILLED_CHEESE_BLOCKS, HAM_RAW_BLOCKS, HAM_COOKED_BLOCKS);
        }

        private static ITag.INamedTag<Item> tagMod(String name) {
            return ItemTags.makeWrapperTag(CheeseMod.getLocation(name).toString());
        }

        private static ITag.INamedTag<Item> tag(String id, String name) {
            return ItemTags.makeWrapperTag(new ResourceLocation(id, name).toString());
        }

        private static ITag.INamedTag<Item> tagForge(String name) {
            return tag("forge", name);
        }
    }
}
