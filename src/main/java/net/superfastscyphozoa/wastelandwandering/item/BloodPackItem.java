package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;
import net.superfastscyphozoa.wastelandwandering.util.WawaFoodComponents;

public class BloodPackItem extends ChemItem {
    public BloodPackItem(Settings settings) {
        super(settings.food(WawaFoodComponents.BLOOD_PACK));
    }

    @Override
    protected void chemEffects(World world, PlayerEntity user) {
        if (!world.isClient) {
            user.heal(healAmount(user));
        }
    }

    private float healAmount(PlayerEntity user) {
        if (this.asItem() == RegisterItems.BLOOD_PACK) {
            if (user.getHealth() <= (user.getMaxHealth() / 4)) {
                return user.getMaxHealth() / 2;
            } else {
                return user.getMaxHealth() / 4;
            }
        } else {
            return user.getMaxHealth() / 1.5F;
        }
    }

    @Override
    protected ItemStack itemToReturnAfterChemUse() {
        return RegisterItems.IV_BAG.getDefaultStack();
    }

    @Override
    protected boolean chemUseConditions(World world, PlayerEntity user, Hand hand) {
        return (user.getHealth() != user.getMaxHealth());
    }

    //drink
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }
}
