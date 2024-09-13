package net.superfastscyphozoa.wastelandwandering.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;

public class WawaTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(WastelandWandering.MOD_ID, name));
        }

    }

    public static class Items {
        public static final TagKey<Item> CHEMS = createTag("chems");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(WastelandWandering.MOD_ID, name));
        }

    }
}
