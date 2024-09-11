package net.superfastscyphozoa.wastelandwandering.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;

import java.util.concurrent.CompletableFuture;

public class WawaBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public WawaBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.CLIMBABLE)
                .add(RegisterBlocks.POISONED_IVY);

        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(RegisterBlocks.IRRADIATED_GRASS_BLOCK);

        getOrCreateTagBuilder(BlockTags.VALID_SPAWN)
                .add(RegisterBlocks.IRRADIATED_GRASS_BLOCK);
    }
}
