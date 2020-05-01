package coffeecatrailway.coffeecheese;

import coffeecatrailway.coffeecheese.client.gui.screen.FoodDrawScreen;
import coffeecatrailway.coffeecheese.client.gui.screen.GrillScreen;
import coffeecatrailway.coffeecheese.client.gui.screen.MelterScreen;
import coffeecatrailway.coffeecheese.client.gui.screen.PizzaOvenScreen;
import coffeecatrailway.coffeecheese.client.render.entity.BoatRendererCM;
import coffeecatrailway.coffeecheese.client.render.entity.FoodieRenderer;
import coffeecatrailway.coffeecheese.client.render.tileentity.FoodWorldPortalTileEntityRenderer;
import coffeecatrailway.coffeecheese.client.render.tileentity.GrillTileEntityRenderer;
import coffeecatrailway.coffeecheese.client.render.tileentity.MelterTileEntityRenderer;
import coffeecatrailway.coffeecheese.client.render.tileentity.PizzaOvenTileEntityRenderer;
import coffeecatrailway.coffeecheese.registry.*;
import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * @author CoffeeCatRailway
 * Created: 14/04/2020
 */
@OnlyIn(Dist.CLIENT)
public class ClientEvents {

    public static void itemColors() {
        ItemColors c = Minecraft.getInstance().getItemColors();
        for (RegistryEntry<? extends LazySpawnEggItem<?>> egg : ModEntities.SPAWN_EGGS) {
            c.register((a, layer) -> egg.get().getColor(layer), egg.get());
        }
    }

    public static void renderLayers() {
        RenderType cutoutMipped = RenderType.getCutoutMipped();

        RenderTypeLookup.setRenderLayer(ModBlocks.CHEESE_LEAVES.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(ModBlocks.GRILLED_CHEESE_LEAVES.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_RAW_LEAVES.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_COOKED_LEAVES.get(), cutoutMipped);

        RenderTypeLookup.setRenderLayer(ModBlocks.CHEESE_DRAW.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(ModBlocks.GRILLED_CHEESE_DRAW.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_RAW_DRAW.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_COOKED_DRAW.get(), cutoutMipped);

        RenderType cutout = RenderType.getCutout();

        RenderTypeLookup.setRenderLayer(ModBlocks.CHEESE_GRASS.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.TALL_CHEESE_GRASS.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.GRILLED_CHEESE_GRASS.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.TALL_GRILLED_CHEESE_GRASS.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_RAW_GRASS.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.TALL_HAM_RAW_GRASS.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_COOKED_GRASS.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.TALL_HAM_COOKED_GRASS.get(), cutout);

        RenderTypeLookup.setRenderLayer(ModBlocks.CHEESE_SAPLING.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.GRILLED_CHEESE_SAPLING.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_RAW_SAPLING.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_COOKED_SAPLING.get(), cutout);

        RenderTypeLookup.setRenderLayer(ModBlocks.CHEESE_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.GRILLED_CHEESE_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_RAW_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_COOKED_DOOR.get(), cutout);

        RenderTypeLookup.setRenderLayer(ModBlocks.CHEESE_WOOD_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.GRILLED_CHEESE_WOOD_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_RAW_WOOD_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_COOKED_WOOD_DOOR.get(), cutout);

        RenderTypeLookup.setRenderLayer(ModBlocks.CHEESE_TRAP_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.GRILLED_CHEESE_TRAP_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_RAW_TRAP_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_COOKED_TRAP_DOOR.get(), cutout);

        RenderTypeLookup.setRenderLayer(ModBlocks.CHEESE_WOOD_TRAP_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.GRILLED_CHEESE_WOOD_TRAP_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_RAW_WOOD_TRAP_DOOR.get(), cutout);
        RenderTypeLookup.setRenderLayer(ModBlocks.HAM_COOKED_WOOD_TRAP_DOOR.get(), cutout);

        RenderTypeLookup.setRenderLayer(ModBlocks.PINEAPPLE.get(), cutout);
        
        RenderType translucent = RenderType.getTranslucent();

        RenderTypeLookup.setRenderLayer(ModFluids.VINEGAR.get(), translucent);
        RenderTypeLookup.setRenderLayer(ModFluids.VINEGAR.get().getFlowingFluid(), translucent);
        RenderTypeLookup.setRenderLayer(ModFluids.VINEGAR.get().getStillFluid(), translucent);

        RenderTypeLookup.setRenderLayer(ModFluids.OIL.get(), translucent);
        RenderTypeLookup.setRenderLayer(ModFluids.OIL.get().getFlowingFluid(), translucent);
        RenderTypeLookup.setRenderLayer(ModFluids.OIL.get().getStillFluid(), translucent);
    }

    public static void containerScreens() {
        ScreenManager.registerFactory(ModContainers.FOOD_DRAW.get(), FoodDrawScreen::new);
        ScreenManager.registerFactory(ModContainers.GRILL.get(), GrillScreen::new);
        ScreenManager.registerFactory(ModContainers.MELTER.get(), MelterScreen::new);
        ScreenManager.registerFactory(ModContainers.PIZZA_OVEN.get(), PizzaOvenScreen::new);
    }

    public static void particleFactories() {
        Minecraft.getInstance().particles.registerFactory(ModParticles.ITEM_CHEESE_BALL.get(), (type, world, v, v1, v2, v3, v4, v5) -> new BreakingParticle(world, v, v1, v2, new ItemStack(ModItems.CHEESE_BALL.get())));
    }

    public static void entityRenderers() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CHEESE_BALL.get(), manager -> new SpriteRenderer<>(manager, itemRenderer));

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CHEESE_FOODIE.get(), manager -> new FoodieRenderer<>(manager, CheeseMod.getLocation("textures/entity/foodie/cheese.png")));
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GRILLED_CHEESE_FOODIE.get(), manager -> new FoodieRenderer<>(manager, CheeseMod.getLocation("textures/entity/foodie/grilled_cheese.png")));
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HAM_RAW_FOODIE.get(), manager -> new FoodieRenderer<>(manager, CheeseMod.getLocation("textures/entity/foodie/ham_raw.png")));
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HAM_COOKED_FOODIE.get(), manager -> new FoodieRenderer<>(manager, CheeseMod.getLocation("textures/entity/foodie/ham_cooked.png")));

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BOAT.get(), BoatRendererCM::new);
    }

    public static void tileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.GRILL.get(), GrillTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.MELTER.get(), MelterTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.PIZZA_OVEN.get(), PizzaOvenTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.FOOD_WORLD_PORTAL.get(), FoodWorldPortalTileEntityRenderer::new);
    }
}
