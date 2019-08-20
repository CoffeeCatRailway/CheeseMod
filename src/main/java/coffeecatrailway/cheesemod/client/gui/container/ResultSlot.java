package coffeecatrailway.cheesemod.client.gui.container;

import coffeecatrailway.cheesemod.tileentity.GrillTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.hooks.BasicEventHooks;

/**
 * @author CoffeeCatRailway
 * Created: 11/08/2019
 */
public class ResultSlot extends FurnaceResultSlot {

    private final PlayerEntity player;
    private int removeCount;

    public ResultSlot(PlayerEntity player, IInventory inventory, int index, int xPosition, int yPosition) {
        super(player, inventory, index, xPosition, yPosition);
        this.player = player;
    }

    @Override
    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack())
            this.removeCount += Math.min(amount, this.getStack().getCount());

        return super.decrStackSize(amount);
    }

    @Override
    protected void onCrafting(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.onCrafting(stack);
    }

    @Override
    protected void onCrafting(ItemStack stack) {
        stack.onCrafting(this.player.world, this.player, this.removeCount);
        if (!this.player.world.isRemote && this.inventory instanceof GrillTileEntity) {
            ((GrillTileEntity) this.inventory).giveExperience(this.player);
        }

        this.removeCount = 0;
        BasicEventHooks.firePlayerSmeltedEvent(this.player, stack);
    }
}
