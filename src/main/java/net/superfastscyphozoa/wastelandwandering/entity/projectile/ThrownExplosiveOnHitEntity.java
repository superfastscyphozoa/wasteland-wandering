package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public abstract class ThrownExplosiveOnHitEntity extends ThrownItemEntity {
    public ThrownExplosiveOnHitEntity(EntityType<? extends ThrownExplosiveOnHitEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownExplosiveOnHitEntity(EntityType<? extends ThrownExplosiveOnHitEntity> projectileEntity, PlayerEntity user, World world) {
        super(projectileEntity, user, world);
    }

    public ThrownExplosiveOnHitEntity(EntityType<? extends ThrownExplosiveOnHitEntity> projectileEntity, double x, double y, double z, World world) {
        super(projectileEntity, x, y, z, world);
    }

    protected boolean hasFlyingParticleEffects(){
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {

            if (hasFlyingParticleEffects()) {
                flyingParticleEffects();
            }
        }
    }

    protected float hitDamage(){
        return 0F;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        float damage = hitDamage();

        if (!this.getWorld().isClient) {

            Entity entity = entityHitResult.getEntity();
            entity.damage(this.getDamageSources().thrown(this, this.getOwner()), damage);

            hitEffects(entity);
        }
    }

    protected void hitEffects(Entity entity){}

    protected boolean canExplodeInWater(){
        return true;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {

            if (!canExplodeInWater()){
                if (!this.isTouchingWater()) {
                    explode();
                }
            } else {
                explode();
            }

            onCollisionEffects();

            this.discard();
        }
    }

    //particles

    protected void flyingParticleEffects() {}

    protected void onCollisionEffects() {}

    //explosion

    protected void explode() {}
}
