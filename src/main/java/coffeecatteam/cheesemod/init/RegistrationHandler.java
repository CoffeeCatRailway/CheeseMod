package coffeecatteam.cheesemod.init;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.Reference;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityCheeseDraw;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityCrackerMaker;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityGrill;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityHamDraw;
import coffeecatteam.cheesemod.util.OreDictionaries;
import coffeecatteam.cheesemod.util.interfaces.IOreDict;
import coffeecatteam.cheesemod.world.gen.GenBlock;
import coffeecatteam.cheesemod.world.gen.GenOre;
import coffeecatteam.cheesemod.world.gen.GenStructure;
import coffeecatteam.cheesemod.world.gen.GenTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

public class RegistrationHandler {

    @Mod.EventBusSubscriber(modid = Reference.MODID)
    public static class Items {
        public static final Set<Item> ITEMS = new HashSet<>();

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            IForgeRegistry<Item> reg = event.getRegistry();

            for (Item item : ITEMS) {
                reg.register(item);
                if (item instanceof IOreDict) {
                    if (!item.getHasSubtypes()) {
                        String oreDict = ((IOreDict) item).registerOre();
                        OreDictionaries.registerOre(oreDict, item);
                        CheeseMod.logger.info("Item [" + item.getUnlocalizedName() + "] registered to ore dictionary name [" + oreDict + "]!");
                    }
                }
            }
        }

        @SubscribeEvent
        public static void registerModels(final ModelRegistryEvent event) {
            for (Item item : ITEMS)
                if (!item.getHasSubtypes() || item instanceof ItemSlab)
                    registerItemModel(item);
        }

        private static void registerItemModel(final Item item) {
            final String registryName = item.getRegistryName().toString();
            final ModelResourceLocation location = new ModelResourceLocation(registryName, "inventory");
            ModelLoader.setCustomModelResourceLocation(item, 0, location);
        }
    }

    @Mod.EventBusSubscriber(modid = Reference.MODID)
    public static class Blocks {
        public static final Set<Block> BLOCKS = new HashSet<>();

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> reg = event.getRegistry();

            for (final Block block : BLOCKS) {
                reg.register(block);
                if (block instanceof IOreDict) {
                    String oreDict = ((IOreDict) block).registerOre();
                    OreDictionaries.registerOre(oreDict, block);
                    CheeseMod.logger.info("Block [" + block.getUnlocalizedName() + "] registered to ore dictionary name [" + oreDict + "]!");
                }
            }
        }

        @SubscribeEvent
        public static void registerModels(final ModelRegistryEvent event) {
            for (Block block : BLOCKS)
                registerBlockModel(block);
        }

        private static void registerBlockModel(final Block block) {
            final String registryName = block.getRegistryName().toString();
            final ModelResourceLocation location = new ModelResourceLocation(registryName, "inventory");
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, location);
        }
    }

    public static void init() {
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
        CheeseMod.logger.info("Items, Blocks, Tools, Armors, Biomes, Entities & TileEntitys Initialized");

        GameRegistry.registerWorldGenerator(new GenOre(), 0);
        GameRegistry.registerWorldGenerator(new GenStructure(), 0);
        GameRegistry.registerWorldGenerator(new GenTree(), 0);
        GameRegistry.registerWorldGenerator(new GenBlock(), 0);
        CheeseMod.logger.info("World Generation Initialized");
    }
}
