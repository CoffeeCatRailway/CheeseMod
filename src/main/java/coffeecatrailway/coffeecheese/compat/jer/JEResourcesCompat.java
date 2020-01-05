package coffeecatrailway.coffeecheese.compat.jer;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.entity.CheeseFoodie;
import jeresources.api.IJERAPI;
import jeresources.api.JERPlugin;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
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
        jerAPI.getMobRegistry().register(cheeseFoodie, LightLevel.any, new LootDrop(cheeseFoodie.getDroppedItem()));

        jerAPI.getDungeonRegistry().registerCategory(CAT_PINE_HUT, "Pine Hut");
        jerAPI.getDungeonRegistry().registerChest(CAT_PINE_HUT, CheeseMod.PINE_HUT_LOOT_TABLE);
    }
}
