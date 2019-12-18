package coffeecatrailway.coffeecheese.client.gui.screen;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.client.gui.container.FoodDrawContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 4/08/2019
 */
@OnlyIn(Dist.CLIENT)
public class FoodDrawScreen extends ContainerScreen<FoodDrawContainer> implements IHasContainer<FoodDrawContainer> {

    private static final ResourceLocation GUI_CHEESE = CheeseMod.getLocation("textures/gui/container/cheese_draw.png");
    private static final ResourceLocation GUI_HAM = CheeseMod.getLocation("textures/gui/container/ham_draw.png");
    private final ResourceLocation GUI_TEXTURE;

    public FoodDrawScreen(FoodDrawContainer container, PlayerInventory inventory, ITextComponent title) {
        super(container, inventory, title);
        this.passEvents = false;

        if (title.getString().toLowerCase().contains("ham"))
            GUI_TEXTURE = GUI_HAM;
        else
            GUI_TEXTURE = GUI_CHEESE;
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
        this.font.drawString(this.title.getFormattedText(), 8.0f, 6.0f, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, (float) (this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
        this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
