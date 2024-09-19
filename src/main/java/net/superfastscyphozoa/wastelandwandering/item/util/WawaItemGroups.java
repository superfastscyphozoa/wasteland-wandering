package net.superfastscyphozoa.wastelandwandering.item.util;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class WawaItemGroups {
    public static final RegistryKey<ItemGroup> WAWA_BLOCKS_GROUP_KEY = register("wawa_blocks_group");
    public static final RegistryKey<ItemGroup> WAWA_ITEMS_GROUP_KEY = register("wawa_items_group");

    private static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(WastelandWandering.MOD_ID, id));
    }

    public static final ItemGroup WAWA_BLOCKS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RegisterBlocks.IRRADIATED_GRASS_BLOCK))
            .displayName(Text.translatable("itemgroup.wasteland-wandering.wawa_blocks_group"))
            .entries((displayContext, entries) -> {

                entries.add(RegisterBlocks.IRRADIATED_GRASS_BLOCK);
                entries.add(RegisterBlocks.PATCHY_IRRADIATED_GRASS_BLOCK);
                entries.add(RegisterBlocks.SCORCHED_SOIL);

                entries.add(RegisterBlocks.IRRADIATED_SHORT_GRASS);
                entries.add(RegisterBlocks.IRRADIATED_TALL_GRASS);

                entries.add(RegisterBlocks.WASTESHRUB);
                entries.add(RegisterBlocks.POISONED_IVY);

                entries.add(RegisterBlocks.WASTEWOOD_SAPLING);
                entries.add(RegisterBlocks.RADPINE_SAPLING);

                entries.add(RegisterBlocks.SOOT_BLOCK);
                entries.add(RegisterBlocks.SOOT);

            }).build();

    public static final ItemGroup WAWA_ITEMS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RegisterItems.OIL))
            .displayName(Text.translatable("itemgroup.wasteland-wandering.wawa_items_group"))
            .entries((displayContext, entries) -> {

                entries.add(RegisterItems.OIL);
                entries.add(RegisterItems.DYNAMITE);
                entries.add(RegisterItems.MOLOTOV_COCKTAIL);

                entries.add(RegisterItems.MUTFRUIT);
                entries.add(RegisterItems.STIMPAK);

            }).build();

    public static void registerWawaItemGroups() {
        WastelandWandering.LOGGER.info("Registering Item Groups for " + WastelandWandering.MOD_ID);

        Registry.register(Registries.ITEM_GROUP, WAWA_BLOCKS_GROUP_KEY, WAWA_BLOCKS_GROUP);
        Registry.register(Registries.ITEM_GROUP, WAWA_ITEMS_GROUP_KEY, WAWA_ITEMS_GROUP);
    }

}
