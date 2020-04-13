package coffeecatrailway.coffeecheese.client.render.tileentity;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.common.block.GrillBlock;
import coffeecatrailway.coffeecheese.common.tileentity.GrillTileEntity;
import coffeecatrailway.coffeecheese.registry.ModItems;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;

/**
 * @author CoffeeCatRailway
 * Created: 8/01/2020
 * <p>
 * Some render code is referenced from:
 * - https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing/blob/1.15/src/main/java/com/buuz135/industrial/proxy/client/render/FluidConveyorTESR.java
 */
@OnlyIn(Dist.CLIENT)
public class GrillTileEntityRenderer extends TileEntityRenderer<GrillTileEntity> {

    public GrillTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(GrillTileEntity tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer typeBuffer, int combinedLight, int combinedOverlay) {
        if (tile.getBlockState().get(GrillBlock.HAS_CATCHER)) {
            matrixStack.push();
            matrixStack.translate(0.5d, 0.485d, 0.5d);
            matrixStack.rotate(Vector3f.XP.rotationDegrees(-90.0f));
            Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(ModItems.OIL_CATCHER.get()), ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrixStack, typeBuffer);
            matrixStack.pop();

            if (!tile.getTankB().isEmpty()) {
                RenderSystem.pushMatrix();
                Texture texture = Minecraft.getInstance().getTextureManager().getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
                if (texture instanceof AtlasTexture) {
                    FluidStack fluid = tile.getTankB().getFluid();
                    TextureAtlasSprite fluidAtlas = ((AtlasTexture) texture).getSprite(fluid.getFluid().getAttributes().getStillTexture(fluid));
                    float posY = 7.1f / 16f + CheeseMod.map(tile.getTankB().getFluidAmount(), 0f, tile.getTankB().getCapacity(), 0f, .85f) / 16f;
                    float right = 2.0f / 16.0f;
                    float left = 14.0f / 16.0f;
                    IVertexBuilder buffer = typeBuffer.getBuffer(RenderTypeHelper.GRILL.applyTexture(new ResourceLocation(fluidAtlas.getName().getNamespace(), "textures/" + fluidAtlas.getName().getPath() + ".png")));
                    Color color = new Color(fluid.getFluid().getAttributes().getColor(tile.getTankA().getFluid()));

                    matrixStack.push();
                    Matrix4f matrix = matrixStack.getLast().getMatrix();
                    float animation = 16 * fluidAtlas.getUvShrinkRatio() * (tile.getWorld().getGameTime() % fluidAtlas.getFrameCount());
                    buffer.pos(matrix, left, posY, right).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).tex(0, 0 + animation).endVertex();
                    buffer.pos(matrix, right, posY, right).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).tex(1f, 0 + animation).endVertex();
                    buffer.pos(matrix, right, posY, left).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).tex(1f, 16f / (fluidAtlas.getHeight() * fluidAtlas.getFrameCount()) + animation).endVertex();
                    buffer.pos(matrix, left, posY, left).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).tex(0, 16f / (fluidAtlas.getHeight() * fluidAtlas.getFrameCount()) + animation).endVertex();
                    matrixStack.pop();
                }
                RenderSystem.popMatrix();
            }
        }
    }
}
