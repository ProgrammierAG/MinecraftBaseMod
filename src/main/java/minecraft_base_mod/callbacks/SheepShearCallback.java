package minecraft_base_mod.callbacks;

import minecraft_base_mod.mixed.SheepMixed;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;

public interface SheepShearCallback {
    Event<SheepShearCallback> EVENT = EventFactory.createArrayBacked(
            SheepShearCallback.class,
            (listeners) -> (player, sheep) -> {
                for (SheepShearCallback listener : listeners) {
                    InteractionResult result = listener.interact(player, sheep);

                    if (result != InteractionResult.PASS) {
                        return result;
                    }
                }
                return InteractionResult.PASS;
            }
    );

    InteractionResult interact(Player player, Sheep sheep);

    public static void initialize() {
        SheepShearCallback.EVENT.register((player, sheep) -> {
            return SheepMixed.onSheared(player, sheep);
        });
    }
}
