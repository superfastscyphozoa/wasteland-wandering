package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterEntities;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

import java.util.Optional;

public class MolotovCocktailEntity extends ThrownExplosiveOnHitEntity {
    public MolotovCocktailEntity(EntityType<? extends ThrownExplosiveOnHitEntity> entityType, World world) {
        super(entityType, world);
    }

    public MolotovCocktailEntity(PlayerEntity user, World world) {
        super(RegisterEntities.MOLOTOV_COCKTAIL_PROJECTILE, user, world);
    }

    public MolotovCocktailEntity(double x, double y, double z, World world) {
        super(RegisterEntities.MOLOTOV_COCKTAIL_PROJECTILE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return RegisterItems.MOLOTOV_COCKTAIL;
    }

    // on hit entity

    @Override
    protected float hitDamage() {
        return 1F;
    }

    @Override
    protected void hitEffects(Entity entity) {
        if (!entity.isTouchingWater()) {
            entity.setOnFireFor(5f);
        }
    }

    //particles

    @Override
    protected boolean hasFlyingParticleEffects() {
        return true;
    }

    @Override
    protected void flyingParticleEffects() {
        if (!this.isTouchingWater()) {
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.6, this.getZ(), 0.0, 0.0, 0.0);
            this.getWorld().addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
        } else {
            this.getWorld().addParticle(ParticleTypes.BUBBLE, this.getX(), this.getY() + 0.4, this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onCollisionParticleEffects() {
        this.getWorld().syncWorldEvent(WorldEvents.SPLASH_POTION_SPLASHED, this.getBlockPos(), -13083194);
    }

    //explosion

    @Override
    protected boolean canExplodeInWater() {
        return false;
    }

    private static final ExplosionBehavior FIRE_EXPLOSION_BEHAVIOR = new AdvancedExplosionBehavior(
            true, true, Optional.of(0.0F), Optional.empty());

    @Override
    protected void explode() {
        this.getWorld().createExplosion(
                this,
                Explosion.createDamageSource(this.getWorld(), this),
                FIRE_EXPLOSION_BEHAVIOR,
                this.getX(), this.getY(), this.getZ(),
                2.25F,
                true,
                World.ExplosionSourceType.NONE,
                ParticleTypes.FLASH,
                ParticleTypes.FLASH,
                SoundEvents.ENTITY_GENERIC_EXPLODE
        );
    }
}
