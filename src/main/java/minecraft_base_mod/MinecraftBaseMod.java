package minecraft_base_mod;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import minecraft_base_mod.callbacks.SheepShearCallback;
import minecraft_base_mod.commands.AbilityPointsCommands;
import minecraft_base_mod.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinecraftBaseMod implements ModInitializer {
    public static final String MOD_ID = "minecraft-base-mod";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world! from: " + MOD_ID);

        initializeCallbacks();

        AbilityPoints.initialize();

        ModItems.initialize();

        PlayerBlockBreakEvents.AFTER.register((level, player, pos, state, blockEntity) -> {
            AbilityPoints.get(player).modifyAbilityPoints(1);
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            var root = Commands.literal("points");

            root.then(Commands.literal("show")
                    .executes(AbilityPointsCommands::showAbilityPoints));

            root.then(Commands.literal("add")
                    .then(Commands.argument("value", IntegerArgumentType.integer())
                            .executes(AbilityPointsCommands::addAbilityPoints)));

            root.then(Commands.literal("give")
                    .then(Commands.argument("player", EntityArgument.player())
                            .then(Commands.argument("value", IntegerArgumentType.integer())
                                    .executes(AbilityPointsCommands::giveAbilityPoints))));

            dispatcher.register(root);
        });
    }

    public void initializeCallbacks(){
        SheepShearCallback.initialize();
    }
}
