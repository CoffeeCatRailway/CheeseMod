package coffeecatteam.cheesemod.objects.entity.render;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.entity.EntityCrayfish;
import coffeecatteam.cheesemod.objects.entity.man.EntityCheeseMan;
import coffeecatteam.cheesemod.objects.entity.model.ModelFoodMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrayfish extends RenderLiving<EntityCrayfish> {

    public RenderCrayfish(RenderManager manager) {
        super(manager, new ModelFoodMan(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCrayfish entity) {
        return new ResourceLocation(Reference.MODID, "textures/entity/crayfish.png");
    }
}
