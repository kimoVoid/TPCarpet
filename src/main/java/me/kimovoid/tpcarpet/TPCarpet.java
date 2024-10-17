package me.kimovoid.tpcarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import me.kimovoid.tpcarpet.commands.features.CsCommand;
import me.kimovoid.tpcarpet.commands.features.PingCommand;
import me.kimovoid.tpcarpet.commands.features.SetSbCommand;
import me.kimovoid.tpcarpet.commands.features.StopCommand;
import me.kimovoid.tpcarpet.loggin.TPCarpetLoggerRegistry;
import me.kimovoid.tpcarpet.utils.TPCarpetSettings;
import net.fabricmc.api.ModInitializer;

import net.minecraft.server.command.ServerCommandSource;
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
	}

	@Override
	public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher){
		SetSbCommand.register(dispatcher);
		PingCommand.register(dispatcher);
		CsCommand.register(dispatcher);
		StopCommand.register(dispatcher);
	}

	@Override
	public void registerLoggers() {
		TPCarpetLoggerRegistry.registerLoggers();
	}

}