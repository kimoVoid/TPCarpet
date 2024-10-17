package me.kimovoid.tpcarpet.loggin.loggers;

import carpet.logging.HUDLogger;
import me.kimovoid.tpcarpet.loggin.TPCarpetLoggerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.BaseText;

import java.util.Arrays;

public abstract class AbstractHUDLogger extends AbstractLogger
{
    public AbstractHUDLogger(String name, boolean strictOption)
    {
        super(name, strictOption);
    }

    public abstract BaseText[] onHudUpdate(String option, PlayerEntity playerEntity);

    @Override
    public HUDLogger createCarpetLogger()
    {
        return TPCarpetLoggerRegistry.standardHUDLogger(
                this.getName(), this.getDefaultLoggingOption(), Arrays.toString(new String[0])
        );
    }


}
