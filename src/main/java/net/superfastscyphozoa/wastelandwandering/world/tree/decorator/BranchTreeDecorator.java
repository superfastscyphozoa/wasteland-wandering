package net.superfastscyphozoa.wastelandwandering.world.tree.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;

public class BranchTreeDecorator extends TreeDecorator {
    public static final MapCodec<BranchTreeDecorator> CODEC = RecordCodecBuilder.mapCodec(objectInstance -> objectInstance.group(
            Codec.floatRange(0.0F, 1.0F).fieldOf("chance").forGetter((BranchTreeDecorator c) -> c.chance),
            BlockStateProvider.TYPE_CODEC.fieldOf("blockstate").forGetter((BranchTreeDecorator b) -> b.blockState),
            Codec.intRange(0, 15).fieldOf("lowestpos").forGetter((BranchTreeDecorator l) -> l.lowestPos)
    ).apply(objectInstance, BranchTreeDecorator::new));

    private final float chance;
    private final BlockStateProvider blockState;
    private final int lowestPos;

    public BranchTreeDecorator(float chance, BlockStateProvider blockState, int lowestPos) {
        this.chance = chance;
        this.blockState = blockState;
        this.lowestPos = lowestPos;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return WawaTreeDecoratorTypes.BRANCH_TREE_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        if (!(random.nextFloat() >= this.chance)) {

            final int[] amount = {1};

            List<BlockPos> list = generator.getLogPositions();
            int i = list.getFirst().getY();

            list.stream().filter((pos) -> pos.getY() - i > random.nextBetween(lowestPos, lowestPos+4)).forEach((pos) -> {

                for (Direction direction : Direction.Type.HORIZONTAL) {

                    Direction direction2 = direction.getOpposite();
                    BlockPos blockPos = pos.add(direction2.getOffsetX(), 0, direction2.getOffsetZ());

                    boolean tooClose = !generator.isAir(blockPos.down()) || !generator.isAir(blockPos.up());

                    if (generator.isAir(blockPos) && amount[0] <= 2 && !tooClose) {

                        if (random.nextBoolean() && random.nextFloat() <= 0.25) {

                            generator.replace(blockPos, this.blockState.get(generator.getRandom(), blockPos)
                                    .with(PillarBlock.AXIS, direction.getAxis()));

                            amount[0]++;
                        }
                    }
                }
            });
        }
    }
}
