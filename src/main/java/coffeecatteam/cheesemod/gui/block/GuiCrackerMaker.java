package coffeecatteam.cheesemod.gui.block;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.config.Config;
import coffeecatteam.cheesemod.gui.container.ContainerCrackerMaker;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityCrackerMaker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCrackerMaker extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/gui/container/cracker_maker.png");
	private final InventoryPlayer player;
	private final TileEntityCrackerMaker tileentity;

	public GuiCrackerMaker(InventoryPlayer player, TileEntityCrackerMaker tileentity) {
		super(new ContainerCrackerMaker(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int textColor = 4210752;
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 4,
				4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2,
				4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		if (TileEntityCrackerMaker.isBurning(tileentity)) {
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 85, this.guiTop + 56 - k, 176, 12 - k, 14, k + 1);
		}

		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 23, 176, 14, l + 1, 16);
	}

	private int getBurnLeftScaled(int pixels) {
		int i = this.tileentity.getField(1);
		if (i == 0)
			i = 200;
		return this.tileentity.getField(0) * pixels / i;
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.tileentity.getField(2);
		int j = this.tileentity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
