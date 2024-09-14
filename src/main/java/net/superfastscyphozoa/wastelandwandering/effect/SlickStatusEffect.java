package net.superfastscyphozoa.wastelandwandering.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterStatusEffects;

public class SlickStatusEffect extends StatusEffect {
    public SlickStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        boolean bl = entity.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);

        int i;
        boolean fieryMob;
        if (entity instanceof BlazeEntity || entity instanceof MagmaCubeEntity || entity.isOnFire()) {
            if (entity instanceof PlayerEntity) {
                i = 5;
            } else {
                i = 25;
            }
            fieryMob = true;
        } else {
            i = 0;
            fieryMob = false;
        }

        if (!entity.getWorld().isClient) {

            entity.damage(entity.getDamageSources().generic(), (float) i);

            if (fieryMob) {

                entity.getWorld().createExplosion(
                        entity,
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        2.5F,
                        bl,
                        World.ExplosionSourceType.MOB
                );

                entity.removeStatusEffect(RegisterStatusEffects.SLICK);

            }
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
