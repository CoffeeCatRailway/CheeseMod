package coffeecatteam.cheesemod.gui.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.gui.container.buttons.TestGUIButton;
import coffeecatteam.cheesemod.init.InitItem;
import coffeecatteam.cheesemod.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

public class GuiTest extends GuiScreen {

	final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/book.png");
	Logger logger = Utils.getLogger();

	int guiWidth = 175;
	int guiHeight = 228;

	String format = "gui.test_gui.";
	String titleDefault = I18n.format(format + "title");
	String close = I18n.format(format + "close");
	String changeTitle = I18n.format(format + "change_title");

	String title = titleDefault;
	int titleColor = 0xE2E2E2;

	GuiButton button1;
	TestGUIButton arrow;
	final int button1id = 0, arrowid = 1;
	final int button1width = 100;

	GuiTextField textBox;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int centerX = (width / 2) - guiWidth / 2;
		int centerY = (height / 2) - guiHeight / 2;

		// GUI Texture
		GlStateManager.pushMatrix();
		{
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			GlStateManager.color(1, 1, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth, guiHeight);
		}
		GlStateManager.popMatrix();

		// Knife
		ItemStack item = new ItemStack(InitItem.KNIFE);
		int itemX = width / 2 - 16;
		int itemY = (centerY + 50) + 8;

		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(itemX, itemY, 0);
			GlStateManager.scale(3, 3, 3);
			mc.getRenderItem().renderItemAndEffectIntoGUI(item, 0, 0);
		}
		GlStateManager.popMatrix();

		// Knife Tooltip
		List<String> text = new ArrayList<String>();
		text.add(item.getDisplayName());
		drawTooltip(text, mouseX, mouseY, itemX, itemY + 16, 32, 32);

		drawCenteredString(fontRenderer, title, width / 2, centerY + 10, titleColor);
		textBox.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public void drawTooltip(List<String> lines, int mouseX, int mouseY, int posX, int posY, int width, int height) {
		if (mouseX >= posX && mouseX <= (posX) + width && mouseY >= posY && mouseY <= (posY) + height) {
			drawHoveringText(lines, mouseX, mouseY);
		}
	}

	@Override
	public void initGui() {
		int centerX = (width / 2) - guiWidth / 2;
		int centerY = (height / 2) - guiHeight / 2;
		buttonList.clear();
		buttonList.add(button1 = new GuiButton(button1id, (width / 2) - button1width / 2, centerY + 25, button1width,
				20, changeTitle));
		buttonList.add(arrow = new TestGUIButton(arrowid, (centerX + guiWidth) - 20, (centerY + guiHeight) - 20));
		textBox = new GuiTextField(0, fontRenderer, (width / 2) - button1width / 2, centerY + 50, 100, 20);
		super.initGui();
	}

	public void updateButtons() {
		if (title.equals(titleDefault)) {
			button1.enabled = true;
		} else if (title.equals(close)) {
			mc.displayGuiScreen(null);
		} else {
			button1.enabled = false; // 1:33:44 / 2:07:45
		}
	}

	public void updateTextBoxes() {
		if (!textBox.getText().isEmpty()) {
			if (!textBox.isFocused()) {
				title = textBox.getText();
				if (title.equals(titleDefault))
					titleColor = 0xE2E2E2;
				else
					titleColor = 0xFF4A00;
			}
		}
		updateButtons();
	}

	@Override
	public void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case button1id:
			title = textBox.getText();
			titleColor = 0xE2E2E2;
			break;
		case arrowid:
			mc.displayGuiScreen(new GuiGameOver(new TextComponentString("§4HA! There is no other pages!")));
		}
		updateButtons();
		super.actionPerformed(button);
	}

	@Override
	public void keyTyped(char typedChar, int keyCode) throws IOException {
		// logger.info(typedChar+" "+keyCode);
		textBox.textboxKeyTyped(typedChar, keyCode);
		if (!textBox.isFocused() && typedChar == 'e') {
			mc.displayGuiScreen(null);
		} else if (textBox.isFocused() && keyCode == 28) {
			textBox.setFocused(false);
		}
		updateTextBoxes();
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		textBox.mouseClicked(mouseX, mouseY, mouseButton);
		updateTextBoxes();
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void onGuiClosed() {
		mc.player.sendMessage(new TextComponentString("§4WHY?!"));
		super.onGuiClosed();
	}
}
