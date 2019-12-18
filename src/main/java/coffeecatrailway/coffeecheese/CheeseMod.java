package coffeecatrailway.coffeecheese;

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

    public static final String MOD_ID = "coffeecheese";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final ResourceLocation PINE_HUT_LOOT_TABLE = CheeseMod.getLocation("chests/pine_hut");

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

    public static float map(float from, float fromMin, float fromMax, float toMin, float toMax) {
        float fromAbs = from - fromMin;
        float fromMaxAbs = fromMax - fromMin;

        float normal = fromAbs / fromMaxAbs;

        float toMaxAbs = toMax - toMin;
        float toAbs = toMaxAbs * normal;

        float to = toAbs + toMin;

        return to;
    }
}
