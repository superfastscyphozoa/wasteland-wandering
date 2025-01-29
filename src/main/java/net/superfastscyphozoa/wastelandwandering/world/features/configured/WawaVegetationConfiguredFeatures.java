package net.superfastscyphozoa.wastelandwandering.world.features.configured;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.collection.DataPool;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.world.features.placed.WawaTreePlacedFeatures;

import java.util.List;

import static net.superfastscyphozoa.wastelandwandering.world.features.configured.WawaConfiguredFeatures.registerKey;

public class WawaVegetationConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SINGLE_RAD_SHORT_GRASS_KEY = registerKey("single_rad_short_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_IRRADIATED_GRASS_KEY = registerKey("patch_irradiated_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_IRRADIATED_TALL_GRASS_KEY = registerKey("patch_irradiated_tall_grass");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_CARROT_FLOWER_KEY = registerKey("patch_carrot_flower");

    public static final RegistryKey<ConfiguredFeature<?, ?>> TREES_WASTED_FOREST_KEY = registerKey("trees_wasted_forest");

    private static RandomPatchFeatureConfig wawaCreateRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntry<PlacedFeature> wastewood = placedFeatureRegistryEntryLookup.getOrThrow(WawaTreePlacedFeatures.WASTEWOOD_PLACED_KEY);
        RegistryEntry<PlacedFeature> largeWastewood = placedFeatureRegistryEntryLookup.getOrThrow(WawaTreePlacedFeatures.LARGE_WASTEWOOD_PLACED_KEY);
        RegistryEntry<PlacedFeature> radpine = placedFeatureRegistryEntryLookup.getOrThrow(WawaTreePlacedFeatures.RADPINE_PLACED_KEY);

        //groundcover

        WawaConfiguredFeatures.register(
                context, SINGLE_RAD_SHORT_GRASS_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(RegisterBlocks.IRRADIATED_SHORT_GRASS.getDefaultState()))
        );

        WawaConfiguredFeatures.register(
                context, PATCH_IRRADIATED_GRASS_KEY, Feature.RANDOM_PATCH,
                wawaCreateRandomPatchFeatureConfig(BlockStateProvider.of(RegisterBlocks.IRRADIATED_SHORT_GRASS), 32)
        );

        WawaConfiguredFeatures.register(
                context, PATCH_IRRADIATED_TALL_GRASS_KEY, Feature.RANDOM_PATCH,
                wawaCreateRandomPatchFeatureConfig
                        (new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                                .add(RegisterBlocks.IRRADIATED_SHORT_GRASS.getDefaultState(), 1)
                                .add(RegisterBlocks.IRRADIATED_TALL_GRASS.getDefaultState(), 5)),
                                42)
        );

        //flowers

        WawaConfiguredFeatures.register(
                context, PATCH_CARROT_FLOWER_KEY, Feature.FLOWER,
                wawaCreateRandomPatchFeatureConfig
                        (new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                                .add(RegisterBlocks.CARROT_FLOWER.getDefaultState(), 2)
                                .add(RegisterBlocks.IRRADIATED_SHORT_GRASS.getDefaultState(), 1)), 64
                )
        );

        //trees

        WawaConfiguredFeatures.register(
                context, TREES_WASTED_FOREST_KEY, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(largeWastewood, 0.4F)),
                        wastewood)
        );
    }
}
