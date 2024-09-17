package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ProjectileItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.util.WawaTags;

import java.util.List;

public abstract class AbstractThrownExplosiveItem extends Item implements ProjectileItem {
    public AbstractThrownExplosiveItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack mainHandStack = user.getMainHandStack();
        ItemStack offHandStack = user.getOffHandStack();
        ItemStack handStack = user.getStackInHand(hand);

        boolean requiresLighter = handStack.isIn(WawaTags.Items.THROWN_EXPLOSIVE_NEEDS_LIGHTER);
        boolean usingLighter = offHandStack.isIn(WawaTags.Items.FUSE_LIGHTER);

        if (requiresLighter){
            if (usingLighter){
                if (!world.isClient) {
                    spawnExplosiveEntity(world, user, mainHandStack);
                }

                damageLighter(user, hand);
                playUseSounds(world, user);

                user.getItemCooldownManager().set(this, 10);

                user.incrementStat(Stats.USED.getOrCreateStat(this));
                mainHandStack.decrementUnlessCreative(1, user);

                return TypedActionResult.success(mainHandStack, world.isClient());
            }
            else {
                return TypedActionResult.fail(user.getStackInHand(hand));
            }
        }
        else {
            if (!world.isClient) {
                spawnExplosiveEntity(world, user, handStack);
            }

            playUseSounds(world, user);

            user.getItemCooldownManager().set(this, 10);

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            handStack.decrementUnlessCreative(1, user);

            return TypedActionResult.success(handStack, world.isClient());
        }
    }

    protected void spawnExplosiveEntity(World world, PlayerEntity user, ItemStack itemStack) {}

    protected void damageLighter(PlayerEntity user, Hand hand) {
        if (user.getOffHandStack().getItem() == Items.FLINT_AND_STEEL){
            user.getOffHandStack().damage(1, user, LivingEntity.getSlotForHand(hand));
        }
        else if (user.getOffHandStack().getItem() == Items.FIRE_CHARGE){
            user.getOffHandStack().decrement(1);
        }
    }

    protected void playUseSounds(World world, PlayerEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ENTITY_SPLASH_POTION_THROW,
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (user.getOffHandStack().getItem() == Items.FIRE_CHARGE){
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_FIRECHARGE_USE,
                    SoundCategory.NEUTRAL,
                    0.3F,
                    world.getRandom().nextFloat() * 0.4F + 0.8F
            );
        } else {
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_FLINTANDSTEEL_USE,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    world.getRandom().nextFloat() * 0.4F + 0.8F
            );
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.isIn(WawaTags.Items.THROWN_EXPLOSIVE_NEEDS_LIGHTER)){
            tooltip.add(Text.translatable("tooltip.wasteland-wandering.fuse_explosive").formatted(Formatting.GRAY));
            super.appendTooltip(stack, context, tooltip, type);
        } else {
            super.appendTooltip(stack, context, tooltip, type);
        }
    }
}
