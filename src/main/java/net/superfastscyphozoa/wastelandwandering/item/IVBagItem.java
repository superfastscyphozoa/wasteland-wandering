package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;
import net.superfastscyphozoa.wastelandwandering.util.WawaTags;
import org.jetbrains.annotations.Nullable;

public class IVBagItem extends Item {
    public IVBagItem(Settings settings) {
        super(settings);
    }

    public static void getIvBagToConvert(PlayerEntity player, LivingEntity killedEntity){
        if (player.getStackInHand(Hand.MAIN_HAND).isOf(RegisterItems.IV_BAG)){
            IVBagItem.convertToBloodPack(player, killedEntity, Hand.MAIN_HAND);
        } else if (player.getStackInHand(Hand.OFF_HAND).isOf(RegisterItems.IV_BAG)){
            IVBagItem.convertToBloodPack(player, killedEntity, Hand.OFF_HAND);
        }
    }

    private static void convertToBloodPack(PlayerEntity player, LivingEntity killedEntity, Hand hand){
        ItemStack ivBagStack = player.getStackInHand(hand);

        ItemStack bloodPack = getBloodType(killedEntity);

        if (bloodPack != null) {
            //decrement iv bag item
            ivBagStack.decrement(1);

            //give converted blood pack to player
            if (ivBagStack.isEmpty()) {
                player.setStackInHand(hand, bloodPack);
            } else {

                if (!player.getInventory().insertStack(bloodPack)) {
                    player.dropItem(bloodPack, false);
                }
            }
        }
    }

    private static @Nullable ItemStack getBloodType(LivingEntity killedEntity) {
        ItemStack bloodPack = new ItemStack(RegisterItems.BLOOD_PACK);
        ItemStack glowingBloodPack = new ItemStack(RegisterItems.GLOWING_BLOOD_PACK);
        ItemStack bloodType;

        //check entity blood type
        if(!bloodlessEntity(killedEntity)){
            if (!glowingBloodEntity(killedEntity)){
                bloodType = bloodPack;
            } else {
                bloodType = glowingBloodPack;
            }
        } else {
            bloodType = null;
        }

        return bloodType;
    }

    private static boolean bloodlessEntity(LivingEntity killedEntity) {
        return killedEntity.getType().isIn(WawaTags.EntityTypes.BLOODLESS);
    }

    private static boolean glowingBloodEntity(LivingEntity killedEntity) {
        return killedEntity.getType().isIn(WawaTags.EntityTypes.HAS_GLOWING_BLOOD);
    }
}
