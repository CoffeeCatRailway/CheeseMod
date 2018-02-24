package coffeecatteam.cheesemod.world.types;

import coffeecatteam.cheesemod.init.InitBiome;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldTypeHam extends WorldType {
	
	public WorldTypeHam() {
		super("ham");
	}

	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderSingle(InitBiome.HAM);
	}
}
