package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public abstract class ThrownExplosiveFuseEntity extends ThrownPhysicsEntity {
    public ThrownExplosiveFuseEntity(EntityType<? extends ThrownExplosiveFuseEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownExplosiveFuseEntity(EntityType<? extends ThrownExplosiveFuseEntity> projectileEntity, PlayerEntity owner, World world) {
        super(projectileEntity, owner, world);
    }

    public ThrownExplosiveFuseEntity(EntityType<? extends ThrownExplosiveFuseEntity> projectileEntity, double x, double y, double z, World world) {
        super(projectileEntity, x, y, z, world);
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    protected int fuse = 40;
    private boolean fuseSoundPlayed = false;

    @Override
    public void tick() {
        super.tick();

        fuse--;

        if (fuse <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            if (this.getWorld().isClient) {
                fuseParticleEffects();
            }
            if (!fuseSoundPlayed) {
                if (fuse <= 25) {
                    playFuseSounds();
                    fuseSoundPlayed = true;
                }
            }
        }
    }

    protected void playFuseSounds() {
        if (!this.getWorld().isClient) {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.ENTITY_TNT_PRIMED,
                    SoundCategory.NEUTRAL,
                    0.7F,
                    0.4F / (this.getWorld().getRandom().nextFloat() * 0.4F + 0.8F)
            );
        }
    }

    protected void explode() {}

    protected void fuseParticleEffects() {
        if (!this.isTouchingWater()) {
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.6, this.getZ(), 0.0, 0.0, 0.0);
            this.getWorld().addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
        } else {
            this.getWorld().addParticle(ParticleTypes.BUBBLE, this.getX(), this.getY() + 0.4, this.getZ(), 0.0, 0.0, 0.0);
        }
    }
}
