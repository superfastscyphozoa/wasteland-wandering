package net.superfastscyphozoa.wastelandwandering.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.effect.SlickStatusEffect;

public class RegisterStatusEffects {

    public static final RegistryEntry<StatusEffect> SLICK = registerStatusEffect("slick", new SlickStatusEffect(StatusEffectCategory.HARMFUL, 0));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(WastelandWandering.MOD_ID, name), statusEffect);
    }

}
