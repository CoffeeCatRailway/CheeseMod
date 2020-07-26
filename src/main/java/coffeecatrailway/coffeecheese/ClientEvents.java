package coffeecatrailway.coffeecheese;

import coffeecatrailway.coffeecheese.client.entity.render.FoodBoatRenderer;
import coffeecatrailway.coffeecheese.registry.CheeseEntities;
import coffeecatrailway.coffeecheese.registry.CheeseItems;
import coffeecatrailway.coffeecheese.registry.CheeseParticles;
import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * @author CoffeeCatRailway
 * Created: 25/07/2020
 */
@OnlyIn(Dist.CLIENT)
public class ClientEvents {

    public static void itemColors() {
        ItemColors c = Minecraft.getInstance().getItemColors();
        for (RegistryEntry<? extends LazySpawnEggItem<?>> egg : CheeseEntities.SPAWN_EGGS) {
            c.register((a, layer) -> egg.get().getColor(layer), egg.get());
        }
    }

    public static void particleFactories() {
        Minecraft.getInstance().particles.registerFactory(CheeseParticles.ITEM_CHEESE_BALL.get(), (type, world, v, v1, v2, v3, v4, v5) -> new BreakingParticle(world, v, v1, v2, new ItemStack(CheeseItems.CHEESE_BALL.get())));
    }

    public static void entityRenderers() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(CheeseEntities.CHEESE_BALL.get(), manager -> new SpriteRenderer<>(manager, itemRenderer));

        RenderingRegistry.registerEntityRenderingHandler(CheeseEntities.BOAT.get(), FoodBoatRenderer::new);
    }
}
