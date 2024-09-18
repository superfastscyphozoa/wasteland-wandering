package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.registry.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {

            ParticleEffect particleEffect = RegisterParticles.OIL_SPLASH;

            this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        Entity entity = entityHitResult.getEntity();

        int i;
        boolean fieryMob;
        if (entity instanceof BlazeEntity || entity instanceof MagmaCubeEntity || entity.isOnFire()){
            if (entity instanceof PlayerEntity || entity instanceof WitherEntity || entity instanceof EnderDragonEntity) {
                i = 8;
            } else {
                i = 25;
            }
            fieryMob = true;
        } else {
            i = 0;
            fieryMob = false;
        }

        if (fieryMob) {
            entity.damage(entity.getDamageSources().generic(), (float) i);
            if (!this.getWorld().isClient) {
                this.explode(this.getPos());
            }
        } else {
            entity.damage(entity.getDamageSources().thrown(this, this.getOwner()), (float) i);
        }

    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        if (!this.getWorld().isClient) {

            this.createOilSplash(hitResult.getType() == HitResult.Type.ENTITY ? ((EntityHitResult)hitResult).getEntity() : null);

            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.getWorld().playSound(
                    null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.BLOCK_HONEY_BLOCK_PLACE,
                    SoundCategory.NEUTRAL, 1.0F,
                    1.0F
            );

            this.discard();
        }
    }

    //create asphalt

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        if (!this.getWorld().isClient){
            replaceConcretePowderOnSplash(blockHitResult);
        }
    }

    protected void replaceConcretePowderOnSplash(@NotNull BlockHitResult blockHitResult){
        BlockPos blockPos = blockHitResult.getBlockPos();
        BlockState blockState = this.getWorld().getBlockState(blockPos);

        if (blockState.isIn(BlockTags.CONCRETE_POWDER)){

            for (int i = 0; i < 64; i++){
                BlockPos blockPos2 = blockPos;

                for (int j = 0; j < i / 16; j++) {

                    blockPos2 = blockPos2.add(
                            random.nextInt(3) - 1,
                            (random.nextInt(3) - 1) * random.nextInt(3) / 2,
                            random.nextInt(3) - 1
                    );

                    if (this.getWorld().getBlockState(blockPos2).isIn(BlockTags.CONCRETE_POWDER)) {
                        this.getWorld().setBlockState(blockPos2, Blocks.BLACK_CONCRETE.getDefaultState(), Block.NOTIFY_LISTENERS);
                    }
                }
            }
        }
    }

    // apply status effect

    private void createOilSplash(@Nullable Entity entity) {
        Box box = this.getBoundingBox().expand(4.0, 2.0, 4.0);
        List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);

        if (!list.isEmpty()) {
            Entity entity2 = this.getEffectCause();

            for (LivingEntity livingEntity : list) {
                if (livingEntity.isAffectedBySplashPotions()) {
                    double d = this.squaredDistanceTo(livingEntity);
                    if (d < 16.0) {

                        StatusEffectInstance statusEffectInstance = getStatusEffectInstance(entity, livingEntity, d);

                        if (!statusEffectInstance.isDurationBelow(20)) {
                            livingEntity.addStatusEffect(statusEffectInstance, entity2);
                        }
                    }
                }
            }
        }
    }

    private static @NotNull StatusEffectInstance getStatusEffectInstance(@Nullable Entity entity, LivingEntity livingEntity, double d) {
        double e;
        if (livingEntity == entity) { e = 1.0; }
        else { e = 1.0 - Math.sqrt(d) / 4.0; }

        int i = ((int)(e * (double)500 + 0.5));

        return new StatusEffectInstance(RegisterStatusEffects.SLICK, i, 0, false, true);
    }

    // explosion

    private void explode(@NotNull Vec3d pos) {
        this.getWorld()
                .createExplosion(
                        this,
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        2F,
                        true,
                        World.ExplosionSourceType.TNT
                );
    }
}
