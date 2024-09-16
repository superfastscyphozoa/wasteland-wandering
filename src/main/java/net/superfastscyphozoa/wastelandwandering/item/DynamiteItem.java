package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.entity.projectile.DynamiteEntity;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class DynamiteItem extends Item implements ProjectileItem {
    public DynamiteItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack mainHandStack = user.getMainHandStack();
        ItemStack offHandStack = user.getOffHandStack();

        if (mainHandStack.getItem() == RegisterItems.DYNAMITE){
            if (!world.isClient) {
                DynamiteEntity dynamiteEntity = new DynamiteEntity(world, user);
                dynamiteEntity.setItem(mainHandStack);
                dynamiteEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(dynamiteEntity);
            }

            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_SPLASH_POTION_THROW,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );

            user.getItemCooldownManager().set(this, 10);

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            mainHandStack.decrementUnlessCreative(1, user);

            return TypedActionResult.success(mainHandStack, world.isClient());
        }
        else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        DynamiteEntity dynamiteEntity = new DynamiteEntity(world, pos.getX(), pos.getY(), pos.getZ());
        dynamiteEntity.setItem(stack);
        return dynamiteEntity;
    }
}
