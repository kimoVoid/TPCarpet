package me.kimovoid.tpcarpet.mixin.rule.yeetUpdateSuppressionCrash.yeet;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.kimovoid.tpcarpet.helpers.rule.yeetUpdateSuppressionCrash.UpdateSuppressionException;
import me.kimovoid.tpcarpet.helpers.rule.yeetUpdateSuppressionCrash.UpdateSuppressionYeeter;
import me.kimovoid.tpcarpet.utils.TPCarpetSettings;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(ReentrantThreadExecutor.class)
public abstract class ReentrantThreadExecutorMixin<R extends Runnable>
{
    @SuppressWarnings("ConstantValue")
    @WrapOperation(
            method = "executeTask",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/thread/ThreadExecutor;executeTask(Ljava/lang/Runnable;)V"
            )
    )
    private void yeetUpdateSupressionCrash_implForThreadExecutorTaskExecuting(ReentrantThreadExecutor<R> obj, R task, Operation<Void> original)
    {
        if (TPCarpetSettings.yeetUpdateSuppressionCrash && ((Object) this instanceof MinecraftServer))
        {
            try
            {
                original.call(obj, task);
            }
            catch (Throwable throwable)
            {
                Optional<UpdateSuppressionException> opt = UpdateSuppressionYeeter.extractInCauses(throwable);
                if (opt.isPresent())
                {
                    opt.get().getSuppressionContext().report();
                }
                else
                {
                    throw throwable;
                }
            }
        }
    }
}
