package minecraft_base_mod;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class AbilityPoints {
    public static final String ABILITY_POINTS_NAME = "ability_points";
    public static final int MIN_ABILITY_POINTS = 0;

    public static final AttachmentType<Integer> ABILITY_POINTS = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, ABILITY_POINTS_NAME),
            builder -> builder
                    .initializer(() -> MIN_ABILITY_POINTS)
                    .persistent(Codec.INT)
                    .syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all())
                    .copyOnDeath()
    );

    public static void initialize() {
    }


    public static AbilityPointsData get(AttachmentTarget target) {
        return new AbilityPointsData(target);
    }

    public record AbilityPointsData(AttachmentTarget target) {
        public int getAbilityPoints() {
            return target.getAttachedOrCreate(ABILITY_POINTS);
        }

        public int modifyAbilityPoints(int value) {
            return target.setAttached(ABILITY_POINTS, Math.max(target.getAttachedOrCreate(ABILITY_POINTS) + value, MIN_ABILITY_POINTS));
        }
    }
}
