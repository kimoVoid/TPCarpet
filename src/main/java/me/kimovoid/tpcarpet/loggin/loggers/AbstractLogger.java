package me.kimovoid.tpcarpet.loggin.loggers;

import carpet.logging.LoggerRegistry;
import com.google.common.base.Joiner;
import me.kimovoid.tpcarpet.TPCarpet;
import me.kimovoid.tpcarpet.loggin.TPCarpetLoggerRegistry;
import net.minecraft.text.BaseText;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

import carpet.logging.Logger;


public abstract class AbstractLogger
{
    public final static String MULTI_OPTION_SEP_REG = "[,. ]";
    public final  static String OPTION_SEP = ",";

    private final String name;

    @SuppressWarnings("FieldCanBeLocal")
    private final boolean strictOption;

    public AbstractLogger(String name, boolean strictOption)
    {
        this.name = name;
        this.strictOption = strictOption;
    }

    public String getName()
    {
        return this.name;
    }

    @Nullable
    public String getDefaultLoggingOption()
    {
        return null;
    }

    public Logger createCarpetLogger()
    {
        return TPCarpetLoggerRegistry.standardLogger(
                this.getName(),
                this.getName(),
                wrapOption(this.getDefaultLoggingOption())

        );
    }
    protected void actionWithLogger(Consumer<Logger> action)
    {

        Logger logger = LoggerRegistry.getLogger(this.getName());
        if (logger != null)
        {
            action.accept(logger);
        }
        else
        {
            System.err.println("Failed to get carpet logger " + this.getName());
        }
    }

    public void log(
            Supplier<BaseText[]> messagePromise
    )
    {
        actionWithLogger(logger -> logger.log(messagePromise));
    }

    private void log(Logger.lMessage messagePromise)
    {
        actionWithLogger(logger -> logger.log(messagePromise));
    }

    public void log(Logger.lMessageIgnorePlayer messagePromise)
    {
        actionWithLogger(logger -> logger.log(messagePromise));
    }

    protected static String[] wrapOption(@Nullable String... options)
    {
        if (options == null)
        {
            return null;
        }
        options = options.clone();
        for (int i = 0; i < options.length; i++)
        {
            options[i] = Arrays.toString(wrapOption(options[i]));
        }
        return options;
    }

    protected static String createCompoundOption(Iterable<String> option)
    {
        return Joiner.on(OPTION_SEP).join(option);
    }

    protected static String createCompoundOption(String... options)
    {
        return createCompoundOption(Arrays.asList(options));
    }
}