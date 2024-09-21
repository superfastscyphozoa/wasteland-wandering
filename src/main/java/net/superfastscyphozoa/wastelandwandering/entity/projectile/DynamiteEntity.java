package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterEntities;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

import java.util.Optional;

public class DynamiteEntity extends ThrownExplosiveFuseEntity {

    public DynamiteEntity(EntityType<? extends DynamiteEntity> entityType, World world) {
        super(entityType, world);
    }

    public DynamiteEntity(PlayerEntity owner, World world) {
        super(RegisterEntities.DYNAMITE_PROJECTILE, owner, world);
    }

    public DynamiteEntity(double x, double y, double z, World world) {
        super(RegisterEntities.DYNAMITE_PROJECTILE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return RegisterItems.DYNAMITE;
    }

    protected static final ExplosionBehavior EXPLOSION_BEHAVIOR = new AdvancedExplosionBehavior(
            false, true, Optional.of(1.22F), Optional.empty());

    @Override
    protected void explode() {
        this.getWorld()
                .createExplosion(
                        this,
                        Explosion.createDamageSource(this.getWorld(), this),
                        EXPLOSION_BEHAVIOR,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        3.5F,
                        false,
                        World.ExplosionSourceType.TNT
                );
    }
}
