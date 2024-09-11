package net.superfastscyphozoa.wastelandwandering.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.superfastscyphozoa.wastelandwandering.data.tags.WawaBlockTagProvider;
import net.superfastscyphozoa.wastelandwandering.data.tags.WawaItemTagProvider;
import net.superfastscyphozoa.wastelandwandering.world.features.configured.WawaConfiguredFeatures;
import net.superfastscyphozoa.wastelandwandering.world.features.placed.WawaPlacedFeatures;

public class WastelandWanderingDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(WawaLootTableProvider::new);
		pack.addProvider(WawaModelProvider::new);
		pack.addProvider(WawaRecipeProvider::new);

		pack.addProvider(WawaBlockTagProvider::new);
		pack.addProvider(WawaItemTagProvider::new);

		pack.addProvider(WawaWorldGenerator::new);

	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder){
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, WawaConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, WawaPlacedFeatures::bootstrap);
	}
}
