package minecraft_base_mod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

import java.util.concurrent.ThreadLocalRandom;


public class Utils {
    public static boolean teleportToNearestFreeSpot(Entity target, int radius, int tries) {
        var level = target.level();

        outer:
        for (int i = 0; i < tries; i++) {
            var targetPos = target.position();

            var blockPos = new BlockPos(
                    (int) (targetPos.x + ThreadLocalRandom.current().nextInt(0, 10)),
                    (int) (targetPos.y + ThreadLocalRandom.current().nextInt(20, 50)),
                    (int) (targetPos.z + ThreadLocalRandom.current().nextInt(0, 10))
            );

            System.out.println(blockPos.getX());
            System.out.println(blockPos.getY());
            System.out.println(blockPos.getZ());

            var belowBlockPos = blockPos.below();

            BlockPos[] sides = {
                    blockPos,
                    blockPos.above(),
                    blockPos.north(),
                    blockPos.east(),
                    blockPos.south(),
                    blockPos.west(),
                    blockPos.below(),
            };

            for (var curr : sides){
                if (!level.getBlockState(curr).isAir()) {
                    continue outer;
                }
            }

            target.teleportTo(belowBlockPos.getX(), belowBlockPos.getY(), belowBlockPos.getZ());
            return true;
        }

        return false;
    }

    public static void initialize(){}
}
