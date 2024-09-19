package net.superfastscyphozoa.wastelandwandering.mixin.world;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import net.superfastscyphozoa.wastelandwandering.world.biome.WawaBiomeKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VanillaBiomeParameters.class)
public abstract class BiomeParametersMixin {

	@Unique
	private final RegistryKey<Biome>[][] commonWastelandBiomes = new RegistryKey[][]{
			{WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
			{WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
			{WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
			{WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
			{WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST, WawaBiomeKeys.WASTED_FOREST},
	};

	@Unique
	private final RegistryKey<Biome>[][] uncommonWastelandBiomes = new RegistryKey[][]{
			{null, null, BiomeKeys.SNOWY_TAIGA, null, null},
			{null, null, null, null, BiomeKeys.OLD_GROWTH_PINE_TAIGA},
			{BiomeKeys.SUNFLOWER_PLAINS, null, null, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, null},
			{null, null, BiomeKeys.PLAINS, null, null},
			{null, null, null, null, null}
	};

	@Unique
	private final RegistryKey<Biome>[][] nearMountainWastelandBiomes = new RegistryKey[][]{
			{BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_TAIGA},
			{BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
			{BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.DARK_FOREST},
			{BiomeKeys.FOREST, BiomeKeys.FOREST, BiomeKeys.FOREST, BiomeKeys.FOREST, BiomeKeys.DARK_FOREST},
			{BiomeKeys.BADLANDS, BiomeKeys.BADLANDS, BiomeKeys.BADLANDS, BiomeKeys.WOODED_BADLANDS, BiomeKeys.WOODED_BADLANDS}
	};

	@Unique
	private final RegistryKey<Biome>[][] specialNearMountainWastelandBiomes = new RegistryKey[][]{
			{BiomeKeys.ICE_SPIKES, null, null, null, null},
			{BiomeKeys.MEADOW, null, BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.OLD_GROWTH_PINE_TAIGA},
			{BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST, null},
			{null, null, null, null, null},
			{BiomeKeys.ERODED_BADLANDS, BiomeKeys.ERODED_BADLANDS, null, null, null}
	};

	@Unique
	private final RegistryKey<Biome>[][] windsweptWastelandBiomes = new RegistryKey[][]{
			{BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_FOREST},
			{BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_FOREST},
			{BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_FOREST},
			{null, null, null, null, null},
			{null, null, null, null, null}
	};

	//overwrite biomes

	@Redirect(method = "getRegularBiome", at = @At(value = "FIELD",
			target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;commonBiomes:[[Lnet/minecraft/registry/RegistryKey;"))
	private RegistryKey<Biome>[][] getRegularBiome(VanillaBiomeParameters instance) {
		return commonWastelandBiomes;
	}
}