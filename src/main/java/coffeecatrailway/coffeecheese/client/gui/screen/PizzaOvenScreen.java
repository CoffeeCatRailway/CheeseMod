package coffeecatrailway.coffeecheese.client.gui.screen;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.client.gui.container.PizzaOvenContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * @author CoffeeCatRailway
 * Created: 10/01/2020
 */
public class PizzaOvenScreen extends ContainerScreen<PizzaOvenContainer> {

    public static final ResourceLocation GUI_TEXTURE = CheeseMod.getLocation("textures/gui/container/pizza_oven.png");

    public PizzaOvenScreen(PizzaOvenContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = this.title.getFormattedText();
        this.font.drawString(title, (float) (this.xSize / 2 - this.font.getStringWidth(title) / 2) + 1, 6.0f, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, (float) (this.ySize - 96 + 4), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
        if (this.container.isBurning()) {
            int k = this.container.getBurnLeftScaled();
            this.blit(i + 77, j + 34 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.container.getCookProgressionScaled();
        this.blit(i + 101, j + 33, 176, 14, l + 1, 16);
    }
}
