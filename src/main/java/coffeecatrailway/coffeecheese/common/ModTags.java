package coffeecatrailway.coffeecheese.common;

import coffeecatrailway.coffeecheese.CheeseMod;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;

/**
 * @author CoffeeCatRailway
 * Created: 21/12/2019
 */
public class ModTags {

    public static class Blocks {

        public static Tag<Block> FOOD_BLOCKS = tag("food_blocks");
        public static Tag<Block> FOOD_BLOCKS_CHEESE = tag("food_blocks/cheese");
        public static Tag<Block> FOOD_BLOCKS_GRILLED_CHEESE = tag("food_blocks/grilled_cheese");
        public static Tag<Block> FOOD_BLOCKS_HAM_RAW = tag("food_blocks/ham_raw");
        public static Tag<Block> FOOD_BLOCKS_HAM_COOKED = tag("food_blocks/ham_cooked");

        public static Tag<Block> LOGS = tag("logs");
        public static Tag<Block> LOGS_CHEESE = tag("logs/cheese");
        public static Tag<Block> LOGS_GRILLED_CHEESE = tag("logs/grilled_cheese");
        public static Tag<Block> LOGS_HAM_RAW = tag("logs/ham_raw");
        public static Tag<Block> LOGS_HAM_COOKED = tag("logs/ham_cooked");

        public static Tag<Block> SLABS = tag("slabs");
        public static Tag<Block> SLABS_CHEESE = tag("slabs/cheese");
        public static Tag<Block> SLABS_GRILLED_CHEESE = tag("slabs/grilled_cheese");
        public static Tag<Block> SLABS_HAM_RAW = tag("slabs/ham_raw");
        public static Tag<Block> SLABS_HAM_COOKED = tag("slabs/ham_cooked");

        public static Tag<Block> SLABS_WOODEN = tag("slabs/wooden");
        public static Tag<Block> SLABS_WOODEN_CHEESE = tag("slabs/wooden/cheese");
        public static Tag<Block> SLABS_WOODEN_GRILLED_CHEESE = tag("slabs/wooden/grilled_cheese");
        public static Tag<Block> SLABS_WOODEN_HAM_RAW = tag("slabs/wooden/ham_raw");
        public static Tag<Block> SLABS_WOODEN_HAM_COOKED = tag("slabs/wooden/ham_cooked");

        public static Tag<Block> STAIRS = tag("stairs");
        public static Tag<Block> STAIRS_CHEESE = tag("stairs/cheese");
        public static Tag<Block> STAIRS_GRILLED_CHEESE = tag("stairs/grilled_cheese");
        public static Tag<Block> STAIRS_HAM_RAW = tag("stairs/ham_raw");
        public static Tag<Block> STAIRS_HAM_COOKED = tag("stairs/ham_cooked");

        public static Tag<Block> STAIRS_WOODEN = tag("stairs/wooden");
        public static Tag<Block> STAIRS_WOODEN_CHEESE = tag("stairs/wooden/cheese");
        public static Tag<Block> STAIRS_WOODEN_GRILLED_CHEESE = tag("stairs/wooden/grilled_cheese");
        public static Tag<Block> STAIRS_WOODEN_HAM_RAW = tag("stairs/wooden/ham_raw");
        public static Tag<Block> STAIRS_WOODEN_HAM_COOKED = tag("stairs/wooden/ham_cooked");

        private static Tag<Block> tag(String name) {
            return new BlockTags.Wrapper(CheeseMod.getLocation(name));
        }
    }

    public static class Fluids {

        public static Tag<Fluid> GRILL_OIL = tag("oil");
        public static Tag<Fluid> MELTED_CHEESE = tag("cheese");

        private static Tag<Fluid> tag(String name) {
            return new FluidTags.Wrapper(CheeseMod.getLocation(name));
        }
    }

    public static class Items {

        public static Tag<Item> FOOD_BLOCKS = tag("food_blocks");
        public static Tag<Item> FOOD_BLOCKS_CHEESE = tag("food_blocks/cheese");
        public static Tag<Item> FOOD_BLOCKS_GRILLED_CHEESE = tag("food_blocks/grilled_cheese");
        public static Tag<Item> FOOD_BLOCKS_HAM_RAW = tag("food_blocks/ham_raw");
        public static Tag<Item> FOOD_BLOCKS_HAM_COOKED = tag("food_blocks/ham_cooked");

        public static Tag<Item> LOGS = tag("logs");
        public static Tag<Item> LOGS_CHEESE = tag("logs/cheese");
        public static Tag<Item> LOGS_GRILLED_CHEESE = tag("logs/grilled_cheese");
        public static Tag<Item> LOGS_HAM_RAW = tag("logs/ham_raw");
        public static Tag<Item> LOGS_HAM_COOKED = tag("logs/ham_cooked");

        public static Tag<Item> SLABS = tag("slabs");
        public static Tag<Item> SLABS_CHEESE = tag("slabs/cheese");
        public static Tag<Item> SLABS_GRILLED_CHEESE = tag("slabs/grilled_cheese");
        public static Tag<Item> SLABS_HAM_RAW = tag("slabs/ham_raw");
        public static Tag<Item> SLABS_HAM_COOKED = tag("slabs/ham_cooked");

        public static Tag<Item> SLABS_WOODEN = tag("slabs/wooden");
        public static Tag<Item> SLABS_WOODEN_CHEESE = tag("slabs/wooden/cheese");
        public static Tag<Item> SLABS_WOODEN_GRILLED_CHEESE = tag("slabs/wooden/grilled_cheese");
        public static Tag<Item> SLABS_WOODEN_HAM_RAW = tag("slabs/wooden/ham_raw");
        public static Tag<Item> SLABS_WOODEN_HAM_COOKED = tag("slabs/wooden/ham_cooked");

        public static Tag<Item> STAIRS = tag("stairs");
        public static Tag<Item> STAIRS_CHEESE = tag("stairs/cheese");
        public static Tag<Item> STAIRS_GRILLED_CHEESE = tag("stairs/grilled_cheese");
        public static Tag<Item> STAIRS_HAM_RAW = tag("stairs/ham_raw");
        public static Tag<Item> STAIRS_HAM_COOKED = tag("stairs/ham_cooked");

        public static Tag<Item> STAIRS_WOODEN = tag("stairs/wooden");
        public static Tag<Item> STAIRS_WOODEN_CHEESE = tag("stairs/wooden/cheese");
        public static Tag<Item> STAIRS_WOODEN_GRILLED_CHEESE = tag("stairs/wooden/grilled_cheese");
        public static Tag<Item> STAIRS_WOODEN_HAM_RAW = tag("stairs/wooden/ham_raw");
        public static Tag<Item> STAIRS_WOODEN_HAM_COOKED = tag("stairs/wooden/ham_cooked");

        public static Tag<Item> CRACKER = tag("cracker");
        public static Tag<Item> PIZZA = tag("pizza");
        public static Tag<Item> TOASTIE = tag("toastie");

        private static Tag<Item> tag(String name) {
            return new ItemTags.Wrapper(CheeseMod.getLocation(name));
        }
    }
}
