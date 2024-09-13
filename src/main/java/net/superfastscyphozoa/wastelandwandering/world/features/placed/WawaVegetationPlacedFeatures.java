package net.superfastscyphozoa.wastelandwandering.world.features.placed;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.superfastscyphozoa.wastelandwandering.world.features.configured.WawaVegetationConfiguredFeatures;

import java.util.Collections;

import static net.superfastscyphozoa.wastelandwandering.world.features.placed.WawaPlacedFeatures.registerKey;

public class WawaVegetationPlacedFeatures {
    public static final RegistryKey<PlacedFeature> RAD_GRASS_BONEMEAL_PLACED_KEY = registerKey("rad_grass_bonemeal_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> singeRadShortGrass = configuredFeatureRegistryEntryLookup.getOrThrow(WawaVegetationConfiguredFeatures.SINGLE_RAD_SHORT_GRASS_KEY);

        WawaPlacedFeatures.register(context, RAD_GRASS_BONEMEAL_PLACED_KEY, singeRadShortGrass, Collections.singletonList(PlacedFeatures.isAir()));
    }
}
