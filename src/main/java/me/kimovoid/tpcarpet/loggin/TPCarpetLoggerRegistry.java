package me.kimovoid.tpcarpet.loggin;

import carpet.logging.HUDLogger;
import carpet.logging.Logger;
import carpet.logging.LoggerRegistry;
import com.google.common.collect.Lists;
import me.kimovoid.tpcarpet.TPCarpet;
import me.kimovoid.tpcarpet.loggin.loggers.AbstractLogger;
import me.kimovoid.tpcarpet.loggin.loggers.memory.MemoryHUDLogger;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.List;

public class TPCarpetLoggerRegistry
{
    private static final List<Runnable> onRegisteredCallbacks = Lists.newArrayList();

    public static boolean __memory;

    public static  void registerLoggers()
    {
        register(MemoryHUDLogger.getInstance());

        onRegisteredCallbacks.forEach(Runnable::run);
    }

    private static void register (AbstractLogger logger)
    {
        register(logger.createCarpetLogger());
    }
    private static void register (Logger logger)
    {
        LoggerRegistry.registerLogger(logger.getLogName(), logger);
    }

    public static Field getLoggerField(String logName)
    {
        try {
            return TPCarpetLoggerRegistry.class.getField("__" + logName);
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException(String.format("Failed to get logger field \"%s\" @ %s", logName, TPCarpet.MOD_NAME));
        }
    }
    public static Logger standardLogger(
            String logName, String def, String[] options
    )
    {
        return new
                Logger(
                        getLoggerField(logName), logName, def, options
        );
    }
    public static HUDLogger standardHUDLogger(
            String logName, String def, @Nullable String options
    )
    {
        return new
                HUDLogger(
                        getLoggerField(logName), logName, def, new String[]{options}
        );
    }
    public static void AddLoggerRegisteredCallback(Runnable callback)
    {
        onRegisteredCallbacks.add(callback);
    }
}
