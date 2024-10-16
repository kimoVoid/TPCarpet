package me.kimovoid.tpextra.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.dimension.DimensionType;

public class CsCommand {

    private static BlockPos savedPosition;
    private static float savedYaw;
    private static float savedPitch;
    private static DimensionType savedDimension;

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
        savedPosition = player.getBlockPos();
        savedYaw = player.yaw;
        savedPitch = player.pitch;
        savedDimension = player.world.getDimension().getType();

        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(new LiteralText("Switched to Spectator Mode!"));
    }

    private static void switchToSurvival(ServerPlayerEntity player) {
        player.setGameMode(GameMode.SURVIVAL);

        if (player.world.getDimension().getType() == savedDimension) {
            player.networkHandler.requestTeleport(
                    savedPosition.getX(),
                    savedPosition.getY(),
                    savedPosition.getZ(),
                    savedYaw,
                    savedPitch
            );
        }

        player.sendMessage(new LiteralText("Switched back to Survival Mode!"));
    }
}




