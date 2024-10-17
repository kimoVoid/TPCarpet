package me.kimovoid.tpcarpet.commands.features;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import me.kimovoid.tpcarpet.utils.TPCarpetSettings;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class StopCommand {

    private static long lastTimeStopped;

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(CommandManager.literal("stop")
                .requires(c -> c.hasPermissionLevel(4))
                .executes(StopCommand::handleStop)
                .then(CommandManager.literal("confirm").executes(StopCommand::forceStop))
        );
    }

    private static int handleStop(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        if (System.currentTimeMillis() - lastTimeStopped > 30000 && TPCarpetSettings.stopCommandDoubleConfirmation){
            lastTimeStopped = System.currentTimeMillis();
            serverCommandSourceCommandContext.getSource().sendError(
                    new LiteralText("The stop confirmation is activated, " +
                            "if you wish to stop the server please rerun the stop command in the next 30 seconds")
            );
            return 1;
        }
        return forceStop(serverCommandSourceCommandContext);
    }

    private static int forceStop(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        serverCommandSourceCommandContext.getSource().getMinecraftServer().stop(false);
        return 0;
    }

}
