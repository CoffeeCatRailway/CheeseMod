package coffeecatteam.cheesemod.util.handlers;

import coffeecatteam.cheesemod.gui.block.GuiCheeseDraw;
import coffeecatteam.cheesemod.gui.block.GuiCrackerMaker;
import coffeecatteam.cheesemod.gui.block.GuiGrill;
import coffeecatteam.cheesemod.gui.block.GuiHamDraw;
import coffeecatteam.cheesemod.gui.container.ContainerCheeseDraw;
import coffeecatteam.cheesemod.gui.container.ContainerCrackerMaker;
import coffeecatteam.cheesemod.gui.container.ContainerGrill;
import coffeecatteam.cheesemod.gui.container.ContainerHamDraw;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityCheeseDraw;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityCrackerMaker;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityGrill;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityHamDraw;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int CHEESE_DRAW_GUI_ID = 0;
	public static final int HAM_DRAW_GUI_ID = 1;
	public static final int GRILL_GUI_ID = 2;
	public static final int CRACKER_MAKER_GUI_ID = 3;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

		switch (ID) {
		case CHEESE_DRAW_GUI_ID:
			return new ContainerCheeseDraw(player.inventory, (TileEntityCheeseDraw) te);
		case HAM_DRAW_GUI_ID:
			return new ContainerHamDraw(player.inventory, (TileEntityHamDraw) te);
		case GRILL_GUI_ID:
			return new ContainerGrill(player.inventory, (TileEntityGrill) te);
		case CRACKER_MAKER_GUI_ID:
			return new ContainerCrackerMaker(player.inventory, (TileEntityCrackerMaker) te);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

		switch (ID) {
		case CHEESE_DRAW_GUI_ID:
			return new GuiCheeseDraw(player.inventory, (TileEntityCheeseDraw) te);
		case HAM_DRAW_GUI_ID:
			return new GuiHamDraw(player.inventory, (TileEntityHamDraw) te);
		case GRILL_GUI_ID:
			return new GuiGrill(player.inventory, (TileEntityGrill) te);
		case CRACKER_MAKER_GUI_ID:
			return new GuiCrackerMaker(player.inventory, (TileEntityCrackerMaker) te);
		default:
			return null;
		}
	}
}
