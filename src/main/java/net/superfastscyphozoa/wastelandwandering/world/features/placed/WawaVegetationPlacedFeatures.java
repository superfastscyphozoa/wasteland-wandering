package net.superfastscyphozoa.wastelandwandering.world.features.placed;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.world.features.configured.WawaVegetationConfiguredFeatures;

import java.util.Collections;
import java.util.List;

import static net.superfastscyphozoa.wastelandwandering.world.features.placed.WawaPlacedFeatures.registerKey;

public class WawaVegetationPlacedFeatures {
    public static final RegistryKey<PlacedFeature> RAD_GRASS_BONEMEAL_PLACED_KEY = registerKey("rad_grass_bonemeal_placed");

    public static final RegistryKey<PlacedFeature> PATCH_IRRADIATED_GRASS_PLACED_KEY = registerKey("patch_irradiated_grass_placed");
    public static final RegistryKey<PlacedFeature> PATCH_SPARSE_IRRADIATED_GRASS_PLACED_KEY = registerKey("patch_sparse_irradiated_grass_placed");
    public static final RegistryKey<PlacedFeature> PATCH_TALL_IRRADIATED_GRASS_PLACED_KEY = registerKey("patch_tall_irradiated_grass_placed");

    public static final RegistryKey<PlacedFeature> PATCH_CARROT_FLOWER_PLACED_KEY = registerKey("patch_carrot_flower_placed");

    public static final RegistryKey<PlacedFeature> TREES_WASTED_FOREST = registerKey("trees_wasted_forest");

    public static List<PlacementModifier> modifiers(int count) {
        return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    }

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> singeRadShortGrass = configuredFeatureRegistryEntryLookup.getOrThrow(WawaVegetationConfiguredFeatures.SINGLE_RAD_SHORT_GRASS_KEY);

        RegistryEntry<ConfiguredFeature<?, ?>> irradiatedGrassPatch = configuredFeatureRegistryEntryLookup.getOrThrow(WawaVegetationConfiguredFeatures.PATCH_IRRADIATED_GRASS_KEY);
        RegistryEntry<ConfiguredFeature<?, ?>> irradiatedTallGrassPatch = configuredFeatureRegistryEntryLookup.getOrThrow(WawaVegetationConfiguredFeatures.PATCH_IRRADIATED_TALL_GRASS_KEY);

        RegistryEntry<ConfiguredFeature<?, ?>> carrotFlowerPatch = configuredFeatureRegistryEntryLookup.getOrThrow(WawaVegetationConfiguredFeatures.PATCH_CARROT_FLOWER_KEY);

        RegistryEntry<ConfiguredFeature<?, ?>> wastedForestTrees = configuredFeatureRegistryEntryLookup.getOrThrow(WawaVegetationConfiguredFeatures.TREES_WASTED_FOREST_KEY);

        //groundcover
        WawaPlacedFeatures.register(context, RAD_GRASS_BONEMEAL_PLACED_KEY, singeRadShortGrass, Collections.singletonList(PlacedFeatures.isAir()));

        WawaPlacedFeatures.register(
                context, PATCH_IRRADIATED_GRASS_PLACED_KEY, irradiatedGrassPatch,
                NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10),
                SquarePlacementModifier.of(),
                PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of()
        );

        WawaPlacedFeatures.register(context, PATCH_SPARSE_IRRADIATED_GRASS_PLACED_KEY, irradiatedGrassPatch,
                modifiers(3));

        WawaPlacedFeatures.register(
                context, PATCH_TALL_IRRADIATED_GRASS_PLACED_KEY, irradiatedTallGrassPatch,
                NoiseThresholdCountPlacementModifier.of(-0.8, 0, 7),
                RarityFilterPlacementModifier.of(12),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );

        //flowers

        WawaPlacedFeatures.register(
                context, PATCH_CARROT_FLOWER_PLACED_KEY, carrotFlowerPatch,
                RarityFilterPlacementModifier.of(19),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );

        //trees
        WawaPlacedFeatures.register(context, TREES_WASTED_FOREST, wastedForestTrees,
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.5f, 3),
                        RegisterBlocks.WASTEWOOD_SAPLING));
    }
}
