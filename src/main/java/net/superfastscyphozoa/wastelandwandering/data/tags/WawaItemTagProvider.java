package net.superfastscyphozoa.wastelandwandering.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;
import net.superfastscyphozoa.wastelandwandering.util.WawaTags;

import java.util.concurrent.CompletableFuture;

public class WawaItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public WawaItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(WawaTags.Items.CHEMS)
                .add(RegisterItems.STIMPAK);

        getOrCreateTagBuilder(WawaTags.Items.FUSE_LIGHTER)
                .add(Items.FIRE_CHARGE, Items.FLINT_AND_STEEL);

        getOrCreateTagBuilder(WawaTags.Items.THROWN_EXPLOSIVE_NEEDS_LIGHTER)
                .add(RegisterItems.DYNAMITE, RegisterItems.MOLOTOV_COCKTAIL);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(RegisterItems.BUMPER_SWORD);
    }
}
