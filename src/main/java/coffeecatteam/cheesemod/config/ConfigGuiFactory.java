package coffeecatteam.cheesemod.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import coffeecatteam.cheesemod.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigGuiFactory implements IModGuiFactory {

	private static String configLangFormat = Config.getConfigLangFormat();

	@Override
	public void initialize(Minecraft minecraftInstance) {
	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new ConfigGui(parentScreen);
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	public static class ConfigGui extends GuiConfig {

		public ConfigGui(GuiScreen parent) {
			super(parent, getConfigElements(), Reference.MODID, false, false,
					I18n.format(configLangFormat + "main_title"));
		}
	}

	public static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<>();
		list.add(new DummyCategoryElement(I18n.format(configLangFormat+"other"), configLangFormat+"other",
				CategoryOther.class));
		list.add(new DummyCategoryElement(I18n.format(configLangFormat + "biome_spawning"),
				configLangFormat + "biome_spawning", CategoryBiomeSpawning.class));
		list.add(new DummyCategoryElement(I18n.format(configLangFormat + "food_suit_scale"),
				configLangFormat + "food_suit_scale", CategoryFoodSuitScale.class));
		return list;
	}

	public static class CategoryOther extends CategoryEntry {

		public CategoryOther(GuiConfig owningScreen, GuiConfigEntries owningEntryList,
				IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}

		@Override
		protected GuiScreen buildChildScreen() {
			Configuration config = Config.getConfig("cheesemod");
			ConfigElement category = new ConfigElement(
					config.getCategory(Config.CATEGORY_OTHER));

			List<IConfigElement> propOnScreen = category.getChildElements();
			String windowTitle = I18n.format(configLangFormat+"other");
			
			return new GuiConfig(owningScreen, propOnScreen, owningScreen.modID,
					this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
					this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
		}
	}

	public static class CategoryBiomeSpawning extends CategoryEntry {

		public CategoryBiomeSpawning(GuiConfig owningScreen, GuiConfigEntries owningEntryList,
				IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}

		@Override
		protected GuiScreen buildChildScreen() {
			Configuration config = Config.getConfig("cheesemod");
			ConfigElement category = new ConfigElement(
					config.getCategory(Config.CATEGORY_BIOME_SPAWNING));

			List<IConfigElement> propOnScreen = category.getChildElements();
			String windowTitle = I18n.format(configLangFormat + "biome_spawning");
			return new GuiConfig(owningScreen, propOnScreen, owningScreen.modID,
					this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
					this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
		}
	}

	public static class CategoryFoodSuitScale extends CategoryEntry {

		public CategoryFoodSuitScale(GuiConfig owningScreen, GuiConfigEntries owningEntryList,
				IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}

		@Override
		protected GuiScreen buildChildScreen() {
			Configuration config = Config.getConfig("cheesemod");
			ConfigElement category = new ConfigElement(
					config.getCategory(Config.CATEGORY_FOOD_SUIT_SCALE));

			List<IConfigElement> propOnScreen = category.getChildElements();
			String windowTitle = I18n.format(configLangFormat + "food_suit_scale");
			return new GuiConfig(owningScreen, propOnScreen, owningScreen.modID,
					this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
					this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
		}
	}
}
