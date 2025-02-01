package net.superfastscyphozoa.wastelandwandering.util;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class WawaFoodComponents {
    public static final FoodComponent MUTFRUIT = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();

    public static final FoodComponent ALCOHOL = new FoodComponent.Builder()
            .nutrition(1).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0), 0.5F)
            .build();

    public static final FoodComponent BLOOD_PACK = new FoodComponent.Builder()
            .nutrition(1).saturationModifier(0.4F)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 150, 0), 0.7F)
            .build();
}
