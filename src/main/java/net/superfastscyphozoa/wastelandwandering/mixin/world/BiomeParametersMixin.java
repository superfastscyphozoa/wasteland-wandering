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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VanillaBiomeParameters.class)
public abstract class BiomeParametersMixin {

	@WrapMethod(
			method = "getRegularBiome"
	)
	private RegistryKey<Biome> getWawaRegularBiomes(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, Operation<RegistryKey<Biome>> original) {

		if (temperature < 4) {

			if (humidity == 0 || humidity == 1) {
				return BiomeKeys.PLAINS;
			}

			else if (humidity >= 2) {

				if (weirdness.max() >= 0L && humidity == 2 && temperature == 3) {
					return BiomeKeys.PLAINS;
				} else {
					return WawaBiomeKeys.WASTED_FOREST;
				}
			}

			else {
				return original.call(temperature, humidity, weirdness);
			}

		}

		else {
			return original.call(temperature, humidity, weirdness);
		}
    }
}