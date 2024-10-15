package me.kimovoid.tpextra.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

public class PingCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("ping")
                        .executes(context -> {
                            ServerPlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                int ping = player.pingMilliseconds;
                                player.sendMessage(new LiteralText("Your ping is: " + ping + " ms"));
                            }
                            return Command.SINGLE_SUCCESS;
                        })
        );
    }
}
