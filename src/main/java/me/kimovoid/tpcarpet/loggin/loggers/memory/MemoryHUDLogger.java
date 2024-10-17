package me.kimovoid.tpcarpet.loggin.loggers.memory;

import carpet.utils.Messenger;
import me.kimovoid.tpcarpet.loggin.loggers.AbstractHUDLogger;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.BaseText;

public class MemoryHUDLogger extends AbstractHUDLogger
{
    public static final String NAME = "memory";

    private static final MemoryHUDLogger INSTANCE = new MemoryHUDLogger();

    private MemoryHUDLogger()
    {
        super(NAME, true);
    }

    public static MemoryHUDLogger getInstance()
    {
        return INSTANCE;
    }

    @Override
    public BaseText[] onHudUpdate(String option, PlayerEntity playerEntity)
    {
        final long bytesPerMB = 1024 * 1024;
        long free = Runtime.getRuntime().freeMemory();
        long total = Runtime.getRuntime().totalMemory();
        long max = Runtime.getRuntime().maxMemory();

        long usedMB = Math.max(total - free, 0) / bytesPerMB;
        long allocateMB = total / bytesPerMB;
        long maxMB = max != Long.MAX_VALUE ? max / bytesPerMB : -1;
        return new BaseText[]{
                Messenger.c(String.format("g %dM / %dM | %dM", usedMB, allocateMB, maxMB))
        };
    }
}
