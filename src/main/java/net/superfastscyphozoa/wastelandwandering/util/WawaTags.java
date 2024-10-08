package net.superfastscyphozoa.wastelandwandering.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;

public class WawaTags {
    public static class Blocks {
        public static final TagKey<Block> IRRADIATED_GRASS = createTag("irradiated_grass");

        public static final TagKey<Block> ASPHALT = createTag("asphalt");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(WastelandWandering.MOD_ID, name));
        }

    }

    public static class Items {

        public static final TagKey<Item> CHEMS = createTag("chems");

        public static final TagKey<Item> FUSE_LIGHTER = createTag("fuse_lighter");
        public static final TagKey<Item> THROWN_EXPLOSIVE_NEEDS_LIGHTER = createTag("thrown_explosive_needs_lighter");

        public static final TagKey<Item> SWEEPING_WEAPON = createTag("sweeping_weapon");
        public static final TagKey<Item> TWO_HANDED_WEAPON = createTag("two_handed_weapon");
        public static final TagKey<Item> SHIELDING_ITEM = createTag("shielding_item");

        public static final TagKey<Item> GLASS = createTag("glass");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(WastelandWandering.MOD_ID, name));
        }

    }
}
