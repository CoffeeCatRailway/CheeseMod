package coffeecatrailway.coffeecheese.client.render.tileentity;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author CoffeeCatRailway
 * Created: 24/03/2020
 */
public class RenderTypeHelper {

    public static final TextureRenderType GRILL = new TextureRenderType("grill_fluid", DefaultVertexFormats.POSITION_COLOR_TEX, 7, 32, false, true);
    public static final TextureRenderType MELTER = new TextureRenderType("melter_fluid", DefaultVertexFormats.POSITION_COLOR_TEX, 7, 32, false, true);

    public static class TextureRenderType {

        private String id;
        private VertexFormat vertexFormat;
        private int glMode, bufferSize;
        private boolean useDelegate, needsSorting;

        private String renderState;
        private Runnable setupTask, clearTask;

        public TextureRenderType(String id, VertexFormat vertexFormat, int glMode, int bufferSize, boolean useDelegate, boolean needsSorting) {
            this(id, vertexFormat, glMode, bufferSize, useDelegate, needsSorting, "translucent_transparency", () -> {
                RenderSystem.enableBlend();
                RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                RenderSystem.enableAlphaTest();
                RenderHelper.disableStandardItemLighting();
                if (Minecraft.isAmbientOcclusionEnabled())
                    RenderSystem.shadeModel(GL11.GL_SMOOTH);
                else
                    RenderSystem.shadeModel(GL11.GL_FLAT);
                RenderSystem.disableCull();
            }, () -> {
                RenderSystem.disableBlend();
                RenderSystem.disableAlphaTest();
            });
        }

        public TextureRenderType(String id, VertexFormat vertexFormat, int glMode, int bufferSize, boolean useDelegate, boolean needsSorting, String renderState, Runnable setupTask, Runnable clearTask) {
            this.id = id;
            this.vertexFormat = vertexFormat;
            this.glMode = glMode;
            this.bufferSize = bufferSize;
            this.useDelegate = useDelegate;
            this.needsSorting = needsSorting;
            this.renderState = renderState;
            this.setupTask = setupTask;
            this.clearTask = clearTask;
        }

        public RenderType applyTexture(ResourceLocation texture) {
            RenderType.State state = RenderType.State.getBuilder().texture(new RenderState.TextureState(texture, false, false))
                    .transparency(new RenderState.TransparencyState(this.renderState, this.setupTask, this.clearTask)).build(true);
            return RenderType.makeType(this.id, this.vertexFormat, this.glMode, this.bufferSize, this.useDelegate, this.needsSorting, state);
        }
    }
}
