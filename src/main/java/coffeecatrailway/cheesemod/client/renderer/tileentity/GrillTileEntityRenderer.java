package coffeecatrailway.cheesemod.client.renderer.tileentity;

import coffeecatrailway.cheesemod.block.GrillBlock;
import coffeecatrailway.cheesemod.tileentity.GrillTileEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 27/08/2019
 */
@OnlyIn(Dist.CLIENT)
public class GrillTileEntityRenderer extends TileEntityRenderer<GrillTileEntity> {

    @Override
    public void render(GrillTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage) {
        Direction direction = Direction.byHorizontalIndex(tile.getBlockState().get(GrillBlock.FACING).getHorizontalIndex() % 4);

        ItemStack ingredient = tile.getStackInSlot(0);
        ItemStack output = tile.getStackInSlot(2);

        renderItemStack(ingredient, direction, x, y, z, -0.2F, -0.15F, 25.0f);
        renderItemStack(output, direction, x, y, z, 0.2F, 0.15F, 45.0f);
    }

    private void renderItemStack(ItemStack stack, Direction direction, double x, double y, double z, float xOff, float yOff, float rotOff) {
        if (stack != ItemStack.EMPTY) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef((float) x + 0.5f, (float) y + 1.005f, (float) z + 0.5f);
            GlStateManager.rotatef(-direction.getHorizontalAngle(), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.translatef(xOff, yOff, 0.0F);
            GlStateManager.rotatef(rotOff, 0.0F, 0.0F, 1.0F);
            GlStateManager.scalef(0.375f, 0.375f, 0.375f);
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            GlStateManager.popMatrix();
        }
    }
}
