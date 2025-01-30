package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class StimpakItem extends ChemItem {

    public StimpakItem(Settings settings) {
        super(settings);
    }

    @Override
    protected void chemEffects(World world, PlayerEntity user) {
        if (!world.isClient) {
            user.setHealth(user.getMaxHealth());
            user.getHungerManager().add(20, 1.2F);
        }
    }

    @Override
    protected boolean chemUseConditions(World world, PlayerEntity user, Hand hand) {
        return ((user.getHealth() != user.getMaxHealth()) || (user.getHungerManager().isNotFull()));
    }

    @Override
    protected void playUseSounds(World world, PlayerEntity user) {
        world.playSound(
                null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH,
                SoundCategory.NEUTRAL, 0.5F,
                1.0F
        );
    }
}
