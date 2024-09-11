package net.superfastscyphozoa.wastelandwandering.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;

public class WastelandWanderingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.WASTEWOOD_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.POISONED_IVY, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.IRRADIATED_SHORT_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlocks.IRRADIATED_TALL_GRASS, RenderLayer.getCutout());

    }
}
