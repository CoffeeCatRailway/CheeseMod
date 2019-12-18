package coffeecatrailway.coffeecheese.client.render.tileentity.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 6/09/2019
 */
@OnlyIn(Dist.CLIENT)
public class MelterFluidModel extends Model {

    private RendererModel fluid;

    public MelterFluidModel() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.fluid = new RendererModel(this, 0, 0);
        this.fluid.addBox(1.0f, 0.0f, 1.0f, 14, 1, 14, 0.0f);
    }

    public void render() {
        this.fluid.render(0.0625f);
    }
}
