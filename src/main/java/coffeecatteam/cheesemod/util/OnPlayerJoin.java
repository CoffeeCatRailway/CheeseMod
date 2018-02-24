package coffeecatteam.cheesemod.util;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.config.Config;
import coffeecatteam.cheesemod.init.InitItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@EventBusSubscriber(modid = Reference.MODID)
public class OnPlayerJoin {

	@SubscribeEvent
	public void PlayerJoin(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		Config.load("cheesemod");
		if (!player.world.isRemote && !Config.getPlayerHasLoggedIn()) {
			NBTTagCompound nbt = player.getEntityData();
			boolean loggedInBefore = nbt.getBoolean("loggedInBefore");
			String msg = "";
			String playerName = TextFormatting.GOLD.toString() + TextFormatting.ITALIC.toString() + player.getName()
					+ TextFormatting.RESET.toString();

			if (!loggedInBefore) {
				player.addItemStackToInventory(new ItemStack(InitItem.CHEESE_SLICE, 2));
				msg = "Welcome, " + playerName + "! \nAre you here for " + TextFormatting.GOLD.toString() + "cheese"
						+ TextFormatting.RESET.toString() + "?";
			} else {
				msg = "Hello again " + playerName + "! \nAre you here for " + TextFormatting.GOLD.toString() + "cheese"
						+ TextFormatting.RESET.toString() + "..? If so, there's no more!";
			}
			player.sendMessage(new TextComponentString(msg));
			nbt.setBoolean("loggedInBefore", true);
			Config.setPlayerHasLoggedIn(true);
		}
	}
}
