package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class AlcoholBottleItem extends Item {

    public AlcoholBottleItem(Settings settings) {
        super(settings);
    }


    //drink

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);

        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (user instanceof PlayerEntity playerEntity && !playerEntity.isInCreativeMode()) {
                ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
                if (!playerEntity.getInventory().insertStack(itemStack)) {
                    playerEntity.dropItem(itemStack, false);
                }
            }

            return stack;
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 40;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }


    //damage entities

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        return 3.0F;
    }


    //break bottle

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        stack.decrementUnlessCreative(1, attacker);

        // figure out how to add particles

        if (!attacker.getWorld().isClient){
           playAttackSounds(target, attacker);
        }

        // give broken bottle on hit

        ItemStack brokenBottle = new ItemStack(Items.BONE_MEAL);

        if (attacker instanceof PlayerEntity playerEntity && !playerEntity.isInCreativeMode()) {
            if (stack.isEmpty()) {

                attacker.setStackInHand(attacker.getActiveHand(), brokenBottle);
            } else {

                if (!playerEntity.getInventory().insertStack(brokenBottle)) {
                    playerEntity.dropItem(brokenBottle, false);
                }
            }
        }
    }


    //particles and sounds

    protected void playAttackSounds(LivingEntity target, LivingEntity attacker){
        attacker.getWorld().playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.BLOCK_GLASS_BREAK,
                SoundCategory.NEUTRAL,
                0.7F,
                0.9F / (attacker.getWorld().getRandom().nextFloat() * 0.4F + 0.8F)
        );
    }
}
