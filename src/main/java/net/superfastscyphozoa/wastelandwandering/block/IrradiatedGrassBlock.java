package net.superfastscyphozoa.wastelandwandering.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;

import java.util.List;
import java.util.Optional;

public class IrradiatedGrassBlock extends SpreadableBlock implements Fertilizable {
    public static final MapCodec<IrradiatedGrassBlock> CODEC = createCodec(IrradiatedGrassBlock::new);

    @Override
    protected MapCodec<IrradiatedGrassBlock> getCodec() {
        return CODEC;
    }

    public IrradiatedGrassBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
        BlockState blockState = RegisterBlocks.IRRADIATED_SHORT_GRASS.getDefaultState();
        Optional<RegistryEntry.Reference<PlacedFeature>> optional = world.getRegistryManager()
                .get(RegistryKeys.PLACED_FEATURE)
                .getEntry(VegetationPlacedFeatures.GRASS_BONEMEAL);

        label49:
        for (int i = 0; i < 128; i++) {
            BlockPos blockPos2 = blockPos;

            for (int j = 0; j < i / 16; j++) {
                blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!world.getBlockState(blockPos2.down()).isOf(this) || world.getBlockState(blockPos2).isFullCube(world, blockPos2)) {
                    continue label49;
                }
            }

            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.isOf(blockState.getBlock()) && random.nextInt(10) == 0) {
                ((Fertilizable)blockState.getBlock()).grow(world, random, blockPos2, blockState2);
            }

            if (blockState2.isAir()) {
                RegistryEntry<PlacedFeature> registryEntry;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = world.getBiome(blockPos2).value().getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    registryEntry = ((RandomPatchFeatureConfig) list.getFirst().config()).feature();
                } else {
                    if (optional.isEmpty()) {
                        continue;
                    }

                    registryEntry = optional.get();
                }

                registryEntry.value().generateUnregistered(world, world.getChunkManager().getChunkGenerator(), random, blockPos2);
            }
        }
    }

    @Override
    public FertilizableType getFertilizableType() {
        return FertilizableType.NEIGHBOR_SPREADER;
    }
}
