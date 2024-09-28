package net.superfastscyphozoa.wastelandwandering.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.block.*;
import net.superfastscyphozoa.wastelandwandering.world.tree.WawaSaplingGenerators;

public class RegisterBlocks {

    //registry

    //irradiated grass

    public static final Block IRRADIATED_GRASS_BLOCK = registerBlock("irradiated_grass_block",
            new IrradiatedGrassBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.ORANGE)
                    .ticksRandomly()
                    .strength(0.6F)
                    .sounds(BlockSoundGroup.GRASS)));

    public static final Block PATCHY_IRRADIATED_GRASS_BLOCK = registerBlock("patchy_irradiated_grass_block",
            new IrradiatedGrassBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.ORANGE)
                    .ticksRandomly()
                    .strength(0.6F)
                    .sounds(BlockSoundGroup.GRASS)));

    public static final Block IRRADIATED_SHORT_GRASS = registerBlock("irradiated_short_grass",
            new IrradiatedShortPlantBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.ORANGE)
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XYZ)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block IRRADIATED_TALL_GRASS = registerBlock("irradiated_tall_grass",
            new TallPlantBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.ORANGE)
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    //soil

    public static final Block SCORCHED_SOIL = registerBlock("scorched_soil",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DIRT_BROWN)
                    .strength(0.5F)
                    .sounds(BlockSoundGroup.GRAVEL)));

    //foliage

    public static final Block POISONED_IVY = registerBlock("poisoned_ivy",
            new IvyBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.ORANGE)
                    .replaceable()
                    .noCollision()
                    .strength(0.2F)
                    .sounds(BlockSoundGroup.GLOW_LICHEN)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block CARROT_FLOWER = registerBlock("carrot_flower",
            new BushyFlowerBlock(
                    StatusEffects.JUMP_BOOST,
                    6.0F,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DARK_GREEN)
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.GRASS)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );

    //wood

    public static final Block WASTEWOOD_SAPLING = registerBlock("wastewood_sapling",
            new SaplingBlock(WawaSaplingGenerators.WASTEWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    public static final Block RADPINE_SAPLING = registerBlock("radpine_sapling",
            new SaplingBlock(WawaSaplingGenerators.RADPINE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    // soot

    public static final Block SOOT_BLOCK = registerBlock("soot_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .requiresTool()
                    .strength(0.2F)
                    .sounds(BlockSoundGroup.SNOW)));

    public static final Block SOOT = registerBlock("soot",
            new SootBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .replaceable()
                    .strength(0.1F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.SNOW)
                    .blockVision((state, world, pos) -> state.get(SootBlock.LAYERS) >= 8)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    // asphalt

    public static final Block WHITE_ASPHALT = registerBlock("white_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block ORANGE_ASPHALT = registerBlock("orange_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block MAGENTA_ASPHALT = registerBlock("magenta_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIGHT_BLUE_ASPHALT = registerBlock("light_blue_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block YELLOW_ASPHALT = registerBlock("yellow_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIME_ASPHALT = registerBlock("lime_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block PINK_ASPHALT = registerBlock("pink_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block GRAY_ASPHALT = registerBlock("gray_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block LIGHT_GRAY_ASPHALT = registerBlock("light_gray_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block CYAN_ASPHALT = registerBlock("cyan_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block PURPLE_ASPHALT = registerBlock("purple_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BLUE_ASPHALT = registerBlock("blue_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BROWN_ASPHALT = registerBlock("brown_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block GREEN_ASPHALT = registerBlock("green_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block RED_ASPHALT = registerBlock("red_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));
    public static final Block BLACK_ASPHALT = registerBlock("black_asphalt",
            new Block(AbstractBlock.Settings.create().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.8F)));

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
            entries.addAfter(RegisterBlocks.IRRADIATED_GRASS_BLOCK, RegisterBlocks.PATCHY_IRRADIATED_GRASS_BLOCK);

            entries.addAfter(Blocks.SHORT_GRASS, RegisterBlocks.IRRADIATED_SHORT_GRASS);
            entries.addAfter(Blocks.TALL_GRASS, RegisterBlocks.IRRADIATED_TALL_GRASS);

            entries.addAfter(Blocks.DIRT, RegisterBlocks.SCORCHED_SOIL);

            entries.addAfter(Blocks.OAK_SAPLING, RegisterBlocks.WASTEWOOD_SAPLING);
            entries.addAfter(RegisterBlocks.WASTEWOOD_SAPLING, RegisterBlocks.RADPINE_SAPLING);

            entries.addAfter(Blocks.VINE, RegisterBlocks.POISONED_IVY);

            entries.addAfter(Blocks.LILY_OF_THE_VALLEY, RegisterBlocks.CARROT_FLOWER);

            entries.addAfter(Blocks.MOSS_CARPET, RegisterBlocks.SOOT_BLOCK);
            entries.addAfter(RegisterBlocks.SOOT_BLOCK, RegisterBlocks.SOOT);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.addAfter(Blocks.PINK_CONCRETE, WHITE_ASPHALT);
            entries.addAfter(WHITE_ASPHALT, LIGHT_GRAY_ASPHALT);
            entries.addAfter(LIGHT_GRAY_ASPHALT, GRAY_ASPHALT);
            entries.addAfter(GRAY_ASPHALT, BLACK_ASPHALT);
            entries.addAfter(BLACK_ASPHALT, BROWN_ASPHALT);
            entries.addAfter(BROWN_ASPHALT, RED_ASPHALT);
            entries.addAfter(RED_ASPHALT, ORANGE_ASPHALT);
            entries.addAfter(ORANGE_ASPHALT, YELLOW_ASPHALT);
            entries.addAfter(YELLOW_ASPHALT, LIME_ASPHALT);
            entries.addAfter(LIME_ASPHALT, GREEN_ASPHALT);
            entries.addAfter(GREEN_ASPHALT, CYAN_ASPHALT);
            entries.addAfter(CYAN_ASPHALT, LIGHT_BLUE_ASPHALT);
            entries.addAfter(LIGHT_BLUE_ASPHALT, BLUE_ASPHALT);
            entries.addAfter(BLUE_ASPHALT, PURPLE_ASPHALT);
            entries.addAfter(PURPLE_ASPHALT, MAGENTA_ASPHALT);
            entries.addAfter(MAGENTA_ASPHALT, PINK_ASPHALT);
        });
    }
}
