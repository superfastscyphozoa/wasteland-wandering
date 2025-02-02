package net.superfastscyphozoa.wastelandwandering.world.features.configured;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.block.PropaguleBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator;
import net.minecraft.world.gen.trunk.MegaJungleTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;

import java.util.List;

import static net.superfastscyphozoa.wastelandwandering.world.features.configured.WawaConfiguredFeatures.registerKey;

public class WawaTreeConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> WASTEWOOD_KEY = registerKey("wastewood");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_WASTEWOOD_KEY = registerKey("large_wastewood");

    public static final RegistryKey<ConfiguredFeature<?, ?>> MUTFRUIT_TREE_KEY = registerKey("mutfruit_tree");

    public static final RegistryKey<ConfiguredFeature<?, ?>> RADPINE_KEY = registerKey("radpine");

    //wastewood

    private static TreeFeatureConfig.Builder wastewood() {
        return (new TreeFeatureConfig.Builder( BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(4, 4, 2),

                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),

                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines().dirtProvider(BlockStateProvider.of(Blocks.ROOTED_DIRT))
                .forceDirt());
    }

    private static TreeFeatureConfig.Builder large_wastewood() {
        return (new TreeFeatureConfig.Builder( BlockStateProvider.of(Blocks.OAK_LOG),
                new MegaJungleTrunkPlacer(5, 6, 5),

                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),

                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines().dirtProvider(BlockStateProvider.of(Blocks.ROOTED_DIRT))
                .forceDirt());
    }

    //mutfruit

    private static TreeFeatureConfig.Builder mutfruit() {
        return (new TreeFeatureConfig.Builder( BlockStateProvider.of(RegisterBlocks.MUTFRUIT_LOG),
                new StraightTrunkPlacer(3, 2, 1),

                BlockStateProvider.of(RegisterBlocks.MUTFRUIT_LEAVES),
                new CherryFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(4),
                        0.25F, 0.7F, 0.4F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines());
    }

    //radpine

    private static TreeFeatureConfig.Builder radpine() {
        return (new TreeFeatureConfig.Builder( BlockStateProvider.of(Blocks.SPRUCE_LOG),
                new StraightTrunkPlacer(10, 4, 3),

                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),

                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines().dirtProvider(BlockStateProvider.of(Blocks.ROOTED_DIRT))
                .forceDirt());
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        AlterGroundTreeDecorator leafLitterDecorator = new AlterGroundTreeDecorator(BlockStateProvider.of(RegisterBlocks.WASTEWOOD_LITTER));

        AttachedToLeavesTreeDecorator mutfruitDecorator  = new AttachedToLeavesTreeDecorator(
                0.3F,
                1,
                0,
                new RandomizedIntBlockStateProvider(
                        BlockStateProvider.of(RegisterBlocks.BUDDING_MUTFRUIT.getDefaultState()),
                        PropaguleBlock.AGE,
                        UniformIntProvider.create(0, 3)
                ),
                1,
                List.of(Direction.DOWN)
        );

        WawaConfiguredFeatures.register(context, WASTEWOOD_KEY, Feature.TREE, wastewood().build());
        WawaConfiguredFeatures.register(context, LARGE_WASTEWOOD_KEY, Feature.TREE, large_wastewood()
                .decorators(ImmutableList.of(leafLitterDecorator)).build());

        WawaConfiguredFeatures.register(context, MUTFRUIT_TREE_KEY, Feature.TREE, mutfruit().
                decorators(ImmutableList.of(mutfruitDecorator)).build());

        WawaConfiguredFeatures.register(context, RADPINE_KEY, Feature.TREE, radpine().build());

    }
}
