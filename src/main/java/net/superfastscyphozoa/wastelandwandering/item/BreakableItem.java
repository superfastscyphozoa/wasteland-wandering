package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public interface BreakableItem {

    //break item
    ItemStack itemToReturnAfterBreak();

    default void itemBreak(ItemStack stack, LivingEntity user){

        stack.decrementUnlessCreative(1, user);

        if (user instanceof PlayerEntity playerEntity && !playerEntity.isInCreativeMode()) {
            if (!user.getWorld().isClient){
                playBreakSounds(user);
            }
        }

        if (itemToReturnAfterBreak() != null) {
            ItemStack brokenItem = new ItemStack(itemToReturnAfterBreak().getItem());

            if (user instanceof PlayerEntity playerEntity && !playerEntity.isInCreativeMode()) {

                if (stack.isEmpty()) {
                    user.setStackInHand(user.getActiveHand(), brokenItem);
                } else {

                    if (!playerEntity.getInventory().insertStack(brokenItem)) {
                        playerEntity.dropItem(brokenItem, false);
                    }
                }
            }
        }
    }

    //material
    BreakableItem.Material getMaterialType();

    enum Material {
        GLASS,
        WOOD
    }

    //sounds
    default SoundEvent getSoundFromMaterial() {
        return switch (this.getMaterialType()) {
            case GLASS -> SoundEvents.BLOCK_GLASS_BREAK;
            case WOOD -> SoundEvents.BLOCK_WOOD_BREAK;
        };
    }

    default void playBreakSounds(LivingEntity entity){
        if (getMaterialType() != null) {
            entity.getWorld().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                    getSoundFromMaterial(),
                    SoundCategory.NEUTRAL,
                    0.7F,
                    0.9F / (entity.getWorld().getRandom().nextFloat() * 0.4F + 0.8F)
            );
        }
    }
}
