package net.superfastscyphozoa.wastelandwandering.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.superfastscyphozoa.wastelandwandering.util.WawaTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @WrapOperation(
            method = "attack",
            constant = @Constant(classValue = SwordItem.class)
    )
    private boolean sweepingCondition(Object object, Operation<Boolean> original) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        ItemStack stackInHand = player.getStackInHand(Hand.MAIN_HAND);

        return original.call(object) || stackInHand.isIn(WawaTags.Items.SWEEPING_WEAPON);
    }

    @WrapOperation(
            method = "damageShield",
            at = @At(value = "FIELD", target = "Lnet/minecraft/item/Items;SHIELD:Lnet/minecraft/item/Item;")
    )
    private Item damageShieldingItem(Operation<Item> original) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        ItemStack activeItem = player.getActiveItem();

        if (activeItem.isIn(WawaTags.Items.SHIELDING_ITEM)) {

            return activeItem.getItem();
        }
        else
        {
            return original.call();
        }
    }

    @WrapOperation(
            method = "disableShield",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ItemCooldownManager;set(Lnet/minecraft/item/Item;I)V")
    )
    private void disableShieldingItem(ItemCooldownManager instance, Item item, int duration, Operation<Void> original) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        ItemStack activeItem = player.getActiveItem();

        if (activeItem.isIn(WawaTags.Items.SHIELDING_ITEM)) {

            Item itemToDisable = activeItem.getItem();
            original.call(instance, itemToDisable, duration);
        }
        else
        {
            original.call(instance, item, duration);
        }
    }
}
