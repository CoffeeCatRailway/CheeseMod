package coffeecatrailway.coffeecheese.client.render.tileentity;

import coffeecatrailway.coffeecheese.client.gui.screen.MelterScreen;
import coffeecatrailway.coffeecheese.client.render.tileentity.model.MelterFluidModel;
import coffeecatrailway.coffeecheese.common.tileentity.MelterTileEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 6/09/2019
 */
@OnlyIn(Dist.CLIENT)
public class MelterTileEntityRenderer extends TileEntityRenderer<MelterTileEntity> {

    private final MelterFluidModel fluidModel = new MelterFluidModel();

    @Override
    public void render(MelterTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage) {
        int fluidAmount = tile.getTank().getFluid().getAmount();
        Fluid fluid = tile.getTank().getFluid().getFluid();

        ResourceLocation fluidTexture = MelterScreen.getFluidTexture(fluid);
        if (fluidTexture != null && !fluidTexture.toString().equals("minecraft:textures/white.png")) {
            GlStateManager.pushMatrix();
            float yOff = MelterScreen.mapFluid(fluidAmount, 0.0f, 0.1f);
            GlStateManager.translatef((float) x, (float) y + 0.26f + yOff, (float) z); /// TODO: Fix texture stretching! ///

            int color = fluid.getAttributes().getColor(this.getWorld(), tile.getPos());
            float[] colors = new float[]{((color >> 16) & 0xFf) / 255.0f, ((color >> 8) & 0xFf) / 255.0f, (color & 0xFf) / 255.0f, ((color >> 24) & 0xFf) / 255.0f};
            GlStateManager.color4f(colors[0], colors[1], colors[2], colors[3]);

            GlStateManager.enableBlend();
            this.bindTexture(fluidTexture);
            fluidModel.render();
            GlStateManager.disableBlend();

            GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.popMatrix();
        }
    }
}
