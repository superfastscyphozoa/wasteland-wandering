package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.superfastscyphozoa.wastelandwandering.particle.WastelandWanderingParticles;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterEntities;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

import java.util.Optional;
import java.util.function.Function;

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

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {

        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        int i;
        boolean flameMob;
        if (entity instanceof BlazeEntity || entity instanceof MagmaCubeEntity || entity.isOnFire()){
            i = 25;
            flameMob = true;
        } else {
            i = 0;
            flameMob = false;
        }

        if (!this.getWorld().isClient) {

            LivingEntity livingEntity2 = this.getOwner() instanceof LivingEntity livingEntity ? livingEntity : null;
            if (livingEntity2 != null) {
                livingEntity2.onAttacking(entity);
            }

            DamageSource damageSource = this.getDamageSources().thrown(this, livingEntity2);
            if (entity.damage(damageSource, (float)i) && entity instanceof LivingEntity livingEntity3) {
                EnchantmentHelper.onTargetDamaged((ServerWorld)this.getWorld(), livingEntity3, damageSource);
            }

            if (!flameMob){
                this.createOilSplash(this.getPos());
            } else {
                this.createExplosion(this.getPos());
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getWorld().isClient) {
            Vec3i vec3i = blockHitResult.getSide().getVector();
            Vec3d vec3d = Vec3d.of(vec3i).multiply(0.25, 0.25, 0.25);
            Vec3d vec3d2 = blockHitResult.getPos().add(vec3d);

            this.createOilSplash(vec3d2);
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.discard();
        }
    }

    @Override
    public void tick() {
        if (!this.getWorld().isClient && this.getBlockY() > this.getWorld().getTopY() + 30) {
            this.createOilSplash(this.getPos());
            this.discard();
        } else {
            super.tick();
        }
    }

    // explosions

    private static final ExplosionBehavior OIL_SPLASH_BEHAVIOR = new AdvancedExplosionBehavior(
            true, false, Optional.of(0.0F),  Registries.BLOCK.getEntryList(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity()));

    protected void createOilSplash(Vec3d pos) {
        this.getWorld()
                .createExplosion(
                        this,
                        null,
                        OIL_SPLASH_BEHAVIOR,
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        1.2F,
                        false,
                        World.ExplosionSourceType.TRIGGER,
                        WastelandWanderingParticles.OIL_SPLASH,
                        WastelandWanderingParticles.OIL_DRIP,
                        SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST
                );
    }

    boolean bl = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);

    protected void createExplosion(Vec3d pos) {
        this.getWorld()
                .createExplosion(
                        this,
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        2.5F,
                        bl,
                        World.ExplosionSourceType.MOB
                );
    }
}
