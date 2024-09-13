package net.superfastscyphozoa.wastelandwandering.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;

public class WastelandWanderingParticles {
    public static final SimpleParticleType OIL_SPLASH = FabricParticleTypes.simple();
    public static final SimpleParticleType OIL_DRIP = FabricParticleTypes.simple();

    public static void registerParticles(){
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(WastelandWandering.MOD_ID, "oil_splash"),
                OIL_SPLASH);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(WastelandWandering.MOD_ID, "oil_drip"),
                OIL_DRIP);
    }
}