package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class MolotovCocktailItem extends AbstractThrownExplosiveItem {
    public MolotovCocktailItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    protected void spawnExplosiveEntity(World world, PlayerEntity user, ItemStack itemStack) {
        SnowballEntity molotovEntity = new SnowballEntity(world, user);
        molotovEntity.setItem(itemStack);
        molotovEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(molotovEntity);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        SnowballEntity molotovEntity = new SnowballEntity(world, pos.getX(), pos.getY(), pos.getZ());
        molotovEntity.setItem(stack);
        return molotovEntity;
    }
}
