package net.superfastscyphozoa.wastelandwandering.mixin.item.weapon;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.superfastscyphozoa.wastelandwandering.util.WawaTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(PlayerEntity.class)
public class SweepAttackMixin {
    @WrapOperation(
            method = "attack",
            constant = @Constant(classValue = SwordItem.class)
    )
    private boolean sweepingCondition(Object object, Operation<Boolean> original) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        ItemStack stackInHand = player.getStackInHand(Hand.MAIN_HAND);

        return original.call(object) || stackInHand.isIn(WawaTags.Items.SWEEPING_WEAPON);
    }
}
