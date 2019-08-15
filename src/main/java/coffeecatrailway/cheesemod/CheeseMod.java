package coffeecatrailway.cheesemod;

import coffeecatrailway.cheesemod.core.registries.ModBlocks;
import coffeecatrailway.cheesemod.core.registries.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Mod(CheeseMod.MOD_ID)
public class CheeseMod {

    public static final String MOD_ID = "cheesemod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final boolean IS_CHRISTMAS = isChristmas();

    private static final ResourceLocation TAB = getLocation("textures/gui/container/creative_inventory/tab.png");
    public static final ItemGroup GROUP_ALL = new ItemGroup(MOD_ID + ".all") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.CHEESE_BLOCK);
        }

        @Override
        public ResourceLocation getBackgroundImage() {
            return TAB;
        }
    };
    public static final ItemGroup GROUP_FOODS = new ItemGroup(MOD_ID + ".foods") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.TOASTIE_CHEESE);
        }

        @Override
        public ResourceLocation getBackgroundImage() {
            return TAB;
        }
    };

    public CheeseMod() {
        DistExecutor.runForDist(
                () -> SideProxy.Client::new,
                () -> SideProxy.Server::new
        );
        LOGGER.info("Proxy setup");
    }

    @Nonnull
    public static String getVersion() {
        Optional<? extends ModContainer> o = ModList.get().getModContainerById(MOD_ID);
        if (o.isPresent()) {
            return o.get().getModInfo().getVersion().toString();
        }
        return "NONE";
    }


    public static boolean isDevBuild() {
        return !getVersion().equals("NONE");
    }

    private static boolean isChristmas() {
        boolean ret = false;
        for (int date = 20; date <= 31; date++)
            if (isDate(Calendar.DECEMBER, date) && !ret)
                ret = true;
        return ret;
    }

    public static boolean isDate(int month, int day) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        int curentMonth = cal.get(Calendar.MONTH);
        int curentDay = cal.get(Calendar.DAY_OF_MONTH);
        boolean inRange = curentDay == day;

        return (curentMonth == month && inRange);
    }

    public static ResourceLocation getLocation(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
