package pipandchell.wastelandwandering.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import pipandchell.wastelandwandering.registry.RegisterBlocks;
import pipandchell.wastelandwandering.registry.RegisterItems;

public class WawaModelProvider extends FabricModelProvider {
    public WawaModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerTintableCross(RegisterBlocks.WASTEWOOD_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerTintableCross(RegisterBlocks.IRRADIATED_SHORT_GRASS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(RegisterBlocks.IRRADIATED_TALL_GRASS, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(RegisterItems.STIMPAK, Models.GENERATED);
    }
}
