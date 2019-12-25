package coffeecatrailway.coffeecheese;

import coffeecatrailway.coffeecheese.registry.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author CoffeeCatRailway
 * Created: 19/07/2019
 */
public class SideProxy {

    public SideProxy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModFeatures::registerAll);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModStats::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModRecipeTypes::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModEntityTypes::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModParticles::registerAll);
    }

    public static class Client extends SideProxy {

        Client() {
        }
    }

    public static class Server extends SideProxy {

        Server() {
        }
    }
}
