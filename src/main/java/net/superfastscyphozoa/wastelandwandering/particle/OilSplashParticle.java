package net.superfastscyphozoa.wastelandwandering.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class OilSplashParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    protected OilSplashParticle(ClientWorld clientWorld, double x, double y, double z, SpriteProvider spriteProvider) {
        super(clientWorld, x, y, z);
        this.spriteProvider = spriteProvider;
        this.setSpriteForAge(spriteProvider);
        this.maxAge = 9 + this.random.nextInt(4);
        this.scale = 1.0F;
        this.setBoundingBoxSpacing(1.0F, 1.0F);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }

    @Override
    public int getBrightness(float tint) {
        return 15728880;
    }

    @Override
    public void tick() {
        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else {
            this.setSpriteForAge(this.spriteProvider);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class OilSplashFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider splashSpriteProvider;

        public OilSplashFactory(SpriteProvider spriteProvider) {
            this.splashSpriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new OilSplashParticle(clientWorld, d, e, f, this.splashSpriteProvider);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class OilDripFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider dripSpriteProvider;

        public OilDripFactory(SpriteProvider spriteProvider) {
            this.dripSpriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            Particle particle = new OilSplashParticle(clientWorld, d, e, f, this.dripSpriteProvider);
            particle.scale(0.15F);
            return particle;
        }
    }
}
