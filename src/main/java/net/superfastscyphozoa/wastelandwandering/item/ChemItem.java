package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class ChemItem extends Item {
    public ChemItem(Settings settings) {
        super(settings);
    }

    private void onUseChem(World world, PlayerEntity user) {
        if (!world.isClient) {
            playUseSounds(world, user);
        }

        if (chemCooldown()) {
            user.getItemCooldownManager().set(this, chemCooldownLength());
        }
        chemEffects(world, user);
    }

    protected void chemEffects(World world, PlayerEntity user) {}

    // use chem

    @Override
    public ItemStack finishUsing(ItemStack itemStack, World world, LivingEntity user) {
        if (isEatenOrDrank(itemStack)){
            super.finishUsing(itemStack, world, user);

            if (user instanceof PlayerEntity playerEntity) {
                onUseChem(world, playerEntity);
            }

            if (user instanceof ServerPlayerEntity serverPlayerEntity) {
                Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, itemStack);
                serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }

            return itemStack;
        } else {
            return super.finishUsing(itemStack, world, user);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if(chemUseConditionsUnlessCreative(world, user, hand)) {
            if (isEatenOrDrank(itemStack)){
                return ItemUsage.consumeHeldItem(world, user, hand);
            } else {
                onUseChem(world, user);

                itemStack.decrementUnlessCreative(1, user);
                user.incrementStat(Stats.USED.getOrCreateStat(this));

                return TypedActionResult.success(itemStack, world.isClient());
            }
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    protected boolean chemUseConditions(World world, PlayerEntity user, Hand hand) {
        return true;
    }

    private boolean chemUseConditionsUnlessCreative(World world, PlayerEntity user, Hand hand) {
        if (!user.isInCreativeMode()) {
            return chemUseConditions(world, user, hand);
        } else {
            return true;
        }
    }

    // cooldown

    protected int chemCooldownLength(){
        return 0;
    }

    private boolean chemCooldown() {
        return chemCooldownLength() > 0;
    }

    //sound

    protected void playUseSounds(World world, PlayerEntity user) {}

    // check chem type

    private boolean isEatenOrDrank(ItemStack stack) {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        return foodComponent != null;
    }
}
