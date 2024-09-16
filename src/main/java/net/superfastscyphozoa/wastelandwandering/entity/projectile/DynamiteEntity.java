package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterEntities;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class DynamiteEntity extends ThrownItemEntity {

    public DynamiteEntity(EntityType<? extends DynamiteEntity> entityType, World world) {
        super(entityType, world);
    }

    public DynamiteEntity(World world, LivingEntity owner) {
        super(RegisterEntities.DYNAMITE_PROJECTILE, owner, world);
    }

    public DynamiteEntity(World world, double x, double y, double z) {
        super(RegisterEntities.DYNAMITE_PROJECTILE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return RegisterItems.DYNAMITE;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = ParticleTypes.FLASH;

            for (int i = 0; i < 8; i++) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = 0;
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float)i);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getWorld().isClient) {
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();

        this.getWorld().addParticle(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 0.8, this.getZ(), 0.0, 0.0, 0.0);
        this.getWorld().addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
    }

    @Override
    protected double getGravity() {
        return 0.05;
    }
}
