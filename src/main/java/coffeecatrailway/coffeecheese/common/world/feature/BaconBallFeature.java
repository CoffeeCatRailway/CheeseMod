package coffeecatrailway.coffeecheese.common.world.feature;

import coffeecatrailway.coffeecheese.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 6/01/2020
 */
public class BaconBallFeature extends Feature<NoFeatureConfig> {

    private final BlockState block;

    public BaconBallFeature(boolean cooked, Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory) {
        super(configFactory);
        if (cooked)
            this.block = ModBlocks.BACON_COOKED_BLOCK.get().getDefaultState();
        else
            this.block = ModBlocks.BACON_RAW_BLOCK.get().getDefaultState();
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (rand.nextFloat() < 0.5f) {
            for (BlockState state = world.getBlockState(pos); state.isAir() && pos.getY() > 0; state = world.getBlockState(pos))
                pos = pos.down();

            int offset = rand.nextInt(2);
            int ret = 0;

            for (int height = 0; height < 128; height++) {
                for (int i = 0; i < 2; i++) {
                    int x = rand.nextInt(2);
                    int y = rand.nextInt(2);
                    int z = rand.nextInt(2);
                    float dist = (float) (height + y + z) * 0.333F + 0.5F;

                    for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-x, -y + offset - height, -z), pos.add(x, y + offset - height, z))) {
                        if (blockpos.distanceSq(pos) <= (double) (dist * dist) && this.block.isValidPosition(world, pos)) {
                            world.setBlockState(blockpos, this.block, 2);
                            ret++;
                        }
                    }

                    pos = pos.add(-rand.nextInt(2), -rand.nextInt(2), -rand.nextInt(2));
                }
            }
            return ret > 0;
        }
        return false;
    }
}
