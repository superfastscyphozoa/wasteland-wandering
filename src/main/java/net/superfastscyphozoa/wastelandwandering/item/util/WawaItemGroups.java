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
    public static final RegistryKey<ItemGroup> WAWA_NATURAL_BLOCKS_GROUP = register("wawa_natural_blocks_group");
    public static final RegistryKey<ItemGroup> WAWA_BUILDING_BLOCKS_GROUP = register("wawa_building_blocks_group");
    public static final RegistryKey<ItemGroup> WAWA_COMBAT_GROUP = register("wawa_combat_group");
    public static final RegistryKey<ItemGroup> WAWA_MISC_ITEMS_GROUP = register("wawa_misc_items_group");

    private static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(WastelandWandering.MOD_ID, id));
    }

    public static final ItemGroup WAWA_NATURAL_BLOCKS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RegisterBlocks.IRRADIATED_TALL_GRASS))
            .displayName(Text.translatable("itemgroup.wasteland-wandering.wawa_natural_blocks"))
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

    public static final ItemGroup WAWA_BUILDING_BLOCKS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RegisterBlocks.GRAY_ASPHALT))
            .displayName(Text.translatable("itemgroup.wasteland-wandering.wawa_building_blocks"))
            .entries((displayContext, entries) -> {

                entries.add(RegisterBlocks.WHITE_ASPHALT);
                entries.add(RegisterBlocks.LIGHT_GRAY_ASPHALT);
                entries.add(RegisterBlocks.GRAY_ASPHALT);
                entries.add(RegisterBlocks.BLACK_ASPHALT);
                entries.add(RegisterBlocks.BROWN_ASPHALT);
                entries.add(RegisterBlocks.RED_ASPHALT);
                entries.add(RegisterBlocks.ORANGE_ASPHALT);
                entries.add(RegisterBlocks.YELLOW_ASPHALT);
                entries.add(RegisterBlocks.LIME_ASPHALT);
                entries.add(RegisterBlocks.GREEN_ASPHALT);
                entries.add(RegisterBlocks.CYAN_ASPHALT);
                entries.add(RegisterBlocks.LIGHT_BLUE_ASPHALT);
                entries.add(RegisterBlocks.BLUE_ASPHALT);
                entries.add(RegisterBlocks.PURPLE_ASPHALT);
                entries.add(RegisterBlocks.MAGENTA_ASPHALT);
                entries.add(RegisterBlocks.PINK_ASPHALT);

            }).build();

    public static final ItemGroup WAWA_COMBAT = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RegisterItems.DYNAMITE))
            .displayName(Text.translatable("itemgroup.wasteland-wandering.wawa_combat"))
            .entries((displayContext, entries) -> {

                entries.add(RegisterItems.DYNAMITE);
                entries.add(RegisterItems.MOLOTOV_COCKTAIL);
                entries.add(RegisterItems.OIL);

            }).build();

    public static final ItemGroup WAWA_MISC_ITEMS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RegisterItems.OIL))
            .displayName(Text.translatable("itemgroup.wasteland-wandering.wawa_misc_items"))
            .entries((displayContext, entries) -> {

                entries.add(RegisterItems.OIL);
                entries.add(RegisterItems.MUTFRUIT);
                entries.add(RegisterItems.STIMPAK);

            }).build();

    public static void registerWawaItemGroups() {
        WastelandWandering.LOGGER.info("Registering Item Groups for " + WastelandWandering.MOD_ID);

        Registry.register(Registries.ITEM_GROUP, WAWA_NATURAL_BLOCKS_GROUP, WAWA_NATURAL_BLOCKS);
        Registry.register(Registries.ITEM_GROUP, WAWA_BUILDING_BLOCKS_GROUP, WAWA_BUILDING_BLOCKS);
        Registry.register(Registries.ITEM_GROUP, WAWA_COMBAT_GROUP, WAWA_COMBAT);
        Registry.register(Registries.ITEM_GROUP, WAWA_MISC_ITEMS_GROUP, WAWA_MISC_ITEMS);
    }

}
