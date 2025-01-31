package net.superfastscyphozoa.wastelandwandering.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class AlcoholBottleItem extends ChemItem implements BreakableItem {

    public AlcoholBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemStack itemToReturnAfterChemUse() {
        return Items.GLASS_BOTTLE.getDefaultStack();
    }

    @Override
    public ItemStack itemToReturnAfterBreak() {
        return RegisterItems.BROKEN_BOTTLE.getDefaultStack();
    }

    @Override
    public Material getMaterialType() {
        return Material.GLASS;
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
        itemBreak(stack, attacker);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        itemBreak(stack, miner);
        return true;
    }
}
