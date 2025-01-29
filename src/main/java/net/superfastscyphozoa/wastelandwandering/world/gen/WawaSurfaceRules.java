package net.superfastscyphozoa.wastelandwandering.world.gen;

import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;
import net.superfastscyphozoa.wastelandwandering.registry.RegisterBlocks;
import net.superfastscyphozoa.wastelandwandering.world.biome.WawaBiomeKeys;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

public class WawaSurfaceRules {
    public static void generateSurfaceRules(){

        MaterialRule wastedForest = condition(MaterialRules.biome(WawaBiomeKeys.WASTED_FOREST), sequence(
                condition(STONE_DEPTH_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.condition(surfaceNoiseThreshold(1.75),
                                        block(RegisterBlocks.IRRADIATED_GRASS_BLOCK.getDefaultState())),

                                MaterialRules.condition(surfaceNoiseThreshold(-0.95),
                                        block(RegisterBlocks.PATCHY_IRRADIATED_GRASS_BLOCK.getDefaultState())),
                block(RegisterBlocks.SCORCHED_SOIL.getDefaultState())))));

        MaterialRule grassy = condition(MaterialRules.biome(WawaBiomeKeys.PRAIRIE, WawaBiomeKeys.WASTED_GLADE), sequence(
                condition(STONE_DEPTH_FLOOR,
                        MaterialRules.sequence(block(RegisterBlocks.IRRADIATED_GRASS_BLOCK.getDefaultState())))));

        SurfaceGeneration.addOverworldSurfaceRules(
                Identifier.of(WastelandWandering.MOD_ID, "rules/overworld"),
                condition(surface(), sequence(wastedForest, grassy)));
    }

    private static MaterialRules.MaterialCondition surfaceNoiseThreshold(double min) {
        return MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, Double.MAX_VALUE);
    }
}
