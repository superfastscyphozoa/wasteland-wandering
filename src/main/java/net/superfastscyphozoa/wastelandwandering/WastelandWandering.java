package net.superfastscyphozoa.wastelandwandering;

import net.fabricmc.api.ModInitializer;

import net.superfastscyphozoa.wastelandwandering.item.util.WawaFuels;
import net.superfastscyphozoa.wastelandwandering.item.util.WawaItemGroups;
import net.superfastscyphozoa.wastelandwandering.registry.*;
import net.superfastscyphozoa.wastelandwandering.util.WawaTrades;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.superfastscyphozoa.wastelandwandering.world.gen.WastelandWanderingWorldGeneration;

public class WastelandWandering implements ModInitializer {
	public static final String MOD_ID = "wasteland-wandering";

	// This logger is used to write text to the console and the log file.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.

		WawaItemGroups.registerWawaItemGroups();

		RegisterItems.registerWawaItems();
		RegisterBlocks.registerWawaBlocks();

		WawaFuels.registerFuels();
		WawaTrades.registerTrades();

		RegisterParticles.registerWawaParticles();
		RegisterStatusEffects.registerWawaStatusEffects();

		RegisterEntities.registerWawaEntities();

		WastelandWanderingWorldGeneration.generateWawaWorldgen();

		LOGGER.info("War never changes!");
	}
}