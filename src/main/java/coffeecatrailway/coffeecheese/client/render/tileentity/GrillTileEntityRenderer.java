package coffeecatrailway.coffeecheese.client.render.tileentity;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.block.GrillBlock;
import coffeecatrailway.coffeecheese.common.tileentity.GrillTileEntity;
import coffeecatrailway.coffeecheese.registry.ModItems;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * @author CoffeeCatRailway
 * Created: 8/01/2020
 *
 * Parts copied from https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing/blob/1.14/src/main/java/com/buuz135/industrial/proxy/client/render/FluidConveyorTESR.java
 */
@OnlyIn(Dist.CLIENT)
public class GrillTileEntityRenderer extends TileEntityRenderer<GrillTileEntity> {

    @Override
    public void render(GrillTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage) {
        if (tile.getBlockState().get(GrillBlock.HAS_CATCHER)) {
            GlStateManager.pushMatrix();
            GlStateManager.translated(x + 0.5d, y + 0.875d, z + 0.5d);
            Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(ModItems.OIL_CATCHER.get()), ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();

            if (tile.data.get(5) > 0) {
                GlStateManager.pushMatrix();
                GlStateManager.translatef((float) x, (float) y, (float) z);

                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder buffer = tessellator.getBuffer();
                this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
                RenderHelper.disableStandardItemLighting();
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GlStateManager.enableBlend();
                GlStateManager.disableCull();

                if (Minecraft.isAmbientOcclusionEnabled())
                    GlStateManager.shadeModel(GL11.GL_SMOOTH);
                else
                    GlStateManager.shadeModel(GL11.GL_FLAT);

                buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
                FluidStack fluid = tile.getTank().getFluid();
                TextureAtlasSprite still = Minecraft.getInstance().getTextureMap().getAtlasSprite(fluid.getFluid().getAttributes().getStill(fluid).toString());
                double posY = 7.1f / 16f + CheeseMod.map(tile.data.get(5), 0f, FluidAttributes.BUCKET_VOLUME, 0f, .85f) / 16f;
                double right = 2 / 16f;
                double left = 14 / 16f;
                Color color = new Color(fluid.getFluid().getAttributes().getColor(tile.getTank().getFluid()));

                buffer.pos(right, posY, right).tex(still.getInterpolatedU(0), still.getInterpolatedV(0)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
                buffer.pos(left, posY, right).tex(still.getInterpolatedU(16), still.getInterpolatedV(0)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
                buffer.pos(left, posY, left).tex(still.getInterpolatedU(16), still.getInterpolatedV(16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
                buffer.pos(right, posY, left).tex(still.getInterpolatedU(0), still.getInterpolatedV(16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();

                tessellator.draw();
                GlStateManager.popMatrix();
            }
        }
    }
}
