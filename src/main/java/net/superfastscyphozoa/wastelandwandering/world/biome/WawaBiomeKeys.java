package net.superfastscyphozoa.wastelandwandering.world.biome;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.superfastscyphozoa.wastelandwandering.WastelandWandering;

public abstract class WawaBiomeKeys {

    //commonwealth biomes
    public static final RegistryKey<Biome> WASTED_FOREST = registerWawaBiomeKeys("wasted_forest");
    public static final RegistryKey<Biome> GNARLED_FOREST = registerWawaBiomeKeys("gnarled_forest");

    //capital wasteland biomes
    public static final RegistryKey<Biome> SCORCHED_FOREST = registerWawaBiomeKeys("scorched_forest");

    private static RegistryKey<Biome> registerWawaBiomeKeys(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(WastelandWandering.MOD_ID, name));
    }
}
