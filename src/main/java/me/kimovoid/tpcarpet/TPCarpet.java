package me.kimovoid.tpcarpet;

import me.kimovoid.tpcarpet.commands.features.CsCommand;
import me.kimovoid.tpcarpet.commands.features.PingCommand;
import me.kimovoid.tpcarpet.commands.features.SetSbCommand;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TPCarpet implements ModInitializer {

	public static TPCarpet INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger("TPExtra");

	@Override
	public void onInitialize() {
		INSTANCE = this;
		LOGGER.info("meow :3");

		/* Commands */
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> SetSbCommand.register(dispatcher));
		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> PingCommand.register(dispatcher)));
		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> CsCommand.register(dispatcher)));
	}
}