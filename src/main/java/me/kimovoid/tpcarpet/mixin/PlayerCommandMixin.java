package me.kimovoid.tpcarpet.mixin;

import carpet.commands.PlayerCommand;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import me.kimovoid.tpcarpet.commands.carpet.FakePlayerRejoinHelper;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import static net.minecraft.server.command.CommandManager.literal;

@Mixin({PlayerCommand.class})
public abstract class PlayerCommandMixin {
    @Shadow(
            remap = false
    )
    private static int spawn(CommandContext<ServerCommandSource> context) {
        return 0;
    }

    @ModifyReceiver(
            method = {"register"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lcom/mojang/brigadier/builder/RequiredArgumentBuilder;suggests(Lcom/mojang/brigadier/suggestion/SuggestionProvider;)Lcom/mojang/brigadier/builder/RequiredArgumentBuilder;",
                    ordinal = 0,
                    remap = false
            )},
            remap = false
    )
    private static RequiredArgumentBuilder<ServerCommandSource, ?> fakePlayerRejoin_addRejoinCommand(RequiredArgumentBuilder<ServerCommandSource, ?> node, SuggestionProvider<?> provider)
    {
        return node.then(literal("rejoin").executes(PlayerCommandMixin::rejoin));
    }
    @Unique
    private static int rejoin(CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        FakePlayerRejoinHelper.isRejoin.set(true);
        try {
            return spawn(context);
        }
         finally
        {
            FakePlayerRejoinHelper.isRejoin.remove();
        }
    }
}
