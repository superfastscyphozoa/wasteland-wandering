package net.superfastscyphozoa.wastelandwandering.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CarrotFlowerBlock extends BushyFlowerBlock{
    public CarrotFlowerBlock(RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, Settings settings) {
        super(stewEffect, effectLengthInSeconds, settings);
    }

    //voxel shape

    protected static final VoxelShape CARROT_FLOWER_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return CARROT_FLOWER_SHAPE;
    }

    //interact

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        ItemStack carrotStack = new ItemStack(Items.CARROT);
        ItemStack inHand = player.getStackInHand(player.getActiveHand());

        boolean emptyHand = inHand.isEmpty();

        if (emptyHand || inHand.isOf(Items.CARROT)) {

            if (emptyHand){
                player.setStackInHand(player.getActiveHand(), carrotStack);
            } else {
                if (!player.getInventory().insertStack(carrotStack)) {
                    player.dropItem(carrotStack, false);
                }
            }

            playPickingSounds(world, pos, player);

            if (!world.isClient) {
                world.removeBlock(pos, false);
            }

            return ActionResult.SUCCESS;

        } else {
            return ActionResult.PASS;
        }
    }

    //sounds

    private void playPickingSounds(World world, BlockPos pos, PlayerEntity player){
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
