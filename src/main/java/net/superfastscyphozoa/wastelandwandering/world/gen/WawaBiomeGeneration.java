package net.superfastscyphozoa.wastelandwandering.world.gen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import net.minecraft.world.biome.BiomeKeys;
import net.superfastscyphozoa.wastelandwandering.world.biome.WawaBiomeKeys;

public class WawaBiomeGeneration {
    public static void generateBiomes(){

        BiomePlacement.replaceOverworld(BiomeKeys.FOREST, WawaBiomeKeys.WASTED_FOREST);
        BiomePlacement.replaceOverworld(BiomeKeys.FLOWER_FOREST, WawaBiomeKeys.WASTED_FOREST);

        BiomePlacement.replaceOverworld(BiomeKeys.PLAINS, WawaBiomeKeys.PRAIRIE);
        BiomePlacement.replaceOverworld(BiomeKeys.SUNFLOWER_PLAINS, WawaBiomeKeys.PRAIRIE);

        BiomePlacement.addSubOverworld(
                WawaBiomeKeys.WASTED_FOREST,
                WawaBiomeKeys.WASTED_GLADE,
                CriterionBuilder.deviationMin(
                        BiomeParameterTargets.PEAKS_VALLEYS,
                        0.05F));
    }

    public static void removeVanillaBiomes(){

        //taiga
        BiomePlacement.removeOverworld(BiomeKeys.TAIGA);
        BiomePlacement.removeOverworld(BiomeKeys.OLD_GROWTH_PINE_TAIGA);
        BiomePlacement.removeOverworld(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA);

        //snowy
        BiomePlacement.removeOverworld(BiomeKeys.SNOWY_TAIGA);
        BiomePlacement.removeOverworld(BiomeKeys.SNOWY_PLAINS);
        BiomePlacement.removeOverworld(BiomeKeys.SNOWY_BEACH);
        BiomePlacement.removeOverworld(BiomeKeys.SNOWY_SLOPES);

        //mountain
        BiomePlacement.removeOverworld(BiomeKeys.FROZEN_PEAKS);
        BiomePlacement.removeOverworld(BiomeKeys.GROVE);
        BiomePlacement.removeOverworld(BiomeKeys.CHERRY_GROVE);

        //jungle
        BiomePlacement.removeOverworld(BiomeKeys.JUNGLE);
        BiomePlacement.removeOverworld(BiomeKeys.BAMBOO_JUNGLE);
        BiomePlacement.removeOverworld(BiomeKeys.SPARSE_JUNGLE);

        //savanna
        BiomePlacement.removeOverworld(BiomeKeys.SAVANNA);
        BiomePlacement.removeOverworld(BiomeKeys.SAVANNA_PLATEAU);
        BiomePlacement.removeOverworld(BiomeKeys.WINDSWEPT_SAVANNA);

        //windswept
        BiomePlacement.removeOverworld(BiomeKeys.WINDSWEPT_HILLS);
        BiomePlacement.removeOverworld(BiomeKeys.WINDSWEPT_FOREST);
        BiomePlacement.removeOverworld(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS);

        //swamp
        BiomePlacement.removeOverworld(BiomeKeys.SWAMP);
        BiomePlacement.removeOverworld(BiomeKeys.MANGROVE_SWAMP);

        //caves
        BiomePlacement.removeOverworld(BiomeKeys.LUSH_CAVES);
        BiomePlacement.removeOverworld(BiomeKeys.DEEP_DARK);
    }
}
