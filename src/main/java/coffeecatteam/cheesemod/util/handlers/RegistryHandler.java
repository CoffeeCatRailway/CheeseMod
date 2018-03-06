package coffeecatteam.cheesemod.util.handlers;

import org.apache.logging.log4j.Logger;

import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.init.InitArmor;
import coffeecatteam.cheesemod.init.InitBiome;
import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.init.InitEntity;
import coffeecatteam.cheesemod.init.InitItem;
import coffeecatteam.cheesemod.init.InitTool;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityCheeseDraw;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityCrackerMaker;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityGrill;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityHamDraw;
import coffeecatteam.cheesemod.util.Utils;
import coffeecatteam.cheesemod.world.gen.GenOre;
import coffeecatteam.cheesemod.world.gen.GenStructure;
import coffeecatteam.cheesemod.world.gen.GenTree;
import coffeecatteam.cheesemod.world.types.WorldTypeCheese;
import coffeecatteam.cheesemod.world.types.WorldTypeHam;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryHandler {

	public static Logger logger = Utils.getLogger();

	public static void Client() {
	}

	public static void Common() {
		InitBlock.init();
		InitItem.init();
		new InitTool();
		new InitArmor();

		InitEntity.init();
		InitBiome.registerBiomes();

		GameRegistry.registerTileEntity(TileEntityCheeseDraw.class, Reference.MODID+":cheese_draw");
		GameRegistry.registerTileEntity(TileEntityHamDraw.class, Reference.MODID+":ham_draw");
		GameRegistry.registerTileEntity(TileEntityGrill.class, Reference.MODID+":grill");
		GameRegistry.registerTileEntity(TileEntityCrackerMaker.class, Reference.MODID+":cracker_maker");
		logger.info("Items, Blocks, Tools, Armors, Biomes, Entities & TileEntitys Initialized");

		GameRegistry.registerWorldGenerator(new GenOre(), 0);
		GameRegistry.registerWorldGenerator(new GenStructure(), 0);
		GameRegistry.registerWorldGenerator(new GenTree(), 0);
		logger.info("World Generation Initialized");
	}

	public static void postInitRegistries() {
		WorldType CHEESE = new WorldTypeCheese();
		WorldType HAM = new WorldTypeHam();
	}
}
