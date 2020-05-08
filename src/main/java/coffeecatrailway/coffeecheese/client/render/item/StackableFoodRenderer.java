package coffeecatrailway.coffeecheese.client.render.item;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.ModCheeseConfig;
import coffeecatrailway.coffeecheese.common.item.StackableFoodItem;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;

/**
 * @author CoffeeCatRailway - Xero: https://github.com/Squishling
 * Created: 28/04/2020
 */
@OnlyIn(Dist.CLIENT)
public class StackableFoodRenderer extends ItemStackTileEntityRenderer {

    private static final float[] ANGLES = new float[]{-5.0f, 0.0f, 25.0f, -75.0f, 0.0f, 90.0f, -90.0f, 75.0f, 5.0f};

    private float getRotateAngle(ListNBT ingredients, int ingredient) {
        return ANGLES[(int) CheeseMod.map(ingredient, 0, ingredients.size(), 0, Math.min(ANGLES.length, ModCheeseConfig.stackableFoodIngredientCount.get()))];
    }

    @Override
    public void render(ItemStack stack, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        super.render(stack, matrix, buffer, combinedLight, combinedOverlay);
        if (stack.getItem() instanceof StackableFoodItem) {
            StackableFoodItem item = (StackableFoodItem) stack.getItem();
            matrix.push();

            // Rotate & position
            matrix.translate(0.5f, 0.5f, 0.5f);
            matrix.rotate(Vector3f.YN.rotationDegrees(90));

            // NBT setup
            CompoundNBT nbt = stack.getOrCreateTag();
            ListNBT ingredients = nbt.getList(StackableFoodItem.TAG_INGREDIENTS, Constants.NBT.TAG_COMPOUND);
            ItemStack bread = new ItemStack(nbt.getBoolean(StackableFoodItem.TAG_TOASTED) ? item.foodProperties.getToastedSide().get() : item.foodProperties.getBaseSide().get());

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
                for (int i = 0; i < ingredients.size(); i++) {
                    matrix.translate(0f, 0f, 0.06f);
                    float angle = getRotateAngle(ingredients, i);
                    matrix.rotate(Vector3f.ZN.rotationDegrees(angle));
                    itemRenderer.renderItem(ItemStack.read((CompoundNBT) ingredients.get(i)), ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrix, buffer);
                    matrix.rotate(Vector3f.ZP.rotationDegrees(angle));
                }
            }

            if (item.foodProperties.hasTwoSides()) {
                matrix.translate(0f, 0f, 0.06f);
                itemRenderer.renderItem(bread, ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrix, buffer);
            }

            matrix.pop();
        }
    }
}
