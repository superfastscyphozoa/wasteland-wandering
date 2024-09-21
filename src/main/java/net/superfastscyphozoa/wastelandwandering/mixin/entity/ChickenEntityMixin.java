package net.superfastscyphozoa.wastelandwandering.mixin.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.entity.variant.ChickenVariant;
import net.superfastscyphozoa.wastelandwandering.entity.variant.ChickenVariantGetterInterface;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChickenEntity.class)
public class ChickenEntityMixin extends MobEntity implements ChickenVariantGetterInterface {

    protected ChickenEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    //variant

    @Unique
    private static final TrackedData<Integer> VARIANT =
            DataTracker.registerData(ChickenEntityMixin.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }

    @Unique
    private int getTypeVariant(){
        return this.dataTracker.get(VARIANT);
    }

    @Override
    public ChickenVariant wasteland_wandering$getVariant() {
        return ChickenVariant.byId(this.getTypeVariant() & 255);
    }

    @Unique
    public void setVariant(ChickenVariant variant) {
        this.dataTracker.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        ChickenVariant variant = Util.getRandom(ChickenVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(VARIANT, nbt.getInt("Variant"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }
}
