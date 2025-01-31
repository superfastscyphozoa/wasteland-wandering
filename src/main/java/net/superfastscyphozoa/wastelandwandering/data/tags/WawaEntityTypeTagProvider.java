package net.superfastscyphozoa.wastelandwandering.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryWrapper;
import net.superfastscyphozoa.wastelandwandering.util.WawaTags;

import java.util.concurrent.CompletableFuture;

public class WawaEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public WawaEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(WawaTags.EntityTypes.BLOODLESS)
                .add(
                        EntityType.ALLAY, EntityType.VEX,
                        EntityType.SKELETON, EntityType.BOGGED, EntityType.STRAY, EntityType.WITHER_SKELETON, EntityType.SKELETON_HORSE,
                        EntityType.SNOW_GOLEM, EntityType.IRON_GOLEM, EntityType.SHULKER,
                        EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.SILVERFISH, EntityType.BEE,
                        EntityType.ENDERMITE, EntityType.ENDERMAN,
                        EntityType.BLAZE, EntityType.BREEZE, EntityType.MAGMA_CUBE,
                        EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN,
                        EntityType.ENDER_DRAGON, EntityType.WITHER, EntityType.WARDEN,
                        EntityType.GHAST, EntityType.PHANTOM,
                        EntityType.CREEPER
                );

        getOrCreateTagBuilder(WawaTags.EntityTypes.HAS_GLOWING_BLOOD)
                .add(
                        EntityType.SLIME
                );
    }
}