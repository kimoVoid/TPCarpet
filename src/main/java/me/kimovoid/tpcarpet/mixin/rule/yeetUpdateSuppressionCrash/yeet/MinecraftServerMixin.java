package me.kimovoid.tpcarpet.mixin.rule.yeetUpdateSuppressionCrash.yeet;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.kimovoid.tpcarpet.helpers.rule.yeetUpdateSuppressionCrash.UpdateSuppressionException;
import me.kimovoid.tpcarpet.helpers.rule.yeetUpdateSuppressionCrash.UpdateSuppressionYeeter;
import me.kimovoid.tpcarpet.utils.TPCarpetSettings;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow protected abstract boolean shouldKeepTicking();

    @WrapOperation(
            method = "tickWorlds",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerWorld;tick(Ljava/util/function/BooleanSupplier;)V"
            )
    )
    private void yeetUpdateSupressionCrash_implOnTickWorlds(ServerWorld serverWorld, BooleanSupplier shouldKeepTicking, Operation<Void> original)
    {
        if (TPCarpetSettings.yeetUpdateSuppressionCrash) {

            try {
                original.call(serverWorld, shouldKeepTicking);
            } catch (Exception e) {
                Optional<UpdateSuppressionException> opt = UpdateSuppressionYeeter.extractInCauses(e);
                opt.ifPresent(exception -> exception.getSuppressionContext().report());
            }
        } else {
            original.call(serverWorld, shouldKeepTicking);
        }
    }
}
