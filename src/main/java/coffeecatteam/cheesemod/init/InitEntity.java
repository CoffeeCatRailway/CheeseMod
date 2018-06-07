package coffeecatteam.cheesemod.init;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.entity.EntityCrayfish;
import coffeecatteam.cheesemod.objects.entity.golem.EntityCheeseGolem;
import coffeecatteam.cheesemod.objects.entity.golem.EntityHamGolem;
import coffeecatteam.cheesemod.objects.entity.man.EntityCheeseMan;
import coffeecatteam.cheesemod.objects.entity.man.EntityHamMan;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class InitEntity {

	public static void init() {
		register("cheese_man", EntityCheeseMan.class, Reference.ENTITY_CHEESE_MAN, 35, 0xC39E00, 0xFF9E00);
		register("ham_man", EntityHamMan.class, Reference.ENTITY_HAM_MAN, 35, 0xD46A52, 0x8E4737);

		register("cheese_golem", EntityCheeseGolem.class, Reference.ENTITY_CHEESE_GOLEM, 50, 0xD6D08A, 0x957E30);
		register("ham_golem", EntityHamGolem.class, Reference.ENTITY_HAM_GOLEM, 50, 0xB66859, 0xB54925);

		register("crayfish", EntityCrayfish.class, Reference.ENTITY_CRAYFISH, 50);
	}

	private static void register(String name, Class<? extends Entity> entity, int id, int trackingRange) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id,
				CheeseMod.instance, trackingRange, 1, true);
	}

	private static void register(String name, Class<? extends Entity> entity, int id, int trackingRange, int eggPrimary,
			int eggSecondary) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id,
				CheeseMod.instance, trackingRange, 1, true, eggPrimary, eggSecondary);
	}

	private static void spawn(Class<? extends EntityLiving> entity, int weightedProb, int min, int max,
			EnumCreatureType typeOfCreature, Biome... biomes) {
		EntityRegistry.addSpawn(entity, weightedProb, min, max, typeOfCreature, biomes);
	}
}
