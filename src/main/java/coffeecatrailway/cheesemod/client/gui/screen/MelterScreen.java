package coffeecatrailway.cheesemod.client.gui.screen;

import coffeecatrailway.cheesemod.CheeseMod;
import coffeecatrailway.cheesemod.client.gui.container.MelterContainer;
import coffeecatrailway.cheesemod.tileentity.MelterTileEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 4/09/2019
 */
@OnlyIn(Dist.CLIENT)
public class MelterScreen extends ContainerScreen<MelterContainer> {

    public static final ResourceLocation GUI_TEXTURE = CheeseMod.getLocation("textures/gui/container/melter.png");

    public MelterScreen(MelterContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

        int i = this.guiLeft + 97;
        int j = this.guiTop + 8;
        if (mouseX >= i && mouseX <= i + 34)
            if (mouseY >= j && mouseY <= j + 16 * 4)
                this.renderTooltip(getFormatedFluidString(Registry.FLUID.getByValue(container.getFluid()), container.getFluidAmount()), mouseX, mouseY);
    }

    public static String getFormatedFluidString(Fluid fluid, int amount) {
        String fluidName = I18n.format("block." + fluid.getRegistryName().toString().replace(":", "."));
        if (fluidName.contains("empty"))
            fluidName = "Empty";
        return I18n.format("gui." + CheeseMod.MOD_ID + ".category.melter.fluid", fluidName, amount, MelterTileEntity.FLUID_CAPTACITY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = this.title.getFormattedText();
        this.font.drawString(title, (float) (this.xSize / 2 - this.font.getStringWidth(title) / 2) - 9.0f, 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 4), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
        if (this.container.isBurning()) {
            int k = this.container.getBurnLeftScaled();
            this.blit(i + 44, j + 34 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.container.getCookProgressionScaled();
        this.blit(i + 68, j + 32, 176, 14, l + 1, 16);

        Fluid fluid = Registry.FLUID.getByValue(container.getFluid());
        if (fluid.isEquivalentTo(Fluids.WATER))
            GlStateManager.color4f(0.23922F, 0.42745F, 1.0F, 1.0F);

        ResourceLocation fluidTexture = getFluidTexture(fluid);
        if (fluidTexture != null) {
            GlStateManager.enableBlend();
            this.minecraft.getTextureManager().bindTexture(fluidTexture);
            int k = (int) mapFluid(this.container.getFluidAmount(), 0, 64);
            int h = getFluidTextureHeight(fluidTexture);
            h = (int) (h * h * 1.5f);
            this.blit(i + 97, j + 8 + 64 - k, 0, 64 - k + 1, 32, k, 16, h);
            GlStateManager.disableBlend();
        }

        if (fluid.isEquivalentTo(Fluids.WATER))
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
        this.blit(i + 97, j + 8, 176, 32, 32, 64);
    }

    public static float mapFluid(float amount, float min, float max) {
        return CheeseMod.map(amount, 0.0f, MelterTileEntity.FLUID_CAPTACITY, min, max);
    }

    public static int getFluidTextureHeight(ResourceLocation fluidTexture) {
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureMap().getSprite(fluidTexture);
        return sprite.getHeight();
    }

    public static ResourceLocation getFluidTexture(Fluid fluid) {
        ResourceLocation fluidTexture = fluid.getAttributes().getStillTexture();
        if (fluidTexture != null)
            return new ResourceLocation(fluidTexture.getNamespace(), "textures/" + fluidTexture.getPath() + ".png");
        return null;
    }
}
