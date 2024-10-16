package me.kimovoid.tpcarpet.helpers.rule.yeetUpdateSuppressionCrash;

import me.kimovoid.tpcarpet.utils.TPCarpetSettings;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class UpdateSuppressionYeeter
{
    public static void noop()
    {
        // load the classes in advance
        // to prevent NoClassDefFoundError due to stack overflow again when loading this class
        UpdateSuppressionContext.noop();
        UpdateSuppressionExceptions.noop();
    }

    @NotNull
    public static Throwable tryReplaceWithWrapper(Throwable throwable, World world, BlockPos pos)
    {
        if (TPCarpetSettings.yeetUpdateSuppressionCrash)
        {
            // no UpdateSuppressionException, try to wrap it
            if (!extractInCauses(throwable).isPresent())
            {
                Throwable wrapped = (Throwable)UpdateSuppressionExceptions.createWrapper(throwable, world, pos);
                if (wrapped != null)
                {
                    throwable = wrapped;
                }
            }
        }
        return throwable;
    }

    public static Optional<UpdateSuppressionException> extractInCauses(Throwable throwable)
    {
        for (; throwable != null; throwable = throwable.getCause())
        {
            if (throwable instanceof UpdateSuppressionException)
            {
                return Optional.of((UpdateSuppressionException)throwable);
            }
        }
        return Optional.empty();
    }
}
