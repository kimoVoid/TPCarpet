package me.kimovoid.tpextra;

import me.kimovoid.tpextra.commands.PingCommand;
import me.kimovoid.tpextra.commands.SetSbCommand;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TPExtra implements ModInitializer {

	public static TPExtra INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger("TPExtra");

	@Override
	public void onInitialize() {
		INSTANCE = this;
		LOGGER.info("meow :3");

		/* Commands */
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> SetSbCommand.register(dispatcher));
		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> PingCommand.register(dispatcher)));
	}
}