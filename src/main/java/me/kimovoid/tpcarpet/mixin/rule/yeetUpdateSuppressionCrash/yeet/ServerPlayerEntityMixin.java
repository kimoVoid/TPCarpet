package me.kimovoid.tpcarpet.mixin.rule.yeetUpdateSuppressionCrash.yeet;

import me.kimovoid.tpcarpet.helpers.rule.yeetUpdateSuppressionCrash.UpdateSuppressionYeeter;
import me.kimovoid.tpcarpet.utils.TPCarpetSettings;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin
{
    @Inject(
            method = "playerTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/crash/CrashReport;create(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    private void yeetUpdateSuppressionCrash_implForPlayerEntityTicking(CallbackInfo ci, Throwable throwable)
    {
        if (TPCarpetSettings.yeetUpdateSuppressionCrash)
        {
            UpdateSuppressionYeeter.extractInCauses(throwable).ifPresent(use -> {
                use.getSuppressionContext().report();
                ci.cancel();
            });
        }
    }
}