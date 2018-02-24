package coffeecatteam.cheesemod.gui.container.buttons;

import coffeecatteam.cheesemod.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class TestGUIButton extends GuiButton {

	final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/gui/book.png");

	int buttonWidth = 16;
	int buttonHeight = 14;
	int u = 175;
	int v = 1;

	public TestGUIButton(int buttonId, int x, int y) {
		super(buttonId, x, y, 16, 14, "");
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if (visible) {
			mc.renderEngine.bindTexture(texture);
			if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height)
				this.hovered = true;
			else
				this.hovered = false;
			if (this.hovered)
				v = 18;
			else
				v = 1;
			drawTexturedModalRect(x, y, u, v, width, height);
		}
	}
}
