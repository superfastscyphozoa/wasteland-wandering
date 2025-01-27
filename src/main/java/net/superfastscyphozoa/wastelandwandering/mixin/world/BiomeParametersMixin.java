package net.superfastscyphozoa.wastelandwandering.mixin.world;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import net.superfastscyphozoa.wastelandwandering.world.biome.WawaBiomeKeys;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VanillaBiomeParameters.class)
public abstract class BiomeParametersMixin {

	@WrapMethod(
			method = "getRegularBiome"
	)
	private RegistryKey<Biome> getWawaRegularBiomes(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, Operation<RegistryKey<Biome>> original) {
		if (temperature < 4) {
			if (humidity == 0 || humidity == 1) {
				return BiomeKeys.PLAINS;
			} else {

				if (weirdness.max() >= 0L && humidity == 2 && temperature == 3) {
					return BiomeKeys.PLAINS;
				} else {
					return WawaBiomeKeys.WASTED_FOREST;
				}
			}
		} else {
			return original.call(temperature, humidity, weirdness);
		}
    }

	@WrapMethod(
			method = "getBiomeOrWindsweptSavanna"
	)
	private RegistryKey<Biome> disableWindsweptSavanna
			(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, RegistryKey<Biome> biomeKey, Operation<RegistryKey<Biome>> original)
	{
        return biomeKey;
    }

	@WrapMethod(
			method = "getShoreBiome"
	)
	private RegistryKey<Biome> getWawaShoreBiomes(int temperature, int humidity, Operation<RegistryKey<Biome>> original)
	{
		if (temperature == 0) {
			return BiomeKeys.BEACH;
		} else {
			return temperature == 4 ? BiomeKeys.DESERT : BiomeKeys.BEACH;
		}
	}

	@WrapMethod(
			method = "getNearMountainBiome"
	)
	private RegistryKey<Biome> getWawaNearMountainBiomes(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, Operation<RegistryKey<Biome>> original)
	{
		if (temperature < 4) {
			if (humidity >= 0 && humidity <= 2) {
				return BiomeKeys.MEADOW;
			} else {
				return WawaBiomeKeys.WASTED_FOREST;
			}
		} else {
			return original.call(temperature, humidity, weirdness);
		}
	}

	@WrapMethod(
			method = "getWindsweptOrRegularBiome"
	)
	private RegistryKey<Biome> getWawaWindsweptBiomes(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, Operation<RegistryKey<Biome>> original)
	{
		if (humidity >= 0 && humidity <= 2) {
			return BiomeKeys.WINDSWEPT_GRAVELLY_HILLS;
		} else {
			return BiomeKeys.WINDSWEPT_HILLS;
		}
	}
}