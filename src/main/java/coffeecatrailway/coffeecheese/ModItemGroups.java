package coffeecatrailway.coffeecheese;

import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 17/09/2019
 */
public class ModItemGroups {

    private static final ResourceLocation TAB = CheeseMod.getLocation("textures/gui/container/creative_inventory/tab.png");

    public static final ItemGroup GROUP_FOODS = new ItemGroup(CheeseMod.MOD_ID + ".foods") {

        private final long cooldown = 1500;
        private long last, now;
        private Item item = Items.STICK;

        @Override
        public ItemStack createIcon() {
            now += System.currentTimeMillis() - last;
            last = System.currentTimeMillis();

            if (now > cooldown) {
                item = ModItems.CHEESE_SLICE.get(); //ModItems.FOODS.get(new Random().nextInt(ModItems.FOODS.size() - 1));
                now = 0;
            }
            return new ItemStack(item);
        }

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack getIcon() {
            return this.createIcon();
        }

        @Override
        public ResourceLocation getBackgroundImage() {
            return TAB;
        }
    };

    public static final ItemGroup GROUP_ARMOR_TOOLS = new ItemGroup(CheeseMod.MOD_ID + ".armor_tools") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.CHEESE_METAL_SWORD.get());
        }

        @Override
        public ResourceLocation getBackgroundImage() {
            return TAB;
        }
    };

    public static final ItemGroup GROUP_ALL = new ItemGroup(CheeseMod.MOD_ID) {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.CHEESE_BLOCK.get());
        }

        @Override
        public ResourceLocation getBackgroundImage() {
            return TAB;
        }
    };
}
