package net.superfastscyphozoa.wastelandwandering.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.util.WawaTags;

import java.util.concurrent.CompletableFuture;

public class WawaBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public WawaBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(WawaTags.Blocks.IRRADIATED_GRASS)
                .add(RegisterBlocks.IRRADIATED_GRASS_BLOCK, RegisterBlocks.PATCHY_IRRADIATED_GRASS_BLOCK);

        getOrCreateTagBuilder(BlockTags.CLIMBABLE)
                .add(RegisterBlocks.POISONED_IVY);

        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(RegisterBlocks.IRRADIATED_GRASS_BLOCK, RegisterBlocks.PATCHY_IRRADIATED_GRASS_BLOCK, RegisterBlocks.SCORCHED_SOIL);

        getOrCreateTagBuilder(BlockTags.VALID_SPAWN)
                .add(RegisterBlocks.IRRADIATED_GRASS_BLOCK, RegisterBlocks.PATCHY_IRRADIATED_GRASS_BLOCK);

        getOrCreateTagBuilder(BlockTags.REPLACEABLE)
                .add(RegisterBlocks.SOOT, RegisterBlocks.IRRADIATED_SHORT_GRASS, RegisterBlocks.IRRADIATED_TALL_GRASS);

        getOrCreateTagBuilder(WawaTags.Blocks.ASPHALT)
                .add(
                        RegisterBlocks.BLACK_ASPHALT,
                        RegisterBlocks.BLUE_ASPHALT,
                        RegisterBlocks.LIGHT_BLUE_ASPHALT,
                        RegisterBlocks.LIME_ASPHALT,
                        RegisterBlocks.LIGHT_GRAY_ASPHALT,
                        RegisterBlocks.GRAY_ASPHALT,
                        RegisterBlocks.GREEN_ASPHALT,
                        RegisterBlocks.BROWN_ASPHALT,
                        RegisterBlocks.MAGENTA_ASPHALT,
                        RegisterBlocks.CYAN_ASPHALT,
                        RegisterBlocks.PINK_ASPHALT,
                        RegisterBlocks.PURPLE_ASPHALT,
                        RegisterBlocks.YELLOW_ASPHALT,
                        RegisterBlocks.RED_ASPHALT,
                        RegisterBlocks.ORANGE_ASPHALT,
                        RegisterBlocks.WHITE_ASPHALT);
    }
}
