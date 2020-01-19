package coffeecatrailway.coffeecheese.common.world.dimension;

import coffeecatrailway.coffeecheese.registry.ModBlocks;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ColumnPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway - Bagu_Chan https://github.com/pentantan
 * Created: 16/01/2020
 */
public class FoodWorldTeleporter extends Teleporter {

    private final Object2LongMap<ColumnPos> field_222275_f = new Object2LongOpenHashMap<>();
    protected final Map<ColumnPos, PortalPosition> destinationCoordinateCache = Maps.newHashMapWithExpectedSize(4096);

    public FoodWorldTeleporter(ServerWorld world) {
        super(world);
    }

    public boolean func_222268_a(Entity entity, float rotationYaw) {
        ColumnPos columnpos = new ColumnPos(entity.getPosition());

        double distance = -1.0D;
        boolean doesPortalExist = true;
        BlockPos location = BlockPos.ZERO;

        if (this.destinationCoordinateCache.containsKey(columnpos)) {
            final PortalPosition portalPosition = this.destinationCoordinateCache.get(columnpos);
            distance = 0.0D;
            location = portalPosition.pos;
            portalPosition.lastUpdateTime = this.world.getGameTime();
            doesPortalExist = false;
        } else {
            final BlockPos entityPos = new BlockPos(entity);
            for (int offsetX = -128; offsetX <= 128; ++offsetX) {
                BlockPos positionCache;
                for (int offsetZ = -128; offsetZ <= 128; ++offsetZ) {
                    for (BlockPos currentPos = entityPos.add(offsetX, this.world.getActualHeight() - 1 - entityPos.getY(), offsetZ); currentPos.getY() >= 0; currentPos = positionCache) {
                        positionCache = currentPos.down();
                        if (this.world.getBlockState(currentPos).getBlock() == ModBlocks.FOOD_PORTAL.get()) {
                            while (this.world.getBlockState(positionCache = currentPos.down()).getBlock() == ModBlocks.FOOD_PORTAL.get())
                                currentPos = positionCache;

                            final double distanceToEntity = currentPos.distanceSq(entityPos);
                            if (distance < 0.0D || distanceToEntity < distance) {
                                distance = distanceToEntity;
                                location = currentPos;
                            }
                        }
                    }
                }
            }
        }

        if (distance >= 0.0D) {
            if (doesPortalExist)
                this.destinationCoordinateCache.put(columnpos, new PortalPosition(location, this.world.getWorld().getGameTime()));

            double tpX = location.getX() + 0.5D;
            double tpY = location.getY() + 0.5D;
            double tpZ = location.getZ() + 0.5D;
            Direction direction = null;

            if (this.world.getBlockState(location.west()).getBlock() == ModBlocks.FOOD_PORTAL.get())
                direction = Direction.NORTH;

            if (this.world.getBlockState(location.east()).getBlock() == ModBlocks.FOOD_PORTAL.get())
                direction = Direction.SOUTH;

            if (this.world.getBlockState(location.north()).getBlock() == ModBlocks.FOOD_PORTAL.get())
                direction = Direction.EAST;

            if (this.world.getBlockState(location.south()).getBlock() == ModBlocks.FOOD_PORTAL.get())
                direction = Direction.WEST;

            final Direction enumfacing1 = Direction.byHorizontalIndex(MathHelper.floor(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3);

            if (direction != null) {
                Direction enumfacing2 = direction.rotateYCCW();
                final BlockPos blockpos2 = location.offset(direction);
                boolean flag2 = this.isInsideBlock(blockpos2);
                boolean flag3 = this.isInsideBlock(blockpos2.offset(enumfacing2));

                if (flag3 && flag2) {
                    location = location.offset(enumfacing2);
                    direction = direction.getOpposite();
                    enumfacing2 = enumfacing2.getOpposite();
                    final BlockPos blockpos3 = location.offset(direction);
                    flag2 = this.isInsideBlock(blockpos3);
                    flag3 = this.isInsideBlock(blockpos3.offset(enumfacing2));
                }

                float f6 = 0.5F;
                float f1 = 0.5F;

                if (!flag3 && flag2)
                    f6 = 1.0F;
                else if (flag3 && !flag2)
                    f6 = 0.0F;
                else if (flag3)
                    f1 = 0.0F;

                tpX = location.getX() + 0.5D;
                tpY = location.getY() + 0.5D;
                tpZ = location.getZ() + 0.5D;
                tpX += enumfacing2.getXOffset() * f6 + direction.getXOffset() * f1;
                tpZ += enumfacing2.getYOffset() * f6 + direction.getYOffset() * f1;
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;

                if (direction == enumfacing1) {
                    f2 = 1.0F;
                    f3 = 1.0F;
                } else if (direction == enumfacing1.getOpposite()) {
                    f2 = -1.0F;
                    f3 = -1.0F;
                } else if (direction == enumfacing1.rotateY()) {
                    f4 = 1.0F;
                    f5 = -1.0F;
                } else {
                    f4 = -1.0F;
                    f5 = 1.0F;
                }

                final double d2 = entity.getMotion().getX();
                final double d3 = entity.getMotion().getZ();
                entity.setMotion(d2 * f2 + d3 * f5, 0.0F, d2 * f4 + d3 * f3);
                entity.rotationYaw = rotationYaw - enumfacing1.getHorizontalIndex() * 90 + direction.getHorizontalIndex() * 90;
            } else
                entity.setMotion(0.0F, 0.0F, 0.0F);

            if (entity instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) entity).connection.setPlayerLocation(tpX, tpY, tpZ, entity.rotationYaw, entity.rotationPitch);
                ((ServerPlayerEntity) entity).connection.captureCurrentPosition();
            } else
                entity.setLocationAndAngles(tpX, tpY, tpZ, entity.rotationYaw, entity.rotationPitch);
            return true;
        } else
            return false;
    }

    private boolean isInsideBlock(BlockPos position) {
        return !this.world.isAirBlock(position) || !this.world.isAirBlock(position.up());
    }

    @Override
    public boolean makePortal(Entity entity) {
        return createPortal(this.world, new BlockPos(MathHelper.floor(entity.posX), MathHelper.floor(entity.posY), MathHelper.floor(entity.posZ)), entity);
    }

    public static boolean createPortal(World world, BlockPos pos, @Nullable Entity entity) {
        BlockState portalState = ModBlocks.FOOD_PORTAL.get().getDefaultState();
        BlockState snowstate = ModBlocks.CHEESE_BLOCK.get().getDefaultState();

        while (pos.getY() > 1 && world.isAirBlock(pos))
            pos = pos.down();

        while (!world.isAirBlock(pos.up()) && world.getBlockState(pos).getBlock() != Blocks.GRASS)
            pos = pos.up();

        //Bottom layers
        for (BlockPos basePos : BlockPos.MutableBlockPos.getAllInBoxMutable(pos.add(-2, 0, -2), pos.add(2, 1, 2)))
            world.setBlockState(basePos, snowstate, 2);

        //air
        for (BlockPos airPos : BlockPos.MutableBlockPos.getAllInBoxMutable(pos.add(-2, 2, -1), pos.add(2, 3, 1)))
            world.setBlockState(airPos, Blocks.AIR.getDefaultState(), 2);

        //Portal blocks
        for (BlockPos portalPos : BlockPos.MutableBlockPos.getAllInBoxMutable(pos.add(-1, 1, -1), pos.add(1, 1, 1)))
            world.setBlockState(portalPos, portalState, 2);

        return true;
    }

    public void tick(long worldTime) {
        if (worldTime % 100L == 0L) {
            this.func_222270_b(worldTime);
            this.func_222269_c(worldTime);
        }
    }

    private void func_222270_b(long worldTime) {
        LongIterator longiterator = this.field_222275_f.values().iterator();

        while (longiterator.hasNext()) {
            long i = longiterator.nextLong();
            if (i <= worldTime)
                longiterator.remove();
        }
    }

    private void func_222269_c(long worldTime) {
        long i = worldTime - 300L;
        Iterator<Map.Entry<ColumnPos, PortalPosition>> iterator = this.destinationCoordinateCache.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<ColumnPos, PortalPosition> entry = iterator.next();
            FoodWorldTeleporter.PortalPosition teleporter$portalposition = entry.getValue();
            if (teleporter$portalposition.lastUpdateTime < i) {
                ColumnPos columnpos = entry.getKey();
                Supplier[] suppliers = new Supplier[2];
                Dimension dimension = this.world.getDimension();
                suppliers[0] = dimension::getType;
                suppliers[1] = () -> columnpos;
                this.world.getChunkProvider().func_217222_b(TicketType.PORTAL, new ChunkPos(teleporter$portalposition.pos), 3, columnpos);
                iterator.remove();
            }
        }
    }

    static class PortalPosition {
        public final BlockPos pos;
        public long lastUpdateTime;

        public PortalPosition(BlockPos pos, long lastUpdateTime) {
            this.pos = pos;
            this.lastUpdateTime = lastUpdateTime;
        }
    }
}
