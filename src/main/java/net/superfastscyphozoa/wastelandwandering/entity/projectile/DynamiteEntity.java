package net.superfastscyphozoa.wastelandwandering.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterEntities;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class DynamiteEntity extends AbstractThrownExplosiveEntity {

    public DynamiteEntity(EntityType<? extends DynamiteEntity> entityType, World world) {
        super(entityType, world);
    }

    public DynamiteEntity(World world, PlayerEntity owner) {
        super(RegisterEntities.DYNAMITE_PROJECTILE, owner, world);
    }

    public DynamiteEntity(World world, double x, double y, double z) {
        super(RegisterEntities.DYNAMITE_PROJECTILE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return RegisterItems.DYNAMITE;
    }
}
