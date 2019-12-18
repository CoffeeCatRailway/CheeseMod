package coffeecatrailway.coffeecheese.client.render.entity;

import coffeecatrailway.coffeecheese.CheeseMod;
import coffeecatrailway.coffeecheese.client.render.entity.model.BoatModelCM;
import coffeecatrailway.coffeecheese.common.entity.item.BoatEntityCM;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
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
    protected final BoatModelCM field_76998_a = new BoatModelCM();

    public BoatRendererCM(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        this.shadowSize = 0.8F;
    }

    @Override
    public void doRender(BoatEntityCM entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entity, entityYaw, partialTicks);
        this.bindEntityTexture(entity);
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
        }

        this.field_76998_a.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        if (this.renderOutlines) {
            GlStateManager.tearDownSolidRenderingTextureCombine();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void setupRotation(BoatEntityCM entityIn, float entityYaw, float partialTicks) {
        GlStateManager.rotatef(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        float f = (float) entityIn.getTimeSinceHit() - partialTicks;
        float f1 = entityIn.getDamageTaken() - partialTicks;
        if (f1 < 0.0F)
            f1 = 0.0F;

        if (f > 0.0F)
            GlStateManager.rotatef(MathHelper.sin(f) * f * f1 / 10.0F * (float) entityIn.getForwardDirection(), 1.0F, 0.0F, 0.0F);

        float f2 = entityIn.getRockingAngle(partialTicks);
        if (!MathHelper.epsilonEquals(f2, 0.0F))
            GlStateManager.rotatef(entityIn.getRockingAngle(partialTicks), 1.0F, 0.0F, 1.0F);

        GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double x, double y, double z) {
        GlStateManager.translatef((float) x, (float) y + 0.375F, (float) z);
    }

    @Override
    protected ResourceLocation getEntityTexture(BoatEntityCM entity) {
        return BOAT_TEXTURES[entity.getBoatModel().ordinal()];
    }

    @Override
    public boolean isMultipass() {
        return true;
    }

    @Override
    public void renderMultipass(BoatEntityCM entityIn, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entityIn, entityYaw, partialTicks);
        this.bindEntityTexture(entityIn);
        this.field_76998_a.renderMultipass(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }
}
