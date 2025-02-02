package net.superfastscyphozoa.wastelandwandering.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PickablePlant {

    ItemConvertible plantToPick();

    default PickablePlant.PickType getPickType(){
        return PickType.DESTROY;
    }

    enum PickType {
        DESTROY,
        RESET_AGE
    }

    default void pickPlant(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit){
        ItemStack plantStack = new ItemStack(plantToPick());
        ItemStack inHand = player.getStackInHand(player.getActiveHand());

        boolean emptyHand = inHand.isEmpty();

        if (emptyHand || inHand.isOf(plantToPick().asItem())) {

            if (emptyHand){
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
                    //case RESET_AGE -> st
                }
            }
        }
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
