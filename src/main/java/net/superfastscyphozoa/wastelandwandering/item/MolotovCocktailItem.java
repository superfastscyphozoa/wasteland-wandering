package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.entity.projectile.MolotovCocktailEntity;
import org.jetbrains.annotations.NotNull;

public class MolotovCocktailItem extends AbstractThrownExplosiveItem {
    public MolotovCocktailItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    protected void spawnExplosiveEntity(World world, PlayerEntity user, ItemStack itemStack) {
        MolotovCocktailEntity molotovEntity = new MolotovCocktailEntity(user, world);
        molotovEntity.setItem(itemStack);
        molotovEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(molotovEntity);
    }

    @Override
    public ProjectileEntity createEntity(World world, @NotNull Position pos, ItemStack stack, Direction direction) {
        MolotovCocktailEntity molotovEntity = new MolotovCocktailEntity(pos.getX(), pos.getY(), pos.getZ(), world);
        molotovEntity.setItem(stack);
        return molotovEntity;
    }
}
