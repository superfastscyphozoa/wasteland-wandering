package net.superfastscyphozoa.wastelandwandering.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.client.particle.WawaParticleFactories;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterEntities;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

@Environment(EnvType.CLIENT)
public class WastelandWanderingClient implements ClientModInitializer {

    public static void registerModelPredicateProvider(){

        //register model predicates

        ModelPredicateProviderRegistry.register(RegisterItems.BUMPER_SWORD, Identifier.ofVanilla("blocking"), (stack, world, entity, seed) ->
                entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);

    }

    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.WASTEWOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.RADPINE_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.POISONED_IVY, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.IRRADIATED_SHORT_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.IRRADIATED_TALL_GRASS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.WASTESHRUB, RenderLayer.getCutout());

        EntityRendererRegistry.register(RegisterEntities.OIL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RegisterEntities.DYNAMITE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RegisterEntities.MOLOTOV_COCKTAIL_PROJECTILE, FlyingItemEntityRenderer::new);

        WawaParticleFactories.registerParticleFactories();

        registerModelPredicateProvider();
    }
}
