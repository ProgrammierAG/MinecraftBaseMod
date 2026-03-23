package minecraft_base_mod.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import minecraft_base_mod.AbilityPoints;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;

public class AbilityPointsCommands {
    public static int showAbilityPoints(CommandContext<CommandSourceStack> context) {
        var player = context.getSource().getPlayer();
        if (player == null) return -1;

        var abilityPoints = AbilityPoints.get(player).getAbilityPoints();

        var message = "You have: %s Ability Points".formatted(abilityPoints);
        ;

        player.sendSystemMessage(Component.literal(message));

        return 1;
    }

    public static int addAbilityPoints(CommandContext<CommandSourceStack> context) {
        var player = context.getSource().getPlayer();
        if (player == null) return -1;

        var value = IntegerArgumentType.getInteger(context, "value");

        AbilityPoints.get(player).modifyAbilityPoints(value);

        var message = "Ability points were modified";

        player.sendSystemMessage(Component.literal(message));

        return 1;
    }

    public static int giveAbilityPoints(CommandContext<CommandSourceStack> context) {
        try {
            var player = context.getSource().getPlayer();

            var value = IntegerArgumentType.getInteger(context, "value");

            var otherPlayer = EntityArgument.getPlayer(context, "player");

            AbilityPoints.get(player).modifyAbilityPoints(-value);
            AbilityPoints.get(otherPlayer).modifyAbilityPoints(value);

            var message = "Ability points were given";

            player.sendSystemMessage(Component.literal(message));

            return 1;
        } catch (Exception e) {
            return -1;
        }
    }
}
