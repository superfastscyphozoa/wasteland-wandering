package net.superfastscyphozoa.wastelandwandering.mixin.item.weapon;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.superfastscyphozoa.wastelandwandering.util.WawaTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntityRenderer.class)
public class ArmPoseMixin {
    @WrapOperation(
            method = "getArmPose",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z")
    )
    private static boolean arm1(ItemStack stack, Item item, Operation<Boolean> original) {

        return original.call(stack, item) || stack.isIn(WawaTags.Items.TWO_HANDED_WEAPON);
    }

    @WrapOperation(
            method = "getArmPose",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;isCharged(Lnet/minecraft/item/ItemStack;)Z")
    )
    private static boolean arm2(ItemStack stack, Operation<Boolean> original) {

        if (stack.isOf(Items.CROSSBOW)){
            return original.call(stack);
        } else {
            return true;
        }
    }
}
