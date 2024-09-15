package net.superfastscyphozoa.wastelandwandering.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterParticles;

@Environment(EnvType.CLIENT)
public class OilDripParticle extends SpriteBillboardParticle {
    final SpriteProvider spriteProvider;

    public OilDripParticle(ClientWorld clientWorld, double x, double y, double z, SpriteProvider spriteProvider) {
        super(clientWorld, x, y, z);

        this.spriteProvider = spriteProvider;
        this.setSpriteForAge(spriteProvider);
        this.maxAge = 9 + this.random.nextInt(4);
        this.setBoundingBoxSpacing(1.0F, 1.0F);

        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.gravityStrength = 0.02F;
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
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.updateAge();
        if (!this.dead) {
            this.velocityY = this.velocityY - (double)this.gravityStrength;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            this.updateVelocity();
            if (!this.dead) {
                this.velocityX *= 0.98F;
                this.velocityY *= 0.98F;
                this.velocityZ *= 0.98F;
            }
        }
    }

    protected void updateAge() {
        if (this.age++ >= this.maxAge) {
            this.age = 0;
        } else {
            this.setSpriteForAge(this.spriteProvider);
        }
    }

    protected void updateVelocity() {
        if (this.onGround) {
            this.markDead();
            this.world.addParticle(RegisterParticles.OIL_DRIP_LANDING, this.x, this.y, this.z, 0.0, 0.0, 0.0);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class OilDripFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider dripSpriteProvider;

        public OilDripFactory(SpriteProvider spriteProvider) {
            this.dripSpriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new OilDripParticle(clientWorld, d, e, f, this.dripSpriteProvider);
        }
    }

    @Environment(EnvType.CLIENT)
    static class Landing extends OilDripParticle {
        Landing(ClientWorld clientWorld, double d, double e, double f, SpriteProvider spriteProvider) {
            super(clientWorld, d, e, f, spriteProvider);
            this.maxAge = 14 + this.random.nextInt(4);
        }

        @Override
        protected void updateAge() {
            if (this.age++ >= this.maxAge) {
                this.markDead();
            } else {
                this.setSpriteForAge(this.spriteProvider);
            }
        }

        @Override
        protected void updateVelocity() {
        }
    }

    @Environment(EnvType.CLIENT)
    public static class OilDripLandingFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider dripLandingSpriteProvider;

        public OilDripLandingFactory(SpriteProvider spriteProvider) {
            this.dripLandingSpriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new OilDripParticle.Landing(clientWorld, d, e, f, this.dripLandingSpriteProvider);
        }
    }
}
