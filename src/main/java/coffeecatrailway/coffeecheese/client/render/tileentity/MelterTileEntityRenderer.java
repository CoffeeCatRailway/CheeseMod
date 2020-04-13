package coffeecatrailway.coffeecheese.client.render.tileentity;

import coffeecatrailway.coffeecheese.client.gui.screen.MelterScreen;
import coffeecatrailway.coffeecheese.common.tileentity.MelterTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;

/**
 * @author CoffeeCatRailway
 * Created: 6/09/2019
 * <p>
 * * Some render code is referenced from:
 * * - https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing/blob/1.15/src/main/java/com/buuz135/industrial/proxy/client/render/FluidConveyorTESR.java
 */
@OnlyIn(Dist.CLIENT)
public class MelterTileEntityRenderer extends TileEntityRenderer<MelterTileEntity> {

    public MelterTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(MelterTileEntity tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer typeBuffer, int combinedLight, int combinedOverlay) {
        if (!tile.getTankA().isEmpty()) {
            RenderSystem.pushMatrix();
            Texture texture = Minecraft.getInstance().getTextureManager().getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            if (texture instanceof AtlasTexture) {
                FluidStack fluid = tile.getTankA().getFluid();
                TextureAtlasSprite fluidAtlas = ((AtlasTexture) texture).getSprite(fluid.getFluid().getAttributes().getStillTexture(fluid));
                float posY = 4.1f / 16f + MelterScreen.mapFluid(tile.getTankA().getFluidAmount(), 0f, 2.85f) / 16f;
                float right = 1.0f / 16.0f;
                float left = 15.0f / 16.0f;
                IVertexBuilder buffer = typeBuffer.getBuffer(RenderTypeHelper.MELTER.applyTexture(new ResourceLocation(fluidAtlas.getName().getNamespace(), "textures/" + fluidAtlas.getName().getPath() + ".png")));
                Color color = new Color(fluid.getFluid().getAttributes().getColor(tile.getTankA().getFluid()));

                matrixStack.push();
                Matrix4f matrix = matrixStack.getLast().getMatrix();
                float animation = 16 * fluidAtlas.getUvShrinkRatio() * (tile.getWorld().getGameTime() % fluidAtlas.getFrameCount());
                buffer.pos(matrix, left, posY, right).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).tex(0f, 0f + animation).endVertex();
                buffer.pos(matrix, right, posY, right).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).tex(1f, 0f + animation).endVertex();
                buffer.pos(matrix, right, posY, left).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).tex(1f, 16f / (fluidAtlas.getHeight() * fluidAtlas.getFrameCount()) + animation).endVertex();
                buffer.pos(matrix, left, posY, left).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).tex(0f, 16f / (fluidAtlas.getHeight() * fluidAtlas.getFrameCount()) + animation).endVertex();
                matrixStack.pop();
            }
            RenderSystem.popMatrix();
        }
    }
}
