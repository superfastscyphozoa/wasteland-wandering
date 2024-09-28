package net.superfastscyphozoa.wastelandwandering.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;

import java.util.concurrent.CompletableFuture;

public class WawaLootTableProvider extends FabricBlockLootTableProvider {
    protected WawaLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(RegisterBlocks.IRRADIATED_GRASS_BLOCK, drops(RegisterBlocks.IRRADIATED_GRASS_BLOCK, Blocks.DIRT));
        addDrop(RegisterBlocks.PATCHY_IRRADIATED_GRASS_BLOCK, drops(RegisterBlocks.PATCHY_IRRADIATED_GRASS_BLOCK, Blocks.DIRT));

        addDrop(RegisterBlocks.IRRADIATED_SHORT_GRASS, irradiatedShortPlantDrops(RegisterBlocks.IRRADIATED_SHORT_GRASS));
        addDrop(RegisterBlocks.IRRADIATED_TALL_GRASS, irradiatedTallPlantDrops(RegisterBlocks.IRRADIATED_TALL_GRASS, RegisterBlocks.IRRADIATED_SHORT_GRASS));

        addDrop(RegisterBlocks.SCORCHED_SOIL);

        addDrop(RegisterBlocks.POISONED_IVY, multifaceGrowthDrops(RegisterBlocks.POISONED_IVY, WITH_SHEARS));

        addDrop(RegisterBlocks.CARROT_FLOWER, wildPlantDrops(RegisterBlocks.CARROT_FLOWER, Items.CARROT));

        addDrop(RegisterBlocks.WASTEWOOD_SAPLING);
        addDrop(RegisterBlocks.RADPINE_SAPLING);

        addDrop(RegisterBlocks.BLACK_ASPHALT);
        addDrop(RegisterBlocks.GRAY_ASPHALT);
        addDrop(RegisterBlocks.LIGHT_GRAY_ASPHALT);
        addDrop(RegisterBlocks.WHITE_ASPHALT);
        addDrop(RegisterBlocks.BROWN_ASPHALT);
        addDrop(RegisterBlocks.RED_ASPHALT);
        addDrop(RegisterBlocks.ORANGE_ASPHALT);
        addDrop(RegisterBlocks.YELLOW_ASPHALT);
        addDrop(RegisterBlocks.LIME_ASPHALT);
        addDrop(RegisterBlocks.GREEN_ASPHALT);
        addDrop(RegisterBlocks.LIGHT_BLUE_ASPHALT);
        addDrop(RegisterBlocks.BLUE_ASPHALT);
        addDrop(RegisterBlocks.CYAN_ASPHALT);
        addDrop(RegisterBlocks.PURPLE_ASPHALT);
        addDrop(RegisterBlocks.PINK_ASPHALT);
        addDrop(RegisterBlocks.MAGENTA_ASPHALT);
    }

    // loot table builders

    //plant

    public LootTable.Builder wildPlantDrops(Block withShears, ItemConvertible veggie) {
        RegistryWrapper.Impl<Enchantment> impl = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return dropsWithShears(
                withShears,
                applyExplosionDecay(
                        withShears,
                        ItemEntry.builder(veggie)
                                .apply(ApplyBonusLootFunction.binomialWithBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))
                )
        );
    }

    //grass

    private final ItemConvertible grassSeeds = Items.PUMPKIN_SEEDS;

    public LootTable.Builder irradiatedShortPlantDrops(Block withShears) {
        RegistryWrapper.Impl<Enchantment> impl = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return dropsWithShears(
                withShears,
                applyExplosionDecay(
                        withShears,
                        ItemEntry.builder(grassSeeds)
                                .conditionally(RandomChanceLootCondition.builder(0.125F))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE), 2))
                )
        );
    }

    public LootTable.Builder irradiatedTallPlantDrops(Block tallPlant, Block shortPlant) {
        LootPoolEntry.Builder<?> builder = ItemEntry.builder(shortPlant)
                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                .conditionally(WITH_SHEARS)
                .alternatively(
                        ((LeafEntry.Builder<?>)this.addSurvivesExplosionCondition(tallPlant, ItemEntry.builder(grassSeeds)))
                                .conditionally(RandomChanceLootCondition.builder(0.125F))
                );
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .with(builder)
                                .conditionally(
                                        BlockStatePropertyLootCondition.builder(tallPlant).properties(StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER))
                                )
                                .conditionally(
                                        LocationCheckLootCondition.builder(
                                                LocationPredicate.Builder.create()
                                                        .block(BlockPredicate.Builder.create().blocks(tallPlant).state(StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER))),
                                                new BlockPos(0, 1, 0)
                                        )
                                )
                )
                .pool(
                        LootPool.builder()
                                .with(builder)
                                .conditionally(
                                        BlockStatePropertyLootCondition.builder(tallPlant).properties(StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER))
                                )
                                .conditionally(
                                        LocationCheckLootCondition.builder(
                                                LocationPredicate.Builder.create()
                                                        .block(BlockPredicate.Builder.create().blocks(tallPlant).state(StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER))),
                                                new BlockPos(0, -1, 0)
                                        )
                                )
                );
    }
}
