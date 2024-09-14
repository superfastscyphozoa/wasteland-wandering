package net.superfastscyphozoa.wastelandwandering.client.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.superfastscyphozoa.wastelandwandering.particle.OilDripParticle;
import net.superfastscyphozoa.wastelandwandering.particle.OilSplashParticle;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterParticles;

public class WawaParticleFactories {
    public static void registerParticleFactories(){
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.OIL_SPLASH, OilSplashParticle.OilSplashFactory::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.OIL_DRIP, OilDripParticle.OilDripFactory::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.OIL_DRIP_LANDING, OilDripParticle.OilDripLandingFactory::new);
    }
}
