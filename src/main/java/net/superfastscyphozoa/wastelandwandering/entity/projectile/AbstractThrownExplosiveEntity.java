package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.Optional;

public abstract class AbstractThrownExplosiveEntity extends AbstractThrownPhysicsEntity {
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

    protected int fuse = 80;
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
            if(!fuseSoundPlayed) {
                if (this.getPos().equals(new Vec3d(this.prevX, this.prevY, this.prevZ))) {
                    playFuseSounds();
                    fuseSoundPlayed = true;
                }
            }
        }
    }

    protected void playFuseSounds(){
        this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.ENTITY_TNT_PRIMED,
                SoundCategory.NEUTRAL,
                0.7F,
                0.4F / (this.getWorld().getRandom().nextFloat() * 0.4F + 0.8F)
        );
    }

    protected static final ExplosionBehavior EXPLOSION_BEHAVIOR = new AdvancedExplosionBehavior(
            false, true, Optional.of(1.22F), Optional.empty());

    protected void explode() {
        this.getWorld()
                .createExplosion(
                        this,
                        Explosion.createDamageSource(this.getWorld(), this),
                        EXPLOSION_BEHAVIOR,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        2.75F,
                        false,
                        World.ExplosionSourceType.TNT
                );
    }

    protected void fuseParticleEffects(){
        if (!this.isTouchingWater()) {
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.6, this.getZ(), 0.0, 0.0, 0.0);
            this.getWorld().addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
        } else {
            this.getWorld().addParticle(ParticleTypes.BUBBLE, this.getX(), this.getY() + 0.4, this.getZ(), 0.0, 0.0, 0.0);
        }
    }
}
