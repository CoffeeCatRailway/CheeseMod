package coffeecatrailway.coffeecheese.common.block;

import coffeecatrailway.coffeecheese.common.ModTags;
import coffeecatrailway.coffeecheese.common.tileentity.FoodWorldPortalTileEntity;
import coffeecatrailway.coffeecheese.common.world.dimension.FoodWorldTeleporter;
import coffeecatrailway.coffeecheese.registry.ModBlocks;
import coffeecatrailway.coffeecheese.registry.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway - Bagu_Chan https://github.com/pentantan & Andromander https://github.com/Andromander
 * Created: 16/01/2020
 */
public class FoodWorldPortalBlock extends ContainerBlock {

    private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    private static final VoxelShape NULL = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

    public FoodWorldPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new FoodWorldPortalTileEntity();
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    public boolean trySpawnPortal(World world, BlockPos pos) {
        FoodWorldPortalBlock.Size size = this.isPortal(world, pos);
        if (size != null && this.canCreatePortalByWorld(world)) {
            size.placePortalBlocks();
            return true;
        } else {
            return false;
        }
    }

    private boolean canCreatePortalByWorld(World world) {
        return (world.dimension.getType() == DimensionType.OVERWORLD || world.dimension.getType() == ModDimensions.FOOD_WORLD_TYPE);
    }

    @Nullable
    public FoodWorldPortalBlock.Size isPortal(IWorld world, BlockPos pos) {
        FoodWorldPortalBlock.Size size = new FoodWorldPortalBlock.Size(world, pos);
        if (size.isValid()) {
            return size;
        } else {
            FoodWorldPortalBlock.Size size1 = new FoodWorldPortalBlock.Size(world, pos);
            return size1.isValid() ? size1 : null;
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
        return facingState.getBlock() != this && !(new FoodWorldPortalBlock.Size(world, pos)).isValid() ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, pos, facingPos);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (!worldIn.isRemote && !entityIn.isPassenger() && !entityIn.isBeingRidden() && entityIn.isNonBoss()) {
            MinecraftServer server = worldIn.getServer();

            if (entityIn.dimension.getModType() == ModDimensions.FOOD_WORLD.get()) {
                if (server != null && entityIn.timeUntilPortal <= 0) {
                    DimensionType warptype = DimensionType.OVERWORLD;
                    entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    if (entityIn.timeUntilPortal > 0) {
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    }

                    if (entityIn instanceof ServerPlayerEntity) {
                        this.changePlayerDimension(warptype, (ServerPlayerEntity) entityIn);
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    } else {
                        this.changeDimension(warptype, entityIn);
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    }
                } else {
                    entityIn.timeUntilPortal = Math.max(entityIn.getPortalCooldown(), 100);
                }
            } else {
                if (server != null && entityIn.timeUntilPortal <= 0) {
                    DimensionType warptype = ModDimensions.FOOD_WORLD_TYPE;
                    entityIn.timeUntilPortal = entityIn.getPortalCooldown();

                    if (entityIn.timeUntilPortal > 0) {
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    }

                    if (entityIn instanceof ServerPlayerEntity) {
                        this.changePlayerDimension(warptype, (ServerPlayerEntity) entityIn);
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    } else {
                        this.changeDimension(warptype, entityIn);
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    }
                } else {
                    entityIn.timeUntilPortal = Math.max(entityIn.getPortalCooldown(), 100);
                }
            }
        }
    }

    public Entity changePlayerDimension(DimensionType destination, ServerPlayerEntity serverPlayerEntity) {
        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(serverPlayerEntity, destination)) return null;
        serverPlayerEntity.invulnerableDimensionChange = true;
        DimensionType dimensiontype = serverPlayerEntity.dimension;

        ServerWorld serverworld = serverPlayerEntity.server.getWorld(dimensiontype);
        serverPlayerEntity.dimension = destination;
        ServerWorld serverworld1 = serverPlayerEntity.server.getWorld(destination);

        FoodWorldTeleporter teleporter = new FoodWorldTeleporter(serverworld1);

        WorldInfo worldinfo = serverPlayerEntity.world.getWorldInfo();
        serverPlayerEntity.connection.sendPacket(new SRespawnPacket(destination, WorldInfo.byHashing(worldinfo.getSeed()), worldinfo.getGenerator(), serverPlayerEntity.interactionManager.getGameType()));
        serverPlayerEntity.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
        PlayerList playerlist = serverPlayerEntity.server.getPlayerList();
        playerlist.updatePermissionLevel(serverPlayerEntity);
        serverworld.removeEntity(serverPlayerEntity, true); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no matching invalidate call.
        serverPlayerEntity.revive();
        double d0 = serverPlayerEntity.getPosX();
        double d1 = serverPlayerEntity.getPosY();
        double d2 = serverPlayerEntity.getPosZ();
        float f = serverPlayerEntity.rotationPitch;
        float f1 = serverPlayerEntity.rotationYaw;
        float f2 = f1;
        serverworld.getProfiler().startSection("moving");
        double moveFactor = serverworld.getDimension().getMovementFactor() / serverworld1.getDimension().getMovementFactor();
        d0 *= moveFactor;
        d2 *= moveFactor;

        serverPlayerEntity.setLocationAndAngles(d0, d1, d2, f1, f);
        serverworld.getProfiler().endSection();
        serverworld.getProfiler().startSection("placing");
        double d7 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minX() + 16.0D);
        double d4 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minZ() + 16.0D);
        double d5 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxX() - 16.0D);
        double d6 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxZ() - 16.0D);
        d0 = MathHelper.clamp(d0, d7, d5);
        d2 = MathHelper.clamp(d2, d4, d6);
        serverPlayerEntity.setLocationAndAngles(d0, d1, d2, f1, f);
        if (!teleporter.placeInPortal(serverPlayerEntity, f2)) {
            teleporter.makePortal(serverPlayerEntity);
            teleporter.placeInPortal(serverPlayerEntity, f2);
        }


        serverworld.getProfiler().endSection();
        serverPlayerEntity.setWorld(serverworld1);
        serverworld1.addNewPlayer(serverPlayerEntity);
        serverPlayerEntity.connection.setPlayerLocation(serverPlayerEntity.getPosX(), serverPlayerEntity.getPosY(), serverPlayerEntity.getPosZ(), f1, f);
        serverPlayerEntity.interactionManager.setWorld(serverworld1);
        serverPlayerEntity.connection.sendPacket(new SPlayerAbilitiesPacket(serverPlayerEntity.abilities));
        playerlist.sendWorldInfo(serverPlayerEntity, serverworld1);
        playerlist.sendInventory(serverPlayerEntity);

        for (EffectInstance effectinstance : serverPlayerEntity.getActivePotionEffects()) {
            serverPlayerEntity.connection.sendPacket(new SPlayEntityEffectPacket(serverPlayerEntity.getEntityId(), effectinstance));
        }

        serverPlayerEntity.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
        serverPlayerEntity.lastExperience = -1;
        serverPlayerEntity.lastHealth = -1.0F;
        serverPlayerEntity.lastFoodLevel = -1;
        net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerChangedDimensionEvent(serverPlayerEntity, dimensiontype, destination);
        return serverPlayerEntity;

    }

    @Nullable
    public Entity changeDimension(DimensionType destination, Entity entity) {
        if (!entity.world.isRemote && !entity.removed) {
            entity.world.getProfiler().startSection("changeDimension");
            MinecraftServer minecraftserver = entity.getServer();
            DimensionType dimensiontype = entity.dimension;
            ServerWorld serverworld = minecraftserver.getWorld(dimensiontype);
            ServerWorld serverworld1 = minecraftserver.getWorld(destination);

            FoodWorldTeleporter teleporter = new FoodWorldTeleporter(serverworld1);
            entity.dimension = destination;
            entity.detach();
            entity.world.getProfiler().startSection("reposition");
            Vec3d vec3d = entity.getMotion();

            entity.world.getProfiler().endStartSection("reloading");
            Entity entity2 = entity.getType().create(serverworld1);
            if (entity2 != null) {
                entity2.copyDataFromOld(entity);
                teleporter.placeInPortal(entity2, entity2.rotationYaw);
                entity2.setMotion(vec3d);
                serverworld1.addFromAnotherDimension(entity2);
                entity.remove();
            }

            entity2.world.getProfiler().endSection();
            serverworld.resetUpdateEntityTick();
            serverworld1.resetUpdateEntityTick();
            entity2.world.getProfiler().endSection();
            return entity2;
        } else
            return null;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return NULL;
    }

    public static class Size {

        private final IWorld world;
        private boolean valid = false;
        private BlockPos nw;
        private BlockPos se;

        public Size(IWorld world, BlockPos pos) {
            this.world = world;

            int east = getDistanceUntilEdge(pos, Direction.EAST);
            int west = getDistanceUntilEdge(pos, Direction.WEST);
            int north = getDistanceUntilEdge(pos, Direction.NORTH);
            int south = getDistanceUntilEdge(pos, Direction.SOUTH);

            int width = east + west - 1;
            int length = north + south - 1;

            if (width > 2 || length > 2)
                return;
            if (width < 2 || length < 2)
                return;

            BlockPos nwCorner = pos.west(west).north(north);
            BlockPos seCorner = pos.east(east).south(south);

            this.nw = nwCorner.add(1, 0, 1);
            this.se = seCorner.add(-1, 0, -1);
            int wallWidth = width + 2;
            int wallLength = length + 2;

            for (int x = 0; x < wallWidth; x++)
                for (int z = 0; z < wallLength; z++)
                    if (x == 0 || z == 0 || x == wallWidth - 1 || z == wallLength - 1)
                        if (!isFrameBlock(world.getBlockState(nwCorner.down().add(x, 1, z))))
                            return;

            this.valid = true;
        }

        private int getDistanceUntilEdge(BlockPos pos, Direction facing) {
            int i;

            for (i = 0; i < 9; ++i) {
                BlockPos blockpos = pos.offset(facing, i);

                if (!this.isEmptyBlock(this.world.getBlockState(blockpos)) || !isFrameBlock(this.world.getBlockState(blockpos.down()))) {
                    break;
                }
            }

            BlockState state = this.world.getBlockState(pos.offset(facing, i));
            return isFrameBlock(state) ? i : 0;
        }

        private boolean isEmptyBlock(BlockState state) {
            return state.isAir();
        }

        private boolean isFrameBlock(BlockState state) {
            return ModTags.Blocks.FOOD_BLOCKS.contains(state.getBlock());
        }

        private boolean isValid() {
            return this.valid;
        }

        private void placePortalBlocks() {
            for (BlockPos portalPos : BlockPos.Mutable.getAllInBoxMutable(nw, se))
                this.world.setBlockState(portalPos, ModBlocks.FOOD_PORTAL.get().getDefaultState(), 2);
        }
    }
}
