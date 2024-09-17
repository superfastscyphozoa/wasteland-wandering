package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public abstract class AbstractThrownExplosiveEntity extends ThrownItemEntity {

    public AbstractThrownExplosiveEntity(EntityType<? extends AbstractThrownExplosiveEntity> entityType, World world) {
        super(entityType, world);
    }

    public AbstractThrownExplosiveEntity(EntityType<? extends AbstractThrownExplosiveEntity> projectileEntity, PlayerEntity owner, World world) {
        super(projectileEntity, owner, world);
    }

    public AbstractThrownExplosiveEntity(EntityType<? extends AbstractThrownExplosiveEntity> projectileEntity, double x, double y, double z, World world) {
        super(projectileEntity, x, y, z, world);
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    protected boolean canHit(Entity entity) {
        if (entity instanceof AbstractThrownExplosiveEntity) {
            return false;
        } else {
            return super.canHit(entity);
        }
    }

    private int hits = 0;

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        if (hits == 0){
            entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 1f);
            this.setVelocity(this.getVelocity().multiply(0.2, 0.6, 0.2));
            hits++;
        }
    }

    @Override
    public void tick() {
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        if (hitResult.getType() != HitResult.Type.MISS) {
            this.hitOrDeflect(hitResult);
        }
        this.checkBlockCollision();

        this.tickPortalTeleportation();

        float h;
        this.updateWaterState();
        if (!this.isTouchingWater()) {
            h = 0.98F;
        } else {
            h = 0.8F;
        }

        explosiveParticleEffects();

        this.applyGravity();
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(h));

        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }
    }

    protected void explosiveParticleEffects(){
        if (!this.isTouchingWater()) {
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.6, this.getZ(), 0.0, 0.0, 0.0);
            this.getWorld().addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
        } else {
            this.getWorld().addParticle(ParticleTypes.BUBBLE, this.getX(), this.getY() + 0.4, this.getZ(), 0.0, 0.0, 0.0);
        }
    }
}
