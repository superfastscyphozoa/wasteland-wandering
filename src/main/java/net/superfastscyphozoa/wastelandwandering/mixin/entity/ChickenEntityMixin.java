package net.superfastscyphozoa.wastelandwandering.mixin.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.entity.variant.ChickenVariant;
import net.superfastscyphozoa.wastelandwandering.entity.variant.ChickenVariantInterface;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChickenEntity.class)
public class ChickenEntityMixin extends PassiveEntity implements ChickenVariantInterface {

    protected ChickenEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
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

    @Override
    public void wasteland_wandering$setVariant(ChickenVariant variant) {
        this.dataTracker.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        ChickenVariant variant = Util.getRandom(ChickenVariant.values(), this.random);
        wasteland_wandering$setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        ChickenEntity baby = EntityType.CHICKEN.create(world);
        ChickenVariant variant = Util.getRandom(ChickenVariant.values(), this.random);
        assert baby != null;
        if (baby instanceof ChickenVariantInterface) {
            ((ChickenVariantInterface) baby).wasteland_wandering$setVariant(variant);
            return baby;
        } else {
            return null;
        }
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
