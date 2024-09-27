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

        //weapon and equipment

        getOrCreateTagBuilder(WawaTags.Items.THROWN_EXPLOSIVE_NEEDS_LIGHTER)
                .add(RegisterItems.DYNAMITE, RegisterItems.MOLOTOV_COCKTAIL);

        getOrCreateTagBuilder(WawaTags.Items.SWEEPING_WEAPON)
                .add(RegisterItems.BUMPER_SWORD);

        getOrCreateTagBuilder(WawaTags.Items.TWO_HANDED_WEAPON)
                .add(RegisterItems.BUMPER_SWORD);

        getOrCreateTagBuilder(WawaTags.Items.SHIELDING_ITEM)
                //.add(Items.SHIELD)
                .add(RegisterItems.BUMPER_SWORD);

        //tool type

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(RegisterItems.BUMPER_SWORD);

        //enchantable

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(RegisterItems.BROKEN_BOTTLE);

        getOrCreateTagBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(RegisterItems.BROKEN_BOTTLE);

        getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(RegisterItems.BROKEN_BOTTLE);

        //ingredients

        getOrCreateTagBuilder(WawaTags.Items.GLASS)
                .add(Items.GLASS)
                .add(Items.GLASS_PANE)

                .add(Items.TINTED_GLASS)

                .add(Items.BLACK_STAINED_GLASS)
                .add(Items.BLACK_STAINED_GLASS_PANE)

                .add(Items.GRAY_STAINED_GLASS)
                .add(Items.GRAY_STAINED_GLASS_PANE)

                .add(Items.LIGHT_GRAY_STAINED_GLASS)
                .add(Items.LIGHT_GRAY_STAINED_GLASS_PANE)

                .add(Items.WHITE_STAINED_GLASS)
                .add(Items.WHITE_STAINED_GLASS_PANE)

                .add(Items.BROWN_STAINED_GLASS)
                .add(Items.BROWN_STAINED_GLASS_PANE)

                .add(Items.RED_STAINED_GLASS)
                .add(Items.RED_STAINED_GLASS_PANE)

                .add(Items.ORANGE_STAINED_GLASS)
                .add(Items.ORANGE_STAINED_GLASS_PANE)

                .add(Items.YELLOW_STAINED_GLASS)
                .add(Items.YELLOW_STAINED_GLASS_PANE)

                .add(Items.LIME_STAINED_GLASS)
                .add(Items.LIME_STAINED_GLASS_PANE)

                .add(Items.GREEN_STAINED_GLASS)
                .add(Items.GREEN_STAINED_GLASS_PANE)

                .add(Items.LIGHT_BLUE_STAINED_GLASS)
                .add(Items.LIGHT_BLUE_STAINED_GLASS_PANE)

                .add(Items.BLUE_STAINED_GLASS)
                .add(Items.BLUE_STAINED_GLASS_PANE)

                .add(Items.CYAN_STAINED_GLASS)
                .add(Items.CYAN_STAINED_GLASS_PANE)

                .add(Items.PINK_STAINED_GLASS)
                .add(Items.PINK_STAINED_GLASS_PANE)

                .add(Items.PURPLE_STAINED_GLASS)
                .add(Items.PURPLE_STAINED_GLASS_PANE)

                .add(Items.MAGENTA_STAINED_GLASS)
                .add(Items.MAGENTA_STAINED_GLASS_PANE);
    }
}
