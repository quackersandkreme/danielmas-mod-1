package net.quackersnkreme.danielmas1;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.quackersnkreme.danielmas1.command.SwapPlayersCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.quackersnkreme.danielmas1.core.SwapManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DanielmasMod1 implements ModInitializer {
	public static final String MOD_ID = "danielmas1";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		//a lot of this mod is based on ZellVex's (github username) SwapInventory mod.

		//test_command
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment)
				-> {dispatcher.register(CommandManager.literal("test_command").executes(context
				-> {context.getSource().sendFeedback(()
				-> Text.literal("Zakk is a pixel pooper."), false);
					return 1;}));});

		//registering the swap players command
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->{
			SwapPlayersCommand.register(dispatcher);
		});

		//used for the timer due to minecraft being single threaded, it triggers the onServerTick method at the end of every server tick or 1/20th of a second
		ServerTickEvents.END_SERVER_TICK.register(SwapManager::onServerTick);
		LOGGER.info("Swap Players mod initialised!");
	}
}