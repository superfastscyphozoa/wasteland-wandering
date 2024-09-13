package net.superfastscyphozoa.wastelandwandering.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;

public class IrradiatedShortPlantBlock extends ShortPlantBlock {
    public IrradiatedShortPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        TallPlantBlock tallPlantBlock = (TallPlantBlock) RegisterBlocks.IRRADIATED_TALL_GRASS;
        if (tallPlantBlock.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
            TallPlantBlock.placeAt(world, tallPlantBlock.getDefaultState(), pos, 2);
        }
    }
}
