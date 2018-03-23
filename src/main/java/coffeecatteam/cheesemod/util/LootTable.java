package coffeecatteam.cheesemod.util;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;

import coffeecatteam.cheesemod.Reference;
import net.minecraft.util.ResourceLocation;

public class LootTable {

	private static final Set<ResourceLocation> LOOT_TABLES = Sets.<ResourceLocation>newHashSet();
	private static final Set<ResourceLocation> READ_ONLY_LOOT_TABLES = Collections.<ResourceLocation>unmodifiableSet(LOOT_TABLES);
	
	public static final ResourceLocation CHEESE_MAN = register("entities/cheese_man");
	public static final ResourceLocation HAM_MAN = register("entities/ham_man");

	public static final ResourceLocation CHEESE_GOLEM = register("entities/golem/cheese_golem");
	public static final ResourceLocation HAM_GOLEM = register("entities/golem/ham_golem");

	public static final ResourceLocation CHEESE_RUIN = register("chests/cheese_ruin");

	private static ResourceLocation register(String id) {
		return register(new ResourceLocation(Reference.MODID, id));
	}

	public static ResourceLocation register(ResourceLocation id) {
		if (LOOT_TABLES.add(id)) {
			return id;
		} else {
			throw new IllegalArgumentException(id + " is already a registered built-in loot table");
		}
	}
}
