package coffeecatrailway.coffeecheese.client.render.entity;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.client.render.entity.model.BoatModelCM;
import coffeecatrailway.coffeecheese.common.entity.item.BoatEntityCM;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 16/09/2019
 */
@OnlyIn(Dist.CLIENT)
public class BoatRendererCM extends EntityRenderer<BoatEntityCM> {

    private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[]{
            CheeseMod.getLocation("textures/entity/boat/cheese.png"),
            CheeseMod.getLocation("textures/entity/boat/grilled_cheese.png"),
            CheeseMod.getLocation("textures/entity/boat/ham_raw.png"),
            CheeseMod.getLocation("textures/entity/boat/ham_cooked.png"),
    };
    protected final BoatModelCM boatModel = new BoatModelCM();

    public BoatRendererCM(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        this.shadowSize = 0.8F;
    }

    @Override
    public void render(BoatEntityCM boat, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
        matrix.push();
        matrix.translate(0.0D, 0.375D, 0.0D);
        matrix.rotate(Vector3f.YP.rotationDegrees(180.0F - yaw));
        float f = (float) boat.getTimeSinceHit() - partialTicks;
        float f1 = boat.getDamageTaken() - partialTicks;
        if (f1 < 0.0F)
            f1 = 0.0F;

        if (f > 0.0F)
            matrix.rotate(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float) boat.getForwardDirection()));

        float f2 = boat.getRockingAngle(partialTicks);
        if (!MathHelper.epsilonEquals(f2, 0.0F))
            matrix.rotate(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), boat.getRockingAngle(partialTicks), true));

        matrix.scale(-1.0F, -1.0F, 1.0F);
        matrix.rotate(Vector3f.YP.rotationDegrees(90.0F));
        this.boatModel.setRotationAngles(boat, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        IVertexBuilder ivertexbuilder = buffer.getBuffer(this.boatModel.getRenderType(this.getEntityTexture(boat)));
        this.boatModel.render(matrix, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        IVertexBuilder ivertexbuilder1 = buffer.getBuffer(RenderType.getWaterMask());
        this.boatModel.func_228245_c_().render(matrix, ivertexbuilder1, packedLight, OverlayTexture.NO_OVERLAY);
        matrix.pop();
        super.render(boat, yaw, partialTicks, matrix, buffer, packedLight);
    }

    @Override
    public ResourceLocation getEntityTexture(BoatEntityCM entity) {
        return BOAT_TEXTURES[entity.getBoatModel().ordinal()];
    }
}
