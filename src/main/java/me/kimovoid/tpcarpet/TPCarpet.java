package me.kimovoid.tpcarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import me.kimovoid.tpcarpet.commands.features.CsCommand;
import me.kimovoid.tpcarpet.commands.features.PingCommand;
import me.kimovoid.tpcarpet.commands.features.SetSbCommand;
import me.kimovoid.tpcarpet.loggin.TPCarpetLoggerRegistry;
import me.kimovoid.tpcarpet.utils.TPCarpetSettings;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TPCarpet implements ModInitializer, CarpetExtension {

	public static final String MOD_NAME = "TPCarpet";
	public static TPCarpet INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger("TPCarpet");

	public static void loadExtension() {
		CarpetServer.manageExtension(INSTANCE);
	}

	@Override
	public void onGameStarted() {
		// let's /carpet handle our few simple settings
		CarpetServer.settingsManager.parseSettingsClass(TPCarpetSettings.class);
	}
	@Override
	public void onInitialize() {
		INSTANCE = this;
		LOGGER.info("meow :3");
		TPCarpet.loadExtension();

		/* Commands */
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> SetSbCommand.register(dispatcher));
		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> PingCommand.register(dispatcher)));
		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> CsCommand.register(dispatcher)));
	}

	@Override
	public void registerLoggers() {
		TPCarpetLoggerRegistry.registerLoggers();
	}

}