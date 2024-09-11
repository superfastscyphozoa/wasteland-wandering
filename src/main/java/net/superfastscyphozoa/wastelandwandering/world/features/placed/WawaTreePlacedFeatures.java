package net.superfastscyphozoa.wastelandwandering.world.features.placed;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.world.features.configured.WawaTreeConfiguredFeatures;

import static net.superfastscyphozoa.wastelandwandering.world.features.placed.WawaPlacedFeatures.registerKey;

public class WawaTreePlacedFeatures {
    public static final RegistryKey<PlacedFeature> WASTEWOOD_PLACED_KEY = registerKey("wastewood_placed");
    public static final RegistryKey<PlacedFeature> RADPINE_PLACED_KEY = registerKey("radpine_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        WawaPlacedFeatures.register(context, WASTEWOOD_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(WawaTreeConfiguredFeatures.WASTEWOOD_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(2, 0.1f, 2),
                        RegisterBlocks.WASTEWOOD_SAPLING));

        WawaPlacedFeatures.register(context, RADPINE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(WawaTreeConfiguredFeatures.RADPINE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(2, 0.1f, 2),
                        RegisterBlocks.RADPINE_SAPLING));
    }
}
