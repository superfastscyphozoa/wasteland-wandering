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

        blockStateModelGenerator.registerDoubleBlock(RegisterBlocks.WASTESHRUB, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.BLACK_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.GRAY_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.LIGHT_GRAY_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.WHITE_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.BROWN_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.PURPLE_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.MAGENTA_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.PINK_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.RED_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.ORANGE_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.YELLOW_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.LIME_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.GREEN_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.BLUE_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.LIGHT_BLUE_ASPHALT);
        blockStateModelGenerator.registerSimpleCubeAll(RegisterBlocks.CYAN_ASPHALT);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(RegisterItems.STIMPAK, Models.GENERATED);
        itemModelGenerator.register(RegisterItems.MUTFRUIT, Models.GENERATED);

        itemModelGenerator.register(RegisterItems.OIL, Models.GENERATED);

        itemModelGenerator.register(RegisterItems.DYNAMITE, Models.GENERATED);
        itemModelGenerator.register(RegisterItems.MOLOTOV_COCKTAIL, Models.GENERATED);
    }
}
