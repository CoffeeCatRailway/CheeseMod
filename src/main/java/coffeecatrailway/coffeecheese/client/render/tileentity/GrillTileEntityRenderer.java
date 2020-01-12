package coffeecatrailway.coffeecheese.client.render.tileentity;

import coffeecatrailway.coffeecheese.common.block.GrillBlock;
import coffeecatrailway.coffeecheese.common.tileentity.GrillTileEntity;
import coffeecatrailway.coffeecheese.registry.ModItems;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 8/01/2020
 */
@OnlyIn(Dist.CLIENT)
public class GrillTileEntityRenderer extends TileEntityRenderer<GrillTileEntity> {

    @Override
    public void render(GrillTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage) {
        if (tile.getBlockState().get(GrillBlock.HAS_CATCHER)) {
            GlStateManager.pushMatrix();
            GlStateManager.translated(x + 0.5d, y + 0.875d, z + 0.5d);
            Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(ModItems.OIL_CATCHER.get()), ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();
        }
    }
}
