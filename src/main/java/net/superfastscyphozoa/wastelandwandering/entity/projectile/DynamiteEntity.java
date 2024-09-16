package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
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
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 1f);
    }

    @Override
    public void tick() {

        this.tickPortalTeleportation();

        this.applyGravity();

        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));

        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }

        this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.6, this.getZ(), 0.0, 0.0, 0.0);
        this.getWorld().addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
    }
}
