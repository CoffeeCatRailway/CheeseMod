package coffeecatrailway.coffeecheese.client.render.tileentity;

import coffeecatrailway.coffeecheese.common.block.PizzaOvenBlock;
import coffeecatrailway.coffeecheese.common.tileentity.PizzaOvenTileEntity;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
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

    public PizzaOvenTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(PizzaOvenTileEntity tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer typeBuffer, int combinedLight, int combinedOverlay) {
        BlockState state = tile.getWorld().getBlockState(tile.getPos());
        if (state.getBlock() != ModBlocks.PIZZA_OVEN.get()) return;

        ItemStack outStack = tile.inventory.getStackInSlot(10);
        if (outStack.getCount() > 0) {
            matrixStack.push();

            double off = (state.get(PizzaOvenBlock.FACING).getHorizontalAngle() / 90d) / 10d;
            double xo = state.get(PizzaOvenBlock.FACING) == Direction.EAST ? off : state.get(PizzaOvenBlock.FACING) == Direction.WEST ? -off : 0d;
            double zo = state.get(PizzaOvenBlock.FACING) == Direction.NORTH ? -off : state.get(PizzaOvenBlock.FACING) == Direction.SOUTH ? off : 0d;
            matrixStack.translate(.5d + xo, .2d, .5d + zo);

            float angle = -state.get(PizzaOvenBlock.FACING).getHorizontalAngle();
            matrixStack.rotate(Vector3f.YP.rotationDegrees(angle));
            matrixStack.rotate(Vector3f.XP.rotationDegrees(-90.0f));

            matrixStack.scale(1.1f, 1.1f, 1.1f);

            Minecraft.getInstance().getItemRenderer().renderItem(outStack, ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrixStack, typeBuffer);
            matrixStack.pop();
        }
    }
}
