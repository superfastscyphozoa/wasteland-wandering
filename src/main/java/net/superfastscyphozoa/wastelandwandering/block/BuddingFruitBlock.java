package net.superfastscyphozoa.wastelandwandering.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;
import org.jetbrains.annotations.Nullable;

public class BuddingFruitBlock extends PlantBlock implements Fertilizable, PickablePlant {
    public static final MapCodec<BuddingFruitBlock> CODEC = createCodec(BuddingFruitBlock::new);
    public static final IntProperty AGE = Properties.AGE_3;

    @Override
    protected MapCodec<BuddingFruitBlock> getCodec() {
        return CODEC;
    }

    public BuddingFruitBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager
                        .getDefaultState()
                        .with(AGE, 0)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public ItemConvertible plantToPick() {
        return RegisterItems.MUTFRUIT;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (state.get(AGE) == 3) {
            pickPlant(state, world, pos, player, hit);
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(AGE, 3);
    }

    protected static final VoxelShape MUTFRUIT_SHAPE = Block.createCuboidShape(0.0, 10.0, 0.0, 16.0, 16.0, 16.0);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return MUTFRUIT_SHAPE;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.up()).isOf(RegisterBlocks.MUTFRUIT_LEAVES);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.UP && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!isFullyGrown(state)) {
            world.setBlockState(pos, state.cycle(AGE), Block.NOTIFY_LISTENERS);
        }
    }

    private static boolean isFullyGrown(BlockState state) {
        return state.get(AGE) == 3;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !isFullyGrown(state);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return !isFullyGrown(state);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (!isFullyGrown(state)){
            world.setBlockState(pos, state.cycle(AGE), Block.NOTIFY_LISTENERS);
        }
    }

    public static BlockState getBuddingFruitState(int age, Block block) {
        return block.getDefaultState().with(AGE, age);
    }
}
