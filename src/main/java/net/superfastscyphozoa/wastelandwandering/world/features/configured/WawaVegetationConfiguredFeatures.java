package net.superfastscyphozoa.wastelandwandering.world.features.configured;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.world.features.placed.WawaTreePlacedFeatures;

import java.util.List;

import static net.superfastscyphozoa.wastelandwandering.world.features.configured.WawaConfiguredFeatures.registerKey;

public class WawaVegetationConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SINGLE_RAD_SHORT_GRASS_KEY = registerKey("single_rad_short_grass");

    public static final RegistryKey<ConfiguredFeature<?, ?>> TREES_WASTED_FOREST_KEY = registerKey("trees_wasted_forest");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntry<PlacedFeature> wastewood = placedFeatureRegistryEntryLookup.getOrThrow(WawaTreePlacedFeatures.WASTEWOOD_PLACED_KEY);
        RegistryEntry<PlacedFeature> largeWastewood = placedFeatureRegistryEntryLookup.getOrThrow(WawaTreePlacedFeatures.LARGE_WASTEWOOD_PLACED_KEY);
        RegistryEntry<PlacedFeature> radpine = placedFeatureRegistryEntryLookup.getOrThrow(WawaTreePlacedFeatures.RADPINE_PLACED_KEY);

        WawaConfiguredFeatures.register(
                context, SINGLE_RAD_SHORT_GRASS_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(RegisterBlocks.IRRADIATED_SHORT_GRASS.getDefaultState()))
        );

        WawaConfiguredFeatures.register(
                context, TREES_WASTED_FOREST_KEY, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(largeWastewood, 0.2F)),
                        wastewood)
        );
    }
}
