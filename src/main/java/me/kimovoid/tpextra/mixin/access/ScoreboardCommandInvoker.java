package me.kimovoid.tpextra.mixin.access;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.server.command.ScoreboardCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ScoreboardCommand.class)
public interface ScoreboardCommandInvoker {

    @Invoker("executeSetDisplay")
    static int executeSetDisplay(ServerCommandSource source, int slot, ScoreboardObjective objective) throws CommandSyntaxException {
        throw new AssertionError();
    }
}