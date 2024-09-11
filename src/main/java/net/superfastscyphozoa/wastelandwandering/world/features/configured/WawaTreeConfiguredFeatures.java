package net.superfastscyphozoa.wastelandwandering.world.features.configured;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.MegaJungleTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import static net.superfastscyphozoa.wastelandwandering.world.features.configured.WawaConfiguredFeatures.registerKey;

public class WawaTreeConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> WASTEWOOD_KEY = registerKey("wastewood");
    public static final RegistryKey<ConfiguredFeature<?, ?>> RADPINE_KEY = registerKey("radpine");

    private static TreeFeatureConfig.Builder wastewood() {
        return (new TreeFeatureConfig.Builder( BlockStateProvider.of(Blocks.OAK_LOG),
                new MegaJungleTrunkPlacer(5, 4, 3),

                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),

                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines());
    }

    private static TreeFeatureConfig.Builder radpine() {
        return (new TreeFeatureConfig.Builder( BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(10, 4, 3),

                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),

                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines());
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        WawaConfiguredFeatures.register(context, WASTEWOOD_KEY, Feature.TREE, wastewood().build());
        WawaConfiguredFeatures.register(context, RADPINE_KEY, Feature.TREE, radpine().build());

    }
}
