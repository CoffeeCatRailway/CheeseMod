package coffeecatrailway.coffeecheese.client.render.item;

import coffeecatrailway.coffeecheese.common.item.SandwichItem;
import coffeecatrailway.coffeecheese.registry.ModItems;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;

/**
 * @author CoffeeCatRailway - Xero: https://github.com/Squishling
 * Created: 28/04/2020
 */
@OnlyIn(Dist.CLIENT)
public class SandwichStackRenderer extends ItemStackTileEntityRenderer {

    @Override
    public void render(ItemStack stack, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        super.render(stack, matrix, buffer, combinedLight, combinedOverlay);
        matrix.push();

        // Rotate & position
        matrix.translate(0.5f, 0.5f, 0.5f);
        matrix.rotate(Vector3f.YN.rotationDegrees(90));

        // NBT setup
        CompoundNBT nbt = stack.getOrCreateTag();
        ListNBT ingredients = nbt.getList(SandwichItem.TAG_INGREDIENTS, Constants.NBT.TAG_COMPOUND);
        ItemStack bread = new ItemStack(nbt.getBoolean(SandwichItem.TAG_TOASTED) ? ModItems.TOAST.get() : ModItems.BREAD_SLICE.get());


        // Move back if ingredients are present
        float offset;
        if (ingredients.size() > 0) {
            offset = -(0.06f * ingredients.size()) / 2.0f;
            matrix.translate(0.0f, 0.0f, offset);
        }

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        itemRenderer.renderItem(bread, ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrix, buffer);

        // Render ingredients
        if (ingredients.size() > 0) {
            for (INBT ingredient : ingredients) {
                matrix.translate(0f, 0f, 0.06f);
                itemRenderer.renderItem(ItemStack.read((CompoundNBT) ingredient), ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrix, buffer);
            }
        }

        matrix.translate(0f, 0f, 0.06f);
        itemRenderer.renderItem(bread, ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrix, buffer);

        matrix.pop();
    }
}
