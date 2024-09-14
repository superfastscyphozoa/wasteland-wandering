package net.superfastscyphozoa.wastelandwandering;

import net.fabricmc.api.ModInitializer;

import net.superfastscyphozoa.wastelandwandering.item.WawaFuels;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterParticles;
import net.superfastscyphozoa.wastelandwandering.util.WawaTrades;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;
import net.superfastscyphozoa.wastelandwandering.world.gen.WastelandWanderingWorldGeneration;

public class WastelandWandering implements ModInitializer {
	public static final String MOD_ID = "wasteland-wandering";

	// This logger is used to write text to the console and the log file.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.

		RegisterItems.registerWawaItems();
		RegisterBlocks.registerWawaBlocks();

		WawaFuels.registerFuels();
		WawaTrades.registerTrades();

		RegisterParticles.registerWawaParticles();

		WastelandWanderingWorldGeneration.generateWawaWorldgen();

		LOGGER.info("War never changes!");
	}
}