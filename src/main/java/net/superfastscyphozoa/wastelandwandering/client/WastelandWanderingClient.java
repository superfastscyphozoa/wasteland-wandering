package net.superfastscyphozoa.wastelandwandering.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.superfastscyphozoa.wastelandwandering.client.particle.WawaParticleFactories;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterEntities;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;

public class WastelandWanderingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.WASTEWOOD_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.POISONED_IVY, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.IRRADIATED_SHORT_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.IRRADIATED_TALL_GRASS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.WASTESHRUB, RenderLayer.getCutout());

        EntityRendererRegistry.register(RegisterEntities.OIL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RegisterEntities.DYNAMITE_PROJECTILE, FlyingItemEntityRenderer::new);

        WawaParticleFactories.registerParticleFactories();
    }
}
