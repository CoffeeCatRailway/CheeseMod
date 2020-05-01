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

        private static Tag<Block> tag(String name) {
            return new BlockTags.Wrapper(CheeseMod.getLocation(name));
        }
    }

    public static class Fluids {

        public static Tag<Fluid> VINEGAR = tag("vinegar");
        public static Tag<Fluid> GRILL_OIL = tag("oil");
        public static Tag<Fluid> MELTED_CHEESE = tag("cheese");
        public static Tag<Fluid> MELTED_GRILLED_CHEESE = tag("grilled_cheese");
        public static Tag<Fluid> MELTED_HAM_RAW = tag("ham_raw");
        public static Tag<Fluid> MELTED_HAM_COOKED = tag("ham_cooked");

        private static Tag<Fluid> tag(String name) {
            return new FluidTags.Wrapper(CheeseMod.getLocation(name));
        }
    }

    public static class Items {

        public static Tag<Item> CHEESE = tag("cheese");
        public static Tag<Item> HAM = tag("ham");
        public static Tag<Item> BREAD_SLICE = tag("bread_slice");

        private static Tag<Item> tag(String name) {
            return new ItemTags.Wrapper(CheeseMod.getLocation(name));
        }
    }
}
