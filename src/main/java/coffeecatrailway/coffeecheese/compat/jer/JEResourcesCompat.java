package coffeecatrailway.coffeecheese.compat.jer;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.entity.CheeseFoodie;
import coffeecatrailway.coffeecheese.common.entity.GrilledCheeseFoodie;
import coffeecatrailway.coffeecheese.common.entity.HamCookedFoodie;
import coffeecatrailway.coffeecheese.common.entity.HamRawFoodie;
import jeresources.api.IJERAPI;
import jeresources.api.JERPlugin;
import jeresources.api.conditionals.LightLevel;
import jeresources.compatibility.JERAPI;

/**
 * @author CoffeeCatRailway
 * Created: 4/01/2020
 */
public class JEResourcesCompat {

    @JERPlugin
    public static IJERAPI jerAPI;

    public static final String CAT_PINE_HUT = "chests/pine_hut";

    public static void register() {
        if (jerAPI == null) jerAPI = JERAPI.getInstance();

        CheeseFoodie cheeseFoodie = new CheeseFoodie(null);
        jerAPI.getMobRegistry().register(cheeseFoodie, LightLevel.any, cheeseFoodie.getLootTableResourceLocation());

        GrilledCheeseFoodie grilledCheeseFoodie = new GrilledCheeseFoodie(null);
        jerAPI.getMobRegistry().register(grilledCheeseFoodie, LightLevel.any, grilledCheeseFoodie.getLootTableResourceLocation());

        HamRawFoodie hamRawFoodie = new HamRawFoodie(null);
        jerAPI.getMobRegistry().register(hamRawFoodie, LightLevel.any, hamRawFoodie.getLootTableResourceLocation());

        HamCookedFoodie hamCookedFoodie = new HamCookedFoodie(null);
        jerAPI.getMobRegistry().register(hamCookedFoodie, LightLevel.any, hamCookedFoodie.getLootTableResourceLocation());

        jerAPI.getDungeonRegistry().registerCategory(CAT_PINE_HUT, "Pine Hut");
        jerAPI.getDungeonRegistry().registerChest(CAT_PINE_HUT, CheeseMod.PINE_HUT_LOOT_TABLE);
    }
}
