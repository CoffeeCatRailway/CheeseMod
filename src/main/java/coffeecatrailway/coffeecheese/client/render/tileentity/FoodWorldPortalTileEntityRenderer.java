package coffeecatrailway.coffeecheese.client.render.tileentity;

import coffeecatrailway.coffeecheese.common.tileentity.FoodWorldPortalTileEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author CoffeeCatRailway
 * Created: 22/01/2020
 * <p>
 * Copied from {@link net.minecraft.client.renderer.tileentity.EndPortalTileEntityRenderer}
 */
@OnlyIn(Dist.CLIENT)
public class FoodWorldPortalTileEntityRenderer extends TileEntityRenderer<FoodWorldPortalTileEntity> {

    private static final Random RANDOM = new Random(31100L);
    private static final List<RenderType> RENDER_TYPES = IntStream.range(0, 16).mapToObj((iteration) -> RenderType.getEndPortal(iteration + 1)).collect(ImmutableList.toImmutableList());

    public FoodWorldPortalTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(FoodWorldPortalTileEntity tile, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        RANDOM.setSeed(31100L);
        double d0 = tile.getPos().distanceSq(this.renderDispatcher.renderInfo.getProjectedView(), true);
        int i = this.getPasses(d0);
        float f = this.getOffset();
        Matrix4f matrix4f = matrix.getLast().getMatrix();
        this.renderCube(f, 0.15F, matrix4f, buffer.getBuffer(RENDER_TYPES.get(0)));

        for(int j = 1; j < i; ++j) {
            this.renderCube(f, 2.0F / (float)(18 - j), matrix4f, buffer.getBuffer(RENDER_TYPES.get(j)));
        }
    }

    private void renderCube(float p_228883_2_, float p_228883_3_, Matrix4f matrix, IVertexBuilder vertexBuilder) {
        float f = (RANDOM.nextFloat() * 0.5F + 0.1F) * p_228883_3_;
        float f1 = (RANDOM.nextFloat() * 0.5F + 0.4F) * p_228883_3_;
        float f2 = (RANDOM.nextFloat() * 0.5F + 0.5F) * p_228883_3_;
        this.renderFace(matrix, vertexBuilder, 0.0F, 1.0F, p_228883_2_, p_228883_2_, 1.0F, 1.0F, 0.0F, 0.0F, f, f1, f2);
    }

    private void renderFace(Matrix4f matrix, IVertexBuilder vertexBuilder, float p_228884_4_, float p_228884_5_, float p_228884_6_, float p_228884_7_, float p_228884_8_, float p_228884_9_, float p_228884_10_, float p_228884_11_, float red, float green, float blue) {
        vertexBuilder.pos(matrix, p_228884_4_, p_228884_6_, p_228884_8_).color(red, green, blue, 1.0F).endVertex();
            vertexBuilder.pos(matrix, p_228884_5_, p_228884_6_, p_228884_9_).color(red, green, blue, 1.0F).endVertex();
            vertexBuilder.pos(matrix, p_228884_5_, p_228884_7_, p_228884_10_).color(red, green, blue, 1.0F).endVertex();
            vertexBuilder.pos(matrix, p_228884_4_, p_228884_7_, p_228884_11_).color(red, green, blue, 1.0F).endVertex();
    }

    protected int getPasses(double p_191286_1_) {
        if (p_191286_1_ > 36864.0D)
            return 1;
        else if (p_191286_1_ > 25600.0D)
            return 3;
        else if (p_191286_1_ > 16384.0D)
            return 5;
        else if (p_191286_1_ > 9216.0D)
            return 7;
        else if (p_191286_1_ > 4096.0D)
            return 9;
        else if (p_191286_1_ > 1024.0D)
            return 11;
        else if (p_191286_1_ > 576.0D)
            return 13;
        else
            return p_191286_1_ > 256.0D ? 14 : 15;
    }

    protected float getOffset() {
        return 0.75F;
    }
}
