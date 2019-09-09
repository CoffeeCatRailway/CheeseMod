package coffeecatrailway.cheesemod.client.renderer.tileentity;

import coffeecatrailway.cheesemod.client.gui.screen.MelterScreen;
import coffeecatrailway.cheesemod.client.renderer.tileentity.model.MelterFluidModel;
import coffeecatrailway.cheesemod.tileentity.MelterTileEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
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
        if (fluidTexture != null) {
            GlStateManager.pushMatrix();

            RayTraceResult raytraceresult = this.rendererDispatcher.cameraHitResult;
            if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.BLOCK && tile.getPos().equals(((BlockRayTraceResult) raytraceresult).getPos())) {
                this.setLightmapDisabled(true);
                this.drawNameplate(tile, MelterScreen.getFormatedFluidString(fluid, fluidAmount), x, y - 0.5d, z, 12);
                this.setLightmapDisabled(false);
            }

            float yOff = MelterScreen.mapFluid(fluidAmount, 0.0f, 0.1f);
            GlStateManager.translatef((float) x, (float) y + 0.26f + yOff, (float) z); /// TODO: Fix texture stretching! ///

            GlStateManager.enableBlend();
            this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            String icon = fluid.getFluid().getAttributes().getStillTexture().toString();
            TextureAtlasSprite fluidSprite = Minecraft.getInstance().getTextureMap().getAtlasSprite(icon);

            int color = fluid.getFluid().getAttributes().getColor();
            float[] colors = new float[]{((color >> 24) & 0xFF) / 255.0f, ((color >> 16) & 0xFF) / 255.0f, ((color >> 8) & 0xFF) / 255.0f, (color & 0xFF) / 255.0f};

            GlStateManager.disableAlphaTest();
            GlStateManager.disableDepthTest();
            GlStateManager.depthMask(false);
            GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
            this.addQuad(bufferBuilder, 0.5, 0.0625, 0.0625, 0.5, 0.5, colors, fluidSprite);
            tessellator.draw();

            GlStateManager.depthMask(true);
            GlStateManager.enableDepthTest();
            GlStateManager.enableAlphaTest();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

            fluidModel.render();
            GlStateManager.disableBlend();

            GlStateManager.popMatrix();
        }
    }

    private void addQuad(BufferBuilder buffer, double posY, double posX1, double posZ1, double posX2, double posZ2, float[] colors, TextureAtlasSprite sprite) {
        buffer.pos(posX1, posY, posZ1).tex(sprite.getInterpolatedU(16 * posX1), sprite.getInterpolatedV(16 * posZ1)).color(colors[3], colors[1], colors[2], colors[0]).endVertex();
        buffer.pos(posX1, posY, posZ2).tex(sprite.getInterpolatedU(16 * posX1), sprite.getInterpolatedV(16 * posZ2)).color(colors[3], colors[1], colors[2], colors[0]).endVertex();
        buffer.pos(posX2, posY, posZ2).tex(sprite.getInterpolatedU(16 * posX2), sprite.getInterpolatedV(16 * posZ2)).color(colors[3], colors[1], colors[2], colors[0]).endVertex();
        buffer.pos(posX2, posY, posZ1).tex(sprite.getInterpolatedU(16 * posX2), sprite.getInterpolatedV(16 * posZ1)).color(colors[3], colors[1], colors[2], colors[0]).endVertex();
    }
}
