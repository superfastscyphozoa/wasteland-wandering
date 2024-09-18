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
import net.superfastscyphozoa.wastelandwandering.entity.projectile.OilEntity;
import org.jetbrains.annotations.NotNull;

public class OilItem extends Item implements ProjectileItem {
    public OilItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
            OilEntity oilEntity = new OilEntity(world, user);
            oilEntity.setItem(itemStack);
            oilEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(oilEntity);

            playUseSounds(world, user);
        }

        user.getItemCooldownManager().set(this, 10);

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);

        return TypedActionResult.success(itemStack, world.isClient());
    }

    private void playUseSounds(World world, PlayerEntity user){
        world.playSound(
                null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ENTITY_SPLASH_POTION_THROW,
                SoundCategory.NEUTRAL, 0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        world.playSound(
                null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.BLOCK_HONEY_BLOCK_PLACE,
                SoundCategory.NEUTRAL, 0.5F,
                1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );
    }

    @Override
    public ProjectileEntity createEntity(World world, @NotNull Position pos, ItemStack stack, Direction direction) {
        OilEntity oilEntity = new OilEntity(world, pos.getX(), pos.getY(), pos.getZ());
        oilEntity.setItem(stack);
        return oilEntity;
    }
}
