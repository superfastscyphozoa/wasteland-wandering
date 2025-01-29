package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class StimpakItem extends Item {

    public StimpakItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if ((user.getHealth() != user.getMaxHealth()) || (user.getHungerManager().isNotFull())) {
            if (!world.isClient) {

                user.setHealth(user.getMaxHealth());
                user.getHungerManager().add(20, 1.2F);

                itemStack.decrement(1);

                playUseSounds(world, user);
            }

            user.getItemCooldownManager().set(this, 25);

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return TypedActionResult.success(itemStack, world.isClient());
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    private void playUseSounds(World world, PlayerEntity user){
        world.playSound(
                null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                SoundCategory.NEUTRAL, 0.5F,
                1.0F
        );
    }
}
