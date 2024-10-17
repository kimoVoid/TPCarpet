package me.kimovoid.tpcarpet.loggin;

import me.kimovoid.tpcarpet.loggin.loggers.AbstractHUDLogger;
import me.kimovoid.tpcarpet.loggin.loggers.memory.MemoryHUDLogger;
import net.minecraft.server.MinecraftServer;
import carpet.logging.LoggerRegistry;

public class TPCarpetHUDController
{
    public static void updateHUD(MinecraftServer server)
    {
        doHudLogging(TPCarpetLoggerRegistry.__memory, MemoryHUDLogger.NAME, MemoryHUDLogger.getInstance());
    }

    public static void doHudLogging(boolean condition, String loggerName, AbstractHUDLogger logger)
    {
        if (condition)
        {
            LoggerRegistry.getLogger(loggerName).log(logger::onHudUpdate);
        }
    }
}
