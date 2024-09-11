package net.superfastscyphozoa.wastelandwandering.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.block.IrradiatedGrassBlock;
import net.superfastscyphozoa.wastelandwandering.block.IvyBlock;
import net.superfastscyphozoa.wastelandwandering.world.tree.WawaSaplingGenerators;

public class RegisterBlocks {

    //registry

    public static final Block IRRADIATED_GRASS_BLOCK = registerBlock("irradiated_grass_block",
            new IrradiatedGrassBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_YELLOW)
                    .ticksRandomly()
                    .strength(0.6F)
                    .sounds(BlockSoundGroup.GRASS)));

    public static final Block IRRADIATED_SHORT_GRASS = registerBlock("irradiated_short_grass",
            new ShortPlantBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XYZ)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block IRRADIATED_TALL_GRASS = registerBlock("irradiated_tall_grass",
            new TallPlantBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block WASTEWOOD_SAPLING = registerBlock("wastewood_sapling",
            new SaplingBlock(WawaSaplingGenerators.WASTEWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    public static final Block RADPINE_SAPLING = registerBlock("radpine_sapling",
            new SaplingBlock(WawaSaplingGenerators.RADPINE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    public static final Block POISONED_IVY = registerBlock("poisoned_ivy",
            new IvyBlock(AbstractBlock.Settings.copy(Blocks.VINE)));

    //registry end

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(WastelandWandering.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(WastelandWandering.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerWawaBlocks() {
        WastelandWandering.LOGGER.info("Registering Blocks for " + WastelandWandering.MOD_ID);

        //tells the game where to put the blocks in the inventory
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {

            entries.addAfter(Blocks.GRASS_BLOCK, RegisterBlocks.IRRADIATED_GRASS_BLOCK);

            entries.addAfter(Blocks.SHORT_GRASS, RegisterBlocks.IRRADIATED_SHORT_GRASS);
            entries.addAfter(Blocks.TALL_GRASS, RegisterBlocks.IRRADIATED_TALL_GRASS);

            entries.addAfter(Blocks.OAK_SAPLING, RegisterBlocks.WASTEWOOD_SAPLING);
            entries.addAfter(RegisterBlocks.WASTEWOOD_SAPLING, RegisterBlocks.RADPINE_SAPLING);

            entries.addAfter(Blocks.VINE, RegisterBlocks.POISONED_IVY);

        });
    }
}
