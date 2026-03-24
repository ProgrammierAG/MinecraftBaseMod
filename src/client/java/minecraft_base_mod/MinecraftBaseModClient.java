package minecraft_base_mod;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class MinecraftBaseModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        KeyMapping.Category CATEGORY = new KeyMapping.Category(
                Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, "custom_category")
        );

        KeyMapping sendToChatKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.example-mod.send_to_chat",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_J,
                        CATEGORY
                ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (sendToChatKey.consumeClick()) {
                if (client.player != null) {
                    var player = client.player;

                    var abilityPoints = AbilityPoints.get(player).getAbilityPoints();

                    var message = "You have: %s Ability Points".formatted(abilityPoints);

                    player.displayClientMessage(Component.literal(message), false);
                }
            }
        });
    }
}