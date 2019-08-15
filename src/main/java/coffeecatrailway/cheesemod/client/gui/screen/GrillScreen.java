package coffeecatrailway.cheesemod.client.gui.screen;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.client.gui.container.GrillContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 9/08/2019
 */
@OnlyIn(Dist.CLIENT)
public class GrillScreen extends ContainerScreen<GrillContainer> {

    private static final ResourceLocation GUI_TEXTURES = CheeseMod.getLocation("textures/gui/container/grill.png");
    private static final ResourceLocation OIL_OVERLAY = CheeseMod.getLocation("textures/block/grill_oil_overlay.png");

    public GrillScreen(GrillContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

        int i = this.guiLeft + 35;
        int j = this.guiTop + 8;
        if (mouseX >= i && mouseX <= i + 16)
            if (mouseY >= j && mouseY <= j + 16 * 4)
                this.renderTooltip("Oil: " + container.getOil() + "mb/1000mb", mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = this.title.getFormattedText();
        this.font.drawString(title, (float) (this.xSize / 2 - this.font.getStringWidth(title) / 2) + 1, 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 4), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI_TEXTURES);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
        if (this.container.isBurning()) {
            int k = this.container.getBurnLeftScaled();
            this.blit(i + 61, j + 34 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.container.getCookProgressionScaled();
        this.blit(i + 85, j + 32, 176, 14, l + 1, 16);

        GlStateManager.enableBlend();
        int k = (int) map(this.container.getOil(), 0, 1000, 0, 64);
        this.blit(i + 35, j + 8 + 64 - k, 176, 160 - k + 1, 16, k);
        GlStateManager.disableBlend();

        this.blit(i + 35, j + 8, 176, 32, 16, 64);
    }

    private float map(float from, float fromMin, float fromMax, float toMin, float toMax) {
        float fromAbs = from - fromMin;
        float fromMaxAbs = fromMax - fromMin;

        float normal = fromAbs / fromMaxAbs;

        float toMaxAbs = toMax - toMin;
        float toAbs = toMaxAbs * normal;

        float to = toAbs + toMin;

        return to;
    }
}
