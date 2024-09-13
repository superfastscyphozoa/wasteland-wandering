package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterEntities;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class OilEntity extends ThrownItemEntity {
    public OilEntity(EntityType<? extends OilEntity> entityType, World world) {
        super(entityType, world);
    }

    public OilEntity(World world, LivingEntity livingEntity) {
        super(RegisterEntities.OIL_PROJECTILE, livingEntity, world);
    }

    public OilEntity(World world, double x, double y, double z) {
        super(RegisterEntities.OIL_PROJECTILE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return RegisterItems.OIL;
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return !itemStack.isEmpty() && !itemStack.isOf(this.getDefaultItem())
                ? new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack)
                : ParticleTypes.SMOKE;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for (int i = 0; i < 8; i++) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i;
        if (entity instanceof BlazeEntity || entity instanceof MagmaCubeEntity){
            i = 5;
        } else {
            i = 0;
        }
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float)i);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }
}
