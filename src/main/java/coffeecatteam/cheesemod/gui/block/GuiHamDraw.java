package coffeecatteam.cheesemod.gui.block;

import java.awt.Color;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.gui.container.ContainerHamDraw;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityHamDraw;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiHamDraw extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(Reference.MODID,
			"textures/gui/ham_draw.png");

	public GuiHamDraw(InventoryPlayer player, TileEntityHamDraw hamDrawTile) {
		super(new ContainerHamDraw(player, hamDrawTile));
		xSize = 176;
		ySize = 166;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(new TextComponentTranslation("tile.ham_draw.name").getFormattedText(), 5, 5,
				Color.darkGray.getRGB());
		fontRenderer.drawString("Inventory", 5, (ySize/2)-10, Color.darkGray.getRGB());
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
