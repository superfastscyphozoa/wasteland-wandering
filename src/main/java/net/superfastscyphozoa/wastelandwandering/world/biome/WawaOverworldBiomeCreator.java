package net.superfastscyphozoa.wastelandwandering.world.biome;

import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.jetbrains.annotations.Nullable;

public class WawaOverworldBiomeCreator {

    private static Biome createBiome(
            boolean precipitation, float temperature, float downfall,
            SpawnSettings.Builder spawnSettings,
            GenerationSettings.LookupBackedBuilder generationSettings,
            @Nullable MusicSound music
    )
    {
        return createBiome(precipitation, temperature, downfall, 4159204, 329011, null, null, spawnSettings, generationSettings, music);
    }

    private static Biome createBiome(
            boolean precipitation, float temperature, float downfall,
            int waterColor, int waterFogColor,
            @Nullable Integer grassColor, @Nullable Integer foliageColor,
            SpawnSettings.Builder spawnSettings,
            GenerationSettings.LookupBackedBuilder generationSettings,
            @Nullable MusicSound music)
    {
        BiomeEffects.Builder builder = new BiomeEffects.Builder()
                .waterColor(waterColor).waterFogColor(waterFogColor)
                .fogColor(12638463).skyColor(OverworldBiomeCreator.getSkyColor(temperature))
                .moodSound(BiomeMoodSound.CAVE).music(music);

        if (grassColor != null) {
            builder.grassColor(grassColor);
        }
        if (foliageColor != null) {
            builder.foliageColor(foliageColor);
        }

        return new Biome.Builder()
                .precipitation(precipitation).temperature(temperature).downfall(downfall)
                .effects(builder.build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    private static void addBasicFeatures(GenerationSettings.LookupBackedBuilder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }

    //biomes

    public static Biome createWastedForest(RegistryEntryLookup<PlacedFeature> featureLookup, RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
        //spawns
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        //generation and features
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(featureLookup, carverLookup);
        addBasicFeatures(generationSettings);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.DARK_FOREST_VEGETATION);
        DefaultBiomeFeatures.addForestFlowers(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addDefaultFlowers(generationSettings);
        DefaultBiomeFeatures.addForestGrass(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings);

        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST);

        return createBiome(true, 0.7F, 0.8F, spawnSettings, generationSettings, musicSound);
    }
}
