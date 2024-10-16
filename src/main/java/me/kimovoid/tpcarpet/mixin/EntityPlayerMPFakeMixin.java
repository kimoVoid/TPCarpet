package me.kimovoid.tpcarpet.mixin;

import carpet.patches.EntityPlayerMPFake;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import me.kimovoid.tpcarpet.commands.carpet.FakePlayerRejoinHelper;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityPlayerMPFake.class)
public abstract class EntityPlayerMPFakeMixin
{
    @WrapWithCondition(
            method = "createFake",
            at = @At(
                    value = "FIELD",
                    target = "Lcarpet/patches/EntityPlayerMPFake;fixStartingPosition:Ljava/lang/Runnable;",
                    remap = false
            )
    )
    private static boolean fakePlayerRejoin_disableFixerOnRejoin(EntityPlayerMPFake instance, Runnable newValue)
    {
        return !FakePlayerRejoinHelper.isRejoin.get();
    }

    @ModifyExpressionValue(
            method = "createFake",
            at = @At(
                    value = "FIELD",
                    target = "Lcarpet/patches/EntityPlayerMPFake;dimension:Lnet/minecraft/world/dimension/DimensionType;",
                    ordinal = 0
            )
    )
    private static DimensionType fakePlayerRejoin_dontDoTransdimensionTeleport(
            DimensionType playerDimension,
            @Local(argsOnly = true) DimensionType targetDimension
    )
    {
        if (FakePlayerRejoinHelper.isRejoin.get())
        {
            playerDimension = targetDimension;
        }
        return playerDimension;
    }
    @WrapWithCondition(
            method = "createFake",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;requestTeleport(DDDFF)V"
            )
    )
    private static boolean fakePlayerRejoin_dontRequestTeleport(
            ServerPlayNetworkHandler instance, double x, double y, double z, float yaw, float pitch
    )
    {
        return !FakePlayerRejoinHelper.isRejoin.get();
    }
}
