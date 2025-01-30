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

    private void onUseChem(ItemStack itemStack, World world, PlayerEntity user) {
        //override playUseSounds to add sounds on use
        if (!world.isClient) {
            playUseSounds(world, user);
        }

        //override chemCooldownLength to a number higher than zero to enable a cooldown
        if (chemCooldownLength() > 0) {
            user.getItemCooldownManager().set(this, chemCooldownLength());
        }

        //override itemToReturnAfterChemUse to get an item after use
        if (itemToReturnAfterChemUse() != null){
            returnItemAfterChemUse(itemStack, user);
        }

        chemEffects(world, user);
    }

    //the actual effect of the chem, override with your desired effects
    protected void chemEffects(World world, PlayerEntity user) {}

    // use chem
    @Override
    public ItemStack finishUsing(ItemStack itemStack, World world, LivingEntity user) {
        if (isEatenOrDrank(itemStack)){
            super.finishUsing(itemStack, world, user);

            if (user instanceof PlayerEntity playerEntity) {
                onUseChem(itemStack, world, playerEntity);
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
                onUseChem(itemStack, world, user);

                itemStack.decrementUnlessCreative(1, user);
                user.incrementStat(Stats.USED.getOrCreateStat(this));

                return TypedActionResult.success(itemStack, world.isClient());
            }
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    // chem use conditions

    //override chemUseConditions to add conditions - you will be unable to use the chem if the conditions are not met
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

    // return item after chem use
    protected ItemStack itemToReturnAfterChemUse(){
        return null;
    }

    private void returnItemAfterChemUse(ItemStack itemStack, PlayerEntity user) {
        ItemStack returnedItem = itemToReturnAfterChemUse();

        if (itemStack.isEmpty()) {
            user.setStackInHand(user.getActiveHand(), returnedItem);
        } else {
            if (!user.isInCreativeMode()) {
                if (!user.getInventory().insertStack(returnedItem)) {
                    user.dropItem(returnedItem, false);
                }
            }
        }
    }

    // cooldown
    protected int chemCooldownLength(){
        return 0;
    }

    //sound
    protected void playUseSounds(World world, PlayerEntity user) {}

    // check chem type
    private boolean isEatenOrDrank(ItemStack stack) {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        return foodComponent != null;
    }
}
