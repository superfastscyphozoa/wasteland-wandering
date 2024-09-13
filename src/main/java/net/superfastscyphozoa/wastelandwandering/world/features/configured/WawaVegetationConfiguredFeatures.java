package net.superfastscyphozoa.wastelandwandering.world.features.configured;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;

import static net.superfastscyphozoa.wastelandwandering.world.features.configured.WawaConfiguredFeatures.registerKey;

public class WawaVegetationConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SINGLE_RAD_SHORT_GRASS_KEY = registerKey("single_rad_short_grass");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        WawaConfiguredFeatures.register(
                context, SINGLE_RAD_SHORT_GRASS_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(RegisterBlocks.IRRADIATED_SHORT_GRASS.getDefaultState()))
        );

    }
}
