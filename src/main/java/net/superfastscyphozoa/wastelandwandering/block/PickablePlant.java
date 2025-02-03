package net.superfastscyphozoa.wastelandwandering.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public interface PickablePlant {

    ItemConvertible plantToPick();

    default PickablePlant.PickType getPickType(){
        return PickType.DESTROY;
    }

    enum PickType {
        DESTROY,
        RESET_AGE
    }

    default boolean pickConditions(PlayerEntity player){
        return (emptyHand(player) || inHand(player).isOf(plantToPick().asItem()));
    }

    default void pickPlant(BlockState state, World world, BlockPos pos, PlayerEntity player){
        ItemStack plantStack = new ItemStack(plantToPick());

        if (pickConditions(player)) {

            if (emptyHand(player)){
                player.setStackInHand(player.getActiveHand(), plantStack);
            } else {
                if (!player.getInventory().insertStack(plantStack)) {
                    player.dropItem(plantStack, false);
                }
            }

            playPickingSounds(world, pos, player);

            if (!world.isClient) {
                switch (this.getPickType()) {
                    case DESTROY -> world.removeBlock(pos, false);
                    case RESET_AGE -> resetAge(state, world, pos, player);
                }
            }
        }
    }

    default ItemStack inHand(PlayerEntity player){
        return player.getStackInHand(player.getActiveHand());
    }

    default boolean emptyHand(PlayerEntity player){
        return inHand(player).isEmpty();
    }

    default void resetAge(BlockState state, World world, BlockPos pos, PlayerEntity player){
        BlockState blockState = state.with(BuddingFruitBlock.AGE, 0);
        world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
    }

    default void playPickingSounds(World world, BlockPos pos, PlayerEntity player){
        world.playSound(player, pos,
                SoundEvents.BLOCK_GRASS_BREAK,
                SoundCategory.NEUTRAL,
                0.6F,
                1.0f);

        world.playSound(player, pos,
                SoundEvents.ENTITY_ITEM_PICKUP,
                SoundCategory.NEUTRAL,
                0.5F,
                1.0f);
    }
}
