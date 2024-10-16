package me.kimovoid.tpcarpet.commands.features;

import com.mojang.brigadier.CommandDispatcher;
import me.kimovoid.tpcarpet.mixin.access.ScoreboardCommandInvoker;
import net.minecraft.command.arguments.ObjectiveArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class SetSbCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("setsb")
                        .then(
                                CommandManager.argument("objective", ObjectiveArgumentType.objective())
                                        .executes(
                                                context -> ScoreboardCommandInvoker.executeSetDisplay(
                                                        context.getSource(),
                                                        1,
                                                        ObjectiveArgumentType.getObjective(context, "objective")
                                                )
                                        )
                        )
        );
    }
}