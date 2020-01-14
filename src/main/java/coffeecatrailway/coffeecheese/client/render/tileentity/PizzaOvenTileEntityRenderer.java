package coffeecatrailway.coffeecheese.client.render.tileentity;

import coffeecatrailway.coffeecheese.common.block.PizzaOvenBlock;
import coffeecatrailway.coffeecheese.common.tileentity.PizzaOvenTileEntity;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 13/01/2020
 */
@OnlyIn(Dist.CLIENT)
public class PizzaOvenTileEntityRenderer extends TileEntityRenderer<PizzaOvenTileEntity> {

    @Override
    public void render(PizzaOvenTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage) {
        BlockState state = tile.getWorld().getBlockState(tile.getPos());
        if (state.getBlock() != ModBlocks.PIZZA_OVEN.get())
            return;

        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);

        ItemStack outStack = tile.inventory.getStackInSlot(10);
        if (outStack.getCount() > 0) {
            Minecraft mc = Minecraft.getInstance();
            ItemRenderer renderItem = mc.getItemRenderer();

            double off = (state.get(PizzaOvenBlock.FACING).getHorizontalAngle() / 90d) / 10d;
            double xo = state.get(PizzaOvenBlock.FACING) == Direction.EAST ? off : state.get(PizzaOvenBlock.FACING) == Direction.WEST ? -off : 0d;
            double zo = state.get(PizzaOvenBlock.FACING) == Direction.NORTH ? -off : state.get(PizzaOvenBlock.FACING) == Direction.SOUTH ? off : 0d;
            GlStateManager.translated(x + .5d + xo, y + .2d, z + .5d + zo);

            float angle = -state.get(PizzaOvenBlock.FACING).getHorizontalAngle();
            GlStateManager.rotated(angle, 0d, 1d, 0d);
            GlStateManager.rotated(-90d, 1d, 0d, 0d);

            GlStateManager.scalef(1.1f, 1.1f, 1.1f);
            GlStateManager.color4f(1f, 1f, 1f, 1f);

            renderItem.renderItem(outStack, ItemCameraTransforms.TransformType.GROUND);
        }

        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
    }
}
