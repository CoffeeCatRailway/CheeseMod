package coffeecatrailway.cheesemod.client.render.tileentity;

import coffeecatrailway.cheesemod.client.gui.screen.MelterScreen;
import coffeecatrailway.cheesemod.client.render.tileentity.model.MelterFluidModel;
import coffeecatrailway.cheesemod.tileentity.MelterTileEntity;
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
            RayTraceResult raytraceresult = this.rendererDispatcher.cameraHitResult;
            if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.BLOCK && tile.getPos().equals(((BlockRayTraceResult) raytraceresult).getPos())) {
                this.setLightmapDisabled(true);
                this.drawNameplate(tile, MelterScreen.getFormatedFluidString(fluid, fluidAmount), x, y - 0.5d, z, 12);
                this.setLightmapDisabled(false);
            }

            GlStateManager.pushMatrix();
            float yOff = MelterScreen.mapFluid(fluidAmount, 0.0f, 0.1f);
            GlStateManager.translatef((float) x, (float) y + 0.26f + yOff, (float) z); /// TODO: Fix texture stretching! ///

            int color = fluid.getAttributes().getColor(this.getWorld(), tile.getPos());
            float[] colors = new float[]{((color >> 16) & 0xFF) / 255.0f, ((color >> 8) & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, ((color >> 24) & 0xFF) / 255.0f};
            GlStateManager.color4f(colors[0], colors[1], colors[2], colors[3]);

            GlStateManager.enableBlend();
            this.bindTexture(fluidTexture);
            fluidModel.render();
            GlStateManager.disableBlend();

            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
        }
    }
}
