package net.superfastscyphozoa.wastelandwandering.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;

public class RegisterParticles {
    public static final SimpleParticleType OIL_SPLASH = FabricParticleTypes.simple();
    public static final SimpleParticleType OIL_DRIP = FabricParticleTypes.simple();
    public static final SimpleParticleType OIL_DRIP_LANDING = FabricParticleTypes.simple();

    public static void registerWawaParticles(){
        WastelandWandering.LOGGER.info("Registering Particles for " + WastelandWandering.MOD_ID);

        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(WastelandWandering.MOD_ID, "oil_splash"),
                OIL_SPLASH);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(WastelandWandering.MOD_ID, "oil_drip"),
                OIL_DRIP);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(WastelandWandering.MOD_ID, "oil_drip_landing"),
                OIL_DRIP_LANDING);
    }
}
