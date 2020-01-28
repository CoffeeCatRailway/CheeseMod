package coffeecatrailway.coffeecheese.compat.jer;

import coffeecatrailway.coffeecheese.common.entity.CheeseFoodie;
import coffeecatrailway.coffeecheese.common.entity.GrilledCheeseFoodie;
import coffeecatrailway.coffeecheese.common.entity.HamCookedFoodie;
import coffeecatrailway.coffeecheese.common.entity.HamRawFoodie;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModItems;
import jeresources.api.IJERAPI;
import jeresources.api.JERPlugin;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.PlantDrop;
import jeresources.compatibility.JERAPI;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author CoffeeCatRailway
 * Created: 4/01/2020
 */
public class JEResourcesCompat {

    @JERPlugin
    public static IJERAPI jerAPI;

    public static void register() {
        if (jerAPI == null) jerAPI = JERAPI.getInstance();

        /// Mobs ///
        CheeseFoodie cheeseFoodie = new CheeseFoodie(null);
        jerAPI.getMobRegistry().register(cheeseFoodie, LightLevel.any, cheeseFoodie.getLootTableResourceLocation());

        GrilledCheeseFoodie grilledCheeseFoodie = new GrilledCheeseFoodie(null);
        jerAPI.getMobRegistry().register(grilledCheeseFoodie, LightLevel.any, grilledCheeseFoodie.getLootTableResourceLocation());

        HamRawFoodie hamRawFoodie = new HamRawFoodie(null);
        jerAPI.getMobRegistry().register(hamRawFoodie, LightLevel.any, hamRawFoodie.getLootTableResourceLocation());

        HamCookedFoodie hamCookedFoodie = new HamCookedFoodie(null);
        jerAPI.getMobRegistry().register(hamCookedFoodie, LightLevel.any, hamCookedFoodie.getLootTableResourceLocation());

        /// Plants ///
        jerAPI.getPlantRegistry().registerWithSoil(ModBlocks.PINEAPPLE.get(), Blocks.FARMLAND.getDefaultState(), new PlantDrop(new ItemStack(ModItems.PINEAPPLE.get()), 0.5f));
    }
}
