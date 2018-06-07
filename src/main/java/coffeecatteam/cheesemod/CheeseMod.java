package coffeecatteam.cheesemod;

import coffeecatteam.cheesemod.config.Config;
import coffeecatteam.cheesemod.objects.tabs.TabCheese;
import coffeecatteam.cheesemod.objects.tabs.TabCheeseArmorTools;
import coffeecatteam.cheesemod.objects.tabs.TabCheeseFoods;
import coffeecatteam.cheesemod.proxy.ProxyCommon;
import coffeecatteam.cheesemod.util.Events;
import coffeecatteam.cheesemod.util.OreDictionaries;
import coffeecatteam.cheesemod.util.Utils;
import coffeecatteam.cheesemod.util.handlers.GuiHandler;
import coffeecatteam.cheesemod.world.types.WorldTypeCheese;
import coffeecatteam.cheesemod.world.types.WorldTypeHam;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY)
public class CheeseMod {

    public static final CreativeTabs CHEESETAB = new TabCheese("cheesetab");
	public static final CreativeTabs CHEESEARMORTOOLSTAB = new TabCheeseArmorTools("cheesearmortoolstab");
	public static final CreativeTabs CHEESEFOODSTAB = new TabCheeseFoods("cheesefoodstab");

	public static Logger logger = Utils.getLogger();

	@Mod.Instance
	public static CheeseMod instance;

	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
	private static ProxyCommon proxy;

	public static final boolean DEVELOPER_MODE = true;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) throws LaunchException {
		if (DEVELOPER_MODE && !(Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment")) {
			throw new LaunchException();
		}
		Config.load("cheesemod");

		proxy.preInit(event);
		logger.info("Pre Initialize");
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init(event);
		OreDictionaries.init();
		logger.info("Initialize");

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
        WorldType CHEESE = new WorldTypeCheese();
        WorldType HAM = new WorldTypeHam();
        MinecraftForge.EVENT_BUS.register(new Events());

		logger.info("Post Initialize");
	}
}
