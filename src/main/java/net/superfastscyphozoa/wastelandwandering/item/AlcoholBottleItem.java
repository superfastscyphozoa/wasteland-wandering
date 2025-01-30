package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class AlcoholBottleItem extends ChemItem {

    public AlcoholBottleItem(Settings settings) {
        super(settings);
    }

    //drink

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
    protected ItemStack itemToReturnAfterChemUse() {
        return Items.GLASS_BOTTLE.getDefaultStack();
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
        bottleBreak(stack, attacker);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        bottleBreak(stack, miner);
        return true;
    }

    protected void bottleBreak(ItemStack stack, LivingEntity player){

        stack.decrementUnlessCreative(1, player);

        ItemStack brokenBottle = new ItemStack(RegisterItems.BROKEN_BOTTLE);

        if (player instanceof PlayerEntity playerEntity && !playerEntity.isInCreativeMode()) {

            if (!player.getWorld().isClient){
                playBreakSounds(player);
            }

            if (stack.isEmpty()) {

                player.setStackInHand(player.getActiveHand(), brokenBottle);
            } else {

                if (!playerEntity.getInventory().insertStack(brokenBottle)) {
                    playerEntity.dropItem(brokenBottle, false);
                }
            }
        }
    }

    //particles and sounds

    protected void playBreakSounds(LivingEntity entity){
        entity.getWorld().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.BLOCK_GLASS_BREAK,
                SoundCategory.NEUTRAL,
                0.7F,
                0.9F / (entity.getWorld().getRandom().nextFloat() * 0.4F + 0.8F)
        );
    }
}
