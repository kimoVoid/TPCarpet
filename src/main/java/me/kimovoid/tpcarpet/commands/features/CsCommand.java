package me.kimovoid.tpcarpet.commands.features;

import com.mojang.brigadier.CommandDispatcher;
import me.kimovoid.tpcarpet.utils.Location;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameMode;

import java.util.HashMap;
import java.util.Map;

public class CsCommand {

    private static final Map<ServerPlayerEntity, Location> savedPositions = new HashMap<>();


    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("cs")
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();
                    if (player != null) {
                        if (player.interactionManager.getGameMode() == GameMode.SURVIVAL) {
                            switchToSpectator(player);
                        } else if (player.interactionManager.getGameMode() == GameMode.SPECTATOR) {
                            switchToSurvival(player);
                        }
                    }
                    return 1;
                })
        );
    }

    private static void switchToSpectator(ServerPlayerEntity player) {
        savedPositions.put(player, new Location(player.getServerWorld(), player.getPos(), player.yaw, player.pitch));

        player.setGameMode(GameMode.SPECTATOR);
        player.sendChatMessage(
                new LiteralText("Switched to Spectator Mode!")
                        .setStyle(new Style().setColor(Formatting.AQUA)),
                MessageType.GAME_INFO
        );
    }

    private static void switchToSurvival(ServerPlayerEntity player) {

        Location tpLoc = savedPositions.get(player);
        savedPositions.remove(player);
        if (tpLoc == null){
            tpLoc = new Location(player.getServerWorld(), player.getPos(), player.yaw, player.pitch);
        }
        player.teleport(tpLoc.getServerWorld(), tpLoc.getX(), tpLoc.getY(), tpLoc.getZ(), tpLoc.getYaw(), tpLoc.getPitch());
        player.setGameMode(GameMode.SURVIVAL);

        player.sendChatMessage(
                new LiteralText("Switched back to Survival Mode!")
                        .setStyle(new Style().setColor(Formatting.AQUA)),
                MessageType.GAME_INFO
        );
    }
}




