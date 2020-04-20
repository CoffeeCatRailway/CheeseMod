package coffeecatrailway.coffeecheese.client.render.entity;

import coffeecatrailway.coffeecheese.client.render.entity.model.FoodieModel;
import coffeecatrailway.coffeecheese.common.entity.FoodieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 13/09/2019
 */
@OnlyIn(Dist.CLIENT)
public class FoodieRenderer<E extends FoodieEntity> extends MobRenderer<E, FoodieModel<E>> {

    private final ResourceLocation textures;

    public FoodieRenderer(EntityRendererManager manager, ResourceLocation textures) {
        super(manager, new FoodieModel<E>(), 0.5f);
        this.textures = textures;
    }

    @Override
    public ResourceLocation getEntityTexture(E entity) {
        return this.textures;
    }
}
