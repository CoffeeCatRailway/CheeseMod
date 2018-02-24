package coffeecatteam.cheesemod.init;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.world.biomes.BiomeCheese;
import coffeecatteam.cheesemod.world.biomes.BiomeHam;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class InitBiome {

	public static final Biome CHEESE = new BiomeCheese();
	public static final Biome HAM = new BiomeHam();

	public static void registerBiomes() {
		Type[] types = { Type.PLAINS, Type.RIVER, Type.FOREST, Type.END, Type.NETHER, Type.VOID };
		registerBiome(CHEESE, "Cheese", BiomeType.WARM, 5, types);
		registerBiome(HAM, "Ham", BiomeType.WARM, 5, types);
	}

	private static Biome registerBiome(Biome biome, String name, BiomeType biomeType, int weight, Type... types) {
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		CheeseMod.logger.info("Biome [" + name + "] registered!");
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
		BiomeManager.addSpawnBiome(biome);
		CheeseMod.logger.info("Biome [" + name + "] added!");
		return biome;
	}
}
