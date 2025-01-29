package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class IVBagItem extends Item {
    public IVBagItem(Settings settings) {
        super(settings);
    }

    public static void convertToBloodPack(PlayerEntity player, Hand hand){
        ItemStack ivBagStack = player.getStackInHand(hand);

        ivBagStack.decrement(1);

        ItemStack bloodPack = new ItemStack(RegisterItems.BLOOD_PACK);
        ItemStack glowingBloodPack = new ItemStack(RegisterItems.GLOWING_BLOOD_PACK);

        if (ivBagStack.isEmpty()) {

            player.setStackInHand(hand, bloodPack);
        } else {

            if (!player.getInventory().insertStack(bloodPack)) {
                player.dropItem(bloodPack, false);
            }
        }

    }
}
