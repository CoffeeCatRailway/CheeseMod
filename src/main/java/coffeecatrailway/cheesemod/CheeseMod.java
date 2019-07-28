package coffeecatrailway.cheesemod;

import coffeecatrailway.cheesemod.core.ModItems;
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
import java.util.Optional;

@Mod(CheeseMod.MOD_ID)
public class CheeseMod {

    public static final String MOD_ID = "cheesemod";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final ItemGroup GROUP_ITEMS = new ItemGroup(MOD_ID) {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.TOASTIE_CHEESE);
        }

        @Override
        public ResourceLocation getBackgroundImage() {
            return new ResourceLocation(CheeseMod.MOD_ID, "textures/gui/container/creative_inventory/tab.png");
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
}
