package net.superfastscyphozoa.wastelandwandering.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;

public class IvyBlock extends MultifaceGrowthBlock {
    public static final MapCodec<IvyBlock> CODEC = createCodec(IvyBlock::new);

    public IvyBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<IvyBlock> getCodec() {
        return CODEC;
    }

    @Override
    public LichenGrower getGrower() {
        return null;
    }
}
