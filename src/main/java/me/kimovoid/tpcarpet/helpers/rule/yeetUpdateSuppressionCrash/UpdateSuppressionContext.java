package me.kimovoid.tpcarpet.helpers.rule.yeetUpdateSuppressionCrash;

import carpet.logging.Logger;
import carpet.logging.LoggerRegistry;
import carpet.utils.Messenger;
import net.minecraft.text.BaseText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class UpdateSuppressionContext
{
    private final Supplier<BaseText> textHolder;
    private final Throwable cause;

    public UpdateSuppressionContext(Throwable cause, World world, BlockPos pos)
    {
        this.cause = cause;
        this.textHolder = () -> Messenger.s("meow");
    }
    public static void noop()
    {
    }

    public BaseText getMessageText()
    {
        return this.textHolder.get();
    }

    public String getMessage()
    {
        return this.getMessageText().getString();
    }
    public Throwable getCause()
    {
        return this.cause;
    }

    public void report()
    {
        BaseText message = Messenger.s(this.getMessage(), Formatting.RED.toString());

        Logger logger = LoggerRegistry.getLogger("updateSuppressedCrashes");
        logger.log(() -> new BaseText[] {message});
    }
}
