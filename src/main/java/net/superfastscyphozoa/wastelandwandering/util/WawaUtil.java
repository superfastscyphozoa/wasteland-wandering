package net.superfastscyphozoa.wastelandwandering.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.superfastscyphozoa.wastelandwandering.item.IVBagItem;

public class WawaUtil {
    public static void registerWawaUtil(){
        WawaItemBlockUtil.registerItemBlockUtil();
    }

    public static void onPlayerKillOther(PlayerEntity player, LivingEntity other, ServerWorld world){
        var damageSource = other.getRecentDamageSource();

        if (damageSource != null) {
            if (!damageSource.isIn(DamageTypeTags.IS_DROWNING) && !damageSource.isIn(DamageTypeTags.IS_EXPLOSION) &&
                    !damageSource.isIn(DamageTypeTags.IS_FALL) && !damageSource.isIn(DamageTypeTags.IS_FIRE) &&
                    !damageSource.isIn(DamageTypeTags.IS_FREEZING) && !damageSource.isIn(DamageTypeTags.IS_LIGHTNING) &&
                    !damageSource.isIn(DamageTypeTags.IS_PROJECTILE)
            ) {
                IVBagItem.getIvBagToConvert(player, other);
            }
        }
    }
}
