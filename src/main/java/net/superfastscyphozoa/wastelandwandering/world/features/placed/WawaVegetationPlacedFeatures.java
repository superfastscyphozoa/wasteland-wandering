package net.superfastscyphozoa.wastelandwandering.world.features.placed;

import com.google.common.collect.ImmutableList;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SurfaceWaterDepthFilterPlacementModifier;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.world.features.configured.WawaVegetationConfiguredFeatures;

import java.util.Collections;
import java.util.List;

import static net.superfastscyphozoa.wastelandwandering.world.features.placed.WawaPlacedFeatures.registerKey;

public class WawaVegetationPlacedFeatures {
    public static final RegistryKey<PlacedFeature> RAD_GRASS_BONEMEAL_PLACED_KEY = registerKey("rad_grass_bonemeal_placed");

    public static final RegistryKey<PlacedFeature> TREES_WASTED_FOREST_PLACED_KEY = registerKey("trees_wasted_forest_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> singeRadShortGrass = configuredFeatureRegistryEntryLookup.getOrThrow(WawaVegetationConfiguredFeatures.SINGLE_RAD_SHORT_GRASS_KEY);
        RegistryEntry<ConfiguredFeature<?, ?>> wastedForestTrees = configuredFeatureRegistryEntryLookup.getOrThrow(WawaVegetationConfiguredFeatures.TREES_WASTED_FOREST_KEY);

        WawaPlacedFeatures.register(context, RAD_GRASS_BONEMEAL_PLACED_KEY, singeRadShortGrass, Collections.singletonList(PlacedFeatures.isAir()));

        WawaPlacedFeatures.register(context, TREES_WASTED_FOREST_PLACED_KEY, wastedForestTrees,
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(2, 0.1f, 2),
                        RegisterBlocks.WASTEWOOD_SAPLING));
    }
}
