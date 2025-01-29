package net.superfastscyphozoa.wastelandwandering.world.gen;

public class WastelandWanderingWorldGeneration {
    public static void generateWawaWorldgen(){

        WawaBiomeGeneration.generateBiomes();
        WawaBiomeGeneration.removeVanillaBiomes();

        WawaSurfaceRules.generateSurfaceRules();

        WawaTreeGeneration.generateTrees();
    }
}
