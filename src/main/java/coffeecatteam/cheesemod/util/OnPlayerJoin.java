package coffeecatteam.cheesemod.util;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.config.Config;
import coffeecatteam.cheesemod.init.InitItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraft.util.text.event.HoverEvent;
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
			player.sendMessage(new TextComponentString(TextFormatting.GOLD + "-----------------------------------------------------"));
			
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

			TextComponentString link = new TextComponentString("click here!");
			link.getStyle().setBold(true);
			link.getStyle().setColor(TextFormatting.BLUE);
			link.getStyle().setClickEvent(new ClickEvent(Action.OPEN_URL, "https://discord.gg/k4fY4hc"));
			link.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new TextComponentString("CoffeeCatTeam discord server")));
			player.sendMessage(new TextComponentString("If you want to help with the development of the "
					+ TextFormatting.GOLD + TextFormatting.ITALIC + "CheeseMod" + TextFormatting.RESET + ", then ")
							.appendSibling(link).appendText(" (Must have discord!)"));

			player.sendMessage(new TextComponentString(TextFormatting.GOLD + "-----------------------------------------------------"));
		}
	}
}
