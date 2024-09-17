package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.entity.projectile.DynamiteEntity;

public class DynamiteItem extends AbstractThrownExplosiveItem {
    public DynamiteItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    protected void spawnExplosiveEntity(World world, PlayerEntity user, ItemStack itemStack) {
        DynamiteEntity dynamiteEntity = new DynamiteEntity(world, user);
        dynamiteEntity.setItem(itemStack);
        dynamiteEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(dynamiteEntity);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        DynamiteEntity dynamiteEntity = new DynamiteEntity(world, pos.getX(), pos.getY(), pos.getZ());
        dynamiteEntity.setItem(stack);
        return dynamiteEntity;
    }
}
