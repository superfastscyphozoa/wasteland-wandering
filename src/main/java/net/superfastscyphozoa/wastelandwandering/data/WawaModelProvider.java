package net.superfastscyphozoa.wastelandwandering.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterItems;

public class WawaModelProvider extends FabricModelProvider {
    public WawaModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        blockStateModelGenerator.registerTintableCross(RegisterBlocks.WASTEWOOD_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(RegisterBlocks.RADPINE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerTintableCross(RegisterBlocks.IRRADIATED_SHORT_GRASS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(RegisterBlocks.IRRADIATED_TALL_GRASS, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerRotatable(RegisterBlocks.BLACK_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.GRAY_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.LIGHT_GRAY_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.WHITE_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.BROWN_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.PURPLE_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.MAGENTA_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.PINK_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.RED_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.ORANGE_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.YELLOW_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.LIME_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.GREEN_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.BLUE_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.LIGHT_BLUE_ASPHALT);
        blockStateModelGenerator.registerRotatable(RegisterBlocks.CYAN_ASPHALT);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(RegisterItems.STIMPAK, Models.GENERATED);
        itemModelGenerator.register(RegisterItems.MUTFRUIT, Models.GENERATED);

        itemModelGenerator.register(RegisterItems.BEER, Models.GENERATED);
        itemModelGenerator.register(RegisterItems.VODKA, Models.GENERATED);

        itemModelGenerator.register(RegisterItems.BROKEN_BOTTLE, Models.HANDHELD);

        itemModelGenerator.register(RegisterItems.OIL, Models.GENERATED);

        itemModelGenerator.register(RegisterItems.DYNAMITE, Models.GENERATED);
        itemModelGenerator.register(RegisterItems.MOLOTOV_COCKTAIL, Models.GENERATED);
    }
}
