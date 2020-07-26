package coffeecatrailway.coffeecheese;

import coffeecatrailway.coffeecheese.integration.registrate.CheeseLang;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

/**
 * @author CoffeeCatRailway
 * Created: 23/07/2020
 */
public abstract class CheeseItemGroup extends ItemGroup {

    private static final ResourceLocation TAB_ITEMS_TEXTURE = CheeseMod.getLocation("textures/gui/container/creative_inventory/tab_items.png");
    private static final ResourceLocation TABS_TEXTURE = CheeseMod.getLocation("textures/gui/container/creative_inventory/tabs.png");

    public CheeseItemGroup(String lang) {
        super(CheeseMod.MOD_ID);
        CheeseLang.GROUPS.put(this, lang);
    }

    public CheeseItemGroup(String label, String lang) {
        super(CheeseMod.MOD_ID + "." + label);
        CheeseLang.GROUPS.put(this, lang);
    }

    @Override
    public ResourceLocation getBackgroundImage() {
        return TAB_ITEMS_TEXTURE;
    }

    @Override
    public ResourceLocation getTabsImage() {
        return TABS_TEXTURE;
    }
}
